//-----------------------------------------------------------------------------------------
// file: CArray
// desc: defines an array object
//-----------------------------------------------------------------------------------------
package c3dclasses.ccore;
import java.util.*;
import cglobal.*;

//-------------------------------------------
// class CArray
// desc: creates a array object
//-------------------------------------------
public class CArray {
	protected ArrayList m_array = new ArrayList();;
	public CArray() { this.clear(); }
	public CArray(Object [] elements) { for (Object element : elements) this.push(element); }
	public CArray(ArrayList array) { for (Object element : array) this.push(element); }
	//public CArray(CArray carray) { super(carray.m_array); }
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
	public CArray concat(Object [] elements) { return new CArray(elements); }
    public CArray concat(CArray carray) { return new CArray(carray.m_array); }
    public String join(String seperator) { int len = m_array.size(); String str = ""; for(int i=0; i<len; i++) str += m_array.get(i).toString() + ((i!=len-1) ? ""+seperator : ""); return str; }
    public CArray reverse() { for(int last=this.length()-1, first=0; first <= last; first++, last--) { Object tmp = this.get(first);this.set(first, this.get(last)); this.set(last, tmp); } return this; } // end reverse();
    public Object shift() { Object element = this.get(0); this.removeAt(0); return element; }
	public int unshift(Object element) { this.m_array.add(0, element); return this.length(); }
    public CArray slice(int start, int end) { return new CArray(this.m_array.subList(start, end).toArray()); }
    public CArray slice(int start) { return new CArray(this.m_array.subList(start, this.length()-1).toArray()); }
	public CArray splice(int index, int howmany, Object [] replacements) { int len=index+howmany; for(int i=0; i<howmany; i++) this.removeAt(index); if(replacements != null) this.set(index, replacements); return this;}
	public CArray splice(int index, int howmany) { return this.splice(index, howmany, null); }
	public String toString() { return _.print_r(this.m_array, true); }
    public ArrayList valueOf() { return this.m_array; }
    public void sort(Comparator cmp) { Collections.sort(this.m_array, cmp); }
	public void sort() { Collections.sort(this.m_array, null); }
	public ArrayList _() { return this.m_array; }
    public Object _(int index) { return this.get(index); }
    public Object _(int index, Object value) { return this.set(index, value); }
	public void visit(CFunction cfunction) { this.visit(cfunction, null); }
    public void visit(CFunction cfunction, Object obj) { int i=0; for(Object element : m_array) { cfunction._(new Object []  { i, element, obj }); i++; } return; }
    public String toStringVisit(CFunction cfunction, Object input) { String str = ""; int i=0; for(Object element : m_array) { str += (String)cfunction._(new Object []  { i, element, input }); i++; } return str; } 
    public void removeAt(int index) { this.m_array.remove(index); }
	public void removeAll(Object element) { while(this.remove(element)) {} }
    public boolean remove(Object element) { return this.m_array.remove(element); }
} // end CArray