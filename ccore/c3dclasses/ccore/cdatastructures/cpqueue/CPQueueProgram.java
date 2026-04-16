//---------------------------------------------------------------------------------
// file: CPQueueProgram
// desc: defines a priority queue object
//---------------------------------------------------------------------------------
package c3dclasses;
import java.util.*;

//-------------------------------------------
// class CPQueueProgram
// desc: defines a priority queue object
//-------------------------------------------
public class CPQueueProgram {
	public static void main(String[] args) {
		CPQueue cpqueue = new CPQueue();
		cpqueue.add(1);
		cpqueue.add(10);
		cpqueue.add(-2);
		cpqueue.add(-1);
		__.println(cpqueue.size());
		__.println(cpqueue.remove().toString());
		__.println(cpqueue.remove().toString());
		__.println(cpqueue.remove().toString());
		__.println(cpqueue.remove().toString());		
	} // end main()
} // end CPQueueProgram
