//---------------------------------------------------------------------------------
// file: CMatrix
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import Jama.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.text.FieldPosition;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.StreamTokenizer;


//---------------------------------------------------------------------------------
// name: CMatrix
// desc: 
//---------------------------------------------------------------------------------
public class CMatrix extends CArray {	
	public CMatrix() { super(); } 
	public CMatrix(CMatrix cmatrix) { } 
	public CMatrix(double [][] m) {	
		for(int i=0; i<m.length; i++)
			this.push(new CVector(m[i]));
	} // end 
	public CMatrix(int nr, int nc) { 
		for(int i=0; i<nr; i++)
			this.push(new CVector(nc));
	} // end CMatrix()
	public CMatrix(CVector... cvectors) { 
		for(int i=0; i<cvectors.length; i++)
			this.push(cvectors[i]);
	} // end CMatrix()
	public int rowLength() { return this.length(); }
	public int columnLength() { return this.i(0).length(); }
	public CVector row(int i) { return (CVector)this._(i); }
	public CVector column(int i) { 
		int l = this.rowLength();
		CVector cvector = new CVector(l);
		for(int r=0; r<l; r++)
			cvector.i(r, this.ij(r, i));
		return cvector;
	} // end column()
	public CVector diagonal() {
		if(!this.issquared())
			return null;
		int nr = this.rowLength();
		CVector v = new CVector(nr);
		for(int i=0; i<nr; i++) 
			v.i(i, this.ij(i,i));
		return v;
	} // end diagonal()
	public CVector i(int i) { return this.row(i); }
	public CVector j(int i) { return this.column(i); } 
	public void set(int row, int column, double value) { this.row(row).i(column, value); }
	public double get(int row, int column) { return this.row(row).i(column);  }
	public void ij(int row, int column, double value) { this.set(row, column, value); }
	public double ij(int row, int column) { return this.get(row, column);  }
	public boolean compatible(CMatrix B) { return this.columnLength() == B.rowLength(); }
	public boolean compatible(CVector v) { return this.columnLength() == v.length(); }
	public boolean issquared(){return this.rowLength() == this.columnLength(); }
	public boolean equals(CMatrix B) { return this.equalComponents(B); }
	public boolean equalDimensions(CMatrix B) { 
		return (B != null && this.rowLength() == B.rowLength() && 
			this.columnLength() == B.columnLength()) 
			? true : false;
	} // end equalDimensions()
	public boolean equalComponents(CMatrix B) {
		if(!this.equalDimensions(B))
			return false;
		int nr = this.rowLength();
		for(int r=0; r<nr; r++)
			if(!this.i(r).equalComponents(B.i(r)))
				return false;
		return true;
	} // end equalComponents()
	public CMatrix multiply(double b) {
		int nr = this.rowLength();
		CMatrix B = new CMatrix();
		for(int r=0; r<nr; r++)
			B.push(this.i(r).multiply(b));
		return B;
	} // end multiply()
	public CMatrix multiply(CVector v) {
		if(!this.compatible(v))
			return new CMatrix();
		int nr = this.rowLength();
		CMatrix B = new CMatrix(nr,1);
		for(int r=0; r<nr; r++)
			B.ij(r,0,this.i(r).multiply(v));
		return B;
	} // end multiply()
	public CMatrix multiply(CMatrix B) {
		if(!this.compatible(B))
			return new CMatrix();
		int nr = this.rowLength();
		int nc = B.columnLength();
		int inc = this.columnLength();
		CMatrix O = new CMatrix();
		for(int r=0; r<nr; r++) {
			CVector v = new CVector(nc);
			for(int c=0; c<nc; c++) {
				double sum = 0;
				for(int i=0; i<inc; i++)
					sum += this.ij(r,i) * B.ij(i,c);// * 100000)/100000;
				v.i(c, cmath.zero(sum));
			} // end for
			O.push(v);
		} // end for
		return O;
	} // end multiply()
	public CMatrix add(CMatrix B) {
		if(!this.equalDimensions(B))
			return new CMatrix();
		int nr = this.rowLength();
		CMatrix C = new CMatrix();
		for(int r=0; r<nr; r++)
			C.push(this.i(r).add(B.i(r)));	
		return C;
	} // end add()
	
	public CMatrix addByColumn(CVector v) {
		int nr = this.rowLength();
		int nc = this.columnLength();
		if(v == null || v.length() != nc)
			return new CMatrix();
		CMatrix C = new CMatrix(nr,nc);
		for(int r=0; r<nr; r++)
			for(int c=0; c<nc; c++)
				C.ij(r,c,(v.i(c)+this.ij(r,c)));	
		return C;
	} // end addByColumn()
	
	public CMatrix multiplyByColumn(CVector v) {
		int nr = this.rowLength();
		int nc = this.columnLength();
		if(v == null || v.length() != nc)
			return new CMatrix();
		CMatrix C = new CMatrix(nr,nc);
		for(int r=0; r<nr; r++)
			for(int c=0; c<nc; c++) {
				C.ij(r,c,(v.i(c)*this.ij(r,c)));
			}				
		return C;
	} // end addByColumn()
	
	public CMatrix addByRow(CVector v) {
		int nr = this.rowLength();
		int nc = this.columnLength();
		if(v == null || v.length() != nr)
			return new CMatrix();
		CMatrix C = new CMatrix(nr,nc);
		for(int r=0; r<nr; r++)
			for(int c=0; c<nc; c++)
				C.ij(r,c,(v.i(r)+this.ij(r,c)));	
		return C;
	} // end addByRow()
	
