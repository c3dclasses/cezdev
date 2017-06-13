##--------------------------------------------------------
## name: initEZDEV_controls.sh
## desc: initializes the (ezdev) controls
##--------------------------------------------------------

############################
## save the home dir
home=$(pwd)

###################################################
## ezdev controls

## create a new meta directory in home folder
if [[ ! -d "$EZDEV_HOME/bin/controls" ]]; then 
	mkdir "$EZDEV_HOME/bin/controls"
fi

## go into the directory
cd "$EZDEV_HOME/bin/controls"

## enumerate all of the ezdev controls
dir=$(pwd)
for file in $(ls *.json); do     
	echo $dir/$file >> ezdev.controls.txt
done

if [[ -f ezdev.controls.txt ]]; then
	## copy enumeration controls to meta folder
	cp ezdev.controls.txt $EZDEV_META
	## delete the enumeration files
	rm *.controls.txt
fi 

###################################################
## envs controls

## create a new meta directory in home folder
if [[ ! -d "$EZDEV_HOME/bin/controls/envs" ]]; then 
	mkdir "$EZDEV_HOME/bin/controls/envs"
fi

## go into the directory
cd "$EZDEV_HOME/bin/controls/envs"

## enumerate all of the ezdev controls
dir=$(pwd)
for file in $(ls *.json); do     
	echo $dir/$file >> ezdev.envs.controls.txt
done
 
if [[ -f ezdev.envs.controls.txt ]]; then
	## copy enumeration controls to meta folder
	cp ezdev.envs.controls.txt $EZDEV_META
	## delete the enumeration files
	rm *.controls.txt
fi

###################################################
## apps controls

## create a new meta directory in home folder
if [[ ! -d "$EZDEV_HOME/bin/controls/apps" ]]; then 
	mkdir "$EZDEV_HOME/bin/controls/apps"
fi

## go into the directory
cd "$EZDEV_HOME/bin/controls/apps"

## enumerate all of the ezdev controls
dir=$(pwd)
for file in $(ls *.json); do     
	echo $dir/$file >> ezdev.apps.controls.txt
done
 
if [[ -f ezdev.apps.controls.txt ]]; then
	## copy enumeration controls to meta folder
	cp ezdev.apps.controls.txt $EZDEV_META
	## delete the enumeration files
	rm *.controls.txt
fi

###############################
## go back to the home dir
cd $home