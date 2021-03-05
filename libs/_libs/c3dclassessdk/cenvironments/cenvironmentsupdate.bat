::------------------------------------------------------------------------------------------
:: name: cenvironmentsupdate.bat
:: desc: updates the environments 
::------------------------------------------------------------------------------------------
echo Update CEnvironments.............................................

:: move all the commands 
call %CENVIRONMENTS_HOME%/ccommands/ccommandscopy
call list-files %EZDEV_META%/c3dclassessdk.filenames.json %C3DCLASSESSDK_PATH%

echo End Update CEnvironments.............................................
