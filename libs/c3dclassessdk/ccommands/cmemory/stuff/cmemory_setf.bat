::--------------------------------------------------------------------------
:: name: cmemory_setf.bat
:: desc: sets a cmemory location contents from a file  
:: usage cmemory_setf <name> "C:/filepath/filename.txt"
::--------------------------------------------------------------------------
c3dclassessdk_java CMemoryCommand %CMEMORY_CONFIG% fcreate %~1 %2
