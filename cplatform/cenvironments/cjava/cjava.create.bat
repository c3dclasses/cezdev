::------------------------------------------------------------------------------------------
:: name: cjava.create.bat
:: desc: creates the Java environment into memory for C3DClasses SDK
::------------------------------------------------------------------------------------------

@echo off

::------------------------------------------------------
:: Save current working directory
::------------------------------------------------------
set "CJAVACREATEHOME=%CD%"
set "SCRIPT_DIR=%~dp0"

::------------------------------------------------------
:: Define C3DClasses SDK metadata
::------------------------------------------------------
if "%C3DCLASSES_NAME%"=="" set "C3DCLASSES_NAME=c3dclassessdk"
if "%C3DCLASSES_VERSION%"=="" set "C3DCLASSES_VERSION=1.0"

::------------------------------------------------------
:: Define Java environment paths
::------------------------------------------------------
set "C3DCLASSES_JAVA=%CMETADATA%\c3dclasses_java"
set "C3DCLASSES_JAR=%C3DCLASSES_JAVA%\target\%C3DCLASSES_NAME%-%C3DCLASSES_VERSION%-jar-with-dependencies.jar"
set "C3DCLASSES_SRCPATH=%C3DCLASSES_JAVA%\src"
set "C3DCLASSES_CLASSPATH=%C3DCLASSES_JAR%;."

echo [INFO] Java project: %C3DCLASSES_JAVA%
echo [INFO] JAR file: %C3DCLASSES_JAR%
echo [INFO] Source path: %C3DCLASSES_SRCPATH%
echo [INFO] Classpath: %C3DCLASSES_CLASSPATH%

::------------------------------------------------------
:: Check if Java environment directory already exists   
::------------------------------------------------------
call cp-file-types-from-src-to-dst.bat "%SCRIPT_DIR%" "%CMETADATA%\cenvironments\cjava"
set PATH=%PATH%;%CMETADATA%\cenvironments\cjava

:: Set BOOT_HOME for utility scripts (required by cp-file-types-from-src-to-dst-ex, list-files)
set "BOOT_HOME=%CBOOT%"

:: Compile Java files into a JAR
echo [ACTION] Calling cjavac.bat to compile Java files into a JAR...
call cjavac.bat

:DONE_CJAVA_INIT

::------------------------------------------------------
:: Restore original directory
::------------------------------------------------------
cd /d "%CJAVACREATEHOME%"