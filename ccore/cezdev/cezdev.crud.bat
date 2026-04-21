@echo off
::------------------------------------------------------------------------------------------
:: name: cezdev.crud.bat
:: desc: Backward-compatible wrapper for scripts.call.bat
:: usage: cezdev.crud.bat <operation> <srcpath> [scriptpattern]
:: example: cezdev.crud.bat create "%CENVIRONMENTS%" "cjava"
::------------------------------------------------------------------------------------------

set "CRUDOP=%~1"
set "SRCPATH=%~2"
set "SCRIPTPATTERN=%~3"

if "%CRUDOP%"=="" (
	echo [ERROR] Operation not provided.
	exit /b 1
)

if "%SRCPATH%"=="" (
	echo [ERROR] Source path not provided.
	exit /b 1
)

if "%SCRIPTPATTERN%"=="" (
	set "PATTERN=*.%CRUDOP%.bat"
) else (
	set "PATTERN=%SCRIPTPATTERN%.%CRUDOP%.bat"
)

call "%~dp0scripts.call.bat" "%SRCPATH%" "%PATTERN%"