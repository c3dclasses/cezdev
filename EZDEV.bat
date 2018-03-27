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
set EZDEV_HOME=%CD%
set EZDEV_META=%EZDEV_HOME%/meta

::------------------------------------------------
:: create the meta directory if it doesn't exist
::------------------------------------------------
IF NOT EXIST %EZDEV_META% ( mkdir %EZDEV_META% )

::------------------------------------------------
:: initialize EZDEV libs
::------------------------------------------------

:: python library
set PYTHON_COMMANDS=%EZDEV_HOME%/libs/python/python-3.5.4-embed-amd64
set PATH=%PATH%;%PYTHON_COMMANDS%

:: r library
set R_COMMANDS=C:/Program Files/R/R-3.4.3/bin
set PATH=%PATH%;%R_COMMANDS%

:: java library
set JAVA_HOME=%EZDEV_HOME%/libs/java/jdk1.8.0_121
set JAVA_BIN=%JAVA_HOME%/bin

:: maven library
set MAVEN_BIN=%EZDEV_HOME%/libs/java/apache-maven-3.2.3/bin
set PATH=%PATH%;%JAVA_BIN%;%MAVEN_BIN%;%CD%;

:: boot commands
javac %EZDEV_HOME%/libs/boot/*.java
set PATH=%PATH%;%EZDEV_HOME%/libs/boot

:: c3dclassessdk library
call %EZDEV_HOME%/libs/c3dclassessdk/ccommands/c3dclassessdk

::---------------------------------------------------
:: run the terminal to run additional commands 
::---------------------------------------------------
cmd