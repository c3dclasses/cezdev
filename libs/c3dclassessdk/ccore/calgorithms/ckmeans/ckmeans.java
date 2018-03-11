//--------------------------------------------------
// file: ckmeans
// desc: 
//--------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------
// name: ckmeans
// desc: 
//---------------------------------------------------------------------
public class ckmeans {
	static CArray _(int k, CArray items, int maxiterations) {
		CArray maxima = ckmeans.get_maxima_point(items);
		CArray minima = ckmeans.get_minima_point(items);
		CArray means = ckmeans.init_random_kmeans(items, k, maxima, minima);
		CArray clustersizes = _.carray_c(means.length());
		for(int i=0; i<clustersizes.length(); i++)
			clustersizes._(i, 0);
		CArray belongsto = _.carray_c(items.length()); 
		for(int i=0; i<belongsto.length(); i++)
			belongsto._(i, 0);
		ckmeans _this = new ckmeans();
		int index = -1;
		for(int i=0; i<maxiterations; i++) {
			boolean bnochange = true;
			for(int j=0; j<items.length(); j++) {
				CArray item = items._carray(j);
				index = ckmeans.classify(means, item);
				clustersizes._(index, clustersizes._int(index)+1);
				means._(index, ckmeans.update_mean(clustersizes._int(index), means._carray(index), item));
				if(index != belongsto._int(j)) {
					bnochange = false;
				} // end if
				belongsto._(j, index);
				
			} // end for
			if(bnochange) {	// nothing changed, so return
				break;
			}
		} // end for
		return means;
	} // end calculate_means()
	
	static CArray init_random_kmeans(CArray items, int k, CArray min, CArray max) {
		CArray means = _.carray_c(k, min.length());
		for(int i=0; i<means.length(); i++) {
			CArray mean = means._carray(i);
			for(int j=0; j<mean.length(); j++) {
				int mn = max._int(j);
				int mx = min._int(j);
				mean._(j, ckmeans.uniform(mn+1, mx-1));
			} // end for
		} // end for
		return means;		
	} // end init_random_kmeans()		
	
	static int uniform(int min, int max) {
		return min + (int)(Math.random() * (max - min)); 
	} // end uniform()
	
	static CArray update_mean(int n, CArray mean, CArray item) {
		for(int i=0; i<mean.length(); i++) {
			double m = mean._double(i);
			m = (double)((m*(n-1))+item._double(i))/(double)n;
			mean._(i, m); 
		} // end for
		return mean;
	} // end update_mean()
	
	static int classify(CArray means, CArray item) {
		int index = -1;
		double minimum = (double)Integer.MAX_VALUE;
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
		for(int i=0; i<xitem.length(); i++) {
			//if(xitem.isnumeric(i))
			double x = xitem._int(i);
			double y = yitem._int(i);
			//boolean bparsed = xitem._isparsed();
			//if(bparsed)
			sum += Math.pow(x - y, 2);
			//else sum += 0;
		}
		return Math.sqrt(sum);
	} // end euclidean_distance()
	
	static CArray get_maxima_point(CArray items) {
		CArray maximum_point = _.carray();
		for(int i=0; i<items.length(); i++) {
			CArray features = items._carray(i);
			for(int j=0; j<features.length(); j++) {
				int feature = features._int(j);
				boolean bparsed = features._isparsed();
				if(maximum_point._(j) == null) {
					maximum_point.push(features._(j));
				}
				else if(bparsed) {
					if(maximum_point._int(j) <= feature) {				
						//_.alert(maximum_point._int(j) + " <= " + feature); 
						maximum_point._(j, feature);
					}
				}				
				else {
					//_.alert(maximum_point._int(j) + " " + features._(j));
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
				int feature = features._int(j);
				boolean bparsed = features._isparsed();
				if(minimum_point._(j) == null) {
					minimum_point.push(features._(j));
				}
				else if(bparsed) { 
					if(minimum_point._int(j) >= feature) {
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
} // end ckmeans