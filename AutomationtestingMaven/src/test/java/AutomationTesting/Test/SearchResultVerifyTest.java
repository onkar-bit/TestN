package AutomationTesting.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchResultVerifyTest {

	
	WebDriver driver;
	
	public String read(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fs = new FileInputStream("C:\\workplace\\AutomationTestingMaven\\alldata.properties");
		properties.load(fs);
		String val = properties.getProperty(key);
		return val;
	}
	
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void url() throws IOException, InterruptedException {
		driver.get(read("shopping"));
		WebElement mouse = driver.findElement(By.xpath("//a[@title='Women']"));
		Actions act = new Actions(driver);
		act.moveToElement(mouse).build().perform();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[1]/ul/li[1]/ul/li[1]/a")).click();
		
		String product = driver.findElement(By.xpath("//div[@class='right-block']/h5/a")).getText();
		System.out.println("the name of product: "+product);
	    Reporter.log(product, true);
		
		driver.findElement(By.xpath("//input[@id='search_query_top']")).sendKeys(read("Tshirtname"));
		driver.findElement(By.xpath("//button[@name='submit_search']")).click();
		
		String searchpro = driver.findElement(By.xpath("//div[@class='right-block']/h5/a")).getText();
		
		if(product.equalsIgnoreCase(searchpro)) {
			System.out.println("The product match test PASS");
		}
		else {
			System.out.println("the product not match test FAIL");
		}
		
	}
}
