package AutomationTesting.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Flipkart {
	
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void gourl() throws IOException, InterruptedException {
		driver.get(read("flipkart"));
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		
		WebElement move =driver.findElement(By.xpath("//div[@class='eFQ30H'][5]"));
		Actions a = new Actions(driver);
		a.moveToElement(move).build().perform();
		//Thread.sleep(5000);
		WebElement move2 = driver.findElement(By.xpath("//a[text()='Cameras & Accessories']"));
		Actions a3 = new Actions(driver);
		a3.moveToElement(move2).build().perform();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//a[text()='DSLR & Mirrorless']")).click();
	}

}
