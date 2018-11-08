package GmailProgram;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailRegistration {
	public WebDriver driver;
	FileInputStream fileInput = null;
	Properties prop;
		
	@BeforeTest
	public void launchBrowser() throws Exception{
		File f=new File("F:/Gmail_RegistrationFunctinality/GmailCreation/src/Config.properties");
		fileInput= new FileInputStream(f);
		prop=new Properties();
		prop.load(fileInput);
		System.setProperty("webdriver.chrome.driver", "E:\\appium_softwares_qualitythought\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		Reporter.log("Browser Maximised");
		driver.get(prop.getProperty("URL"));
		Reporter.log("Navigate to Gmail Home Page");
		 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			    
			}
	@Test
	public void gmailAccountRegistrationHomePage() throws InterruptedException
	{
		//Gmail home page verification
		Assert.assertEquals("Gmail - Free Storage and Email from Google",driver.getTitle(),
				"Unable to navigate Gmail home page");
		//Click on Create An Account Link Button on home page
		driver.findElement(By.xpath("//a[contains(text(),'CREATE AN ACCOUNT')]")).click();
		Reporter.log("Create an Account button clicked");
		String parentwindow=driver.getWindowHandle();
		Set<String> allwindows=driver.getWindowHandles();
		for(String child:allwindows)
		{
			if(!parentwindow.equalsIgnoreCase(child))
			{
				driver.switchTo().window(child);
				//Gmail SignUp page verification
				Assert.assertEquals("Create your Google Account",driver.getTitle(),
								"Unable to navigate Gmail create your google account page");
				Reporter.log("Navigated to Create your Google Account Page");
				//Enter Gmail Creation data on Gmail Creation Page
				driver.findElement(By.id("firstName")).sendKeys(prop.getProperty("FirstName"));
				Reporter.log("Enter First Name on Create your Google Account Page");
				driver.findElement(By.id("lastName")).sendKeys(prop.getProperty("LastName"));
				Reporter.log("Enter Last Name Create your Google Account Page");
				driver.findElement(By.id("username")).sendKeys(prop.getProperty("UserName"));
				Reporter.log("User User Name on Create your Google Account Page");
				driver.findElement(By.xpath("(//input[@name='Passwd'])[1]")).sendKeys(prop.getProperty("Password"));
				Reporter.log("Enter Password on Create your Google Account Page");
				driver.findElement(By.name("ConfirmPasswd")).sendKeys(prop.getProperty("ConfirmPassword"));
				Reporter.log("Enter Confirm Password on Create your Google Account Page");
				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
				Reporter.log("Enter Next button on on Create your Google Account Page");
																
				
			}
		}
			
			
	}
	
		
	@AfterTest
	public void logoutPage(){
		driver.close();
	}

}
