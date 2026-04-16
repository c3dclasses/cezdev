::------------------------------------------------------------------------------------------
:: name: cprojects.crud.bat
:: desc: CRUD wrapper for projects using cplatform.crud.
:: usage: cprojects.crud <action> [project]
::        action: create, read, update, delete
:: example: cprojects.crud create myproject
::------------------------------------------------------------------------------------------
@echo off
call "%CEZDEV%\cplatform.crud.bat" %~1 "%CPROJECTS%" "%~2"
