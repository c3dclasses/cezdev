##-------------------------------------------------------------------------------------------------
## name: cpfiles.sh
## desc: cpfiles of a given format from a src path to a dest path
## usage: . cpfiles.sh .js format srcdir dstdir
##-------------------------------------------------------------------------------------------------

## check the arguments
if [[ $# -lt 3 ]]; then
	echo "ERROR: usage - $ . movefiles.sh ext srcdir dstdir"
	exit 1
fi 

## get the name of the src and dest path and format to move
ext=$1
srcpath=$2
dstpath=$3

## check if the source directory exist
if [[ ! -d $srcpath ]]; then 
	echo "ERROR: please provide an existing source directory."
	exit 1
fi

## check if the destination directory exist if not create it
if [[ -z $dstpath ]]; then 
	echo "ERROR: please provide a valid destination directory."
	exit 1
fi

## check if the destination directory exist if not create it
if [[ ! -d $dstpath ]]; then 
	mkdir $dstpath
fi

## copy all files with given extension
cd $srcpath
cp $ext $dstpath

## recursively do the same above operation for each directory
for file in $(ls); do
	if [[ -d $srcpath/$file ]]; then
		cpfiles.sh "$ext" "$srcpath/$file" "$dstpath/$file"
	fi
done 

exit