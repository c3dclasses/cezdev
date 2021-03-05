::------------------------------------------------------------------------------------------
:: name: getvar.bat
:: desc: gets the variable stored in CMemory and sets the enviroment variable
:: usage: getvar <envvar>
::------------------------------------------------------------------------------------------
call cjava c3dclasses.CGetVarCommand %EZDEV_META_CVARS% %*

:: run the temp bat file to set the variable and delete it
echo Calling the temporary bat file: %1_tmp.bat
call "%EZDEV_META%\%1_tmp.bat"
echo Removing the temporary bat file: %1_tmp.bat
del "%EZDEV_META%\%1_tmp.bat"
