##-------------------------------------------------------------------------------------------------
## name: c3dclassessdk_tojava.sh
## desc: generates a maven project from c3dclassessdk project structure
## usage: c3dclassessdk_tojava.sh EZDEV_HOME/bin/c3dclassessdk EZDEV_HOME/bin/c3dclassessdk_java
##-------------------------------------------------------------------------------------------------

## save the home dir
home=$(pwd)

## check the arguments
if [[ $# -lt 2 ]]; then
	echo "ERROR: usage - $ c3dclassessdk_tojava.sh srcpath dstpath"
fi 

## get the name of the src and dest path
srcpath=$1
dstpath=$2

## check if the source directory exist
if [[ ! -d $srcpath ]]; then 
	echo "ERROR: please provide an existing source directory."
fi

## check if the destination directory exist if not create it
if [[ ! -d $dstpath ]]; then 
	mkdir $dstpath
fi

##############################################
## copy pom files
##############################################
cp $srcpath/pom.* $dstpath

##############################################
## copy src/main/java files
##############################################
if [[ ! -d $dstpath/src ]]; then 
	mkdir $dstpath/src
fi
if [[ ! -d $dstpath/src/main ]]; then 
	mkdir $dstpath/src/main
fi
if [[ ! -d $dstpath/src/main/java ]]; then 
	mkdir $dstpath/src/main/java
fi
# move all java file from source location to dest
cpfiles.sh *.java $srcpath/cglobal $dstpath/src/main/java/cglobal
cpfiles.sh *.java $srcpath/c3dclasses $dstpath/src/main/java/c3dclasses
cpfiles.sh *.java $srcpath/cmain $dstpath/src/main/java/cmain

##############################################
## copy src/test/java files
##############################################
if [[ ! -d $dstpath/src ]]; then 
	mkdir $dstpath/src
fi
if [[ ! -d $dstpath/src/test ]]; then 
	mkdir $dstpath/src/test
fi
if [[ ! -d $dstpath/src/test/java ]]; then 
	mkdir $dstpath/src/test/java
fi
##cpfiles.sh *.tst.java $srcpath/ctest $dstpath/src/test/java
cpfiles.sh *UnitTest.java $srcpath/cglobal $dstpath/src/test/java/cglobal

## go back to home dir
cd $home