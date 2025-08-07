package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToCreateCampaginWithAllDetails {
	public static void main(String[] args) throws IOException, InterruptedException {
//read data from properties
	FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Commondata.properties");	
	Properties prop= new Properties();	
	prop.load(fis);	
	String BROWSER = prop.getProperty("Browser");
	String URL = prop.getProperty("Url");
	String USERNAME = prop.getProperty("Username");
	String PASSWORD = prop.getProperty("Password");
//
	//Random number
	Random ran = new Random();
	int rancount=ran.nextInt(1000);	
	
// read data from excel
FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\Testscriptdat.xlsx");
Workbook wb = WorkbookFactory.create(fis1);
Sheet sh = wb.getSheet("Campaign");

String campname = sh.getRow(1).getCell(2).getStringCellValue();
String targetsize = sh.getRow(1).getCell(3).getStringCellValue();
wb.close();

//date
Date date=new Date();
SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
sim.format(date);
Calendar cal=sim.getCalendar();
cal.add(Calendar.DAY_OF_MONTH,30);
String daterequired = sim.format(cal.getTime());

	WebDriver driver=null;
	if(BROWSER.equals("Edge"))
	{
	  driver=new EdgeDriver();	
	}
	else 		if(BROWSER.equals("Chrome"))
	{
		driver=new ChromeDriver();
	}
	else		if(BROWSER.equals("Firefox"))
	{
		driver=new FirefoxDriver();
	}
	else
	{ driver=new EdgeDriver(); 

	}
	driver.manage().window().maximize();
	driver.get(URL);
	driver.findElement(By.id("username")).sendKeys(USERNAME);
	driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
	driver.findElement(By.xpath("//button[text()='Sign In']")).click();	
	
	//Create Campaign
	
	driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
	driver.findElement(By.name("campaignName")).sendKeys(campname+rancount);
	WebElement target = driver.findElement(By.name("targetSize"));
	target.clear();
	target.sendKeys(targetsize);
	
	driver.findElement(By.name("campaignStatus")).sendKeys("On Process");
	
	WebElement exCloseDate = driver.findElement(By.name("expectedCloseDate"));
	Actions act1=new Actions(driver);
	act1.click(exCloseDate).sendKeys(daterequired).perform();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
	
    //validation 
    WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']")); 
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10)); 
    wait.until(ExpectedConditions.visibilityOf(toastmsg)); 
    String msg = toastmsg.getText(); 
     
   
  if(msg.contains(campname)) 
  { 
   System.out.println("campaign created"); 
  } 
  else { 
   System.out.println("campaign not created"); 
  } 
  driver.findElement(By.xpath("//button[@aria-label='close']")).click(); 
   
   
  //logout 
  WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
  Actions act=new Actions(driver); 
  act.moveToElement(icon).perform(); 
  WebElement logoutbtn = driver.findElement(By.xpath("//div[@class='dropdown-item logout']")); 
  act.moveToElement(logoutbtn).click().perform(); 
   
  driver.quit();
	}
}
