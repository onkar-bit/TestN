package NewTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Fileuploadform {
	
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
		System.setProperty("webdriver.chrome.driver","C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void go() throws IOException {
		driver.get(read("formurl"));
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(read("fname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(read("lname"));
		driver.findElement(By.xpath("//input[@id='sex-0']")).click();
		driver.findElement(By.xpath("//input[@id='exp-0']")).click();
		driver.findElement(By.xpath("//input[@id='profession-1']")).click();
		driver.findElement(By.xpath("//input[@id='tool-2']")).click();
		
		WebElement sel = driver.findElement(By.xpath("//select[@id='continents']"));
		Select obj = new Select(sel);
		obj.selectByVisibleText("South America");
		
		WebElement sel2 = driver.findElement(By.xpath("//select[@id='selenium_commands']"));
		Select obj1 = new Select(sel2);
		obj1.selectByVisibleText("WebElement Commands");
		
		WebElement upload = driver.findElement(By.xpath("//input[@class='input-file']"));
		upload.sendKeys("C:\\Users\\285971\\Desktop\\excel1.xlsx");
		
	}

}
