package ImplementationofALL;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepository.CampaignPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.ProductPage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;

public class CreateProductWithMandatoryDetails extends BaseClass{
	@Test
	public void toCreateProductWithMandatoryDetails() throws EncryptedDocumentException, IOException
	{
		
		ExcelFileUtility eutil = new ExcelFileUtility(); 
		JavaUtility jutil = new JavaUtility(); 
		
		 String productName = eutil.toreadDatafromExcelFile("Product", 1, 2); 
		  String quantity = eutil.toreadDatafromExcelFile("Product", 1, 5); 
		  String price = eutil.toreadDatafromExcelFile("Product", 1, 6); 
		  
		  ProductPage pp=new ProductPage(driver);
		  CampaignPage cp=new CampaignPage(driver);

		
		 		
		//create product
           HomePage hp=new HomePage(driver); 
            hp.getProduct().click(); 
            pp.getADDButton().click();
            
           // pp.getProductID().sendKeys("1222");			  
			 pp.getProductName().sendKeys(productName+jutil.togetRandomNumber());
			WebElement Category = pp.getProCategory();
			Category.click();
			wutil.select(Category,3);
			
			pp.getQuantity().sendKeys(quantity);
			
			WebElement price1 = pp.getPrice();
			price1.clear();
			price1.sendKeys(price);
			
			WebElement venId = pp.getVendorID();
			venId.click();
			wutil.select(venId,4);		
			pp.getCreateProductBtn().click();
			
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
		
	}
	
}

	

