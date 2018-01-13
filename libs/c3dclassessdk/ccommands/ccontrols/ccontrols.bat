::------------------------------------------------------------------------------------------
:: name: ccontrols.bat
:: desc: the controls command designed to build application controls via commandline 
::------------------------------------------------------------------------------------------
set CMEMORY_DEFAULT_DRIVER_PATH=%C3DCLASSESSDK_COMMANDS%/cmemory/cjsonmemory.json
set CMEMORY_DEFAULT_DRIVER_TYPE=c3dclasses.CJSONMemoryDriver
set CMEMORY_FILE=%EZDEV_HOME%/meta/cmemory.tmp
set CCONTROLS_USAGE_FILE=%C3DCLASSESSDK_COMMANDS%/ccontrols/ccontrols.usage.txt

if "%1" == "-init" ( 
	if "%2" == "" (
		:: make sure the command starts in another window
		start /min c3dclassessdk_java_bg CControlsCommand %CMEMORY_DEFAULT_DRIVER_PATH% %CMEMORY_DEFAULT_DRIVER_TYPE% "%2"
		goto done
	)
) 

if "%1" == "-usage" ( 
	alert -file "%CCONTROLS_USAGE_FILE%" "CControls Usage"
	echo "%CCONTROLS_USAGE_FILE%"
	goto done
) 

cmemory -set ccontrol-command "%1 %2 %3 %4"

done
