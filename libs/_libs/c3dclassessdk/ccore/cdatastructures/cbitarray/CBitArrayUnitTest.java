//---------------------------------------------------------------------------------
// file: CBitArrayUnitTest
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;
import Jama.*; 

//---------------------------------------------------------------------------------
// name: CBitArrayUnitTest
// desc: 
//---------------------------------------------------------------------------------
public class CBitArrayUnitTest extends CUnitTest{
	@Test
	public void test() {
		CBitArray cbitarray = new CBitArray();
		this.assertTrue(cbitarray != null);
		this.assertTrue(cbitarray.create(56) != false);
		cbitarray.setBit(0);
		cbitarray.setBit(1);
		cbitarray.enableBit(20, true);
		this.assertTrue(cbitarray.isBitSet(0));
		this.assertTrue(cbitarray.isBitSet(1));
		this.assertTrue(!cbitarray.isBitSet(2));
		this.assertTrue(cbitarray.toBinaryString().equals("000000000000000000000000000000000000100000000000000000011"));
		cbitarray.clearBit(0);
		this.assertTrue(cbitarray.toBinaryString().equals("000000000000000000000000000000000000100000000000000000010"));
		cbitarray.clearBit(1);
		this.assertTrue(cbitarray.toBinaryString().equals("000000000000000000000000000000000000100000000000000000000"));
		this.assertTrue(cbitarray.toDecimalString().equals("56 1 1048576 0"));
		
		CBitArray cbitarray2 = new CBitArray();
		this.assertTrue(cbitarray2 != null);
		this.assertTrue(cbitarray2.createFromString(cbitarray.toDecimalString(), false) != false);
		this.assertTrue(cbitarray2.toBinaryString().equals(cbitarray.toBinaryString()));
		this.assertTrue(cbitarray2.toDecimalString().equals(cbitarray.toDecimalString()));
	} // end test()
} // end CBitArrayUnitTest