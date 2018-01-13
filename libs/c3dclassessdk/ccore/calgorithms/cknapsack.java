//--------------------------------------------------
// file: cknapsack
// desc: defines a huffman code object 
//--------------------------------------------------
package c3dclasses;
import java.util.*;

//-----------------------------------------------------------------------
// name: cknapsack
// desc: defines the knapsack algorithm
//-----------------------------------------------------------------------
public class cknapsack {
	public static int m_table[][] = null;
	
	public static int _0_1_rec(int weight, CArray items) {
		return cknapsack._0_1_rec(weight, items, items.length()-1);
	} // end _0_1_rec()
	
	public static int _0_1_rec(int weight, CArray items, int n) {
		if(weight <= 0 || items == null || n == 0)
			return 0;	
		CObject item = items._cobject(n);
		int item_weight = item._int("weight");
		int item_value = item._int("value");
		if(item_weight > weight)
			return cknapsack._0_1_rec(weight, items, n-1);
		return Math.max(item_value + cknapsack._0_1_rec(weight - item_weight, items, n-1), cknapsack._0_1_rec(weight, items, n-1));
	} // end _0_1_rec()
	
	public static int _0_1(int weight, CArray items) {	
		int n = items.length();
		int K[][] = new int [n+1][weight+1];
		for(int i=0; i <= n; i++) {		
			for(int w=0; w <= weight; w++) {
				if(i==0 || w==0)
					K[i][w] = 0;
				else if (items._cobject(i-1)._int("weight") <= w) {
					CObject item = items._cobject(i-1);
					int item_weight = item._int("weight");
					int item_value = item._int("value");
					K[i][w] = Math.max(item_value + K[i-1][w-item_weight],  K[i-1][w]);		 
				} // end else 
				else K[i][w] = K[i-1][w];
			} // end for
		} // end for
		cknapsack.m_table = K;
		return  K[n][weight];
	} // end _0_1()
	
	public static int fractional(int weight, CArray items) {
		return 0;
	} // end fractional()

	public static int bruteforce(int weight, CArray items) {
		return 0;
	} // end bruteforce()
	
	public static String toStringTable() {
		int K[][] = cknapsack.m_table;
		String str = "";
		//for(int i=0; i<K.length; i++)		
		for(int i=0; i<K.length; i++) {
			for(int w=0; w<K[i].length; w++) {
				str += K[i][w] + "   ";		
			} // end for
			str += "\n";
		} // end for
		return str;
	} // end print_table()
} // end cknapsack

//--------------------------------------------------------------------------------
// 	notes:
//	problem: packing knapsack to achieve max value of packed items
// 	W - weight of the knapsack
//	S - a Set consisting on n items
//	0-1 - each item must be entirely accepted or rejected
//  max(sum_i-N(Vi)) such that the sum_i-N(Wi)<=W
//  brute force approach: 
//		enumate all 2^n possible combination O(2^n) runtime 
//		go through all combination and find value and total weight less than W
//  dynamica programming approach:
//		identify the subproblems
//		problem items = 1...n S_n
//		subproblem = 1...k 	S_k
//--------------------------------------------------------------------------------
