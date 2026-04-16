from pathlib import Path
import sys

def load_env_file(file_path: str) -> dict[str, str]:
    env_vars = {}
    with open(file_path, "r", encoding="utf-8") as f:
        for line in f:
            if "=" in line:
                key, val = line.strip().split("=", 1)
                env_vars[key] = val
    return env_vars

def compare_env(baseline_file: str, current_file: str):
    baseline = load_env_file(baseline_file)
    current = load_env_file(current_file)

    new_vars = {k: v for k, v in current.items() if k not in baseline}
    changed_vars = {k: (baseline[k], current[k]) for k in current if k in baseline and current[k] != baseline[k]}

    return new_vars, changed_vars

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python compare_env.py <baseline_file> <current_file>")
        sys.exit(1)

    baseline_file = sys.argv[1]
    current_file = sys.argv[2]

    new_vars, changed_vars = compare_env(baseline_file, current_file)

    print("\n=== New Variables ===")
    if new_vars:
        print(f"{'Name':<30} | Value")
        print("-"*70)
        for k, v in new_vars.items():
            print(f"{k:<30} | {v}")
    else:
        print("None")

    print("\n=== Changed Variables ===")
    if changed_vars:
        print(f"{'Name':<30} | Old Value -> New Value")
        print("-"*70)
        for k, (old, new) in changed_vars.items():
            print(f"{k:<30} | {old} -> {new}")
    else:
        print("None")
