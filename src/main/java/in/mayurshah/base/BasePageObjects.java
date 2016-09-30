package in.mayurshah.base;

import in.mayurshah.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePageObjects {

	protected static String getElementText(Log log, WebDriver driver, By by) {
		return getElementText(log, driver.findElement(by));
	}

	protected static String getElementText(Log log, WebElement element) {
		return element.getText();
	}

	protected static String getTextboxText(Log log, WebDriver driver, By by) {
		return getTextboxText(log, driver.findElement(by));
	}

	protected static String getTextboxText(Log log, WebElement element) {
		return element.getAttribute("value");
	}

	protected static void selectInput(Log log, WebDriver driver, By by,
			String value) {
		selectInput(log, driver.findElement(by), value);
	}

	protected static void selectInput(Log log, WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	protected static void enterValueIntoTextBox(Log log, WebDriver driver,
			By by, String str, boolean clear) {
		enterValueIntoTextBox(log, driver.findElement(by), str, clear);
	}

	protected static void enterValueIntoTextBox(Log log, WebElement element,
			String str, boolean clear) {
		if (clear)
			element.clear();
		element.sendKeys(str);
	}

	protected static void clickElement(Log log, WebDriver driver, By by) {
		clickElement(log, driver, driver.findElement(by));
	}

	protected static void clickElement(Log log, WebDriver driver,
			WebElement element) {
		waitForClickable(log, driver, element, 5);
		element.click();
	}

	protected static void waitForFrame(Log log, WebDriver driver, By by,
			int duration) {
		try {
			new WebDriverWait(driver, duration).until(ExpectedConditions
					.frameToBeAvailableAndSwitchToIt(by));
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}
	}

	protected static void containsText(Log log, WebDriver driver, By by,
			String str, boolean caseSensitive) {
		containsText(log, driver.findElement(by), str, caseSensitive);
	}

	protected static void containsText(Log log, WebElement element, String str,
			boolean caseSensitive) {
		log.write("Checking string " + str + " in " + element.getText());
		if (caseSensitive)
			Assert.assertTrue(element.getText().contains(str));
		else
			Assert.assertTrue(element.getText().toLowerCase()
					.contains(str.toLowerCase()));
	}

	protected static void assertText(Log log, WebDriver driver, By by,
			String str, boolean caseSensitive) {
		assertText(log, driver.findElement(by), str, caseSensitive);
	}

	protected static void assertText(Log log, WebElement element, String str,
			boolean caseSensitive) {
		if (caseSensitive)
			Assert.assertEquals(element.getText().trim(), str.trim());
		else
			Assert.assertEquals(element.getText().toLowerCase().trim(), str
					.toLowerCase().trim());
	}

	protected static void assertExists(Log log, WebDriver driver, By by) {
		assertExists(log, driver, driver.findElement(by));
	}

	protected static void assertExists(Log log, WebDriver driver,
			WebElement element) {
		waitForElementVisible(log, driver, element, 10);
		Assert.assertTrue(element.isDisplayed());
	}

	protected static void mouseOver(Log log, WebDriver driver, By by) {
		mouseOver(log, new Actions(driver), driver.findElement(by));
	}

	protected static void mouseOver(Log log, Actions actions, WebElement element) {
		actions.moveToElement(element).perform();
	}

	/**
	 * Function to explicitly wait for an element to be clickable
	 *
	 * @param log -To log steps.
	 * 
	 * @param driver
	 *            -WebDriver currently in use by script
	 * @param by
	 *            -By identifying the element to be interacted with
	 * @param duration
	 *            -Int identifying the wait time in seconds
	 */
	protected static void waitForClickable(Log log, WebDriver driver, By by,
			int duration) {
		for (int x = 0; x <= 5; x++) {
			try {
				new WebDriverWait(driver, duration).until(ExpectedConditions
						.elementToBeClickable(by));
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}

	protected static void waitForClickable(Log log, WebDriver driver,
			WebElement element, int duration) {
		for (int x = 0; x <= 5; x++) {
			try {
				new WebDriverWait(driver, duration).until(ExpectedConditions
						.elementToBeClickable(element));
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}

	protected static void waitForElementVisible(Log log, WebDriver driver,
			By by, int duration) {
		for (int x = 0; x <= 5; x++) {
			try {
				new WebDriverWait(driver, duration).until(ExpectedConditions
						.visibilityOfElementLocated(by));
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}

	protected static void waitForElementVisible(Log log, WebDriver driver,
			WebElement element, int duration) {
		for (int x = 0; x <= 5; x++) {
			try {
				new WebDriverWait(driver, duration).until(ExpectedConditions
						.visibilityOf(element));
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}

}
