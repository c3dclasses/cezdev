::------------------------------------------------------
:: name: libs.bat
:: desc: initializes all the libs used by EZDEV
::------------------------------------------------------

::-----------------------------------------------------
:: initialize EZDEV standard libs needed to run ezdev
::-----------------------------------------------------
call %EZDEV_STDLIBS%/_libs

::------------------------------------------------
:: initialize all the other libs
::------------------------------------------------

:: python library
set PYTHON_COMMANDS=%EZDEV_LIBS%/python/python-3.8.2-embed-amd64
set PATH=%PATH%;%PYTHON_COMMANDS%
set PYTHON_COMMANDS_SCRIPTS=%PYTHON_COMMANDS%/Scripts
set PATH=%PATH%;%PYTHON_COMMANDS_SCRIPTS%
set PATH=%PATH%;C:/Users/gpad/AppData/Local/Packages/PythonSoftwareFoundation.Python.3.8_qbz5n2kfra8p0/LocalCache/local-packages/Python38/Scripts

:: r / rstudio library
set R_COMMANDS=%EZDEV_LIBS%/R/R-3.5.0/bin
set PATH=%PATH%;%R_COMMANDS%
set R_STUDIO_COMMANDS=%EZDEV_LIBS%/R/RStudio-1.2.1335/bin
set PATH=%PATH%;%R_STUDIO_COMMANDS%

:: latex
set LATEX_COMMANDS=%EZDEV_LIBS%/latex/miktex/miktex/bin/x64
set PATH=%PATH%;%LATEX_COMMANDS%

:: octave library
set OCTAVE_COMMANDS=%EZDEV_LIBS%/octave-4.0.0/bin
set PATH=%PATH%;%OCTAVE_COMMANDS%

:: node library
:: set PATH=%PATH%;C:/Users/Teacher/AppData/Roaming/npm
:: set PATH=%PATH%;C:/Users/Teacher/AppData/Roaming/npm/node_modules/http-server/bin/http-server
set PATH=%PATH%;%EZDEV_LIBS%/node/node-v10.15.0-win-x64
:: set up js environment
:: alert "init the js environment"
:: call cjsinit

:: cygwin library
set CYGWIN_HOME=%EZDEV_LIBS%/cygwin/cygwin-b20
set PATH=%PATH%;%CYGWIN_HOME%

:: gephi library
set GEPHI_HOME=%EZDEV_LIBS%/gephi
set PATH=%PATH%;%GEPHI_HOME%

:: youtube-dl
set PATH=%PATH%;%EZDEV_LIBS%/youtube-dl

:: vscode
set PATH=%PATH%;%EZDEV_LIBS%/vscode/VSCode-win32-x64-1.35.1/bin

:: git
set PATH=%PATH%;%EZDEV_LIBS%/git/PortableGit/bin
set PATH=%PATH%;%EZDEV_LIBS%/git/TortoiseGit/bin

:: filezilla
set PATH=%PATH%;"%EZDEV_LIBS%/filezilla/FileZilla FTP Client"

:: spring
set PATH=%PATH%;"%EZDEV_LIBS%\spring\spring-2.2.0.RELEASE\bin"

:: eclipse
set PATH=%PATH%;"%EZDEV_LIBS%\eclipse\eclipse-jee-2019-09-R-win32-x86_64\eclipse"

:: mingw - gcc compiler
set PATH=%PATH%;"%EZDEV_LIBS%\mingw\bin"

:: elasticsearch 
set ELASTICSEARCH_HOME="%EZDEV_LIBS%\elasticsearch\elasticsearch-7.10.0"
set PATH=%PATH%;%ELASTICSEARCH_HOME%\bin

:: kibana
set KIBANA_HOME="%EZDEV_LIBS%\kibana\kibana-7.10.0-windows-x86_64"
set PATH=%PATH%;%KIBANA_HOME%\bin

:: logstash
set LOGSTASH_HOME="%EZDEV_LIBS%\logstash\logstash-7.8.0"
set PATH=%PATH%;%LOGSTASH_HOME%\bin

:: hadoop
set HADOOP_HOME="%EZDEV_LIBS%\hadoop\hadoop-3.2.1"
set PATH=%PATH%;"%HADOOP_HOME%\bin"
set PATH=%PATH%;"%HADOOP_HOME%\sbin"

:: sparky
set SPARK_HOME=%EZDEV_LIBS%/spark/spark-3.0.1-bin-hadoop2.7
set PATH=%PATH%;"%SPARK_HOME%/bin"

:: airflow
set AIRFLOW_HOME=%EZDEV_LIBS%/airflow
set PATH=%PATH%;"%AIRFLOW_HOME%/bin"

:: postgresql
set POSTGRESQL_HOME=%EZDEV_LIBS%/postgresql/postgresql-13.1-1-windows-x64-binaries/pgsql
set PATH=%PATH%;%POSTGRESQL_HOME%/bin
set PATH=%PATH%;"%POSTGRESQL_HOME%/pgAdmin 4/bin"
set PATH=%PATH%;%EZDEV_LIBS%/postgresql/bin
