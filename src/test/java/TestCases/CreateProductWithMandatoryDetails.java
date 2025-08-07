package TestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.ProPertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateProductWithMandatoryDetails {

	public static void main(String[] args) throws IOException {

		
		WebDriver driver=new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	//login
	driver.get("http://49.249.28.218:8098/");
	driver.findElement(By.id("username")).sendKeys("rmgyantra");
	driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	driver.findElement(By.xpath("//button[text()='Sign In']")).click();
	
	//create product
	Actions act=new Actions(driver);

	WebElement product = driver.findElement(By.xpath("//a[text()='Products']"));
	act.moveToElement(product).click().perform();		
	driver.findElement(By.xpath("//span[text()='Add Product']")).click();
	driver.findElement(By.name("productId")).sendKeys("1234");
	driver.findElement(By.name("productName")).sendKeys("Fan11");
	
	WebElement Category = driver.findElement(By.name("productCategory"));
	Category.click();
	Select select = new Select(Category);
	select.selectByValue("Electronics");
	
	driver.findElement(By.name("quantity")).sendKeys("4");
	
	WebElement price = driver.findElement(By.name("price"));
	price.clear();
	price.sendKeys("7");
		
	WebElement venId = driver.findElement(By.name("vendorId"));
	venId.click();
	Select select1 = new Select(venId);
	select1.selectByValue("VID_094");		
	driver.findElement(By.xpath("//button[text()='Add']")).click();
	
	//Validation
	
			WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(toastmsg));
			String msg=toastmsg.getText();
			
			if(msg.contains("Fan11"))
			{
				System.out.println("Product Created");
			}
			else
			{
				System.out.println("Product not created");
			}
		
			driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	

			//logout
			WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
			act.moveToElement(icon).perform();
			WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
			act.moveToElement(logout).click().perform();
			driver.quit();

}
}


/*	
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(http://By.id("username")).sendKeys("rmgyantra");
		driver.findElement(http://By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Add Product')]")).click();
		driver.findElement(http://By.name("productName")).sendKeys("Iphone 16");
		
		WebElement categorydropdown=driver.findElement(http://By.name("productCategory"));
		//Dropdown 1
		Select drop1=new Select(categorydropdown);
		drop1.selectByValue("Electricals");
		WebElement quantity=driver.findElement(http://By.name("quantity"));
		quantity.clear();
		quantity.sendKeys("7");
		
		WebElement price=driver.findElement(http://By.name("price"));
		quantity.clear();
		price.sendKeys("89000");
		//DropDown 2
		WebElement vendordropdown=driver.findElement(http://By.name("vendorId"));
		Select drop2=new Select(vendordropdown);
		drop2.selectByValue("VID_007");
		driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
 */
