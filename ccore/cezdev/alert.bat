@echo off
::------------------------------------------------------------------------------------------
:: name: alert.bat
:: desc: Shows an alert message in the command line
:: usage: alert <message> <title> <warn>
::------------------------------------------------------------------------------------------
echo [CALLING] %~nx0

:: Save the current directory
set "ALERT_HOME=%CD%"

:: Change to the script's directory
cd /d "%~dp0"

:: Compile if AlertCommand.class does not exist
if exist "AlertCommand.class" goto :run
javac AlertCommand.java
if errorlevel 1 (
    echo [ERROR] Failed to compile AlertCommand.java
    cd /d "%ALERT_HOME%"
    exit /b 1
)

:run
:: Run the Java command (pass all arguments)
java AlertCommand %*

echo [ENDING] %~nx0

:: Return to the original directory
cd /d "%ALERT_HOME%"
