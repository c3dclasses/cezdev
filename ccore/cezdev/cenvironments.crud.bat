::------------------------------------------------------------------------------------------
:: name: cenvironments.crud.bat
:: desc: CRUD wrapper for environments using cplatform.crud.
:: usage: cenvironments.crud <action> [environment]
::        action: create, read, update, delete
:: example: cenvironments.crud create cpy
::------------------------------------------------------------------------------------------
@echo off
call "%CEZDEV%\cplatform.crud.bat" %~1 "%CENVIRONMENTS%" "%~2"
