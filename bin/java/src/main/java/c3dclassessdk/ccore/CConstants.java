//-----------------------------------------------------------------
// file: CConstants.php
// desc: defines useful constants used through C3DClassesSDK
//-----------------------------------------------------------------
package c3dclassessdk.ccore;
import c3dclassessdk.clib.*;
import c3dclassessdk.ccore.*;

//----------------------------------------------------------------
// name: CConstants
// desc: defines bit constants 
//----------------------------------------------------------------
public class CConstants {				
	// alignment within or inside a region
	static public final int NONE = CBit.BITNONE;		
	static public final int TOP = CBit.BIT1;
	static public final int BOTTOM = CBit.BIT2; 
	static public final int RIGHT = CBit.BIT3; 
	static public final int LEFT = CBit.BIT4; 
	static public final int VCENTER = CBit.BIT5;
	static public final int HCENTER = CBit.BIT6;
	static public final int VMIDDLE = CConstants.VCENTER;
	static public final int HMIDDLE = CConstants.HCENTER ;
	static public final int TOPLEFT = ( CConstants.TOP | CConstants.LEFT );
	static public final int TOPRIGHT = ( CConstants.TOP | CConstants.RIGHT );
	static public final int LEFTTOP = CConstants.TOPLEFT;
	static public final int RIGHTTOP = CConstants.TOPRIGHT;
	static public final int BOTTOMLEFT = ( CConstants.TOP | CConstants.LEFT );
	static public final int BOTTOMRIGHT = ( CConstants.BOTTOM | CConstants.RIGHT );
	static public final int LEFTBOTTOM = CConstants.BOTTOMLEFT;
	static public final int RIGHTBOTTOM = CConstants.BOTTOMRIGHT;
	static public final int CENTER = ( CConstants.VCENTER | CConstants.HCENTER );
	static public final int MIDDLE = CConstants.CENTER;
	
	// alignment outside a region
	static public final int ABOVE = CBit.BIT7;
	static public final int BELOW = CBit.BIT8;
	static public final int RSIDE = CBit.BIT9;
	static public final int LSIDE = CBit.BIT10;
	
	// alignment within a linear regionstatic public final int FIRST = CBit.BIT11;
	static public final int FIRST = CBit.BIT11;
	static public final int LAST = CBit.BIT12;
	static public final int INTERIOR = CBit.BIT13;
	static public final int FIRSTLAST = ( CConstants.FIRST | CConstants.LAST );
	static public final int LASTFIRST = ( CConstants.FIRST | CConstants.LAST );
	
	// direction 
	static public final int UP = CBit.BIT14; 
	static public final int DOWN = CBit.BIT15; 
	static public final int BACKWARD = CBit.BIT16; 
	static public final int FORWARD = CBit.BIT17;
	static public final int NORTH = CBit.BIT18; 
	static public final int SOUTH = CBit.BIT19; 
	static public final int EAST = CBit.BIT20; 
	static public final int WEST = CBit.BIT21;			
	static public final int NORTHEAST = ( CConstants.NORTH | CConstants.EAST );
	static public final int SOUTHEAST = ( CConstants.SOUTH | CConstants.EAST );
	static public final int NORTHWEST = ( CConstants.NORTH | CConstants.WEST );
	static public final int SOUTHWEST = ( CConstants.SOUTH | CConstants.WEST );
	
	// defines three possible location of a poinr
	static public final int FRONT = CBit.BIT22; 
	static public final int BACK = CBit.BIT23; 
	static public final int COPLANER = CBit.BIT24; 
	static public final int SPLIT = CBit.BIT25;			
		
	// dimensions
	static public final int WIDTH = CBit.BIT26; 
	static public final int HEIGHT = CBit.BIT27; 
	static public final int LENGTH = CBit.BIT28; 
	static public final int HEIGHTWIDTH = (CConstants.HEIGHT | CConstants.WIDTH);
	static public final int WIDTHHEIGHT = CConstants.HEIGHTWIDTH;
	static public final int WIDTHHEIGHTLENGTH = ( CConstants.HEIGHTWIDTH | CConstants.LENGTH );
} // end CConstants