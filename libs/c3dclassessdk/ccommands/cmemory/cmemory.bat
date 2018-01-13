::---------------------------------------------------------------------------------------------
:: name: cmemory.bat
:: desc: manipulates a memory location
:: usage:
::
:: INCLUDING / USING
:: cmemory -include <identifier> <configfile> 	- includes a configuration 
:: cmemory -use <identifier>					- uses a configuration
:: cmemory -config <params> 					- show memory configuration details
::
:: PRINTING
:: cmemory -cache <params> 				- prints local memory cache
::
:: GET / SET / PROMPTING
:: cmemory -set	<varname> <varvalue>	- sets a location locally
:: cmemory -prompt <varname>			- sets a location value from a prompt dialog
:: cmemory -promptfile <varname>		- sets a location with a file via file dialog
:: cmemory -promptpath <varname>		- sets a loaction with a path via file dialog
:: cmemory -get	<varname> 				- gets a location locally, get the m_value by default
:: cmemory -get	<varname> <varmember> 	- gets a location member locally ex.) cmemory -get	<varname> m_iupdated - gets when member was updated
::
:: CRUDS
:: cmemory -create <varname> <varvalue>	- retrieves a memory location remotely 
:: cmemory -retrieve <varname>			- retrieves a memory location remotely 
:: cmemory -update <varname> <varvalue>	- updates a memory location remotely 
:: cmemory -delete <varname>			- deletes a memory location locally remotely 
:: cmemory -sync						- sync local and remote memory locations
::
:: OTHER
:: cmemory -assign <varname> <envvarname> - assign cmemory varname to envvarname
::----------------------------------------------------------------------------------------------
@echo off

:: set the default memory driver
set CMEMORY_DEFAULT_DRIVER_PATH=%C3DCLASSESSDK_COMMANDS%/cmemory/cjsonmemory.json
set CMEMORY_DEFAULT_DRIVER_TYPE=c3dclasses.CJSONMemoryDriver
set CMEMORY_FILE=%EZDEV_HOME%/meta/cmemory.tmp
set CMEMORY_USAGE_FILE=%C3DCLASSESSDK_COMMANDS%/cmemory/cmemory.usage.txt


if "%1" == "-assign" ( 
	_ %~3 "cmemory -get %2"
	goto done
) 

if "%1" == "-usage" ( 
	alert -file "%CMEMORY_USAGE_FILE%" "CMemory Usage"
	echo "%CMEMORY_USAGE_FILE%"
	goto done
) 

c3dclassessdk_java CMemoryCommand %CMEMORY_DEFAULT_DRIVER_PATH% %CMEMORY_DEFAULT_DRIVER_TYPE% %1 %2 %3 %4
done