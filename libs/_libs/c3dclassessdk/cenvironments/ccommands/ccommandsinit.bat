::------------------------------------------------------------------------------------------
:: name: ccommandsinit.bat
:: desc: initializes all the commands in c3dclassessdk 
::------------------------------------------------------------------------------------------
echo Initializing the commands.............................................
set CCMDINIT=%CD%

:: create the commands directory in meta folder and set the PATH
set C3DCLASSESSDK_CCOMMANDS=%EZDEV_META%/ccommands
IF EXIST "%C3DCLASSESSDK_CCOMMANDS%" ( GOTO DONE_CCOMMANDS_INIT )
mkdir "%C3DCLASSESSDK_CCOMMANDS%" 
:DONE_CCOMMANDS_INIT

call %CENVIRONMENTS_HOME%/ccommands/ccommandscopy
call set PATH=%PATH%;%C3DCLASSESSDK_CCOMMANDS%

cd /d %CCMDINIT%
echo End Initializing the commands.............................................