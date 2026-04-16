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

echo [INFO] Copying scripts to %DST%...
call files.copy.bat bat "%SRC%" "%DST%"

echo [INFO] Adding to PATH: %DST%
call append-dir-to-path.bat "%DST%"
call append-dir-to-path.bat "%CEZDEV%"