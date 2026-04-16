@echo off
setlocal EnableExtensions EnableDelayedExpansion

REM --- 1) First arg = target env var name
if "%~1"=="" goto :usage
set "VARNAME=%~1"
shift /1

REM --- 2) Build the command line from the remaining args (no %* pitfalls)
if "%~1"=="" goto :usage
set "CMDLINE=%~1"
:build
shift /1
if "%~1"=="" goto :run
set "CMDLINE=%CMDLINE% %~1"
goto :build

:run
REM --- 3) Execute via cmd /c so built-ins (date, dir, etc.) work
set "_ACCUM="
for /f "usebackq tokens=* delims=" %%A in (`cmd /c "%CMDLINE%"`) do (
    if defined _ACCUM (
        set "_ACCUM=!_ACCUM! %%A"
    ) else (
        set "_ACCUM=%%A"
    )
)

REM --- 4) Persist to caller's environment
endlocal & set "%VARNAME%=%_ACCUM%"
goto :eof

:usage
echo [ERROR] Usage: %~n0 VAR_NAME command [args...]
exit /b 1
