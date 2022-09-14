package AutomationTesting.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class shopping {
	
	WebDriver driver;
	
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void search() {
		driver.get("https://shop.demoqa.com/");
		
		String text = driver.findElement(By.xpath("//a[text()='Tokyo Talkies']")).getText();
		System.out.println("the text: "+text);
		
		driver.findElement(By.xpath("//a[@class='noo-search']")).click();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("TOKYO TALKIES",Keys.ENTER);
		
		String txt = driver.findElement(By.xpath("//h1[@class='product_title entry-title']")).getText();
		if(text.equals(txt)) {
			System.out.println("searched product match test PASS");
		}
		else {
			System.out.println("searched product not match test FAIL");
		}
	}
	
	@Test
	public void buy() throws InterruptedException {
		driver.get("https://shop.demoqa.com/");
		
		driver.findElement(By.xpath("//*[@id=\"noo-site\"]/div[2]/div[4]/div/div[1]/div/div/div/div[2]/div[2]/div[1]/div/h3/a")).click();
		driver.findElement(By.xpath("//button[@class='single_add_to_cart_button button alt']")).click();
		driver.findElement(By.xpath("//a[text()='View cart']")).click();
		driver.findElement(By.xpath("//a[normalize-space(text())='Proceed to checkout']")).click();
		
		driver.findElement(By.xpath("//input[@id='billing_first_name']")).sendKeys("ajay");
		driver.findElement(By.xpath("//input[@id='billing_last_name']")).sendKeys("demo");
		
		driver.findElement(By.xpath("//input[@id='billing_address_1']")).sendKeys("north pune");
		driver.findElement(By.xpath("//input[@id='billing_city']")).sendKeys("pune");
		
		WebElement sel = driver.findElement(By.xpath("//select[@id='billing_state']"));
		Select obj = new Select(sel);
		obj.selectByVisibleText("Maharashtra");
		
		driver.findElement(By.xpath("//input[@id='billing_postcode']")).sendKeys("542225");
		driver.findElement(By.xpath("//input[@id='billing_phone']")).sendKeys("8888555547");
		driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("om@gmail.com");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-200)");
		
		driver.findElement(By.xpath("//input[@id='terms']")).click();
		
		driver.findElement(By.xpath("//button[@id='place_order']")).click();
		Thread.sleep(5000);
		
		JavascriptExecutor js5= (JavascriptExecutor)driver;
		js5.executeScript("window.scrollBy(0,500)");
		
		String msg = driver.findElement(By.xpath("//div[@class='noo-checkout-complete']/p[1]")).getText();
		
		if(msg.contains("Thank")) {
			System.out.println("order place test PASS");
		}
		else{
			System.out.println("order not placed test FAIL");
		}
		
	}
	
}

  
