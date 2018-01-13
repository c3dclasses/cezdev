::--------------------------------------------------------------------------
:: name: cmemory_set.bat
:: desc: sets a cmemory location 
:: usage cmemory_set <name>
::--------------------------------------------------------------------------
c3dclassessdk_java CMemoryCommand %CMEMORY_CONFIG% create %~1 %2
