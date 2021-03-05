::-------------------------------------------------------------------------------------------------
:: name: cjscopy.bat
:: desc: converts c3dclassessdk project into a webpack project
:: usage: c3d2js c3dclassessdk c3dclassessdk_js
::-------------------------------------------------------------------------------------------------
:: set C3DCLASSESSDK_JS=%EZDEV_META%/c3dclassessdk_js
:: set src=%C3DCLASSESSDK_PATH%
:: set dst=%C3DCLASSESSDK_JS%

:: copy over config files
:: IF NOT EXIST "%dst%" ( mkdir "%dst%" )
:: cp "%C3DCLASSESSDK_PATH%/core/__/__Commands/cjs/webpack.config.js" "%dst%"
:: cp "%C3DCLASSESSDK_PATH%/core/__/__Commands/cjs/package.json" "%dst%"
:: cp "%C3DCLASSESSDK_PATH%/core/__/__Commands/cjs/.babelrc" "%dst%"

:: cd "%dst%"

:: create package.json
:: call npm init --y

:: install modules - react, webpack, babel
:: call npm i react react-dom
:: call npm i --save-dev webpack webpack-dev-server webpack-cli
:: call npm i --save-dev babel-core bable-loader babel-present-env babel-present-react html-webpack-plugin

