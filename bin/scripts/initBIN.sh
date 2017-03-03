##------------------------------------------------------
## name: initBIN.bat
## desc: initializes the commands in this directory
##------------------------------------------------------

## include java path
. include_varpath.sh JAVA_HOME "Set JAVA_HOME - Please locate where java environment is in your system."

## check if the JAVA_HOME path exist
if [[ -d $JAVA_HOME ]]; then 
	. setvar.sh JAVA_PATH $JAVA_HOME
	. setvar.sh JAVA_BIN_PATH $JAVA_HOME/bin
	. setvar.sh JAVA_JRE_PATH $JAVA_HOME/jre
else
	alert.sh "ERROR in initBIN.sh reason: JAVA_HOME variable is not properly set!!! Please set the path correctly!"
	exit
fi