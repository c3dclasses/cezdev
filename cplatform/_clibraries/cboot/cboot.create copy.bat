:: --------------------------------------------------
:: cboot.create.bat
:: Compiles cboot Java utilities and adds to PATH
:: --------------------------------------------------
@echo off

set "CBOOT_HOME=%CD%"
cd /d "%CBOOT%"

echo [INFO] Compiling cboot Java files in %CBOOT%
javac *.java 2>nul
if not exist "CCopyFoldersCommand.class" (
    echo [WARN] Some Java files may have failed to compile
) else (
    echo [OK] Java files compiled
)

cd /d "%CBOOT_HOME%"

echo [INFO] Adding CBOOT to PATH: %CBOOT%
set PATH=%PATH%;%CBOOT%
