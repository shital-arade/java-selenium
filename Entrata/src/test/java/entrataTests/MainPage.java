package entrataTests;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.TakesScreenshot;

public class MainPage {
	public static WebDriver driver;
	String baseurl="https://www.entrata.com/";
	
	//Launch chrome browser and maximize
	 @BeforeTest
	 public void openBrowser()
	 	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 	}
	 
	 
	 //Verify page title using assert
	 @Test(priority=0)
	 public void VerifyTitle()
	 {
	   driver.get(baseurl);
	   String ExpectedTitle="Property Management Software | Entrata";
	   String ActualTitle=	driver.getTitle();
	   AssertJUnit.assertEquals(ExpectedTitle, ActualTitle);
	 }
	 
	 //open main menus
	 @Test(priority=1)
	 public void OpenMenus()
	 {
	  
	   WebElement prod=driver.findElement(By.cssSelector("div.main-nav-link"));
	   Actions act=new Actions(driver);
	   act.moveToElement(prod).perform(); 
	   AssertJUnit.assertTrue(prod.isDisplayed());
	   driver.findElement(By.linkText("ResidentPay")).click();
	   
	 }
	 
	 //capture full page screenshot
	 @Test(priority=2)
	 public void TakeScreenShot() throws IOException
	 	{ 
		 TakesScreenshot ts=((TakesScreenshot)driver);
		 File file=ts.getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(file, new File("./screenshots/MainMenus.png"));
		 File downloadedFile = new File("./screenshots/MainMenus.png");
	        if (downloadedFile.exists()) {
	            System.out.println("Screenshot already available.");
	        } 
	        else 
	        {
	            System.out.println("Screenshot Succesfull.");
	        }
	 	}
	 
	 //close driver
	 @AfterTest
	 public void DriverClose()
	 	{
		 driver.close();
		 driver.quit();
	 	}
}
