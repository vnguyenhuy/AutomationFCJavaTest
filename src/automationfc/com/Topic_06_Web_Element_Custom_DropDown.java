package automationfc.com;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Custom_DropDown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String loginURL = "https://jqueryui.com/resources/demos/selectmenu/default.html";
	JavascriptExecutor executor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				GlobalConstant.LOCAL_PATH + GlobalConstant.WEB_DRIVER_CHROME_FOLDER);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(loginURL);

		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
	}

	// @Test(priority = 1)
	public void Test_01_JQuery() {
		driver.get(loginURL);
		sleepInSeond(1);

		selectItemInCustomDropdown(By
				.xpath("//label[text() = 'Select a number']/following-sibling::span/span[@class='ui-selectmenu-text']"),
				By.cssSelector("ul#number-menu div"), "8");
		sleepInSeond(1);
		Assert.assertEquals(getText(By.xpath(
				"//label[text() = 'Select a number']/following-sibling::span/span[@class='ui-selectmenu-text']")), "8");

		selectItemInCustomDropdown(By
				.xpath("//label[text() = 'Select a number']/following-sibling::span/span[@class='ui-selectmenu-text']"),
				By.cssSelector("ul#number-menu div"), "16");
		sleepInSeond(1);
		Assert.assertEquals(getText(By.xpath(
				"//label[text() = 'Select a number']/following-sibling::span/span[@class='ui-selectmenu-text']")),
				"16");

		selectItemInCustomDropdown(By
				.xpath("//label[text() = 'Select a number']/following-sibling::span/span[@class='ui-selectmenu-text']"),
				By.cssSelector("ul#number-menu div"), "5");
		sleepInSeond(1);
		Assert.assertEquals(getText(By.xpath(
				"//label[text() = 'Select a number']/following-sibling::span/span[@class='ui-selectmenu-text']")), "5");
	}

	// @Test(priority = 2)
	public void Test_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSeond(2);

		selectItemInCustomDropdown(By.cssSelector(".ui.fluid.selection.dropdown"), By.cssSelector("div.item>span.text"),
				"Elliot Fu");
		sleepInSeond(1);
		Assert.assertTrue(isDisplayed(By.xpath("//div[@class = 'divider text' and text() = 'Elliot Fu']")));

		selectItemInCustomDropdown(By.cssSelector(".ui.fluid.selection.dropdown"), By.cssSelector("div.item>span.text"),
				"Christian");
		sleepInSeond(1);
		Assert.assertTrue(isDisplayed(By.xpath("//div[@class = 'divider text' and text() = 'Christian']")));

		selectItemInCustomDropdown(By.cssSelector(".ui.fluid.selection.dropdown"), By.cssSelector("div.item>span.text"),
				"Justen Kitsune");
		sleepInSeond(1);
		Assert.assertTrue(isDisplayed(By.xpath("//div[@class = 'divider text' and text() = 'Justen Kitsune']")));

		selectItemInCustomDropdown(By.cssSelector(".ui.fluid.selection.dropdown"), By.cssSelector("div.item>span.text"),
				"Stevie Feliciano");
		sleepInSeond(1);
		Assert.assertTrue(isDisplayed(By.xpath("//div[@class = 'divider text' and text() = 'Stevie Feliciano']")));
	}

	// @Test(priority = 3)
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSeond(1);

		selectItemInCustomDropdown(By.cssSelector(".dropdown-toggle"),
				By.xpath("//ul[@class = 'dropdown-menu']//a[contains(text(), 'First Option')]"), "First Option");
		Assert.assertTrue(getText(By.xpath("//li[@class = 'dropdown-toggle']")).trim().equals("First Option"));

		selectItemInCustomDropdown(By.cssSelector(".dropdown-toggle"),
				By.xpath("//ul[@class = 'dropdown-menu']//a[contains(text(), 'Second Option')]"), "Second Option");
		Assert.assertTrue(getText(By.xpath("//li[@class = 'dropdown-toggle']")).trim().equals("Second Option"));

		selectItemInCustomDropdown(By.cssSelector(".dropdown-toggle"),
				By.xpath("//ul[@class = 'dropdown-menu']//a[contains(text(), 'Third Option')]"), "Third Option");
		Assert.assertTrue(getText(By.xpath("//li[@class = 'dropdown-toggle']")).trim().equals("Third Option"));
	}

	// @Test(priority = 4)
	public void TC_04_Default() {
		driver.get("https://demo.nopcommerce.com/register");
		sleepInSeond(1);

		selectItemInCustomDropdown(By.cssSelector("select[name = 'DateOfBirthDay']"),
				By.cssSelector("select[name = 'DateOfBirthDay']>option"), "15");
		Assert.assertTrue(isSelected(By.xpath("//option[text() = '15']")));
		sleepInSeond(1);

		selectItemInCustomDropdown(By.cssSelector("select[name = 'DateOfBirthDay']"),
				By.cssSelector("select[name = 'DateOfBirthDay']>option"), "25");
		Assert.assertTrue(isSelected(By.xpath("//option[text() = '25']")));
		sleepInSeond(1);

		selectItemInCustomDropdown(By.cssSelector("select[name = 'DateOfBirthDay']"),
				By.cssSelector("select[name = 'DateOfBirthDay']>option"), "5");
		Assert.assertTrue(isSelected(By.xpath("//option[text() = '5']")));
		sleepInSeond(2);
	}

	//@Test(priority = 5)
	public void TC_05_AngularJS() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemInCustomDropdown(By.xpath(
				"//ejs-dropdownlist[@id='games']//span[@class='e-input-group e-control-wrapper e-ddl e-lib e-keyboard']"),
				By.xpath("//ul[@id='games_options']/li"), "Badminton");
		Assert.assertEquals(
				driver.findElement(By.xpath("//ejs-dropdownlist[@id = 'games']//input")).getAttribute("aria-label"),
				"Badminton");
	}

	//@Test(priority = 6)
	public void TC_06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemEditableInCustomDropdown(By.xpath("//input[@class='search']"), By.xpath("//div[@class='visible menu transition']//span"), "Albania");
		sleepInSeond(1);
		Assert.assertTrue(isDisplayed(By.xpath("//div[@class='ui fluid search selection dropdown']/div[text() = 'Albania']")));
		
		selectItemEditableInCustomDropdown(By.xpath("//input[@class='search']"), By.xpath("//div[@class='visible menu transition']//span"), "Bahamas");
		sleepInSeond(1);
		Assert.assertTrue(isDisplayed(By.xpath("//div[@class='ui fluid search selection dropdown']/div[text() = 'Bahamas']")));
		
		selectItemEditableInCustomDropdown(By.xpath("//input[@class='search']"), By.xpath("//div[@class='visible menu transition']//span"), "Belgium");
		sleepInSeond(1);
		Assert.assertTrue(isDisplayed(By.xpath("//div[@class='ui fluid search selection dropdown']/div[text() = 'Belgium']")));
		
		selectItemEditableInCustomDropdown(By.xpath("//input[@class='search']"), By.xpath("//div[@class='visible menu transition']//span"), "Barbados");
		sleepInSeond(1);
		Assert.assertTrue(isDisplayed(By.xpath("//div[@class='ui fluid search selection dropdown']/div[text() = 'Barbados']")));
	}
	
	@Test(priority = 7)
	public void TC_07_Multiple_Dropdown() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		String expectedList[] = {"September", "July", /*"April",*/ "January"};
		selectMultipleItemInDropdown(By.xpath("(//div[@class = 'ms-parent multiple-select'])[1]"), By.xpath("(//div[@class = 'ms-drop bottom'])[1]//span"), expectedList);
		sleepInSeond(1);
		verifyMultipleItemInDropdown(By.xpath("(//div[@class = 'ms-parent multiple-select'])[1]/button[@class='ms-choice']/span"), 
									 By.xpath("(//div[@class = 'ms-parent multiple-select'])[1]//div[@class='ms-drop bottom']//span"), 
									 By.xpath("(//div[@class = 'ms-parent multiple-select'])[1]//li[@class='selected']//span"));
		sleepInSeond(3);
	}
	
	public void selectItemInCustomDropdown(By parentBy, By childBy, String expectedText) {
		click(parentBy);
		// sleepInSeond(1);

		List<WebElement> allChildItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		for (WebElement item : allChildItems) {
			if (item.getText().trim().equals(expectedText)) {
				System.out.println("The element: " + item.getText());
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSeond(1);
				item.click();
				sleepInSeond(1);
				break;
			}
		}
	}

	public void selectMultipleItemInDropdown(By parentBy, By childBy, String[] expectedList) {
		click(parentBy);
		
		List<WebElement> allChildItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		for (String expectedText : expectedList) {
			for (WebElement childItem : allChildItem) {				
				if(childItem.getText().trim().equals(expectedText)) {
					System.out.println("select item: " + expectedText);
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childItem);
					childItem.click();
					sleepInSeond(1);
					break;
				}
			}
		}
		
	}
	
	public boolean verifyMultipleItemInDropdown(By parentBy, By childBy, By selectedList) {
		boolean status = false;
		String displayedText = getText(parentBy);
		
		
		List<WebElement>childItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		int sizeOfChildItems = childItems.size();
		
		List<WebElement> selectedItems = driver.findElements(selectedList);
		int sizeOfSlectedItems = selectedItems.size();
				
		if(sizeOfSlectedItems <= 3) {
			for (WebElement item : selectedItems) {
				if(!displayedText.contains(item.getText())) {
					status = false;
					break;
				   }
			}
		}
		else if(sizeOfSlectedItems > 3 && sizeOfSlectedItems < 12) {
			if(getText(parentBy).equals(sizeOfSlectedItems + " of " + sizeOfChildItems + " selected")) {
				status = true;
			}
		}
		else {
			if(getText(parentBy).equals("All selected")) {
				status = true;
			}
		}
		return status;
	}
	
	public void selectItemEditableInCustomDropdown(By parentBy, By childBy, String expectedText) {
		click(parentBy);
		
		sendKey(parentBy, expectedText);
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		for (WebElement webElm : allItems) {
			if(webElm.getText().trim().equals(expectedText)) {
				System.out.println("The element: " + webElm.getText());
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElm);
				webElm.click();
			}
		}
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