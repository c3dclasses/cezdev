//---------------------------------------------------------------------------------
// file: f
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: f
// desc:
//---------------------------------------------------------------------------------
public class f {	
	public f(String strname) { f.set(strname, this); }
	public double _(double x) { return x; } 
	public void datafile(double xlimit) {
		CArray data = _.carray();
		data.push(_.carray("x","y") );
		for(double x=0; x<xlimit; x++)
			data.push(_.carray(x,this._(x)) );
		//_.alert(_.file_path(this));
		//_.alert(_.m_filetodir._string("f.java"));
		//_.alert(_.dir_path(this));
		// _.alert("the class name:" + this.getClass().getSimpleName());
		//String strpath = "C:\\Users\\developer\\Desktop\\cezdev\\libs\\c3dclassessdk\\ccore\\cmath";
		//_.save_csv_file(strpath + "\\data.out", data);
		_.file_set_contents(_.dir_path("f.java") + "\\data.out", data.join("\n"));
	} // end datafile()
	
	public void graph(double xlimit) {
		this.datafile(xlimit);
		_.exec_command("cmd /c dir");
	}
	
	// 
	static protected CHash m_fns = _.chash();
	static public f _(String strname) { return f.get(strname); } 
	static public f get(String strname) { return (f)f.m_fns._(strname); }
	static public void set(String strname, f fn) { f.m_fns._(strname, fn); }
	
	
} // end f