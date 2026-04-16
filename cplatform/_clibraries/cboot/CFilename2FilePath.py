import sys
import json
from pathlib import Path

def filename_to_filepath(mapping_file: Path, filename: str) -> str:
    # Load JSON mapping
    try:
        with open(mapping_file, "r", encoding="utf-8") as f:
            mapping = json.load(f)
    except FileNotFoundError:
        sys.stderr.write(f"Error: Mapping file not found: {mapping_file}\n")
        sys.exit(1)
    except json.JSONDecodeError as e:
        sys.stderr.write(f"Error: Invalid JSON format in {mapping_file}: {e}\n")
        sys.exit(1)

    # Find and return the path
    if filename in mapping:
        return mapping[filename]
    else:
        sys.stderr.write(f"Error: '{filename}' not found in mapping file.\n")
        sys.exit(1)

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print(f"Usage: {Path(sys.argv[0]).name} <filename> <mapping_file>")
        sys.exit(1)

    filename = sys.argv[1]
    mapping_file = Path(sys.argv[2])

    filepath = filename_to_filepath(mapping_file, filename)
    print(filepath)
