//--------------------------------------------------
// file: cmath
// desc: defines a class containing algorthms 
//--------------------------------------------------
package c3dclasses;
import java.util.*;

//--------------------------------------------------------
// name: csort
// desc: defines sorting algorithms 
//--------------------------------------------------------
public class cmath {
	/*
	// aymptotic notation
	static boolean theta(CFunction cfuncntion)
	static boolean omega(CFunction cfuncntion)
	static boolean alpha(CFunction cfuncntion)
	*/
	
	/*
	public static CMatrix cmatrix_multiply_rec(CMatrix cmatrix1, CMatrix cmatrix2) {
		return null;
	} // end cmatrix_multiply()
	
	public static CMatrix cmatrix_multiply_rec_strasson(CMatrix cmatrix1, CMatrix cmatrix2) {
		return null;
	} // end cmatrix_multiply()
	
	public static CMatrix cmatrix_multiply_bf(CMatrix cmatrix1, CMatrix cmatrix2) {
		return null;
	} // end cmatrix_multiply()
	
	public static CBitArray cbitarray_multiply(CBitArray cbitarray1, CBitArray cbitarray2) {
		return null;
	} // end cbitarray_multiply()
	*/
	
	// mode + 2 (mean) = 3 (median)
	public static double get_median_from_empirical_formula(double mode, double mean) { return (mode+(2*mean))/3; }
	public static double get_mode_from_empirical_formula(double mean, double median) { return (3*median)-(2*mean); }
	public static double get_mean_from_empirical_formula(double mode, double median) { return ((3*median)-mode)/2; }
} // end cmath

/*
//----------------------------------------------
// name: pad_bits_to_front()
// desc: given "0000", 1, 2 => "110000"
//----------------------------------------------
static public String pad_bits_to_front(String strbitstopad, String strbit, int ibitcount) {
                for(int i=0; i<bitcount; i++)
                                strbitstopad = strbit + stristrbitstopad;
                return strbitstopad;
} // end pad_bits_from_front()
 
//----------------------------------------------
// name: pad_bits_to_front()
// desc: given "0000", "1", 2 => "000011"
//----------------------------------------------
static public String pad_bits_to_back(String strbitstopad, String strbit, int ibitcount) {
                for(int i=0; i<bitcount; i++)
                                strbitstopad = stristrbitstopad + strbit;
                return strbitstopad;
} // end pad_bits_from_front()
 
//----------------------------------------------
// name: to_int()
// desc: converts the bit to an integer
//----------------------------------------------
static public to_int(String strbits) {
                if(strbits == null || strbits == "")
                                return 0;
                result = 0;
                for(int i=strbits.length-1; i>-1; i--)
                                if(strbits[i].getCharAt(i) == '1')
                                                result = result + 1 << i
                return result;
} // end to_int()
 
//----------------------------------------------
// name: add_bit_str()
// desc: given "0011" + "1100" => "1111"
//----------------------------------------------
static public add_bit_str(String stropbits1, String stropbits2) {
                if(stropbits1 == null || stropbits1 == "")
                                stropbits1 = "0";
                if(stropbits2 == null || stropbits2 == "")
                                stropbits2 = "0";
 
                int minlen = Math.max(stropbits1.length, stropbits2.length);
                int maxlen = Math.min(stropbits1.length, stropbits2.length);
                int padlen = maxlen - minlen;
 
                // make sure both bits are the same length
                stropbits1 = cmath.pad_bits_to_front(stropbits1, "0", len);
                stropbits2 = cmath.pad_bits_to_front(stropbits2, "0", len);
                strresult = "";
 
                // do the addition using O(n) operations
                for(int i=0; i<stropbits1.length; i++)
                                char csum = _add_bit_char(stropbits1.getChar(i), stropbits2.getChar(i), strcarry);
                                char ccarry = _add_bit_char(stropbits1.getChar(i), stropbits2.getChar(i), strcarry);
                                strresult = csum + strresult;            
                } // end for()
 
                return ccarry + strresult;
} // end add_bit_str()
 
//---------------------------------------------
//
//
//---------------------------------------------
*/