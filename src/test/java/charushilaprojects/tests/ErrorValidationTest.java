package charushilaprojects.tests;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import charushilaprojects.TestComponents.BaseTest;
import charushilaprojects.TestComponents.Retry;
import charushilaprojects.pageobjects.CartPage;
import charushilaprojects.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

@Test
public void LoginErrorValidation() throws IOException, InterruptedException 
     {		 
		 String productName = "IPHONE 13 PRO";              
         landingPage.loginApplication("Charu123@gmail.com", "Charu123");//use correct password to fail the test
         Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
     }

@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
public void ProductErrorValidation() throws InterruptedException 
{    
	 String productName = "IPHONE 13 PRO"; //use different login credentials while running 2 test cases with same credentials
	 ProductCatalogue productCatalogue = landingPage.loginApplication("Charu123@gmail.com", "Charu123");
     List <WebElement> products = productCatalogue.getProductList();
     productCatalogue.addToCart(productName);  
     CartPage cartPage = productCatalogue.goToCart();        
     Boolean match = cartPage.verifyProductDisplay(productName);
     Assert.assertTrue(match); //use assertFalse to fail the test
	
}

}
