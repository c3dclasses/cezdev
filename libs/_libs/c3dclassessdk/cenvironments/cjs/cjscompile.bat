::---------------------------------------------------------------------------------------------------
:: name: cjscompile.bat
:: desc: compiles the javascript framework
::---------------------------------------------------------------------------------------------------
set CJSCOMPILE=%CD%
set PROPERTIES_JSON="%CJS_HOME%/config/properties.json"
set WEBPACK_JS="%CJS_HOME%/config/webpack.config.tmp.js"
set BABELRC="%CJS_HOME%/config/.babelrc.tmp"
set PACKAGE_JS="%CJS_HOME%/config/package.tmp.json"

IF NOT EXIST %NODE_MODULES_DIR% ( call cjsinit )

:: update the environments
call cenvironmentsupdate

:: write information to configuration template files
call ctemplate "%PROPERTIES_JSON%" "%PACKAGE_JS%" "%EZDEV_HOME%/libs/_libs/package.json"
call ctemplate "%PROPERTIES_JSON%" "%WEBPACK_JS%" "%EZDEV_HOME%/libs/_libs/webpack.config.js" 
call ctemplate "%PROPERTIES_JSON%" "%BABELRC%" "%EZDEV_HOME%/libs/_libs/.babelrc"

:: write information to html and js template files
:: call ctemplate "%CJS_HOME%/config/properties.json" "%CJS_HOME%/config/index.tmp.js" "%EZDEV_HOME%/libs/_libs/node_app/index.js" 
call ctemplate "%CJS_HOME%/config/properties.json" "%CJS_HOME%/config/index.tmp.html" "%EZDEV_HOME%/libs/_libs/node_app/index.html"  

:: compile
call cjava c3dclasses.CJSCompileCommand %1 %2 "%CJS_HOME%/config/properties.json" "%CJS_HOME%/config/index.tmp.js" "%EZDEV_HOME%/libs/_libs/node_app/index.js"

:: run the app
cd /d %EZDEV_HOME%/libs/_libs
start npm start

cd /d %CJSCOMPILE%