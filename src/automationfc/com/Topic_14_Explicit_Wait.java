package automationfc.com;

import java.io.File;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_14_Explicit_Wait {

	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	Actions actions;
	String picture001;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.dirver", System.getProperty("user.dir") + GlobalConstant.WEB_DRIVER_CHROME_FOLDER);
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\WindowDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//explicitWait = new WebDriverWait(driver, 10);
		
		picture001 = System.getProperty("user.dir") + File.separator + "uploadFile" + File.separator + "001.png";
	}
	
	//@Test
	public void TC01() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
		
		click(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ContentPlaceholder1_RadCalendar1_Top")));
		
		Assert.assertEquals(getText(By.id("ctl00_ContentPlaceholder1_Label1")), "No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='Thursday, June 17, 2021']")));
		
		click(By.xpath("//td[@title='Thursday, June 17, 2021']"));
		
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1']/div[@class='raDiv']")));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Thursday, June 17, 2021' and @class='rcSelected rcHover']")));
		
		click(By.xpath("//td[@title='Thursday, June 17, 2021']"));
		
		Assert.assertTrue(isDisplay(By.xpath("//span[text() = 'Thursday, June 17, 2021']")));
	}
	
	//@Test
	public void TC02() {
		driver.get("https://filebin.net/");
		
		sendkey(By.xpath("//input[@type='file']"), picture001);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='progress-bar bg-danger']")));
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//strong[text()='001.png']")));
		
		Assert.assertTrue(isDisplay(By.xpath("//strong[text()='001.png']")));
	}
	
	//@Test
	public void TC_03() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.manage().timeouts().implicitlyWait( 8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);
		
		System.out.println("Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@text() = 'Country']")));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		System.out.println("End: " + getDateTimeNow());
	}
	
	//@Test
	public void TC_04_Fluent() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		driver.manage().timeouts().implicitlyWait( 30, TimeUnit.SECONDS);
		
		System.out.println("Start: " + getDateTimeNow());
		FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(driver.findElement(By.id("javascript_countdown_time")))
												.ignoring(NoSuchElementException.class).pollingEvery(1, TimeUnit.SECONDS).withTimeout(15, TimeUnit.SECONDS);
		fluentWait.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				// TODO Auto-generated method stub
				boolean flag = element.getText().endsWith("02");
				System.out.println("time: " + element.getText());
				return flag;
			}
			
		});
		
		System.out.println("End: " + getDateTimeNow());
	}
	
	@Test
	public void TC_05() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		
		waitForElementAndClick(By.xpath("//button[text() = 'Start']"));
		
		waitForElementAndDisplay(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']"));
	}
	
	public WebElement waitForWebElement(By locator) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).ignoring(NoSuchElementException.class)
												.pollingEvery(1, TimeUnit.SECONDS).withTimeout(15, TimeUnit.SECONDS);
		WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		
		return element;
	}
	
	public void waitForElementAndClick(By locator) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
											.pollingEvery(1,  TimeUnit.SECONDS)
											.ignoring(NoSuchElementException.class);
		WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}

		});
		element.click();
	}
	
	public boolean waitForElementAndDisplay(By locator) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				                           .withTimeout(30, TimeUnit.SECONDS)
				                           .pollingEvery(1, TimeUnit.SECONDS)
				                           .ignoring(NoSuchElementException.class);
		WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
			
		});
		return element.isDisplayed();
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
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
