::-----------------------------------------------------------------------------------
:: name: cmemory_getenvvar.bat
:: desc: retrieves a variable stored in cmemory and store it in cmemory_envvar
:: usage: cmemory_getenvvar.sh <name> ---> %cmemory_envvar% has the contents
::-----------------------------------------------------------------------------------
_ cmemory_envvar "cmemory_get %1"


