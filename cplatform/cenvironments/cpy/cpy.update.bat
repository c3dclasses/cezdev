::-------------------------------------------------------------------------------------------------
:: name: cpy.update.bat
:: desc: Updates the Python files in c3dclasses project to a Python package structure
:: usage: cpy.update.bat
::-------------------------------------------------------------------------------------------------

@echo off

:: set the src and dst directories to write from and to
set src=%C3DCLASSES%
set dst=%C3DCLASSES_PY%

:: Ensure destination directory exists
if not exist "%dst%" mkdir "%dst%"

:: move the Python src files to the destination (excluding test files)
call cp-file-types-from-src-to-dst-ex "%src%" "%dst%\c3dclasses" ".py" "test_,_test.py" "unit_test.py,mock_test.py"

:: move test Python files to tests directory
call cp-file-types-from-src-to-dst-ex "%src%" "%dst%\tests" "test_,_test.py"

:: Copy configuration files from environment directory
set "CONFIG_SRC=%~dp0"
echo [ACTION] Copying setup.cfg from: %CONFIG_SRC%setup.cfg
copy /Y "%CONFIG_SRC%setup.cfg" "%dst%\setup.cfg"

echo [ACTION] Copying pyproject.toml from: %CONFIG_SRC%pyproject.toml
copy /Y "%CONFIG_SRC%pyproject.toml" "%dst%\pyproject.toml"

echo [ACTION] Copying README.md from: %CONFIG_SRC%README.md
copy /Y "%CONFIG_SRC%README.md" "%dst%\README.md"

echo [ACTION] Copying LICENSE from: %CONFIG_SRC%LICENSE
copy /Y "%CONFIG_SRC%LICENSE" "%dst%\LICENSE"

:: Verify setup.cfg was copied
if not exist "%dst%\setup.cfg" (
    echo [ERROR] Failed to copy setup.cfg to %dst%
    exit /b 1
)

:: Generate filenames.json BEFORE running tests (required by __.dir_path())
call list-files %CMETADATA%\c3dclassessdk.filenames.json %src%

:: move into the folder and run Python install and tests
cd /d "%dst%"
echo [ACTION] Installing Python package in editable mode...
call python -m pip install -e .

echo [ACTION] Running pytest...
call pytest

:: list all the files in the c3dclasses folder, and c3dclasses_py folder
call list-files %CMETADATA%\c3dclasses_py.filenames.json %dst%
call list-files %CMETADATA%\c3dclasses.filenames.json %src%