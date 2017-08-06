::--------------------------------------------------------------------------
:: name: cmemory_del.bat
:: desc: deletes a memory location from cmemory
:: usage . cmemory_del <name>
::--------------------------------------------------------------------------
c3dclassessdk_java CMemoryCommand %CCMEMORY_CONFIG% delete %~1
