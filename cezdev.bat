::------------------------------------------------------
:: name: CEZDEV.bat
:: desc: starts the CEZDEV application
::------------------------------------------------------

@echo off
setlocal

echo.
echo ::---------------------------------------------------------------------------------------
echo ::  Starting EasyDeveloper (cezdev.bat)
echo ::---------------------------------------------------------------------------------------

::------------------------------------------------------
:: Save baseline environment variables
::------------------------------------------------------
set CBASEENVVARS=%~dp0cdata\cmetadata\baseline_vars.txt
echo [SAVE] %CBASEENVVARS%
set > "%CBASEENVVARS%"

::------------------------------------------------------
:: Initialize CEZDEV variables
::------------------------------------------------------
echo [INIT] CEZDEV variables...
set CEZDEV_VERSION=1.0
set CEZDEV_NAME=CEZDEV
set CEZDEV_DEBUG=true
set CEZDEV_HOME=%~dp0
set CCORE=%CEZDEV_HOME%ccore
set CPLATFORM=%CEZDEV_HOME%cplatform
set CEZDEV=%CCORE%\cezdev
set C3DCLASSES=%CCORE%\c3dclasses
set _CLIBRARIES=%CPLATFORM%\_clibraries
set CBOOT=%_CLIBRARIES%\cboot
set CLIBRARIES=%CPLATFORM%\clibraries
set CVIDEOS=%CEZDEV_HOME%cdata\cvideo
set CMETADATA=%CEZDEV_HOME%cdata\cmetadata
set CDATA=%CEZDEV_HOME%cdata\cdata
set CPROJECTS=%CPLATFORM%\cprojects
set CENVIRONMENTS=%CPLATFORM%\cenvironments
set CMETADATA_CVARS=%CMETADATA%\cvars.json
set CMEMORY_DRIVER=json
set CWSL=\\wsl.localhost\Ubuntu\home\c3dclasses

::------------------------------------------------------
:: Ensure required directories exist
::------------------------------------------------------
IF NOT EXIST "%CMETADATA%" (
    mkdir "%CMETADATA%"
    echo [MKDIR] %CMETADATA%
)
IF NOT EXIST "%CLIBRARIES%" (
    mkdir "%CLIBRARIES%"
    echo [MKDIR] %CLIBRARIES%
)
IF NOT EXIST "%_CLIBRARIES%" (
    mkdir "%_CLIBRARIES%"
    echo [MKDIR] %_CLIBRARIES%
)
IF NOT EXIST "%CVIDEOS%" (
    mkdir "%CVIDEOS%"
    echo [MKDIR] %CVIDEOS%
)

::------------------------------------------------------
:: Set PATH to include CEZDEV and its tools
::------------------------------------------------------
set PATH=%CEZDEV%;%PATH%

::------------------------------------------------------
:: Create initial metadata files if they don't exist
::------------------------------------------------------
call _clibraries.crud.bat create
call clibraries.crud.bat create
::call "%CEZDEV%\cenvironments.crud.bat" create
::call "%CEZDEV%\cprojects.crud.bat" create



::set "MONITOR_CALLBACKS=%CEZDEV%\cenvironments.modified.bat"
::set "MONITOR_CALLBACKS=%MONITOR_CALLBACKS%,%CEZDEV%\_clibraries.modified.bat"
::set "MONITOR_CALLBACKS=%MONITOR_CALLBACKS%,%CEZDEV%\clibraries.modified.bat"
::set "MONITOR_CALLBACKS=%MONITOR_CALLBACKS%,%CEZDEV%\cprojects.modified.bat"
::call monitor_file_changes.bat "%CCORE%,%CPLATFORM%" "%MONITOR_CALLBACKS%"

::------------------------------------------------------
:: Return to CEZDEV home
::------------------------------------------------------
cd /d "%CEZDEV_HOME%"

echo.
echo ::---------------------------------------------------------------------------------------
echo ::  System (cezdev.bat) ready
echo ::---------------------------------------------------------------------------------------
echo.

::------------------------------------------------------
:: Launch interactive terminal
::------------------------------------------------------
cmd