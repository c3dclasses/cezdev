::--------------------------------------------------------------------------
:: name: ccontrols_retrieve.bat
:: desc: sends a command to ccontrolscommand via cmemory
:: usage: ccontrols_retrieve <identifier> <propertytoretrieve>
:: example: 
:: ccontrols_retrieve myform visible
:: ccontrols_retrieve myform.mybutton visible
::--------------------------------------------------------------------------
cmemory_set ccontrol-command "retrieve %1 %2 %3 %4"
cmemory_get ccontrol-command-output 