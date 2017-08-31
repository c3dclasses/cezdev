//-----------------------------------------------------------------
// file: cconstants.php
// desc: defines useful constants used through C3DClassesSDK.web 
//-----------------------------------------------------------------
package c3dclasses.ccore;

// includes
// include_js(relname(__FILE__) . '/cconstants.js'); 

//----------------------------------------------------------------
// name: CConstants
// desc: defines bit constants 
//----------------------------------------------------------------
class CConstants {		
/*		
	//---------------------------------------------------
	// name: init()
	// desc: initializes the constantes
	//---------------------------------------------------
	public static void init() { 
		// alignment within a rectangle 
		CConstants.addConstant("NONE", (CConstants.NONE = CBit.BITNONE)); 
		CConstants.addConstant("TOP", (CConstants.TOP = CBit.BIT[1])); 
		CConstants.addConstant("BOTTOM", (CConstants.BOTTOM = CBit.BIT[2])); 
		CConstants.addConstant("RIGHT", (CConstants.RIGHT = CBit.BIT[3])); 
		CConstants.addConstant("LEFT", (CConstants.LEFT = CBit.BIT[4])); 
		CConstants.addConstant("VCENTER", (CConstants.VCENTER = CBit.BIT[5])); 
		CConstants.addConstant("HCENTER", (CConstants.HCENTER = CBit.BIT[6])); 
		CConstants.addConstant("VMIDDLE", (CConstants.VCENTER)); 
		CConstants.addConstant("HMIDDLE", (CConstants.HCENTER)); 
		CConstants.TOPLEFT = (CConstants.TOP | CConstants.LEFT);
		CConstants.TOPRIGHT	= (CConstants.TOP | CConstants.RIGHT);
		CConstants.LEFTTOP = (CConstants.TOP | CConstants.LEFT);
		CConstants.RIGHTTOP	= (CConstants.TOP | CConstants.RIGHT);
		CConstants.BOTTOMLEFT = (CConstants.BOTTOM | CConstants.LEFT);
		CConstants.BOTTOMRIGHT = (CConstants.BOTTOM | CConstants.RIGHT);
		CConstants.LEFTBOTTOM = (CConstants.BOTTOM | CConstants.LEFT);
		CConstants.RIGHTBOTTOM	= (CConstants.BOTTOM | CConstants.RIGHT);
		CConstants.CENTER = (CConstants.VCENTER | CConstants.HCENTER);
		CConstants.MIDDLE = (CConstants.VCENTER | CConstants.HCENTER);
			
		// alignment outside a region
		CConstants.addConstant("ABOVE", (CConstants.ABOVE = CBit.BIT[7])); 
		CConstants.addConstant("BELOW", (CConstants.BELOW = CBit.BIT[8])); 
		CConstants.addConstant("RSIDE", (CConstants.RSIDE = CBit.BIT[9])); 
		CConstants.addConstant("LSIDE", (CConstants.LSIDE = CBit.BIT[10]));
			
		// alignment within a linear region
		CConstants.addConstant("FIRST", (CConstants.FIRST = CBit.BIT[11])); 
		CConstants.addConstant("LAST", (CConstants.LAST = CBit.BIT[12])); 
		CConstants.addConstant("INTERIOR", (CConstants.INTERIOR = CBit.BIT[13])); 
		CConstants.FIRSTLAST = (CConstants.FIRST | CConstants.LAST);
		
		// direction 
		CConstants.addConstant("UP", (CConstants.UP = CBit.BIT[14])); 
		CConstants.addConstant("DOWN", (CConstants.DOWN = CBit.BIT[15])); 
		CConstants.addConstant("BACKWARD", (CConstants.BACKWARD = CBit.BIT[16])); 
		CConstants.addConstant("FORWARD", (CConstants.FORWARD = CBit.BIT[17]));
		CConstants.addConstant("NORTH", (CConstants.NORTH = CBit.BIT[18])); 
		CConstants.addConstant("SOUTH", (CConstants.SOUTH = CBit.BIT[19])); 
		CConstants.addConstant("EAST", (CConstants.EAST = CBit.BIT[20])); 
		CConstants.addConstant("WEST", (CConstants.WEST = CBit.BIT[21]));			
		CConstants.NORTHEAST = (CConstants.NORTH | CConstants.EAST);
		CConstants.SOUTHEAST = (CConstants.SOUTH | CConstants.EAST);
		CConstants.NORTHWEST = (CConstants.NORTH | CConstants.WEST);
		CConstants.SOUTHWEST = (CConstants.SOUTH | CConstants.WEST);
	
		// defines three possible location of a poinr
		CConstants.addConstant("FRONT", (CConstants.FRONT = CBit.BIT[22])); 
		CConstants.addConstant("BACK", (CConstants.BACK = CBit.BIT[23])); 
		CConstants.addConstant("COPLANER", (CConstants.COPLANER = CBit.BIT[24])); 
		CConstants.addConstant("SPLIT", (CConstants.SPLIT = CBit.BIT[25]));			
		
		// dimensions
		CConstants.addConstant("WIDTH", (CConstants.WIDTH = CBit.BIT[26])); 
		CConstants.addConstant("HEIGHT", (CConstants.HEIGHT = CBit.BIT[27])); 
		CConstants.addConstant("LENGTH", (CConstants.LENGTH = CBit.BIT[28])); 
		CConstants.HEIGHTWIDTH = (CConstants.HEIGHT | CConstants.WIDTH);
		CConstants.WIDTHHEIGHT = CConstants.HEIGHTWIDTH;
		CConstants.WIDTHHEIGHTLENGTH = (CConstants.HEIGHTWIDTH | CConstants.LENGTH);
	} // end init()		
	
	//----------------------------------------
	// name: addConstant()
	// desc: adds a constant
	//----------------------------------------
	static public boolean addConstant(String strname, int ibit) {
		// check incoming data
		if(!strname || strname == "" || ibit < 0) {
			return false;
		} // end if
			
		// check the string constant array 
		if(CConstants.m_strconstants == null) {
			CConstants.m_strconstants = new CArray();
		} // end if
			
		CConstants.m_strconstants[] = strname;
		return true;
	} // end addConstant()
	
	//-------------------------------------------------------------
	// name: toString()
	// desc: given the constants returns a string version of it
	//-------------------------------------------------------------
	static public String toString(int iconstants) {
		// check the constants array
		if(iconstants <= 0) {
			return CConstants.m_strconstants[0];
		} // end if
			
		Sting str = ""; 
		for(int ibit=31; ibit>-1; ibit--) {
			if((iconstants&(1<<ibit))>0)
				str += "" + CConstants.m_strconstants[ibit+1] + " "; 
		} // end for

		// return the string
		return str;
	} // end toString()
		
	// members
	static public m_strconstants = null;
	
	// alignment within or inside a region
	static public final int NONE;		
	static public final int TOP;
	static public final int BOTTOM; 
	static public final int RIGHT;
	static public final int LEFT; 
	static public final int VCENTER;
	static public final int HCENTER;
	static public final int VMIDDLE;
	static public final int HMIDDLE;
	static public final int TOPLEFT;
	static public final int TOPRIGHT;
	static public final int LEFTTOP;
	static public final int RIGHTTOP;
	static public final int BOTTOMLEFT;
	static public final int BOTTOMRIGHT;
	static public final int LEFTBOTTOM;
	static public final int RIGHTBOTTOM;
	static public final int CENTER;
	static public final int MIDDLE;
		
	// alignment outside a region
	static public final int ABOVE;	
	static public final int BELOW;   
	static public final int RSIDE;  
	static public final int LSIDE;
	
	// alignment within a linear region
	static public final int FIRST;	
	static public final int LAST;	
	static public final int FIRSTLAST;
	static public final int INTERIOR;
	
	// direction 
	static public final int UP; 
	static public final int DOWN;
	static public final int BACKWARD;
	static public final int FORWARD;
	static public final int NORTH;
	static public final int SOUTH;
	static public final int EAST;
	static public final int WEST;
	static public final int NORTHEAST;
	static public final int SOUTHEAST;
	static public final int NORTHWEST;
	static public final int SOUTHWEST;
		
	// defines three possible location of a poinr
	static public final int FRONT;
	static public final int BACK;
	static public final int COPLANER;
	static public final int SPLIT;
		
	// dimensions
	static public final int WIDTH;
	static public final int HEIGHT;
	static public final int LENGTH;
	static public final int HEIGHTWIDTH;
	static public final int WIDTHHEIGHT;
	static public final int WIDTHHEIGHTLENGTH;
	*/
} // end CConstants

// initialize these constants 
// CConstants.init();

