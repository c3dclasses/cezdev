::----------------------------------------------------------------------------------------------------------
:: name: cp-files
:: desc: copies the folder structure and selected files from src dir to dst dir
:: usage: cp-files <required:srcdir> <required:dstdir> <optional:formats-to-copy> <optional:formats-to-skip>
::--------------------------------------------------------------------------------------------------------
set CPFILESHOME=%CD%
cd /d %BOOT_HOME%
java CCopyFoldersCommand %*
cd /d %CPFILESHOME%