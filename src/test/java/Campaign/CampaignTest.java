package Campaign;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepository.CampaignPage;
import ObjectRepository.HomePage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
@Listeners(ListenerUtilities.ListenerImplementation.class)
public class CampaignTest extends BaseClass {

		@Test(groups= "smoke")
			public void tocreateCampaignwithMandatoryFilesTest() throws EncryptedDocumentException, IOException
			{	
			ExcelFileUtility eutil = new ExcelFileUtility(); 
			  JavaUtility jutil = new JavaUtility(); 
			 
			  String campname = eutil.toreadDatafromExcelFile("Campaign", 1, 2); 
			  String target = eutil.toreadDatafromExcelFile("Campaign", 1, 3); 
			 
			  // create campaign 
			  HomePage hp = new HomePage(driver); 
			  hp.getCreateCampBtn().click(); 
			  CampaignPage cp = new CampaignPage(driver); 
			  cp.getCampaignNameTF().sendKeys(campname); 
			  cp.getTargetTF().sendKeys(target); 
			  cp.getCraetecampaignSubmitbtn().click(); 
			 
			  // validation 
			  WebElement toastmsg = cp.getToastmsg(); 
			  wutil.waitForVisibilityOfElement(driver, toastmsg); 
			  String msg = toastmsg.getText(); 
			 
			  if (msg.contains(campname)) { 
			   System.out.println("campaign created"); 
			  } 
			 
			  else { 
			   System.out.println("campaign not created"); 
			  } 
			  cp.getClosemsg().click();
				 
				 } 		
	
	
	
		@Test(groups= "smoke")
		public void tocreateCampaignwithMandatoryFiles1Test() throws EncryptedDocumentException, IOException
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
	
	
		@Test(groups= "regression")
		public void tocreateCampaignwithExpectedDAteTest() throws EncryptedDocumentException, IOException, InterruptedException
		{
			ExcelFileUtility eutil = new ExcelFileUtility(); 
			JavaUtility jutil = new JavaUtility(); 
			
			  String campname = eutil.toreadDatafromExcelFile("Campaign", 1, 2); 
			  String target = eutil.toreadDatafromExcelFile("Campaign", 1, 3); 
			 
			  HomePage hp= new HomePage(driver);
			  hp.getCreateCampBtn().click();
			  CampaignPage cp=new CampaignPage(driver);
			  cp.getCampaignNameTF().sendKeys(campname);
			  cp.getTargetTF().sendKeys(target);	
			  
			  String daterequired = jutil.togetRequiredData(30);	 
			  WebElement expClosedate = cp.getDateTF(); 
			  wutil.passInput(driver, expClosedate, daterequired); 
			 
			  cp.getCraetecampaignSubmitbtn().click();
			 
			  // validation 
			  WebElement toastmsg =cp.getToastmsg();
			  
		  wutil.waitForVisibilityOfElement(driver, toastmsg); 
		  String msg = toastmsg.getText(); 
	   	Assert.assertTrue(msg.contains(campname));				
		cp.getClosemsg().click();
			 
		
		}
	
	

		
	
}
