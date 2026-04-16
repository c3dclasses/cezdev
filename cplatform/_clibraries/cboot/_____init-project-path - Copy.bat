::------------------------------------------------------------------------------------------
:: name: init-project-path.bat
:: desc: prepares and registers a project path
::------------------------------------------------------------------------------------------

@echo off

echo.
echo ==================================================
echo   CEZDEV Project Path Setup
echo ==================================================
echo.

::------------------------------------------------------
:: Validate argument
::------------------------------------------------------
if "%~1"=="" (
    echo [ERROR] Project name not provided.
    echo.
    echo Usage:
    echo   %~n0.bat ^<project-name^>
    echo.
    exit /b 1
)

set "PROJECTNAME=%~1"

echo [STEP] Initializing project path...
echo [INFO] Project name:
echo        %PROJECTNAME%
echo.

::------------------------------------------------------
:: Set project metadata path
::------------------------------------------------------
set "CEZDEV_PROJECT=%CMETADATA%\cprojects\%PROJECTNAME%"

echo [INFO] Project directory:
echo        %CEZDEV_PROJECT%
echo.

::------------------------------------------------------
:: Copy project command files
::------------------------------------------------------
echo [ACTION] Copying project command files...

call cp-file-types-from-src-to-dst.bat "%CPROJECTS%\%PROJECTNAME%" "%CEZDEV_PROJECT%"

if errorlevel 1 (
    echo [ERROR] Failed to copy project command files.
    echo.
    exit /b 1
)

echo [OK] Project command files copied.
echo.

::------------------------------------------------------
:: Add project path to PATH
::------------------------------------------------------
echo [STEP] Registering project in PATH...

call set "PATH=%PATH%;%CEZDEV_PROJECT%"

echo [OK] PATH updated.
echo.

echo ==================================================
echo   Project path initialization complete
echo ==================================================
echo.