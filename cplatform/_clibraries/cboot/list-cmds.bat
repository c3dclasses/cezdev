::----------------------------------------------------------------------------------------------------------
:: name: list-cmds
:: usage: 
::--------------------------------------------------------------------------------------------------------
set LISTCMDSHOME=%CD%
cd /d %CBOOT%
call python CListFilesOfTypeInFolderCommand.py .bat %CBOOT% %C3DCLASSESSDK_PATH% %CPROJECTS%
cd /d %LISTCMDSHOME%