	public CMatrix subRowMatrix(int srow, int erow) { return this.subMatrix(srow, erow, 0, this.columnLength()-1); }
	public CMatrix subColumnMatrix(int scol, int ecol) { return this.subMatrix(0, this.rowLength()-1, scol, ecol); }
	public CMatrix subMatrix(int srow, int erow, int scol, int ecol) {
		int nr = this.rowLength();
		if( nr < 1 || srow < 0 || srow >= nr || erow < 0 || erow >= nr || (erow - srow) + 1 < 1)
			return new CMatrix();
		CMatrix M = new CMatrix();
		for(int i=srow; i<erow+1; i++)
			M.push(this.i(i).subVertex(scol, ecol));		
		return M;
	} // end subMatrix()
	
	public static CMatrix I(int n) {
		CMatrix I = new CMatrix(n,n);
		for(int i=0; i<n; i++)
			I.ij(i,i,1);
		return I;
	} // end I()
	
	public double determinant() { return this.determinant(this.rowLength()); }
    public double determinant(int n) {
		int D = 0;
		if (n == 1)
            return this.ij(0,0); 
        int sign = 1; 
        for (int cf = 0; cf < n; cf++) {
            CMatrix M_cf = this.cofactor(cf, 0);
            D += sign * this.ij(0,cf) * M_cf.determinant(n-1);
            sign = -sign;
        } // end for
        return D;
    } // end determinant()
	public CMatrix cofactor(int cf, int r) {
        int i = 0, j = 0, p = r, q = cf;
		int n = this.rowLength();
        CMatrix M_cf = new CMatrix(n,n);
		for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    M_cf.set(i,j++,this.get(row,col));
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    } // end if
                } // end if
            } // end for
        } // end for
		return M_cf;
    } // end cofactor()
	public CMatrix adjugate() {
		int n = this.rowLength();
		if (n == 1) {
			CMatrix m = new CMatrix();
			m.push(new CVector(1));		
			return m;
		} // end if
		double sign = 1;
		CMatrix Adj = new CMatrix(n,n);
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				CMatrix M_cf = this.cofactor(i, j);
				sign = ((i+j)%2==0) ? 1 : -1;
				Adj.ij(j,i,(sign)*(M_cf.determinant(n-1)));
			} // end for
		} // end for
		return Adj.transpose();
    } // adjugate()
	public CMatrix transpose() {
		int nc = this.columnLength();
		int nr = this.rowLength();
		CMatrix o = new CMatrix(nc,nr);
		for(int r=0; r<nr; r++)
			for(int c=0; c<nc; c++)
				o.set(c,r, this.get(r,c));
		return o;
	} // end transpose()
	boolean isSingular() { return this.determinant() == 0; }
	boolean isDegenerate() { return this.determinant() == 0; }
	public CMatrix inverse() {
		double det = this.determinant();
		if (det == 0)
			return new CMatrix(); // this is a singular 
		int n = this.rowLength();
		CMatrix Adj = this.adjugate();
		CMatrix Inv = new CMatrix(n,n);
		// Find Inverse using formula ""
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++)
				Inv.set(i,j, Adj.get(i,j)/det); // inverse(A) = adj(A)/det(A)
		return Inv;
	} // inverse()
	
	public CMatrix covariance() { return this.transpose().multiply(this).multiply(1/(double)this.length()); }
	
	public CArray svd() {
		int nr = this.rowLength();
		int nc = this.rowLength();
		double [][] m = new double [nr][nc];  
		for(int i=0; i<nr; i++)
			for(int j=0; j<nr; j++) 
				m[i][j] = this.ij(i,j);
		Matrix Mj = new Matrix(m);	
		
	
		System.out.println("AA = U S V^T");
		System.out.println();
		SingularValueDecomposition s = Mj.svd();
		System.out.print("U = ");
		Matrix U = s.getU();
		U.print(9, 6);
		System.out.print("Sigma = ");
		Matrix S = s.getS();
		S.print(9, 6);
		System.out.print("V = ");
		Matrix V = s.getV();
		V.print(9, 6);
		System.out.println("rank = " + s.rank());
		System.out.println("condition number = " + s.cond());
		System.out.println("2-norm = " + s.norm2());
		// print out singular values
		System.out.print("singular values = ");
		Matrix svalues = new Matrix(s.getSingularValues(), 1);
		
		Matrix Uj = s.getU();
		Matrix Sj = s.getS();
		Matrix Vj = s.getV();
		
		
		return _.args(
			new CMatrix(Uj.getArray()),
			new CMatrix(Sj.getArray()),
			new CMatrix(Vj.getArray())
		); // end _.args()	
	} // end svd()

	public String toString() { return this.toString(12,6); }
	public String toString(int w, int d) {
		DecimalFormat format = new DecimalFormat();
		format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		format.setMinimumIntegerDigits(1);
		format.setMaximumFractionDigits(d);
		format.setMinimumFractionDigits(d);
		format.setGroupingUsed(false);	
		String str = "";
		int nr = this.rowLength();
		int nc = this.columnLength();
		for (int i = 0; i < nr; i++) {
			for (int j = 0; j < nc; j++) {
				String s = format.format(this.ij(i,j)); // format the number
				int padding = Math.max(1,w-s.length()); // At _least_ 1 space
				for (int k = 0; k < padding; k++)
					str += ' ';
				str += s;
			} // end for
			str += "\n";
		} // end for
		return str;
	} // end toString()
} // end CMatrix