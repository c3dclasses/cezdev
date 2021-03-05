::------------------------------------------------------------------------------------------
:: name: alert
:: desc: shows an alert msg in cmdline
:: usage: alert <msg> <title> <warn>
::------------------------------------------------------------------------------------------
set ALERTHOME=%CD%
cd /d %BOOT_HOME%
java CMessageBoxCommand %*
cd /d %ALERTHOME%