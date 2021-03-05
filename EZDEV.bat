::------------------------------------------------------
:: name: EZDEV.bat
:: desc: starts the EZDEV application
::------------------------------------------------------

::-----------------------------------
:: starting message
::-----------------------------------
@echo on
echo Starting EasyDeveloper ..........................................

::-----------------------------------
:: initialize the variables 
::-----------------------------------
set EZDEV_VERSION=1.0
set EZDEV_NAME=EZDEV
set EZDEV_DEBUG=true
set EZDEV_HOME=%~dp0
set EZDEV_LIBS=%EZDEV_HOME%\libs
set EZDEV_STDLIBS=%EZDEV_LIBS%\_libs
set EZDEV_META=%EZDEV_HOME%\meta
set EZDEV_META_CVARS="%EZDEV_HOME%\meta\cvars.json"
set EZDEV_CMEMORY_DRIVER=""

::------------------------------------------------
:: create the meta directory if it doesn't exist
::------------------------------------------------
IF NOT EXIST %EZDEV_META% ( mkdir %EZDEV_META% )

::------------------------------------------------
:: initialize EZDEV libs
::------------------------------------------------
call %EZDEV_LIBS%/libs

::---------------------------------------------------
:: run the terminal to run additional commands 
::---------------------------------------------------
cmd