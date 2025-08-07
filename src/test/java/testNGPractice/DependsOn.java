package testNGPractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class DependsOn {

	@Test
	public void addCart() {
		Reporter.log("added",true);
	}
	@Test(dependsOnMethods = "addCart")
	public void editCart() {
		Reporter.log("edit",true);
	}
	@Test(dependsOnMethods = {"addCart","editCart"})
	public void deleteCart() {
		Reporter.log("added",true);
	}
}
