<?php
//-----------------------------------------------------------------
// file: cconstants.php
// desc: defines useful constants used through C3DClassesSDK.web 
//-----------------------------------------------------------------

// includes
include_js( relname(__FILE__) . '/cconstants.js'); 

//----------------------------------------------------------------
// name: CConstants
// desc: defines bit constants 
//----------------------------------------------------------------
class CConstants{				
	//---------------------------------------------------
	// name: init()
	// desc: initializes the constantes
	//---------------------------------------------------
	static public function init(){ 
		// alignment within a rectangle 
		CConstants :: addConstant( "NONE", ( CConstants::$NONE = CBit::$BITNONE ) ); 
		CConstants :: addConstant( "TOP", ( CConstants::$TOP = CBit::$BIT[1] ) ); 
		CConstants :: addConstant( "BOTTOM", ( CConstants::$BOTTOM = CBit::$BIT[2] ) ); 
		CConstants :: addConstant( "RIGHT", ( CConstants::$RIGHT = CBit::$BIT[3] ) ); 
		CConstants :: addConstant( "LEFT", ( CConstants::$LEFT = CBit::$BIT[4] ) ); 
		CConstants :: addConstant( "VCENTER", ( CConstants::$VCENTER = CBit::$BIT[5] ) ); 
		CConstants :: addConstant( "HCENTER", ( CConstants::$HCENTER = CBit::$BIT[6] ) ); 
		CConstants :: addConstant( "VMIDDLE", ( CConstants::$VCENTER ) ); 
		CConstants :: addConstant( "HMIDDLE", ( CConstants::$HCENTER ) ); 
		CConstants :: $TOPLEFT = ( CConstants::$TOP | CConstants::$LEFT );
		CConstants :: $TOPRIGHT	= ( CConstants::$TOP | CConstants::$RIGHT );
		CConstants :: $LEFTTOP = ( CConstants::$TOP | CConstants::$LEFT );
		CConstants :: $RIGHTTOP	= ( CConstants::$TOP | CConstants::$RIGHT );
		CConstants :: $BOTTOMLEFT = ( CConstants::$BOTTOM | CConstants::$LEFT );
		CConstants :: $BOTTOMRIGHT = ( CConstants::$BOTTOM | CConstants::$RIGHT );
		CConstants :: $LEFTBOTTOM = ( CConstants::$BOTTOM | CConstants::$LEFT );
		CConstants :: $RIGHTBOTTOM	= ( CConstants::$BOTTOM | CConstants::$RIGHT );
		CConstants :: $CENTER = ( CConstants::$VCENTER | CConstants::$HCENTER );
		CConstants :: $MIDDLE = ( CConstants::$VCENTER | CConstants::$HCENTER );
			
		// alignment outside a region
		CConstants :: addConstant( "ABOVE", ( CConstants::$ABOVE = CBit::$BIT[7] ) ); 
		CConstants :: addConstant( "BELOW", ( CConstants::$BELOW = CBit::$BIT[8] ) ); 
		CConstants :: addConstant( "RSIDE", ( CConstants::$RSIDE = CBit::$BIT[9] ) ); 
		CConstants :: addConstant( "LSIDE", ( CConstants::$LSIDE = CBit::$BIT[10] ) );
			
		// alignment within a linear region
		CConstants :: addConstant( "FIRST", ( CConstants::$FIRST = CBit::$BIT[11] ) ); 
		CConstants :: addConstant( "LAST", ( CConstants::$LAST = CBit::$BIT[12] ) ); 
		CConstants :: addConstant( "INTERIOR", ( CConstants::$INTERIOR = CBit::$BIT[13] ) ); 
		CConstants :: $FIRSTLAST = ( CConstants :: $FIRST | CConstants :: $LAST );
		
		// direction 
		CConstants :: addConstant( "UP", ( CConstants::$UP = CBit::$BIT[14] ) ); 
		CConstants :: addConstant( "DOWN", ( CConstants::$DOWN = CBit::$BIT[15] ) ); 
		CConstants :: addConstant( "BACKWARD", ( CConstants::$BACKWARD = CBit::$BIT[16] ) ); 
		CConstants :: addConstant( "FORWARD", ( CConstants::$FORWARD = CBit::$BIT[17] ) );
		CConstants :: addConstant( "NORTH", ( CConstants::$NORTH = CBit::$BIT[18] ) ); 
		CConstants :: addConstant( "SOUTH", ( CConstants::$SOUTH = CBit::$BIT[19] ) ); 
		CConstants :: addConstant( "EAST", ( CConstants::$EAST = CBit::$BIT[20] ) ); 
		CConstants :: addConstant( "WEST", ( CConstants::$WEST = CBit::$BIT[21] ) );			
		CConstants :: $NORTHEAST = ( CConstants :: $NORTH | CConstants :: $EAST );
		CConstants :: $SOUTHEAST = ( CConstants :: $SOUTH | CConstants :: $EAST );
		CConstants :: $NORTHWEST = ( CConstants :: $NORTH | CConstants :: $WEST );
		CConstants :: $SOUTHWEST = ( CConstants :: $SOUTH | CConstants :: $WEST );
	
		// defines three possible location of a poinr
		CConstants::addConstant( "FRONT", ( CConstants::$FRONT = CBit::$BIT[22] ) ); 
		CConstants::addConstant( "BACK", ( CConstants::$BACK = CBit::$BIT[23] ) ); 
		CConstants::addConstant( "COPLANER", ( CConstants::$COPLANER = CBit::$BIT[24] ) ); 
		CConstants::addConstant( "SPLIT", ( CConstants::$SPLIT = CBit::$BIT[25] ) );			
		
		// dimensions
		CConstants::addConstant( "WIDTH", ( CConstants::$WIDTH = CBit::$BIT[26] ) ); 
		CConstants::addConstant( "HEIGHT", ( CConstants::$HEIGHT = CBit::$BIT[27] ) ); 
		CConstants::addConstant( "LENGTH", ( CConstants::$LENGTH = CBit::$BIT[28] ) ); 
		CConstants :: $HEIGHTWIDTH = (CConstants :: $HEIGHT | CConstants :: $WIDTH);
		CConstants :: $WIDTHHEIGHT = CConstants :: $HEIGHTWIDTH;
		CConstants :: $WIDTHHEIGHTLENGTH = ( CConstants :: $HEIGHTWIDTH | CConstants :: $LENGTH );
	} // end init()		
	
