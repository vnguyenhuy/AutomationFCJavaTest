package automationfc.com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup_IFrame {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait webDriverWait;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + GlobalConstant.WEB_DRIVER_CHROME_FOLDER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriverWait = new WebDriverWait(driver, 4000);
		jsExecutor = (JavascriptExecutor) driver;
	}
	
	//@Test
	public void TC_01_ZingPoll( ) {
		driver.get("https://www.zingpoll.com/");
		
		click(By.cssSelector("#Loginform"));
		sleepThreading(3);
		
		Assert.assertTrue(isDisplay(By.cssSelector("#Login .modal-content")));
		
		click(By.cssSelector(".modal-dialog.modal_dialog_custom button[type = 'button'].close"));
		sleepThreading(3);
		
		Assert.assertFalse(isDisplay(By.cssSelector("#Login .modal-content")));
	}
	
	//@Test
	public void TC_02_Shopee() {
		driver.get("https://shopee.vn/");
		
		click(By.cssSelector(".shopee-popup__close-btn"));
		sleepThreading(2);
		
		Assert.assertFalse(isElementDisplay(By.cssSelector(".shopee-popup__container")));
	}

	//@Test
	public void TC_03_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		
		if(isElementDisplay(By.cssSelector("div.mailch-wrap"))) {
			click(By.cssSelector("#close-mailch"));			
		}				
		
		sendkey(By.cssSelector("#search-2 input.search-field"), "Automation");
		click(By.cssSelector("#search-2 .glass"));
		
		List<WebElement> listOfArticle = driver.findElements(By.cssSelector("h3.post-title a"));
		System.out.println(listOfArticle.size());
		sleepThreading(3);
		
		for (WebElement article : listOfArticle) {
			System.out.println(article.getText());
			Assert.assertTrue(article.getText().contains("Automation"));
		}
	}
	
	//@Test
	public void TC_04_Shopee() {
		driver.get("https://shopee.vn/");
		
		String searchProduct = "Iphone Pro";
		click(By.cssSelector(".shopee-popup__close-btn"));
		sleepThreading(2);
		
		sendkey(By.cssSelector(".shopee-searchbar-input__input"), searchProduct);
		click(By.cssSelector("button.btn-solid-primary.btn--s.btn--inline"));
		sleepThreading(2);
		
		List<WebElement> listProducts = driver.findElements(By.cssSelector(".yQmmFK _1POlWt _36CEnF"));
		sleepThreading(2);
		
		for (WebElement product : listProducts) {
			Assert.assertTrue(product.getText().toLowerCase().contains(searchProduct.split(" ")[0]) 
					          || product.getText().toLowerCase().contains(searchProduct.split(" ")[1]));
		}
	}
	
	//@Test
	public void TC_05_IFrame() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[data-testid='fb:page Facebook Social Plugin']")));
		
		String likes = getText(By.cssSelector("._1drq"));
		System.out.println(likes);
		sleepThreading(2);
		
		driver.switchTo().defaultContent();
		System.out.println(getText(By.cssSelector("h1.post-title")));
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'docs.google.com')]")));
		sendkey(By.xpath("//div[contains(@data-params, 'HỌ TÊN')]//input"), "William");
		sleepThreading(3);
	}
	
	@Test
	public void TC_06_Frame() {
		driver.get("https://v1.hdfcbank.com/assets/popuppages/netbanking.htm");
		
		click(By.xpath("(//a[text() = 'Continue to NetBanking'])[2]"));
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		
		sendkey(By.xpath("//input[@name='fldLoginUserId']"), "William");
		click(By.xpath("//a[@onclick = 'return fLogon();']//img[@alt='continue']"));
		Assert.assertTrue(isDisplay(By.xpath("//input[@class='input_password' and @type='password']")));
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
		
		Assert.assertTrue(isDisplay(By.xpath("//a[text() = 'Terms and Conditions']")));
	}
	
	public boolean isElementDisplay(By by) {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		List<WebElement> listOfPopup = driver.findElements(by);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(listOfPopup.size() <= 0) {
			return false;
		} else if(listOfPopup.size() > 0  && !listOfPopup.get(0).isDisplayed()) {
			return false;
		}
		return true;
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
