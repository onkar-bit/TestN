package AutomationTesting.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Onlineshop {
	
	WebDriver driver;
	
	@Parameters("browser")
	@BeforeMethod
	public void launch(String browser) {
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
		else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
	}
	
	@Test
	public void openweb() {
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.xpath("//a[normalize-space(text())='Sign in']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("mnopq@gmail.com");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("abc123");
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
				
		WebElement move = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));
		Actions act = new Actions(driver);
		act.moveToElement(move).build().perform();
		
		driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[2]/ul/li[2]/a")).click();
	}

}
