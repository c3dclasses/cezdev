//--------------------------------------------------
// file: cphysics
// desc: defines a class containing algorthms 
//--------------------------------------------------
package c3dclasses;
import java.util.*;

//--------------------------------------------------------
// name: cphysics
// desc: defines a class containing algorthms 
//--------------------------------------------------------
public class cphysics {
	public static double get_cpoints_distance(CPoint cpoint1, CPoint cpoint2) {
		return Math.sqrt(Math.pow(cpoint1.x - cpoint2.x, 2) + Math.pow(cpoint1.y - cpoint2.y, 2) + Math.pow(cpoint1.z - cpoint2.z, 2));
	} // end get_cpoints_distance()
	
	public static double closest_cpoints(CArray cpoints) {
		return 0.0;
	} // end closest_cpoints()
		
	public static double closet_cpoints_rec(Object [] cpoints, int s, int e) {
		int n = e - s;
		// base case
		if(n <= 3)
			return cphysics.closet_cpoints_bf(cpoints, n);
		
		int mid = n / 2;		
		
		// divide
		CPoint mid_cpoint = (CPoint) cpoints[mid];
		double min_dist_left = cphysics.closet_cpoints_rec(cpoints, s, mid);
		double min_dist_right = cphysics.closet_cpoints_rec(cpoints, mid, e);
		
		// conquer
		double min_dist = Math.min(min_dist_left, min_dist_right);
		CPoint [] cpoint_strip = new CPoint[n];
    	int j = 0;
		for(int i = 0; i < n; i++) {
        	CPoint cpoint = (CPoint) cpoints[i];
			if(Math.abs(cpoint.x - mid_cpoint.x) < min_dist) {
            	cpoint_strip[j] = cpoint;
				j++;
			} // end if
		} // end for
		
		return 0;// Math.min(min_dist, cphysics.closet_cpoints_in_strip(cpoint_strip, j, min_dist));
	} // end closest_points() 
	
	public static double closet_cpoints_bf(Object [] cpoint, int n) {	
		double fmindist = Float.MAX_VALUE;
    	for (int i1 = 0; i1 < n; i1++)
        	for (int i2 = i1+1; i2 < n; i2++) {
            	CPoint cpoint1 = (CPoint) cpoint[i1];
				CPoint cpoint2 = (CPoint) cpoint[i2];		
				if (cphysics.get_cpoints_distance(cpoint1, cpoint2) < fmindist)
                	fmindist = cphysics.get_cpoints_distance(cpoint1, cpoint2);
			} // end for
    	return fmindist;
	} // end closest_points_bf
	
	/*
	// A utility function to find the distance beween the closest points of
	// strip of given size. All points in strip[] are sorted accordint to
	// y coordinate. They all have an upper bound on minimum distance as d.
	// Note that this method seems to be a O(n^2) method, but it's a O(n)
	// method as the inner loop runs at most 6 times
	static double closet_cpoints_in_strip(CPoint cpoint_strip[], int size, float min_dist)
	{
		float min = min_dist;  // Initialize the minimum distance as d
		csort.qs(cpoint_strip, new CFunction() { Object _(Object params) {
		}}); // end csort.qs() 
	 
		// Pick all points one by one and try the next points till the difference
		// between y coordinates is smaller than d.
		// This is a proven fact that this loop runs at most 6 times
		for (int i = 0; i < size; ++i)
			for (int j = i+1; j < size && (strip[j].y - strip[i].y) < min; ++j)
				if (dist(strip[i],strip[j]) < min)
					min = dist(strip[i], strip[j]);
	 
		return min;
	}
	*/
} // end __