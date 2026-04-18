::------------------------------------------------------------------------------------------
:: name: scripts.copy.bat
:: desc: Copy scripts from source to destination and add to PATH
:: usage: scripts.copy <src> <dst>
:: example: scripts.copy C:\source C:\dest
::------------------------------------------------------------------------------------------
@echo off

set "SRC=%~1"
set "DST=%~2"

if "%SRC%"=="" (
    echo Usage: scripts.copy ^<src^> ^<dst^>
    exit /b 1
)

if "%DST%"=="" (
    echo Usage: scripts.copy ^<src^> ^<dst^>
    exit /b 1
)

if not exist "%SRC%" (
    echo [ERROR] Source directory does not exist: %SRC%
    exit /b 1
)

echo [CALLING] %~nx0
echo [INFO] Source directory: %SRC%
echo [INFO] Destination directory: %DST%
echo [INFO] File type filter: *.bat

call files.copy.bat bat "%SRC%" "%DST%"

echo [INFO] Adding to PATH: %DST%
call path.append.bat "%DST%"

echo [ENDING] %~nx0