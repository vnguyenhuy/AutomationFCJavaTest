package automationfc.com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String pageURL;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + GlobalConstant.WEB_DRIVER_CHROME_FOLDER);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}
	
	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		By popupLoginBy = By.cssSelector(".popup-login-tab-login");
		By loginBtnBy = By.cssSelector(".fhs-btn-login");
		By usernameTxtBy = By.cssSelector("#login_username");
		By passwordTxtBy = By.cssSelector("#login_password");
		
		click(popupLoginBy);
		
		Assert.assertFalse(isEnable(loginBtnBy));
		
		/*--------------------------------------------------------------------------*/
		
		sendkey(usernameTxtBy, "william@hotmail.com");
		sendkey(passwordTxtBy, "123123");		
		
		Assert.assertTrue(isEnable(loginBtnBy));
		
		/*--------------------------------------------------------------------------*/
		
		driver.navigate().refresh();
		sleepThreading(1);
		click(popupLoginBy);
		
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginBtnBy));
		sleepThreading(1);
		Assert.assertTrue(isEnable(loginBtnBy));
		
		click(loginBtnBy);
		
		Assert.assertEquals(getText(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")), "Thông tin này không thể để trống");
		Assert.assertEquals(getText(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div")), "Thông tin này không thể để trống");
		
		/*--------------------------------------------------------------------------*/
		
		driver.navigate().refresh();
		sleepThreading(1);
		click(popupLoginBy);
		
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(loginBtnBy));
		sleepThreading(3);
		
		//Assert.assertEquals(getText(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")), "Thông tin này không thể để trống");
		//Assert.assertEquals(getText(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div")), "Thông tin này không thể để trống");
	}
	
	@Test
	public void TC_02_RadioBtn_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[@class = 'kd-title']")));
		
		click(By.xpath("//label[text() = '1.6 Diesel, 77kW']/preceding-sibling::input"));
		sleepThreading(2);
		
		Assert.assertTrue(isSelected(By.xpath("//label[text() = '1.6 Diesel, 77kW']/preceding-sibling::input")));
		
		click(By.xpath("//label[text() = '2.0 Diesel, 103kW']/preceding-sibling::input"));
		
		Assert.assertFalse(isSelected(By.xpath("//label[text() = '1.6 Diesel, 77kW']/preceding-sibling::input")));
		Assert.assertTrue(isSelected(By.xpath("//label[text() = '2.0 Diesel, 103kW']/preceding-sibling::input")));
	}
	
	//@Test
	public void TC_03_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[@class = 'kd-title']")));
		
		click(By.xpath("//label[text() = 'Luggage compartment cover']/preceding-sibling::input"));
		click(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input"));
		click(By.xpath("//label[text() = 'Rain sensor']/preceding-sibling::input"));
		
		Assert.assertTrue(isSelected(By.xpath("//label[text() = 'Luggage compartment cover']/preceding-sibling::input")));
		Assert.assertTrue(isSelected(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input")));
		Assert.assertTrue(isSelected(By.xpath("//label[text() = 'Rain sensor']/preceding-sibling::input")));
	}
	
	@Test
	public void TC_03_Select_All_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		List<WebElement> allItems = driver.findElements(By.xpath("//input[@type = 'checkbox' and not(@disabled)]"));
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[@class = 'kd-title']")));
		sleepThreading(3);
		
		for (WebElement item : allItems) {
			if(!item.isSelected()) {
				item.click();
			}
			
			Assert.assertTrue(item.isSelected());
		}

		for (WebElement item : allItems) {
			if(item.isSelected()) {
				item.click();
			}
			
			Assert.assertFalse(item.isSelected());
		}		 
	}
	
	@Test
	public void TC_04_Custom_Radio_Checkbox() {
		driver.get("https://material.angular.io/components/radio/examples");
		By autumnRadioBtn = By.xpath("//span[contains(text(), 'Winter')]/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(autumnRadioBtn));
		sleepThreading(2);
		Assert.assertTrue(driver.findElement(autumnRadioBtn).isSelected());
	}
	
	@Test
	public void TC_05_Custom_Radio_Checkbox_Google_Form() {
		//Radio Button
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		click(By.cssSelector("div[data-value='Cần Thơ']"));
		sleepThreading(2);
		
		Assert.assertTrue(isDisplay(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")));
		
		//look for the checkbox elements starting with 'Quang'
		List<WebElement> allCheckboxBtn = driver.findElements(By.xpath("//div[starts-with(@aria-label, 'Quảng') and @role='checkbox']"));
		for (WebElement checkboxBtn : allCheckboxBtn) {
			checkboxBtn.click();
			sleepThreading(1);
		}
		
		for(int i=0; i < allCheckboxBtn.size(); i++) {
			Assert.assertEquals(allCheckboxBtn.get(i).getAttribute("aria-checked"), "true");
		}
		
		sleepThreading(2);
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
