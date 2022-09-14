package AutomationTesting.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AssignmentFilpkart {
	
	WebDriver driver;
	
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\workplace\\AutomationTestingMaven\\Drivers\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void openflipkart() {
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		
		//check page is opened
		String actual = driver.getTitle();
		String expected = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
		Assert.assertEquals(actual, expected);
		Reporter.log("page nevigated", true);
		
		//search product
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Tv", Keys.ENTER);
		
		//validate search result displayed
		String actual_title_tv =driver.getTitle();
		if(actual_title_tv.contains("Tv")) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}
		
		Reporter.log("search results displayed", true);
		
		//fetch all product
		List<WebElement> list_product_name = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		List<WebElement> list_product_price = driver.findElements(By.xpath("//div[@class='_30jeq3 _1_WHN1']"));
		
		//hash map for store product
		String product_name;
		String product_price;
		int int_product_price;
		HashMap<Integer, String> map_final_products = new HashMap<Integer, String>();
		for(int i=0; i<list_product_name.size(); i++) {
			product_name = list_product_name.get(i).getText();
			product_price = list_product_price.get(i).getText();
			product_price = product_price.replaceAll("[^0-9]", "");
			int_product_price = Integer.parseInt(product_price);
			map_final_products.put(int_product_price, product_name);
			
			//get all keys from hash map
			Set<Integer> allkeys = map_final_products.keySet();
			ArrayList<Integer> array_list_values_product_prices = new ArrayList<Integer>(allkeys);
			Collections.sort(array_list_values_product_prices);
			
			//highest product price
			int high_price = array_list_values_product_prices.get(array_list_values_product_prices.size()-1);
			
			//lowest price
			int low_price = array_list_values_product_prices.get(0);
			
			Reporter.log("high product price is: "+high_price + "product name is: "+map_final_products.get(high_price), true);
			Reporter.log("lowest product price is: " +low_price + "product name is: "+map_final_products.get(low_price), true);
		}
	}

}
