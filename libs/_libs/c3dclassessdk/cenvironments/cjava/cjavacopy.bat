::-------------------------------------------------------------------------------------------------
:: name: cjavacopy.bat
:: desc: copies the java files in c3dclassessdk project to a maven project
:: usage: cjavacopy c3dclassessdk c3dclassessdk_java
::-------------------------------------------------------------------------------------------------
 
:: set the src and dst directories to write from and to
set src=%C3DCLASSESSDK_PATH%
set dst=%C3DCLASSESSDK_JAVA%

:: move the pom file, java src, test files to the destination
call cp-files "%src%" "%dst%/src/main/java" ".java" "UnitTest.java,unittest.java" "CUnitTest.java,CMockUnitTest.java"
call cp-files "%src%" "%dst%/src/test/java" "UnitTest.java,unittest.java"
copy "%src%\cenvironments\cjava\pom.xml" "%dst%"

:: move into the folder and run maven install
cd /d %C3DCLASSESSDK_JAVA%
call mvn clean install test -e -Drelease.artifactId=%C3DCLASSESSDK_NAME% -Drelease.version=%C3DCLASSESSDK_VERSION% -Drelease.path=%EZDEV_HOME%

:: list all the files in the c3dclassessdk folder, and c3dclassessdk_java folder
:: list all the filenames in c3dclassessdk in a json file
call list-files %EZDEV_META%/c3dclassessdk_java.filenames.json %EZDEV_META%/c3dclassessdk_java 
call list-files %EZDEV_META%/c3dclassessdk.filenames.json %C3DCLASSESSDK_PATH%