	//----------------------------------------
	// name: addConstant()
	// desc: adds a constant
	//----------------------------------------
	static public function addConstant( $strname, $ibit ){
		// check incoming data
		if( !$strname || $strname == "" || $ibit < 0 ){
			return false;
		} // end if
			
		// check the string constant array 
		if( CConstants :: $m_strconstants == NULL ){
			CConstants :: $m_strconstants = array();
		} // end if
			
		CConstants :: $m_strconstants[] = $strname;
		return true;
	} // end addConstant()
	
	//-------------------------------------------------------------
	// name: toString()
	// desc: given the constants returns a string version of it
	//-------------------------------------------------------------
	static function toString( $iconstants ){
		// check the constants array
		if( iconstants <= 0 ){
			return CConstants :: $m_strconstants[0];
		} // end if
			
		$str = ""; 
		for( $ibit=31; $ibit>-1; $ibit-- ){
			if(($iconstants&(1<<$ibit))>0)
				$str = $str . "" . CConstants::$m_strconstants[$ibit+1] . " "; 
		} // end for

		// return the string
		return $str;
	} // end toString()
		
	// members
	static public $m_strconstants = NULL;
	
	// alignment within or inside a region
	static public $NONE;		
	static public $TOP;
	static public $BOTTOM; 
	static public $RIGHT;
	static public $LEFT; 
	static public $VCENTER;
	static public $HCENTER;
	static public $VMIDDLE;
	static public $HMIDDLE;
	static public $TOPLEFT;
	static public $TOPRIGHT;
	static public $LEFTTOP;
	static public $RIGHTTOP;
	static public $BOTTOMLEFT;
	static public $BOTTOMRIGHT;
	static public $LEFTBOTTOM;
	static public $RIGHTBOTTOM;
	static public $CENTER;
	static public $MIDDLE;
		
	// alignment outside a region
	static public $ABOVE;	
	static public $BELOW;   
	static public $RSIDE;  
	static public $LSIDE;
	
	// alignment within a linear region
	static public $FIRST;	
	static public $LAST;	
	static public $FIRSTLAST;
	static public $INTERIOR;
	
	// direction 
	static public $UP; 
	static public $DOWN;
	static public $BACKWARD;
	static public $FORWARD;
	static public $NORTH;
	static public $SOUTH;
	static public $EAST;
	static public $WEST;
	static public $NORTHEAST;
	static public $SOUTHEAST;
	static public $NORTHWEST;
	static public $SOUTHWEST;
		
	// defines three possible location of a poinr
	static public $FRONT;
	static public $BACK;
	static public $COPLANER;
	static public $SPLIT;
		
	// dimensions
	static public $WIDTH;
	static public $HEIGHT;
	static public $LENGTH;
	static public $HEIGHTWIDTH;
	static public $WIDTHHEIGHT;
	static public $WIDTHHEIGHTLENGTH;
} // end CConstants

// initialize these constants 
CConstants :: init();
?>
