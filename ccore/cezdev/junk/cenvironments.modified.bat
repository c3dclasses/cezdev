::------------------------------------------------------------------------------------------
:: name: cenvironments.modified.bat
:: desc: Handles file modification events for environments (cenvironments).
:: usage: cenvironments.modified <modified-type> <filepath> <item-kind> <platform> <platform-name>
::        modified-type: CREATE, UPDATE, DELETE, RENAME
::        item-kind: FILE, DIR
:: example: cenvironments.modified UPDATE "C:\environments\cpy\config.bat" FILE cenvironments cpy
::------------------------------------------------------------------------------------------
@echo off
setlocal enabledelayedexpansion

echo [CALLING] %~nx0

set "MODTYPE=%~1"
set "MODFILE=%~2"
set "PLATFORM=%~3"
set "PLATFORMNAME=%~4"

:: Extract filename from path
for %%F in ("%MODFILE%") do set "FILENAME=%%~nxF"

:: Log the modification
echo [CENVIRONMENTS] %MODTYPE%: %FILENAME% in %PLATFORMNAME%

:: Look for environment-specific modified handler
set "HANDLER=%CENVIRONMENTS%\%PLATFORMNAME%\%PLATFORMNAME%.modified.bat"
if exist "!HANDLER!" (
    call "!HANDLER!" "%MODTYPE%" "%MODFILE%" "%PLATFORM%" "%PLATFORMNAME%"
) else (
    echo [INFO] Handler does not exist: %PLATFORMNAME%.modified.bat
)

:: Add custom handling logic here as needed

echo [ENDING] %~nx0
