::------------------------------------------------------------------------------------------
:: name: monitor-file-changes.bat
:: desc: Monitors a directory for file changes using polling.
:: usage:
::   monitor-file-changes.bat <directory-path> [interval-seconds]
::   monitor-file-changes.bat "C:\myproject" 5
::------------------------------------------------------------------------------------------
@echo off
setlocal enabledelayedexpansion

if "%~1"=="" (
    echo [ERROR] Directory path not provided.
    echo.
    echo Usage:
    echo   %~n0.bat ^<directory-path^> [interval-seconds]
    echo.
    exit /b 1
)

set "WATCHDIR=%~1"
set "INTERVAL=%~2"
if "%INTERVAL%"=="" set "INTERVAL=3"

if not exist "%WATCHDIR%" (
    echo [ERROR] Directory "%WATCHDIR%" does not exist.
    exit /b 1
)

set "PREVFILE=%TEMP%\filewatch_prev_%RANDOM%.txt"
set "CURRFILE=%TEMP%\filewatch_curr_%RANDOM%.txt"

echo.
echo ==================================================
echo   CEZDEV File Monitor
echo ==================================================
echo.
echo [INFO] Watching: %WATCHDIR%
echo [INFO] Interval: %INTERVAL% seconds
echo [INFO] Press Ctrl+C to stop.
echo.
echo [STATUS] Started at %DATE% %TIME%
echo --------------------------------------------------
echo.

:: Capture initial state
dir /s /b /a-d "%WATCHDIR%" > "%PREVFILE%" 2>nul

:Loop
timeout /t %INTERVAL% /nobreak >nul

:: Capture current state
dir /s /b /a-d "%WATCHDIR%" > "%CURRFILE%" 2>nul

:: Check for new files
for /f "usebackq delims=" %%F in ("%CURRFILE%") do (
    set "FOUND="
    for /f "usebackq delims=" %%P in ("%PREVFILE%") do (
        if "%%F"=="%%P" set "FOUND=1"
    )
    if not defined FOUND (
        echo [%TIME%] [NEW]     %%~nxF
    )
)

:: Check for deleted files
for /f "usebackq delims=" %%P in ("%PREVFILE%") do (
    set "FOUND="
    for /f "usebackq delims=" %%F in ("%CURRFILE%") do (
        if "%%P"=="%%F" set "FOUND=1"
    )
    if not defined FOUND (
        echo [%TIME%] [DELETED] %%~nxP
    )
)

:: Check for modified files (compare file sizes/dates)
for /f "usebackq delims=" %%F in ("%CURRFILE%") do (
    if exist "%%F" (
        for %%A in ("%%F") do set "CURRINFO=%%~zA%%~tA"
        
        :: Check if file existed before
        set "EXISTED="
        for /f "usebackq delims=" %%P in ("%PREVFILE%") do (
            if "%%F"=="%%P" set "EXISTED=1"
        )
        
        if defined EXISTED (
            :: Compare with stored info
            if not "!CURRINFO!"=="!PREVINFO_%%~nxF!" (
                if defined PREVINFO_%%~nxF (
                    echo [%TIME%] [MODIFIED] %%~nxF
                )
            )
        )
        set "PREVINFO_%%~nxF=!CURRINFO!"
    )
)

:: Update previous state
copy /y "%CURRFILE%" "%PREVFILE%" >nul

goto Loop
