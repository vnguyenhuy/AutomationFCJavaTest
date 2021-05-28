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

public class Topic_05_Web_Element_Exercise {
	WebDriver driver;
    String firstName, lastName, fullName, email, pw, confirmPw;
    By emailText = By.cssSelector("#mail");
	By under18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By javaCheckBox = By.cssSelector("#java");
	By passwordtextBox = By.cssSelector("#password");
	By disableCheckBox = By.cssSelector("#check-disbaled");
	By disableButton = By.cssSelector("#button-disabled");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("http://live.demoguru99.com");
		firstName = "william";
		lastName = "vo";
		fullName = firstName + " " + lastName;
		email = generalEmail(firstName);
		pw = "123123";
		confirmPw = "123123";
	}

	/*@Test
	public void TC_01_Verify_Url() {
		
	}
	 
	
	@Test
	public void TC_02_Verify_Title() {
		
	}	
	
	@Test
	public void TC_03_Displayed_Function() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		
		if(isDisplayed(emailText)) {
			sendKey(emailText, "william.vo.2706@gmail.com");
		}
		
		if(isDisplayed(educationTextArea)) {
			sendKey(educationTextArea, "Automation FC");
		}
		
		if(isDisplayed(under18Radio)) {
			click(under18Radio);
		}
	}
	
	
	@Test
	public void TC_04_Selected_Function() {
		click(under18Radio);
		click(javaCheckBox);
		
		Assert.assertTrue(isSelected(under18Radio));
		Assert.assertTrue(isSelected(javaCheckBox));
		
		click(javaCheckBox);
		
		Assert.assertFalse(isSelected(under18Radio));
		Assert.assertFalse(isSelected(javaCheckBox));
	} 
	
	@Test
	public void TC_05_Enable() {
		Assert.assertTrue(isEnable(educationTextArea));
		Assert.assertTrue(isEnable(under18Radio));
		Assert.assertTrue(isEnable(emailText));
		Assert.assertTrue(isEnable(javaCheckBox));		
	}*/
	
	@Test
	public void TC_06_Register_Validate() {
		driver.get("https://login.mailchimp.com/signup/");
		
		By signUpBtn = By.cssSelector("#create-account");
		By newslettercheckBox = By.cssSelector("#marketing_newsletter");
		By email = By.cssSelector("#email");
		By username = By.cssSelector("#new_username");
		By passwordTextbox = By.cssSelector("#new_password");
		By lowercaseCompleted = By.cssSelector(".lowercase-char.completed");
		By uppercaseCompleted = By.cssSelector(".uppercase-char.completed");
		By numberChar = By.cssSelector(".number-char.completed");
		By specialChar = By.cssSelector(".special-char.completed");
		By minChar = By.cssSelector("li[class='8-char completed']"); 
		
		sendKey(email, "william@hotmail.com");
		sendKey(username, "will");
		
		sendKey(passwordTextbox, "AUTOMATION");
		Assert.assertTrue(isDisplayed(uppercaseCompleted));
		Assert.assertFalse(isEnable(signUpBtn));
		
		sendKey(passwordTextbox, "automation");
		Assert.assertTrue(isDisplayed(lowercaseCompleted));
		Assert.assertFalse(isEnable(signUpBtn));
		
		sendKey(passwordTextbox, "123456");
		Assert.assertTrue(isDisplayed(numberChar));
		Assert.assertFalse(isEnable(signUpBtn));
		
		sendKey(passwordTextbox, "#!@$%^&");
		Assert.assertTrue(isDisplayed(specialChar));
		Assert.assertFalse(isEnable(signUpBtn));
		
		sendKey(passwordTextbox, "12345678");
		Assert.assertTrue(isDisplayed(minChar));
		Assert.assertTrue(isDisplayed(numberChar));
		Assert.assertFalse(isEnable(signUpBtn));
		
		sendKey(passwordTextbox, "auto1234AUTO@");
		Assert.assertFalse(isDisplayed(uppercaseCompleted));
		Assert.assertFalse(isDisplayed(lowercaseCompleted));
		Assert.assertFalse(isDisplayed(numberChar));
		Assert.assertFalse(isDisplayed(specialChar));
		Assert.assertFalse(isDisplayed(minChar));
		Assert.assertTrue(isEnable(signUpBtn));
		
		click(newslettercheckBox);
		Assert.assertTrue(isSelected(newslettercheckBox));
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
	
	public boolean isDisplayed(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println(by + " is displayed");
			return true;
		} else {
			System.out.println(by + " is NOT displayed");
			return false;
		}		
	}
	
	public boolean isSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println(by + " is selected");
			return true;
		} else {
			System.out.println(by + " is NOT selected");
			return false;
		}
	}
	
	public boolean isEnable(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println(by + " is enable");
			return true;
		} else {
			System.out.println(by + " is NOT enable");
			return false;
		}
	}
	
	public void sendKey(By by, String string) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(string);
	}
	
	public void click(By by) {
		driver.findElement(by).click();
	}
}