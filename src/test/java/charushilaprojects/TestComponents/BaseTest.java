package charushilaprojects.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;

import charushilaprojects.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
public WebDriver driver; //global variable
public LandingPage landingPage;
protected static ExtentReports extent; 
public WebDriver initializeDriver() throws IOException {
	//properties class
	 Properties prop = new Properties();
	 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//charushilaprojects//resources//GlobalData.properties");
	 prop.load(fis); 
	 
String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
			// prop.getProperty("browser");
	 
	 if(browserName.contains("chrome")) 
	 {
     ChromeOptions options = new ChromeOptions();
	 WebDriverManager.chromedriver().setup();
	 if(browserName.contains("headless")) 
	 {
	 options.addArguments("headless");
	 }
     driver = new ChromeDriver(options);
     driver.manage().window().setSize(new Dimension(1440, 900));//full screen
	 }  
	 else if(browserName.equalsIgnoreCase("firefox")) 
	 {//firefox code
		 WebDriverManager.firefoxdriver().setup();
		// System.setProperty("webdriver.gecko.driver", "/home/charushilabhosale/eclipse-workspace/geckodriver");
	     driver = new FirefoxDriver();	 	 
	 }
	 else if(browserName.equalsIgnoreCase("edge"))         	
	 {//edge code
		 	 } 
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     driver.manage().window().maximize();
     return driver;
}
public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
	TakesScreenshot ts = (TakesScreenshot)driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File destFile = new File(System.getProperty("user.dir")+"reports" + testCaseName + ".png");
	FileUtils.copyFile(source, destFile);
	return System.getProperty("user.dir")+"reports" + testCaseName + ".png";
}

@BeforeMethod(alwaysRun=true)
public LandingPage launchApplication() throws IOException {
	driver = initializeDriver();
	 //Login
    landingPage = new LandingPage(driver);
    landingPage.goTo();
    return landingPage;
}
@AfterMethod
	public void tearDown() {
	  driver.close();
	}

	
}
