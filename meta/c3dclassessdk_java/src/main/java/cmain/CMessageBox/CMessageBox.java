//-------------------------------------------------------
// name: CMessageBox.java
// desc: defines an CMessageBox prompt for the user
//-------------------------------------------------------

// includes
import javax.swing.JOptionPane;
import cglobal.*;

//------------------------------------------------------------------------
// name: CMessageBox
// desc: the main application of CEnvironmentVariables
//------------------------------------------------------------------------
public class CMessageBox {				
	// main entry point of the application
	public static void main(String[] args) {
		if(args.length <= 1) 
			return;
		String strfilename = args[0];
		String strmsg = (args.length > 1) ? args[1] : "";
		String strtitle = (args.length > 2) ? args[2] : "";
		String strtype = (args.length > 3) ? args[3].toLowerCase() : "info";
		int itype = JOptionPane.INFORMATION_MESSAGE;
		if(strtype.equals("error")) itype = JOptionPane.ERROR_MESSAGE;
		else if(strtype.equals("warn")) itype = JOptionPane.WARNING_MESSAGE;
		else if(strtype.equals("plain")) itype = JOptionPane.PLAIN_MESSAGE;
		JOptionPane.showMessageDialog(null, strmsg, strtitle, itype);
	} // end main()
} // end CMessageBox
