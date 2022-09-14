package AutomationTesting.Test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Guru99Bank {
	
	WebDriver driver;
	
	@BeforeMethod
	public void demo() {
		System.setProperty("webdriver.gecko.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\geckodriver.exe");
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
	
	}
	
	@Test
	public void validcred() {
		driver.get("https://www.demo.guru99.com/V4/index.php");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr388062");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jeravab");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		String msg = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
		Reporter.log(msg);
		
		if(msg.contains("Welcome")) {
			System.out.println("The login sucessfull test PASS");
		}
		else {
			System.out.println("Login not success test FAIL");
		}
	}
	
	//invalid credential
	@Test
	public void invalidcred() {
		driver.get("https://www.demo.guru99.com/V4/index.php");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr388062");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jhngbt");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	//title verify
	@Test
	public void titleverify() {
		driver.get("https://www.demo.guru99.com/V4/index.php");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr388062");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jeravab");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		String title =driver.getTitle();
		Reporter.log(title, true);
		
		if(title.equals(title)) {
			System.out.println("the title match test PASS");
		}
		else {
			System.out.println("the title not match test FAIL");
		}
		
	}
		

}
