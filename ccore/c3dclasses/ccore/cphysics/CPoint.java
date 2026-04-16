//--------------------------------------------------
// file: CPoint
// desc: defines a point object 
//--------------------------------------------------
package c3dclasses;
import java.util.*;

//--------------------------------------------------------
// name: CPoint
// desc: defines and object that stores position info
//--------------------------------------------------------
public class CPoint {
	public CPoint(double x, double y, double z) { 
		this.set(x, y, z); 
	} // end CPoint()
	public CPoint() { 
		this.set(0, 0, 0); 
	} // end CPoint()
	public void set(double x, double y, double z) { 
		this.x = x; 
		this.y = y; 
		this.z = z; 
	} // end set()
	public void translate(double dx, double dy, double dz) { 
		this.x += dx; 
		this.y += dy; 
		this.z += dz; 
	} // end translate()
	public void rotate() {
	} // end rotate()
	public double x;
	public double y;
	public double z;	
} // end CPoint