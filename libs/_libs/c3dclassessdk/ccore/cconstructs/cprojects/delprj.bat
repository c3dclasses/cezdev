::------------------------------------------------------------------------------------------
:: name: delprj.bat
:: desc: deletes a project from the cprojects folder
::------------------------------------------------------------------------------------------
setlocal
set HOME=%CD%
del "%EZDEV_META%\%1.prj.bat"
rmdir /S "%C3DCLASSESSDK_SRCPATH%\main\java\cprojects\%1"
rmdir /S "%C3DCLASSESSDK_PATH%\cprojects\%1"
cd /d %HOME%
