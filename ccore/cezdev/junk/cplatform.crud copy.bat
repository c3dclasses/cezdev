:: --------------------------------------------------
:: cplatform.crud.bat
:: Runs CRUD operation scripts for platforms
:: Usage: cplatform.crud <crudop> <platform-dir> [platform]
:: Example: cplatform.crud create C:\platforms aws
:: --------------------------------------------------

@echo off

set "CRUDOP=%~1"
set "PLATFORM_DIR=%~2"
set "PLATFORM=%~3"

:: Validate required params
if "%CRUDOP%"=="" (
    echo Usage: cplatform.crud ^<crudop^> ^<platform-dir^> [platform]
    exit /b 1
)

if "%PLATFORM_DIR%"=="" (
    echo Usage: cplatform.crud ^<crudop^> ^<platform-dir^> [platform]
    exit /b 1
)

:: --------------------------------------------------
:: Run all platforms if no platform specified
:: --------------------------------------------------
if "%PLATFORM%"=="" (
    echo [CALLING] %~nx0
    echo [INFO] Operation: %CRUDOP%
    echo [INFO] Platform directory: %PLATFORM_DIR%
    
    :: Then run each individual platform
    for /d %%P in ("%PLATFORM_DIR%\*") do (
        echo [CREATING] %%~nP
        call "%~f0" "%CRUDOP%" "%PLATFORM_DIR%" "%%~nP"
    )
    echo [ENDING] %~nx0
    exit /b 0
)


:: --------------------------------------------------
:: Run a single platform
:: --------------------------------------------------
set "SCRIPT=%PLATFORM_DIR%\%PLATFORM%\%PLATFORM%.%CRUDOP%.bat"

::for %%D in ("%PLATFORM_DIR%") do set "PLATFORM_DIR_NAME=%%~nxD"
::REM For create operations
::if /i "%CRUDOP%"=="create" (
::    set "DIR_SCRIPT=%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat"
::    if exist "%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat" (
::        echo [CALLING] %PLATFORM_DIR_NAME%.%CRUDOP%.bat...
::        call "%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat" "%PLATFORM_DIR%" "%PLATFORM%"
::    )
::)

::REM For delete operations
::if /i "%CRUDOP%"=="delete" (
::    set "DIR_SCRIPT=%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat"
::    if exist "%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat" (
::        echo [CALLING] %PLATFORM_DIR_NAME%.%CRUDOP%.bat...
::        call "%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat" "%PLATFORM_DIR%" "%PLATFORM%"
::    )
::)

::REM For update operations
::if /i "%CRUDOP%"=="update" (
::    set "DIR_SCRIPT=%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat"
::    if exist "%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat" (
::        echo [CALLING] %PLATFORM_DIR_NAME%.%CRUDOP%.bat...
::        call "%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat" "%PLATFORM_DIR%" "%PLATFORM%"
::    )
::)

::REM For read operations, display platform information
::if /i "%CRUDOP%"=="read" (
::    set "DIR_SCRIPT=%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat"
::    if exist "%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat" (
::        echo [CALLING] %PLATFORM_DIR_NAME%.%CRUDOP%.bat...
::        call "%PLATFORM_DIR%\%PLATFORM_DIR_NAME%.%CRUDOP%.bat" "%PLATFORM_DIR%" "%PLATFORM%"
::    )
::    
::    set "PLATFORMPATH=%PLATFORM_DIR%\%PLATFORM%" 
::    REM Read description from description.txt if exists
::    set "DESCFILE=%PLATFORM_DIR%\%PLATFORM%\description.txt"
::    if exist "%PLATFORM_DIR%\%PLATFORM%\description.txt" (
::        set /p DESC=<"%PLATFORM_DIR%\%PLATFORM%\description.txt"
::    ) else (
::        echo description: ^(none^)
::    )
::    
::    call echo path: %%PLATFORM_DIR%%\%%PLATFORM%%
::    
::    REM List bat files
::    echo bat files:
::    set "BATFOUND=0"
::    for %%B in ("%PLATFORM_DIR%\%PLATFORM%\*.bat") do (
::        echo - %%~nxB
::        set "BATFOUND=1"
::    )
::    if "%BATFOUND%"=="0" echo ^(none^)
::    echo --------------------------------------------------
::)

if exist "%SCRIPT%" (
    call "%SCRIPT%" "%PLATFORM_DIR%" "%PLATFORM%" || exit /b 1
) 

exit /b 0