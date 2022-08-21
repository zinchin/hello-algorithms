package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.recursion.LineRecursion.*;

import org.junit.jupiter.api.Test;

//import telran.recursion.LineRecursion;

class RecursionTest {
	int count=0;
	@Test
	void factorialTest() {
		assertEquals(24,factorial(4));
	}
	
	@Test
	void powTest() {
	assertEquals(1000,pow(10, 3));	
	assertEquals(-1000,pow(-10, 3));
//	assertEquals(9,pow(-3, 2));
   // assertEquals(100,pow(-10, 2));
	}
	
	@Test
	void sumTest() {
		int ar[] = {1,2,3,4};
		assertEquals(10, sum(ar));
	}
	
	@Test
	void squareTest() {
		assertEquals(4, square(-2));
		assertEquals(9, square(3));
		
	} 

	@Test
	void multTest() {
		assertEquals(-6, mult(-3,2));
		assertEquals(6, mult(3,2));
		
	} 
	
	//void test() {
	//	f();
	//	System.out.println(count);
	//}
    
	
	
	//private void f() {
	//	if (Math.random()<0.99) {
	//	f();
	//	count++;
	//	}
		
	//}
	
	

}
