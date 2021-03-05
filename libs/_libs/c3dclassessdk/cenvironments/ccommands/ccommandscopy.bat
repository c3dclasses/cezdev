::--------------------------------------------------------------------------------------------
:: name: ccommandscopy.bat
:: desc: copies all the commands in the c3dclassessdk folder to ccommands directory in meta 
::--------------------------------------------------------------------------------------------
:: copy over all the commands
For /R "%C3DCLASSESSDK_PATH%" %%G IN (*.bat) do copy "%%G" "%C3DCLASSESSDK_CCOMMANDS%"
