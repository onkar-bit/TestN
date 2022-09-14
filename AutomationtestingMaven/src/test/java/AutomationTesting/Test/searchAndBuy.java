package AutomationTesting.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class searchAndBuy {
	
	WebDriver driver;
	
	@BeforeMethod
	public void go() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void open() {
		driver.get("http://tutorialsninja.com/demo/index.php?route=common/home");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("ajaydemo@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("ajaydemo");
		driver.findElement(By.xpath("//input[@value='Login']"));
		
		WebElement mov = driver.findElement(By.xpath("//a[text()='Desktops']"));
		Actions act = new Actions(driver);
		act.moveToElement(mov).build().perform();
		
		driver.findElement(By.xpath("//a[text()='Show All Desktops']")).click();
		String msg= driver.findElement(By.xpath("//a[text()='Samsung SyncMaster 941BW']")).getText();
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(msg, Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='Samsung SyncMaster 941BW']")).click();
		driver.findElement(By.xpath("//button[text()='Add to Cart']")).click();
		driver.findElement(By.xpath("//span[@id='cart-total']")).click();
		driver.findElement(By.xpath("//p[@class='text-right']//a[2]")).click();
		
	}
	
	

}
