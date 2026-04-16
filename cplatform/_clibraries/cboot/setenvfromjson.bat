::------------------------------------------------------------------------------------------
:: name: setenvfromjson.bat
:: desc: Reads a JSON mapping file and sets an environment variable based on a key
:: usage: setenvfromjson <key_filename> <json_file>
::------------------------------------------------------------------------------------------
@echo off
setlocal ENABLEDELAYEDEXPANSION

if "%~1"=="" (
    echo [ERROR] Usage: setenvfromjson ^<key_filename^> ^<json_file^>
    exit /b 1
)
if "%~2"=="" (
    echo [ERROR] Usage: setenvfromjson ^<key_filename^> ^<json_file^>
    exit /b 1
)

set "KEY_FILENAME=%~1"
set "JSON_FILE=%~2"

:: Use Python to read the JSON and output VAR_NAME=VALUE
for /f "usebackq tokens=1,2 delims==" %%A in (`python -c "import json,os,re,sys; 
data=json.load(open(r'%JSON_FILE%')); 
key=sys.argv[1]; 
if key not in data: sys.exit(1); 
val=data[key]; 
varname=re.sub(r'\W+','_',os.path.splitext(key)[0]).upper(); 
print(f'{varname}={val}')" "%KEY_FILENAME%"`) do (
    set "VAR_NAME=%%A"
    set "VAR_VALUE=%%B"
)

:: Set it in the current shell
if not defined VAR_NAME (
    echo [ERROR] Key '%KEY_FILENAME%' not found in %JSON_FILE%
    exit /b 1
)

set "%VAR_NAME%=%VAR_VALUE%"
echo [INFO] Set %VAR_NAME%=%VAR_VALUE%

endlocal & set "%VAR_NAME%=%VAR_VALUE%"
