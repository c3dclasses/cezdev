@echo off
::------------------------------------------------------------------------------------------
:: name: open.bat
:: desc: Opens a file or folder using the default application
:: usage: open <path>
::------------------------------------------------------------------------------------------

:: Save the current directory
set "OPEN_HOME=%CD%"

:: Change to CBOOT
if not defined CBOOT (
    echo [ERROR] CBOOT is not set.
    exit /b 1
)
cd /d "%CBOOT%" || (
    echo [ERROR] Unable to change to "%CBOOT%".
    exit /b 1
)

:: Run the Java command (pass all arguments)
java CMessageBoxCommand %*

:: Return to the original directory
cd /d "%ALERT_HOME%"
