//--------------------------------------------------
// file: cpoint.js
// desc: defines a point object 
//--------------------------------------------------

//--------------------------------------------------------
// name: CPoint
// desc: defines and object that stores position info
//--------------------------------------------------------
var CPoint = new Class({
	initialize : function( x, y, z ){
		this.set( x, y, z );
	}, // end CPoint()
	set : function( x, y, z ){ 
		this.x = x;
		this.y = y;
		this.z = z;	
	}, // end set()
	translate : function( dx, dy, dz ){ 
		this.x += dx; 
		this.y += dy; 
		this.z += dz; 
	}, // end translate()
	rotate : function(){
	} // end rotate()
}); // end CPoint