#!/usr/bin/env python3
import os
import sys
import json

class PathListCommand:
    @staticmethod
    def main(args):
        if len(args) < 2:
            print("[ERROR] usage: PathListCommand <required:filetowriteto> <required:srcdir1> [srcdir2 ...]")
            print("[INFO] example 1: PathListCommand C:\\cezdev\\meta\\c3dclassessdk-src.json C:\\c3dclassessdk\\src")
            return

        print("[CALLING] PathListCommand")

        file_to_write_to = args[0]
        bappend = False  # Modify if append behavior needed

        print(f"[INFO] File to write to: {file_to_write_to}")
        print(f"[INFO] Source directories: {args[1:]}")
        print("[STEP] Building file-to-folder mapping...")

        file_to_folder = {}
        for src_dir in args[1:]:
            src_path = os.path.abspath(src_dir)
            file_to_folder.update(PathListCommand.to_dict_file_to_folder(src_path))

        print(f"[INFO] Total files mapped: {len(file_to_folder)}")
        print("[STEP] Writing JSON mapping to file...")

        # Write JSON mapping to file
        PathListCommand.set_file_contents(file_to_write_to, json.dumps(file_to_folder, indent=2), bappend)

        print("[ENDING] PathListCommand")

    @staticmethod
    def to_dict_file_to_folder(srcfolder):
        mapping = {}
        if os.path.isdir(srcfolder):
            for entry in os.listdir(srcfolder):
                mapping.update(PathListCommand.to_dict_file_to_folder(os.path.join(srcfolder, entry)))
        else:
            mapping[os.path.basename(srcfolder)] = srcfolder.replace("\\", "/")
        return mapping

    @staticmethod
    def set_file_contents(filename, contents, append):
        if not contents:
            return False
        try:
            mode = "a" if append else "w"
            with open(filename, mode, encoding="utf-8") as f:
                f.write(contents)
            return True
        except Exception as ex:
            print(f"Error writing to file {filename}: {ex}")
            return False

if __name__ == "__main__":
    PathListCommand.main(sys.argv[1:])
