::------------------------------------------------------------------------------------------
:: name: cjava.modified.bat
:: desc: Callback script triggered when Java environment files are modified
:: params: %1=modified_type %2=filepath %3=platform %4=platform_name
::------------------------------------------------------------------------------------------

@echo off
setlocal enabledelayedexpansion

echo [CALLING] %~nx0
echo [PARAM] modified_type: %~1
echo [PARAM] filepath: %~2
echo [PARAM] platform: %~3
echo [PARAM] platform_name: %~4

set "FILEPATH=%~2"

:: Check if filepath contains c3dclasses AND (.java OR pom.xml)
set "HAS_C3D="
set "HAS_JAVA="
set "HAS_POM="

if not "!FILEPATH:c3dclasses=!"=="!FILEPATH!" set "HAS_C3D=1"
if not "!FILEPATH:.java=!"=="!FILEPATH!" set "HAS_JAVA=1"
if not "!FILEPATH:pom.xml=!"=="!FILEPATH!" set "HAS_POM=1"

if defined HAS_C3D (
    if defined HAS_JAVA (
        echo [INFO] Java file changed in c3dclasses, updating...
        call "%~dp0cjava.update.bat"
        goto :end
    )
    if defined HAS_POM (
        echo [INFO] pom.xml changed in c3dclasses, updating...
        call "%~dp0cjava.update.bat"
        goto :end
    )
)

:end
echo [ENDING] %~nx0
endlocal

