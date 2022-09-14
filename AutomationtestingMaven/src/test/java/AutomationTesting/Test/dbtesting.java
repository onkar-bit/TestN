package AutomationTesting.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class dbtesting {
	
	WebDriver driver;
	
	
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void sort() {
		driver.get("http://tutorialsninja.com/demo/index.php?route=common/home");
		WebElement move= driver.findElement(By.xpath("//a[text()='Desktops']"));
		Actions act = new Actions(driver);
		act.moveToElement(move).build().perform();
		
		driver.findElement(By.xpath("//a[text()='Show All Desktops']")).click();
		
		WebElement sel = driver.findElement(By.xpath("//select[@id='input-sort']"));
		Select s1 = new Select(sel);
		s1.selectByVisibleText("Price (High > Low)");
		
		List<WebElement> price = driver.findElements(By.xpath("//p[@class='price']"));
		
		List<String> prices = new ArrayList<String>();
		
		for(WebElement e : price) {
			prices.add(e.getText());
		}
		
		List<String> sortedPrice = new ArrayList<String>(prices);
		
		Collections.sort(sortedPrice);
		
		System.out.println("product price list: "+sortedPrice);
		System.out.println("first product price after sort: "+prices.get(0));
		
		
	}
	
	@Test
	public void high() {
		driver.get("http://tutorialsninja.com/demo/index.php?route=common/home");
		WebElement move = driver.findElement(By.xpath("//a[text()='Desktops']"));
		Actions actm = new Actions(driver);
		actm.moveToElement(move).build().perform();
		
		driver.findElement(By.xpath("//a[text()='Show All Desktops']")).click();
		
		WebElement sel2 = driver.findElement(By.xpath("//select[@id='input-sort']"));
		Select obj4 = new Select(sel2);
		obj4.selectByVisibleText("Price (Low > High)");
		
		List<WebElement> price = driver.findElements(By.xpath("//p[@class='price']"));
		
		List<String> prices = new ArrayList<String>();
		
		for(WebElement e: price) {
			prices.add(e.getText());
		}
		
		List<String> sortedprices = new ArrayList<String>(prices);
		Collections.sort(sortedprices);
		System.out.println("price "+sortedprices);
		System.out.println("after sort first price: "+prices.get(0));
		
	}


}
