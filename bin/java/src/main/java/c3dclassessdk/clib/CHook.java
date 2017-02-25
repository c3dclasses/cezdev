//-----------------------------------------------------
// file: CHook.java
// desc: 
//-----------------------------------------------------
package c3dclassessdk.clib;
import c3dclassessdk.clib.*;
import c3dclassessdk.ccore.*;


//-----------------------------------------------------------
// name: CHook
// desc: 
//-----------------------------------------------------------
public class CHook{
	public static CHash m_chashhook = null;
	
	public static boolean add( String strhookname, CFunction fnhandler ){	
		if( strhookname == "" || fnhandler == null )
			return false;
		if( CHook.m_chashhook == null )
			CHook.m_chashhook = new CHash();				
		if( CHook.m_chashhook.containsKey( strhookname ) == false )
			CHook.m_chashhook.set( strhookname, new CArray() );
		CArray chook = (CArray)CHook.m_chashhook.get( strhookname );
		if( chook != null ) 
			chook.push( fnhandler );
		return true;
	} // end add()
	
	public static boolean remove( String strhookname ){
		return CHook.remove( strhookname, null );
	} // end remove()
	
	public static boolean remove( String strhookname, CFunction fnhandler ){
		if( strhookname == "" || strhookname == null || CHook.m_chashhook == null )
			return false;	
		if( fnhandler == null ) 
			CHook.m_chashhook.remove( strhookname );
		else if( CHook.m_chashhook.containsKey( strhookname ) == true ){
			CArray chook = (CArray) CHook.m_chashhook.get( strhookname );
			chook.remove( fnhandler );
		} // end else
		return true;
	} // end remove()
	
	public static String fire( String strhookname ){ 
		return CHook.fire( strhookname, null);
	} // end fire()
	
	public static String fire( String strhookname, Object params ){ 
		CArray chook=null;
		//if( CHook.m_chashhook != null && ( chook = (CArray)CHook.m_chashhook.get( strhookname ) ) != null )
		//	return chook.toStringVisit( "CHook::doCallback", params ); 
		return "";
	} // end fire()
	/*
	public static function doCallback( CFunction fnhandler, Object params ){
		return ( isFunction(fnhandler) ) ? call_user_func( fnhandler, params ) : "";
	} // end doCallback()
	*/
} // end CHook