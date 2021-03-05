::---------------------------------------------------------------------------------------------------
:: name: cjsinit.bat
:: desc: initializes the javascript environment
::       ex. (node, webpack, js, html, css, sass, angular, react, and etc....)
::---------------------------------------------------------------------------------------------------
echo initializing cjs environment.............................................
set CJSINIT=%CD%
set NODE_MODULES_DIR=%EZDEV_HOME%/libs/_libs/node_modules
set NODE_APP_DIR=%EZDEV_HOME%\libs\_libs\node_app
set CJS_HOME=%CENVIRONMENTS_HOME%/cjs
:: install all the needed modules - react, webpack, babel
IF NOT EXIST %NODE_APP_DIR% ( mkdir %NODE_APP_DIR% )
IF EXIST %NODE_MODULES_DIR% ( GOTO DONE_NODE_INIT )
cd /d %EZDEV_HOME%/libs/_libs
call npm i npm-check-updates react react-dom
call npm i npm-check-updates --save-dev webpack webpack-dev-server webpack-cli
call npm i npm-check-updates --save-dev babel-core babel-loader@7 babel-preset-env babel-preset-react html-webpack-plugin
:DONE_NODE_INIT
cd /d %CJSINIT%
echo end initializing cjs environment.............................................