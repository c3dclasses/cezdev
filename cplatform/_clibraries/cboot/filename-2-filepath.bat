::------------------------------------------------------------------------------------------
:: name: filename-2-filepath.bat
:: desc: 
::------------------------------------------------------------------------------------------
@echo off
set FILENAME2FILEPATHHOME=%CD%
cd /d %CBOOT%
set "FILENAME=%~1"
set "MAPFILE=%CMETADATA%\c3dclassessdk.filenames.json"
python CFilename2FilePath.py "%FILENAME%" "%MAPFILE%"
cd /d %FILENAME2FILEPATHHOME%