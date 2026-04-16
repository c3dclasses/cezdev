::------------------------------------------------------------------------------------------
:: name: cjava.bat
:: desc: Runs a Java program or class file
:: usage: cjava ClassName [args...]
::------------------------------------------------------------------------------------------

@echo off
setlocal

set "CJAVAHOME=%CD%"

::------------------------------------------------------
:: Run Java with the C3DClasses classpath
::------------------------------------------------------
cd /d "%C3DCLASSES_JAVA%\target"
call java -cp "%C3DCLASSES_JAR%;%CJAVAHOME%" %*

cd /d "%CJAVAHOME%"
endlocal