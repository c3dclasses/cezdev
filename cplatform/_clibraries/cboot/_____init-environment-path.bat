::------------------------------------------------------------------------------------------
:: name: init-environment-path.bat
:: desc: prepares and registers an environment path
::------------------------------------------------------------------------------------------

@echo off

echo.
echo ::---------------------------------------------------------------------------------------
echo ::  CEZDEV Environment Path Setup
echo ::---------------------------------------------------------------------------------------
echo.

::------------------------------------------------------
:: Validate argument
::------------------------------------------------------
if "%~1"=="" (
    echo [ERROR] Environment name not provided.
    echo.
    echo Usage:
    echo   %~n0.bat ^<environment-name^>
    echo.
    exit /b 1
)

set "ENVNAME=%~1"

echo [STEP] Initializing environment path...
echo [INFO] Environment name:
echo        %ENVNAME%
echo.

::------------------------------------------------------
:: Set environment metadata path
::------------------------------------------------------
set "CENVIRONMENT=%CMETADATA%\cenvironments\%ENVNAME%"

echo [INFO] Environment directory:
echo        %CENVIRONMENT%
echo.

::------------------------------------------------------
:: Copy environment command files
::------------------------------------------------------
echo [ACTION] Copying environment command files...

call cp-file-types-from-src-to-dst.bat "%CENVIRONMENTS%\%ENVNAME%" "%CENVIRONMENT%"

if errorlevel 1 (
    echo [ERROR] Failed to copy environment command files.
    echo.
    exit /b 1
)

echo [OK] Environment command files copied.
echo.

::------------------------------------------------------
:: Add environment path to PATH
::------------------------------------------------------
echo [STEP] Registering environment in PATH...

call set "PATH=%PATH%;%CENVIRONMENT%"

echo [OK] PATH updated.
echo.

echo ::---------------------------------------------------------------------------------------
echo ::  Environment path initialization complete
echo ::---------------------------------------------------------------------------------------
echo.