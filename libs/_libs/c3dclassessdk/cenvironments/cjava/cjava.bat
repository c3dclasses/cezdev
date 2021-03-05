::------------------------------------------------------------------------------------------
:: name: cjava.bat
:: desc: runs a java program or class file
::------------------------------------------------------------------------------------------
setlocal
set THISDIR=%CD%
cd /d %EZDEV_HOME%/meta/c3dclassessdk_java/target
call java -cp %C3DCLASSESSDK_JAR%;%THISDIR% %1 %*
cd /d %THISDIR%
endlocal