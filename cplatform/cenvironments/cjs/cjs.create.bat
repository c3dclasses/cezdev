::------------------------------------------------------------------------------------------
:: name: cjs.create.bat
:: desc: Creates the JavaScript environment into memory for C3DClasses SDK
::------------------------------------------------------------------------------------------

@echo off

::------------------------------------------------------
:: Save current working directory
::------------------------------------------------------
set "CJSCREATEHOME=%CD%"

::------------------------------------------------------
:: Define C3DClasses SDK metadata
::------------------------------------------------------
if "%C3DCLASSES_NAME%"=="" set "C3DCLASSES_NAME=c3dclassessdk"
if "%C3DCLASSES_VERSION%"=="" set "C3DCLASSES_VERSION=1.0"

::------------------------------------------------------
:: Define JavaScript environment paths
::------------------------------------------------------
set "C3DCLASSES_JS=%CMETADATA%\c3dclasses_js"
set "C3DCLASSES_JS_SRCPATH=%C3DCLASSES_JS%\src"
set "NODE_MODULES_DIR=%_CLIBRARIES%\node_modules"
set "NODE_APP_DIR=%_CLIBRARIES%\node_app"
set "CJS_HOME=%~dp0"

echo [INFO] JavaScript project: %C3DCLASSES_JS%
echo [INFO] Source path: %C3DCLASSES_JS_SRCPATH%
echo [INFO] Node modules: %NODE_MODULES_DIR%
echo [INFO] Node app: %NODE_APP_DIR%

::------------------------------------------------------
:: Ensure node_app directory exists
::------------------------------------------------------
if not exist "%NODE_APP_DIR%" (
    echo [ACTION] Creating node_app directory...
    mkdir "%NODE_APP_DIR%"
)

::------------------------------------------------------
:: Install Node modules if not present
::------------------------------------------------------
if exist "%NODE_MODULES_DIR%" (
    echo [OK] Node modules already installed.
    goto DONE_NODE_INIT
)

echo [ACTION] Installing Node modules...
cd /d "%_CLIBRARIES%"

echo [ACTION] Installing React dependencies...
call npm i react react-dom

echo [ACTION] Installing Webpack dependencies...
call npm i --save-dev webpack webpack-dev-server webpack-cli

echo [ACTION] Installing Babel dependencies...
call npm i --save-dev @babel/core babel-loader @babel/preset-env @babel/preset-react html-webpack-plugin

echo [OK] Node modules installed.

:DONE_NODE_INIT

::------------------------------------------------------
:: Build JavaScript package
::------------------------------------------------------
echo [ACTION] Calling cjs.update.bat to build JavaScript package...
call "%~dp0cjs.update.bat"

::------------------------------------------------------
:: Restore original directory
::------------------------------------------------------
cd /d "%CJSCREATEHOME%"
