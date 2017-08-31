::-------------------------------------------------------------------------------------------------
:: name: c3dclassessdk_tojava.bat
:: desc: generates a maven project from c3dclassessdk project structure
:: usage: c3dclassessdk_tojava.sh EZDEV_HOME/bin/c3dclassessdk EZDEV_HOME/bin/c3dclassessdk_java
::-------------------------------------------------------------------------------------------------

:: save the home dir
SET HOME=%CD%

:: check the arguments
if ""=="%2" (
	echo "ERROR: usage - c3dclassessdk_tojava.bat <srcpath> <dstpath>"
	cd /d %HOME%
	exit 1
)

:: get the name of the src and dest path
set srcpath=%~1
set dstpath=%~2

:: check if the source directory exist
if not exist "%srcpath%" ( 
	echo "ERROR: please provide an existing source directory."
	cd /d %HOME%
	exit 1
)

:: check if the destination directory exist if not create it
if not exist "%dstpath%" ( 
	mkdir "%dstpath%"
)

::-----------------------------------------------------
:: copy pom files
::-----------------------------------------------------
copy "%srcpath%\pom.xml" "%dstpath%"

::-----------------------------------------------------
:: copy src/main/java files
::-----------------------------------------------------
if not exist "%dstpath%/src" ( 
	mkdir "%dstpath%/src"
)
if not exist "%dstpath%/src/main" ( 
	mkdir "%dstpath%/src/main"
)
if not exist "%dstpath%/src/main/java" ( 
	mkdir "%dstpath%/src/main/java"
)
:: move all java file except unittest files from source location to dest
call cpfiles "*.java" "%srcpath%" "%dstpath%\src\main\java" "*UnitTest.java"

::--------------------------------------------------
:: copy src/test/java files
::--------------------------------------------------
if not exist "%dstpath%/src" ( 
	mkdir "%dstpath%/src"
)
if not exist "%dstpath%/src/main" ( 
	mkdir "%dstpath%/src/test"
)
if not exist "%dstpath%/src/main/java" ( 
	mkdir "%dstpath%/src/test/java"
)
:: move all java files from source location to dest
call cpfiles "*UnitTest.java" "%srcpath%" "%dstpath%/src/test/java"

:: go back to home dir
cd /d %HOME%