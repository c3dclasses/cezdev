::---------------------------------------------------------------------------------------------------
:: name: cjavacopy.bat
:: desc: Compiles the C3DClasses SDK Java project using Maven
::---------------------------------------------------------------------------------------------------

@echo off

echo.
echo [STEP] Compiling Java project with Maven...
echo.

set "CJAVACOPY_HOME=%CD%"

::------------------------------------------------------
:: Navigate to Java project directory
::------------------------------------------------------
if "%C3DCLASSES_JAVA%"=="" (
    echo [ERROR] C3DCLASSES_JAVA environment variable is not set.
    exit /b 1
)

if not exist "%C3DCLASSES_JAVA%" (
    echo [ACTION] Creating Java project directory...
    mkdir "%C3DCLASSES_JAVA%"
    if errorlevel 1 (
        echo [ERROR] Failed to create directory:
        echo        %C3DCLASSES_JAVA%
        exit /b 1
    )
    echo [OK] Directory created.
)

cd /d "%C3DCLASSES_JAVA%"

::------------------------------------------------------
:: Copy pom.xml if not present
::------------------------------------------------------
if not exist "pom.xml" (
    echo [ACTION] Copying pom.xml to project directory...
    copy "%~dp0pom.xml" "pom.xml"
)

::------------------------------------------------------
:: Run Maven package
::------------------------------------------------------
echo [ACTION] Running Maven package...
call mvn clean package -DskipTests

if errorlevel 1 (
    echo [ERROR] Maven build failed.
    cd /d "%CJAVACOPY_HOME%"
    exit /b 1
)

echo [OK] Maven build completed successfully.

cd /d "%CJAVACOPY_HOME%"
exit /b 0
