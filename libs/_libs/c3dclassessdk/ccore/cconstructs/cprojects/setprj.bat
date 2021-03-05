::------------------------------------------------------------------------------------------
:: name: cprj.bat
:: desc: creates and sets up a project to be used in c3dclassessdk framework
::------------------------------------------------------------------------------------------
set CPRJHOME=%CD%
call cjava c3dclasses.CProjectsCommand %EZDEV_META% %*
call "%EZDEV_META%\%1.prj.bat"
cd /d %CPRJHOME%
