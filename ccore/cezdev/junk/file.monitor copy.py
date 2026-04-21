"""
name: file.monitor.py
desc: Monitors multiple directories for file changes using watchdog library.
usage:
    python file.monitor.py <directories> [callback-scripts] [exclude-patterns]
    
    All arguments support comma-separated values for multiple entries.
    
examples:
    # Single directory
    python file.monitor.py "C:\myproject"
    
    # Multiple directories
    python file.monitor.py "C:\project1,C:\project2,C:\project3"
    
    # Single directory with single callback
    python file.monitor.py "C:\myproject" "callback.bat"
    
    # Multiple directories with multiple callbacks
    python file.monitor.py "C:\proj1,C:\proj2" "handler1.bat,handler2.bat"
    
    # With exclude patterns (regex, already supports | for OR)
    python file.monitor.py "C:\myproject" "callback.bat" "\.git|\.pyc|__pycache__"
    
    # Multiple exclude patterns (comma-separated, combined with |)
    python file.monitor.py "C:\myproject" "" "\.git,\.pyc,__pycache__,node_modules"
"""

import sys
import re
import time
import subprocess
from datetime import datetime
from pathlib import Path

try:
    from watchdog.observers import Observer
    from watchdog.events import FileSystemEventHandler
except ImportError:
    print("[ERROR] watchdog library not installed.")
    print("Run: pip install watchdog")
    sys.exit(1)


def parse_comma_list(value):
    """Parse a comma-separated string into a list, stripping whitespace."""
    if not value:
        return []
    return [item.strip() for item in value.split(',') if item.strip()]


def build_exclude_pattern(patterns_str):
    """Build a combined regex pattern from comma-separated patterns."""
    if not patterns_str:
        return None
    patterns = parse_comma_list(patterns_str)
    if not patterns:
        return None
    # Combine patterns with | (OR), escaping if needed
    # If the input already contains |, treat as raw regex
    if '|' in patterns_str and ',' not in patterns_str:
        return patterns_str
    # Otherwise combine comma-separated patterns with |
    combined = '|'.join(patterns)
    return combined


class FileChangeHandler(FileSystemEventHandler):
    """Handles file system events and prints them."""
    
    def __init__(self, callback_scripts=None, exclude_pattern=None, watch_dirs=None):
        super().__init__()
        self.callback_scripts = callback_scripts or []
        self.exclude_pattern = re.compile(exclude_pattern) if exclude_pattern else None
        self.watch_dirs = watch_dirs or []
        self._file_mtimes = {}  # Track file modification times to filter out read-only access
    
    def _should_skip(self, filepath):
        """Check if the file/dir should be skipped based on exclude pattern."""
        if self.exclude_pattern:
            return self.exclude_pattern.search(filepath) is not None
        return False
    
    def _get_relative_path(self, filepath):
        """Get relative path from the watch directory."""
        filepath = Path(filepath)
        for watch_dir in self.watch_dirs:
            try:
                return filepath.relative_to(watch_dir)
            except ValueError:
                continue
        return filepath
    
    def _extract_platform_info(self, filepath):
        """Extract platform and platform-name from the relative path.
        
        Path format: <platform>/<platform-name>/<filename>
        Returns: (platform, platform_name) or (None, None) if can't extract
        """
        rel_path = self._get_relative_path(filepath)
        parts = rel_path.parts
        if len(parts) >= 2:
            return parts[0], parts[1]
        return None, None
    
    def _get_callback_script(self, platform):
        """Get the callback script path based on platform.
        
        Returns: path to <platform>.modified.bat or None if not found
        Only returns a callback if it was explicitly passed by the user.
        """
        if not platform:
            return None
        callback_name = f"{platform}.modified.bat"
        # Only return callback if it was explicitly passed by user
        for callback_script in self.callback_scripts:
            if Path(callback_script).name == callback_name:
                return callback_script
        return None

    def _print_change(self, modified_type, filepath, item_kind):
        """Print file change in structured format."""
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        filename = Path(filepath).name
        rel_path = self._get_relative_path(filepath)
        print(f"[{timestamp}]")
        print(f"  filename: {filename}")
        print(f"  path: {rel_path}")
        print(f"  type: {item_kind}")
        print(f"  modified: {modified_type}")
    
    def _call_callbacks(self, modified_type, filepath, item_kind):
        """Call callback script based on platform extracted from filepath."""
        if not self.callback_scripts:
            print()
            return
        
        platform, platform_name = self._extract_platform_info(filepath)
        
        if not platform or not platform_name:
            print()
            return
        
        callback_script = self._get_callback_script(platform)
        callback_name = f"{platform}.modified.bat"
        
        if not callback_script or not Path(callback_script).exists():
            print(f"  callback: {callback_name} does not exist")
            print()
            return
        
        print(f"  callback-platform: {platform}")
        print(f"  callback-platform-name: {platform_name}")
        print(f"  callback: {callback_name}")
        print(f"  callback-path: {self._get_relative_path(callback_script)}")
        
        try:
            subprocess.run(
                [callback_script, modified_type, filepath, item_kind, platform, platform_name],
                shell=False,
                check=False
            )
        except Exception as e:
            print(f"[ERROR] Callback '{callback_script}' failed: {e}")
        print()
    
    def on_created(self, event):
        if self._should_skip(event.src_path):
            return
        item_kind = "DIR" if event.is_directory else "FILE"
        self._print_change("CREATE", event.src_path, item_kind)
        self._call_callbacks("CREATE", event.src_path, item_kind)
    
    def on_modified(self, event):
        if event.is_directory:
            return
        if self._should_skip(event.src_path):
            return
        
        # Only trigger UPDATE if file was actually modified (not just accessed/read)
        # Requires file to be previously tracked to filter out first-time read events
        try:
            current_mtime = Path(event.src_path).stat().st_mtime
            last_mtime = self._file_mtimes.get(event.src_path)
            
            if last_mtime is None:
                # First time seeing this file - just track it, don't trigger UPDATE
                # This filters out read-only access triggering false UPDATE events
                self._file_mtimes[event.src_path] = current_mtime
                return
            
            if current_mtime == last_mtime:
                # File was accessed but not modified, skip
                return
            
            self._file_mtimes[event.src_path] = current_mtime
        except (FileNotFoundError, OSError):
            # File was deleted before we could stat it, skip (on_deleted handles this)
            return
        
        self._print_change("UPDATE", event.src_path, "FILE")
        self._call_callbacks("UPDATE", event.src_path, "FILE")
    
    def on_deleted(self, event):
        if self._should_skip(event.src_path):
            return
        # Clean up mtime tracking for deleted files
        self._file_mtimes.pop(event.src_path, None)
        item_kind = "DIR" if event.is_directory else "FILE"
        self._print_change("DELETE", event.src_path, item_kind)
        self._call_callbacks("DELETE", event.src_path, item_kind)
    
    def on_moved(self, event):
        if self._should_skip(event.src_path) and self._should_skip(event.dest_path):
            return
        item_kind = "DIR" if event.is_directory else "FILE"
        self._print_change("RENAME", event.dest_path, item_kind)
        self._call_callbacks("UPDATE", event.dest_path, item_kind)


