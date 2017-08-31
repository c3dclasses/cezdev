::------------------------------------------------------------------------------------------
:: name: c3dclassessdk_java.bat
:: desc: runs a java program defined in c3dclassessdk 
:: usage: c3dclassessdk_java classnametorun params
::------------------------------------------------------------------------------------------

:: check the arguments
if "" == "%1" ( 
    echo "ERROR: illegal number of parameters - c3dclassessdk_java <classnametorun> <optional-params>"
	exit 1
)

:: save home dir
setlocal
set home=%CD%

:: go to the meta directory
cd /d %EZDEV_HOME%/meta/c3dclassessdk_java/target

:: run the class in the jar file - pass all args to the java program
call java -cp %C3DCLASSESSDK_NAME%-%C3DCLASSESSDK_VERSION%-jar-with-dependencies.jar %1 %*
:: echo %C3DCLASSESSDK_NAME%-%C3DCLASSESSDK_VERSION%-jar-with-dependencies.jar %1 "%*"

:: go back to home dir
cd /d %home%
endlocal