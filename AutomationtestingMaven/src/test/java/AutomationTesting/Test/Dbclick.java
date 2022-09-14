package AutomationTesting.Test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dbclick {
	
	WebDriver driver;
	
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void dblclick() {
		driver.get("https://omayo.blogspot.com/");
		WebElement dbc = driver.findElement(By.xpath("//button[text()=' Double click Here   ']"));
		Actions act = new Actions(driver);
		act.doubleClick(dbc).build().perform();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		driver.findElement(By.xpath("//div[@id='HTML11']//textarea")).clear();
		driver.findElement(By.xpath("//div[@id='HTML11']//textarea")).sendKeys("Hi i am onkar");
		
	}

}
