function REMOTE_DELETE ( parser )  
	CNode astnode = new Node("REMOTE_DELETE");
	node = cparser.accept("delete");
	astnode.add(node);
	node = cparser.accept("@");
	astnode.add(node);
	node = cparser.acceptNonToken(IDENTIFIER);
	astnode.add(node);
	return astnode;
