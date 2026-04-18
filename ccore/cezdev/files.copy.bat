::------------------------------------------------------------------------------------------
:: name: files.copy.bat
:: desc: Copy files of a specific type from src to dst directory
:: usage: files.copy <extension> <src> <dst>
:: example: files.copy bat C:\source C:\dest
::------------------------------------------------------------------------------------------
@echo off

set "EXT=%~1"
set "SRC_FOLDER=%~2"
set "DST_FOLDER=%~3"

if "%EXT%"=="" (
    echo Usage: files.copy ^<extension^> ^<src^> ^<dst^>
    exit /b 1
)

if "%SRC_FOLDER%"=="" (
    echo Usage: files.copy ^<extension^> ^<src^> ^<dst^>
    exit /b 1
)

if "%DST_FOLDER%"=="" (
    echo Usage: files.copy ^<extension^> ^<src^> ^<dst^>
    exit /b 1
)

set "FILE_TYPE=*.%EXT%"

echo [CALLING] %~nx0
echo [INFO] Source directory: %SRC_FOLDER%
echo [INFO] Destination directory: %DST_FOLDER%
echo [INFO] File type filter: %FILE_TYPE%

::------------------------------------------------------
:: Ensure destination directory exists
::------------------------------------------------------
if not exist "%DST_FOLDER%" (
    echo [ACTION] Creating destination directory...
    mkdir "%DST_FOLDER%"
    echo [OK] Directory created.
) else (
    echo [OK] Destination directory already exists.
)

::------------------------------------------------------
:: Copy files
::------------------------------------------------------
echo [STEP] Copying files...

for /r "%SRC_FOLDER%" %%G in (%FILE_TYPE%) do (
    echo [COPYING]: %%~nxG
    copy "%%G" "%DST_FOLDER%" >nul
)

echo [ENDING] %~nx0
