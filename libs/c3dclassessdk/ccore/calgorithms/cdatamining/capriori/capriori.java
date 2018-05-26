//--------------------------------------------------
// file: capriori
// desc: implements the apriori algorithm
//--------------------------------------------------
package c3dclasses;

//--------------------------------------------------------
// name: capriori
// desc: implements the apriori algorithm
//--------------------------------------------------------
public class capriori {
	static public CArray _(CArray D, int minsup) {
		CArray L = _.carray();
		L.push(capriori._large_1_itemset(D, minsup));
		for(int k=1; L._(k-1) != null; k++) {
			CHash Ck = capriori._gen(L._chash(k-1));	
			for(int i=0; i<D.length(); i++) {
				CArray t = D._explode(i, ",");
				CHash Ct = capriori._subset(Ck,t);
				CArray ct = Ct.keys();
				for(int j=0; j<ct.length(); j++) {
					Ck._(ct._string(j), Ck._int(ct._string(j)) + 1);
				} // end for 		
			} // end for
			Ck = capriori._large(Ck, minsup);
			_.println("Completed Frequent Itemset of length " + L.length() + " of " + Ck.size() + " items.");		
			_.set_file_contents("C:/Users/developer/Desktop/cezdev/libs/c3dclassessdk/cnotes/cmodels/cdatamining/hw1/Lk" + (L.length() + 1) + "-output.txt", Ck.toString());
			L.push((Ck.size()>0)?Ck:null);	
		} // end for
		return L;		
	} // _()
	
	static public CHash _subset(CHash Ck, CArray t_items) {
		CHash Ct = _.chash();
		//CArray t_items = t;//._explode(0, ",");
		CArray c = Ck.keys();
		for(int i=0; i<c.length(); i++) {
			CArray c_items = c._explode(i, ",");						
			CArray u_items = c_items.union(t_items);
			if(u_items.length() != t_items.length())
				continue;	
			boolean bsubset=true;
			String str_items = u_items.join(",");
			for(int j=0; j<u_items.length(); j++) {
				//_.alert(u_items._string(j) + "\n" + t_items._string(j));
				if(str_items.indexOf(t_items._string(j)) == -1) {
					bsubset = false;
					break;
				} // end if	
			} // end for
			if(bsubset)
				Ct._(c._(i), 0);
		} // end for
		return Ct;
	} // end _subset()
	
	static public CHash _large_1_itemset(CArray D, int minsup) {
		CHash C = _.chash();
		for(int i=0; i<D.length(); i++) {
			CArray items = D._explode(i, ",");
			//int TID = T._int(0);
			//CArray items = T._explode(0, ",");
			for(int j=0; j<items.length(); j++) {
				String stritem = items._string(j);
				if(C._(stritem) != null)
					C._(stritem, C._int(stritem) + 1);
				else C._(stritem, 1);
			} // end for
		} // end for
		return capriori._large(C, minsup);
	} // end large_1_itemset()
	
	static public CHash _gen(CHash L) {
		// joining
		CArray itemsets = L.keys();
		CHash C = _.chash(); 
		for(int i=0; i<itemsets.length(); i++) {
			CArray items1 = itemsets._explode(i, ",");
			int itemsize = items1.length();
			for(int j=i+1; j<itemsets.length(); j++) {
				CArray items2 = itemsets._explode(j, ",");	
				CArray items = items1.union(items2);
				boolean bprun = false;
				if(items.length() == itemsize + 1) {
					CArray subsets = items.powerset(itemsize);
					// pruning
					for(int s=0; s<subsets.length(); s++) {
						if(L._(subsets._carray(s).join(",")) == null)
							bprun = true;
					} // end for()
					if(bprun == false)
						C._(items.join(","),0);
				} // end if
			} // end for
		} // end for()
		return C;
	} // end _gen()
	
	static public CHash _large(CHash C, int minsup) {
		CHash L = _.chash();
		CArray keys = C.keys();
		for(int i=0; i<keys.length(); i++) {	
			String key = keys._string(i);
			if(C._int(key) >= minsup)
				L._(key, C._(key));
		} // end for
		return L;
	} // end _large()
} // end capriori