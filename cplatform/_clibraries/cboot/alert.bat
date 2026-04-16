@echo off
::------------------------------------------------------------------------------------------
:: name: alert.bat
:: desc: Shows an alert message in the command line
:: usage: alert <message> <title> <warn>
::------------------------------------------------------------------------------------------

:: Save the current directory
set "ALERT_HOME=%CD%"

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
