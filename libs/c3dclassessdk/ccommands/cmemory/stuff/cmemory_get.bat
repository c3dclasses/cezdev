::--------------------------------------------------------------------------
:: name: cmemory_get.sh
:: desc: retrieves a variable stored in cmemory location
:: usage cmemory_get.sh <name>
::--------------------------------------------------------------------------
@echo off
call c3dclassessdk_java CMemoryCommand %CMEMORY_CONFIG% retrieve %~1


