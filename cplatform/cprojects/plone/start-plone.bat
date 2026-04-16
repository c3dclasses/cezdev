@echo off
::------------------------------------------------------------------------------------------
:: name: start-plone.bat
:: desc: Starts the Plone project inside WSL Ubuntu.
:: usage: start-plone
::------------------------------------------------------------------------------------------

echo Starting Plone inside WSL...

REM Enter WSL Ubuntu, change to the Plone directory, and run the start command.
start wsl -d Ubuntu bash -ic "cd /home/c3dclasses/plone && make start"

echo Plone process exited.
pause