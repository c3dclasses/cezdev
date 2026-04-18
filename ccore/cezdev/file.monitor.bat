@echo off
echo [CALLING] %~nx0
start python "%~dp0file.monitor.py" %*
echo [ENDING] %~nx0
