package Shopper;

import org.testng.annotations.Test;

public class Concept {
	@Test
	public void btest() {
		System.out.println("Test B passed");
		System.out.println("Changes Btest");
	}
	
	@Test(dependsOnMethods="btest")
	public void atest() {
		System.out.println("Test A passed");
		System.out.println("Main");
	}
	
	@Test(dependsOnMethods="atest", invocationCount = 5, enabled = false)
	public void ctest() {
		System.out.println("Test C passed");
		System.out.println("Morning changed");
	}
}
