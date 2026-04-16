@echo off
REM =====================================================
REM Usage:
REM   is-file-mod-date-newer-than-dir.bat "C:\path\to\dir" "C:\path\to\reference.txt" [extension]
REM Returns:
REM   1 = if any file in <dir> (recursively) is newer than <referenceFile>
REM   0 = otherwise
REM   2 = error (bad usage or missing paths)
REM =====================================================

setlocal enabledelayedexpansion

if "%~2"=="" (
  echo [ERROR] Usage: %~nx0 directory referenceFile [extension]
  exit /b 2
)

set "dirPath=%~1"
set "refFile=%~2"
set "ext=%~3"

if not exist "%dirPath%" (
  echo [ERROR] Directory "%dirPath%" not found
  exit /b 2
)
if not exist "%refFile%" (
  echo [ERROR] Reference file "%refFile%" not found
  exit /b 2
)

REM Normalize extension filter
if defined ext (
  if "%ext:~0,1%"=="." (
    set "search=*.%ext:~1%"
  ) else (
    set "search=*.%ext%"
  )
) else (
  set "search=*"
)

REM Get reference file's last modified time (sortable)
for %%F in ("%refFile%") do set "refDate=%%~tF"
for /f "tokens=1,2 delims= " %%a in ("%refDate%") do (
  set "refD=%%a"
  set "refT=%%b"
)
set "refSortable=%refD:~6,4%%refD:~0,2%%refD:~3,2%%refT:~0,2%%refT:~3,2%%refT:~6,2%"

set "isNewer=0"

REM Loop through files recursively with extension filter
for /r "%dirPath%" %%F in (%search%) do (
  set "fileDate=%%~tF"
  for /f "tokens=1,2 delims= " %%a in ("!fileDate!") do (
    set "fD=%%a"
    set "fT=%%b"
  )
  set "fileSortable=!fD:~6,4!!fD:~0,2!!fD:~3,2!!fT:~0,2!!fT:~3,2!!fT:~6,2!"

  if "!fileSortable!" GTR "!refSortable!" (
    echo [INFO] Found newer file: %%F
    set "isNewer=1"
    goto :done
  )
)

:done
endlocal & exit /b %isNewer%
