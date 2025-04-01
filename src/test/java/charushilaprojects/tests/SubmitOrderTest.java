package charushilaprojects.tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import charushilaprojects.TestComponents.BaseTest;
import charushilaprojects.pageobjects.CartPage;
import charushilaprojects.pageobjects.CheckoutPage;
import charushilaprojects.pageobjects.ConfirmationPage;
import charushilaprojects.pageobjects.OrderPage;
import charushilaprojects.pageobjects.ProductCatalogue;
import charushilaprojects.data.DataReader;

public class SubmitOrderTest extends BaseTest {
	String productName = "IPHONE 13 PRO";  

@Test(dataProvider="getData",groups={"Purchase"})
public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {		 
		             
         ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
         
         //List of products and add to cart & waits
         List <WebElement> products = productCatalogue.getProductList();
         productCatalogue.addToCart(input.get("product"));
         
         CartPage cartPage = productCatalogue.goToCart(); 
         
        //Validate if our selected item is added to cart        
         Boolean match = cartPage.verifyProductDisplay(input.get("product"));
         Assert.assertTrue(match, "Order was not placed successfully");
         
        //Checkout & add country name & place order
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();     
        
        //Validate message
        //String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("Order placed");     	    
	}

@Test(dependsOnMethods= {"submitOrder"})
public void OrderHistoryTest() 
  {  	//"IPHONE 13 PRO";
	ProductCatalogue productCatalogue = landingPage.loginApplication("Charu123@gmail.com", "Charu123");
	OrderPage ordersPage = productCatalogue.goToOrdersPage();	
	Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
   }

@DataProvider
public Object[][] getData() throws IOException {
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("email", "Charu123@gmail.com");
	map.put("password", "Charu123");
	map.put("product", "IPHONE 13 PRO");
	
	HashMap<String, String> map1 = new HashMap<String, String>();
	map1.put("email", "anshika@gmail.com");
	map1.put("password", "Iamking@000");
	map1.put("product", "IPHONE 13 PRO");
	return new Object[][] {{map}, {map1}};
	
//	DataReader dataReader = new DataReader(); 
//	List<HashMap<String, String>> data = dataReader.getJsonDataToMap(System.getProperty("user.dir")+ "//src//test//java//charushilaprojects//data//PurchaseOrder.json");
//  return new Object[][] { {data.get(0)}, {data.get(1)} };	
}
//@DataProvider
//public Object[][] getData() {
//	return new Object[][] { {"Charu123@gmail.com", "Charu123", "IPHONE 13 PRO"}, {"anshika@gmail.com", "Iamking@000", "IPHONE 13 PRO"
//			+ ""} };

}
