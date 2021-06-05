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

public class Topic_07_Web_Element_TextArea {
	WebDriver driver;
	String loginURL = "http://demo.guru99.com/v4";
	String email;
	String userID;
	String password;

	String name, dob, addr, city, state, pin, mobileNum;
	String editName, editDob, editAddr, editCity, editState, editPin, editMobileNum, editEmail, editPassword;

	By nameTextBox = By.cssSelector("input[name='name']");
	By maleCheckBox = By.cssSelector("input[value = 'm']");
	By dateOfBirth = By.cssSelector("input[name = 'dob']");
	By addrTextArea = By.cssSelector("textarea[name = 'addr']");
	By cityTextBox = By.cssSelector("input[name = 'city']");
	By stateTextBox = By.cssSelector("input[name = 'state']");
	By pinTextBox = By.cssSelector("input[name = 'pinno']");
	By mobileNumTextBox = By.cssSelector("input[name = 'telephoneno']");
	By emailTextBox = By.cssSelector("input[name = 'emailid']");
	By passTextBox = By.cssSelector("input[name = 'password']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(loginURL);

		name = "bruno";
		dob = "1992-03-04";
		addr = "132 Ham Nghi street";
		city = "Lisbon";
		state = "Cali";
		pin = "123123";
		mobileNum = "0983399940";
		
		editAddr = "149 Trinh Dinh Trong"; 
		editCity = "Porto"; 
		editState = "Texas"; 
		editPin = "222222"; 
		editMobileNum = "0918222345"; 
		editEmail = generalEmail("bruno"); 
		editPassword = "333444";
	}

	@Test
	public void Test_01_Register() {
		email = generalEmail("william");

		click(By.xpath("//a[text() = 'here']"));
		sleepInSeond(2);
		sendKey(By.cssSelector("input[name='emailid']"), email);
		click(By.cssSelector("input[name='btnLogin']"));
		sleepInSeond(2);
		userID = getText(By.xpath("//td[text() = 'User ID :']/following-sibling::td"));
		password = getText(By.xpath("//td[text() = 'Password :']/following-sibling::td"));
	}

	@Test
	public void Test_02_Login() {
		driver.get(loginURL);
		sleepInSeond(3);
		sendKey(By.cssSelector("input[name = 'uid']"), userID);
		sendKey(By.cssSelector("input[name = 'password']"), password);
		click(By.cssSelector("input[name = 'btnLogin']"));
		sleepInSeond(2);
		Assert.assertEquals(getText(By.cssSelector("marquee.heading3")), "Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void Test_03_Create_New_User() {
		click(By.xpath("//a[text() = 'New Customer']"));

		sendKey(nameTextBox, name);
		sendKey(dateOfBirth, dob);
		sendKey(addrTextArea, addr);
		sendKey(cityTextBox, city);
		sendKey(stateTextBox, state);
		sendKey(pinTextBox, pin);
		sendKey(mobileNumTextBox, mobileNum);
		sendKey(emailTextBox, email);
		sendKey(passTextBox, password);
		click(By.cssSelector("input[name='sub']"));
		sleepInSeond(3);

		Assert.assertEquals(getText(By.cssSelector(".heading3")), "Customer Registered Successfully!!!");
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Customer Name']/following-sibling::td")), name);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Birthdate']/following-sibling::td")), dob);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Address']/following-sibling::td")), addr);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'City']/following-sibling::td")), city);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'State']/following-sibling::td")), state);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Pin']/following-sibling::td")), pin);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")), mobileNum);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Email']/following-sibling::td")), email);
		userID = getText(By.xpath("//td[text() = 'Customer ID']/following-sibling::td"));
	}

	@Test
	public void Test_04_Edit_User() {
		click(By.xpath("//a[text() = 'Edit Customer']"));
		sendKey(By.cssSelector("input[name='cusid']"), userID);
		click(By.cssSelector("input[name='AccSubmit']"));

		Assert.assertFalse(isEnabled(By.cssSelector("input[name = 'name']")));
		Assert.assertFalse(isEnabled(By.cssSelector("input[name = 'gender']")));
		Assert.assertFalse(isEnabled(By.cssSelector("input[name = 'dob']")));

		Assert.assertEquals(driver.findElement(nameTextBox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dateOfBirth).getAttribute("value"), dob);
		Assert.assertEquals(driver.findElement(addrTextArea).getAttribute("value"), addr);
		Assert.assertEquals(driver.findElement(cityTextBox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextBox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextBox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(mobileNumTextBox).getAttribute("value"), mobileNum);
		Assert.assertEquals(driver.findElement(emailTextBox).getAttribute("value"), email);		

		clear(addrTextArea);
		sendKey(addrTextArea, editAddr);

		clear(cityTextBox);
		sendKey(cityTextBox, editCity);

		clear(stateTextBox);
		sendKey(stateTextBox, editState);

		clear(pinTextBox);
		sendKey(pinTextBox, editPin);

		clear(mobileNumTextBox);
		sendKey(mobileNumTextBox, editMobileNum);

		clear(emailTextBox);
		sendKey(emailTextBox, editEmail);
		
		click(By.cssSelector("input[name='sub']"));
		
		Assert.assertEquals(getText(By.cssSelector(".heading3")), "Customer details updated Successfully!!!");
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Customer Name']/following-sibling::td")), name);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Birthdate']/following-sibling::td")), dob);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Address']/following-sibling::td")), editAddr);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'City']/following-sibling::td")), editCity);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'State']/following-sibling::td")), editState);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Pin']/following-sibling::td")), editPin);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")), editMobileNum);
		Assert.assertEquals(getText(By.xpath("//td[text() = 'Email']/following-sibling::td")), editEmail);
	}

	public void click(By by) {
		driver.findElement(by).click();
	}

	public void sendKey(By by, String string) {
		driver.findElement(by).sendKeys(string);
	}

	public void clear(By by) {
		driver.findElement(by).clear();
	}

	public boolean isEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println("The element is enable");
			return true;
		} else {
			System.out.println("The element is enable");
			return false;
		}
	}

	public boolean isDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			System.out.println("The element is displayed");
			return true;
		} else {
			System.out.println("The element is displayed");
			return false;
		}
	}

	public boolean isSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			System.out.println("The element is selected");
			return true;
		} else {
			System.out.println("The element is selected");
			return false;
		}
	}

	public String getText(By by) {
		return driver.findElement(by).getText();
	}

	public void sleepInSeond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String generalEmail(String name) {
		Random rand = new Random();
		int num = rand.nextInt(9999);
		return name + num + "@hotmail.com";
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}