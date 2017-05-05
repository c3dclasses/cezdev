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
if [[ ! -z $dstpath ]]; then 
	echo "ERROR: please provide a valid destination directory."
	exit 1
fi


## check if the destination directory exist if not create it
if [[ ! -d $dstpath ]]; then 
	mkdir $dstpath
fi

cd $srcpath
echo "copy: "
echo "ext: $ext"
echo "from: $srcpath"
echo "to: $dstpath"
ls $srcpath/$ext
cp $ext $dstpath

for file in $(ls); do
	if [[ -d $srcpath/$file ]]; then
		echo "go to: "
	 	echo "$srcpath/$file"
		echo "$dstpath/$file"
		cpfiles.sh "$ext" "$srcpath/$file" "$dstpath/$file"
	fi
done 

exit

## copy all files with format to the dstpath
##ls *.java
##echo "srcpath: $srcpath"
##echo "dstpath: $dstpath"
##cd $srcpath
##cp $format $dstpath
## recurse through each directory and move files there too
##for dir in $(ls -d */ || exit 1); do
##	echo $dir
##cpfiles.sh $format $srcpath/$dir $dstpath/$dir
##done 
## go back to home dir
## cd $home
##echo "end #################"