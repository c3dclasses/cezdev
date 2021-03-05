::------------------------------------------------------------------------------------------
:: name: cjsdestroy.bat
:: desc: destroys the js environment
::------------------------------------------------------------------------------------------
rmdir /s %EZDEV_STDLIBS%\node_modules
rmdir /s %EZDEV_STDLIBS%\node_app
del %EZDEV_STDLIBS%\package-lock.json
del %EZDEV_STDLIBS%\package.json
del %EZDEV_STDLIBS%\webpack.config.js
del %EZDEV_STDLIBS%\.babelrc

