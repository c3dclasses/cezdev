::-------------------------------------------------------------------------------------------------
:: Name: cp-file-types-from-src-to-dst.bat
:: Description: Copies all files of a specified type from the source folder to the destination folder
:: Usage: cp-file-types-from-src-to-dst.bat [src_folder] [dst_folder] [filetype]
:: Example: cp-file-types-from-src-to-dst.bat "C:\Source" "C:\Dest" *.txt
::-------------------------------------------------------------------------------------------------

@echo off

echo.
echo ::---------------------------------------------------------------------------------------
echo ::  %~nx0: Starting File Copy Utility
echo ::---------------------------------------------------------------------------------------

::------------------------------------------------------
:: Validate source folder
::------------------------------------------------------
IF "%~1"=="" (
    echo [ERROR] Source folder not specified.
    echo.
    echo Usage:
    echo   %~nx0 [src_folder] [dst_folder] [filetype]
    echo.
    exit /b 1
)

set "SRC_FOLDER=%~1"

::------------------------------------------------------
:: Validate destination folder
::------------------------------------------------------
IF "%~2"=="" (
    echo [ERROR] Destination folder not specified.
    echo.
    echo Usage:
    echo   %~nx0 [src_folder] [dst_folder] [filetype]
    echo.
    exit /b 1
)

set "DST_FOLDER=%~2"

::------------------------------------------------------
:: Determine file type
::------------------------------------------------------
set "FILE_TYPE=%~3"
if "%FILE_TYPE%"=="" set "FILE_TYPE=*.bat"

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
    echo   Copying: %%~nxG
    copy "%%G" "%DST_FOLDER%" >nul
)

echo ::---------------------------------------------------------------------------------------
echo ::  %~nx0: Ending File Copy Utility
echo ::---------------------------------------------------------------------------------------
echo.