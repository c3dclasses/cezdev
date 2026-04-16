//-----------------------------------------------------------------
// file: cconstants.php
// desc: defines useful constants used through C3DClassesSDK.web 
//-----------------------------------------------------------------
package c3dclasses;

//----------------------------------------------------------------
// name: CConstants
// desc: defines bit constants 
//----------------------------------------------------------------
class CConstants {		
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
		
		// logical 
		CConstants.G = CConstants.GREATER = CBit.BIT[14];
		CConstants.L = CConstants.LESS = CBit.BIT[15];
		CConstants.E = CConstants.EQUAL = CBit.BIT[16];
		CConstants.GE = CConstants.GREATEREQUAL = (CConstants.GREATER|CConstants.EQUAL);
		CConstants.LE = CConstants.LESSEQUAL = (CConstants.LESS|CConstants.EQUAL);
		CConstants.addConstant("GREATER", CConstants.GREATER); 
		CConstants.addConstant("LESS", CConstants.LESS); 
		CConstants.addConstant("EQUAL", CConstants.EQUAL); 
		CConstants.addConstant("GREATEREQUAL", CConstants.LESSEQUAL);
		CConstants.addConstant("LESSEQUAL", CConstants.GREATEREQUAL); 
		CConstants.addConstant("G", CConstants.G); 
		CConstants.addConstant("L", CConstants.L); 
		CConstants.addConstant("E", CConstants.E); 
		CConstants.addConstant("GE", CConstants.GE);
		CConstants.addConstant("LE", CConstants.LE);
	} // end init()		
	
	//----------------------------------------
	// name: addConstant()
	// desc: adds a constant
	//----------------------------------------
	static public boolean addConstant(String strname, int ibit) {
		// check incoming data
		if(strname == null || strname == "" || ibit < 0) {
			return false;
		} // end if
			
		// check the string constant array 
		if(CConstants.m_strconstants == null) {
			CConstants.m_strconstants = __.carray();
		} // end if
		
		CConstants.m_strconstants.push(strname);
		return true;
	} // end addConstant()
	
	//-------------------------------------------------------------
	// name: toString()
	// desc: given the constants returns a string version of it
	//-------------------------------------------------------------
	static public String toString(int iconstants) {
		// check the constants array
		if(iconstants <= 0) {
			return CConstants.m_strconstants._string(0);
		} // end if
			
		String str = ""; 
		for(int ibit=31; ibit>-1; ibit--) {
			if((iconstants&(1<<ibit))>0)
				str += "" + CConstants.m_strconstants._string(ibit+1) + " "; 
		} // end for

		// return the string
		return str;
	} // end toString()
		
	// members
	static public CArray m_strconstants = null;
	
	// alignment within or inside a region
	static public int NONE;		
	static public int TOP;
	static public int BOTTOM; 
	static public int RIGHT;
	static public int LEFT; 
	static public int VCENTER;
	static public int HCENTER;
	static public int VMIDDLE;
	static public int HMIDDLE;
	static public int TOPLEFT;
	static public int TOPRIGHT;
	static public int LEFTTOP;
	static public int RIGHTTOP;
	static public int BOTTOMLEFT;
	static public int BOTTOMRIGHT;
	static public int LEFTBOTTOM;
	static public int RIGHTBOTTOM;
	static public int CENTER;
	static public int MIDDLE;
		
	// alignment outside a region
	static public int ABOVE;	
	static public int BELOW;   
	static public int RSIDE;  
	static public int LSIDE;
	
	// alignment within a linear region
	static public int FIRST;	
	static public int LAST;	
	static public int FIRSTLAST;
	static public int INTERIOR;
	
	// direction 
	static public int UP; 
	static public int DOWN;
	static public int BACKWARD;
	static public int FORWARD;
	static public int NORTH;
	static public int SOUTH;
	static public int EAST;
	static public int WEST;
	static public int NORTHEAST;
	static public int SOUTHEAST;
	static public int NORTHWEST;
	static public int SOUTHWEST;
		
	// defines three possible location of a poinr
	static public int FRONT;
	static public int BACK;
	static public int COPLANER;
	static public int SPLIT;
		
	// dimensions
	static public int WIDTH;
	static public int HEIGHT;
	static public int LENGTH;
	static public int HEIGHTWIDTH;
	static public int WIDTHHEIGHT;
	static public int WIDTHHEIGHTLENGTH;
	
	// logical
	static public int GREATER;
	static public int LESS;
	static public int EQUAL;
	static public int GREATEREQUAL;
	static public int LESSEQUAL;
	static public int G;
	static public int L;
	static public int E;
	static public int GE;
	static public int LE;
} // end CConstants

// initialize these constants 
// CConstants.init();

