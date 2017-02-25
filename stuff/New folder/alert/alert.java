//-------------------------------------------------------
// name: alert.java
// desc: defines an alert prompt for the user
//-------------------------------------------------------

// includes
import javax.swing.JOptionPane;

//------------------------------------------------------------------------
// name: alert
// desc: the main application of CEnvironmentVariables
//------------------------------------------------------------------------
public class alert {
	// constructor
	public alert() {		
	} // end alert() 	
				
	// main entry point of the application
	public static void main(String[] args) {
		if(args.length <= 0)
			return;
		JOptionPane.showMessageDialog(null, "" + args[0]);
	} // end main()
} // end CMain
