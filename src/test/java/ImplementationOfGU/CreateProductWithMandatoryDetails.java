package ImplementationOfGU;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

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

import ObjectRepository.CampaignPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.ProductPage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.ProPertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateProductWithMandatoryDetails {

	public static void main(String[] args) throws IOException, InterruptedException {

				
				ProPertiesUtility putil = new ProPertiesUtility(); 
				ExcelFileUtility eutil = new ExcelFileUtility(); 
				JavaUtility jutil = new JavaUtility(); 
				WebDriverUtility wutil = new WebDriverUtility(); 			
				
				  String BROWSER = putil.ToReadDataFromProprtiesFile("Browser"); 
				  String URL = putil.ToReadDataFromProprtiesFile("Url"); 
				  String USERNAME = putil.ToReadDataFromProprtiesFile("Username"); 
				  String PASSWORD = putil.ToReadDataFromProprtiesFile("Password"); 
				 
				  String productName = eutil.toreadDatafromExcelFile("Product", 1, 2); 
				  String quantity = eutil.toreadDatafromExcelFile("Product", 1, 5); 
				  String price = eutil.toreadDatafromExcelFile("Product", 1, 6); 

								 
				  WebDriver driver = null; 
				 
				  if (BROWSER.equals("Edge")) { 
				 
				   driver = new EdgeDriver(); 
				  } else if (BROWSER.equals("Chrome")) { 
				   driver = new ChromeDriver(); 
				  } else if (BROWSER.equals("Firefox")) { 
				   driver = new FirefoxDriver(); 
				  } 
				  driver.manage().window().maximize(); 
				  wutil.waitForPageToLoad(driver); 
				  driver.get(URL); 
				  LoginPage lp= new LoginPage(driver);
				  ProductPage pp=new ProductPage(driver);
				  CampaignPage cp=new CampaignPage(driver);

					 lp.getUN().sendKeys(USERNAME);
					 lp.getPW().sendKeys(PASSWORD);
					 lp.getLoginBtn().click();
				  Thread.sleep(2000);
				 		
				//create product
		           HomePage hp=new HomePage(driver); 
	                hp.getProduct().click(); 
	               pp.getADDButton().click();

//				WebElement product = driver.findElement(By.xpath("//a[text()='Products']"));
//				wutil.mouseHover(driver, product);
//				driver.findElement(By.xpath("//span[text()='Add Product']")).click();
				  pp.getProductID().sendKeys("1222");
				  
				 pp.getProductName().sendKeys(productName+jutil.togetRandomNumber());
				WebElement Category = pp.getProCategory();
				Category.click();
				wutil.select(Category,"Electronics" );
				
				pp.getQuantity().sendKeys(quantity);
				
				WebElement price1 = pp.getPrice();
				price1.clear();
				price1.sendKeys(price);
				
				WebElement venId = pp.getVendorID();
				venId.click();
				wutil.select(venId, "VID_094");		
				pp.getCreateProductBtn().click();
				
				Thread.sleep(2000);
				
				//validation
				 WebElement toastmsg =cp.getToastmsg();			  
				  wutil.waitForVisibilityOfElement(driver, toastmsg); 
					  String msg = toastmsg.getText(); 
					 
					  if (msg.contains(productName+jutil.togetRandomNumber())) 
					{
						System.out.println("Product Created");
					}
					else
					{
						System.out.println("Product not created");
					}
						
				cp.getClosemsg().click();
				//logout
				hp.getLogoutBtn().click(); 
				
			   // close browser 
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
