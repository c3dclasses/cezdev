##--------------------------------------------------------------------------
## name: cmemory_set.sh
## desc: sets a cmemory location 
## usage cmemory_set.sh <name> <value>
##--------------------------------------------------------------------------
c3dclassessdk_java.sh CMemoryCommand $CMEMORY_CONFIG create $1 "$2"
