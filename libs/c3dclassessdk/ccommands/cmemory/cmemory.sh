##----------------------------------------------------------------------------------
## name: cmemory.sh
## desc: manipulates a memory location
## usage:
## cmemory -config <params> 		- show memory configuration details
## cmemory -cache <params> 			- prints local memory cache
## cmemory -set	<name> <value>		- sets a location locally
## cmemory -get	<name> 				- gets a location locally 
## cmemory -update <name> <value>	- updates a memory location remotely 
## cmemory -retrieve <name>			- retrieves a memory location remotely 
## cmemory -delete <name>			- deletes a memory location locally remotely 
##---------------------------------------------------------------------------------
c3dclassessdk_java.sh CMemoryCommand $CMEMORY_CONFIG $1 $2 "$3"
