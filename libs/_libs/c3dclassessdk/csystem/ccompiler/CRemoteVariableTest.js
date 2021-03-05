<body> := <remote_create> | <remote_read_update> | <remote_delete> | <body>
<remote_create> := _remote ( <params> ) @ <identifier> ;
<remote_read_update> := @ <identifier> | @ <identifier> = <lside>  
<remote_delete> := delete @ <identifier> 
<identifier> := ( <stop> | <done> | . ) <identifier>
<params> := ( [<stop>|<done>] | <body> | . ) <params>
