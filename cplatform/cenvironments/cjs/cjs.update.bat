::-------------------------------------------------------------------------------------------------
:: name: cjs.update.bat
:: desc: Updates the JavaScript files in c3dclasses project to a webpack project structure
:: usage: cjs.update.bat
::-------------------------------------------------------------------------------------------------

@echo off

:: set the src and dst directories to write from and to
set src=%C3DCLASSES%
set dst=%C3DCLASSES_JS%

:: Ensure destination directory exists
if not exist "%dst%" mkdir "%dst%"
if not exist "%dst%\src" mkdir "%dst%\src"

:: Copy JavaScript source files to the destination
call cp-file-types-from-src-to-dst-ex "%src%" "%dst%\src" ".js" "test_,_test.js,UnitTest.js" "unit_test.js"

:: Copy test JavaScript files to tests directory
call cp-file-types-from-src-to-dst-ex "%src%" "%dst%\tests" "test_,_test.js,UnitTest.js"

:: Copy configuration files from environment config directory
set "CONFIG_SRC=%~dp0config\"

echo [ACTION] Copying webpack.config.js...
if exist "%CONFIG_SRC%webpack.config.tmp.js" (
    copy /Y "%CONFIG_SRC%webpack.config.tmp.js" "%dst%\webpack.config.js"
)

echo [ACTION] Copying package.json...
if exist "%CONFIG_SRC%package.tmp.json" (
    copy /Y "%CONFIG_SRC%package.tmp.json" "%dst%\package.json"
)

echo [ACTION] Copying index.html...
if exist "%CONFIG_SRC%index.tmp.html" (
    copy /Y "%CONFIG_SRC%index.tmp.html" "%dst%\src\index.html"
)

echo [ACTION] Copying index.js...
if exist "%CONFIG_SRC%index.tmp.js" (
    copy /Y "%CONFIG_SRC%index.tmp.js" "%dst%\src\index.js"
)

:: Generate filenames.json BEFORE running tests
call list-files %CMETADATA%\c3dclassessdk.filenames.json %src%

:: Move into the folder and install dependencies
cd /d "%dst%"

if not exist "node_modules" (
    echo [ACTION] Installing npm dependencies...
    call npm install
)

:: Build the project
echo [ACTION] Building webpack project...
call npm run build

:: List all the files in the c3dclasses folder, and c3dclasses_js folder
call list-files %CMETADATA%\c3dclasses_js.filenames.json %dst%
call list-files %CMETADATA%\c3dclasses.filenames.json %src%
