##-------------------------------------------------------------------------------------------------
## name: cprnfiles.sh
## desc: copy and rename files of a given format from a src path to a dest path
## usage: . cpfiles.sh format srcdir exttoremove dstdir
##-------------------------------------------------------------------------------------------------

## check the arguments
if [[ $# -lt 4 ]]; then
	echo "ERROR: usage - $ . cpfiles.sh ext1 srcdir ext2 dstdir"
	exit 1
fi 

## get the name of the src and dest path and format to move
ext1=$1
srcpath=$2
ext2=$3
dstpath=$4

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
cp $ext1 $dstpath/$ext2

## recursively do the same above operation for each directory
for file in $(ls); do
	if [[ -d $srcpath/$file ]]; then
		cpfiles.sh "$ext1" "$srcpath/$file" "$ext2" "$dstpath/$file"
	fi
done 

exit