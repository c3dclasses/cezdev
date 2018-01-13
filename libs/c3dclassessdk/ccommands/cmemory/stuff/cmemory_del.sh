##--------------------------------------------------------------------------
## name: cmemory_del.sh
## desc: deletes a memory location from cmemory
## usage . cmemory_del <name>
##--------------------------------------------------------------------------
c3dclassessdk_java.sh CMemoryCommand $CMEMORY_CONFIG delete $1
