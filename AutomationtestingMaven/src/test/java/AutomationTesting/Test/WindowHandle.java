package AutomationTesting.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowHandle {
	
	WebDriver driver;
	
	public String readfile(String key) throws IOException {
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
	public void window() throws IOException {
		driver.get(readfile("winurl"));
		driver.findElement(By.xpath("//a[text()=' Click this link to start new Tab '][3]")).click();
		
		String parent = driver.getWindowHandle();
		
		ArrayList<String> allwindow = new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(allwindow.get(0));
		String title= driver.getTitle();
		Reporter.log(title, true);
		
		driver.switchTo().window(allwindow.get(1));
		String title1 = driver.getTitle();
		Reporter.log(title1, true);
		
		driver.switchTo().window(allwindow.get(0));
		String url = driver.getCurrentUrl();
		Reporter.log(url, true);
		
		driver.switchTo().window(allwindow.get(1));
		driver.findElement(By.xpath("//div[text()='Sign In']")).click();
		String title2 = driver.getTitle();
		Reporter.log(title2, true);				
		
	}
	
	@Test
	public void demo() throws IOException, InterruptedException {
		driver.get(readfile("way"));
		WebElement move = driver.findElement(By.xpath("//button[@id='mousehover']"));
		Actions act = new Actions(driver);
		act.moveToElement(move).build().perform();
		
		driver.findElement(By.xpath("//a[text()='Reload']")).click();
		
		driver.switchTo().frame("courses-iframe");
		
		WebElement sel = driver.findElement(By.xpath("//select[@name='categories']"));
		Select obj = new Select(sel);
		obj.selectByValue("2022");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='ALL COURSES']")).click();
		
	}
	
	@Test
	public void webdev() {
		driver.get("https://webdriveruniversity.com/");
		driver.findElement(By.xpath("//h1[text()='CONTACT US']")).click();
		
		String parent = driver.getWindowHandle();
		
		ArrayList<String> allwindow = new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(allwindow.get(1));
		
		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("ak");
		driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("ajay");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("om@yahoo.com");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("hi");
		driver.findElement(By.xpath("//input[@value='SUBMIT']")).click();
	}
	
}
