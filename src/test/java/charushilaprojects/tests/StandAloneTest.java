package charushilaprojects.tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		//WedDriver Manager Setup
		String productName = "IPHONE 13 PRO";
         WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "/home/charushilabhosale/eclipse-workspace/chromedriver");
         WebDriver driver = new ChromeDriver();
         //Global waits
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         driver.manage().window().maximize();
         driver.get("https://rahulshettyacademy.com/client");
         //Login
         driver.findElement(By.id("userEmail")).sendKeys("Charu123@gmail.com");
         driver.findElement(By.id("userPassword")).sendKeys("Charu123");
         driver.findElement(By.id("login")).click();
         
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
         //List of products and add to cart
         //List<WebElement> products = driver.findElements(By.xpath("//div/h5"));
         List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));  
         WebElement prod = products.stream().filter(product -> 
         product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
         prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //add to cart clicked    
        //explicitly wait 
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        System.out.println("Item is Added to Cart");
        //validate if our selected item is added to cart
        List<WebElement> cartProdutcs = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProdutcs.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        //checkout & add country name & place order
        driver.findElement(By.cssSelector(".totalRow button")).click();
       //below 4 lines could also be done by finding xpath/css of search box, sending text"india"
        // and then finding xpath of india and clicking it normally
        Actions a = new Actions(driver);
        a.sendKeys( driver.findElement(By.cssSelector(".form-group input")), "india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
        //Scroll to view place order option
        WebElement placeOrderButton = driver.findElement(By.cssSelector(".action__submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
        //Wait for any overlay to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        placeOrderButton.click();
        //validate message
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("Order placed");
        driver.close();
    
       
	}

}
