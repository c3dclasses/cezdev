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
import java.io.*;
import java.util.*;


//---------------------------------------------------------------------------------
// name: CMatrix
// desc: 
//---------------------------------------------------------------------------------
public class CMatrix extends CArray {	
	
	///////////////////////
	// constructors
	public CMatrix() { super(); } 
	public CMatrix(CMatrix cmatrix) {} 
	public CMatrix(double [][] m) {	
		for(int i=0; i<m.length; i++)
			this.push(new CVector(m[i]));
	} // end CMatrix()
	public CMatrix(int nr, int nc) { 
		for(int i=0; i<nr; i++)
			this.push(new CVector(nc));
	} // end CMatrix()
	public CMatrix(CVector... cvectors) { 
		for(int i=0; i<cvectors.length; i++)
			this.push(cvectors[i]);
	} // end CMatrix()
	public CMatrix(CVector cvector, int nrows, int ncols) { 
		this(nrows, ncols);
		if(nrows*ncols != cvector.length())
			return;
		for(int r=0, i=0; r<nrows; r++)
			for(int c=0; c<ncols; c++, i++)
				this.ij(r,c,cvector.i(i));
	} // end CMatrix()
	
	////////////////////////
	// checking
	public int rowLength() { return this.length(); }
	public int columnLength() { CVector v = this.i(0); return (v != null) ? v.length() : 0; }
	public boolean rowValid(int irow) { return irow < this.rowLength() && irow > -1; }
	public boolean columnValid(int icolumn) { return icolumn < this.columnLength() && icolumn > -1; }
	public boolean rcValid(int irow, int icolumn) { return columnValid(icolumn) && rowValid(irow); }
	boolean validRowIndex(int irow) { int n = this.rowLength(); return (n > 0 && irow > -1 && irow < n); }
	boolean validColumnIndex(int icol) { int n = this.columnLength(); return (n > 0 && icol > -1 && icol < n); }
	
	
	////////////////////////
	// indexing
	public CVector row(int i) { return (CVector)this._(i); }
	public CVector column(int i) { 
		int l = this.rowLength();
		CVector cvector = new CVector(l);
		for(int r=0; r<l; r++)
			cvector.i(r, this.ij(r, i));
		return cvector;
	} // end column()
	public CVector i(int i) { return this.row(i); }
	public void i(int i, double v) { if(this.i(i) != null) this.i(i).set(v); }
	public void i0(double v) { this.i(0, v); }
	public void iN(double v) { this.i(this.rowLength()-1, v); }
	public CVector j(int i) { return this.column(i); } 
	public void j(int i, double v) { int l = this.rowLength(); for(int r=0; r<l; r++) this.ij(r, i, v); }
	public void j0(double v) { this.j(0, v); }
	public void jN(double v) { this.j(this.columnLength()-1, v); }
	public void set(int row, int column, double value) { this.row(row).i(column, value); }
	public double get(int row, int column) { return this.row(row).i(column);  }
	public void ij(int row, int column, double value) { this.set(row, column, value); }
	public void ij_plus(int row, int column, double value) { this.ij(row, column, this.ij(row, column)+value); }
	public void ij_times(int row, int column, double value) { this.ij(row, column, this.ij(row, column)*value); }
	public double ij(int row, int column) { return this.get(row, column);  }
	public void ij(double value) { for(int i=0; i<this.rowLength();i++) this.i(i,value); }
	
	public CVector diagonal() {
		if(!this.squared())
			return null;
		int nr = this.rowLength();
		CVector v = new CVector(nr);
		for(int i=0; i<nr; i++) 
			v.i(i, this.ij(i,i));
		return v;
	} // end diagonal()
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
	public void subMatrix(int srow, int scol, CMatrix mat) {
		if(mat == null || !this.rcValid(srow,scol))
			return;
		int erow = srow + mat.rowLength()-1;
		int ecol = scol + mat.columnLength()-1;
		if(!this.rcValid(erow,ecol)) 
			return;
		for(int i1=srow, i2=0; i1<erow+1; i1++, i2++)
			for(int j1=scol, j2=0; j1<ecol+1; j1++, j2++)
				this.ij(i1,j1,mat.ij(i2,j2));
		return;
	} // end subMatrix()
	public void subMatrixBlock(int iblock, CMatrix mat) {
		int bw = mat.rowLength();
		int nbpw = this.columnLength() / bw;
		int iblockrow = iblock % nbpw;
		int iblockcol = iblock / nbpw;
		this.subMatrix(iblockrow, iblockcol, mat);
	} // end subMatrixBlock()
	public void subMatrixBlock(int irowblock, int icolblock, CMatrix matBlock) { 
		this.subMatrix(matBlock.rowLength() * irowblock, matBlock.columnLength() * icolblock, matBlock); 
	} // end subMatrixBlock()
	public CMatrix copy() { return this.subMatrix(0,this.rowLength()-1, 0, this.columnLength()-1); }
	public void copy(CMatrix mat) { 
		int nr = mat.rowLength();		
		this.clear();
		for(int i=0; i<nr; i++)
			this.push(mat.i(i));
	} // end copy()
	public boolean swapRows(int irow1, int irow2, int ncols) {
		if(!this.validRowIndex(irow1) || !this.validRowIndex(irow2))
			return false;
		CVector row1 = this.i(irow1);
		CVector row2 = this.i(irow2);
		int l = row1.length();
		for(int i=0; i<ncols; i++) {
			double tmp = row1.i(i);
			row1.i(i, row2.i(i));
			row2.i(i, tmp);	
		} // end for
		return true;
	} // end swapRows()

