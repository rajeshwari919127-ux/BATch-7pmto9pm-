package ImplementationofALL;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepository.CampaignPage;
import ObjectRepository.HomePage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.ProPertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateCampaignWithStatus extends BaseClass{
	@Test
	public void tocreateCampaignwithMandatoryFiles1() throws EncryptedDocumentException, IOException
	{

	ExcelFileUtility eutil = new ExcelFileUtility(); 
	JavaUtility jutil = new JavaUtility(); 

	
	  String campname = eutil.toreadDatafromExcelFile("campaign", 1, 2); 
	  String target = eutil.toreadDatafromExcelFile("campaign", 1, 3); 
	 
	  
			  // create campaign 
	  HomePage hp = new HomePage(driver); 
	  hp.getCreateCampBtn().click(); 
	  CampaignPage cp = new CampaignPage(driver); 
	  cp.getCampaignNameTF().sendKeys(campname); 
	  cp.getStausTF().sendKeys("pass"); 
	  cp.getTargetTF().sendKeys(target); 
	  cp.getCraetecampaignSubmitbtn().click();
	  
	  // validation 
	  WebElement toastmsg =cp.getToastmsg();	  
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
		
cp.getClosemsg().click();
	 
	 } 	

}
