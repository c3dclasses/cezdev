@echo off
::-----------------------------------------------------------------------------------------------------------------------------------
:: name: cp-file-types-from-src-to-dst-ex.bat
:: desc: Copies the folder structure and selected files from a source directory to a destination directory
:: usage: cp-file-types-from-src-to-dst-ex <srcdir> <dstdir> [formats-to-copy] [formats-to-skip]
:: example: call cp-file-types-from-src-to-dst-ex "%src%" "%dst%/src/main/java" ".java" "UnitTest.java,unittest.java" "CUnitTest.java,CMockUnitTest.java"
::-----------------------------------------------------------------------------------------------------------------------------------

echo.
echo ::---------------------------------------------------------------------------------------
echo ::  %~nx0: Starting File Copy Utility
echo ::---------------------------------------------------------------------------------------

:: Save the current directory
set "CPFILES_HOME=%CD%"

:: Check CBOOT variable
if not defined CBOOT (
    echo [ERROR] CBOOT is not set.
    exit /b 1
)

:: Change to CBOOT directory
cd /d "%CBOOT%" || (
    echo [ERROR] Unable to change to "%CBOOT%".
    exit /b 1
)

:: Execute Java command with all passed arguments
java CCopyFoldersCommand %*

echo ::---------------------------------------------------------------------------------------
echo ::  %~nx0: Ending File Copy Utility
echo ::---------------------------------------------------------------------------------------
echo.

:: Return to the original directory
cd /d "%CPFILES_HOME%"
