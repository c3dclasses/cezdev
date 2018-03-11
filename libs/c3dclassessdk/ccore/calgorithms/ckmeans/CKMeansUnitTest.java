//--------------------------------------------------
// file: CKMeansUnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;
import java.awt.image.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
 
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

//--------------------------------------------------------
// name: CKMeansUnitTest
// desc:
//--------------------------------------------------------
public class CKMeansUnitTest extends CUnitTest {
	@Test 
	public void test() throws Exception {
		
		/*
		SimpleKMeans kmeans = new SimpleKMeans();
 
		kmeans.setSeed(10);
 
		//important parameter to set: preserver order, number of cluster.
		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(2);
		
		String strfilename = _.dir_path(this) + "/files/ckmeans-dataset-test.arff";
		//BufferedReader datafile = readDataFile("C:/Users/developer/Desktop/weka-3-7-12/data/labor.arff");
		BufferedReader datafile = readDataFile(strfilename);
		
		Instances data = new Instances(datafile);
 
 
		kmeans.buildClusterer(data);
 
		// This array returns the cluster number (starting with 0) for each instance
		// The array has as many elements as the number of instances
		int[] assignments = kmeans.getAssignments();
 
		int i=0;
		for(int clusterNum : assignments) {
		    System.out.printf("Instance %d -> Cluster %d \n", i, clusterNum);
		    i++;
		}
		
		// getClusterCentroids
		_.out(this, "");
		_.outln(this, kmeans.getClusterCentroids());
		_.outln(this, "");
		_.outln(this, kmeans);
		*/
		/*
		cwekakmeans km = new cwekakmeans();
		CArray items = _.load_csv_file(_.dir_path(this) + "/files/ckmeans-dataset-test.txt");
		//cwekakmeans.generate_dataset_file(items,"simple-dataset");			
		CArray clusters = cwekakmeans._(2, items, 200, "simple-dataset");
		_.out(km, clusters.toJSON(false));
		*/
		
	
		/*
		_.out(this, items.toJSON(false));
		_.out(this, "\n\n");
		CArray means = ckmeans._(2, items, 200);
		_.out(this, means.toJSON(false));		
		
		CArray clusters = ckmeans.find_clusters(means, items);
		_.out_append(km, "\n");
		_.out_append(km, clusters.toJSON(false));
		*/
		
		//_.alert(ckmeans.get_maxima_point(dataset) + "\n" + ckmeans.get_minima_point(dataset));
		//CArray means = _.carray_c(5, 2, 3);
		//_.alert(means);
		//_.alert("Uniform: " + ckmeans.uniform(2.3,9.5));
		//_.alert(dataset);
		//_.out(this,means.toJSON(false));
		//_.out_append(this, "\n");
		//_.out_append(this,clusters.toJSON(false));
		//_.alert(this.getClass().getClassLoader().getResource("").getPath());
		//_.alert(_.file(this));
		//_.alert(_.get_home_path());
		//_.out(this,img.toJSON(false));
		//_.save_img_file(_.dir_path(this) + "\\" + "test-array2.png", "png", img);
		//_.out(this, " " + blocks.length());
		//_.out(this, blocks._carray(0).toJSON(false));
		//_.out_append(this, blocks._carray(1).toJSON(false));
		//_.out_append(this, blocks._carray(2).toJSON(false));
		//_.out_append(this, blocks._carray(3).toJSON(false));
		//_.out_append(this, blocks._carray(4).toJSON(false));
		//_.out_append(this, blocks._carray(5).toJSON(false));
		//_.out_append(this, blocks._carray(6).toJSON(false));
		//_.out_append(this, blocks._carray(blocks.length()-1).toJSON(false));
		//_.out_append(this, "\n");
		//_.out_append(this, blocks.length() + " " + blocks._carray(0).length());
	} // end test()
	

} // end CKMeansUnitTest