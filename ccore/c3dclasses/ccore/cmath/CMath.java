//---------------------------------------------------------------------------------
// file: CMath
// desc: 
//---------------------------------------------------------------------------------
package c3dclasses;

//---------------------------------------------------------------------------------
// name: CMath
// desc: 
//---------------------------------------------------------------------------------
public class CMath {	
	private static double ZERO_EPSILONE = 0.001f;
	
	public static  void 	episolone(double e) { ZERO_EPSILONE = e; }
	public static  double 	zero(double v){ return (Math.abs(v)<ZERO_EPSILONE) ? 0 : v; }
	public static  boolean 	equal(double x, double y) { return Math.abs(x - y) < CMath.ZERO_EPSILONE; }
	public static  boolean 	within(double value, double min, double max) { return (min <= value && value <= max); }

	public static  void 	initialize(long s) { StdRandom.setSeed(s); }
	public static  double 	random(){ return StdRandom.random(); }
	public static  int	 	uniform(int N) { return StdRandom.uniform(N); }		
	public static  double 	uniform(double min, double max) { return StdRandom.uniform(min, max); }	
	public static  int	 	uniform(int min, int max) { return StdRandom.uniform(min, max); }		
	public static  boolean	bernoulli(double p) { return StdRandom.bernoulli(p); }		
	public static  double 	gaussian(){ return StdRandom.gaussian(); }
	public static  double 	gaussian(double m, double s){ return StdRandom.gaussian(m,s); }
	
	public static boolean 	isPrime(int n) {
		if(n < 2)
			return false;
		for(int i=2; i*i<=n; i++)
			if(n%i == 0)
				return false;
		return true;
	} // isPrime()
	
	public static  double 	harmonic(int n){ 
		double sum = 0.0;
		for(int i=1; i<=n; i++)
			sum += (1.0 / i);
		return sum;
	} // end harmonic`
} // end CMath