//---------------------------------------------------------------------------------
// file: CVector
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import java.io.*;
import java.util.*;


//---------------------------------------------------------------------------------
// name: CVector
// desc: 
//---------------------------------------------------------------------------------
public class CVector {
	protected double [] m_data = null;
	
	////////////////////////////
	// constructor
	public CVector() {}
	public CVector(CVector v) { this.m_data = v.m_data.clone(); } 
	public CVector(int length) { this.m_data = new double [length]; } 
	public CVector(double [] data) { this.m_data = data.clone(); } 	
	
	//////////////////////////
	// info
	public int length() { return this.m_data.length; }
	
	//////////////////////////
	// indexing
	public double i(int i) { return this.m_data[i]; }
	public void i(int i, double value) { this.m_data[i] = value; }
	public void set(double v) { int l = this.length(); for(int i=0; i<l; i++) this.i(i,v); } 
	public CVector copy(){ return new CVector(this);}
	public CVector subVertex(int e) { return this.subVertex(0, e); }
	public CVector subVertex(int s, int e) {
		int l = this.length();
		int n = (e-s)+1;
		if(s<0 || e<0 || s>=l || e>=l || n<1)
			return null;
		CVector v = new CVector(n);
		for(int i=s; i<n; i++) 
			v.i(i-s,this.i(i));
		return v;
	} // end subVertex()
	
	///////////////////////
	// operations
	public double times(CVector v) { return this.multiply(v); }
	public double multiply(CVector v) {
		int l = this.length();
		if(l != v.length())
			return 0;
		double sum = 0;
		for(int i=0; i<l; i++)
			sum += this.i(i) * v.i(i);
		return sum;
	} // multiply()
	public CVector inverse() {
		int l = this.length();
		CVector o = new CVector(l);
		for(int i=0; i<l; i++) {
			//_.alert(this.i(i));
			o.i(i,1.0/this.i(i));
		}
		return o;
	} // end inverse()
	public CVector divide(CVector vector) {
		int l = this.length();
		CVector o = new CVector(l);
		for(int i=0; i<l; i++)
			o.i(i,this.i(i)/vector.i(i));
		return o;
	} // end divide()
	public CVector times(double s) { return this.multiply(s); }
	public CVector divide(double s) { return this.multiply(1/s); }
	public CVector multiply(double s) {
		int l = this.length();
		CVector o = new CVector(l);
		for(int i=0; i<l; i++) 
			o.i(i,this.i(i)*s);
		return o;
	} // multiply()	
	public double magnitude() {
		double sum = 0;
		int l = this.length();
		for(int i=0; i<l; i++)
			sum += Math.pow(this.i(i), 2);
		return Math.sqrt(sum);
	} // end magnitude()
	public CVector normalize() {
		int l = this.length();
		double m = this.magnitude();
		CVector n = new CVector(l);
		for(int i=0; i<l; i++)
			n.i(i, this.i(i)/m);
		return n;
	} // end normalize()
	public double distance(CVector v) { return this.euclideanDistance(v); }
	public double euclideanDistance(CVector v) {
		double result = 0;
		int l = this.length();
		for(int i=0; i<l; i++)
			result += Math.pow(this.i(i)-v.i(i), 2);
		return (double)Math.sqrt(result);
	} // end euclideanDistance()
	public CVector plus(CVector v) { return this.add(v); }	
	public CVector add(CVector v) {
		int l = this.length();
		if(l != v.length())
			return null;
		CVector o = new CVector(l);
		for(int i=0; i<l; i++)
			o.i(i,this.i(i)+v.i(i));
		return o;
	} // end add()
	public CVector minus(CVector v) { return this.subtract(v); }	
	public CVector subtract(CVector v) {
		int l = this.length();
		if(l != v.length())
			return null;
		CVector o = new CVector(l);
		for(int i=0; i<l; i++)
			o.i(i,this.i(i)-v.i(i));
		return o;
	} // end add()
	
