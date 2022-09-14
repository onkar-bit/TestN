package AutomationTesting.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Orangeauto {
	
	WebDriver driver;
	
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void url() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		
		WebElement mov= driver.findElement(By.xpath("//a[@id='menu_recruitment_viewRecruitmentModule']"));
		Actions act = new Actions(driver);
		act.moveToElement(mov).build().perform();
		
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")).click();
		
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		WebElement sel= driver.findElement(By.xpath("//select[@id='addJobVacancy_jobTitle']"));
		Select obj = new Select(sel);
		obj.selectByVisibleText("Automation Tester");
		
		driver.findElement(By.xpath("//input[@id='addJobVacancy_name']")).sendKeys("Test engineer");
		driver.findElement(By.xpath("//input[@id='addJobVacancy_hiringManager']")).sendKeys("sravani mallikarjun");
		driver.findElement(By.xpath("//input[@id='addJobVacancy_noOfPositions']")).sendKeys("3");
		
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		
	}

}
