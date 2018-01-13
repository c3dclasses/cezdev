//-------------------------------------------------------
// name: CAlgorithmsProgram.java
// desc: 
//-------------------------------------------------------
import javax.swing.JOptionPane;
import c3dclasses.*;


public class CAlgorithmsProgram {				
	public static void main(String[] args) {
	/*
		_.println("this is my algorthms program");
		int [] a = {9,8,7,6,5,4,3,2,1}; 
		Object objs[] = _.obj(a);
		//for(int i=0; i<objs.length; i++)
		_.print(objs);
		_.println("");
		int [] a2 = {1,2,3,4,5,6,7,8,9}; 
		Object objs2[] = _.obj(a2);	// convert a2 into an object
		//for(int i=0; i<objs2.length; i++)
		_.print(objs2);
		_.println("");
		// perform a linear search find object containing 6
		_.println("Position of 6 is at index: " + csearch.linear_search(objs, new Integer(6)));
		// perform a linear search find object containing 6
		_.println("Position of 6 is at index: " + csearch.binary_search(objs2, new Integer(6)));
		CHash freq = chuffmancode.get_element_frequency("aaaabbbccd");
		_.println();
		_.print(freq);
		//csort.qs(objs, 0, objs.length, null);
	*/
	
		/*CArray carray = _.carray(7,8,9,6,5,3,4,1,2);
		_.print(carray._().toArray());
		
		carray.sort(csort.bubble_sort_func(), ccompare._int());
		_.println();
		//carray.sort(sortfunc, cmpfunc)
		_.print(carray);
*/
		//int [] a = {7,8,9,6,5,3,4,1,2}; 
		//Object objs[] = _.obj(a);
		//_.print(objs);
		//csort.bubble_sort(objs);
		//_.println();
		//_.print(objs);
		
		//////////////////////////
		// knapsack 
		CArray items = _.carray(
			_.cobject("weight", 10, "value", 60),
			_.cobject("weight", 20, "value", 100),
			_.cobject("weight", 30, "value", 120)
		);
		int w = 50;
		_.println(items);		
		_.println(cknapsack._0_1_rec(w, items));
		_.println(cknapsack._0_1(w, items));
		_.println(cknapsack.toStringTable());
		_.println();
		
		CArray items2 = _.carray(
			_.cobject("weight", 2, "value", 12),
			_.cobject("weight", 7, "value", 28),
			_.cobject("weight", 10, "value", 30),
			_.cobject("weight", 12, "value", 5)
		);
		int w2 = 12;		
		_.println(items2);		
		_.println(cknapsack._0_1_rec(w2, items2));
		_.println(cknapsack._0_1(w2, items2));
		_.println(cknapsack.toStringTable());

		////////////////////////////
		// coinchange
		CArray D = _.carray(1,5,10,25);
		ccoinchange.greedy(D,25);
	
	} // end main()
} // end CDatastructureProgram
