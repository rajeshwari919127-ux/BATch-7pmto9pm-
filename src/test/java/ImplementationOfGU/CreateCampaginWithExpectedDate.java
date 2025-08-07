package ImplementationOfGU;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ObjectRepository.CampaignPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.ProPertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateCampaginWithExpectedDate {

	public static void main(String[] args) throws IOException {
		ProPertiesUtility putil = new ProPertiesUtility(); 
		ExcelFileUtility eutil = new ExcelFileUtility(); 
		JavaUtility jutil = new JavaUtility(); 
		WebDriverUtility wutil = new WebDriverUtility(); 
		 
		  String BROWSER = putil.ToReadDataFromProprtiesFile("Browser"); 
		  String URL = putil.ToReadDataFromProprtiesFile("Url"); 
		  String USERNAME = putil.ToReadDataFromProprtiesFile("Username"); 
		  String PASSWORD = putil.ToReadDataFromProprtiesFile("Password"); 
		 
		  String campname = eutil.toreadDatafromExcelFile("Campaign", 1, 2); 
		  String target = eutil.toreadDatafromExcelFile("Campaign", 1, 3); 
		 
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
			 lp.getUN().sendKeys(USERNAME);
			 lp.getPW().sendKeys(PASSWORD);
			 lp.getLoginBtn().click();
		 		 
		  // create campaign 
			 HomePage hp=new HomePage(driver);
			 hp.getCreateCampBtn().click();
			 CampaignPage cp=new CampaignPage(driver);
			
			 cp.getCampaignNameTF().sendKeys(campname);
			 cp.getTargetTF().sendKeys(target);
							
		  String daterequired = jutil.togetRequiredData(30);
		  WebElement expClosedate =cp.getDateTF();				    
		  wutil.passInput(driver, expClosedate, daterequired); 				   
		  cp.getCraetecampaignSubmitbtn().click();
								  
		  		 
		  // validation 
		  WebElement toastmsg = cp.getToastmsg(); 
		  wutil.waitForVisibilityOfElement(driver, toastmsg); 
		  String msg = toastmsg.getText(); 
		 
		  if (msg.contains(campname)) 
		{
			System.out.println("Campaing Created");
		}
		else
		{
			System.out.println("Campaign not created");
		}
	
		  cp.getClosemsg();
		
		//logout
		driver.quit();
	
	}

}
