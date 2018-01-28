::------------------------------------------------------
:: name: EZDEV.bat
:: desc: starts the EZDEV application
::------------------------------------------------------

## starting message
@echo on
echo Starting EasyDeveloper ..........................................

:: initialize the variables 
set EZDEV_VERSION=1.0
set EZDEV_NAME=EZDEV
set EZDEV_DEBUG=true
set EZDEV_HOME=%CD%
set EZDEV_META=%EZDEV_HOME%/meta

:: create the meta directory if it doesn't exist
IF NOT EXIST %EZDEV_META% ( 
	mkdir %EZDEV_META% 
)

:: set the commands on the path
set PYTHON_COMMANDS=%EZDEV_HOME%/libs/python/python-3.5.4-embed-amd64
set PATH=%PATH%;%PYTHON_COMMANDS%

:: initialize EZDEV
cd /d %EZDEV_HOME%/libs/c3dclassessdk/ccommands
call c3dclassessdk

:: pause before terminating
pause

:: run the terminal to run additional commands 
cmd