::------------------------------------------------------------------------------------------
:: name: cplatform.modified.bat
:: desc: Dispatches file modification events to the appropriate platform handler.
:: usage: cplatform.modified <modified-type> <filepath> <item-kind> <platform> <platform-name>
::        modified-type: CREATE, UPDATE, DELETE, RENAME
::        item-kind: FILE, DIR
:: example: cplatform.modified UPDATE "C:\cplatform\clibrary\node\config.bat" FILE clibrary node
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

:: Validate parameters
if "%MODTYPE%"=="" (
    echo [ERROR] Missing modified-type parameter.
    exit /b 1
)

if "%MODFILE%"=="" (
    echo [ERROR] Missing filepath parameter.
    exit /b 1
)

if "%PLATFORM%"=="" (
    echo [ERROR] Missing platform parameter.
    exit /b 1
)

if "%PLATFORMNAME%"=="" (
    echo [ERROR] Missing platform-name parameter.
    exit /b 1
)

echo [CALLING] %~nx0
echo [INFO] Modification Type: %MODTYPE%
echo [INFO] File Path: %MODFILE%
echo [INFO] Item Kind: %ITEMKIND%
echo [INFO] Platform: %PLATFORM%
echo [INFO] Platform Name: %PLATFORMNAME%

echo Modification detected: %MODTYPE% %ITEMKIND% %MODFILE% for %PLATFORM% %PLATFORMNAME%

if /i "%PLATFORM%"=="clibraries" (
    call "%CEZDEV%\clibraries.modified.bat" "%MODTYPE%" "%MODFILE%" "%ITEMKIND%" "%PLATFORM%" "%PLATFORMNAME%"
    goto :EOF
)

if /i "%PLATFORM%"=="cenvironments" (
    call "%CEZDEV%\cenvironments.modified.bat" "%MODTYPE%" "%MODFILE%" "%ITEMKIND%" "%PLATFORM%" "%PLATFORMNAME%"
    goto :EOF
)

if /i "%PLATFORM%"=="cprojects" (
    call "%CEZDEV%\cprojects.modified.bat" "%MODTYPE%" "%MODFILE%" "%ITEMKIND%" "%PLATFORM%" "%PLATFORMNAME%"
    goto :EOF
)

echo [INFO] File modified in unknown platform: %PLATFORM%/%PLATFORMNAME% - %FILENAME%

echo [ENDING] %~nx0
