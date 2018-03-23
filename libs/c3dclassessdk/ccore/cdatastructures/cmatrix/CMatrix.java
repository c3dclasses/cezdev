//---------------------------------------------------------------------------------
// file: CMatrix
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: CMatrix
// desc: 
//---------------------------------------------------------------------------------
public class CMatrix extends CArray {	
	boolean compatible(CArray B) {
	} // end compatible()
	
	boolean equalDimensions(CArray B) {
		if(B == null)
			return false;
		int nr = this.length();
		if(nr > 0 && nr != B.length())
			return false;
		int nc = this._carray(0).length();
		if(nc > 0 && nc != B._carray(0).length())
			return false;
		return true;
	} // end equalDimensions()
	
	CMatrix multiply(float b) {
		int nr = this.length();
		int nc = this._cmatrix(0).length();
		CArray B = _.carray_c(nr,nc);
		for(int r=0; r<nr; r++) {
			for(int c=0; c<nc; c++) {
				int sum = A._carray(r)._float(c) * b
				B._carray(r)._(c, sum);
			} // end for
		} // end for
		return B;
	} // end multiply_matrix()
	
	CMatrix add(CMatrix B) {
		if(!this.equalDimensions(B))
			return null;
		int nr = this.length();
		int nc = this._carray(0).length();
		CMatrix C = _.cmatrix_c(nr,nc);
		for(int r=0; r<nr; r++) {
			for(int c=0; c<nc; c++) {
				int sum = this._carray(r)._float(c) + B._carray(r)._float(c)
				C._carray(r)._(c, sum);
			} // end for
		} // end for
		return C;
	} // end add()
} // end CMatrix