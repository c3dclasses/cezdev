##------------------------------------------------------------------------------------------
## name: java_run.sh
## desc: runs a java program 
## usage: java_run classnametorun params
##------------------------------------------------------------------------------------------

# check the arguments
if [[ $# -lt 1 ]]; 
    then echo "ERROR: illegal number of parameters"
	exit 1
fi

## save home dir
home=$(pwd)

## go to the meta directory
cd $EZDEV_HOME/bin/java/target

## run the class in the jar file - pass all args to the java program
java -cp $EZDEV_NAME-$EZDEV_VERSION-jar-with-dependencies.jar $1 "$@"

## go back to home dir
cd $home