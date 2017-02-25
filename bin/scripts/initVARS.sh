##---------------------------------------------------------------------------------------
## name: initVARS.sh
## desc: initializes the configuration vars (defines the state of the application)
##---------------------------------------------------------------------------------------

## init the vars
. setvar.sh EZDEV_PATH $EZDEV_HOME
. setvar.sh EZDEV_META_PATH $EZDEV_META
. setvar.sh EZDEV_BIN_PATH $EZDEV_HOME/bin
. setvar.sh EZDEV_MAIN_PATH $EZDEV_HOME/bin/main
. setvar.sh EZDEV_SCRIPTS_PATH $EZDEV_HOME/bin/scripts
. setvar.sh EZDEV_ENVS_PATH $EZDEV_HOME/envs
. setvar.sh EZDEV_HOSTS /mnt/c/Windows/System32/drivers/etc/hosts
. setvar.sh EZDEV_VARS $EZDEV_META/vars.txt

## this will store lib paths the app depends on 
export EZDEV_VARS_PATH=$EZDEV_HOME/bin/vars
. setvar.sh EZDEV_VARS_PATH $EZDEV_HOME/bin/vars
if [[ ! -e $EZDEV_VARS_PATH ]]; then 
	mkdir $EZDEV_VARS_PATH 
fi

## set gui stuff for ezdev
. setvar.sh EZDEV_ENVS $EZDEV_META\envs.cbx.json
. setvar.sh EZDEV_APPS $EZDEV_META\apps.cbx.json
. setvar.sh EZDEV_CBXS $EZDEV_META\cbxs.txt
. setvar.sh EZDEV_BTNS $EZDEV_META\btns.txt
. setvar.sh EZDEV_MNUS $EZDEV_META\EZDEV_mnus.txt
. setvar.sh EZDEV_ENVS_MNUS $EZDEV_META\envs.mnus.txt
. setvar.sh EZDEV_APPS_MNUS $EZDEV_META\apps.mnus.txt

## update commands
. setvar.sh EZDEV_ENV_ONUPDATE $EZDEV_SCRIPTS_PATH\updateENV.bat
. setvar.sh EZDEV_APP_ONUPDATE $EZDEV_SCRIPTS_PATH\updateAPP.bat

. setvar.sh EZDEV_ONCOMMAND $EZDEV_SCRIPTS_PATH\doCommand.bat
. setvar.sh EZDEV_ONSELECT $EZDEV_SCRIPTS_PATH\doSelection.bat
. setvar.sh EZDEV_TITLE "Easy Developement (EZDEV)"
. setvar.sh EZDEV_LOOKANDFEEL com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
. setvar.sh EZDEV_WIDTH 400
. setvar.sh EZDEV_HEIGHT 300

