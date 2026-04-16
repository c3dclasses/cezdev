//--------------------------------------------------------------------------------
// file: cconstants.js
// desc: defines useful constants used through C3DClassesSDK.web 
//--------------------------------------------------------------------------------

//----------------------------------------------------------------
// class: CConstants
// desc: defines bit constants 
//----------------------------------------------------------------
var CConstants = new Class({	
	//-----------------------------------------------------
	// name: ClassMethods
	// desc: stores all the static members and methods
	//-----------------------------------------------------
	ClassMethods : {	
		
		// members
		m_strconstants : null,
			
		//---------------------------------------------------
		// name: init()
		// desc: initializes the constantes
		//---------------------------------------------------
		init : function(){ 	
			// standard constants
			CConstants.addConstant( "NONE", CBit.BITNONE ); 
			CConstants.addConstant( "TOP", CBit.BIT[1] ); 
			CConstants.addConstant( "BOTTOM", CBit.BIT[2] ); 
			CConstants.addConstant( "RIGHT", CBit.BIT[3] ); 
			CConstants.addConstant( "LEFT", CBit.BIT[4] ); 
			CConstants.addConstant( "VCENTER", CBit.BIT[5] ); 
			CConstants.addConstant( "HCENTER", CBit.BIT[6] ); 
			CConstants.VMIDDLE = CConstants.VCENTER;
			CConstants.HMIDDLE = CConstants.HCENTER;
			CConstants.TOPLEFT = ( CConstants.TOP | CConstants.LEFT );
			CConstants.TOPRIGHT	= ( CConstants.TOP | CConstants.RIGHT );
			CConstants.LEFTTOP = ( CConstants.TOP | CConstants.LEFT );
			CConstants.RIGHTTOP	= ( CConstants.TOP | CConstants.RIGHT );
			CConstants.BOTTOMLEFT = ( CConstants.BOTTOM | CConstants.LEFT );
			CConstants.BOTTOMRIGHT = ( CConstants.BOTTOM | CConstants.RIGHT );
			CConstants.LEFTBOTTOM = ( CConstants.BOTTOM | CConstants.LEFT );
			CConstants.RIGHTBOTTOM	= ( CConstants.BOTTOM | CConstants.RIGHT );
			CConstants.CENTER = ( CConstants.VCENTER | CConstants.HCENTER );
			CConstants.MIDDLE = ( CConstants.VCENTER | CConstants.HCENTER );
			
			// alignment outside a region
			CConstants.addConstant( "ABOVE", CBit.BIT[7] ); 
			CConstants.addConstant( "BELOW", CBit.BIT[8] ); 
			CConstants.addConstant( "RSIDE", CBit.BIT[9] ); 
			CConstants.addConstant( "LSIDE", CBit.BIT[10] ); 
			
			// alignment within a linear region
			CConstants.addConstant( "FIRST", CBit.BIT[11] ); 
			CConstants.addConstant( "LAST", CBit.BIT[12] ); 
			CConstants.addConstant( "INTERIOR", CBit.BIT[13] ); 
			CConstants.FIRSTLAST = ( CConstants.FIRST | CConstants.LAST );
			
			// direction 
			CConstants.addConstant( "UP", CBit.BIT[14] ); 
			CConstants.addConstant( "DOWN", CBit.BIT[15] ); 
			CConstants.addConstant( "BACKWARD", CBit.BIT[16] ); 
			CConstants.addConstant( "FORWARD", CBit.BIT[17] ); 
			CConstants.addConstant( "NORTH", CBit.BIT[18] ); 
			CConstants.addConstant( "SOUTH", CBit.BIT[19] ); 
			CConstants.addConstant( "EAST", CBit.BIT[20] ); 
			CConstants.addConstant( "WEST", CBit.BIT[21] ); 
			CConstants.NORTHEAST = ( CConstants.NORTH | CConstants.EAST );
			CConstants.SOUTHEAST = ( CConstants.SOUTH | CConstants.EAST );
			CConstants.NORTHWEST = ( CConstants.NORTH | CConstants.WEST );
			CConstants.SOUTHWEST = ( CConstants.SOUTH | CConstants.WEST );
	
			// defines three possible location of a poinr
			CConstants.addConstant( "FRONT", CBit.BIT[22] ); 
			CConstants.addConstant( "BACK", CBit.BIT[23] ); 
			CConstants.addConstant( "COPLANER", CBit.BIT[24] ); 
			CConstants.addConstant( "SPLIT", CBit.BIT[25] ); 
		
			// dimensions
			CConstants.addConstant( "WIDTH", CBit.BIT[26] ); 
			CConstants.addConstant( "HEIGHT", CBit.BIT[27] ); 
			CConstants.addConstant( "LENGTH", CBit.BIT[28] ); 
			CConstants.HEIGHTWIDTH = (CConstants.HEIGHT | CConstants.WIDTH);
			CConstants.WIDTHHEIGHT = CConstants.HEIGHTWIDTH;
			CConstants.WIDTHHEIGHTLENGTH = ( CConstants.HEIGHTWIDTH | CConstants.LENGTH );
			return true;
		}, // end init()		
		
		//----------------------------------------
		// name: addConstant()
	// desc: adds a constant
		//----------------------------------------
		addConstant : function( strname, ibit ){
			// check incoming data
			if( !strname || strname == "" || ibit < 0 ){
				return false;
			} // end if
			
			// check the string constant array 
			if( !CConstants.m_strconstants ) {
				CConstants.m_strconstants = [];
			} // end if
			
			CConstants[strname] = ibit; 
			CConstants.m_strconstants.push( strname );
			return true;
		}, // end addConstant()
		
		//-------------------------------------------------------------
		// name: toSting()
		// desc: given the constants returns a string version of it
		//-------------------------------------------------------------
		toString : function( iconstants ){
			// check the contants array
			if( iconstants <= 0 ){
				return CConstants.m_strconstants[0];
			} // end if
			
			var str = ""; 
			for( var ibit=31; ibit>-1; ibit-- ){
				if((iconstants&(1<<ibit)) > 0)
					str += ( CConstants.m_strconstants[ibit+1] + " " ); 
			} // end for
			
			// return the string
			return str;
		} // end toString()
	} // end ClassMethods
}); // end CConstants

// initialize these constants 
CConstants.init();