	///////////////////////////////
	// equality, compatibility
	public boolean equals(CVector v) { 
		CVector this_nv = this.normalize();
		CVector nv = v.normalize();
		return this.equalMagnitude(v) && this_nv.equalComponents(nv); 
	} // equals()
	public boolean equalComponents(CVector v) {
		if(v == null && v.length() != this.length())
			return false;
		int l = this.length();
		for(int i=0; i<l; i++)
			if(!cmath.equal(this.i(i), v.i(i)))
				return false;
		return true;
	} // end equalComponents()
	public boolean equalMagnitude(CVector v) {
		return cmath.equal(this.magnitude(),v.magnitude());
	} // end equalMagnitude()
	public boolean between(CVector vmin, CVector vmax) {
		if(vmin == null || vmax == null)
			return false;
		int l=this.length();
		for(int i=0; i<l; i++) {
			if(!cmath.is_value_within_range(this.i(i), vmin.i(i), vmax.i(i)))
				return false;
		} // end for
		return true;
	} // end between()

	///////////////////////////
	// statistics
	public double mode() {
		return 0;
	} // end mode()
	public int find(double v) {
		int l = this.length();
		for(int i=1; i<l; i++) 
			if(cmath.equal(this.m_data[i],v))
				return i;
		return -1;
	} // end find()
	
	public double max() {
		if(this.m_data == null)
			return 0.0;
		double max = this.i(0);
		int l = this.length();
		for(int i=1; i<l; i++) {
			max = Math.max(max,this.m_data[i]);
		} // end for
		return max;
	} // end max()
	public double min() {
		if(this.m_data == null)
			return 0.0;
		double min = this.i(0);
		int l = this.length();
		for(int i=1; i<l; i++) {
			min = Math.min(min,this.m_data[i]);
		} // end for
		return min;
	} // end min()
	public double range() {
		return Math.abs(this.max()-this.min());	
	} // end range()
	public double sum() {
		if(this.m_data == null)
			return 0.0;
		int l = this.length();
		double sum = 0;
		for(int i=0; i<l; i++) {
			sum += this.m_data[i];
		} // end for
		return sum;
	} // end sum()
	public double mean() {
		return this.sum() / this.length();
	} // end mean()
	
	///////////////////////////
	// toString, toJSON()
	public String toJSON() { return this.toJSON(true);}
	public String toJSON(boolean bpack) {return "";}
	public String toString() {
		String str = "";
		int l = Math.min(this.m_data.length,100);
		for(int i=0; i<l; i++) { 
			str += this.m_data[i];
			if(i < this.m_data.length-1)
				str += ",";
		} // end for()
		if(l>100)
			str += ",...";
		return str;
	} // end toString()
	public CMatrix toMatrix(int nrows, int ncols) { return new CMatrix(this, nrows, ncols); } 
	public boolean toFile(String strfilename) {
		try {
			if(strfilename == null || strfilename == "")
				return false;
			FileWriter out = new FileWriter(strfilename, true);
			int l = this.m_data.length;
			for(int i=0; i<l; i++) { 
				out.write(""+this.m_data[i]);
				if(i < this.m_data.length-1)
					out.write(",");
			} // end for
			out.close();
			return true;
		}
		catch(Exception ex) {
			return false;
		} // end catch()	
	} // end toFile()
	
	////////////
	// static 
	public static CVector rand(CVector vmin, CVector vmax) {
		if(vmin == null || vmax == null)
			return null;
		int l = vmin.length();
		CVector vrand = new CVector(l);
		for(int i=0; i<l; i++)
			vrand.i(i, cmath.get_random_value_from_range(vmin.i(i), vmax.i(i)));
		return vrand;
	} // end rand()
	public static CVector rand(int length) {
		CVector v = new CVector(length);
		for(int i=0; i<length; i++)
			v.i(i, Math.random());
		return v;
	} // end rand()
	
} // end CVector