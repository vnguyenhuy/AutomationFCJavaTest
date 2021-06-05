package automationfc.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Default_DropDown {
	WebDriver driver;
	String loginURL;

	String fname = "Tam", lname = "Huy", email = generalEmail(lname), compName = "TSB", password = "123123", day = "27",
			month = "June", year = "1991";

	//Single DropDown List
	By register = By.cssSelector(".ico-register");
	By resultRegister = By.cssSelector(".result");
	By maleGender = By.cssSelector("#gender-male");
	By fnameTextBox = By.cssSelector("#FirstName");
	By lnameTextBox = By.cssSelector("#LastName");
	By emailTextBox = By.cssSelector("#Email");
	By compNameTextBox = By.cssSelector("#Company");
	By pwTextBox = By.cssSelector("#Password");
	By confirmpwTextBox = By.cssSelector("#ConfirmPassword");
	By registerBtn = By.cssSelector("#register-button");
	By dayDropDownBy = By.cssSelector("select[name = 'DateOfBirthDay']");
	By monthDropDownBy = By.cssSelector("select[name = 'DateOfBirthMonth']");
	By yearDropDownBy = By.cssSelector("select[name = 'DateOfBirthYear']");
	By myAccount = By.cssSelector(".ico-account");

	//Multiple DropDown List	
	By multiJobDropDown = By.cssSelector("#job2");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				GlobalConstant.LOCAL_PATH + GlobalConstant.WEB_DRIVER_CHROME_FOLDER);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		
	}

	@Test(priority = 1)
	public void Test_01_Register() {
		loginURL = "https://demo.nopcommerce.com";
		driver.get(loginURL);
		click(register);
		sleepInSeond(2);
		sendKey(fnameTextBox, fname);
		sendKey(lnameTextBox, lname);
		sendKey(emailTextBox, email);
		sendKey(compNameTextBox, compName);
		sendKey(pwTextBox, password);
		sendKey(confirmpwTextBox, password);

		Select selectDay = selectDropDownByText(dayDropDownBy, day);
		Select selectMoth = selectDropDownByText(monthDropDownBy, month);
		Select selectYear = selectDropDownByText(yearDropDownBy, year);

		Assert.assertEquals(getSizeOfdropDownBySelect(selectDay), 32);
		Assert.assertEquals(getSizeOfdropDownBySelect(selectMoth), 13);
		Assert.assertEquals(getSizeOfdropDownBySelect(selectYear), 112);		

		click(registerBtn);
		sleepInSeond(3);
		Assert.assertEquals(getText(resultRegister), "Your registration completed");

		click(myAccount);
		sleepInSeond(3);
		selectDay = createNewSelect(dayDropDownBy);
		Assert.assertEquals(getSizeOfdropDown(dayDropDownBy), 32);

		selectDay = createNewSelect(monthDropDownBy);
		Assert.assertEquals(getSizeOfdropDown(monthDropDownBy), 13);

		selectDay = createNewSelect(yearDropDownBy);
		Assert.assertEquals(getSizeOfdropDown(yearDropDownBy), 112);
		sleepInSeond(3);
	}

	@Test(priority = 2)
	public void TC_02_Multiple_DropDown() {
		loginURL = "https://automationfc.github.io/basic-form/index.html";
		driver.get(loginURL);
		Select select = createNewSelect(multiJobDropDown);
		
		sleepInSeond(3);
		selectDropDownByTextBySelect(select, "Automation");
		selectDropDownByTextBySelect(select, "Adhoc");
		selectDropDownByTextBySelect(select, "Desktop");
		selectDropDownByTextBySelect(select, "Perfomance");
		
		List<WebElement> lstOfSelectedElements = select.getAllSelectedOptions();
		List<String> lstOfSelectedItems = new ArrayList<String>();
		
		for (WebElement element : lstOfSelectedElements) {
			lstOfSelectedItems.add(element.getText());
		}
		
		Assert.assertTrue(lstOfSelectedItems.contains("Automation"));
		Assert.assertTrue(lstOfSelectedItems.contains("Adhoc"));
		Assert.assertTrue(lstOfSelectedItems.contains("Desktop"));
		Assert.assertTrue(lstOfSelectedItems.contains("Perfomance"));
		
		
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

	public Select createNewSelect(By by) {
		Select select = new Select(driver.findElement(by));
		return select;
	}

	public Select selectDropDownByText(By by, String text) {
		Select select = new Select(driver.findElement(by));
		select.selectByVisibleText(text);
		return select;
	}

	public Select selectDropDownByTextBySelect(Select select, String text) {
		select.selectByVisibleText(text);
		return select;
	}

	public int getSizeOfdropDown(By by) {
		Select select = new Select(driver.findElement(by));
		return select.getOptions().size();
	}

	public int getSizeOfdropDownBySelect(Select select) {
		return select.getOptions().size();
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