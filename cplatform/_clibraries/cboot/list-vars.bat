::----------------------------------------------------------------------------------------------------------
:: name: list-vars
:: usage: 
::--------------------------------------------------------------------------------------------------------
set LISTVARSHOME=%CD%
cd /d %CBOOT%
set CURRENT_VARS_LIST_TXT=%CMETADATA%\current_vars.txt
:: Compare with current session
set > %CURRENT_VARS_LIST_TXT%
echo [INFO] User-defined variables since baseline:
:: fc %CBASEENVVARS% %CURRENT_VARS_LIST_TXT%
python CCompareEnvironmentVariablesCommand.py %CBASEENVVARS% %CURRENT_VARS_LIST_TXT%

cd /d %LISTVARSHOME%