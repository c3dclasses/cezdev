::------------------------------------------------------------------------------------------
:: name: cpy.read.bat
:: desc: Displays the Python environment configuration for C3DClasses SDK
::------------------------------------------------------------------------------------------

@echo off

::------------------------------------------------------
:: Define C3DClasses SDK metadata
::------------------------------------------------------
if "%C3DCLASSES_NAME%"=="" set "C3DCLASSES_NAME=c3dclassessdk"
if "%C3DCLASSES_VERSION%"=="" set "C3DCLASSES_VERSION=1.0"

::------------------------------------------------------
:: Define Python environment paths
::------------------------------------------------------
set "C3DCLASSES_PY=%CMETADATA%\c3dclasses_py"
set "C3DCLASSES_PY_EGG_INFO=%C3DCLASSES_PY%\c3dclasses.egg-info"
set "C3DCLASSES_PY_SRCPATH=%C3DCLASSES_PY%\c3dclasses"

echo [INFO] C3DCLASSES_NAME: %C3DCLASSES_NAME%
echo [INFO] C3DCLASSES_VERSION: %C3DCLASSES_VERSION%
echo [INFO] Python project: %C3DCLASSES_PY%
echo [INFO] Source path: %C3DCLASSES_PY_SRCPATH%
echo [INFO] Egg-info path: %C3DCLASSES_PY_EGG_INFO%

::------------------------------------------------------
:: Check if environment exists
::------------------------------------------------------
if exist "%C3DCLASSES_PY%" (
    echo [OK] Python environment directory exists.
) else (
    echo [INFO] Python environment directory does NOT exist.
)
