//------------------------------------------------------------------------------------
// file: cresource.js
// desc: opens input and/or output files that can be used globally throughout the SDK
//------------------------------------------------------------------------------------

//-------------------------------------------------------
// name: CResource
// desc: input and output file resource
//-------------------------------------------------------
var CResource = new Class({
	// members
	initialize : function() { 
		this.m_hashparams=null;
	}, // end CResource() 
	
	open : function( strpath, params ) {
		this.m_hashparams = new CHash();
		if( !this.m_hashparams )
			return false;
		this.m_hashparams.create( params );
		this.m_hashparams.set("cresource_path", strpath);
		return true;
	}, // end open()
	
	restore : function() { 
		return this.open( this.m_hashparams.get( "cresource_path" ), this.m_hashparams.valueOf() ); 
	}, // end restore()
	
	_toString : function() { 
		return ""; 
	}, // end toString() 
	
	getParams : function() {
		return this.m_hashparams;
	}, // end getParams()
	
	param : function(strname){
		return ( this.m_hashparams ) ? this.m_hashparams.get(strname) : ""; 
	}, // end params

	path : function(){
		return this.param( "cresource_path" ); 
	}, // end path()
	
	type : function(){
		return this.param("cresource_type"); 
	}, // end path()
	
	id : function(){
		return this.param("cresource_id"); 
	}, // end id()
	
	updateParams : function(params){
		if( params && this.m_hashparams)
			for( var key in params )
				this.m_hashparams.set( key, params[key] );
	}, // end updateParams()
	
	/////////////////////////
	// class methods
	
	ClassMethods: {
		m_hashpathtoresource : null,	// stores the "filepath" -> resource
		m_hashidtoresource : null,	// stroes the "identifier" -> resource
	
		_getByPath : function( strpath ){
			return (CResource.m_hashpathtoresource) ? CResource.m_hashpathtoresource.get( strpath ) : null;
		}, // end _getByPath()
	
		_addByPath : function( strpath, cresource ){
			if( CResource.m_hashpathtoresource == null )
				CResource.m_hashpathtoresource = new CHash();
			if( cresource && CResource.m_hashpathtoresource.containsKey( strpath ) == false ){
				CResource.m_hashpathtoresource.set( strpath, cresource );
				return true;
			} // end if
			return false;			
		}, // end _addByPath()
		
		_getByID : function( strid ){
			return (CResource.m_hashidtoresource) ? CResource.m_hashidtoresource.get( strid ) : null;
		}, // end _getByID()
	
		_addByID : function( strid, cresource ){
			if( CResource.m_hashidtoresource == null )
				CResource.m_hashidtoresource = new CHash();
			if( cresource && CResource.m_hashidtoresource.containsKey( strid ) == false ){
				CResource.m_hashidtoresource.set( strid, cresource );
				return true;
			} // end if
			return false;
		}, // end _addByID()
	
		_register : function( strid, strpath, params ){
			var cresource = null;
			if( strid && ( cresource = CResource._getByID( strid ) ) ){
				cresource.updateParams( params );
				return cresource;
			}
			if( strpath && ( cresource = CResource._getByPath( strpath ) ) ){
				CResource._addByID( strid, cresource );
				cresource.updateParams( params );
				return cresource;
			} // end if
			var strtype = params["cresource_type"];
			
			if( strtype == "" || (cresource = new window[strtype]()) == null || cresource.open( strpath, params ) == false ){
				return null; 	
			} // end if
			
			console.log("cresource: of type: " + strtype + " id: " + strid);
			console.log(cresource);
			
			CResource._addByID( strid, cresource );
			CResource._addByPath( strpath, cresource );		
			return cresource;
		}, // end _register()
	
		toStringVisit : function( strcallback ){
			var rs = CResource.m_hashidtoresource;
			return ( rs ) ? rs.toStringVisit( strcallback ) : null;
		} // end toStringVisit()
	} // end ClassMethods
}); // end CResource

function include_resource(strid, strpath, params){
	return CResource._register( strid, strpath, params );
} // end include_resource()

function use_resource(strid){
	var cresource = CResource._getByID(strid);
	return cresource;
} // end use_resource()