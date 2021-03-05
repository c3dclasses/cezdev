::------------------------------------------------------------------------------------------
:: name: cenvironmentsinit.bat
:: desc: sets up the environments 
::------------------------------------------------------------------------------------------
echo Setting up CEnvironments.............................................
set CENVHOME=%CD%

:: set env variables
set C3DCLASSESSDK_VERSION=1.0
set C3DCLASSESSDK_NAME="C3DCLASSESSDK"
set C3DCLASSESSDK_PATH=%C3DCLASSESSDK_HOME%

:: move all the commands 
call %CENVIRONMENTS_HOME%/ccommands/ccommandsinit
call %CENVIRONMENTS_HOME%/cjava/cjavainit

cd /d %CENVHOME%
echo End Setting up CEnvironments.............................................
