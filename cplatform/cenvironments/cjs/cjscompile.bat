::---------------------------------------------------------------------------------------------------
:: name: cjscompile.bat
:: desc: Compiles the JavaScript framework using webpack
:: usage: cjscompile [source] [destination]
::---------------------------------------------------------------------------------------------------

@echo off
setlocal

set "CJSCOMPILEHOME=%CD%"
set "CJS_HOME=%~dp0"
set "PROPERTIES_JSON=%CJS_HOME%config\properties.json"

::------------------------------------------------------
:: Define JavaScript environment paths
::------------------------------------------------------
set "C3DCLASSES_JS=%CMETADATA%\c3dclasses_js"
set "NODE_MODULES_DIR=%_CLIBRARIES%\node_modules"

::------------------------------------------------------
:: Ensure Node modules are installed
::------------------------------------------------------
if not exist "%NODE_MODULES_DIR%" (
    echo [ACTION] Node modules not found, initializing...
    call "%~dp0cjs.create.bat"
)

::------------------------------------------------------
:: Update environments
::------------------------------------------------------
echo [ACTION] Updating environments...
call cenvironments-update

::------------------------------------------------------
:: Process template files
::------------------------------------------------------
echo [ACTION] Processing configuration templates...

call ctemplate "%PROPERTIES_JSON%" "%CJS_HOME%config\package.tmp.json" "%_CLIBRARIES%\package.json"
call ctemplate "%PROPERTIES_JSON%" "%CJS_HOME%config\webpack.config.tmp.js" "%_CLIBRARIES%\webpack.config.js"
call ctemplate "%PROPERTIES_JSON%" "%CJS_HOME%config\index.tmp.html" "%_CLIBRARIES%\node_app\index.html"

::------------------------------------------------------
:: Compile JavaScript
::------------------------------------------------------
echo [ACTION] Compiling JavaScript...
call cjava c3dclasses.CJSCompileCommand %1 %2 "%PROPERTIES_JSON%" "%CJS_HOME%config\index.tmp.js" "%_CLIBRARIES%\node_app\index.js"

::------------------------------------------------------
:: Start the app
::------------------------------------------------------
echo [ACTION] Starting webpack development server...
cd /d "%_CLIBRARIES%"
start npm start

cd /d "%CJSCOMPILEHOME%"
endlocal