	//////////////////////////////
	// statistics
	public CVector minRowComponents() {
		int nr = this.rowLength();
		int nc = this.columnLength();
		if(nr<=0 || nc <=0)
			return null;
		CVector v = new CVector(this.row(0));
		for(int r=1; r<nr; r++)
			for(int c=0; c<nc; c++)
				v.i(c,(double)Math.min(v.i(c), this.get(r,c)));
		return v;
	} // end minRowComponents()
	public CVector maxRowComponents() {
		int nr = this.rowLength();
		int nc = this.columnLength();
		if(nr<=0 || nc <=0)
			return null;
		CVector v = new CVector(this.row(0));
		for(int r=1; r<nr; r++)
			for(int c=0; c<nc; c++)
				v.i(c,(double)Math.max(v.i(c), this.get(r,c)));
		return v;
	} // end maxRowComponents()
	public CVector maxColumnComponents() {
		int nr = this.rowLength();
		int nc = this.columnLength();
		if(nr<=0 || nc <=0)
			return null;
		CVector v = new CVector(this.column(0));
		for(int c=1; c<nc; c++)
			for(int r=0; r<nr; r++)
				v.i(r,(double)Math.max(v.i(r), this.get(r,c)));
		return v;
	} // end maxRowComponents()
	public CVector minColumnComponents() {
		int nr = this.rowLength();
		int nc = this.columnLength();
		if(nr<=0 || nc <=0)
			return null;
		CVector v = new CVector(this.column(0));
		for(int c=1; c<nc; c++)
			for(int r=0; r<nr; r++)
				v.i(r,(double)Math.min(v.i(r), this.get(r,c)));
		return v;
	} // end minColumnComponents()
	public CVector toVector() {
		int nrows = this.rowLength();
		int ncols = this.columnLength();
		CVector v = new CVector(nrows*ncols);
		int i=0;
		for(int r=0; r<nrows; r++) {
			for(int c=0; c<ncols; c++) {
				v.i(i,this.ij(r,c));
				i++;
			} // end for
		} // end for
		return v;
	} // end toVector()
	public CVector packByRow() { 
		return null;
	} // end packByRow()
	public CVector packByColumn() { 
		return null;
	} // end packByColumn()
	
	//////////////////////////////////////////
	// equality, compatibility, initialized
	public boolean empty() { return this.columnLength()<1 && this.rowLength()<1; }
	public boolean compatible(CMatrix B) { return this.columnLength() == B.rowLength(); }
	public boolean compatible(CVector v) { return this.columnLength() == v.length(); }
	public boolean squared(){return this.rowLength() == this.columnLength(); }
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
	public boolean between(CVector vmin, CVector vmax) {
		int l=this.length();
		for(int i=0; i<l; i++) {
			CVector v = (CVector)this.row(i);
			if(!v.between(vmin, vmax))
				return false;
		} // end for
		return true;
	} // end between()
	public boolean singular() { return this.determinant() == 0; }
	public boolean degenerate() { return this.determinant() == 0; }
	public int rank() {
		int C = this.columnLength();
		int R = this.rowLength();
		CMatrix M = this.copy();
		int rank = C;
		for (int row = 0; row < rank; row++) {
			// Before we visit current row 'row', we make
			// sure that mat[row][0],....mat[row][row-1] are 0.
			// Diagonal element is not zero
			if (M.ij(row,row) != 0) {
				for (int col = 0; col < R; col++) {
					if (col != row) {
						// This makes all entries of current
						// column as 0 except entry 'mat[row][row]'
						double mult = (double)M.ij(col,row) / M.ij(row,row);
						for (int i = 0; i < rank; i++) {			
							M.ij_plus(col,i,-mult*M.ij(row,i));
						} // end for
					} // end if
				} // end for 
			} // end if
			// Diagonal element is already zero. Two cases
			// arise:
			// 1) If there is a row below it with non-zero
			//    entry, then swap this row with that row
			//    and process that row
			// 2) If all elements in current column below
			//    mat[r][row] are 0, then remvoe this column
			//    by swapping it with last column and
			//    reducing number of columns by 1.
			else {
				boolean reduce = true;
				/* Find the non-zero element in current
					column  */
				for (int i = row + 1; i < R;  i++) {
					// Swap the row with non-zero element
					// with this row.
					if (M.ij(i,row) != 0) {
						this.swapRows(row, i, rank);
						reduce = false;
						break ;
					} // end if
				} // end for 
				// If we did not find any row with non-zero
				// element in current column, then all
				// values in this column are 0.
				if (reduce) {
					// Reduce number of columns
					rank--;
					// Copy the last column here
					for (int i = 0; i < R; i ++)
						M.ij(i,row,M.ij(i,rank));
				} // end if
				// Process this row again
				row--;
			} // end else
		} // end for()
		return rank;
	} // end rank()
	
