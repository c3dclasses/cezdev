::---------------------------------------------------------------------------------------------------
:: name: cjavac.bat
:: desc: Compiles the entire C3DClasses framework or compiles single files against the framework
:: usage: cjavac [JavaFile.java ...]
::---------------------------------------------------------------------------------------------------

@echo off

set "CJAVACHOME=%CD%"

if "%1"=="" goto NOPARAM
    javac -classpath %C3DCLASSES_CLASSPATH% %*
    goto DONE

:NOPARAM
call cjava.update.bat
goto DONE

:DONE
cd /d "%CJAVACHOME%"
