//---------------------------------------------------------------------------------
// file: CHeapProgram
// desc: defines a heap object
//---------------------------------------------------------------------------------
package c3dclasses;

//-------------------------------------------
// class CHeap
// desc: defines a heap object
//-------------------------------------------
public class CHeapProgram {
	public static void main(String[] args) {		
		CHeap cheap1 = new CHeap();
		CHeap cheap2 = new CHeap(8);
		CHeap cheap3 = new CHeap(__.carray(2,3,0,5,4,6));
		CHeap cheap4 = new CHeap(__.carray(2,3,0,5,4,6), CHeap.compareMaxIntegers());
		cheap4.heapify();
		__.println(cheap1.toString());
		__.println(cheap2.toString());
		__.println(cheap3.toString());
		__.println(cheap4.toString());
		__.println();
		
		__.println(cheap3.sort());
		__.println(cheap3.copy().sort());
		
		//__.println(cheap4.copy().sort());
		
		
		
		cheap4.push(10);
		cheap4.push(-1);
		__.println(cheap4.sort().toString());
	} // end main()
} // end CHeap
