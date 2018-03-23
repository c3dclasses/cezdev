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
	
	float magnitude() {
		float sum = 0.0;
		int l = this.length();
		for(int i=0; i<l; i++)
			sum += this._float(i) * this._float(i);
		return (float)Math.sqrt(sum);
	} // end magnitude()
	
	CVector normalize() {
		int l = this.length();
		float m = this.magnitude();
		CVector n = _.cvector_c(l);
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
		if(v == null)
			return false;
		int l = this.length();
		for(int i=0; i<l; i++)
			if(!cmath.equal(this._float(i), v._float(i)))
				return false;
		return true;
	} // end equal_components()

	boolean equalMagnitude(CArray v) {
		return cmath.equal(this.magnitude(),v.magnitude());
	} // end equal_magnitude()
	
	float euclideanDistance(CVector v) {
		float result = 0;
		int l = this.length();
		for(int i=0; i<l; i++)
			result += Math.pow((this._float(i)-v._float(i)), 2);
		return (float)Math.sqrt(result);
	} // end euclidean_distance()
	
	boolean isWithinRange(CArray vmin, CArray vmax) {
		if(vmin == null || vmax == null)
			return false;
		int l=this.length();
		for(int i=0; i<l; i++) {
			if(cmath.is_value_within_range(this._float(i), vmin._float(i), vmax._float(i)))
				return false;
		} // end for
		return true;
	} // end isWithinVectorRange()
	
	////////////////////
	// static methods
	static CVector getMinComponentVector(CArray cvectors) {
		if(vectors == null)
			return null;
		int l1 = vectors.length();
		if(l1<=0)
			return null;
		CVector v1 = vectors._cvector(0).copy();
		for(int i1=1; i1<l1; i1++) {
			CVector v2 = vectors._cvector(i1);
			int l2 = v2.length();
			for(int i2=0; i2<l2; i2++) {
				v1._(i2,(float)Math.min(v1._float(i2),v2._float(i2)));
			} // end for
		} // end for	
		return v1;
	} // end getMinComponentVector()

	static CVector getMaxComponentVector(CArray cvectors) {
		if(vectors == null)
			return null;
		int l1 = vectors.length();
		if(l1<=0)
			return null;
		CVector v1 = vectors._cvector(0).copy();
		for(int i1=1; i1<l1; i1++) {
			CVector v2 = vectors._cvector(i1);
			int l2 = v2.length();
			for(int i2=0; i2<l2; i2++) {
				v1._(i2,(float)Math.max(v1._float(i2),v2._float(i2)));
			} // end for
		} // end for	
		return v1;
	} // end getMaxComponentVector()

	static CVector getRandomVectorFromRange(CVector vmin, CVector vmax) {
		if(vmin == null || vmax == null)
			return null;
		int l = vmin.length();
		CVector vrand = _.cvector_c(l);
		for(int i=0; i<l; i++)
			vrand._(i, cmath.get_random_value_from_range(vmin._float(i)+1, vmax._float(i)-1));
		return vrand;
	} // end getRandomVectorFromRange()
	
	static CArray getRandomVectorsFromRange(int n, CVector vmin, CVector vmax) {
		if(n < 1 || vmin == null || vmax == null)
			return null;
		int l = vmin.length();
		CArray vrands = _.carray();
		for(int i=0; i<n; i++)
			vrands.push(CVector.getRandomVectorFromRange(vmin, vmax));
		return vrands;
	} // end getRandomVectorsFromRange()

	static boolean areVectorsWithinRange(CArray vectors, CVector vmin, CVector vmax) {
		if(v == null)
			return false;
		int l=vectors.length();
		for(int i=0; i<l; i++) {
			CVector v = (CVector)vectors._(i);
			if(!v.isWithinRange(vmin, vmax))
				return false;
		} // end for
		return true;
	} // end isVectorsWithinVectorRange()	
} // end CVector