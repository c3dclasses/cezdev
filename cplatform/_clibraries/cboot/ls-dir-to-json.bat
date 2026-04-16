@echo off
setlocal enabledelayedexpansion

:: Check arguments
if "%~1"=="" (
    echo [ERROR] Usage: %~n0.bat ^<directory-path^> ^<output-json-file^>
    exit /b 1
)
if "%~2"=="" (
    echo [ERROR] Usage: %~n0.bat ^<directory-path^> ^<output-json-file^>
    exit /b 1
)

rem Arguments
set "TARGET_DIR=%~1"
set "OUTFILE=%~2"

rem Remove trailing backslash if present
if "%TARGET_DIR:~-1%"=="\" set "TARGET_DIR=%TARGET_DIR:~0,-1%"

rem Start the JSON file
(echo {) > "%OUTFILE%"

set "first=1"

rem Loop through directories only
for /D %%D in ("%TARGET_DIR%\*") do (
    rem %%D is each directory under TARGET_DIR (non-recursive)
    set "dirname=%%~nxD"
    set "dirpath=%%~fD"
    rem Escape backslashes for JSON
    set "jsonpath=!dirpath:\=\\!"
    if "!first!"=="1" (
        set "first=0"
    ) else (
        echo ,>> "%OUTFILE%"
    )
    echo   "!dirname!": "!jsonpath!" >> "%OUTFILE%"
)

rem Close JSON
(echo }) >> "%OUTFILE%"

endlocal
