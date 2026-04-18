::------------------------------------------------------------------------------------------
:: name: directory.copy.bat
:: desc: Copy files of a specific type from src to dst directory (extended version)
::       Copies folder structure and selected files from source to destination
:: usage: directory.copy <src> <dst> <formats-to-copy> [formats-to-skip] [files-to-copy]
:: example: directory.copy "%src%" "%dst%\src\main\java" ".java" "UnitTest.java,unittest.java" "CUnitTest.java"
::------------------------------------------------------------------------------------------
@echo off

echo [CALLING] %~nx0
echo [INFO] Source directory: %~1
echo [INFO] Destination directory: %~2

:: Save the current directory
set "CPFILES_HOME=%CD%"

:: Change to script directory (where DirectoryCopyCommand.java is located)
cd /d "%~dp0" || (
    echo [ERROR] Unable to change to "%~dp0".
    exit /b 1
)

:: Check if DirectoryCopyCommand.class exists, compile if needed
if not exist "DirectoryCopyCommand.class" (
    echo [INFO] DirectoryCopyCommand.class not found. Compiling...
    if not exist "DirectoryCopyCommand.java" (
        echo [ERROR] DirectoryCopyCommand.java not found in "%~dp0".
        cd /d "%CPFILES_HOME%"
        exit /b 1
    )
    javac DirectoryCopyCommand.java
    if errorlevel 1 (
        echo [ERROR] Failed to compile DirectoryCopyCommand.java.
        cd /d "%CPFILES_HOME%"
        exit /b 1
    )
    echo [INFO] Compilation successful.
)

:: Execute Java command with all passed arguments
java DirectoryCopyCommand %*

echo [ENDING] %~nx0

:: Return to the original directory
cd /d "%CPFILES_HOME%"
