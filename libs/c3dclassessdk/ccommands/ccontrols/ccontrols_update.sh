##--------------------------------------------------------------------------
## name: ccontrols_update.sh
## desc: sends a command to ccontrolscommand via cmemory
## usage: ccontrols_update.sh <identifier> <value> <params>
## example: 
## ccontrols_update.sh myform visible true
## ccontrols_update.sh myform.mybutton visible false
##--------------------------------------------------------------------------
cmemory_set.sh ccontrol-command "update $1 $2 $3 $4"