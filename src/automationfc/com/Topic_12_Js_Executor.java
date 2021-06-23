package automationfc.com;

import java.util.Random;
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

public class Topic_12_Js_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\WindowDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;		
	}
	
	//@Test
	public void TC_01_Guru() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		sleepInSecond(2);
		
		String domain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(domain, "live.demoguru99.com");
		
		String url = (String) jsExecutor.executeScript("return document.URL");
		Assert.assertEquals(url, "http://live.demoguru99.com/");
		
		clickToElementByJS("//a[text() = 'Mobile']");
		
		clickToElementByJS("//a[@title='Samsung Galaxy']/following-sibling::div[@class='product-info']//a[text() = 'Add to Compare']");
		sleepInSecond(2);
		
		Assert.assertTrue(areExpectedTextInInnerText("The product Samsung Galaxy has been added to comparison list."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		
		Assert.assertTrue(areExpectedTextInInnerText("CUSTOMER SERVICE"));
		
		scrollToElement("//span[text() = 'Newsletter']");
		sleepInSecond(5);
	}
	
	//@Test
	public void TC_2_HTML5() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
		sleepInSecond(2);
		
		sendkeyToElementByJS("//input[@id='fname']", "Malay");
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");
		sleepInSecond(2);
		
		sendkeyToElementByJS("//input[@id='pass']", "Malay");
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");
		sleepInSecond(2);
		
		sendkeyToElementByJS("//input[@id='em']", "123!!@#$%^");
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), 
														"A part following '@' should not contain the symbol '#'.");
		sleepInSecond(2);
	}
	
	//@Test
	public void TC_03_Ubuntu() {
		navigateToUrlByJS("https://login.ubuntu.com/");
		sleepInSecond(2);			
		
		clickToElementByJS("//button[@class='p-button--positive js-close']");
		sleepInSecond(2);
		
		sendkeyToElementByJS("//input[@id='id_email' and @placeholder='Your email address']", "a");
		sleepInSecond(2);		
		
		clickToElementByJS("//span[text() = 'Log in']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='id_email' and @placeholder='Your email address']"), 
														"Please include an '@' in the email address. 'a' is missing an '@'.");	
		sleepInSecond(3);		
	}
	
	@Test
	public void TC_04_Gru() {
		String userID;
		String password;
		String email = generateEmail("milk");
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(2);
		
		clickToElementByJS("//a[text() = 'here']");
		sleepInSecond(1);
		sendkeyToElementByJS("//input[@name='emailid']", email);
		clickToElementByJS("//input[@value='Submit']");
		sleepInSecond(1);
		
		userID = getTextByJS("//td[text()='User ID :']/following-sibling::td");
		password = getTextByJS("//td[text()='Password :']/following-sibling::td");
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(2);
		
		sendkeyToElementByJS("//input[@name='uid']", userID);
		sendkeyToElementByJS("//input[@name='password']", password);
		clickToElementByJS("//input[@name = 'btnLogin']");
		sleepInSecond(2);
		
		clickToElementByJS("//a[text() = 'New Customer']");
		driver.findElement(By.xpath("//input[@name = 'name']")).sendKeys("MyAnh");
		removeAttributeInDOM("//input[@name = 'dob']", "type");
		driver.findElement(By.xpath("//input[@name = 'dob']")).sendKeys("27-06-1991");
		driver.findElement(By.xpath("//textarea[@name = 'addr']")).sendKeys("Ong Park Hang Seo la HLV cua doi tuyen VN");
		driver.findElement(By.xpath("//input[@name = 'city']")).sendKeys("Ho Chi Minh");
		driver.findElement(By.xpath("//input[@name = 'state']")).sendKeys("SouthSide");
		driver.findElement(By.xpath("//input[@name = 'pinno']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name = 'telephoneno']")).sendKeys("0983399940");
		driver.findElement(By.xpath("//input[@name = 'emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("654321");	
		//clickToElementByJS("//input[@value= 'Submit']");
		driver.findElement(By.xpath("//input[@value= 'Submit']")).click();
		sleepInSecond(2);								
		
		Assert.assertEquals(getTextByJS("//p[text() = 'Customer Registered Successfully!!!']"), "Customer Registered Successfully!!!");
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	//useful
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	//useful
	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	//useful
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	//useful
	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].value = " + '"' + value + '"' + ';', getElement(locator));
	}

	
	  public String getTextByJS(String locator) {
		  
			  String text = jsExecutor.executeScript("return arguments[0].innerText;", getElement(locator)).toString();
			  return text;
	  }	 
	
	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	//useful
	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	//useful
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
		
	}
	
	public void sleepInSecond(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String generateEmail(String username) {
		Random random = new Random();
		random.ints(1000, 10000);
		int radString = random.nextInt();
		return username + radString+ "@gmail.com";
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
