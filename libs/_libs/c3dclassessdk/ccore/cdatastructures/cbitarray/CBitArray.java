//-----------------------------------------------------------------------------------------
// file: CBitArray.java
// desc: defines an object that access a interger bits as an array and sets them on/off
//-----------------------------------------------------------------------------------------
package c3dclasses;

//----------------------------------------------------------------
// class CBitArray
// desc: defines an object that defines bit flag 
//----------------------------------------------------------------
public class CBitArray {	
	static final int SIZEOFINT = 32;	
	static final double BITTOINT = 0.03125;
	static final int ALLBITS = 0xFFFFF;
	protected int m_arribits[];
	protected int m_inumofbits;
	protected int m_inumofbitsset;
	
	public CBitArray() { 
		this.m_arribits = null;
		this.m_inumofbits = 0;
		this.m_inumofbitsset = 0;
	} // end CBitArray
	
	public CBitArray(int inumofbits) {
		this.create(inumofbits);
	} // end CBitArray()
	
	public boolean create(int inumofbits) {
		int [] ibitarrays  = null;
		int inumbitarrays = 0;
		if (inumofbits < 1) 
			return false;
		try{
			inumbitarrays = (int)Math.ceil(inumofbits/(double)CBitArray.SIZEOFINT); 
			ibitarrays = new int [inumbitarrays]; 
			for (int i=0; i<ibitarrays.length; i++)
				ibitarrays[i]=0;
		} // end try
		catch(Exception e) {
			ibitarrays = null;
			return false;
		} // end catch
		this.m_arribits = ibitarrays;
		this.m_inumofbits = inumofbits;	
		this.m_inumofbitsset = 0;	
		return true;	
	} // end create()
	
	boolean createFromString(String strcbitarray, boolean bhex) {
		if (strcbitarray==null || strcbitarray == "") 
			return false;
		int inumtokens 		= 0;
		int inumbits 		= 0;
		int inumbitsset 	= 0;
		int inumbitarrays	= 0;
		int [] arribitarrays  	= null;
		int i 				= 0;
		String [] arrtokens 		= strcbitarray.split(" ");
		if (arrtokens.length < 3) 
			return false;
		inumbits = Integer.parseInt(arrtokens[i++]);
		if (inumbits < 1) 
			return false;
		inumbitarrays = (int)Math.ceil((inumbits) /(double)(CBitArray.SIZEOFINT)); 
		if (arrtokens.length < inumbitarrays) 
			return false;
		inumbitsset = Integer.parseInt(arrtokens[i++]);
		arribitarrays = new int [inumbitarrays];
		for (int j=0; j<inumbitarrays; j++) {
			if (bhex == false) arribitarrays[j] = Integer.parseInt(arrtokens[i++]);
			else arribitarrays[j] = Integer.parseInt(arrtokens[i++], 16);
		} // end for
		this.m_arribits = arribitarrays;
		this.m_inumofbits = inumbits;
		this.m_inumofbitsset = inumbitsset;	
		return true;
	} // end create()	

	public boolean isEmpty() { return this.m_inumofbitsset <= 0; }
	public boolean isFull() { return this.m_inumofbitsset >= this.m_inumofbits; }
	public int [] getBitArrays() { return this.m_arribits; }
	public int getNumOfBits() { return this.m_inumofbits; }
	public int getNumOfBitsSet() { return this.m_inumofbitsset; }
	
	public int [] getAllSetBitIndices() {
		if (this.m_inumofbits == 0 || this.m_arribits == null) 
			return null;		
		int inumsetbits = 0;
		for (int i=0; i<this.m_inumofbits; i++)
			if(this.isBitSet(i)) 
				inumsetbits++;		
		if (inumsetbits == 0)
			return null;		
		int [] indices = new int [inumsetbits];
		for (int j=0, i=0; i<this.m_inumofbits; i++)
			if (this.isBitSet(i)) 
				indices[j++] = i;				
		return indices;
	} // end getAllSetBitIndices()
	
	public boolean setBit(int ibit) {
		int ibitflag = this.computeBitArray(ibit);
		if (ibitflag == -1) 
			return false;
		ibit = ibit % CBitArray.SIZEOFINT;		
        this.m_arribits[ibitflag] = (this.m_arribits[ibitflag]|(1<<ibit)); 		
		this.m_inumofbitsset++;
		return true; 						 
	} // end setBit()
	
	public boolean setAllBits() {
		if (this.m_arribits == null) 
			return false;
		for (int i=0; i<this.m_arribits.length; i++)
			this.m_arribits[i] = CBitArray.ALLBITS;
		this.m_inumofbitsset = this.m_inumofbits;
		return true;
	} // end setAllBits()	
	
	public boolean clearBit(int ibit) {
		int ibitflag = this.computeBitArray(ibit);
		if (ibitflag == -1) 
			return false;
		ibit = ibit % CBitArray.SIZEOFINT;		
		this.m_arribits[ibitflag] &= (~(1<<ibit)); 
		this.m_inumofbitsset--;
		return true;		
	} // end clearBit()
	
	public boolean clearAllBits() {
		if (this.m_arribits == null) 
			return false;		
		for (int i=0; i<this.m_arribits.length; i++)
			this.m_arribits[i] = 0;
		this.m_inumofbitsset=0;
		return true;
	} // end clearAllBits();
	
	public boolean enableBit(int ibit, boolean benable) { return (benable) ? this.setBit(ibit) : this.clearBit(ibit); }
	
	public boolean isBitSet(int ibit) {		
		int ibitflag = this.computeBitArray(ibit);	
		if (ibitflag == -1)
			return false;
		ibit = ibit % CBitArray.SIZEOFINT;		
		if ((this.m_arribits[ibitflag] & (1<<ibit)) > 0)
			return true;
		return false;
	} // end isBitSet()
	
	public boolean getBit(int ibit) { return this.isBitSet(ibit); }
	
	public String toBinaryString() {
		String str="";
		for (int ibit=this.m_inumofbits; ibit>-1; ibit--) 	
			str += ((this.isBitSet(ibit)) ? "1" : "0");		
		return str.trim();
	} // toBinaryString()
	
	public String toHexString() { return this._toString(true); }
	
	public String toDecimalString() { return this._toString(false); }
	
	public String _toString(boolean bhex) {
		String str = "" + this.m_inumofbits + " " + this.m_inumofbitsset + " ";
		if (this.m_arribits != null) 
			for (int i=0; i<this.m_arribits.length; i++) {
				if (bhex == false) 
					str += this.m_arribits[i];
				else str += Integer.toHexString(this.m_arribits[i]);
				str += " ";
			} // end for
		return str.trim();
	} // toString()
	
	public String toString() { return this.toBinaryString(); }
	
	public int computeBitArray(int ibit) { 
		return (this.m_arribits == null || ibit < 0 || ibit >= this.m_inumofbits) ? -1 : (int)Math.floor(ibit * CBitArray.BITTOINT); 
	} // end computeBitArray()
} // end CBitArray