//------------------------------------------------------------------------------------------------
// name: CProcessorUnitTest
// desc: defines the driver interface and implementor to do crud operation on control objects
//------------------------------------------------------------------------------------------------
package c3dclasses;
import org.junit.Test;

//-----------------------------------------------------------------------------
// name: CFooProcessor
// desc: processor object
//-----------------------------------------------------------------------------
class CFooProcessor extends CProcessor {	
	public CFooProcessor() {
		super();
		this.addCInstructions("CFooInstructions", new CFooInstructions(this));	
	} // end CFooProcessor()
	public String toInstruction(CObject cobject) {
		return (cobject == null) ? "" : cobject._string("m_strop").toLowerCase();
	} // end toInstruction()
} // end CFooProcessor

//-----------------------------------------------------------------------------
// name: CFooInstructions
// desc: instructions object
//-----------------------------------------------------------------------------
class CFooInstructions extends CInstructions {	
	public CFooInstructions(CProcessor cprocessor) {
		super(cprocessor);
		CFunction fnAdd = new CFunction() { public CReturn call(CObject cobject) { 
			int iop1 = cobject._int("m_iop1");
			int iop2 = cobject._int("m_iop2");
			cobject._("m_result", iop1 + iop2);						
			return null;
		}}; // end fnAdd()
		CFunction fnMulitply = new CFunction() { public CReturn call(CObject cobject) { 
			int iop1 = cobject._int("m_iop1");
			int iop2 = cobject._int("m_iop2");
			cobject._("m_result", iop1 * iop2);		
			return null;
		}}; // end fnMulitply()
		cprocessor._("add", fnAdd);
		cprocessor._("mul", fnMulitply);
		cprocessor._("*", fnMulitply);
		cprocessor._("+", fnAdd);
	} // end CFooInstructions()
} // end CFooInstructions()

//-----------------------------------------------------------------------------
// name: CFooObject
// desc: interface used to run CFooProcessor Instructions
//-----------------------------------------------------------------------------
class CFooObject extends CObject {
	static public CFooProcessor m_cfooprocessor = new CFooProcessor();
	public CFooObject setProp(String strop, int op1, int op2) {	
		this._("m_strop", strop);
		this._("m_iop1", op1);
		this._("m_iop2", op2);
		CFooObject.m_cfooprocessor.execute(this);
		return this;
	} // end setProp()
} // end CFooObject

//-----------------------------------------------------------------------------
// name: CProcessorUnitTest
// desc: 
//-----------------------------------------------------------------------------
public class CProcessorUnitTest extends CUnitTest {
	@Test
	public void test() {
		CFooObject cfooobject = new CFooObject();
		cfooobject.setProp("add", 3, 3);
		this.assertTrue(cfooobject._int("m_result") == 6);
		cfooobject.setProp("mul", 3, 3);
		this.assertTrue(cfooobject._int("m_result") == 9);
		cfooobject.setProp("+", 3, 3);
		this.assertTrue(cfooobject._int("m_result") == 6);
		cfooobject.setProp("*", 3, 3);
		this.assertTrue(cfooobject._int("m_result") == 9);
	} // end test()
} // end CProcessorUnitTest