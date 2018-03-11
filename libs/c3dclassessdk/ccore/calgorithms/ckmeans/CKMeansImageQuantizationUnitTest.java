//--------------------------------------------------
// file: CKMeansImageOuantizationUnitTest
// desc:
//--------------------------------------------------
package c3dclasses;
import org.junit.Test;
import org.junit.Ignore;
import java.awt.image.*;

//--------------------------------------------------------
// name: CKMeansImageOuantizationUnitTest
// desc:
//--------------------------------------------------------
public class CKMeansImageQuantizationUnitTest extends CUnitTest {
	@Test 
	public void test() {
		//String strinfilename = _.dir_path(this) + "\\files\\" + "tower-of-babel-large2.png";
		//String stroutfilename = _.dir_path(this) + "\\files\\" + "out-test333.png";
		String strinfilename = "C:/Users/developer/Desktop/cezdev2/libs/c3dclassessdk/ccore/calgorithms/ckmeans/files/tower-of-babel-large2.png";
		String stroutfilename = "C:/Users/developer/Desktop/cezdev2/libs/c3dclassessdk/ccore/calgorithms/ckmeans/files/out-test333.png";
		
		// load the image in
		CArray img = _.load_img_file(strinfilename);
		int bw = 2;
		int bh = 2;
		int nb = 6;
		int niterations = 20000;
		int h = img.length();
		int w = img._carray(0).length();
		
		// convert image to vector of nxn img blocks
		CArray blocks = convert_img_to_vectors(img, bw, bh);
		CArray output = cwekakmeans._(nb, blocks, niterations, "image-quantization");
		CArray codebook = output._carray(0);
		CArray clusterassignments = output._carray(1);
		CArray compressedblocks = compress_img_using_clusters(codebook, clusterassignments);
	
		CArray cbimg = convert_vectors_to_img(codebook,nb*bw, bh, bw, bh);
		_.save_img_file(_.dir_path(this) + "\\codebook.png", "png", cbimg);
	
	
		CArray cpimg = convert_vectors_to_img(compressedblocks, w, h, bw, bh);
		_.save_img_file(stroutfilename, "png", cpimg);
	
		
		//CArray img2 = convert_vectors_to_img(blocks, w, h, bw, bh);
		//_.save_img_file(stroutfilename, "png", img2);
		
		
		//_.out(c, clusters.toJSON(false));
		
		
		/*
		//CArray clusters = ckmeans.find_clusters(means, blocks);
		// convert the vector of nxn blocks back into an image
		*/
	//	CArray img2 = convert_vectors_to_img(blocks, w, h, bw, bh);
	//	_.save_img_file(stroutfilename, "png", img2);
		
		
	} // end test()
	
	//--------------------------------------------------------
	// name: compress_img_using_means()
	// desc:
	//--------------------------------------------------------
	public CArray compress_img_using_clusters(CArray clusters, CArray clusterassignments) {
		CArray compressed_blocks = _.carray();
		for(int i=0; i<clusterassignments.length(); i++) {
			int index = clusterassignments._int(i);
			compressed_blocks.push(clusters._(index));
		} // end for
		return compressed_blocks;
	} // end compress_img_using_clusters()
	
	//--------------------------------------------------------
	// name: convert_img_to_vectors()
	// desc: 
	//--------------------------------------------------------
	public CArray convert_img_to_vectors(CArray img, int bw, int bh) {
		if(img == null || img.length() < 1 || img._carray(0) == null && bw < 1 && bh < 1)
			return null;
		int nc = img.length();
		int nr = img._carray(0).length();
		int np = nr*nc;
		int nv = np/(bw*bh);
		int vsize = bw*bh;
		CArray vectors = _.carray();
		if(vectors == null)
			return null;
		for(int r=0; r<nr; r+=bh) {
			for(int c=0; c<nc; c+=bw) {
				CArray vector = _.carray();
				for(int br=r; br<r+bh; br++) {
					for(int bc=c; bc<c+bw; bc++) {
						vector.push(img._carray(br)._int(bc));
					} // end for
				} // end for
				vectors.push(vector);
			} // end for
		} // end for
		return vectors;
	} // end convert_img_to_vectors()

	//--------------------------------------------------------
	// name: convert_vectors_to_img()
	// desc: 
	//--------------------------------------------------------
	public CArray convert_vectors_to_img(CArray vectors, int w, int h, int bw, int bh) {
		if(vectors == null && vectors.length() <= 0 && vectors._carray(0) == null)
			return null;
		int nbr = w/bw;	// number of blocks per row
		int nbc = h/bh;	// number of blocks per column
		int npb = bw*bh; // number of pixels per block
		CArray img = _.carray_c(h,w);	// new image
		if(img == null)
			return null;
		for(int r=0; r<h; r+=bh) {
			for(int c=0; c<w; c+=bw) {
				int b = nbr * (int)Math.floor(r*0.5) + (int)Math.floor(c*0.5);
				for(int p=0; p<npb; p++) {
					int x = c+(p%bw); 
					int y = r+(p/bh);
					//_.alert(vectors._carray(b)._(p));
					img._carray(y)._(x, vectors._carray(b)._int(p));
				} // end for
			} // end for
		} // end for
		return img;
	} // end convert_vectors_to_img()
} // end CKMeansImageOuantizationUnitTest