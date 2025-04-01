package charushilaprojects.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import charushilaprojects.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	//Constructor
	
	public ProductCatalogue(WebDriver driver){
	//Initialization
	super(driver);
	this.driver=driver;	
	PageFactory.initElements(driver, this);	
	}
	
	 //List<WebElement> products = driver.findElements(By.xpath("//div/h5"));
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));  
	@FindBy(css=".mb-3")
	List <WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToBeAppear(productsBy);
		return products;	
	}
	
	public WebElement getProductByName(String productName) {
		 WebElement prod = getProductList().stream().filter(product -> 
         product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		 return prod;
	}
	
	public void addToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();  
        waitForElementToBeAppear(toastMessage);
        waitForElementToBeDisappear(spinner);
    	
	}


}
