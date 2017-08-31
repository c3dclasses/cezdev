::--------------------------------------------------------------------------
:: name: ccontrols_update.bat
:: desc: sends a command to ccontrolscommand via cmemory
:: usage: ccontrols_update <identifier> <value> <params>
:: example: 
:: ccontrols_update myform visible true
:: ccontrols_update myform.mybutton visible false
::--------------------------------------------------------------------------
cmemory_set ccontrol-command "update %1 %2 %3 %4"