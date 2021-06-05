package automationfc.com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Action {

	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	Actions actions;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + GlobalConstant.WEB_DRIVER_CHROME_FOLDER);		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		actions = new Actions(driver);
	}
	
	//@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		actions.moveToElement(driver.findElement(By.xpath("//a[text() = 'Tooltips']"))).perform();
		sleepThreading(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "That's what this widget is");
	}
	
	//@Test
	public void TC_02_Hover() {
		driver.get("https://www.myntra.com/");
		
		actions.moveToElement(driver.findElement(By.xpath("//a[text() = 'Kids' and @class = 'desktop-main']"))).perform();
		sleepThreading(1);
		
		actions.click(driver.findElement(By.xpath("//a[@class = 'desktop-categoryName' and text() = 'Home & Bath']"))).perform();
		sleepThreading(2);
		
		Assert.assertTrue(isDisplay(By.xpath("//span[@class = 'breadcrumbs-crumb' and text() = 'Kids Home Bath']")));
	}
	
	//@Test
	public void TC_03_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> retangles = driver.findElements(By.cssSelector(".ui-state-default.ui-selectee"));
	
		actions.clickAndHold(retangles.get(0)).release(retangles.get(3)).perform();
		sleepThreading(2);
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-state-default.ui-selectee.ui-selected")).size(), 4);
	}
	
	@Test
	public void TC_04_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> retangles = driver.findElements(By.cssSelector(".ui-state-default.ui-selectee"));
		
		actions.keyDown(Keys.CONTROL).perform();
		actions.click(retangles.get(0)).perform();
		actions.click(retangles.get(5)).perform();
		actions.click(retangles.get(11)).perform();
		actions.click(retangles.get(8)).perform();
		actions.click(retangles.get(3)).perform();
		actions.click(retangles.get(7)).perform();
		actions.keyUp(Keys.CONTROL).perform();
		sleepThreading(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-state-default.ui-selectee.ui-selected")).size(), 6);
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
