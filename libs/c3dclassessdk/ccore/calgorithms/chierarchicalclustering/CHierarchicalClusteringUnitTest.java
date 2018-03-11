//--------------------------------------------------
// file: CHierarchicalClusteringUnitTest
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

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import weka.clusterers.HierarchicalClusterer;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.EuclideanDistance;
import weka.core.Instances;
import weka.gui.hierarchyvisualizer.HierarchyVisualizer;
import org.junit.Ignore;

//--------------------------------------------------------
// name: CHierarchicalClusteringUnitTest
// desc:
//--------------------------------------------------------
public class CHierarchicalClusteringUnitTest extends CUnitTest {
	@Ignore
	@Test 
	public void test() throws Exception {
		// Instantiate clusterer
		HierarchicalClusterer clusterer = new HierarchicalClusterer();
		clusterer.setOptions(new String[] {"-L", "COMPLETE"});
		clusterer.setDebug(true);
		clusterer.setNumClusters(2);
		clusterer.setDistanceFunction(new EuclideanDistance());
		clusterer.setDistanceIsBranchLength(true);
		
		// Build dataset
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute("A"));
		attributes.add(new Attribute("B"));
		attributes.add(new Attribute("C"));
		Instances data = new Instances("Weka test", attributes, 3);
		
		// Add data
		data.add(new DenseInstance(1.0, new double[] { 1.0, 0.0, 1.0 }));
		data.add(new DenseInstance(1.0, new double[] { 0.5, 0.0, 1.0 }));
		data.add(new DenseInstance(1.0, new double[] { 0.0, 1.0, 0.0 }));
		data.add(new DenseInstance(1.0, new double[] { 0.0, 1.0, 0.3 }));
		
		// Cluster network
		clusterer.buildClusterer(data);
		
		// Print normal
		clusterer.setPrintNewick(false);
		System.out.println(clusterer.graph());
		// Print Newick
		clusterer.setPrintNewick(true);
		System.out.println(clusterer.graph());
		
		// Let's try to show this clustered data!
		JFrame mainFrame = new JFrame("Weka Test");
		mainFrame.setSize(600, 400);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = mainFrame.getContentPane();
		content.setLayout(new GridLayout(1, 1));
		
		HierarchyVisualizer visualizer = new HierarchyVisualizer(clusterer.graph());
		content.add(visualizer);
		
		mainFrame.setVisible(true);
	} // end test()
} // end CHierarchicalClusteringUnitTest