::------------------------------------------------------------------------------------------
:: name: cjava.modified.bat
:: desc: Checks if the Java source has been modified after the last build
:: usage: cjava.modified.bat [ReferenceFile]
::        If no reference file provided, uses the JAR file as reference
::------------------------------------------------------------------------------------------

@echo off
setlocal enabledelayedexpansion

echo [CALLING] %~nx0

::------------------------------------------------------
:: Define C3DClasses SDK metadata
::------------------------------------------------------
if "%C3DCLASSES_NAME%"=="" set "C3DCLASSES_NAME=c3dclassessdk"
if "%C3DCLASSES_VERSION%"=="" set "C3DCLASSES_VERSION=1.0"

::------------------------------------------------------
:: Define Java environment paths
::------------------------------------------------------
set "C3DCLASSES_JAVA=%CMETADATA%\c3dclasses_java"
set "C3DCLASSES_JAR=%C3DCLASSES_JAVA%\target\%C3DCLASSES_NAME%-%C3DCLASSES_VERSION%-jar-with-dependencies.jar"

::------------------------------------------------------
:: Set reference file (JAR by default, or user-provided)
::------------------------------------------------------
if "%~1"=="" (
    set "REFFILE=%C3DCLASSES_JAR%"
) else (
    set "REFFILE=%~1"
)

::------------------------------------------------------
:: Validate required variables
::------------------------------------------------------
if "%C3DCLASSES%"=="" (
    echo [ERROR] C3DCLASSES environment variable is not set.
    endlocal
    exit /b 1
)

if "%CMETADATA%"=="" (
    echo [ERROR] CMETADATA environment variable is not set.
    endlocal
    exit /b 1
)

::------------------------------------------------------
:: Check if source directory exists
::------------------------------------------------------
echo [STEP] Checking source directory: %C3DCLASSES%

if not exist "%C3DCLASSES%\" (
    echo [ERROR] Source directory does NOT exist.
    endlocal
    exit /b 1
)

::------------------------------------------------------
:: Check if reference file exists
::------------------------------------------------------
echo [STEP] Checking reference file: %REFFILE%

if not exist "%REFFILE%" (
    echo [INFO] Reference file does NOT exist (no previous build).
    echo [OK] Java environment needs to be built.
    endlocal
    exit /b 0
)

::------------------------------------------------------
:: Get reference file's last modified timestamp
::------------------------------------------------------
echo [ACTION] Comparing timestamps...

for %%F in ("%REFFILE%") do set "REFDATE=%%~tF"

:: Assume not modified
set "MODIFIED=0"

:: Loop through Java files in the source directory
for /r "%C3DCLASSES%" %%F in (*.java) do (
    set "FILEDATE=%%~tF"
    if "!FILEDATE!" GTR "!REFDATE!" (
        set "MODIFIED=1"
        set "MODIFIED_FILE=%%F"
        goto :done
    )
)

:done
if "%MODIFIED%"=="1" (
    echo [OK] Java source modified. File: %MODIFIED_FILE%
    echo [ENDING] %~nx0
    endlocal
    exit /b 0
) else (
    echo [INFO] Java source has NOT been modified after last build.
    echo [ENDING] %~nx0
    endlocal
    exit /b 1
)
