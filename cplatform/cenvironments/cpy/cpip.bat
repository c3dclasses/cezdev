::------------------------------------------------------------------------------------------
:: name: cpip.bat
:: desc: Runs pip commands for Python package management
:: usage: cpip install <module-name>
::------------------------------------------------------------------------------------------

@echo off
setlocal

set "CPIPHOME=%CD%"

::------------------------------------------------------
:: If no arguments, show usage
::------------------------------------------------------
if "%~1"=="" (
    echo [INFO] Usage: cpip install ^<module-name^>
    echo [INFO] Example: cpip install requests
    goto DONE
)

::------------------------------------------------------
:: Run pip with provided arguments
::------------------------------------------------------
echo [ACTION] Running: python -m pip %*
call python -m pip %*

:DONE
cd /d "%CPIPHOME%"
endlocal
