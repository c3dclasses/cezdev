//-----------------------------------------------------------------------------------------
// file: CArray
// desc: 
//-----------------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CArray
// desc: 
//-------------------------------------------
public class CArray extends CCast {
	//////////////////
	// constructor
	public CArray() { this.clear(); }
	public CArray(int [] capacity) { 
		this(capacity, 0, null);
	} // end CArray()
	public CArray(int [] capacity, int index, Object value) {
		if(capacity == null || index >= capacity.length)
			return;
		else if(capacity != null && index == capacity.length-1) {
			for(int i=0; i < capacity[index]; i++)
				this.push(value); 
			return;
		} // end if
		for(int i=0; i < capacity[index]; i++)
			this.push(new CArray(capacity, index+1, value)); 
		return;
	} // end CArray()
	public CArray(CArray carray) { this(carray.m_array); }
	public CArray(Object element) { this.push(element); }
	public CArray(Object [] elements) { for (Object element : elements) this.push(element); }
	public CArray(ArrayList array) { for (Object element : array) this.push(element); }
	
	///////////////////
	// info
	public void capacity(int minCapacity) { this.m_array.ensureCapacity(minCapacity); }
	public int length() { return this.m_array.size(); }
    
	/////////////////////////
	// equality
	public int compare(int i, int j, CFunction cmpfunc) { return cmpfunc.call(_.args(this._(i), this._(j)))._int(0); } 
	public boolean equals(CArray carray) { return this.toString().equals(carray.toString()); }
	
	///////////////////
	// indexing
	public int indexOf(Object element) { return this.m_array.indexOf(element); }
	public int lastIndexOf(Object element) {  return this.m_array.lastIndexOf(element); }
	public CCast set(int iindex, Object object) { this.m_array.set(iindex, object); return this; }
	public Object get(int iindex) { return (this.m_array != null && iindex >= 0 && iindex < this.length()) ? this.m_array.get(iindex) : null; }
	public Object first() { return this.get(0); }
	public Object last() { return this.get(this.length()-1); }
	public Object top() { return this.last(); }
	public Object pop() { return (this.length() > 0) ? this.m_array.remove(this.length() - 1) : null; }
	public Object _(int index) { return this.get(index); }
    public Object _(int index, Object value) { return this.set(index, value); }
	
	////////////////////
	// operations
	public int push(Object element) { this.m_array.add(element); return this.length(); } 
	public int append(CArray carray){ if(carray == null) return this.length(); for(int i=0; i<carray.length(); i++) this.push(carray._(i)); return this.length(); } 
	public CArray concat(Object [] elements) { return new CArray(elements); }
    public CArray concat(CArray carray) { return new CArray(carray.m_array); }
	public CArray copy(){ return this.concat(this); }
	public CArray union(CArray carray) {
		CHash chash = _.chash();
		for(int i=0; i<this.length(); i++) {
			chash._(this._(i), "");
		} // end for()
		for(int i=0; i<carray.length(); i++) {
			chash._(carray._(i), "");
		} // end for()
		return chash.keys();
	} // end union()
    public String join(String seperator) { 
		int len = m_array.size(); 
		String str = ""; 
		for(int i=0; i<len; i++) 
			str += m_array.get(i).toString() + ((i!=len-1) ? ""+seperator : ""); 
		return str; 
	} // end join()
    public CArray reverse() { 
		for(int last=this.length()-1, first=0; first <= last; first++, last--) { 
			Object tmp = this.get(first);
			this.set(first, this.get(last)); 
			this.set(last, tmp); } 
			return this; 
	} // end reverse();
    public Object shift() { Object element = this.get(0); this.removeAt(0); return element; }
	public int unshift(Object element) { this.m_array.add(0, element); return this.length(); }
    public CArray slice(int start, int end) { return new CArray(this.m_array.subList(start, end).toArray()); }
    public CArray slice(int start) { return new CArray(this.m_array.subList(start, this.length()).toArray()); }
	public CArray splice(int index, int howmany, Object [] replacements) { 
		int len=index+howmany; 
		for(int i=0; i<howmany; i++) 
		this.removeAt(index); 
		if(replacements != null) 
			this.set(index, replacements); 
		return this;
	} // end splice()
	public CArray splice(int index, int howmany) { return this.splice(index, howmany, null); }
	public ArrayList valueOf() { return this.m_array; }
    public void sort(Comparator cmp) { Collections.sort(this.m_array, cmp); }
	public void sort() { Collections.sort(this.m_array, null); }
	public void sort(CFunction sortfunc, CFunction cmpfunc) { 
		if(sortfunc == null || cmpfunc == null)
			return;
		Object [] a = this.m_array.toArray(); 
		sortfunc._(_.args(a, cmpfunc));
		for(int i=0; i<a.length; i++)
			this._(i,a[i]);
	} // end sort()
	public ArrayList _() { return this.m_array; }
	public void clear() { this.m_array = new ArrayList(); }
		public CArray powerset() { return this.powerset(-1); }
	public CArray powerset(int itemsize) {
		int setsize = this.length();
		int n = (int) Math.pow(2, setsize);
		CArray powerset = _.carray();
		for(int i=0; i<n; i++) {
			CArray set = _.carray();
			for(int j=0; j<setsize; j++) {
				if((i&(1<<j)) > 0) {
					set.push(this._(j));
				} // end if
			} // end for
			if(itemsize == set.length() || itemsize == -1)
				powerset.push(set);
		} // end for
		return powerset;
	} // end powerset()
	public void visit(CFunction cfunction) { this.visit(cfunction, null); }
    public void visit(CFunction cfunction, Object obj) { 
		int i = 0; 
		for(Object element : this.m_array) { 
			cfunction._(new Object []  { i, element, obj }); 
			i++; 
		} // end for
		return; 
	} // end visit()
	public void removeAt(int index) { this.m_array.remove(index); }
	public void removeAll(Object element) { while(this.remove(element)) {} }
    public boolean remove(Object element) { return this.m_array.remove(element); }
	public boolean swap(int i, int j) {	
		Object tmp = this._(i); 
		if(tmp != null && this._(j) != null) { 
			this._(i, this._(j)); 
			this._(j, tmp); 
			return true;
		} // end if
		return false;
	} // end swap()
	int partition(int low, int high, CFunction fncompare) {   	
		Object pivot = this._(high);  	
		int i = (low-1);
		for (int j = low; j <= high-1; j++) {
			if (fncompare.call(_.args(pivot, this._(j)))._boolean()) {
				i++;   
				this.swap(i,j);
			} // end if
		} // end for 
		this.swap(i+1,high);
		return (i+1);
	} // end partition()
	
	//////////////////////////
	// toString, toJSON()
	public String toString() { return _.print_r(this.m_array, true); }
	public String toJSON(boolean bpack) { return CJSON.encode(this, bpack); }
	public String toJSON() { return this.toJSON(false); }
	public String toStringVisit(CFunction cfunction, Object input) { 
		String str = ""; 
		int i=0; 
		for(Object element : m_array) { 
			str += (String)cfunction._(new Object []  { i, element, input }); 
			i++; 
		} // end for
		return str; 
	} // end toStringVisit()  
	public Object [] toArray() { 
		Object [] arr = new Object [this.length()]; 
		for(int i=0; i<arr.length; i++) 
			arr[i] = this._(i); 
		return (arr != null && arr.length > 0) ? arr : null; 
	} // end toArray()
    protected ArrayList m_array = new ArrayList();
} // end CArray