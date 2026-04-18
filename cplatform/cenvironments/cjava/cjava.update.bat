::-------------------------------------------------------------------------------------------------
:: name: cjava.update.bat
:: desc: updates the java files in c3dclasses project to a maven project
:: usage: cjava.update.bat
::-------------------------------------------------------------------------------------------------

@echo off

echo [CALLING] %~nx0

::------------------------------------------------------
:: Validate required environment variables
::------------------------------------------------------
if "%C3DCLASSES%"=="" (
    echo [ERROR] C3DCLASSES environment variable is not set.
    exit /b 1
)

if "%C3DCLASSES_JAVA%"=="" (
    set "C3DCLASSES_JAVA=%CMETADATA%\c3dclasses_java"
)

:: Save current directory
set "CJAVAUPDATEHOME=%CD%"

:: set the src and dst directories to write from and to
set "src=%C3DCLASSES%"
set "dst=%C3DCLASSES_JAVA%"

echo [INFO] Source (C3DCLASSES): %src%
echo [INFO] Destination (C3DCLASSES_JAVA): %dst%

:: Ensure destination directory exists
if not exist "%dst%" mkdir "%dst%"

:: move the pom file, java src, test files to the destination
echo [COPYING] Java source files...
call directory.copy.bat "%src%" "%dst%\src\main\java" ".java" "UnitTest.java,unittest.java" "CUnitTest.java,CMockUnitTest.java"
echo [COPYING] Java test files...
call directory.copy.bat "%src%" "%dst%\src\test\java" ".java" "" "UnitTest.java,unittest.java"

:: Copy pom.xml - use script directory path
set "SCRIPT_DIR=%~dp0"
set "POM_SRC=%SCRIPT_DIR%pom.xml"
echo [COPYING] pom.xml from: %POM_SRC%
echo [COPYING] pom.xml to: %dst%\pom.xml
copy /Y "%POM_SRC%" "%dst%\pom.xml"

:: Verify pom.xml was copied
if not exist "%dst%\pom.xml" (
    echo [ERROR] Failed to copy pom.xml to %dst%
    exit /b 1
)

:: Generate filenames.json BEFORE running Maven tests (required by __.dir_path())
call path.list.bat "%CMETADATA%\c3dclassessdk.filenames.json" "%src%"

:: move into the folder and run maven install
cd /d "%dst%"
call mvn clean install test -e -Drelease.artifactId=%C3DCLASSES_NAME% -Drelease.version=%C3DCLASSES_VERSION% -Drelease.path=%CEZDEV_HOME%

:: list all the files in the c3dclasses folder, and c3dclasses_java folder
:: list all the filenames in c3dclasses in a json file
call path.list.bat "%CMETADATA%\c3dclasses_java.filenames.json" "%dst%"
call path.list.bat "%CMETADATA%\c3dclasses.filenames.json" "%src%"

:: Restore original directory
cd /d "%CJAVAUPDATEHOME%"

echo [ENDING] %~nx0
