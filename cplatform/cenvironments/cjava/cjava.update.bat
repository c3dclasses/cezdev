::-------------------------------------------------------------------------------------------------
:: name: cjava.update.bat
:: desc: updates the java files in c3dclasses project to a maven project
:: usage: cjava.update.bat c3dclasses c3dclasses_java
::-------------------------------------------------------------------------------------------------

@echo off

:: set the src and dst directories to write from and to
set src=%C3DCLASSES%
set dst=%C3DCLASSES_JAVA%

:: Ensure destination directory exists
if not exist "%dst%" mkdir "%dst%"

:: move the pom file, java src, test files to the destination
call cp-file-types-from-src-to-dst-ex "%src%" "%dst%\src\main\java" ".java" "UnitTest.java,unittest.java" "CUnitTest.java,CMockUnitTest.java"
call cp-file-types-from-src-to-dst-ex "%src%" "%dst%\src\test\java" "UnitTest.java,unittest.java"

:: Copy test resource files (.dat, .json, .csv) to test resources directory
::call cp-file-types-from-src-to-dst-ex "%src%" "%dst%\src\test\resources" ".dat"
::call cp-file-types-from-src-to-dst-ex "%src%" "%dst%\src\test\resources" ".json"
::call cp-file-types-from-src-to-dst-ex "%src%" "%dst%\src\test\resources" ".csv"

:: Copy pom.xml - use CENVIRONMENTS path
set "POM_SRC=%CENVIRONMENTS%\cjava\bin\pom.xml"
echo [ACTION] Copying pom.xml from: %POM_SRC%
echo [ACTION] Copying pom.xml to: %dst%\pom.xml
copy /Y "%POM_SRC%" "%dst%\pom.xml"

:: Verify pom.xml was copied
if not exist "%dst%\pom.xml" (
    echo [ERROR] Failed to copy pom.xml to %dst%
    exit /b 1
)

:: Generate filenames.json BEFORE running Maven tests (required by __.dir_path())
call list-files %CMETADATA%\c3dclassessdk.filenames.json %src%

:: move into the folder and run maven install
cd /d "%dst%"
call mvn clean install test -e -Drelease.artifactId=%C3DCLASSES_NAME% -Drelease.version=%C3DCLASSES_VERSION% -Drelease.path=%CEZDEV_HOME%

:: list all the files in the c3dclasses folder, and c3dclasses_java folder
:: list all the filenames in c3dclasses in a json file
call list-files %CMETADATA%\c3dclasses_java.filenames.json %dst% 
call list-files %CMETADATA%\c3dclasses.filenames.json %src%
