##------------------------------------------------------
## name: initC3DCLASSESSDK.sh
## desc: initializes the c3dclassessdk framework
##------------------------------------------------------

## save the home dir
home=$(pwd)

## make the c3dclassessdk scripts executable
cd $EZDEV_HOME/libs/c3dclassessdk/cscripts
chmod 755 *.sh
cd $home

## set the PATH variable
export PATH=$PATH:$EZDEV_HOME/libs/c3dclassessdk/cscripts

## build the java mvn project from the c3dclassessdk project
. c3dclassessdk_tojava.sh $EZDEV_HOME/libs/c3dclassessdk $EZDEV_HOME/meta/c3dclassessdk_java

## (Important) sets the JAVA_BIN path - required so we can run the commands - EZDEV will not function without this 
. setvar.sh JAVA_BIN $EZDEV_HOME/libs/java/jdk1.8.0_121/bin
. setvar.sh MAVEN_BIN $EZDEV_HOME/libs/java/apache-maven-3.2.3/bin
. setvar.sh PATH "$PATH:$JAVA_BIN:$MAVEN_BIN:$(pwd)/cmds:$(pwd)"

## go to the java directory
cd $EZDEV_HOME/meta/c3dclassessdk_java

## run maven package to create the EZDEV.jar
mvn clean install test -e -Drelease.artifactId=$C3DCLASSESSDK_NAME -Drelease.version=$C3DCLASSESSDK_VERSION

## go back to home dir
cd $home