@echo off
::------------------------------------------------------------------------------------------
:: name: append-dir-to-path.bat
:: desc: Creates the given directory if it doesn't exist, then appends it to PATH
:: usage: append-dir-to-path <directory>
::------------------------------------------------------------------------------------------

if "%~1"=="" (
    echo [ERROR] Usage: %~nx0 ^<directory^>
    exit /b 1
)

:: Normalize argument
set "ADTP_DIR=%~1"
:: Remove quotes if passed
set "ADTP_DIR=%ADTP_DIR:"=%"

:: Create directory if it doesn’t exist
if not exist "%ADTP_DIR%" (
    echo [INFO] Directory "%ADTP_DIR%" does not exist. Creating it...
    mkdir "%ADTP_DIR%" || (
        echo [ERROR] Failed to create "%ADTP_DIR%".
        exit /b 1
    )
) else (
    echo [INFO] Directory "%ADTP_DIR%" already exists.
)

:: Append to PATH if not already present
echo %PATH% | find /I "%ADTP_DIR%" >nul
if errorlevel 1 (
    set "PATH=%PATH%;%ADTP_DIR%"
    echo [INFO] Added "%ADTP_DIR%" to PATH for this session.
) else (
    echo [INFO] "%ADTP_DIR%" is already in PATH.
)

exit /b 0
