//---------------------------------------------------------------------------------
// file: cmath
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: cmath
// desc: 
//---------------------------------------------------------------------------------
public class cmath {	
	public static float ZERO_EPSILONE = 0.000000000001f;
	static boolean equal(float x, float y) { return (x - y) < cmath.ZERO_EPSILONE; } 
	static float get_random_value_from_range(float min, float max) { return min + ((float)Math.random() * (max - min)); }	
	static boolean is_value_within_range(float value, float min, float max) { return (min <= value && value <= max); }
} // end cmath