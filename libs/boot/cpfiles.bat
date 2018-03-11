::----------------------------------------------------------------------------------------------------------
:: name: cpfiles
:: usage: cpfiles <required:srcdir> <required:dstdir> <optional:formats-to-copy> <optional:formats-to-skip>
::--------------------------------------------------------------------------------------------------------
set CPFILESHOME=%CD%
cd /d %EZDEV_HOME%/libs/boot
java CCopyFoldersCommand %*
cd /d %CPFILESHOME%