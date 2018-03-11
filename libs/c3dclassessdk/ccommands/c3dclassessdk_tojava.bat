::-------------------------------------------------------------------------------------------------
:: name: c3dclassessdk_tojava
:: desc: generates a maven project from c3dclassessdk project structure
:: usage: c3dclassessdk_tojava EZDEV_HOME/libs/c3dclassessdk EZDEV_HOME/meta/c3dclassessdk_java
::-------------------------------------------------------------------------------------------------

::---------------------------------------------
:: get the name of the src and dest path
::---------------------------------------------
set src=%C3DCLASSESSDK_PATH%
set dst=%EZDEV_META%/c3dclassessdk_java

::------------------------------------------
:: create destination directory
::------------------------------------------
IF NOT EXIST "%dst%" ( mkdir "%dst%" )

::-----------------------------------------------------
:: copy pom files
::-----------------------------------------------------
copy "%src%\pom.xml" "%dst%"

::-----------------------------------------------------
:: copy src/main/java files
::-----------------------------------------------------
call cpfiles "%src%" "%dst%/src/main/java" ".java" "UnitTest.java,unittest.java"

::--------------------------------------------------
:: copy src/test/java files
::--------------------------------------------------
call cpfiles "%src%" "%dst%/src/test/java" "UnitTest.java,unittest.java"

::--------------------------------------------------
:: go to the java directory
::--------------------------------------------------
cd /d %EZDEV_META%/c3dclassessdk_java

::--------------------------------------------------
:: run maven package to create the package
::--------------------------------------------------
call mvn clean install test -e -Drelease.artifactId=%C3DCLASSESSDK_NAME% -Drelease.version=%C3DCLASSESSDK_VERSION%

::--------------------------------------------------------------
:: build the files containing the paths to files in the src
::--------------------------------------------------------------
call listfiles %C3DCLASSESSDK_PATH% %EZDEV_META%/c3dclassessdk_java/c3dclasses-src.json
call listfiles %EZDEV_META%/c3dclassessdk_java %EZDEV_META%/c3dclassessdk_java/c3dclasses-meta.json