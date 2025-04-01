package charushilaprojects.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import charushilaprojects.pageobjects.CartPage;
import charushilaprojects.pageobjects.OrderPage;

public class AbstractComponent {
     WebDriver driver;
     
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void  waitForElementToBeAppear(By findBy) 
	{
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));	
	}
	public void  waitForWebElementToBeAppear(WebElement findBy) 
	{
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	      wait.until(ExpectedConditions.visibilityOf(findBy));	
	}
	
	public void  waitForElementToBeDisappear(WebElement ele) throws InterruptedException 
	{     //Thread.sleep(1000);
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void  waitForElementToDisappearBy(By findBy) throws InterruptedException 
	{ 
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
//	public CartPage goToCart() {
//		   cartHeader.click();
//		   CartPage cartPage = new CartPage(driver);
//		   return cartPage;
//	}
	public CartPage goToCart() throws InterruptedException {
	    By spinner = By.cssSelector(".ngx-spinner-overlay"); // Change this based on actual class if needed
	    waitForElementToDisappearBy(spinner); // Ensure spinner disappears before clicking
	    cartHeader.click();
	    return new CartPage(driver);
	}

	public OrderPage goToOrdersPage() {
		   orderHeader.click();
		   OrderPage orderPage = new OrderPage(driver);
		   return orderPage;
	}
}
