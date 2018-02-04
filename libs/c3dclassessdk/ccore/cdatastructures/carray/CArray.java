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
public class CArray {
	public CArray() { this.clear(); }
	public CArray(int capacity) { for(int i=0; i<capacity; i++){ this.push(null); } }
	public CArray(Object element) { this.push(element); }
	public CArray(Object [] elements) { for (Object element : elements) this.push(element); }
	public CArray(ArrayList array) { for (Object element : array) this.push(element); }
	public int length() { return this.m_array.size(); }
    public int indexOf(Object element) { return this.m_array.indexOf(element); }
	public int lastIndexOf(Object element) {  return this.m_array.lastIndexOf(element); }
	public void clear() { this.m_array = new ArrayList(); }
	public Object get(int iindex) { return (this.m_array != null && iindex >= 0 && iindex < this.length()) ? this.m_array.get(iindex) : null; }
	public Object first() { return this.get(0); }
	public Object last() { return this.get(this.length()-1); }
	public Object top() { return this.last(); }
	public Object set(int iindex, Object object) { return this.m_array.set(iindex, object); }
	public Object pop() { return (this.length() > 0) ? this.m_array.remove(this.length() - 1) : null; }
	public int push(Object element) { this.m_array.add(element); return this.length(); } 
	public int append(CArray carray){ if(carray == null) return this.length(); for(int i=0; i<carray.length(); i++) this.push(carray._(i)); return this.length(); } 
	public CArray concat(Object [] elements) { return new CArray(elements); }
    public CArray concat(CArray carray) { return new CArray(carray.m_array); }
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
	public String toString() { return _.print_r(this.m_array, true); }
	//public String toString() { return this.toJSON(true); }
	public String toJSON(boolean bpack) { return CJSON.encode(this, bpack); }
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
	public int compare(int i, int j, CFunction cmpfunc) { return cmpfunc.call(_.args(this._(i), this._(j)))._int(0); } 
	public ArrayList _() { return this.m_array; }
    public Object _(int index) { return this.get(index); }
    public Object _(int index, Object value) { return this.set(index, value); }
	public CArray _(int sindex, int eindex) { return this.slice(sindex, eindex+1); } // adding 1 make sindex and eindex inclusize ranges 
	public boolean _boolean(int index) { return Boolean.valueOf(this.get(index).toString()); }
	public int _int(int index) { return Integer.valueOf(this.get(index).toString()); }
	public float _float(int index) { return Float.valueOf(this.get(index).toString()); }
	public String _string(int index) { return (String)this.get(index); }
	public Object [] _array(int index){ return (Object [])this.get(index); }	
	public CArray _carray(int index) { return (CArray) this.get(index); }
	public CHash _chash(int index) { return (CHash)this.get(index); }
	public CObject _cobject(int index) {return (CObject) this._(index); }
	public CFunction _cfunction(int index) {return (CFunction) this._(index); }
	public void visit(CFunction cfunction) { this.visit(cfunction, null); }
    public void visit(CFunction cfunction, Object obj) { 
		int i = 0; 
		for(Object element : this.m_array) { 
			cfunction._(new Object []  { i, element, obj }); 
			i++; 
		} // end for
		return; 
	} // end visit()
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
	protected ArrayList m_array = new ArrayList();
} // end CArray