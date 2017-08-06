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
export C3DCLASSESSDK_VERSION=1.0
export C3DCLASSESSDK_NAME=EZDEV
export C3DCLASSESSDK_PATH=$EZDEV_HOME/libs/c3dclassessdk

## initialize EZDEV
cd $C3DCLASSESSDK_PATH/cscripts
. ./initc3dclassessdk.sh

## pause before terminating
read -p "Press enter to continue"

## run the terminal to run additional commands
bash