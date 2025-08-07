package testNGPractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo {

	@Test
	public void a11()
	{
		Reporter.log("all executed",true);
	}
    @Test(enabled = false)
   	public void a23()
   	{
   		Reporter.log("a23 executed",true);
   	}
    @Test
   	public void b25()
   	{
   		Reporter.log("b25 executed",true);
   	}
    @Test
   	public void b16()
   	{
   		Reporter.log("b16 executed",true);
   	}
    @Test
   	public void a17()
   	{
   		Reporter.log("a17 executed",true);
   	}
}
