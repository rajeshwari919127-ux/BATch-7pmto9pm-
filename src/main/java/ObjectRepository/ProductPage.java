package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	WebDriver driver; 
	 public ProductPage(WebDriver driver) 
	 { 
	  this.driver=driver; 
	  PageFactory.initElements(driver,this);
	 }
	 
	 @FindBy(xpath="//span[text()='Add Product']")
	 private WebElement ADDButton;

	@FindBy(name="productId")
	private WebElement productID;
	
	@FindBy(name="productName")
	private WebElement productName;
	
	@FindBy(name="productCategory")
	private WebElement ProCategory;
	
	@FindBy(name="quantity")
	private WebElement Quantity;
	
	@FindBy(name="price")
	private WebElement Price;
	
	@FindBy(name="vendorId")
	private WebElement VendorID;
	
	@FindBy(xpath="//button[text()='Add']")
	private WebElement CreateProductBtn;
	
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebElement getADDButton() {
		return ADDButton;
	}

	public WebElement getProductID() {
		return productID;
	}

	public WebElement getProductName() {
		return productName;
	}

	public WebElement getProCategory() {
		return ProCategory;
	}

	public WebElement getQuantity() {
		return Quantity;
	}

	public WebElement getPrice() {
		return Price;
	}

	public WebElement getVendorID() {
		return VendorID;
	}

	public WebElement getCreateProductBtn() {
		return CreateProductBtn;
	}
	
	
	
	
}
