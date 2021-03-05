::---------------------------------------------------------------------------------------------------
:: name: cjavac.bat
:: desc: compiles the entire c3dclassessdk framework or compiles single files against the framework
::---------------------------------------------------------------------------------------------------
set CJAVACHOME=%CD%
IF "%1"=="" GOTO NOPARAM
	javac -classpath %C3DCLASSESSDK_CLASSPATH% %*
GOTO DONE
:NOPARAM
call cenvironmentsupdate
call %CENVIRONMENTS_HOME%/cjava/cjavacopy
GOTO DONE
:DONE
cd /d %CJAVACHOME%
