::------------------------------------------------------------------------------------------
:: name: clibraries.modified.bat
:: desc: Handles file modification events for user libraries (clibraries).
:: usage: clibraries.modified <modified-type> <filepath> <item-kind> <platform> <platform-name>
::        modified-type: CREATE, UPDATE, DELETE, RENAME
::        item-kind: FILE, DIR
:: example: clibraries.modified UPDATE "C:\libraries\node\config.bat" FILE clibraries node
::------------------------------------------------------------------------------------------
@echo off
setlocal enabledelayedexpansion

set "MODTYPE=%~1"
set "MODFILE=%~2"
set "ITEMKIND=%~3"
set "PLATFORM=%~4"
set "PLATFORMNAME=%~5"

:: Extract filename from path
for %%F in ("%MODFILE%") do set "FILENAME=%%~nxF"

:: Log the modification
echo [CLIBRARIES] %MODTYPE% %ITEMKIND%: %FILENAME% in %PLATFORMNAME%

:: Look for platform-specific modified handler
set "HANDLER=%CLIBRARIES%\%PLATFORMNAME%\%PLATFORMNAME%.modified.bat"
if exist "!HANDLER!" (
    call "!HANDLER!" "%MODTYPE%" "%MODFILE%" "%ITEMKIND%"
)

:: Add custom handling logic here as needed
