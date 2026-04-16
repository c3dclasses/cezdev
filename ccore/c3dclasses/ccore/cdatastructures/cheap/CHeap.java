//---------------------------------------------------------------------------------
// file: CHeap
// desc: defines a heap object
//---------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------------
// name: CHeapNode
// desc: defines an object that stores a heap node
//-------------------------------------------------
class CHeapNode extends CObject {
	protected CHeap m_cheap;	// the heap this node belongs to
	protected Object m_data;	// the data of the heap
	protected int m_index;
	public CHeapNode(CHeap cheap, Object data) { m_cheap = cheap; m_data = data; m_index=-1; }
	public CCast set(Object data) { m_data = data; return this; }
	public Object get() {return this.m_data; }
	public int getIndex() { return m_index; }
	public void setIndex(int index){ this.m_index = index; }
	public String toString() { return m_data.toString(); }
	public CHeap getCHeap() {return m_cheap; }
} // end CHeapNode

//-------------------------------------------
// class CHeap
// desc: defines a heap object
//-------------------------------------------
public class CHeap {
	protected CArray m_carray;		// store the elements of the heap
	protected int m_size;			// current size of the heap	
	CFunction m_cmpfunc;			// the comparison function to set the min/max heap
	public 	CHeap(int capacity) {
		this.init(__.carray(), CHeap.compareMinIntegers());
		for(int i=0; i<capacity; i++)
			this.m_carray.push(null);
	} // end CHeap()	
	public CHeap(CArray a) { this(a, CHeap.compareMinIntegers()); }
	public CHeap(CArray a, CFunction cmpfunc) {
		this.init(a, cmpfunc);
		this.heapify();
	} // end CHeap()
	public CHeap() { this.init(__.carray(), CHeap.compareMinIntegers()); }
	public CHeap copy() {
		CHeap copy = new CHeap();
		copy.m_cmpfunc = this.m_cmpfunc;
		copy.m_size = this.m_size;
		for(int i=0; i<this.m_carray.length(); i++){
			copy.m_carray.push(new CHeapNode(this,((CHeapNode)this.m_carray.get(i)).get()));
		} // end for
		return copy;
	} // end copy()
	public int size() { return this.m_size; }
	public boolean empty() { return this.size() == 0; }
	public boolean full() { return this.size() == this.m_carray.length(); }
	public Object top() { return ((CHeapNode)this.m_carray.get(0)).get(); } 
	public Object peek() { return this.top(); } 
	public CHeapNode getCHeapNode() { return (CHeapNode)this.m_carray.get(0); } 
	//public Object contains(Object o) { return this.m_carray.indexOf(o); } 
	public void clear() { this.m_size = 0; this.m_carray.clear(); }
	public void insert(Object o) { this.push(o); }
	public void push(Object o) {
		if(o == null)
			return; 
		if(this.m_size == this.m_carray.length())	// if storage is overflow
			this.m_carray.push(null);	// increase the storage 
		this.m_size++;
		this.m_carray.set(this.m_size-1, new CHeapNode(this,o));
		//this.swap(0, this.m_size-1);	// swap the move large element to top and small to the bottom 
		this.siftUp(0, this.m_size-1); // try to move the bottom element up the tree
		return;
	} // push()
	public Object delete() { return this.pop(); }
	public Object pop() {
		if(this.empty())
			return null;
		Object obj = null;
		CHeapNode cheapnode = (CHeapNode)this.m_carray.get(0);
		if(cheapnode == null) 
			return null;
		cheapnode.setIndex(-1);
		obj = cheapnode.get();
		this.swap(0, this.m_size-1);
		this.m_size--;
		this.siftDown(0, this.m_size-1);
		return obj;
	} // end pop()
	public void heapify() { this.heapifyBottomUp(this.size()); }
	public CArray sort() {
		Object o = null;
		CArray a = __.carray();
		while((o = this.pop()) != null)			
			a.push(o);
		return a;
	} // end sort()
	public void setCompareFunction(CFunction cmpfunc) { this.m_cmpfunc = cmpfunc; }
	public CFunction getCompareFunction() { return this.m_cmpfunc; }
	public String toString() { return this.toString(false); }
	public String toString(boolean bformat) {
		if(this.m_size < 1)
			return "[]";
		int nl = 1;
		int l=1;
		String nltab = "\n\t";
		String str = "[";
		str += (bformat) ? nltab : "";
		str += "L0:[";
		for(int i=0; i<this.m_size; i++) {
			if(nl == i) {
				nl = (nl * 2) + 1;
				str += "],";
				str += (bformat) ? nltab : "";
				str += "L"+l+":[";
				l++;
			} // end if
			str += this.m_carray._object(i).toString();
			if(i+1 != nl && i+1 != this.m_size)
				str += ",";
		} // end for()
		str += "]";
		str += (bformat) ? "\n" : "";
		str += "]";
		return str;
	} // end toString()
	public CArray getCArray() { return this.m_carray; }
	
