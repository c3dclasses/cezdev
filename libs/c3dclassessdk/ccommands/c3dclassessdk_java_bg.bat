::------------------------------------------------------------------------------------------
:: name: c3dclassessdk_java_bg.bat
:: desc: runs a java program defined in c3dclassessdk in the background
:: usage: c3dclassessdk_java_bg classnametorun params
::------------------------------------------------------------------------------------------

:: run the class in the jar file - pass all args to the java program
cd /d %EZDEV_HOME%/meta/c3dclassessdk_java/target
call java -cp %C3DCLASSESSDK_NAME%-%C3DCLASSESSDK_VERSION%-jar-with-dependencies.jar %1 %*
exit