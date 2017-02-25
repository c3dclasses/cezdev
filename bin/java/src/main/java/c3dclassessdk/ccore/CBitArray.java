//-----------------------------------------------------------------------------------------
// file: cbitarray.php
// desc: defines an object that access a interger bits as an array and sets them on/off
//-----------------------------------------------------------------------------------------
package c3dclassessdk.ccore;
import java.util.*;
import c3dclassessdk.clib.*;
import c3dclassessdk.ccore.*;

//----------------------------------------------------------------
// name: CBitArray
// desc:  defines an object that defines bit flag 
//----------------------------------------------------------------
public class CBitArray {
	protected int [] m_arribits = null;
	protected int m_inumofbits = 0;
	protected int m_inumofbitsset = 0;
	static public final int SIZEOFINT = Integer.SIZE;	
	static public final double BITTOINT = (double)1.0/(double)Integer.SIZE;
	static public final int ALLBITS = 0xFFFFFF;

	public CBitArray() { 
	} // end CBitArray

	public boolean create(int inumofbits) {
		int inumbitarrays = 0;
		if(inumofbits < 1) 
			return false;
		inumbitarrays = (int) Math.ceil((double) inumofbits / (double)CBitArray.SIZEOFINT); 
		
		_.alert(inumbitarrays);
		
		ArrayList<Integer> ibitarrays = new ArrayList<Integer>();
		for(int i=0; i<inumbitarrays; i++)
			ibitarrays.add(0);	
		if(ibitarrays.size() < 1)
			return false;	
		int len = ibitarrays.size();
		this.m_arribits = new int[len];
		for(int i=0; i<len; i++)
			this.m_arribits[i] = ibitarrays.get(i); 
		this.m_inumofbits = inumofbits;	
		this.m_inumofbitsset = 0;	
		return true;	
	} // end create()
	
	public boolean createFromString(String strcbitarray) {
		return createFromString(strcbitarray, false);
	} // end 
	
	public boolean createFromString(String strcbitarray, boolean bhex) {
		if(strcbitarray==null || strcbitarray == "") 
			return false;	
			
		_.alert(strcbitarray); 
		CArray carrtokens  = _.split(" ", strcbitarray);
		if(carrtokens == null)
			return false;
		int inumofbits = _.parseInt((String)carrtokens._(0));
		int inumbitarrays = (int) Math.ceil((double) inumofbits / (double)CBitArray.SIZEOFINT); 
		
		if(inumbitarrays < 1)
			return false;	
		this.m_arribits = new int[inumbitarrays];
		for(int i=0; i<inumbitarrays; i++) {
			this.m_arribits[i] = _.parseInt((String)carrtokens._(2+i));
			//_.alert((String)carrtokens._(2+i));
		}
		this.m_inumofbits = _.parseInt((String)carrtokens._(0));
		this.m_inumofbitsset = _.parseInt((String)carrtokens._(1));
		return true;
	} // end create()	

	public boolean isEmpty() { return this.m_inumofbitsset <= 0; }
	
	public boolean isFull() { return this.m_inumofbitsset >= this.m_inumofbits; }
	
	public int [] getBitArrays() { return this.m_arribits; }
	
	public int getNumOfBits() { return this.m_inumofbits; } 
	
	public int getNumOfBitsSet() { return this.m_inumofbitsset; }	
	
	public int [] getAllSetBitIndices() {
		if(this.m_inumofbits == 0 || this.m_arribits == null) 
			return null;		
		int inumsetbits = 0;
		for(int i=0; i<this.m_inumofbits; i++)
			if (this.isBitSet(i)) 
				inumsetbits++;		
		if(inumsetbits == 0)
			return null;		
		int [] indices = new int [inumsetbits];
		int i=0;
		for(int j=0; i<this.m_inumofbits; i++)
			if(this.isBitSet(i)) 
				indices[j++] = i;				
		return indices;
	} // end getAllSetBitIndices()
	
	public boolean setBit(int ibit) {
		int ibitarray = this.computeBitArray(ibit);	
		if(ibitarray == -1) 
			return false;
		ibit = ibit % CBitArray.SIZEOFINT;		
        this.m_arribits[ ibitarray ] = (this.m_arribits[ ibitarray ]|(1<<ibit)); 		
		this.m_inumofbitsset++;
		return true; 						 
	} // end setBit()
	
	public boolean setAllBits() {
		if(this.m_arribits == null) 
			return false;
		for(int i=0; i<this.m_arribits.length; i++)
			this.m_arribits[i] = CBitArray.ALLBITS;
		this.m_inumofbitsset = this.m_inumofbits;
		return true;
	} // end setAllBits()	
	
	public boolean clearBit(int ibit) {
		int ibitarray = this.computeBitArray(ibit);
		if (ibitarray == -1) 
			return false;
		ibit = ibit % CBitArray.SIZEOFINT;		
		this.m_arribits[ ibitarray ] &= (~(1<<ibit)); 
		this.m_inumofbitsset--;
		return true;		
	} // end clearBit()
	
	public boolean clearAllBits() {
		if(this.m_arribits == null) 
			return false;		
		for(int i=0; i<this.m_arribits.length; i++)
			this.m_arribits[i] = 0;
		this.m_inumofbitsset=0;
		return true;
	} // end clearAllBits();
	
	public boolean enableBit(int ibit, boolean benable) { 
		return (benable) ? this.setBit(ibit) : this.clearBit(ibit); 
	} // end enabelBit	
	
	public boolean isBitSet(int ibit) {		
		int ibitarray = this.computeBitArray(ibit);	
		if(ibitarray == -1)
			return false;
		ibit = ibit % CBitArray.SIZEOFINT;		
		if((this.m_arribits[ibitarray] & (1<<ibit)) > 0)
			return true;
		return false;
	} // end isBitSet()
	
	public String toBinaryString() {
		String str="";
		for(int ibit=this.m_inumofbits-1; ibit>-1; ibit--) {
			_.println(((this.isBitSet(ibit)) ? "1" : "0"));	
			str += ((this.isBitSet(ibit)) ? "1" : "0");		
		}
		return str;
	} // toBinaryString()	
	
	public String toHexString() { return this.toString(true); }
	
	public String toDecimalString() { return this.toString(false); }
	
	public String toString() { return this.toString(false); } 
	
	public String toString(boolean bhex) {
		String str = "" + this.m_inumofbits + " " + this.m_inumofbitsset + " ";
		if(this.m_arribits == null)
			return "";	 
		for(int i=0; i<this.m_arribits.length; i++) {
			int dec = this.m_arribits[i];
			str = str + ((bhex == false) ? dec : Integer.toHexString(dec)) + " ";
		} // end for
		return str;
	} // toString()
	
	public int computeBitArray(int ibit) { 
		return (this.m_arribits == null || ibit < 0 || ibit >= this.m_inumofbits) ? -1 : (int)
		Math.floor((double) ibit * CBitArray.BITTOINT); 
	} // end computeBitArray()
} // end CBitArray