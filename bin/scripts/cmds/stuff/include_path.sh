##------------------------------------------------------
## name: include_path
## desc: adds a path to the path environment variable
## %1 - the path to add to the path variable
##------------------------------------------------------

:: check parameter
if "%~1" == "" ( 
	alert "ERROR: %CD%/include_path.bat - please provide a correct path"
	exit
)

## store export line to ezdev exports 
echo export $name="\"$value\"" >> $EZDEV_EXPORTS

## replace underscores with dots
name=${name//_/.}

## convert the string to lowercase
name=${name,,}

## store name/value into vars file
echo $name="$value" >> $EZDEV_META/vars.txt