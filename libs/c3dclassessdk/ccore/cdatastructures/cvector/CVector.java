//---------------------------------------------------------------------------------
// file: CVector
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: CVector
// desc: 
//---------------------------------------------------------------------------------
public class CVector extends CArray{	
	public CVector() { super(); } 
	public CVector(CVector CVector) { super(CVector); } 
	public CVector(int length) { super(new int[]{length},0,0.0);} 
	public CVector(Object [] objects) { super(objects); } 

	public float magnitude() {
		float sum = 0;
		int l = this.length();
		for(int i=0; i<l; i++)
			sum += this._float(i) * this._float(i);
		return (float)Math.sqrt(sum);
	} // end magnitude()
	
	public CVector normalize() {
		int l = this.length();
		float m = this.magnitude();
		CVector n = _.cvector(l);
		for(int i=0; i<l; i++)
			n._(i, this._float(i)/m);
		return n;
	} // end normalize()
	
	// equality
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
			if(!cmath.equal(this._float(i), v._float(i)))
				return false;
		return true;
	} // end equalComponents()

	boolean equalMagnitude(CVector v) {
		return cmath.equal(this.magnitude(),v.magnitude());
	} // end equalMagnitude()
	
	float euclideanDistance(CVector v) {
		float result = 0;
		int l = this.length();
		for(int i=0; i<l; i++)
			result += Math.pow((this._float(i)-v._float(i)), 2);
		return (float)Math.sqrt(result);
	} // end euclideanDistance()
	
	boolean isWithinRange(CVector vmin, CVector vmax) {
		if(vmin == null || vmax == null)
			return false;
		int l=this.length();
		for(int i=0; i<l; i++) {
			if(!cmath.is_value_within_range(this._float(i), vmin._float(i), vmax._float(i)))
				return false;
		} // end for
		return true;
	} // end isWithinRange()
	
	////////////////////
	// static methods
	static CVector getMinComponentVector(CMatrix cvectors) {
		if(cvectors == null)
			return null;
		int nr = cvectors.rowLength();
		int nc = cvectors.columnLength();
		if(nr<=0 || nc <=0)
			return null;
		CVector v = _.cvector(cvectors.row(0));
		for(int r=1; r<nr; r++)
			for(int c=0; c<nc; c++)
				v._(c,(float)Math.min(v._float(c),cvectors.get(r,c)));
		return v;
	} // end getMinComponentVector()
	
	static CVector getMaxComponentVector(CMatrix cvectors) {
		if(cvectors == null)
			return null;
		int nr = cvectors.rowLength();
		int nc = cvectors.columnLength();
		if(nr<=0 || nc <=0)
			return null;
		CVector v = _.cvector(cvectors.row(0));
		for(int r=1; r<nr; r++)
			for(int c=0; c<nc; c++)
				v._(c,(float)Math.max(v._float(c),cvectors.get(r,c)));
		return v;
	} // end getMaxComponentVector()
	
	static CVector getRandomVectorFromRange(CVector vmin, CVector vmax) {
		if(vmin == null || vmax == null)
			return null;
		int l = vmin.length();
		CVector vrand = _.cvector(l);
		for(int i=0; i<l; i++)
			vrand._(i, cmath.get_random_value_from_range(vmin._float(i), vmax._float(i)));
		return vrand;
	} // end getRandomVectorFromRange()

	static CMatrix getRandomVectorsFromRange(int n, CVector vmin, CVector vmax) {
		if(n < 1 || vmin == null || vmax == null)
			return null;
		int l = vmin.length();
		CMatrix vrands = _.cmatrix();
		for(int i=0; i<n; i++)
			vrands.push(CVector.getRandomVectorFromRange(vmin, vmax));
		return vrands;
	} // end getRandomVectorsFromRange()
	
	static boolean areVectorsWithinRange(CMatrix cvectors, CVector vmin, CVector vmax) {
		if(cvectors == null)
			return false;
		int l=cvectors.length();
		for(int i=0; i<l; i++) {
			CVector v = (CVector)cvectors._(i);
			if(!v.isWithinRange(vmin, vmax))
				return false;
		} // end for
		return true;
	} // end areVectorsWithinRange()	
} // end CVector