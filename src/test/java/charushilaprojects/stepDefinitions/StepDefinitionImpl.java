package charushilaprojects.stepDefinitions;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import charushilaprojects.TestComponents.BaseTest;
import charushilaprojects.pageobjects.CartPage;
import charushilaprojects.pageobjects.CheckoutPage;
import charushilaprojects.pageobjects.ConfirmationPage;
import charushilaprojects.pageobjects.LandingPage;
import charushilaprojects.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue ;
    public ConfirmationPage confirmationPage;
    
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password){
    productCatalogue = landingPage.loginApplication(username, password);
    }
    
    @When("^I add product (.+) to Cart$")
    public void add_product_to_cart(String productName) throws InterruptedException {
    	List <WebElement> products = productCatalogue.getProductList();
        productCatalogue.addToCart(productName);
    }
    
    @When("^Chekout (.+) and submit the order$")
    public void checkout_submit_order(String productName) throws InterruptedException{
    	CartPage cartPage = productCatalogue.goToCart();         
        //Validate if our selected item is added to cart        
         Boolean match = cartPage.verifyProductDisplay(productName);
         Assert.assertTrue(match, "Order was not placed successfully");         
        //Checkout & add country name & place order
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder(); 
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_on_confirmationPage(String string) {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }
    
    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String str1Arg) {
        Assert.assertEquals(str1Arg, landingPage.getErrorMessage());
        driver.close();
    }

	
}
