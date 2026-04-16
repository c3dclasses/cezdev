::------------------------------------------------------------------------------------------
:: name: cpy.destroy.bat
:: desc: Removes the Python environment from C3DClasses SDK
::------------------------------------------------------------------------------------------

@echo off

::------------------------------------------------------
:: Validate required variables
::------------------------------------------------------
if "%CMETADATA%"=="" (
    echo [ERROR] CMETADATA environment variable is not set.
    exit /b 1
)

::------------------------------------------------------
:: Remove Python environment directory
::------------------------------------------------------
set "C3DCLASSES_PY=%CMETADATA%\c3dclasses_py"

if exist "%C3DCLASSES_PY%" (
    echo [ACTION] Removing: %C3DCLASSES_PY%
    rmdir /s /q "%C3DCLASSES_PY%"
    echo [OK] Python environment removed.
) else (
    echo [INFO] Python environment directory does not exist.
)
