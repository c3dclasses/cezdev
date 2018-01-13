//--------------------------------------------------
// file: chuffmancode
// desc: defines a huffman code object 
//--------------------------------------------------
package c3dclasses;
import java.util.*;

//--------------------------------------------------------
// name: chuffmancode
// desc: defines a huffmancode object 
//--------------------------------------------------------
public class chuffmancode {
	public static CHash get_symbol_frequencies(String symbols) {
		CHash freq = _.chash();
		for(int i=0; i<symbols.length(); i++) {
			char c = symbols.charAt(i);
			if(freq._(c) == null)
				freq._(c, 1);
			else freq._(c, freq._int(c)+1); 
		} // end for
		return freq;
	} // end get_symbol_frequencies()
/*
	public static CTree get_symbol_prefix_tree(CHash symbolfreq) {
			
	} // end get_symbol_prefix_tree()
*/	
	public static void main(String[] args) {
		_.println(chuffmancode.get_symbol_frequencies("helloworld"));
	} // end main()
} // end chuffmancode
