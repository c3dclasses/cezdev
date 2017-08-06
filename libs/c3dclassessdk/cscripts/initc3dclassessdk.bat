::------------------------------------------------------
:: name: initc3dclassessdk.bat
:: desc: initializes the c3dclassessdk framework
::------------------------------------------------------

set CMEMORY_CONFIG=%EZDEV_HOME%/libs/c3dclassessdk/cscripts/cmemory/cjsonmemoryconfig.json

:: save the home dir
SET HOME=%CD%

:: create the meta directory if it doesn't exist
IF NOT EXIST %EZDEV_META% ( 
	mkdir %EZDEV_META% 
)

:: set the PATH variable
set PATH=%PATH%;%C3DCLASSESSDK_PATH%\cscripts
set PATH=%PATH%;%C3DCLASSESSDK_PATH%\cscripts\cmemory


:: build the java mvn project from the c3dclassessdk project
call c3dclassessdk_tojava.bat %C3DCLASSESSDK_PATH% %EZDEV_META%\c3dclassessdk_java

:: (Important) sets the JAVA_BIN path - required so we can run the commands - EZDEV will not function without this 
set JAVA_HOME=%EZDEV_HOME%\libs\java\jdk1.8.0_121
set JAVA_BIN=%JAVA_HOME%\bin
set MAVEN_BIN=%EZDEV_HOME%\libs\java\apache-maven-3.2.3\bin
set PATH=%PATH%;%JAVA_BIN%;%MAVEN_BIN%;%CD%;

:: go to the java directory
cd /d %EZDEV_HOME%/meta/c3dclassessdk_java

:: run maven package to create the EZDEV.jar
mvn clean install test -e -Drelease.artifactId=%C3DCLASSESSDK_NAME% -Drelease.version=%C3DCLASSESSDK_VERSION%

:: go back to home dir
cd %HOME%