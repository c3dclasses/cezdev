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
set "ITEMKIND=%~3"
set "PLATFORM=%~4"
set "PLATFORMNAME=%~5"

:: Extract filename from path
for %%F in ("%MODFILE%") do set "FILENAME=%%~nxF"

:: Log the modification
echo [CENVIRONMENTS] %MODTYPE% %ITEMKIND%: %FILENAME% in %PLATFORMNAME%

:: Look for environment-specific modified handler
set "HANDLER=%CENVIRONMENTS%\%PLATFORMNAME%\%PLATFORMNAME%.modified.bat"
if exist "!HANDLER!" (
    call "!HANDLER!" "%MODTYPE%" "%MODFILE%" "%ITEMKIND%"
)

:: Add custom handling logic here as needed

echo [ENDING] %~nx0
