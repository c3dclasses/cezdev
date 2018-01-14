//--------------------------------------------------------------
// name: CInstructions
// desc: implements the processors instruction set
//--------------------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: CInstructions
// desc: implements the processor's instruction set
//--------------------------------------------------------
public class CInstructions {	
	protected CProcessor m_cprocessor; // the processor that runs the instructions
	public CInstructions(CProcessor cprocessor) { this.m_cprocessor = cprocessor; }
	public CProcessor getProcessor() { return this.m_cprocessor; }
} // end CInstructions