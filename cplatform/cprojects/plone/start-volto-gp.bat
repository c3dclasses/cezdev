@echo off
echo Starting GP Volto inside WSL...

REM Enter WSL Ubuntu, cd to the directory, and run make start.
start wsl -d Ubuntu bash -ic "cd /home/c3dclasses/GeoPlatform/chs/apps/plone-geoplatform-addon && make start"

echo GP Volto process exited.
pause