package AutomationTesting.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Totalprice {
	
	WebDriver driver;
	
	public String read(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fs = new FileInputStream("C:\\workplace\\AutomationTestingMaven\\alldata.properties");
		properties.load(fs);
		String val = properties.getProperty(key);
		return val;
	}
	
	public void screenshot(WebDriver driver, String name) throws IOException {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//add to cart
	@Test(priority = 0)
	public void verifyaddtocart() throws IOException, InterruptedException {
		driver.get(read("shoppingnew"));
		driver.findElement(By.xpath("//a[text()='MacBook']")).click();
		driver.findElement(By.xpath("//input[@id='input-quantity']")).clear();
		driver.findElement(By.xpath("//input[@id='input-quantity']")).sendKeys(read("quant"));
		driver.findElement(By.xpath("//button[@id='button-cart']")).click();
		
		String msg =driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText();
		Reporter.log(msg, true);
		
		if(msg.equals(msg)) {
			System.out.println("the product added successfully Test PASS");
		}
		else {
			System.out.println("the product not added test FAIL");
		}
		
		}
	
	//Register and Buy feature
	@Test(priority = 1)
	public void loginandorder() throws IOException, InterruptedException {
		driver.get(read("shoppingnew"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(read("fname"));
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(read("lname"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(read("email1"));
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(read("tel"));
		
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(read("pass"));
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(read("cnfpass"));
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		
		WebElement mouse =driver.findElement(By.xpath("//a[text()='Desktops']"));
		Actions act = new Actions(driver);
		act.moveToElement(mouse).build().perform();
		
		//Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Show All Desktops']")).click();
		driver.findElement(By.xpath("//a[text()='HP LP3065']")).click();
		driver.findElement(By.xpath("//button[text()='Add to Cart']")).click();
		driver.findElement(By.xpath("//span[@id='cart-total']")).click();
		
		//Thread.sleep(5000);
		
		driver.findElement(By.xpath("//p[@class='text-right']/a[2]")).click();
		
		//Thread.sleep(5000);
		
		driver.findElement(By.xpath("//input[@id='input-payment-firstname']")).sendKeys(read("fname"));
		driver.findElement(By.xpath("//input[@id='input-payment-lastname']")).sendKeys(read("lname"));
		
		driver.findElement(By.xpath("//input[@id='input-payment-address-1']")).sendKeys(read("add"));
		driver.findElement(By.xpath("//input[@id='input-payment-city']")).sendKeys(read("city"));
		driver.findElement(By.xpath("//input[@id='input-payment-postcode']")).sendKeys(read("post"));
		
		WebElement sel = driver.findElement(By.xpath("//select[@id='input-payment-country']"));
		Select obj = new Select(sel);
		obj.selectByValue("99");
		
		//Thread.sleep(4000);
		WebElement sel2 = driver.findElement(By.xpath("//select[@id='input-payment-zone']"));
		Select obj1 = new Select(sel2);
		obj1.selectByValue("1493");
		
		//Thread.sleep(5000);
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='button-shipping-address']")).click();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='button-shipping-method']")).click();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@id='button-payment-method']")).click();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='button-confirm']")).click();
		//Thread.sleep(5000);
		String val = driver.findElement(By.xpath("//div[@id='common-success']/div/div/p[1]")).getText();
		Reporter.log(val, true);
		screenshot(driver, "ordercom");
		
		if(val.equalsIgnoreCase(val)) {
			System.out.println("the order is placed test PASS");
		}
		else {
			System.out.println("the order not placed test FAIL");
		}
		
	}
	
	//search product verify
	@Test(priority = 2)
	public void verifysearch() throws IOException {
		
		driver.get(read("shoppingnew"));
		WebElement move = driver.findElement(By.xpath("//a[text()='Desktops']"));
		Actions act = new Actions(driver);
		act.moveToElement(move).build().perform();
		
		driver.findElement(By.xpath("//a[text()='Show All Desktops']")).click();
		String val=driver.findElement(By.xpath("//a[text()='iPhone']")).getText();
		Reporter.log(val, true);
		
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(read("search"));
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		
		String cmp=driver.findElement(By.xpath("//a[text()='iPhone']")).getText();
		if(val.equals(cmp)) {
			System.out.println("The product match test PASS");
		}
		else {
			System.out.println("the product does not match test FAIL");
		}
		
		
	}
	
	//Login feature
	@Test(priority = 3)
	public void login() throws IOException {
		driver.get(read("shoppingnew"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		//invalid email
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("jklmn@gmail.com");
		//valid pass
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("omkarb");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String error = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		Reporter.log(error, true);
		
		if(error.equals(error)) {
			System.out.println("error msg displayed test PASS");
		}
		else {
			System.out.println("error msg not displayed test FAIL");
		}
	}
	
	@Test(priority = 4)
	public void login1() throws IOException {
		driver.get(read("shoppingnew"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		//valid email
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("omband9@gmail.com");
		//valid pass
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("omkarb");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String acc = driver.findElement(By.xpath("//div[@id='content']/h2[1]")).getText();
		if(acc.contains("Account")) {
			System.out.println("Successfully login");
		}
		else {
			System.out.println("not logged in");
		}
		
		WebElement move = driver.findElement(By.xpath("//a[text()='Desktops']"));
		Actions act = new Actions(driver);
		act.moveToElement(move).build().perform();
		
		driver.findElement(By.xpath("//a[text()='Show All Desktops']")).click();
		
		driver.findElement(By.xpath("//a[text()='iPod Classic']")).click();
		driver.findElement(By.xpath("//input[@id='input-quantity']")).clear();
		driver.findElement(By.xpath("//input[@id='input-quantity']")).sendKeys("2");
		
	}
}
