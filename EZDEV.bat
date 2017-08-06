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
set C3DCLASSESSDK_VERSION=1.0
set C3DCLASSESSDK_NAME=EZDEV
set C3DCLASSESSDK_PATH=%EZDEV_HOME%/libs/c3dclassessdk


:: initialize EZDEV
cd /d %C3DCLASSESSDK_PATH%/cscripts
call initc3dclassessdk

:: pause before terminating
pause

:: run the terminal to run additional commands 
cmd