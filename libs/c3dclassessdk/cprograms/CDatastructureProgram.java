//-------------------------------------------------------
// name: CDatastructureProgram.java
// desc: 
//-------------------------------------------------------
import javax.swing.JOptionPane;
import cglobal.*;
import c3dclasses.csystem.cui.*;
import c3dclasses.ccore.*;

public class CDatastructureProgram {				
	public static void main(String[] args) {
		CLinkedList list = new CLinkedList();
		
		// adding tail
		list.addTail("Kevin");
		list.addTail("Joseph");
		list.addTail("Lewis");
		list.addTail("Adashima");
		list.addTail("Oyo");
		_.println(list);
		
		// removing tail
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		list.removeTail();
		_.println(list);
		
		// adding head
		list.addHead("Kevin");
		list.addHead("Joseph");
		list.addHead("Lewis");
		list.addHead("Adashima");
		list.addHead("Oyo");
		_.println(list);
		
		// removing tail
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
		list.removeHead();
		_.println(list);
				
		// adding at
		list.addHead("Kevin");
		list.addHead("Joseph");
		list.addHead("Lewis");
		list.addHead("Adashima");
		list.addHead("Oyo");
		list.addAt(0, "New1");
		list.addAt(2, "New2");
		list.addAt(4, "New3");
		list.addAt(6, "New4");
		list.addAt(8, "New5");
		_.println(list);
		
		// remove at
		list.removeAt(0);
		_.println(list);
		list.removeAt(list.size()-1);
		_.println(list);
		list.removeAt(4);
		_.println(list);
		list.removeAt(6);
		_.println(list);
		list.removeAt(2);
		_.println(list);
		list.removeAt(2);
		_.println(list);
		list.removeAt(1);
		_.println(list);
	} // end main()
} // end CDatastructureProgram