	////////////////////////
	// helper methods
	protected void init(CArray a, CFunction cmpfunc) {
		this.m_carray = a;
		this.m_size = 0;
		this.m_carray._set_cmp(CHeap.compareCHeapNodes()); 
    	this.setCompareFunction(cmpfunc);
		for(int i=0; i<a.length(); i++)
			this.push(a._object(i));
	} // end init()
	protected boolean swap(int index1, int index2) {
		CArray a = this.m_carray;
		if(a == null)
			return false;
		a._swp(index1, index2);
		((CHeapNode)a.get(index1)).setIndex(index1);
		((CHeapNode)a.get(index2)).setIndex(index2);
		return true;
	} // end swap()
	protected boolean siftUp(int startIndex, int endIndex) {
		CArray a = this.m_carray;
		boolean bsiftup = false;
		int childIndex = endIndex;
		while(childIndex > startIndex) {
			int parentIndex = this.parent(childIndex);
			if(a._cmp(parentIndex, childIndex) == -1) {
				this.swap(parentIndex, childIndex);
				childIndex = parentIndex;
				bsiftup = true;
			} // end if
			else return bsiftup;
		} // end while
		return bsiftup;
	} // end siftUp()
	protected void heapifyTopDown(int size) {
		// start from each element and siftup from that element to rebuild the heap
		for(int endIndex = 1; endIndex < size; endIndex++)
			this.siftUp(0, endIndex);
	} // end heapifyTopDown()
	protected boolean siftDown(int startIndex, int endIndex){
		CArray a = this.m_carray;
		int parentIndex = startIndex;
		while(this.leftChild(parentIndex) <= endIndex) {
			int leftChildIndex = this.leftChild(parentIndex);
			int rightChildIndex = this.rightChild(parentIndex);
			int swapIndex = parentIndex;
			if(a._cmp(swapIndex, leftChildIndex) == -1)
				swapIndex = leftChildIndex;
			if(rightChildIndex <= endIndex && a._cmp(swapIndex, rightChildIndex) == -1)
				swapIndex = rightChildIndex;
			if(parentIndex != swapIndex) {
				this.swap(parentIndex, swapIndex);
				parentIndex = swapIndex;
			} // end if
			else return false;
		} // end while
		return true;
	} // end siftDown()
	protected void heapifyBottomUp(int size) {
		// start from the last nontrivial heap or last parent heap and siftdown
		for(int startIndex = (size-2)/2; startIndex >= 0; startIndex--)
			this.siftDown(startIndex, size-1);
	} // end heapifyBottomUp()
	protected static int parent(int i) { return (i-1)/2; } // parent index of index i
	protected static int leftChild(int i) { return (2*i+1); } // right child of parent index i
	protected static int rightChild(int i) { return (2*i+2); } // right child of parent index i
	protected static CFunction compareCHeapNodes() {
		return new CFunction() {
			public CReturn call(CArray params) {
				CHeapNode cheapnode1 = (CHeapNode) params._object(0);
				CHeapNode cheapnode2 = (CHeapNode) params._object(1);
				CHeap cheap = cheapnode1.getCHeap();
				return cheap.m_cmpfunc.call(__.args(cheapnode1.get(), cheapnode2.get()));
			} // end call()
		}; // end CFunction()
	} // compareCHeapNodes()
	protected static CFunction compareMinIntegers() { 
		return new CFunction() { 
			public CReturn call(CArray args) { 
				int key0 = ((Integer) args._object(0)).intValue();
				int key1 = ((Integer) args._object(1)).intValue();
				if(key0 < key1) return CReturn._done(1);
				else if(key0 > key1) return CReturn._done(-1);
				return CReturn._done(0);
			} // end _()
		}; // end CFunction()
	} // compareMinIntegers()
	protected static CFunction compareMaxIntegers() { 
		return new CFunction() { 
			public CReturn call(CArray args) { 
				int key0 = ((Integer) args._object(0)).intValue();
				int key1 = ((Integer) args._object(1)).intValue();
				if(key0 > key1) return CReturn._done(1);
				else if(key0 < key1) return CReturn._done(-1);
				return CReturn._done(0);
			} // end _()
		}; // end CFunction()
	} // compareMaxIntegers()
} // end CHeap
