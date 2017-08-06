##--------------------------------------------------------------------------
## name: cmemory_setf.sh
## desc: sets a cmemory location contents from a file  
## usage cmemory_setf.sh <name> "C:/filepath/filename.txt"
##--------------------------------------------------------------------------
c3dclassessdk_java.sh CMemoryCommand $CMEMORY_CONFIG fcreate $1 "$2"
