//--------------------------------------------------
// file: ccompare
// desc: contains comparison function for algorithms 
//--------------------------------------------------
package c3dclasses;
import java.util.*;

//--------------------------------------------------------
// name: ccompare
// desc: contains comparison function for algorithms 
//--------------------------------------------------------
public class ccompare {
	// compares integers
	static public CFunction _int() { return new CFunction() { public CArray _(CArray args) { 
		if(args._int(0) > args._int(1))
			return _.args(new Integer(1));
		else if(args._int(0) < args._int(1))
			return _.args(new Integer(-1));
		return _.args(new Integer(0));
	}};} // _int()
} // end ccompare

