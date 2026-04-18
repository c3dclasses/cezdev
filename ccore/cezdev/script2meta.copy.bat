@echo off
echo [CALLING] %~nx0
for %%I in ("%~1") do set "dirname=%%~nI"
call scripts.copy.bat "%~1" "%CMETADATA%\%dirname%"
set "PATH=%PATH%;%CMETADATA%\%dirname%"
echo [ENDING] %~nx0
