::-------------------------------------------------------------------------------------------------
:: name: cpfiles.bat
:: desc: cpfiles of a given file format from a src path to a dest path
:: usage: call cpfiles.sh formattocopy srcdir dstdir formattofilter|optional
::-------------------------------------------------------------------------------------------------

:: check the arguments
if ""=="%3" (
	echo "ERROR: usage - $ . cpfiles format srcdir dstdir formattofilter-optional"
	exit 1
)

:: get the name of the src and dest path and format to move
set filterext=%~1
set srcdir=%~2
set dstdir=%~3
set filteroutext=%~4

:: check if the srcdir exist
if not exist "%srcdir%" ( 
	echo "ERROR: please provide an existing source directory"
	exit 1
)

:: check if the srcdir exist
if not exist "%dstdir%" ( 
	mkdir "%dstdir%"
)

:: copy all the files with the given extension to destination path
cd /d "%srcdir%"
copy "%filterext%" "%dstdir%"

:: remove the slashes 
set str=%dstdir%/%filteroutext%
set str=%str:/=\%
	
:: filter out the files we dont want to copy
if exist "%filteroutext%" (
	del %str%
)

:: recursively do the same above for each subdirectory
for /D %%s in (*) do call cpfiles.bat "%filterext%" "%srcdir%/%%s" "%dstdir%/%%s" "%filteroutext%"