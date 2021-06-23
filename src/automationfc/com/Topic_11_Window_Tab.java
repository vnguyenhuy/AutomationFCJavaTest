package automationfc.com;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Window_Tab {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + GlobalConstant.WEB_DRIVER_CHROME_FOLDER);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 4);
		jsExecutor = (JavascriptExecutor) driver;
	}
	
	//@Test
	public void TC_01_New_Window() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentTitle = "SELENIUM WEBDRIVER FORM DEMO";
		
		click(By.xpath("//a[text() = 'GOOGLE']"));		
		sleepThreading(2);
		switchToNewWindowByTitle("Google");		
		Assert.assertEquals(driver.getTitle(), "Google");		
		
		switchToNewWindowByTitle(parentTitle);		
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
				
		click(By.xpath("//a[text() = 'FACEBOOK']"));
		sleepThreading(2);
		switchToNewWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");		
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");		
		
		switchToNewWindowByTitle(parentTitle);	
		
		click(By.xpath("//a[text() = 'TIKI']"));
		sleepThreading(2);
		switchToNewWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");		
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		switchToNewWindowByTitle(parentTitle);
		closeAllWindowWithoutParent(parentTitle);
		
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	}
	
	//@Test
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");
		String parentTitle = driver.getTitle();
		
		click(By.xpath("//div[@id='k-footer']//img[@alt='facebook']"));		
		switchToNewWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		sleepThreading(2);
		
		closeAllWindowWithoutParent(parentTitle);
	}
	
	@Test
	public void TC_03_LiveGruu() {
		driver.get("http://live.demoguru99.com/index.php/");		
		sleepThreading(2);
		
		click(By.xpath("//a[text() = 'Mobile']"));
		String parentTitle = driver.getTitle();
		sleepThreading(2);
		
		click(By.xpath("//a[@title='Xperia']/following-sibling::div[@class='product-info']//a[text() = 'Add to Compare']"));		
		Assert.assertTrue(isDisplay(By.xpath("//span[text() = 'The product Sony Xperia has been added to comparison list.']")));
		
		click(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div[@class='product-info']//a[text() = 'Add to Compare']"));		
		//jsExecutor.executeScript("arguments[0].scrollIntoView(true);", 
		//						  driver.findElement(By.xpath("//span[text() = 'The product Samsung Galaxy has been added to comparison list.")));
		sleepThreading(1);
		Assert.assertTrue(isDisplay(By.xpath("//span[text() = 'The product Samsung Galaxy has been added to comparison list.']")));
		
		click(By.xpath("//span[text() = 'Compare']"));
		sleepThreading(2);
		
		switchToNewWindowByTitle("Products Comparison List - Magento Commerce");		
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		switchToNewWindowByTitle(parentTitle);
		closeAllWindowWithoutParent(parentTitle);
		
		click(By.xpath("//a[text() = 'Clear All']"));
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepThreading(3);
		
		alert.accept();
	}
	
	public void switchToNewWindowByTitle(String pageTitle) {
		Set<String> allPagesID = driver.getWindowHandles();
		
		for (String id : allPagesID) {
			driver.switchTo().window(id);
			if(pageTitle.equals(driver.getTitle())) {
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParent(String parentPage) {
		Set<String> allPagesID = driver.getWindowHandles();
		String parentID = "";
		for (String pageID : allPagesID) {
			driver.switchTo().window(pageID);
			if(!driver.getTitle().equals(parentPage)) {				
				driver.close();
			}
			else {
				parentID = pageID;
			}
		}
		driver.switchTo().window(parentID);
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
