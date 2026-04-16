::------------------------------------------------------------------------------------------
:: name: cprojects.modified.bat
:: desc: Handles file modification events for projects (cprojects).
:: usage: cprojects.modified <modified-type> <filepath> <item-kind> <platform> <platform-name>
::        modified-type: CREATE, UPDATE, DELETE, RENAME
::        item-kind: FILE, DIR
:: example: cprojects.modified UPDATE "C:\projects\myapp\src\main.py" FILE cprojects myapp
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
echo [CPROJECTS] %MODTYPE% %ITEMKIND%: %FILENAME% in %PLATFORMNAME%

:: Look for project-specific modified handler
set "HANDLER=%CPROJECTS%\%PLATFORMNAME%\%PLATFORMNAME%.modified.bat"
if exist "!HANDLER!" (
    call "!HANDLER!" "%MODTYPE%" "%MODFILE%" "%ITEMKIND%"
)

:: Add custom handling logic here as needed
