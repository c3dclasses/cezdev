//-------------------------------------------------------
// name: CGlobalProgram.java
// desc: 
//-------------------------------------------------------
import javax.swing.JOptionPane;
import cglobal.*;
import c3dclasses.ccore.*;

public class CGlobalProgram {				
	public static void main(String[] args) {
		String jsonString = "{\"stat\": { \"sdr\": \"aa:bb:cc:dd:ee:ff\", \"rcv\": \"aa:bb:cc:dd:ee:ff\", \"time\": \"UTC in millis\", \"type\": 1, \"subt\": 1, \"argv\": [{\"type\": 1, \"val\":\"stackoverflow\"}, {\"type\": 2, \"val\":\"stackoverflow2\"}]}}";
		CHash chash = (CHash) _.json_decode(jsonString,true);
		_.println(chash.toString());
		
		_.println(chash._("stat").toString());
			
		//String str = _.json_encode(chash);
		
		
	} // end main()
} // end CMessageBox
