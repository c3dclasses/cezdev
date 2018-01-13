##--------------------------------------------------------------------------
## name: cmemory_setcmd.sh
## desc: sets a cmemory location from the output contents of a command 
## usage: cmemory_setcmd.bat <varname> "<varcmd>"
##--------------------------------------------------------------------------
varname=$1
varcmd=$2
varfile=$CMEMORY_FILE%

:: run the command and redirect output to file
$varcmd > $varfile

:: store the contents to cmemory
cmemory_setf.sh $varname $varfile
