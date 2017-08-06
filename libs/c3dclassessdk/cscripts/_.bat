::------------------------------------------------------------------------------------------
:: name: _.bat
:: desc: stores the results of a command into the _ variable 
:: usage: _ myvar "cmemory_get foo"
::------------------------------------------------------------------------------------------
@echo off
set envvar=%~1
set envcmd=%~2

:: echo "%envvar%"
:: echo "%envcmd%"

:: set the out file
set outfile="C:/Users/developer/Desktop/cezdev2/meta/temp.out"
if exist %outfile% ( del %outfile% )

:: run the command and redirect output to file
call %envcmd% > %outfile%

:: set the env variable
set /P varout=<%outfile% 
if defined varout (
 :: alert "%varout%"
	set "%envvar%=%varout%"
)

:: check if the variable was set
if "%envvar%" == "" (
    alert "ERROR: Could not include var: %envvar%"
    exit
)

:: remove the outfile
del %outfile%
