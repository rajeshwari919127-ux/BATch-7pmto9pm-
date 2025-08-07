package BaseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.ProPertiesUtility;
import genericUtilities.WebDriverUtility;

public class BaseClass {
	public WebDriver driver=null;	
	public static WebDriver sdriver=null;//listener
public	ProPertiesUtility putil = new ProPertiesUtility(); 
public	WebDriverUtility wutil = new WebDriverUtility(); 
	
	  @BeforeSuite(groups= {"smoke","regression"})
	  public void beforeSuite() {
		  System.out.println("DB connectivity open");
	  }

	  @BeforeClass(groups= {"smoke","regression"})
	  public void beforeClass() throws IOException {
		  
		  String BROWSER = putil.ToReadDataFromProprtiesFile("Browser"); 
		  
		  if (BROWSER.equals("Edge")) { 
				 
			   driver = new EdgeDriver(); 
			  } else if (BROWSER.equals("Chrome")) { 
			   driver = new ChromeDriver(); 
			  } else if (BROWSER.equals("Firefox")) { 
			   driver = new FirefoxDriver(); 
			  } 
			 sdriver=driver;
		  System.out.println("launch browser");
	  }

	  @BeforeMethod(groups= {"smoke","regression"})
	  public void beforeMethod() throws IOException {
		  String URL = putil.ToReadDataFromProprtiesFile("Url"); 
		  String USERNAME = putil.ToReadDataFromProprtiesFile("Username"); 
		  String PASSWORD = putil.ToReadDataFromProprtiesFile("Password"); 
		  driver.manage().window().maximize(); 
		  wutil.waitForPageToLoad(driver); 
		  driver.get(URL); 
		  
		  LoginPage lp= new LoginPage(driver);
		  lp.getUN().sendKeys(USERNAME);
		  lp.getPW().sendKeys(PASSWORD);
		  lp.getLoginBtn().click();
		  System.out.println("login");
	  }

	  @AfterMethod(groups= {"smoke","regression"})
	  public void afterMethod() {
		  HomePage hp =new HomePage(driver);
		  WebElement icon=hp.getUserIcon();
		  wutil.mouseHover(driver, icon);
		  WebElement logout=hp.getLogoutBtn();
		  wutil.clickOnWebElement(driver, logout);
	 
		  System.out.println("logout");
	  }
	  @AfterClass(groups= {"smoke","regression"})
	  public void afterClass() {
		 driver.quit();
		  System.out.println("close browser");
	  }

	  @AfterSuite(groups= {"smoke","regression"})
	  public void afterSuite() {
		    
		  System.out.println("db close");
}
}