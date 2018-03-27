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
	public static float ZERO_EPSILONE = 0.001f;
	static float zero(float v){ return (Math.abs(v)<ZERO_EPSILONE) ? 0 : v; }
	static boolean equal(float x, float y) { return Math.abs(x - y) < cmath.ZERO_EPSILONE; } 
	static float get_random_value_from_range(float min, float max) { return min + ((float)Math.random() * (max - min)); }	
	static boolean is_value_within_range(float value, float min, float max) { return (min <= value && value <= max); }
	
	static double zero(double v){ return (Math.abs(v)<(double)ZERO_EPSILONE) ? 0 : v; }
	static boolean equal(double x, double y) { return Math.abs(x - y) < cmath.ZERO_EPSILONE; } 
	static double get_random_value_from_range(double min, double max) { return min + (Math.random() * (max - min)); }	
	static boolean is_value_within_range(double value, double min, double max) { return (min <= value && value <= max); }

	public static CMatrix mean_normalization(CMatrix X) {
		int n = X.columnLength(); 
		CVector mean = new CVector(n);
		CVector range = new CVector(n);
		for(int j=0; j<n; j++) {
			CVector vcol = X.j(j);
			mean.i(j,vcol.mean());
			range.i(j,vcol.range());
		} // end for
		CMatrix Xn = X.addByColumn(mean.multiply(-1));
		return Xn.multiplyByColumn(range.inverse());
	} // end mean_normalization()	
} // end cmath