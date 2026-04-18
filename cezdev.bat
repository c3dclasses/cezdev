::------------------------------------------------------
:: name: CEZDEV.bat
:: desc: starts the CEZDEV application
::------------------------------------------------------

@echo off

echo.
echo ::---------------------------------------------------------------------------------------
echo ::  (cezdev.bat) Starting EasyDeveloper (CEZDEV)...
echo ::---------------------------------------------------------------------------------------
echo [CALLING] %~nx0

::------------------------------------------------------
:: Call the create script to initialize environment variables and directories
::------------------------------------------------------
call "%~dp0ccore\cezdev\cezdev.create.bat" "%~dp0"

::------------------------------------------------------
:: Create initial metadata files if they don't exist
::------------------------------------------------------
call clibraries.crud.bat create
call cenvironments.crud.bat create
::call cprojects.crud.bat create

::------------------------------------------------------
:: Optional: Start file monitoring (uncomment to enable)
::------------------------------------------------------
::set "MONITOR_CALLBACKS=%CEZDEV%\cenvironments.modified.bat"
::set "MONITOR_CALLBACKS=%MONITOR_CALLBACKS%,%CEZDEV%\cprojects.modified.bat"
::call file.monitor.bat "%CCORE%,%CPLATFORM%" "%MONITOR_CALLBACKS%"

::------------------------------------------------------
:: Return to CEZDEV home
::------------------------------------------------------
cd /d "%CEZDEV_HOME%"

echo [ENDING] %~nx0
echo ::---------------------------------------------------------------------------------------
echo ::  System (cezdev.bat) ready
echo ::---------------------------------------------------------------------------------------
echo.


::------------------------------------------------------
:: Launch interactive terminal
::------------------------------------------------------
cmd