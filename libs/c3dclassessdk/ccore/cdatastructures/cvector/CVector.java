//---------------------------------------------------------------------------------
// file: CVector
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: CVector
// desc: 
//---------------------------------------------------------------------------------
public class CVector {
	protected double [] m_data = null;
	public CVector() {}
	public CVector(CVector v) { this.m_data = v.m_data.clone(); } 
	public CVector(int length) { this.m_data = new double [length]; } 
	public CVector(double [] data) { this.m_data = data.clone(); } 
	
	//public CVector(double ... values) { this.m_data = values.clone(); } 
	public int length() { return this.m_data.length; }
	public double i(int i) { return this.m_data[i]; }
	public void i(int i, double value) { this.m_data[i] = value; }
	public CVector add(CVector v) {
		int l = this.length();
		if(l != v.length())
			return null;
		CVector o = new CVector(l);
		for(int i=0; i<l; i++)
			o.i(i,this.i(i)+v.i(i));
		return o;
	} // end add()
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
			_.alert(this.i(i));
			o.i(i,1.0/this.i(i));
		}
		return o;
	} // end inverse()
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
	boolean equals(CVector v) { 
		CVector this_nv = this.normalize();
		CVector nv = v.normalize();
		return this.equalMagnitude(v) && this_nv.equalComponents(nv); 
	} // equals()
	boolean equalComponents(CVector v) {
		if(v == null && v.length() != this.length())
			return false;
		int l = this.length();
		for(int i=0; i<l; i++)
			if(!cmath.equal(this.i(i), v.i(i)))
				return false;
		return true;
	} // end equalComponents()
	boolean equalMagnitude(CVector v) {
		return cmath.equal(this.magnitude(),v.magnitude());
	} // end equalMagnitude()
	double euclideanDistance(CVector v) {
		double result = 0;
		int l = this.length();
		for(int i=0; i<l; i++)
			result += Math.pow(this.i(i)-v.i(i), 2);
		return (double)Math.sqrt(result);
	} // end euclideanDistance()
	boolean isWithinRange(CVector vmin, CVector vmax) {
		if(vmin == null || vmax == null)
			return false;
		int l=this.length();
		for(int i=0; i<l; i++) {
			if(!cmath.is_value_within_range(this.i(i), vmin.i(i), vmax.i(i)))
				return false;
		} // end for
		return true;
	} // end isWithinRange()
	public String toStringVector() {
		String str = "";
		int l = this.length();
		for(int i=0; i<l; i++) {
			str += this.i(i);
			if(i < l-1)
				str += ", ";
		} // end for
		return str;
	} // end toStringVector()
	public String toString() {
		String str = "";
		for(int i=0; i<this.m_data.length; i++) { 
			str += this.m_data[i];
			if(i < this.m_data.length-1)
				str += ",";
		} // end for()
		return str;
	} // end toString()
	
	// statistics on vectors
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
	
	// static 
	static CVector getMinComponentVector(CMatrix cvectors) {
		if(cvectors == null)
			return null;
		int nr = cvectors.rowLength();
		int nc = cvectors.columnLength();
		if(nr<=0 || nc <=0)
			return null;
		CVector v = new CVector(cvectors.row(0));
		for(int r=1; r<nr; r++)
			for(int c=0; c<nc; c++)
				v.i(c,(double)Math.min(v.i(c),cvectors.get(r,c)));
		return v;
	} // end getMinComponentVector()
	static CVector getMaxComponentVector(CMatrix cvectors) {
		if(cvectors == null)
			return null;
		int nr = cvectors.rowLength();
		int nc = cvectors.columnLength();
		if(nr<=0 || nc <=0)
			return null;
		CVector v = new CVector(cvectors.row(0));
		for(int r=1; r<nr; r++)
			for(int c=0; c<nc; c++)
				v.i(c,(double)Math.max(v.i(c),cvectors.get(r,c)));
		return v;
	} // end getMaxComponentVector()
	static CVector getRandomVectorFromRange(CVector vmin, CVector vmax) {
		if(vmin == null || vmax == null)
			return null;
		int l = vmin.length();
		CVector vrand = new CVector(l);
		for(int i=0; i<l; i++)
			vrand.i(i, cmath.get_random_value_from_range(vmin.i(i), vmax.i(i)));
		return vrand;
	} // end getRandomVectorFromRange()
	static CMatrix getRandomVectorsFromRange(int n, CVector vmin, CVector vmax) {
		if(n < 1 || vmin == null || vmax == null)
			return null;
		int l = vmin.length();
		CMatrix vrands = new CMatrix();
		for(int i=0; i<n; i++)
			vrands.push(CVector.getRandomVectorFromRange(vmin, vmax));
		return vrands;
	} // end getRandomVectorsFromRange()
	static boolean areVectorsWithinRange(CMatrix cvectors, CVector vmin, CVector vmax) {
		if(cvectors == null)
			return false;
		int l=cvectors.length();
		for(int i=0; i<l; i++) {
			CVector v = (CVector)cvectors.row(i);
			if(!v.isWithinRange(vmin, vmax))
				return false;
		} // end for
		return true;
	} // end areVectorsWithinRange()	
} // end CVector