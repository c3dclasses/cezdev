::------------------------------------------------------------------------------------------
:: name: cjavainit.bat
:: desc: initializes and load the java environment into memory to be used in c3dclassessdk
::------------------------------------------------------------------------------------------
echo Start initialize CJava environment.............................................
set CJAVAINITHOME=%CD%

:: set varias locations - java folder path, jar path, src path, class path
set C3DCLASSESSDK_JAVA=%EZDEV_META%/c3dclassessdk_java
set C3DCLASSESSDK_JAR=%EZDEV_HOME%/meta/c3dclassessdk_java/target/%C3DCLASSESSDK_NAME%-%C3DCLASSESSDK_VERSION%-jar-with-dependencies.jar
set C3DCLASSESSDK_SRCPATH=%EZDEV_META%/c3dclassessdk_java/src
set C3DCLASSESSDK_CLASSPATH=%EZDEV_META%/c3dclassessdk_java/target/%C3DCLASSESSDK_NAME%-%C3DCLASSESSDK_VERSION%-jar-with-dependencies.jar;.

:: if the java directory already exist dont create it
IF EXIST C3DCLASSESSDK_JAVA ( GOTO DONE_CJAVA_INIT )
:: move the commands and java files to the meta directory
call %CENVIRONMENTS_HOME%/cjava/cjavac
:DONE_CJAVA_INIT

cd /d %CJAVAINITHOME%
echo End initialize CJava environment.............................................
