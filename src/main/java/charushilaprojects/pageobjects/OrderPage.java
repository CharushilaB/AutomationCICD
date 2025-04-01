package charushilaprojects.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import charushilaprojects.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// List<WebElement> cartProdutcs = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productsNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public Boolean verifyOrderDisplay(String productName) {
        Boolean match = productsNames.stream().anyMatch(product -> 
        product.getText().equalsIgnoreCase(productName));
        return match;	
	}
	
}
