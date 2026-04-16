::------------------------------------------------------------------------------------------
:: name: version.crud.bat
:: desc: CRUD operations for platform versioning
:: usage: version.crud <crudop> <platform-dir> <platform-name>
::        crudop: create, read, update, delete
:: example: version.crud create C:\platforms myplatform
::------------------------------------------------------------------------------------------
@echo off

set "CRUDOP=%~1"
set "PLATFORM_DIR=%~2"
set "PLATFORM_NAME=%~3"

:: Validate required params
if "%CRUDOP%"=="" (
    echo Usage: version.crud ^<crudop^> ^<platform-dir^> ^<platform-name^>
    exit /b 1
)

if "%PLATFORM_DIR%"=="" (
    echo Usage: version.crud ^<crudop^> ^<platform-dir^> ^<platform-name^>
    exit /b 1
)

if "%PLATFORM_NAME%"=="" (
    echo Usage: version.crud ^<crudop^> ^<platform-dir^> ^<platform-name^>
    exit /b 1
)

:: Handle CRUD operations
if /i "%CRUDOP%"=="create" (
    echo Creating version for %PLATFORM_NAME%
    set "VERSION_FILE=%PLATFORM_DIR%\%PLATFORM_NAME%\version.json"
    echo {"name": "%PLATFORM_NAME%", "version": "1.0.0", "created": "%DATE%"} > "%PLATFORM_DIR%\%PLATFORM_NAME%\version.json"
    echo Created: %PLATFORM_DIR%\%PLATFORM_NAME%\version.json
)

if /i "%CRUDOP%"=="read" (
    set "VERSION_FILE=%PLATFORM_DIR%\%PLATFORM_NAME%\version.json"
    if exist "%PLATFORM_DIR%\%PLATFORM_NAME%\version.json" (
        echo Version for %PLATFORM_NAME%:
        type "%PLATFORM_DIR%\%PLATFORM_NAME%\version.json"
        echo.
    ) else (
        echo No version.json found for %PLATFORM_NAME%
    )
)

if /i "%CRUDOP%"=="update" (
    set "VERSION_FILE=%PLATFORM_DIR%\%PLATFORM_NAME%\version.json"
    if exist "%PLATFORM_DIR%\%PLATFORM_NAME%\version.json" (
        echo Updating version for %PLATFORM_NAME%
        powershell -Command "$json = Get-Content '%PLATFORM_DIR%\%PLATFORM_NAME%\version.json' | ConvertFrom-Json; $v = $json.version -split '\.'; $v[2] = [int]$v[2] + 1; $json.version = $v -join '.'; $json | ConvertTo-Json -Compress | Set-Content '%PLATFORM_DIR%\%PLATFORM_NAME%\version.json'"
        echo Updated version:
        type "%PLATFORM_DIR%\%PLATFORM_NAME%\version.json"
        echo.
    ) else (
        echo No version.json found for %PLATFORM_NAME%
    )
)

if /i "%CRUDOP%"=="delete" (
    set "VERSION_FILE=%PLATFORM_DIR%\%PLATFORM_NAME%\version.json"
    if exist "%PLATFORM_DIR%\%PLATFORM_NAME%\version.json" (
        del "%PLATFORM_DIR%\%PLATFORM_NAME%\version.json"
        echo Deleted: %PLATFORM_DIR%\%PLATFORM_NAME%\version.json
    ) else (
        echo No version.json found for %PLATFORM_NAME%
    )
)

exit /b 0
