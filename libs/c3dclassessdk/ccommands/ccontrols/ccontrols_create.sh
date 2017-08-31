##----------------------------------------------------------------------------------------------
## name: ccontrol_create.bat
## desc: sends a command to ccontrolscommand via cmemory
## usage: ccontrol_create <controltype> <identifier> <value> <params>
## note: uses cmemory object to sends a message of commands to CControlsCommand program 
## example: 
## ccontrol_create form myform thisismyform "param1 param2 param3"
## ccontrol_create button myform.mybutton "thisismybutton" "param1 param2 param3"
##----------------------------------------------------------------------------------------------
cmemory_set.sh ccontrol-command "create $1 $2 $3 $4"
cmemory_set.sh ccontrol-command "create $1 $2 $3 $4"