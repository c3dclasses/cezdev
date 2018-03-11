::------------------------------------------------------------------------------------------
:: name: alert
:: desc: 
:: usage: alert <msg> <title> <warn>
::------------------------------------------------------------------------------------------
set ALERTHOME=%CD%
cd /d %EZDEV_HOME%/libs/boot
java CMessageBoxCommand %*
cd /d %ALERTHOME%