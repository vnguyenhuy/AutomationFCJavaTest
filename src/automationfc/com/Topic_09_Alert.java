package automationfc.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {

	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + GlobalConstant.WEB_DRIVER_CHROME_FOLDER);
		driver = new ChromeDriver();		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);		
	}
	
	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepThreading(2);
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		sleepThreading(3);
		
		Assert.assertEquals(getText(By.cssSelector("#result")), "You clicked an alert successfully");
	}
	
	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepThreading(2);
		driver.findElement(By.xpath("//button[text() = 'Click for JS Confirm']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		sleepThreading(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Cancel");
	}
	
	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepThreading(2);
		String alertText = "give up the past";
		
		driver.findElement(By.xpath("//button[text() = 'Click for JS Prompt']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepThreading(1);
		alert.sendKeys(alertText);
		sleepThreading(3);
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + alertText);
		sleepThreading(1);
	}
	
	@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");		
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
		
		String homeURL = "http://the-internet.herokuapp.com";
		
		driver.get(homeURL);
		String basic_Auth = driver.findElement(By.xpath("//a[text() = 'Basic Auth']")).getAttribute("href");
		
		String[] urlValue = homeURL.split("//");
		
		String newAuth = urlValue[0] + "//" + "admin:" + "admin@" + urlValue[1] + "/" + basic_Auth;
		
		driver.get(newAuth);
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");
		
	}
	
	public void click(By by) {
		driver.findElement(by).click();
	}
	
	public void sendkey(By by, String text) {
		driver.findElement(by).sendKeys(text);
	}
	
	public boolean isSelected(By by) {
		return driver.findElement(by).isSelected();
	}

	public boolean isEnable(By by) {
		return driver.findElement(by).isEnabled();
	}

	public boolean isDisplay(By by) {
		return driver.findElement(by).isDisplayed();
	}
	
	public String getText(By by) {
		return driver.findElement(by).getText();
	}
	
	public String getAttribute(By by, String attribute) {
		return driver.findElement(by).getAttribute(attribute);
	}
	public void sleepThreading(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
