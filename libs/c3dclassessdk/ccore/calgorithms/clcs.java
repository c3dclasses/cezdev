//--------------------------------------------------
// file: clcs
// desc: longest common subsequence
//--------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// file: clcs
// desc: longest common subsequence
//--------------------------------------------------------
public class clcs {
	// memeize 
	public static int _mm(String X, String Y) { 
		int L[][] = new int[X.length()+1][Y.length()+1]; 
		for(int x=0; x<X.length(); x++)
			for(int y=0; y<Y.length(); y++)
				L[x][y] = -1;
		return clcs._mm(X, Y, X.length(), Y.length(), L); 
	} // end _mm()
	public static int _mm(String X, String Y, int m, int n, int [][] L) {
		if(L[m][n] != -1)
			return L[m][n];
		int count;
		if(m == 0 && n == 0)
			count = 0;
   		else if (X.charAt(m-1) == Y.charAt(n-1))
     		count = 1 + clcs._mm(X, Y, m-1, n-1, L);
   		else count = Math.max(clcs._mm(X, Y, m, n-1, L), clcs._mm(X, Y, m-1, n, L));
		L[m][n] = count;
		return L[m][n];
	} // end _mm()
	
	// recursive 
	public static int _rc(String X, String Y) { return clcs._rc(X, Y, X.length(), Y.length()); } 
	public static int _rc(String X, String Y, int m, int n) {
		if(m == 0 && n == 0)
			return 0;
   		if (X.charAt(m-1) == Y.charAt(n-1))
     		return 1 + clcs._rc(X, Y, m-1, n-1);
   		else return Math.max(clcs._rc(X, Y, m, n-1), clcs._rc(X, Y, m-1, n));
	} // end _rc()
	
	// dynamic programming
	public static int _dp(String X, String Y) { return clcs._dp(X, Y, X.length(), Y.length()); } 
	public static int _dp(String X, String Y, int m, int n) {
	   	int L[][] = new int[m+1][n+1];
    	/* Following steps build L[m+1][n+1] in bottom up fashion. Note
        	 that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
    	for (int i=0; i<=m; i++) {
      		for (int j=0; j<=n; j++) {
        		if (i == 0 || j == 0)
            		L[i][j] = 0;
        		else if (X.charAt(i-1) == Y.charAt(j-1))
            		L[i][j] = L[i-1][j-1] + 1;
        		else
            	L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
      		} // end for 
    	} // end for
  		return L[m][n];
  	} // end _dp()
	
	// tester
	public static void main(String[] args) {
		_.print(clcs._mm("abcdef", "abcdef"));
		_.print(clcs._rc("abcdef", "abcdef"));
		_.print(clcs._dp("abcdef", "abcdef"));
	} // end main()
} // end clcs

/*
       lcs("AXYT", "AYZX")
                       /                 
         lcs("AXY", "AYZX")            lcs("AXYT", "AYZ")
         /                              /               
lcs("AX", "AYZX") lcs("AXY", "AYZ")   lcs("AXY", "AYZ") lcs("AXYT", "AY")
*/