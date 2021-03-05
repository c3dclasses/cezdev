::------------------------------------------------------------------------------------------
:: name: setvar.bat
:: desc: sets the variable containing
:: usage: setvar <envvar> <promptmsg|value> <-path|-file|-dir>
::------------------------------------------------------------------------------------------
call cjava c3dclasses.CSetVarCommand %EZDEV_META_CVARS% %*

:: run the temp bat file to set the variable and delete it
echo Calling the temporary bat file: %1_tmp.bat
call "%EZDEV_META%\%1_tmp.bat"
echo Removing the temporary bat file: %1_tmp.bat
del "%EZDEV_META%\%1_tmp.bat"
