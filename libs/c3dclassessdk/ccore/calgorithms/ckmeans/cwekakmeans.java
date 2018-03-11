//--------------------------------------------------
// file: cwekakmeans
// desc: 
//--------------------------------------------------
package c3dclasses;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

//--------------------------------------------------------------------------
// name: cwekakmeans
// desc: https://www.programcreek.com/2014/02/k-means-clustering-in-java/
//--------------------------------------------------------------------------
public class cwekakmeans {
	public static CArray _(int k, CArray items, int maxiterations, String strrelation) {
		String strfilename = generate_dataset_file(items, strrelation);
		return cwekakmeans._(k, strfilename, maxiterations);
	} // end _()
	
	public static CArray _(int k, String strwekadatasetfilename, int maxiterations) {
		try {
			cwekakmeans _this = new cwekakmeans();
			SimpleKMeans kmeans = new SimpleKMeans();
			kmeans.setSeed(10);
			kmeans.setPreserveInstancesOrder(true);
			kmeans.setNumClusters(k);
			BufferedReader inputreader = new BufferedReader(new FileReader(strwekadatasetfilename));
			kmeans.buildClusterer(new Instances(inputreader));
			Instances centroids = kmeans.getClusterCentroids();
			int[] assignments = kmeans.getAssignments();
			CArray cluster_assignments = _.carray();
			for(int i=0; i<assignments.length; i++) {
				cluster_assignments.push(assignments[i]);
			} // end for
			return _.args(generate_clusters(centroids), cluster_assignments);
		} // end try
		catch(Exception ex){ 
			_.alert(ex.getMessage());
			return null;
		} // end catch
	} // end _()
	
	// helper functions
	public static String generate_dataset_file(CArray items, String strrelation) {
		String strfilename = /*_.dir_path(new cwekakmeans()) +*/ "C:/Users/developer/Desktop/cezdev2/libs/c3dclassessdk/ccore/calgorithms/ckmeans/weka-" + strrelation + ".arff";
		if(items == null)
			return null;
		String str = "";
		str += "@relation " + strrelation + "\n";
		
		if(items.length() > 0) {
			for(int j=0; j<items._carray(0).length(); j++) {
				str += "@attribute 'a" + j + "' numeric\n";
			} // end for
		} // end if
		
		str += "@data\n";
		for(int i=0; i<items.length(); i++) {
			for(int j=0; j<items._carray(i).length(); j++) {
				str += items._carray(i)._(j);
				if(j<items._carray(i).length()-1)
					str += ",";
			} // end for
			str += "\n";
		} // end for
		_.set_file_contents(strfilename, str);
		return strfilename;
	} // end generate_weka_dataset_file()
	
	public static CArray generate_clusters(Instances centroids) {
		if(centroids == null)
			return null;
		CArray ccentroids = _.carray();
		for(int i=0; i<centroids.numInstances(); i++) {
			CArray comp = _.split(",",centroids.instance(i).toString());
			CArray c = _.carray();
			for(int j=0; j<comp.length(); j++) {
				CArray intpart = _.split(".", comp._string(j));
				c.push(Integer.parseInt(intpart._string(0)));
				//c.push(comp._(j));
				
			} // end for
			ccentroids.push(c);
		} // end for()
		return ccentroids;
	} // end generate_clusters()
	
  /**
   * Returns the instance at the given position.
   * 
   * @param index the instance's index (index starts with 0)
   * @return the instance at the given position
   
  // @ requires 0 <= index;
  // @ requires index < numInstances();
  public Instance instance(int index) {

    return m_Instances.get(index);
  }
*/

/**
   * Returns string including all instances, their weights and their indices in
   * the original dataset.
   * 
   * @return description of instance and its weight as a string
   *
  protected String instancesAndWeights() {

    StringBuffer text = new StringBuffer();

    for (int i = 0; i < numInstances(); i++) {
      text.append(instance(i) + " " + instance(i).weight());
      if (i < numInstances() - 1) {
        text.append("\n");
      }
    }
    return text.toString();
  }
  */
} // end cwekakmeans