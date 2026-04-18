::------------------------------------------------------------------------------------------
:: name: cjava.create.bat
:: desc: creates the Java environment into memory for C3DClasses SDK
::------------------------------------------------------------------------------------------

@echo off

:: Save the current directory
set "CJAVACREATEHOME=%CD%"

echo [CALLING] %~nx0

if "%C3DCLASSES_NAME%"=="" set "C3DCLASSES_NAME=c3dclassessdk"
if "%C3DCLASSES_VERSION%"=="" set "C3DCLASSES_VERSION=1.0"
set "C3DCLASSES_JAVA=%CMETADATA%\c3dclasses_java"
set "C3DCLASSES_JAR=%C3DCLASSES_JAVA%\target\%C3DCLASSES_NAME%-%C3DCLASSES_VERSION%-jar-with-dependencies.jar"
set "C3DCLASSES_SRCPATH=%C3DCLASSES_JAVA%\src"
set "C3DCLASSES_CLASSPATH=%C3DCLASSES_JAR%;."

echo [INFO] Java project: %C3DCLASSES_JAVA%
echo [INFO] JAR file: %C3DCLASSES_JAR%
echo [INFO] Source path: %C3DCLASSES_SRCPATH%
echo [INFO] Classpath: %C3DCLASSES_CLASSPATH%

echo [ACTION] Creating Java environment...
call script2meta.copy.bat "%~dp0"

if exist "%C3DCLASSES_JAR%" (
    echo [INFO] JAR file already exists: %C3DCLASSES_JAR%
) else (
    echo [ACTION] Calling cjavac.bat to compile Java files into a JAR...
    call cjavac.bat
)

echo [ENDING] %~nx0

:: Restore original directory
cd /d "%CJAVACREATEHOME%"