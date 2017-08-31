##------------------------------------------------------------------------------------------
## name: c3dclassessdk_java.sh
## desc: runs a java program defined in c3dclassessdk 
## usage: c3dclassessdk_java classnametorun params
##------------------------------------------------------------------------------------------

# check the arguments
if [[ $# -lt 1 ]]; 
    then echo "ERROR: illegal number of parameters"
	exit 1
fi

## save home dir
home=$(pwd)

## go to the meta directory
cd $EZDEV_HOME/meta/c3dclassessdk_java/target

## run the class in the jar file - pass all args to the java program
java -cp $C3DCLASSESSDK_NAME-$C3DCLASSESSDK_VERSION-jar-with-dependencies.jar $1 "$@"

## go back to home dir
cd $home