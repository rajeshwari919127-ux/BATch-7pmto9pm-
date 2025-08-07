package DDT;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {

		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
		
		Properties prop= new Properties();
		
		prop.load(fis);
		
		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");

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

	}

}
