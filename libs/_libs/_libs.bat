::----------------------------------------------------------------------------
:: name: _libs.bat
:: desc: initializes the libraries required to boot EZDEV and C3DClassesSDK
:: note: these values must be hard coded 
::----------------------------------------------------------------------------
@echo on
echo Initializing Boot Libriaries.................................................

:: java library
set JAVA_HOME=%EZDEV_STDLIBS%/java/jdk1.8.0_121
set JAVA_BIN=%JAVA_HOME%/bin

:: maven library
set MAVEN_BIN=%EZDEV_STDLIBS%/java/apache-maven-3.2.3/bin
set PATH=%PATH%;%JAVA_BIN%;%MAVEN_BIN%;%CD%;

:: boot commands
set BOOT_HOME=%EZDEV_STDLIBS%/boot
javac %BOOT_HOME%/*.java
set PATH=%PATH%;%BOOT_HOME%

:: c3dclassessdk library
set C3DCLASSESSDK_HOME=%EZDEV_STDLIBS%/c3dclassessdk
set CENVIRONMENTS_HOME=%C3DCLASSESSDK_HOME%/cenvironments
call %CENVIRONMENTS_HOME%/cenvironmentsinit

echo End Initializing Boot Libriaries.................................................
