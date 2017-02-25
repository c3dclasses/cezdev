##---------------------------------------------------------------------------------------------
## name: include_varpath
## desc: includes a variable path by prompting user for location
## usage: include_varfile <ENVVARNAME> <PROMPT_MSG>
## (ex.): include_varfile NODE_EXE "Set NODE_EXE - Please locate where node executable file 
##			is location in your system
##---------------------------------------------------------------------------------------------

## save the path to the working directory
home=$(pwd)

## get command line args
envvar="$1"
envtitle="$2"

## check if the environment variable exist and directory exist before prompting user for it
if [[ -v $envvar ]]; then
	## check if the dir exist
	file=${!envvar}
	if [[ -f $file ]]; then
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
	files=$(cat $envvarfile)       
	for file in $files ; do  
		if [[ -f $file ]]; then
			alert.sh $envvar=$file
			. setvar.sh $envvar $file 
			cd $home
			return
		fi       
	done          
	## done          
	IFS=$old_IFS     # restore default field separator 
fi

## open file chooser and select a path
path=$(java_run.sh CFileDialogBox $envtitle file)
echo $path >> $envvarfile
. include_varfile.sh $1 $2

## go back to the home dirctory
cd $home