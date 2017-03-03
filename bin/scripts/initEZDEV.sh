##------------------------------------------------------
## name: initEZDEV.bat
## desc: initializes the EZDEV environment
##------------------------------------------------------

## make the commands executable
cd cmds
chmod 755 *.sh
cd ..

## set the PATH variable
export PATH=$PATH:$(pwd)/cmds:$(pwd):$EZDEV_HOME/meta

## create meta directory env var
. setvar.sh EZDEV_META $EZDEV_HOME/meta

## add the meta, scripts, scripts/cmd
. setvar.sh PATH "$PATH:$(pwd)/cmds:$(pwd):$EZDEV_META"

## create a new meta directory in home folder
if [[ ! -e $EZDEV_META ]]; then 
	mkdir $EZDEV_META 
fi

## remove the _export.txt file
rm $EZDEV_META/*.txt

## initialize JAVA
. initJAVA.sh

## set ezdevs bin dir
. initBIN.sh

## initialize the configuration variables
. initVARS.sh

## initialize ezdev controls
. initEZDEV_controls.sh

## pause before terminating
read -p "Press enter to continue"
