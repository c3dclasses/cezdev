::------------------------------------------------------------------------------------------
:: name: cpy.create.bat
:: desc: Creates the Python environment into memory for C3DClasses SDK
::------------------------------------------------------------------------------------------

@echo off

::------------------------------------------------------
:: Save current working directory
::------------------------------------------------------
set "CPYCREATEHOME=%CD%"

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

echo [INFO] Python project: %C3DCLASSES_PY%
echo [INFO] Source path: %C3DCLASSES_PY_SRCPATH%

::------------------------------------------------------
:: Build Python package
::------------------------------------------------------
echo [ACTION] Calling cpy.update.bat to build Python package...
call "%~dp0cpy.update.bat"

:DONE_CPY_INIT

::------------------------------------------------------
:: Restore original directory
::------------------------------------------------------
cd /d "%CPYCREATEHOME%"