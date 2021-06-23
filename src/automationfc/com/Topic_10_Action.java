package automationfc.com;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	JavascriptExecutor jsExecutor;
	String jsDrapDropPath= System.getProperty("user.dir") + "\\drapAndDrop\\drag_and_drop_helper.js";
	String jQueryPath = System.getProperty("user.dir") + "\\drapAndDrop\\jquery_load_helper.js";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\WindowDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		actions = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 3000);
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
	
	//@Test
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
	
	//@Test
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement doubleClickBtn = driver.findElement(By.xpath("//button[text() = 'Double click me']"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickBtn);
		actions.doubleClick(doubleClickBtn).perform();
		Assert.assertTrue(isDisplay(By.xpath("//p[@id = 'demo' and text() = 'Hello Automation Guys!']")));
	}
	
	//@Test
	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement rightClickBtn = driver.findElement(By.xpath("//span[text() = 'right click me']"));
		
		actions.contextClick(rightClickBtn).perform();
		sleepThreading(3);
		
		Assert.assertTrue(isDisplay(By.cssSelector(".context-menu-icon-copy:not(.context-menu-hover):not(.context-menu-visible)")));
		
		actions.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-copy"))).perform();
		sleepThreading(2);
		Assert.assertTrue(isDisplay(By.cssSelector(".context-menu-icon-copy.context-menu-hover.context-menu-visible")));
		
		actions.click(driver.findElement(By.cssSelector(".context-menu-icon-copy.context-menu-hover.context-menu-visible"))).perform();
		sleepThreading(2);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		alert.accept();
		sleepThreading(1);
		
	}
	
	//@Test
	public void TC_07_Drap_And_Drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement sourceBtn = driver.findElement(By.cssSelector("div[id = 'draggable']"));
		
		WebElement targetBtn = driver.findElement(By.cssSelector("div[id = 'droptarget']"));
		
		actions.dragAndDrop(sourceBtn, targetBtn).perform();
		sleepThreading(2);
		
		Assert.assertTrue(isDisplay(By.xpath("//div[text() = 'You did great!']")) 
				          && Color.fromString(targetBtn.getCssValue("background-color")).asHex().equals("#03a9f4"));
	}	
	
	//@Test
	public void TC_07_Drap_And_Drop_HTML5() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String sourceCss = "#column-a";
		String targetCss = "#column-b";
		
		String jsValue = readFile(jsDrapDropPath);
		
		jsValue = jsValue + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(jsValue);
		
		sleepThreading(3);
		
		Assert.assertEquals(getText(By.xpath("//div[@id='column-a']//header")), "B");
		
		jsExecutor.executeScript(jsValue);
		sleepThreading(3);
		
		Assert.assertEquals(getText(By.xpath("//div[@id='column-a']//header")), "A");
		
	}
	
	@Test
	public void TC_08_Drap_And_Drop_Offset() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepThreading(3);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepThreading(3);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepThreading(3);				
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
