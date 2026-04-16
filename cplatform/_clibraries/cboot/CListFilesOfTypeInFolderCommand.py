import os
import sys

def list_files_with_relative_path(base_dir, extension):
    """
    Recursively list all files with the given extension,
    returning filename and path relative to base_dir
    """
    file_entries = []

    base_dir = os.path.abspath(base_dir)

    for root, _, files in os.walk(base_dir):
        for file in files:
            if file.lower().endswith(extension.lower()):
                rel_path = os.path.relpath(root, base_dir)
                file_entries.append([file, rel_path])

    return file_entries


def print_table(entries):
    if not entries:
        print("No files found.")
        return

    headers = ["Filename", "Relative Path"]
    col_widths = [len(h) for h in headers]

    for row in entries:
        for i, cell in enumerate(row):
            col_widths[i] = max(col_widths[i], len(str(cell)))

    # Print header
    header_row = " | ".join(h.ljust(col_widths[i]) for i, h in enumerate(headers))
    sep_row = "-+-".join("-" * col_widths[i] for i in range(len(headers)))
    print(header_row)
    print(sep_row)

    # Print rows
    for row in entries:
        print(" | ".join(str(cell).ljust(col_widths[i]) for i, cell in enumerate(row)))


if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python list_files_relative_table_multi.py <extension> <dir1> [dir2 ... dirN]")
        print("Example: python list_files_relative_table_multi.py .bat C:\\Users\\kevle\\Desktop\\c3dclasses C:\\OtherDir")
        sys.exit(1)

    extension = sys.argv[1]
    directories = sys.argv[2:]

    all_entries = []
    for dir_path in directories:
        if os.path.isdir(dir_path):
            all_entries.extend(list_files_with_relative_path(dir_path, extension))
        else:
            print(f"Warning: {dir_path} is not a valid directory. Skipping.")

    print_table(all_entries)
