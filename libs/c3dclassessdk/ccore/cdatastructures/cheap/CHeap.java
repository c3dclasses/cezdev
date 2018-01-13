//---------------------------------------------------------------------------------
// file: CHeap
// desc: defines a heap object
//---------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CHeap
// desc: defines a heap object
//-------------------------------------------
public class CHeap {
	protected CFunction m_cmpfunc;	// comparison function 
	protected CArray m_array;		// store the elements of the heap
	protected int m_size;			// current size of the heap	
	protected int m_infinity;		// stores an infinite value
	
	public CHeap(int capacity, boolean bmin) {
		this.m_size = 0;
    	this.m_array = _.carray();
		this.m_cmpfunc = (bmin) ? CHeap.compare_min() : CHeap.compare_max();
		this.m_infinity = (bmin) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		for(int i=0; i<capacity; i++)
			this.m_array.push(_.cobject());
	} // end CHeap()
	
	public int size() { return this.m_size; }
	
	public Object top() { return this.m_array._cobject(0)._("m_data"); } // returns the minimum key (key at root) from min heap
	
	public int parent(int i) { return (i-1)/2; } // parent index of index i
	
	public int left(int i) { return (2*i+1); } // right child of parent index i
	
	public int right(int i) { return (2*i+2); } // right child of parent index i
	
	public boolean insert(int key, Object data) { return this.push(key, data); }
	
	public boolean push(int key, Object data) {
	  	if (this.m_size == this.m_array.length())	// reached capacity
			return false;
    	this.m_size++;	
    	int i = this.m_size - 1; // First insert the new key at the end
    	this.m_array._cobject(i)._("m_data", data);
		this.decreaseKey(i, key); 
		return true;	
	} // push()
	
	public Object extract() { return this.pop(); }	
	
	public Object pop() {
		if (this.m_size <= 0)
        	return null;
    	if (this.m_size == 1) {
        	this.m_size--;
        	return this.m_array._cobject(0)._("m_data");
    	} // end if
		Object root = this.m_array._cobject(0)._("m_data"); // Store the minimum value, and remove it from heap
    	this.m_array._(0, this.m_array._(this.m_size-1));
    	this.m_size--;
    	this.heapify(0); // setup new root
		return root;
	} // end pop()	

	public Object delete(int i) {
		this.decreaseKey(i, this.m_infinity);
    	return this.pop();	
	} // end delete()
	
	public void decreaseKey(int i, int new_key) { // extracts the root which is the minimum element
		this.m_array._cobject(i)._("m_key", new_key);
		while (i != 0 && this.m_array.compare(i, this.parent(i), this.m_cmpfunc) == 1) {
			this.m_array.swap(i, this.parent(i));
       		i = this.parent(i);
    	} // end while()
	} // end decreaseKey()
	
	// A recursive method to heapify a subtree with root at given index
	// This method assumes that the subtrees are already heapified
	public void heapify(int i) {
    	int l = this.left(i);
    	int r = this.right(i);
    	int smallest = i;
    	if (l < this.m_size && this.m_array.compare(l, i, this.m_cmpfunc) == 1)
        	smallest = l;
	    if (r < this.m_size && this.m_array.compare(r, smallest, this.m_cmpfunc) == 1)
			smallest = r;
    	if (smallest != i) {
        	this.m_array.swap(i, smallest);
        	this.heapify(smallest);
    	} // end if
	} // heapify()
	
	// compares integers
	static public CFunction compare_min() { 
		return new CFunction() { public CArray _(CArray args) { 
			int key0 = args._cobject(0)._int("m_key");
			int key1 = args._cobject(1)._int("m_key");
			if(key0 > key1) return _.args(new Integer(1));
			else if(key0 < key1) return _.args(new Integer(-1));
			return _.args(new Integer(0));
	}};} // compare_min()

	static public CFunction compare_max() { 
		return new CFunction() { public CArray _(CArray args) { 
			int key0 = args._cobject(0)._int("m_key");
			int key1 = args._cobject(1)._int("m_key");
			if(key0 < key1) return _.args(new Integer(1));
			else if(key0 > key1) return _.args(new Integer(-1));
			return _.args(new Integer(0));
	}};} // compare_max()
	
	public String toString() {
		int nl = 1;
		String str = "";
		for(int i=0; i<this.m_size; i++) {
			if(nl == i) {
				nl = (nl * 2) + 1;
				str += "\n";
			} // end if
			str += this.m_array._cobject(i)._int("m_key");
			str += "\t";
		} // end for()
		return str;
	} // end toString()
		
	public static void main(String[] args) {
		CHeap cheap = new CHeap(10, false);//, ccompare._int());
		cheap.push(3, "my data 3");
		cheap.push(2, "my data 2");
		cheap.push(1, "my data 1");
		cheap.push(15, "my data 15");
		cheap.push(5, "my data 5");
		cheap.push(4, "my data 4");
		cheap.push(4, "my data 4");
		_.println(cheap.toString());
		
		_.println("cheap.pop(): " + cheap.pop());
		_.println(cheap.toString());
		_.println("cheap.pop(): " + cheap.pop());
		_.println(cheap.toString());
		_.println("cheap.pop(): " + cheap.pop());
		_.println(cheap.toString());
		_.println("cheap.pop(): " + cheap.pop());
		_.println(cheap.toString());
		
		_.println("cheap.delete(): " + cheap.delete(4));
		//_.println("cheap.getMin(): " + cheap.top());
	} // end main()
} // end CHeap

/*
Insert[edit]
	To add an element to a heap we must perform an up-heap operation (also known as bubble-up, percolate-up, sift-up, trickle-up, heapify-up, or cascade-up), by following this algorithm:
	Add the element to the bottom level of the heap.
	Compare the added element with its parent; if they are in the correct order, stop.
	If not, swap the element with its parent and return to the previous step.
	The number of operations required depends only on the number of levels the new element must rise to satisfy the heap property, thus the insertion operation has a worst-case 		time complexity of O(log n) but an average-case complexity of O(1).[4]
	
Extract[edit]
The procedure for deleting the root from the heap (effectively extracting the maximum element in a max-heap or the minimum element in a min-heap) and restoring the properties is called down-heap (also known as bubble-down, percolate-down, sift-down, trickle down, heapify-down, cascade-down, and extract-min/max).
Replace the root of the heap with the last element on the last level.
Compare the new root with its children; if they are in the correct order, stop.
If not, swap the element with one of its children and return to the previous step. (Swap with its smaller child in a min-heap and its larger child in a max-heap.)
*/