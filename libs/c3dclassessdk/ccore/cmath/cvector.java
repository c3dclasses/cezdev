//---------------------------------------------------------------------------------
// file: cla
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: cla
// desc: 
//---------------------------------------------------------------------------------
public class cla {	
	static float get_magnitude(CArray v) {
		if(v==null)
			return 0;
		float sum = 0;
		int l = v.length();
		for(int i=0; i<l; i++)
			if(!v._nan(i))
				sum += v._float(i) * v._float(i);
		return (float)Math.sqrt(sum);
	} // end get_magnitude()
	
	static CArray normalize(CArray v) {
		if(v==null)
			return null;
		int l = v.length();
		float m = cvector.get_magnitude(v);
		CArray n = _.carray_c(l);
		for(int i=0; i<l; i++)
			if(!v._nan(i))
				n._(i, v._float(i)/m);
		return n;
	} // end normalize()
	
	static boolean equals(CArray v1, CArray v2) { 
		CArray nv1 = cvector.normalize(v1);
		CArray nv2 = cvector.normalize(v1);
		return cvector.equal_magnitudes(v1,v2) && cvector.equal_components(nv1,nv2); 
	} // equal()
	
	static boolean equal_components(CArray v1, CArray v2) {
		if(v1 == null || v2 == null)
			return false;
		int l = v1.length();
		for(int i=0; i<l; i++)
			if(!v1._nan(i) && !cmath.equal(v1._float(i), v2._float(i)))
				return false;
		return true;
	} // end equal_components()

	static boolean equal_magnitudes(CArray v1, CArray v2) {
		return cmath.equal(get_magnitude(v1),get_magnitude(v2));
	} // end equal_magnitudes()
	
	static float get_euclidean_distance(CArray v1, CArray v2) {
		float result = 0;
		int l = v1.length();
		for(int i=0; i<l; i++)
			if(!v1._nan(i))
				result += Math.pow((v1._float(i)-v2._float(i)), 2);
		return (float)Math.sqrt(result);
	} // end get_euclid_distance()
	 
	static CArray get_min_components(CArray vectors) {
		if(vectors == null)
			return null;
		int l1 = vectors.length();
		if(l1<=0)
			return null;
		CArray x1 = vectors._carray(0).copy();
		for(int i1=1; i1<l1; i1++) {
			CArray x2 = vectors._carray(i1);
			int l2 = x2.length();
			for(int i2=0; i2<l2; i2++) {
				if(!x2._nan(i2))
					x1._(i2,(float)Math.min(x1._float(i2),x2._float(i2)));
			} // end for
		} // end for	
		return x1;
	} // end get_min_components()
	
	static CArray get_max_components(CArray vectors) {
		if(vectors == null)
			return null;
		int l1 = vectors.length();
		if(l1<=0)
			return null;
		CArray x1 = vectors._carray(0).copy();
		for(int i1=1; i1<l1; i1++) {
			CArray x2 = vectors._carray(i1);
			int l2 = x2.length();
			for(int i2=0; i2<l2; i2++) {
				if(!x2._nan(i2))
					x1._(i2,(float)Math.max(x1._float(i2),x2._float(i2)));
			} // end for
		} // end for	
		return x1;
	} // end get_max_components()
	
	static CArray get_random_vector_from_range(CArray vmin, CArray vmax) {
		if(vmin == null || vmax == null)
			return null;
		int l = vmin.length();
		CArray vrand = _.carray_c(l);
		for(int i=0; i<l; i++) {
			if(!vmin._nan(i))
				vrand._(i, cmath.get_random_value_from_range(vmin._float(i)+1, vmax._float(i)-1));
		} // end for
		return vrand;
	} // end get_rand_from_range()
	
	static CArray get_random_vectors_from_range(int n, CArray vmin, CArray vmax) {
		if(n < 1 || vmin == null || vmax == null)
			return null;
		int l = vmin.length();
		CArray vrands = _.carray();
		for(int i=0; i<n; i++)
			vrands.push(cvector.get_random_vector_from_range(vmin, vmax));
		return vrands;
	} // end get_rand_vectors_from_range()
	
	static boolean is_vector_within_vector_range(CArray v, CArray vmin, CArray vmax) {
		if(v == null || vmin == null || vmax == null)
			return false;
		int l=v.length();
		for(int i=0; i<l; i++) {
			if(!v._nan()) 
				if(cmath.is_value_within_range(v._float(i), vmin._float(i), vmax._float(i)))
					return false;
		} // end for
		return true;
	} // end is_vector_within_vector_range()
	
	static boolean is_vectors_within_vector_range(CArray v, CArray vmin, CArray vmax) {
		if(v == null)
			return false;
		int l=v.length();
		for(int i=0; i<l; i++)
			if(!cvector.is_vector_within_vector_range(v._carray(i), vmin, vmax))
				return false;
		return true;
	} // end is_vectors_within_vector_range()
	
	
	//static float gradient_descent(float falpha) {
	//	float ftheta0
	//	float ftheta1
	//}
	
	static float cost_function(carray ts) {
		if(ts == null)
			return 0.0;
		int m = ts.length();
		float sum = 0;
		for(int i=0; i<m; i++) {			
			xi = ts._carray(i)._float(0);	// observation
			yi = ts._carray(i)._float(1);	// prediction
			sum += Math.pow((hypothesis(xi) - yi), 2)
		} // end for
		return sum/(2*m);
	} // end cost_function()
	
	
	
	/*
	static CArray max(CArray v1, CArray v2) {
		int l2 = x.length();
		for(int j=0; j<l2; j++) {
			if(x._nan(j))
				continue;				
			float _x = x._float(j);
			if(max._float(j) >= _x) 
				max._(j, _x);
		} // end for
	} // end max()
	*/
	
	/////////////////////
	// matrix
	
	static boolean matrix_compatible(CArray A, CArray B) {
		
	} // end matrix_compatible()
	
	static boolean are_matrix_dimensions_same(CArray A, CArray B) {
		if(A == null || B == null)
			return false;
		int nr = A.length();
		if(nr > 0 && nr != B.length())
			return false;
		int nc = A._carray(0).length();
		if(nc > 0 && nc != B._carray(0).length())
			return false;
		return true;
	} // end are_matrix_dimensions_same()
	
	static CArray multiply_matrix(CArray A, float b) {
		int nr = A.length();
		int nc = A._carray(0).length();
		CArray B = _.carray_c(nr,nc);
		for(int r=0; r<nr; r++) {
			for(int c=0; c<nc; c++) {
				int sum = A._carray(r)._float(c) * b
				B._carray(r)._(c, sum);
			} // end for
		} // end for
		return B;
	} // end multiply_matrix()
	
	static CArray add_matrix(CArray A, CArray B) {
		if(are_matrix_dimensions_same(A,B))
			return null;
		int nr = A.length();
		int nc = A._carray(0).length();
		CArray C = _.carray_c(nr,nc);
		for(int r=0; r<nr; r++) {
			for(int c=0; c<nc; c++) {
				int sum = A._carray(r)._float(c) + B._carray(r)._float(c)
				C._carray(r)._(c, sum);
			} // end for
		} // end for
		return C;
	} // end add_matrix()
	
} // end cvector