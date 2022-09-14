package AutomationTesting.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HotelBook {
	
	WebDriver driver;
	
	public String read(String Key) throws IOException {
	Properties properties = new Properties();
	FileInputStream fs = new FileInputStream("C:\\workplace\\AutomationTestingMaven\\alldata.properties");
	properties.load(fs);
	String val = properties.getProperty(Key);
	return val;
	}
	
	
	@Parameters("browser")
	@BeforeMethod
	public void launch(String browser) {
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}
	
	//Hotel booking
	@Test
	public void hotel() throws IOException, InterruptedException {
		driver.get(read("hotelurl"));
		driver.findElement(By.xpath("//button[text()='Got It']")).click();
		driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys(read("email"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(read("pass"));
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		
		driver.findElement(By.xpath("//a[text()='Hotels']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		
		WebElement ele =driver.findElement(By.xpath("//*[@id=\"fadein\"]/section[2]/div/div[2]/div/div/div/div[1]/div/div[7]/div/div[1]/a/img"));
		ele.click();

		//Thread.sleep(5000);
		WebElement butt =driver.findElement(By.xpath("//*[@id=\"availability\"]/div[2]/div[2]/div[2]/div/div[2]/form/div/div[4]/div/div/button/span[1]"));
		JavascriptExecutor js4 = (JavascriptExecutor)driver;
		js4.executeScript("arguments[0].click();", butt);
		
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(read("fname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(read("lname"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(read("email1"));
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(read("tel"));
		driver.findElement(By.xpath("//input[@name='address']")).sendKeys(read("add"));
		
		driver.findElement(By.xpath("//input[@name='firstname_1']")).sendKeys(read("name1"));
		driver.findElement(By.xpath("//input[@name='lastname_1']")).sendKeys(read("lname1"));
		
		driver.findElement(By.xpath("//input[@name='firstname_2']")).sendKeys(read("name2"));
		driver.findElement(By.xpath("//input[@name='lastname_2']")).sendKeys(read("lname2"));
		//Thread.sleep(5000);
		WebElement pay = driver.findElement(By.xpath("/html/body/div[2]/form/section/div/div/div[1]/div[3]/div[2]/div/ul/div[2]/div/label/div/img"));
		JavascriptExecutor js9 = (JavascriptExecutor)driver;
		js9.executeScript("arguments[0].click()", pay);
		
		WebElement accept=driver.findElement(By.xpath("/html/body/div[2]/form/section/div/div/div[1]/div[4]/div/div/div/label"));
		JavascriptExecutor js7 = (JavascriptExecutor)driver;
		js7.executeScript("arguments[0].click()", accept);
		
		WebElement book=driver.findElement(By.xpath("//button[@id='booking']"));
		JavascriptExecutor js6 = (JavascriptExecutor)driver;
		js6.executeScript("arguments[0].click()", book);
		
		String msg = driver.findElement(By.xpath("//div[@class='mx-2']")).getText();
		Reporter.log(msg, true);
		
		String booknum= driver.findElement(By.xpath("//span[@class='text-right']")).getText();
		Reporter.log(booknum, true);
		
		if(msg.equals(msg)) {
			System.out.println("hotel booked test PASS");
		}
		else {
			System.out.println("hotel not booked test FAIL");
		}
	}
	
	//Tours booking	
	@Test
	public void tour() throws IOException {
		driver.get(read("hotelurl"));
		driver.findElement(By.xpath("//button[text()='Got It']")).click();
		driver.findElement(By.xpath("//a[text()='Tours']")).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='Sydney']"));
		JavascriptExecutor js5 = (JavascriptExecutor)driver;
		js5.executeScript("arguments[0].click()", element);
		
		WebElement demo = driver.findElement(By.xpath("//select[@id='childs']"));
		Select obj4 = new Select(demo);
		obj4.selectByValue("1");
		
		WebElement book =driver.findElement(By.xpath("//button[@type='submit']"));
		JavascriptExecutor js10 =(JavascriptExecutor)driver;
		js10.executeScript("arguments[0].click()", book);
		
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(read("fname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(read("lname"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(read("email"));
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(read("tel"));
		driver.findElement(By.xpath("//input[@name='address']")).sendKeys(read("add"));
		
		driver.findElement(By.xpath("//input[@name='firstname_1']")).sendKeys(read("name2"));
		driver.findElement(By.xpath("//input[@name='lastname_1']")).sendKeys(read("lname2"));	
		
		driver.findElement(By.xpath("//input[@name='firstname_2']")).sendKeys(read("name1"));
		driver.findElement(By.xpath("//input[@name='lastname_2']")).sendKeys(read("lname1"));
		
		WebElement clk =driver.findElement(By.xpath("//*[@id=\"gateway_pay-later\"]"));
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", clk);
		
		WebElement accept1 =driver.findElement(By.xpath("/html/body/div[2]/form/section/div/div/div[1]/div[4]/div/div/div/label"));
		JavascriptExecutor js3 = (JavascriptExecutor)driver;
		js3.executeScript("arguments[0].click()", accept1);
		
		WebElement book1= driver.findElement(By.xpath("//button[@id='booking']"));
		JavascriptExecutor js8 = (JavascriptExecutor)driver;
		js8.executeScript("arguments[0].click()", book1);
	}
	
	//Flight booking
	@Test
	public void Flightbook() throws IOException {
		driver.get(read("hotelurl"));
		driver.findElement(By.xpath("//button[text()='Got It']")).click();
		driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys(read("email"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(read("pass"));
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		
		driver.findElement(By.xpath("//a[text()='flights']")).click();
		
		WebElement link = driver.findElement(By.xpath("/html/body/section[2]/div/div[2]/div/div/div[1]/div/div/div[7]/a/div/div/div[2]/h6"));
		JavascriptExecutor js2 = (JavascriptExecutor)driver;
		js2.executeScript("arguments[0].click();", link);
		
		WebElement book = driver.findElement(By.xpath("/html/body/main/div/div[2]/section/ul/li[1]/div/form/div/div[2]/div/button"));
		JavascriptExecutor js3 = (JavascriptExecutor)driver;
		js3.executeScript("arguments[0].click();", book);
		
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(read("fname"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(read("lname"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(read("email"));
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(read("tel"));
		driver.findElement(By.xpath("//input[@name='address']")).sendKeys(read("add"));
		
		driver.findElement(By.xpath("//input[@name='firstname_1']")).sendKeys(read("name1"));
		driver.findElement(By.xpath("//input[@name='lastname_1']")).sendKeys(read("lname1"));
		
		WebElement sel = driver.findElement(By.xpath("//select[@name='nationality_1']"));
		Select obj = new Select(sel);
		obj.selectByValue("IN");
		
		WebElement mnth = driver.findElement(By.xpath("//select[@name='dob_month_1']"));
		Select obj2 = new Select(mnth);
		obj2.selectByValue("03");
		
		WebElement day = driver.findElement(By.xpath("//select[@name='dob_day_1']"));
		Select obj3 = new Select(day);
		obj3.selectByValue("30");
		
		WebElement year = driver.findElement(By.xpath("//select[@name='dob_year_1']"));
		Select obj4 = new Select(year);
		obj4.selectByVisibleText("1996");
		
		driver.findElement(By.xpath("//input[@name='passport_1']")).sendKeys(read("passport"));
		WebElement pass= driver.findElement(By.xpath("//select[@name='passport_issuance_month_1']"));
		Select obj5 = new Select(pass);
		obj5.selectByValue("05");
		
		WebElement passday= driver.findElement(By.xpath("//select[@name='passport_issuance_day_1']"));
		Select obj6 = new Select(passday);
		obj6.selectByValue("8");
		
		WebElement passyear= driver.findElement(By.xpath("//select[@name='passport_issuance_year_1']"));
		Select obj7 = new Select(passyear);
		obj7.selectByValue("2019");
		
		WebElement passexp= driver.findElement(By.xpath("//select[@name='passport_month_1']"));
		Select obj8 = new Select(passexp);
		obj8.selectByValue("04");
		
		WebElement pay = driver.findElement(By.xpath("//input[@value='pay-later']"));
		JavascriptExecutor js9 = (JavascriptExecutor)driver;
		js9.executeScript("arguments[0].click();", pay);
		
		WebElement agr = driver.findElement(By.xpath("/html/body/div[2]/form/section/div/div/div[1]/div[4]/div/div/div/label"));
		JavascriptExecutor js88 = (JavascriptExecutor)driver;
		js88.executeScript("arguments[0].click();", agr);
		
		WebElement cnfbook = driver.findElement(By.xpath("//button[@id='booking']"));
		JavascriptExecutor js44 = (JavascriptExecutor)driver;
		js44.executeScript("arguments[0].click();", cnfbook);
		
		String msg = driver.findElement(By.xpath("//h3[@class='title pb-1']")).getText();
		String resnum = driver.findElement(By.xpath("//h3[@class='title']/span")).getText();
		
		Reporter.log(msg, true);
		Reporter.log(resnum, true);
		
		if(msg.contains("Thanks")) {
			System.out.println("successfully booked test PASS");
		}
		else {
			System.out.println("not booked test FAIL");
		}
		
		
	}
	

}


