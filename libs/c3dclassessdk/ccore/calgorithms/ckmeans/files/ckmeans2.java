//--------------------------------------------------
// file: ckmeans
// desc: 
//--------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------
// name: ckmeans
// desc: http://stanford.edu/~cpiech/cs221/handouts/kmeans.html
//---------------------------------------------------------------------
public class ckmeans2 {
	/*
	static CArray _(CArray items, int k) {
    	//CArray kpoints = ckmeans.get_random_kpoints(dataset, k);
		
		//f
		
		///int iterations = 0
    	//CArray oldcentroids = null;
		// Run the main k-means algorithm
		//while not ckmeans.should_stop(oldCentroids, centroids, iterations) {
        //	// Save old centroids for convergence test. Book keeping.
        //	oldCentroids = centroids
        //	iterations++;
        	// Assign labels to each datapoint based on centroids
        //	labels = ckmeans.get_labels(dataSet, centroids)
        	// Assign centroids based on datapoint labels
        //	centroids = ckmeans.get_centroids(dataset, labels, k)
		//} // end while()
    	// We can get the labels too by calling getLabels(dataSet, centroids)
    	//return centroids
		return null;
	} // end _()
	

	static CArray _(int k, CArray items, int maxiterations) {
		CArray maxima = ckmeans.get_maxima_point(items);
		CArray minima = ckmeans.get_minima_point(items);
		CArray means = ckmeans.init_random_kmeans(items, k, maxima, minima);
		CArray clustersizes = _.carray_c(means.length());
		CArray belongsto = _.carray_c(items.length()); 
		int index = -1;
		for(int i=0; i<maxiterations; i++) {
			boolean bnochange = true;
			for(int j=0; j<items.length(); j++) {
				CArray item = items._carray(j);
				index = ckmeans.classify(means, item);
				clustersizes._(index, clustersizes._int(index)+1);
				means._(index, ckmeans.update_mean(clustersizes._int(index), means._carray(index), item));
				if(index != belongsto._int(i)) {
					bnochange = false;
					belongsto._(i, index);
				} // end if
				if(bnochange)	// nothing changed, so return
					break;
			} // end for
		} // end for
		return means;
	} // end calculate_means()
		
	static CArray init_random_kmeans(CArray items, int k, CArray min, CArray max) {
		CArray means = _.carray_c(k, min.length());
		for(int i=0; i<means.length(); i++) {
			CArray mean = means._carray(i);
			for(int j=0; j<mean.length(); j++) {
				double mn = max._double(j);
				double mx = min._double(j);
				if(min._isparsed())
					mean._(j, ckmeans.uniform(mn+1, mx-1));
				else mean._(j, max._(j)); 
			} // end for
		} // end for
		return means;		
	} // end init_random_kmeans()		
	
	static double uniform(double min, double max) {
		return min + (Math.random() * (max - min)); 
	} // end uniform()
	
	static CArray update_mean(int n, CArray mean, CArray item) {
		for(int i=0; i<mean.length(); i++) {
			double f = mean._double(i);
			if(mean._isparsed())
				continue;
			f = (f*((double)n-1)+item._double(i))/(double)n;
			mean._(i, f); 
		} // end for
		return mean;
	} // end update_mean()
	
	static int classify(CArray means, CArray item) {
		int index = -1;
		double minimum = (double) Integer.MAX_VALUE;
		for(int i=0; i<means.length(); i++) {
			double dis = ckmeans.euclidean_distance(item, means._carray(i));
			if(dis < minimum) {
				minimum = dis;
				index = i;
			} // end if
		} // end for
		return index;
	} // end classify()
	
	static double euclidean_distance(CArray xitem, CArray yitem) {
		double sum = 0;
		for(int i=0; i<xitem.length(); i++) 
			sum += Math.pow(xitem._double(i) - xitem._double(i), 2);
		return Math.sqrt(sum);
	} // end euclidean_distance()
	
	static CArray get_maxima_point(CArray items) {
		CArray maximum_point = _.carray();
		for(int i=0; i<items.length(); i++) {
			CArray features = items._carray(i);
			for(int j=0; j<features.length(); j++) {
				double feature = features._double(j);
				boolean bparsed = features._isparsed();
				if(maximum_point._(j) == null) {
					maximum_point.push(features._(j));
				}
				else if(bparsed) {
					if(maximum_point._double(j) <= feature) {				
						//_.alert(maximum_point._double(j) + " <= " + feature); 
						maximum_point._(j, feature);
					}
				}				
				else {
					//_.alert(maximum_point._double(j) + " " + features._(j));
					maximum_point._(j, features._(j));
				}
			} // end for
		} // end for	
		return maximum_point;
	} // end get_maxima_point()
	
	static CArray get_minima_point(CArray items) {
		CArray minimum_point = _.carray();
		for(int i=0; i<items.length(); i++) {
			CArray features = items._carray(i);
			for(int j=0; j<features.length(); j++) {
				double feature = features._double(j);
				boolean bparsed = features._isparsed();
				if(minimum_point._(j) == null) {
					minimum_point.push(features._(j));
				}
				else if(bparsed) { 
					if(minimum_point._double(j) >= feature) {
						minimum_point._(j, feature);
					}
				}
				else {
					 minimum_point._(j, features._(j));
				}
			} // end for
		} // end for	
		return minimum_point;
	} // end get_minima_point()
	
	static CArray find_clusters(CArray means, CArray items) {
		CArray clusters = _.carray_c(means.length());
		for(int i=0; i<clusters.length(); i++)
			clusters._(i, _.carray());
		for(int i=0; i<items.length(); i++) {
			int index = ckmeans.classify(means, items._carray(i));
			clusters._carray(index).push(items._carray(i));
		} // end for
		return clusters;
	} // end find_clusters()
	*/
} // end ckmeans