##--------------------------------------------------------------------------
## name: setvar.sh
## desc: sets a configuration variable for the EZDEV java application
## usage . setvar <name> <value>
##--------------------------------------------------------------------------

## get the name/value pairs from the args
name=$1
value=$2

## create the env variable
export $name="$value"

## replace underscores with dots (look fancier for java)
name=${name//_/.}

## convert the string to lowercase
name=${name,,}

## store name/value into vars file for java to see
echo $name="$value" >> $EZDEV_HOME/meta/vars.txt