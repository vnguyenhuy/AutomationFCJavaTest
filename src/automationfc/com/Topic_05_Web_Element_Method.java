package automationfc.com;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Method {
	WebDriver driver;
    String firstName, lastName, fullName, email, pw, confirmPw;
    
    @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com");
		firstName = "william";
		lastName = "vo";
		fullName = firstName + " " + lastName;
		email = generalEmail(firstName);
		pw = "123123";
		confirmPw = "123123";		
	}

	/*@Test
	public void TC_01_Verify_Url() {
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	 */
	
	@Test
	public void TC_02_Verify_Title() {
		driver.findElement(By.cssSelector(".large[alt='Magento Commerce']")).click();
		sleepInSeond(3);
		Assert.assertEquals(driver.getTitle(), "Home page");
		
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		sleepInSeond(3);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();;
		sleepInSeond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}	
	
	@Test
	public void TC_03_Verify_Navigation() {
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		sleepInSeond(3);
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		driver.navigate().back();
		sleepInSeond(3);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.navigate().forward();
		sleepInSeond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	
	@Test
	public void TC_04_Verify_PageSource() {
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		String currentPageSource = driver.getPageSource();
		sleepInSeond(3);
		Assert.assertTrue(currentPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();;
		currentPageSource = driver.getPageSource();
		sleepInSeond(3);
		Assert.assertTrue(currentPageSource.contains("Create an Account"));
	} 
	
	@Test
	public void TC_05_Create_New_Account() {
		driver.get("http://live.demoguru99.com");
		sleepInSeond(3);
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		sleepInSeond(3);
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
	
		sleepInSeond(2);
		driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("#email_address")).sendKeys(email);
		driver.findElement(By.cssSelector("#password")).sendKeys(pw);
		driver.findElement(By.cssSelector("#confirmation")).sendKeys(confirmPw);
		
		driver.findElement(By.cssSelector("button[title = 'Register']")).click();
		sleepInSeond(3);
		String currentLoginText = driver.findElement(By.xpath("//h3[text() = 'Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(currentLoginText.contains(fullName));
		Assert.assertTrue(currentLoginText.contains(email));
		
		driver.findElement(By.cssSelector(".skip-account>.label")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
	}
	
	@Test
	public void TC_06_Login() {
		driver.get("http://live.demoguru99.com");
		sleepInSeond(3);
		driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
		
		driver.findElement(By.cssSelector("#email")).sendKeys(email);
		driver.findElement(By.cssSelector("#pass")).sendKeys(pw);
		driver.findElement(By.xpath("//span[text() = 'Login']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".welcome-msg strong")).getText(), "Hello, " + fullName + "!");
		
		driver.findElement(By.cssSelector(".skip-account>.label")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSeond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String generalEmail(String name) {
		Random rand = new Random();
		int num = rand.nextInt(9999);
		return name + num + "@hotmail.com";
	}
}