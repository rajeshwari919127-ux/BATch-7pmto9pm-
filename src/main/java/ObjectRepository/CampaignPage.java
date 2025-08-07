package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
WebDriver driver;
public CampaignPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver,this);
}
	@FindBy(name="campaignName")
	private WebElement campaignNameTF;
	
	@FindBy(name="campaignStatus")
	private WebElement stausTF;
	
	@FindBy(name="targetSize")
	private WebElement targetTF;
	
	@FindBy(name="expectedCloseDate")
	private WebElement dateTF;
	
	@FindBy(xpath="//button[text()='Create Campaign']")
	private WebElement CraetecampaignSubmitbtn;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement toastmsg;
	
	@FindBy(xpath="//button[@aria-label='close']")
	private WebElement closemsg;

	public WebElement getCampaignNameTF() {
		return campaignNameTF;
	}

	public WebElement getStausTF() {
		return stausTF;
	}

	public WebElement getTargetTF() {
		return targetTF;
	}

	public WebElement getDateTF() {
		return dateTF;
	}

	public WebElement getCraetecampaignSubmitbtn() {
		return CraetecampaignSubmitbtn;
	}

	public WebElement getToastmsg() {
		return toastmsg;
	}

	public WebElement getClosemsg() {
		return closemsg;
	}
	
}
