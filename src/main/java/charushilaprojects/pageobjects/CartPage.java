package charushilaprojects.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import charushilaprojects.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

WebDriver driver;

	public  CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// List<WebElement> cartProdutcs = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public Boolean verifyProductDisplay(String productName) {
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> 
        cartProduct.getText().equalsIgnoreCase(productName));
        return match;	
	}
	public CheckoutPage goToCheckout() {
		checkout.click();
		return new CheckoutPage(driver);
	}
	
}
