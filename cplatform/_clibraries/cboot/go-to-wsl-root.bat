@echo off
:: Get default WSL distro
for /f "tokens=2 delims=* " %%i in ('wsl --list --verbose ^| find "*"') do set Distro=%%i

:: Get Linux home path
for /f "usebackq tokens=*" %%i in (`wsl -d %Distro% sh -c "echo $HOME"`) do set HomePath=%%i

:: Build UNC path
set UNC=\\wsl.localhost\%Distro%%HomePath%

:: Change directory
cd /d %UNC%

:: Show where we landed
cd
