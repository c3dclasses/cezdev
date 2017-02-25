##------------------------------------------------------------------------------------------
## name: java_build.sh
## desc: builds all of the java functionality needed by EZDEV to function
##------------------------------------------------------------------------------------------

echo $1

## go to the java directory
cd $1

## compile all the java files in the directory
javac *.java

## add all the files to the jar file
jar cvfe CEZDEV.jar *.class 

## remove all the classes
rm *.class

## loop through each directory
for f in *; do
	if [[ -d $f ]]; then
		java_build.sh $(pwd)/$f
	fi
done

## go to the java directory
##cd $EZDEV_HOME/bin/main/alert
## compile and run alert.java
##javac.exe -cp . *.java
## move all of the classes to meta directory
##mv *.class $EZDEV_META
## run the java alert
##cd $EZDEV_META
##java alert $@
## go back to EZDEV home directory
## cd $EZDEV_HOME
##java.exe 
## show the message box
##vbs_messagebox %*
## set the return value
##set /P return=<alert.out 
## delete the file
##del alert.out