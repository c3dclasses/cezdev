::------------------------------------------------------
:: name: c3dclassessdk.bat
:: desc: initializes the c3dclassessdk framework
::------------------------------------------------------
set C3DCLASSESSDK_VERSION=1.0
set C3DCLASSESSDK_NAME=EZDEV
set C3DCLASSESSDK_PATH=%EZDEV_HOME%/libs/c3dclassessdk
set C3DCLASSESSDK_COMMANDS=%C3DCLASSESSDK_PATH%/ccommands

:: save the home dir
SET HOME=%CD%

:: set the commands on the path
set PATH=%PATH%;%C3DCLASSESSDK_COMMANDS%
cd /d %C3DCLASSESSDK_COMMANDS%
for /D %%s in (*) do (
 	call setpath %C3DCLASSESSDK_COMMANDS%/%%s
)
cd /d %HOME%

:: build the java mvn project from the c3dclassessdk project
call c3dclassessdk_tojava %C3DCLASSESSDK_PATH% %EZDEV_META%/c3dclassessdk_java

:: (Important) sets the JAVA_BIN path - required so we can run the commands - EZDEV will not function without this 
set JAVA_HOME=%EZDEV_HOME%\libs\java\jdk1.8.0_121
set JAVA_BIN=%JAVA_HOME%\bin
set MAVEN_BIN=%EZDEV_HOME%\libs\java\apache-maven-3.2.3\bin
set PATH=%PATH%;%JAVA_BIN%;%MAVEN_BIN%;%CD%;

:: go to the java directory
cd /d %EZDEV_META%/c3dclassessdk_java

:: run maven package to create the EZDEV.jar
mvn clean install test -e -Drelease.artifactId=%C3DCLASSESSDK_NAME% -Drelease.version=%C3DCLASSESSDK_VERSION%

:: go back to home dir
cd /d %HOME%