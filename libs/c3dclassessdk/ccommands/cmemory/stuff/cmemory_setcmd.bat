::--------------------------------------------------------------------------
:: name: cmemory_setcmd.bat
:: desc: sets a cmemory location from the output contents of a command 
:: usage: cmemory_setcmd.bat <varname> "<varcmd>"
::--------------------------------------------------------------------------
set varname=%~1
set varcmd=%~2
set varfile="%CMEMORY_FILE%"

:: run the command and redirect output to file
call %varcmd% > %varfile%

:: store the contents to cmemory
cmemory_setf %varname% %varfile%

