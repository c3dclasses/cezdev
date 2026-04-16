:: --------------------------------------------------
:: cboot.create.bat
:: Compiles cboot Java utilities and adds to PATH
:: --------------------------------------------------
@echo off

javac %CBOOT%\*.java 2>nul
set PATH=%PATH%;%CBOOT%
