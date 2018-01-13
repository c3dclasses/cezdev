##--------------------------------------------------------------------------
## name: ccontrols_retrieve.sh
## desc: sends a command to ccontrolscommand via cmemory
## usage: ccontrols_retrieve.sh <identifier> <propertytoretrieve>
## example: 
## ccontrols_retrieve.sh myform visible
## ccontrols_retrieve.sh .shmyform.mybutton visible
##--------------------------------------------------------------------------
cmemory_set.sh ccontrol-command "retrieve $1 $2 $3 $4"
cmemory_get.sh ccontrol-command-output 