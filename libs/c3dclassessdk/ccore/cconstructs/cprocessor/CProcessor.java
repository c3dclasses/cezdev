//------------------------------------------------------------------------------------------------
// name: CProcessor
// desc: defines the driver interface and implementor to do crud operation on control objects
//------------------------------------------------------------------------------------------------
package c3dclasses;

//-----------------------------------------------------------------------------
// name: CProcessor
// desc: defines the interface object to do crud operation on control objects
//-----------------------------------------------------------------------------
public class CProcessor extends CHash {
	protected CHash m_cinstructions; // stores the instuction set object	
	public CProcessor() { this.m_cinstructions = new CHash(); } 
	public CObject process(CObject operand) {
		if(operand == null) return null;
		CFunction cfunction = (CFunction) this._(this.toInstruction(operand));
		return (cfunction == null) ? null : cfunction._(operand);			
	} // end process() 
	public boolean addCInstructions(String strinstructionname, Object cinstructions) { this.m_cinstructions._(strinstructionname, cinstructions); return true; }
	public Object getCInstructions(String strinstruction) { return this.m_cinstructions._(strinstruction); }
	public String toInstruction(CObject operand) { return ""; }
} // end CProcessor
