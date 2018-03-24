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
	public CMatrix() { super(); } 
	
	public CMatrix(CMatrix cmatrix) { super(cmatrix); } 
	
	public CMatrix(int rows, int cols) { super(new int [] {rows, cols}); } 
	
	public CMatrix(Object [] objects) { super(objects); } 
	
	public int rowLength() { return this.length(); }
	
	public int columnLength() { return this._cvector(0).length(); }
	
	public CVector row(int i) { return this._cvector(i); }
	
	public CVector column(int i) { 
		CVector cvector = _.cvector(this.columnLength());
		int l = this.rowLength();
		for(int r=0; r<l; r++)
			cvector._(r, this._cvector(r)._(i));
		return cvector;
	} // end column()
	
	public void set(int row, int column, float value) { this._cvector(row)._(column, value); }
	
	public float get(int row, int column) { return this._cvector(row)._float(column);  }
	
	public boolean compatible(CMatrix B) { return this.columnLength() == B.rowLength(); }
	
	public boolean equalDimensions(CMatrix B) {
		if(B == null)
			return false;
		int nr = this.rowLength();
		if(nr > 0 && nr != B.rowLength())
			return false;
		int nc = this.columnLength();
		if(nc > 0 && nc != B.columnLength())
			return false;
		return true;
	} // end equalDimensions()
	
	public CMatrix multiply(float b) {
		int nr = this.rowLength();
		int nc = this.columnLength();
		CMatrix B = _.cmatrix(nr,nc);
		for(int r=0; r<nr; r++)
			for(int c=0; c<nc; c++)
				B.set(r,c,(this.get(r,c)*b));
		return B;
	} // end multiply()
	
	public CMatrix add(CMatrix B) {
		if(!this.equalDimensions(B))
			return null;
		int nr = this.rowLength();
		int nc = this.columnLength();
		CMatrix C = _.cmatrix(nr,nc);
		for(int r=0; r<nr; r++)
			for(int c=0; c<nc; c++)
				C.set(r,c,(this.get(r,c) + B.get(r,c)));
		return C;
	} // end add()
} // end CMatrix