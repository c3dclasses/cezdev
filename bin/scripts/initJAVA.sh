##------------------------------------------------------
## name: initJAVA.bat
## desc: initializes the JAVA/MAVEN environment
##------------------------------------------------------

## save the home dir
home=$(pwd)

## (Important) sets the JAVA_BIN path - required so we can run the commands - EZDEV will not function without this 
. setvar.sh JAVA_BIN $EZDEV_HOME/libs/java/jdk1.7.0/bin
. setvar.sh MAVEN_BIN $EZDEV_HOME/libs/java/apache-maven-3.2.3/bin
. setvar.sh PATH "$PATH:$JAVA_BIN:$MAVEN_BIN:$(pwd)/cmds:$(pwd)"

## go to the java directory
cd $EZDEV_HOME/bin/java

## run maven package to create the EZDEV.jar
mvn install -Drelease.artifactId=$EZDEV_NAME -Drelease.version=$EZDEV_VERSION

## go back to home dir
cd $home