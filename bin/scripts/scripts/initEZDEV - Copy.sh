##------------------------------------------------------
## name: initEZDEV.bat
## desc: initializes the EZDEV environment
##------------------------------------------------------

## setup c3dclassessdk runtime framework
. initC3DCLASSESSDK.sh


## create meta directory env var
##export EZDEV_META=$EZDEV_HOME/meta
## create a new meta directory in home folder
##if [[ ! -e $EZDEV_META ]]; then
##	mkdir $EZDEV_META 
##fi

## make the commands executable
## cd cmds
## chmod 755 *.sh
## cd ..

## set the PATH variable
## export PATH=$PATH:$(pwd):$(pwd)/cmds:$EZDEV_HOME/meta

## initialize JAVA 
## . initJAVA.sh

## create meta directory env var
## . setvar.sh EZDEV_META $EZDEV_HOME/meta

## add the meta, scripts, scripts/cmd
## . setvar.sh PATH "$PATH:$(pwd)/cmds:$(pwd):$EZDEV_META"

## create a new meta directory in home folder
## if [[ ! -e $EZDEV_META ]]; then 
##  mkdir $EZDEV_META 
##fi

## remove the _export.txt file
##rm $EZDEV_META/*.txt

## initialize c3dclassessdk
## . initC3DCLASSESSDK.sh

## initialize JAVA
## . initJAVA.sh

## set ezdevs bin dir
## . initBIN.sh

## initialize the configuration variables
## . initVARS.sh

## initialize ezdev controls
## . initEZDEV_controls.sh

## pause before terminating
## read -p "Press enter to continue"
