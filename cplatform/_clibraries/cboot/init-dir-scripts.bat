@echo off
:: Check if a directory path was provided
if "%~1"=="" (
    echo [ERROR] Usage: %~nx0 [path-to-scripts] [file-type-of_scripts]
    exit /b 1
)

:: Check if a file type of the script was provided
if "%~2"=="" (
    echo [ERROR] Usage: %~nx0 [path-to-scripts] [file-type-of_scripts]
    exit /b 1
)

:: Set the directory from the first argument
set "SCRIPT_DIR=%~1"

:: Check if the directory exists
if not exist "%SCRIPT_DIR%" (
    echo [ERROR] The directory "%SCRIPT_DIR%" does not exist.
    exit /b 1
)

:: Change to that directory
pushd "%SCRIPT_DIR%" || (echo [ERROR] Failed to change to %SCRIPT_DIR% & exit /b 1)

:: Get the subtype of the files to run in the directory
set "SCRIPT_TYPE=%~2"

echo [INFO] Looking for files matching: %SCRIPT_TYPE% in %SCRIPT_DIR%

:: Loop through files matching the given pattern
for %%F in (%SCRIPT_TYPE%) do (
    echo [INFO] Running %%F...
    call "%%F"
    echo.
)

:: Return to original directory
popd

echo [INFO] All scripts executed.
pause