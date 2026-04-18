::---------------------------------------------------------------------------------------------------
:: name: cjavac.bat
:: desc: Compiles the entire C3DClasses framework or compiles single files against the framework
:: usage: cjavac [JavaFile.java ...]
::---------------------------------------------------------------------------------------------------
@echo off
set "CJAVACHOME=%CD%"
echo [CALLING] %~nx0

if "%1"=="" goto NOPARAM
javac -classpath "%C3DCLASSES_CLASSPATH%" %*
goto DONE

:NOPARAM
call "%~dp0cjava.update.bat"
goto DONE

:DONE
echo [ENDING] %~nx0
cd /d "%CJAVACHOME%"
