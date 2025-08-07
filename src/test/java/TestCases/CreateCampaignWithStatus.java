package TestCases;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignWithStatus {

	public static void main(String[] args) {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//login
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//Random number
		Random ran = new Random();
		int rancount=ran.nextInt(1000);
		
		//Create Campaign
		
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("MANGO4"+rancount);
		driver.findElement(By.name("campaignStatus")).sendKeys("On Process");
		WebElement target = driver.findElement(By.name("targetSize"));
		target.clear();
		target.sendKeys("10");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		//Validation
		
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg=toastmsg.getText();
		
		if(msg.contains("MANGO4"))
		{
			System.out.println("Campaing Created");
		}
		else
		{
			System.out.println("Campaign not created");
		}
	
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
		
		//logout
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act=new Actions(driver);
		act.moveToElement(icon).perform();
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act.moveToElement(logout).click().perform();
		driver.quit();
	}

}
