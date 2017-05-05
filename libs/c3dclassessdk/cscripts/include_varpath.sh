##---------------------------------------------------------------------------------------------
## name: include_varpath
## desc: includes a variable path by prompting user for location
## usage: include_varpath <ENVVARNAMR> <PROMPT_MSG>
## (ex.): include_varpath JAVA_HOME "Set JAVA_HOME - Please locate where java environment 
##			is location in your system
##---------------------------------------------------------------------------------------------

## save the path to the working directory
home=$(pwd)

## get command line args
envvar="$1"
envtitle="$2"

## check if the environment variable exist and directory exit before prompting user for it
if [[ -v $envvar ]]; then
	## check if the dir exist
	path=${!envvar}
	if [[ -d $path ]]; then
		alert.sh "the variable exist $envvar=${!envvar}"
		cd $home
		return
	fi
fi

## check if the vars file for the environment variable exist, if so try to check existence of the paths in the file
envvarfile=$EZDEV_HOME/bin/vars/$envvar.vars
if [[ -f $envvarfile ]]; then
	old_IFS=$IFS      # save the field separator           
	IFS=$'\n'     # new field separator, the end of line   
	paths=$(cat $envvarfile)       
	for path in $paths ; do  
		if [[ -d $path ]]; then
			alert.sh $envvar=$path
			. setvar.sh $envvar $path 
			cd $home
			return
		fi       
	done          
	IFS=$old_IFS     # restore default field separator 
fi

## open file chooser and select a path
path=$(c3dclassessdk_java.sh CFileDialogBox "Open Path" dir)
echo $path >> $envvarfile
. include_varpath.sh $1 $2

## go back to the home dirctory
cd $home