package AutomationTesting.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TotalPricechange {
	
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
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void opensite() throws IOException, InterruptedException {
		driver.get(read("shopsite"));
		driver.findElement(By.xpath("//a[normalize-space(text())='Sign in']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(read("login"));
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(read("pass"));
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
		
		WebElement mouse = driver.findElement(By.xpath("//a[@title='Women']"));
		Actions act = new Actions(driver);
		act.moveToElement(mouse).build().perform();
		
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a")).click();
		
		WebElement move = driver.findElement(By.xpath("//img[@title='Faded Short Sleeve T-shirts']"));
		Actions act1 = new Actions(driver);
		act1.moveToElement(move).build().perform();
		
		driver.findElement(By.xpath("//span[text()='More']")).click();
		
		driver.findElement(By.xpath("//button[@name='Submit']")).click();
		driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
		
		String tot = driver.findElement(By.xpath("//span[@id='total_price']")).getText();
		Reporter.log(tot);
		
		WebElement quant=driver.findElement(By.xpath("//input[@class='cart_quantity_input form-control grey']"));
		quant.clear();
		quant.sendKeys("2");
		
		Thread.sleep(8000);
		String tot2 = driver.findElement(By.xpath("//span[@id='total_price']")).getText();
		Reporter.log(tot2);
		
		if(tot.matches(tot2)) {
			System.out.println("The total price is match and not updated test FAIL");
			
		}
		else {
			System.out.println("The total price does not match and updated test PASS");
		}
				
	}
	
	//check Login feature
	@Test
	public void login() throws IOException {
		driver.get(read("shopsite"));
		driver.findElement(By.xpath("//a[@class='login']")).click();
		
		//check password encrypted format or not
		driver.findElement(By.id("passwd")).sendKeys("abcxyz");		
		WebElement input = driver.findElement(By.id("passwd"));
		boolean isEncrypted = input.getAttribute("type").equals("password");
		System.out.println("the output: "+isEncrypted);
	}
	
	@Test
	public void windowhandle() throws IOException {
		driver.get(read("win"));
		driver.findElement(By.xpath("//button[@id='windowButton']")).click();
		
		String parent = driver.getWindowHandle();
		
		ArrayList<String> allwindow = new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(allwindow.get(0));
		System.out.println("The title is: "+driver.getTitle());
		
		driver.switchTo().window(allwindow.get(1));
		String txt = driver.findElement(By.id("sampleHeading")).getText();
		System.out.println("the text :"+txt);
		
	}
	
	//edit detail
	@Test
	public void orangehrmeditjob() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	
		WebElement mouse=driver.findElement(By.xpath("//a[@id='menu_recruitment_viewRecruitmentModule']"));
		Actions act = new Actions(driver);
		act.moveToElement(mouse).build().perform();
		
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")).click();
		driver.findElement(By.xpath("//a[text()='Junior Account Assistant']")).click();
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		
		WebElement sel = driver.findElement(By.xpath("//select[@id='addJobVacancy_jobTitle']"));
		Select obj = new Select(sel);
		obj.selectByVisibleText("Payroll Administrator");
		
		WebElement cle= driver.findElement(By.xpath("//input[@id='addJobVacancy_noOfPositions']"));
		cle.clear();
		cle.sendKeys("5");
		
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		
	}
	
	//add details
	@Test
	public void orangeaddjob() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		
		WebElement rec = driver.findElement(By.xpath("//a[@id='menu_recruitment_viewRecruitmentModule']"));
		Actions act1 = new Actions(driver);
		act1.moveToElement(rec).build().perform();
		
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")).click();
		
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		
		WebElement job = driver.findElement(By.xpath("//select[@id='addJobVacancy_jobTitle']"));
		Select obj1 = new Select(job);
		obj1.selectByVisibleText("HR Manager");
		
		driver.findElement(By.xpath("//input[@id='addJobVacancy_name']")).sendKeys("HR");
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("john dia");
		
		driver.findElement(By.xpath("//input[@id='addJobVacancy_noOfPositions']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}
	
	//attendance
	@Test
	public void punchattendance() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		
		WebElement mousemove = driver.findElement(By.xpath("//a[@id='menu_time_viewTimeModule']"));
		Actions act = new Actions(driver);
		act.moveToElement(mousemove).build().perform();
		
		WebElement mv = driver.findElement(By.xpath("//a[@id='menu_attendance_Attendance']"));
		Actions act2 = new Actions(driver);
		act2.moveToElement(mv).build().perform();
		
		driver.findElement(By.xpath("//a[@id='menu_attendance_punchIn']")).click();
		driver.findElement(By.xpath("//textarea[@id='attendance_note']")).sendKeys("present");
		driver.findElement(By.xpath("//input[@id='btnPunch']")).click();
		driver.findElement(By.xpath("//textarea[@id='attendance_note']")).sendKeys("out");
		driver.findElement(By.xpath("//input[@id='btnPunch']")).click();
		
	}



}
