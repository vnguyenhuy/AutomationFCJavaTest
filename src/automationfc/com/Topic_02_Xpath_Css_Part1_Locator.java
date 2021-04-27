package automationfc.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part1_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("");
		sleepInSeond(3);
		
		driver.findElement(By.id("gender-male")).click();
		sleepInSeond(3);
	}

	@Test
	public void TC_02_Class() {
		driver.navigate().refresh();
		
		driver.findElement(By.className("search-box-text")).sendKeys("MacBook");
		sleepInSeond(3);
		
		driver.findElement(By.className("search-box-button")).click();
		sleepInSeond(3);
	}

	@Test
	public void TC_03_Name() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.name("Email")).sendKeys("huy@gmail.com");
		sleepInSeond(3);
		
		driver.findElement(By.name("Newsletter"));
		sleepInSeond(3);
	}
	
	@Test
	public void TC_04_TagName() {
		System.out.println("Sum link = " + driver.findElements(By.tagName("a")).size());
		System.out.println("Sum link = " + driver.findElements(By.tagName("input")).size());		
	}
	
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Log in"));
		sleepInSeond(3);
	}
	
	@Test
	public void TC_06_Partial_LinkText() {
		driver.findElement(By.partialLinkText("Recently viewed products")).click();
		sleepInSeond(3);
		driver.findElement(By.partialLinkText("Recently")).click();
		sleepInSeond(3);
		driver.findElement(By.partialLinkText("viewed products")).click();
		sleepInSeond(3);
	}
	
	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("AutomationFC");
		sleepInSeond(3);
		
		driver.findElement(By.cssSelector("input[class='search-box-text ui-autocomplete-input']")).sendKeys("MacBook");		
		sleepInSeond(3);
		
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("william@gmail.com");
		sleepInSeond(3);
		
		driver.findElement(By.cssSelector("a[href*='login']")).click();
		sleepInSeond(3);
	}
	
	@Test
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("AutomationFC");
		sleepInSeond(3);
		
		driver.findElement(By.xpath("//input[contains(@class, 'search-box-text')]")).sendKeys("MacBook");		
		sleepInSeond(3);
		
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("william@gmail.com");
		sleepInSeond(3);
		
		driver.findElement(By.xpath("//a[contains(@href, 'login')]")).click();
		sleepInSeond(3);
		
		driver.findElement(By.xpath("//a[text() = 'Log in']")).click();
		sleepInSeond(3);
		
		driver.findElement(By.xpath("//a[contains(text(), 'Recently')]")).click();
		sleepInSeond(3);
		
		driver.findElement(By.xpath("//a[contains(text(), 'viewed products')]")).click();
		sleepInSeond(3);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSeond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}