def main():
    if len(sys.argv) < 2:
        print("[ERROR] Directory path(s) not provided.")
        print()
        print("Usage:")
        print("    python file.monitor.py <directories> [callback-scripts] [exclude-patterns]")
        print()
        print("    All arguments support comma-separated values for multiple entries.")
        print()
        print("Examples:")
        print("    python file.monitor.py \"C:\\myproject\"")
        print("    python file.monitor.py \"C:\\proj1,C:\\proj2\"")
        print("    python file.monitor.py \"C:\\myproject\" \"callback.bat\"")
        print("    python file.monitor.py \"C:\\proj1,C:\\proj2\" \"handler1.bat,handler2.bat\"")
        print("    python file.monitor.py \"C:\\myproject\" \"\" \"\\.git,\\.pyc,__pycache__\"")
        print()
        sys.exit(1)
    
    # Parse arguments
    watch_dirs = parse_comma_list(sys.argv[1])
    callback_scripts = parse_comma_list(sys.argv[2]) if len(sys.argv) > 2 else []
    exclude_pattern = build_exclude_pattern(sys.argv[3]) if len(sys.argv) > 3 else None
    
    # Validate directories
    valid_dirs = []
    for watch_dir in watch_dirs:
        if not Path(watch_dir).exists():
            print(f"[WARNING] Directory '{watch_dir}' does not exist, skipping.")
            continue
        if not Path(watch_dir).is_dir():
            print(f"[WARNING] '{watch_dir}' is not a directory, skipping.")
            continue
        valid_dirs.append(watch_dir)
    
    if not valid_dirs:
        print("[ERROR] No valid directories to watch.")
        sys.exit(1)
    
    print()
    print("=" * 50)
    print("  CEZDEV File Monitor (Python)")
    print("=" * 50)
    print()
    print(f"[INFO] Watching {len(valid_dirs)} director{'y' if len(valid_dirs) == 1 else 'ies'}:")
    for d in valid_dirs:
        print(f"       - {d}")
    if callback_scripts:
        print(f"[INFO] Callback script{'s' if len(callback_scripts) > 1 else ''}:")
        for s in callback_scripts:
            print(f"       - {s}")
    if exclude_pattern:
        print(f"[INFO] Exclude pattern:")
        print(f"       {exclude_pattern}")
    print("[INFO] Press Ctrl+C to stop monitoring.")
    print()
    print("-" * 50)
    print(f"[STATUS] Monitoring started at {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    print("-" * 50)
    print()
    
    event_handler = FileChangeHandler(callback_scripts, exclude_pattern, valid_dirs)
    observer = Observer()
    
    # Schedule all directories
    for watch_dir in valid_dirs:
        observer.schedule(event_handler, watch_dir, recursive=True)
    
    observer.start()
    
    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        print()
        print("[STATUS] Stopping file monitor...")
        observer.stop()
    
    observer.join()
    print("[STATUS] File monitor stopped.")


if __name__ == "__main__":
    main()
