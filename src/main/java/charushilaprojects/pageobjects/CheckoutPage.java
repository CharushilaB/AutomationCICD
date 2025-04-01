package charushilaprojects.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import charushilaprojects.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	 public CheckoutPage(WebDriver driver){
		 super(driver);
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
//driver.findElement(By.cssSelector(".form-group input")), "india"
	 @FindBy(css=".form-group input")
	 WebElement country;
	 
	 @FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	 WebElement selectCountry;
	 
	 @FindBy(css=".action__submit")
	 WebElement submit;
	 
	 By results = By.cssSelector(".ta-results");
	 By eleToDisapper = By.cssSelector(".ng-animating");

	 public void selectCountry(String countryName) 
	 {	 
		    Actions a = new Actions(driver);
	        a.sendKeys(country, countryName).build().perform();
	        waitForElementToBeAppear(By.cssSelector(".ta-results"));
	        selectCountry.click();
	 }
	 public ConfirmationPage submitOrder() throws InterruptedException {
	     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);//not in Lecture
	     waitForElementToDisappearBy(eleToDisapper);//not in Lecture
	     submit.click();
		 return new ConfirmationPage(driver);
	 }
}
