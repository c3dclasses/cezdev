::------------------------------------------------------------------------------------------
:: name: cjsdestroy.bat
:: desc: destroys the js environment
::------------------------------------------------------------------------------------------
rmdir /s %CEZDEV_STDLIBS%\node_modules
rmdir /s %CEZDEV_STDLIBS%\node_app
del %CEZDEV_STDLIBS%\package-lock.json
del %CEZDEV_STDLIBS%\package.json
del %CEZDEV_STDLIBS%\webpack.config.js
del %CEZDEV_STDLIBS%\.babelrc

