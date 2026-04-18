::------------------------------------------------------------------------------------------
:: name: clibraries.crud.bat
:: desc: CRUD wrapper for user libraries using cplatform.crud.
:: usage: clibraries.crud <action> [library]
::        action: create, read, update, delete
:: example: clibraries.crud create node
::------------------------------------------------------------------------------------------
@echo off
echo [CALLING] %~nx0
call "%CEZDEV%\cplatform.crud.bat" %~1 "%CLIBRARIES%" "%~2"
echo [ENDING] %~nx0
