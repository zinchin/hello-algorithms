package telran.recursion;

public class LineRecursion {
public static long factorial(int n) {
	
    if(n==0) {
    	return 1;
    }
	return n*factorial(n-1);
}
/**
 * 
 * @param a either negative or positive
 * @param b positive
 * @return a^b
 */
/**public static long pow(int a, int b) {
	if(b==0) {
		return 1;
	}
	//TODO
	//no * / allowed
	// no cycles  
	return a*pow(a,b-1);
}**/

public static long pow(long a, long b) {
	if (b<0) {
		throw new IllegalArgumentException("no correct argument");}
	if(b==0) {
		return 1;
	}
	return mult(a,pow(a,b-1));
}

public static long mult(long a,long b) {
	if (b==0) {
		return 0; 
	}
	return b<0 ? +mult(-a,-b) : a+mult(a,b-1);

	}


public static long square(int x) {
	
	return pow(x,2);
}
/**
 * 
 * @param ar - array integer numbers
 * @return sum of all numbers from given array
 */
public static int sum(int ar[]) {
	
	
	return sum(0,ar);
	
}
private static int sum(int firstIndex, int[] ar) {
	if (firstIndex >=ar.length) {
		return 0;
	}
	return ar[firstIndex]+sum(firstIndex+1,ar);
}

}
