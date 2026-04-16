::------------------------------------------------------------------------------------------
:: name: files.copy.bat
:: desc: Copy files of a specific type from src to dst directory
:: usage: files.copy <extension> <src> <dst>
:: example: files.copy bat C:\source C:\dest
::------------------------------------------------------------------------------------------
@echo off

set "EXT=%~1"
set "SRC=%~2"
set "DST=%~3"

if "%EXT%"=="" (
    echo Usage: files.copy ^<extension^> ^<src^> ^<dst^>
    exit /b 1
)

if "%SRC%"=="" (
    echo Usage: files.copy ^<extension^> ^<src^> ^<dst^>
    exit /b 1
)

if "%DST%"=="" (
    echo Usage: files.copy ^<extension^> ^<src^> ^<dst^>
    exit /b 1
)

call cp-file-types-from-src-to-dst.bat "%SRC%" "%DST%" "*.%EXT%"
