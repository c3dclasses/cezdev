@echo off
::------------------------------------------------------------------------------------------
:: name: scripts.call.bat
:: desc: Finds and executes create scripts matching a pattern in a directory
:: usage: scripts.call.bat <srcpath> <scriptpattern>
:: example: scripts.call.bat "%CENVIRONMENTS%" "cjava"
::------------------------------------------------------------------------------------------

echo [CALLING] %~nx0

set "SRCPATH=%~1"
set "SCRIPTPATTERN=%~2"

if "%SRCPATH%"=="" (
    echo [ERROR] Source path of scripts to call not provided.
    goto :end
)

if "%SCRIPTPATTERN%"=="" (
    echo [ERROR] Script pattern of scripts to call not provided.
    goto :end
)

echo [INFO] Source path of scripts to call: %SRCPATH%
echo [INFO] Pattern of scripts to call: %SCRIPTPATTERN%

for /r "%SRCPATH%" %%F in (%SCRIPTPATTERN%) do (
    if exist "%%F" (
        echo [EXECUTING] %%~nxF
        call "%%F"
    )
)

:end
echo [ENDING] %~nx0