	///////////////////////////////
	// operations
	public CMatrix times(double b) { return multiply(b); }
	public CMatrix multiply(double b) {
		int nr = this.rowLength();
		CMatrix B = new CMatrix();
		for(int r=0; r<nr; r++)
			B.push(this.i(r).multiply(b));
		return B;
	} // end multiply()
	public CMatrix times(CVector v) { return multiply(v); }
	public CMatrix multiply(CVector v) {
		if(!this.compatible(v))
			return new CMatrix();
		int nr = this.rowLength();
		CMatrix B = new CMatrix(nr,1);
		for(int r=0; r<nr; r++)
			B.ij(r,0,this.i(r).multiply(v));
		return B;
	} // end multiply()
	public CMatrix times(CMatrix B) { return multiply(B); }
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
	} // end multiplyByColumn()
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
	public double determinant() { 
		return this.determinant(this.rowLength()); 
	} // end determinant
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
	public CMatrix covariance() { 
		return this.transpose().multiply(this).multiply(1/(double)this.length()); 
	} // end covariance
	public CArray svd() {
		int nr = this.rowLength();
		int nc = this.rowLength();
		double [][] m = new double [nr][nc];  
		for(int i=0; i<nr; i++)
			for(int j=0; j<nr; j++) 
				m[i][j] = this.ij(i,j);
		Matrix Mj = new Matrix(m);		
		SingularValueDecomposition s = Mj.svd();
		Matrix Uj = s.getU();
		Matrix Sj = s.getS();
		Matrix Vj = s.getV();
		return _.args(
			new CMatrix(Uj.getArray()),
			new CMatrix(Sj.getArray()),
			new CMatrix(Vj.getArray())
		); // end _.args()	
	} // end svd()
	
	//////////////////////////////////////////
	// computations for rows and columns
	
	// computes the closest row for a given vector
	
	public int closest_i_idx(CVector cvector) {
		int nrows = this.rowLength();
		if(cvector.length() != this.columnLength() && nrows < 1)
			return -1;
		int closest_index = 0;
		double closest_dist = this.i(0).euclideanDistance(cvector);
		for(int i=1; i<nrows; i++) {
			double dist = this.i(i).euclideanDistance(cvector);
			if(dist < closest_dist) {
				closest_dist = dist;
				closest_index = i;
			} // end if		
		} // end for
		return closest_index;
	} // end closest_i_idx()

	public CArray closestRowsIndexesToMatrix(CMatrix mat) {
		int nrows = mat.rowLength();
		CArray closestRowIndexes = new CArray();
		for(int i=0; i<nrows; i++)
			closestRowIndexes.push(this.closest_i_idx(mat.i(i)));
		return closestRowIndexes;
	} // end closestRowsIndexesToMatrix()
	
	public CVector closest_i(CVector cvector) {
		int nrows = this.rowLength();
		if(cvector.length() != this.columnLength() && nrows < 1)
			return null;
		int closest_index = 0;
		double closest_dist = this.i(0).euclideanDistance(cvector);
		for(int i=1; i<nrows; i++) {
			double dist = this.i(i).euclideanDistance(cvector);
			if(dist < closest_dist) {
				closest_dist = dist;
				closest_index = i;
			} // end if		
		} // end for
		return this.i(closest_index).copy();
	} // end closest_i()
	
	public CMatrix closestRowsToMatrix(CMatrix mat) {
		if(this.columnLength() != mat.columnLength())
			return null;
		int nrows = mat.rowLength();
		CMatrix closestRows = new CMatrix();
		for(int i=0; i<nrows; i++)
			closestRows.push(this.closest_i(mat.i(i)));
		return closestRows;
	} // end closestRowsToMatrix()
	
	///////////////////////
	// 
	public CMatrix subMatrixGrid(int nxvectors, int nyvectors) {
		CMatrix mat = new CMatrix();
		CVector min = this.minRowComponents();
		CVector max = this.maxRowComponents();
		CVector delta_x = max.minus(min).divide((double)(nxvectors-1));
		CVector delta_y = max.minus(min).divide((double)(nyvectors-1));
		for(int i=0; i<nxvectors; i++) {
			for(int j=0; j<nyvectors; j++) {
				CVector v = new CVector(2);
				v.i(0,min.i(0)+delta_x.i(0)*i);
				v.i(1,min.i(1)+delta_y.i(1)*j);
				mat.push(v);
			} // end for
		} // end for
		return mat;
	} // end subMatrixGrid()
	
	// AddByColumn()
	// multiplyByColumn()
	// AddByRow()
	// multiplyByRow()
	// addByComponent()
	// multiplyByComponent()
	// norm1()
	// norm2()
	// normInf()
	// normF()
	// minus()
	// plusEquals()
	// timesEquals()
	// minusEquals()
	// divideEquals()

	public void appendRows(CMatrix matRows) {
		if(matRows == null || (this.columnLength() != 0 && 
			this.columnLength() != matRows.columnLength()))
			return;
		int l = matRows.rowLength();
		for(int i=0; i<l; i++)
			this.push(matRows.i(i));
		return;
	} // end appendRows()
	public void appendColumns(CMatrix matCols) { 
		CMatrix M = this.transpose(); 
		M.appendRows(matCols); 
		this.copy(M.transpose()); 
	} // end appendColumns()
	public void appendColumn(double value) { 		
		CMatrix M = this.transpose(); 
		M.push(new CVector(this.rowLength()));
		M.iN(value);
		this.copy(M.transpose());
	} // end appendColumn()
	
	////////////////////////////
	// toString(), toJSON()
	public String toJSON() { return this.toJSON(true);}
	public String toJSON(boolean bpack) {return "";}
	public String toString() { return this.toString(12,6); }
	public String toString(int w, int d) {
		DecimalFormat format = new DecimalFormat();
		format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		format.setMinimumIntegerDigits(1);
		format.setMaximumFractionDigits(d);
		format.setMinimumFractionDigits(d);
		format.setGroupingUsed(false);	
		String str = "";
		int nr = Math.min(this.rowLength(), 50);
		int nc = Math.min(this.columnLength(), 50);
		for (int i = 0; i < nr; i++) {
			for (int j = 0; j < nc; j++) {
				String s = format.format(this.ij(i,j)); // format the number
				int padding = Math.max(1,w-s.length()); // At _least_ 1 space
				for (int k = 0; k < padding; k++)
					str += ' ';
				str += s;
			} // end for
			if(this.columnLength() > 50) {
				int padding = Math.max(1,w-4); // At _least_ 1 space
				for (int k = 0; k < padding; k++)
					str += ' ';
				str += "...";
			} // end if
			str += "\n";
		} // end for
		if(this.rowLength() > 50)
			str += "...";
		return str;
	} // end toString()
	public boolean toFile(String strfilename) {
		try {
			if(strfilename == null || strfilename == "")
				return false;
			FileWriter out = new FileWriter(strfilename);
			int nr = this.rowLength();
			int nc = this.columnLength();
			for(int i=0; i<nr; i++) { 
				for(int j=0; j<nc; j++) { 
					out.write(""+this.ij(i,j));
					if(j < nc-1)
						out.write(",");
				} // end for
				out.write("\n");
			} // end for
			out.close();
		} // end try
		catch(Exception ex) {
			return false;
		} // end catch()	
		return true;	
	} // end toFile()
	public boolean toImageFile(String strimgfilename, String strformat) { return _.save_img_file(strimgfilename, strformat, this); }
	
	//////////////////
	// static 
	public static CMatrix identity(int n) {
		CMatrix I = new CMatrix(n,n);
		for(int i=0; i<n; i++)
			I.ij(i,i,1);
		return I;
	} // end I()
	public static CMatrix rand(int nrows, CVector vmin, CVector vmax) {
		if(nrows < 1 || vmin == null || vmax == null || vmin.length() <= 0 || vmax.length() <= 0)
			return null;
		CMatrix A = new CMatrix();
		for(int i=0; i<nrows; i++)
			A.push(CVector.rand(vmin, vmax));
		return A;
	} // end rand()
	public static CMatrix rand(int nrows, int ncols) {
		CMatrix A = new CMatrix();
		for(int i=0; i<nrows; i++)
			A.push(CVector.rand(ncols));
		return A;
	} // end rand()
} // end CMatrix