@echo off
setlocal enabledelayedexpansion

:: First arg = env var name
set "envvar=%~1"

:: Remove first arg from %* (everything after var name is the command)
set "allargs=%*"
for /f "tokens=1* delims= " %%A in ("!allargs!") do set "cmd=%%B"

:: Run the command and grab first line of output
for /f "usebackq delims=" %%A in (`!cmd!`) do (
    set "value=%%A"
    goto :done
)

:done
call setvar "%envvar%" "!value!"
endlocal
