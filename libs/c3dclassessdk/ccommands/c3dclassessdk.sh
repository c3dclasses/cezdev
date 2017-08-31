##------------------------------------------------------
## name: c3dclassessdk_init.sh
## desc: initializes the c3dclassessdk framework
##------------------------------------------------------
export C3DCLASSESSDK_VERSION=1.0
export C3DCLASSESSDK_NAME=EZDEV
export C3DCLASSESSDK_PATH=$EZDEV_HOME/libs/c3dclassessdk
export C3DCLASSESSDK_COMMANDS=$C3DCLASSESSDK_PATH/ccommands

## move this to cmemory - memory initializes itself
export CMEMORY_CONFIG=$C3DCLASSESSDK_COMMANDS/cmemory/cjsonmemoryconfig.json
export CMEMORY_FILE=$EZDEV_HOME/meta/cmemory.tmp

## save the home dir
home=$(pwd)

## make the c3dclassessdk scripts executable
## cd $C3DCLASSESSDK_PATH/cscripts
## chmod 755 *.sh
## cd $home

## set the PATH variable
for command in $(ls $C3DCLASSESSDK_COMMANDS); do
	if [[ -d $C3DCLASSESSDK_COMMANDS/$command ]]; then
		export PATH=$PATH:$C3DCLASSESSDK_COMMANDS/$command
	fi
done 

## build the java mvn project from the c3dclassessdk project
. c3dclassessdk_tojava.sh $C3DCLASSESSDK_PATH $EZDEV_HOME/meta/c3dclassessdk_java

## (Important) sets the JAVA_BIN path - required so we can run the commands - EZDEV will not function without this 
export JAVA_BIN=$EZDEV_HOME/libs/java/jdk1.8.0_121/bin
export MAVEN_BIN=$EZDEV_HOME/libs/java/apache-maven-3.2.3/bin
export PATH=$PATH:$JAVA_BIN:$MAVEN_BIN:$(pwd)/cmds:$(pwd)

## go to the java directory
cd $EZDEV_HOME/meta/c3dclassessdk_java

## run maven package to create the EZDEV.jar
mvn clean install test -e -Drelease.artifactId=$C3DCLASSESSDK_NAME -Drelease.version=$C3DCLASSESSDK_VERSION

## go back to home dir
cd $home