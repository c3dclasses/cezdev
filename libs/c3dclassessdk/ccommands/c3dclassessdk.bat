::------------------------------------------------------
:: name: c3dclassessdk
:: desc: 
::------------------------------------------------------
set C3DCLASSESSDK_VERSION=1.0
set C3DCLASSESSDK_NAME=EZDEV
set C3DCLASSESSDK_PATH=%EZDEV_HOME%/libs/c3dclassessdk
set C3DCLASSESSDK_COMMANDS=%C3DCLASSESSDK_PATH%/ccommands

:: save the home dir
SET C3DCLASSESSDK_HOME=%CD%

:: set the commands on the path
set PATH=%PATH%;%C3DCLASSESSDK_COMMANDS%
cd /d %C3DCLASSESSDK_COMMANDS%
for /D %%s in (*) do (
	call setpath %C3DCLASSESSDK_COMMANDS%/%%s
)

:: convert the c3dclasses project to a java maven project
call c3dclassessdk_tojava

:: go back to home dir
cd /d %C3DCLASSESSDK_HOME%