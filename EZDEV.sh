##------------------------------------------------------
## name: EZDEV.sh
## desc: starts the EZDEV application
##------------------------------------------------------

## starting message
echo Starting EasyDeveloper ..........................................

## initialize the variables 
export EZDEV_VERSION=1.0
export EZDEV_NAME=EZDEV
export EZDEV_DEBUG=true
export EZDEV_HOME=$(pwd)
export EZDEV_META=$EZDEV_HOME/meta

## create the meta directory if it doesn't exist
if [[ ! -d $EZDEV_META ]]; then 
	mkdir $EZDEV_META
fi

## initialize c3dclassessdk
cd $EZDEV_HOME/libs/c3dclassessdk/ccommands
. ./c3dclassessdk.sh

## pause before terminating
read -p "Press enter to continue"

## run the terminal to run additional commands
bash