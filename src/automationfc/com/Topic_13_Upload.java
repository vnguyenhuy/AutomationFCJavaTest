package automationfc.com;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Upload {
	
	WebDriver driver;
	String picture001;
	String picture002;
	String picture003;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + GlobalConstant.WEB_DRIVER_CHROME_FOLDER);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		picture001 = System.getProperty("user.dir") + File.separator + "uploadFile" + File.separator + "001.png";
		picture002 = System.getProperty("user.dir") + File.separator + "uploadFile" + File.separator + "002.png";
		picture003 = System.getProperty("user.dir") + File.separator + "uploadFile" + File.separator + "003.png";
	}		
	
	//@Test
	public void TC_01_Upload_One_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sendkey(By.cssSelector("input[name='files[]']"), picture001);
		sleepThreading(2);
		
		Assert.assertEquals(getText(By.cssSelector(".template-upload .name")), "001.png");
		
		click(By.xpath("//span[text() = 'Start']"));
		sleepThreading(2);
		Assert.assertTrue(isDisplay(By.xpath("//a[text() = '001.png']")));
		
	}
	
	//@Test
	public void TC_02_Upload_Multi_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sendkey(By.cssSelector("input[name='files[]']"), picture001 + "\n" + picture002 + "\n" + picture003);
		sleepThreading(2);
		
		Assert.assertTrue(isDisplay(By.xpath("//p[text() = '001.png']")));
		Assert.assertTrue(isDisplay(By.xpath("//p[text() = '002.png']")));
		Assert.assertTrue(isDisplay(By.xpath("//p[text() = '003.png']")));
		
		List<WebElement> uploadFiles = driver.findElements(By.xpath("//span[text() = 'Start']"));
		
		for (WebElement uploadFile : uploadFiles) {
			sleepThreading(2);
			uploadFile.click();			
		}
		sleepThreading(3);
		Assert.assertTrue(isDisplay(By.xpath("//a[text() = '001.png']")));
		Assert.assertTrue(isDisplay(By.xpath("//a[text() = '002.png']")));
		Assert.assertTrue(isDisplay(By.xpath("//a[text() = '003.png']")));
	}
	
	@Test
	public void TC_03_Robot() throws AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepThreading(2);
		click(By.cssSelector(".btn-success"));
		sleepThreading(2);
		
		StringSelection stringSection = new StringSelection(picture001);		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSection, null);
		
		Robot robot = new Robot();
		sleepThreading(1);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		sleepThreading(2);
		
		Assert.assertTrue(isDisplay(By.xpath("//p[text() = '001.png']")));		
		
		click(By.xpath("//span[text() = 'Start']"));
		sleepThreading(2);
		Assert.assertTrue(isDisplay(By.xpath("//a[text() = '001.png']")));
	}
	public void sleepThreading(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
