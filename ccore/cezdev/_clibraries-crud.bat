::------------------------------------------------------------------------------------------
:: name: _clibraries.crud.bat
:: desc: CRUD wrapper for internal/system libraries using cplatform.crud.
:: usage: _clibraries.crud <action> [library]
::        action: create, read, update, delete
:: example: _clibraries.crud create ccore
::------------------------------------------------------------------------------------------
@echo off

set "ACTION=%~1"
set "LIBRARY=%~2"

:: If specific library specified, just run it
if not "%LIBRARY%"=="" (
    call cplatform.crud.bat %ACTION% "%_CLIBRARIES%" "%LIBRARY%"
    exit /b 0
)

:: Run all libraries except cboot first
for /d %%P in ("%_CLIBRARIES%\*") do (
    if /i not "%%~nP"=="cboot" (
        echo Creating: %%~nP
        call cplatform.crud.bat %ACTION% "%_CLIBRARIES%" "%%~nP"
    )
)

:: Run cboot last
if exist "%_CLIBRARIES%\cboot" (
    echo Creating: cboot
    call cplatform.crud.bat %ACTION% "%_CLIBRARIES%" "cboot"
)

exit /b 0
