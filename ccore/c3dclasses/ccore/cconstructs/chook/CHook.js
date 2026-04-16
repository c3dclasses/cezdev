//-------------------------------------------------------------------------------------------------------
// file: chook.php
// desc: provides a way to store php handler function that will be executed later by the ckernal object
//-------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------
// name: CHook
// desc: singleton object used to store handler functions
//-----------------------------------------------------------
var CHook = new Class ({
	ClassMethods : {		
	m_chashhook : null,
	add : function( strhookname, fnhandler ){
		if( strhookname == "" || strhookname == null || fnhandler == null )
			return false;
		if( CHook.m_chashhook == null )
			CHook.m_chashhook = new CHash();		
		if( CHook.m_chashhook.containsKey( strhookname ) == false )
			CHook.m_chashhook.set( strhookname, new Array() );
		var chook = CHook.m_chashhook.get( strhookname )
		if( chook ) 
			chook.push( fnhandler );
		return true;
	}, // end add()
	remove : function( strhookname, fnhandler ){
		if( strhookname == "" || strhookname == null || CHook.m_chashhook == null )
			return false;
		if( fnhandler == null )
			CHook.m_chashhook.remove( strhookname );
		else if( CHook.m_chashhook.containsKey( strhookname ) == true )
			CHook.m_chashhook.get( strhookname ).remove( fnhandler );
		return true;
	}, // end remove()
	fire : function( strhookname ){	 
		var chook = null;
		if( CHook.m_chashhook == null || ( chook = CHook.m_chashhook.get( strhookname ) ) == null )
			return false;		
		chook.visit( CHook.doCallback ); 
		return true;	
	}, // end fire()
	doCallback : function ( key, fnhandler, params ){
		if( isFunction( fnhandler ) ) fnhandler( key, params ); 
	} // end doCallback()
	} // end ClassMethods
}); // end CHook