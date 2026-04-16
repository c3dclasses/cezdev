::------------------------------------------------------------------------------------------
:: name: files.copyx.bat
:: desc: Copy files of a specific type from src to dst directory (extended version)
:: usage: files.copyx <extension> <src> <dst>
:: example: files.copyx bat C:\source C:\dest
::------------------------------------------------------------------------------------------
@echo off

set "EXT=%~1"
set "SRC=%~2"
set "DST=%~3"

if "%EXT%"=="" (
    echo Usage: files.copyx ^<extension^> ^<src^> ^<dst^>
    exit /b 1
)

if "%SRC%"=="" (
    echo Usage: files.copyx ^<extension^> ^<src^> ^<dst^>
    exit /b 1
)

if "%DST%"=="" (
    echo Usage: files.copyx ^<extension^> ^<src^> ^<dst^>
    exit /b 1
)

call cp-file-types-from-src-to-dst-ex.bat "%SRC%" "%DST%" "%EXT%"
