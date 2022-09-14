package AutomationTesting.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Shopping {
	
	WebDriver driver;
	
	public String read(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fs = new FileInputStream("C:\\workplace\\AutomationTestingMaven\\alldata.properties");
		properties.load(fs);
		String text = properties.getProperty(key);
		return text;
	}
	
	public void takescreen(WebDriver driver, String name) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("C:\\workplace\\AutomationTestingMaven\\Screenshot\\"+name+".png");
		FileUtils.copyFile(SrcFile, DestFile);
		
	}
	
	
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void website() throws IOException, InterruptedException {
		driver.get(read("url"));
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(read("username"));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(read("pass"));
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		
		driver.findElement(By.xpath("//div[@class='inventory_list']/div[4]/div/div/a/div")).click();
		driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(read("fname"));
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(read("lname"));
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(read("code"));
		
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		driver.findElement(By.xpath("//button[@id='finish']")).click();
		
		String actual = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
		String expected ="THANK YOU FOR YOUR ORDER";
		Reporter.log(actual, true);
		
		
		Assert.assertEquals(actual, expected);
		takescreen(driver,"order completed");
		
		driver.findElement(By.xpath("//button[text()='Open Menu']")).click();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

}
