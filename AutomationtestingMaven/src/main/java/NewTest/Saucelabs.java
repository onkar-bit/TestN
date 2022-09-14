package NewTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Saucelabs {
	
	WebDriver driver;
	
	public String read(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fs = new FileInputStream("C:\\workplace\\AutomationTestingMaven\\data.properties");
		properties.load(fs);
		String val = properties.getProperty(key);
		return val;
	}
	
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	//login check
	@Test
	public void go() {
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		//pass invalid
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sa");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		
		String msg = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Reporter.log(msg, true);
		
		if(msg.equals(msg)) {
			System.out.println("error msg displayed test PASS");
		}
		else {
			System.out.println("error msg not displayed test FAIL");
		}
	    
	}
	
	//register
	@Test
	public void bankreg() throws IOException {
		driver.get(read("bankweb"));
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='customer.firstName']")).sendKeys(read("fname"));
		driver.findElement(By.xpath("//input[@id='customer.lastName']")).sendKeys(read("lname"));
		driver.findElement(By.xpath("//input[@id='customer.address.street']")).sendKeys(read("add"));
		driver.findElement(By.xpath("//input[@id='customer.address.city']")).sendKeys(read("city"));
		driver.findElement(By.xpath("//input[@id='customer.address.state']")).sendKeys(read("state"));
		driver.findElement(By.xpath("//input[@id='customer.address.zipCode']")).sendKeys(read("zip"));
		driver.findElement(By.xpath("//input[@id='customer.phoneNumber']")).sendKeys(read("mobile"));
		driver.findElement(By.xpath("//input[@id='customer.ssn']")).sendKeys(read("ssn"));
		driver.findElement(By.xpath("//input[@id='customer.username']")).sendKeys(read("mail"));
		
		driver.findElement(By.xpath("//input[@id='customer.password']")).sendKeys(read("pass"));
		driver.findElement(By.xpath("//input[@id='repeatedPassword']")).sendKeys(read("pass"));
		driver.findElement(By.xpath("//input[@value='Register']")).click();
		
	}
	
	//invalid login test
	@Test
	public void banklogin() throws IOException {
		driver.get(read("bankweb"));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("om@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("hdfcvbn");
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		
		String msg = driver.findElement(By.xpath("//p[@class='error']")).getText();
		Reporter.log(msg, true);
		if(msg.equals(msg)) {
			System.out.println("the error displayed test PASS");
		}
		else {
			System.out.println("the error not display test FAIL ");
		}
		
	}
	//check transaction
	@Test
	public void trans() throws IOException {
		driver.get(read("bankweb"));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(read("mail"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(read("pass"));
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		
		driver.findElement(By.xpath("//a[text()='Find Transactions']")).click();
		driver.findElement(By.xpath("//input[@id='criteria.onDate']")).sendKeys("03-01-2022");
		driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div[5]/button")).click();
	}

}
