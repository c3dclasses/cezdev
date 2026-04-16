::------------------------------------------------------------------------------------------
:: name: cpy.bat
:: desc: Runs a Python program with path resolution support
:: usage: cpy myscript.py arg1 arg2 ...
::------------------------------------------------------------------------------------------

@echo off
setlocal

set "CPYHOME=%CD%"

::------------------------------------------------------
:: If no arguments, run update procedure
::------------------------------------------------------
if "%~1"=="" (
    echo [ACTION] No script provided, running cpy.update.bat...
    call "%~dp0cpy.update.bat"
    goto DONE
)

::------------------------------------------------------
:: Try to resolve file via filename-2-filepath
::------------------------------------------------------
call setvarx FILE_2_RUN filename-2-filepath "%~1"
call getvar FILE_2_RUN

::------------------------------------------------------
:: If no mapping found, run python directly
::------------------------------------------------------
if "%FILE_2_RUN%"=="" (
    echo [ACTION] Running: python %*
    call python %*
    goto DONE
)

::------------------------------------------------------
:: If mapping found, run resolved path
::------------------------------------------------------
echo [ACTION] Running: python "%FILE_2_RUN%" %~2 %~3 %~4 %~5 %~6 %~7 %~8 %~9
call python "%FILE_2_RUN%" %~2 %~3 %~4 %~5 %~6 %~7 %~8 %~9

:DONE
cd /d "%CPYHOME%"
endlocal
