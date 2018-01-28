//--------------------------------------------------
// file: cmatrixchainmultiplication
// desc:
//--------------------------------------------------
package c3dclasses;
import java.util.*;

//--------------------------------------------------------
// file: cmatrixchainmultiplication
// desc:
//--------------------------------------------------------
public class cmatrixchainmultiplication {
	// Matrix Ai has dimension p[i-1] x p[i] for i = 1..n
    static int _rec(int p[], int i, int j) {
        if (i == j)
            return 0;
        int min = Integer.MAX_VALUE;
        // place parenthesis at different places between first
        // and last matrix, recursively calculate count of
        // multiplications for each parenthesis placement and
        // return the minimum count
        for (int k=i; k<j; k++) {
            int count = _rec(p, i, k) +
                        _rec(p, k+1, j) +
                        p[i-1]*p[k]*p[j];
            if (count < min)
                min = count;
        } // end for 
        // Return minimum count
        return min;
    } // end MatrixChainOrder()
	
	public static void main(String[] args) {
	} // end main()
} // end clcs

