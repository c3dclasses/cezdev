::------------------------------------------------------------------------------------------
:: name: cp-cprjs.bat
:: desc: copies all the projects to cproject dir before building it
::------------------------------------------------------------------------------------------
set CPCPRJSHOME=%CD%
cd /d %EZDEV_META%
for /R %%f in (*.prj.bat) do ("%%f")
cd /d %CPCPRJSHOME%
