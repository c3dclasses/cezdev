<?php
//-------------------------------------------------------------------------------------------------------
// file: chook.php
// desc: provides a way to store php handler function that will be executed later by the ckernal object
//-------------------------------------------------------------------------------------------------------

// header
include_js( relname(__FILE__) . "/chook.js" );

//-----------------------------------------------------------
// name: CHook
// desc: singleton object used to store handler functions
//-----------------------------------------------------------
class CHook{
	public static $m_chashhook = NULL;
	public static function add( $strhookname, $fnhandler, $index=-1 ){	
		if( $strhookname == "" || $fnhandler == "" || is_callable( $fnhandler ) == false )
			return false;
		if( CHook :: $m_chashhook == NULL )
			CHook :: $m_chashhook = new CHash();				
		if( CHook :: $m_chashhook->containsKey( $strhookname ) == FALSE )
			CHook :: $m_chashhook->set( $strhookname, new CArray() );
		$chook = CHook :: $m_chashhook->get( $strhookname );
		if( $chook ) 
			$chook->push( $fnhandler );
			//$chook->insertAt( $index, $fnhandler );
		return true;
	} // end add()
	public static function remove( $strhookname, $fnhandler = NULL ){
		if( $strhookname == "" || $strhookname == NULL || CHook::$m_chashhook == NULL )
			return false;	
		if( $fnhandler == NULL ) 
			CHook :: $m_chashhook->remove( $strhookname );
		else if( CHook :: $m_chashhook->containsKey( $strhookname ) == true )
			CHook :: $m_chashhook->get( $strhookname )->remove( $fnhandler );
		return true;
	} // end remove()
	public static function fire( $strhookname, $params=NULL ){ 
		if( CHook :: $m_chashhook != NULL && ( $chook = CHook :: $m_chashhook->get( $strhookname ) ) != NULL )
			return $chook->toStringVisit( "CHook::doCallback", $params ); 
	} // end fire()
	public static function doCallback( $key, $fnhandler, $params ){
		return ( isFunction($fnhandler) ) ? call_user_func( $fnhandler, $params ) : "";
	} // end doCallback()
} // end CHandler
?>