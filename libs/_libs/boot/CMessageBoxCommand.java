//-------------------------------------------------------
// name: CMessageBoxCommand
// desc:
//-------------------------------------------------------
import javax.swing.JOptionPane;

//------------------------------------------------------------------------
// name: CMessageBoxCommand
// desc: 
//------------------------------------------------------------------------
public class CMessageBoxCommand {				
	public static void main(String[] args) {
		if(args.length < 1) 
			return;
		String strmsg = (args.length > 0) ? args[0] : "";
		String strtitle = (args.length > 1) ? args[1] : "Message";
		String strtype = (args.length > 2) ? args[2].toLowerCase() : "info";
		int itype = JOptionPane.PLAIN_MESSAGE;
		if(strtype.equals("error")) itype = JOptionPane.ERROR_MESSAGE;
		else if(strtype.equals("warn")) itype = JOptionPane.WARNING_MESSAGE;
		else if(strtype.equals("plain")) itype = JOptionPane.PLAIN_MESSAGE;
		JOptionPane.showMessageDialog(null, strmsg, strtitle, itype);
	} // end main()
} // end CMessageBoxCommand
