::------------------------------------------------------------------------------------------
:: name: cplatform.modified.bat
:: desc: Dispatches file modification events to the appropriate platform handler.
:: usage: cplatform.modified <modified-type> <filepath> <item-kind> <platform> <platform-name>
::        modified-type: CREATE, UPDATE, DELETE, RENAME
::        item-kind: FILE, DIR
:: example: cplatform.modified UPDATE "C:\cplatform\clibrary\node\config.bat" FILE clibrary node
::------------------------------------------------------------------------------------------
@echo off
setlocal enabledelayedexpansion
alert modified_type: %~1
alert filepath: %~2 
alert item_kind: %~3
alert platform: %~4
alert platform_name: %~5
