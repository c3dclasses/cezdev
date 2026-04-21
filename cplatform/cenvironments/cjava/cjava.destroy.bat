::------------------------------------------------------------------------------------------
:: name: cjava.destroy.bat
:: desc: Removes the Java environment from C3DClasses SDK
::------------------------------------------------------------------------------------------

@echo off

echo [CALLING] %~nx0

::------------------------------------------------------
:: Validate required variables
::------------------------------------------------------
if "%CMETADATA%"=="" (
    echo [ERROR] CMETADATA environment variable is not set.
    exit /b 1
)

::------------------------------------------------------
:: Remove Java environment directory
::------------------------------------------------------
set "C3DCLASSES_JAVA=%CMETADATA%\c3dclasses_java"

if exist "%C3DCLASSES_JAVA%" (
    echo [REMOVING] %C3DCLASSES_JAVA%
    rmdir /s /q "%C3DCLASSES_JAVA%"
    echo [OK] Java environment removed.
) else (
    echo [INFO] Java environment directory does not exist.
)

echo [ENDING] %~nx0
