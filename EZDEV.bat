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
set C3DCLASSESSDK_VERSION=1.0
set C3DCLASSESSDK_NAME=EZDEV

:: initialize EZDEV
cd bin\scripts
call initEZDEV

:: pause before terminating

:: run the terminal to run additional commands 
pause

cmd