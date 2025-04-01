package charushilaprojects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import charushilaprojects.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	//Constructor
	public LandingPage(WebDriver driver){
	//Initialization
	super(driver);
	this.driver=driver;	
	PageFactory.initElements(driver, this);	
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	//class="ng-tns-c4-22 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error"
     
	@FindBy(css="[class*='flyInOut'")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApplication(String email, String password)
    {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
    }
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {

		waitForWebElementToBeAppear(errorMessage);
		return errorMessage.getText();

	}
}
