//--------------------------------------------------
// file: cintervalscheduling
// desc: interval scheduling algorithm
//--------------------------------------------------
package c3dclasses;
import java.util.*;

//--------------------------------------------------------
// file: cintervalscheduling
// desc: interval scheduling algorithm
//--------------------------------------------------------
public class cintervalscheduling {
	public static int weighted_rec(CArray tasks) {	
		tasks.sort(csort.bubble_sort_func(), cintervalscheduling.cmp_func());
		return cintervalscheduling.weighted_rec(tasks, tasks.length());
	} // end weighted_rec() 	
	
	public static int weighted_rec(CArray tasks, int n) {	
		if(n == 1)
			return tasks._cobject(n-1)._int("profit");
		int inclProf = tasks._cobject(n-1)._int("profit");
		int i = cintervalscheduling.latest_compatible_task(tasks, n-1);
		if(i != -1)
			inclProf += cintervalscheduling.weighted_rec(tasks, i+1);
		int exclProf = cintervalscheduling.weighted_rec(tasks, n-1);
		return Math.max(inclProf, exclProf);
	} // end weighted_rec() 	

	public static int weighted(CArray tasks) {	
		tasks.sort(csort.bubble_sort_func(), cintervalscheduling.cmp_func());
		int n = tasks.length();
		int [] table = new int [n];
		table[0] = tasks._cobject(0)._int("profit");
		for(int i=1; i<n; i++) {
			int inclProf = tasks._cobject(i)._int("profit");
			int l = cintervalscheduling.latest_compatible_task(tasks, i);
			if(l != -1)
				inclProf += table[l];
			table[i] = Math.max(inclProf, table[i-1]);
		} // end for()
		return table[table.length-1];
	} // end weighted() 	
	
	// helper functions	
	public static CFunction cmp_func() {
		return new CFunction() { public CArray _(CArray args) { 
			int s1 = args._cobject(0)._int("finish");
			int s2 = args._cobject(1)._int("finish");
			if(s1 > s2)
				return _.args(new Integer(1));
			else if(s1 < s2)
				return _.args(new Integer(-1));
			return _.args(new Integer(0));
		}}; // end CFunction()
	} // end compare_task_func()	
	
	// use a CArray.search(search_func, compare_func, elementtosearch) 
	public static int latest_compatible_task(CArray tasks, int i) {
		for(int j=i-1; j>=0; j--) {
			if(tasks._cobject(j)._int("finish") <= tasks._cobject(i)._int("start"))
				return j;
		} // end for
		return -1;
	} // end compare_finish_time()
	
	public static void main(String[] args) {
		CArray tasks = _.carray(
			_.cobject("start", 3, "finish", 10, "profit", 20),
			_.cobject("start", 1, "finish", 2, "profit", 50),
			_.cobject("start", 6, "finish", 19, "profit", 100),
			_.cobject("start", 2, "finish", 100, "profit", 200)
		); // end _.carray()
		_.println(tasks);
		_.println(cintervalscheduling.weighted(tasks));	
		_.println(tasks);
		
		_.println(tasks);
		_.println(cintervalscheduling.weighted_rec(tasks));	
		_.println(tasks);
		
		
	} // end main()
} // end cntervalscheduling

