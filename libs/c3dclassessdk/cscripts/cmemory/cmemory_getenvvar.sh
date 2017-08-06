##-----------------------------------------------------------------------------------
## name: cmemory_getenvvar.sh
## desc: retrieves a variable stored in cmemory and store it in cmemory_envvar
## usage: cmemory_getenvvar.sh <name> ---> %cmemory_envvar% has the contents
##-----------------------------------------------------------------------------------
export cmemory_envvar=$(cmemory_get.sh $1)
