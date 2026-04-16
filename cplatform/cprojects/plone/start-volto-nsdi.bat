@echo off
echo Starting NSDI Volto inside WSL...

REM Enter WSL Ubuntu, cd to the directory, and run pnpm start for NSDI.
start wsl -d Ubuntu bash -ic "cd /home/c3dclasses/GeoPlatform/chs/apps/nsdi-volto-frontend && pnpm run start:nsdi"

echo NSDI Volto process exited.
pause