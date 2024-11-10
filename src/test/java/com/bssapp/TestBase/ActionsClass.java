package com.bssapp.TestBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ActionsClass extends BaseClass {

WebDriver driver;
public ActionsClass(WebDriver driver){
	this.driver=driver;
	action = new Actions(driver);
}
	
	
	
	public  void switchToWindowIndex(int index) {


		Set<String> handles=driver.getWindowHandles();

		ArrayList<String> window=new ArrayList<>(handles);
		window.get(index);
		driver.switchTo().window(window.get(index));

	}

	public  WebElement waitForElementVisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public  WebElement waitForElementPresent(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public  WebElement waitForElementClickable(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public  void waitForPageLoad(int timeoutInSeconds) {
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeoutInSeconds));
	}
	
	public  Boolean waitForElementInvisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}
	

	public  Alert waitForAlert(int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public  String getAlertText() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		return alertText;
	}



	public  String randomAlphabets(int length) {
		String randomString =RandomStringUtils.randomAlphabetic(length);
		return randomString;
	}
	public static String randomNumbers(int length) {
		String randomNumeric =RandomStringUtils.randomNumeric(length);
		return randomNumeric;
	}


	public  void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}
	public  void switchToFrameBy(String framexpath) {
		driver.switchTo().frame(driver.findElement(By.xpath(framexpath)));
	}
	public  void switchtoDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public  void implicitwait(int waitInSeconds) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitInSeconds));
	}

	public  void sendKeys(WebElement element,Keys keys) {

		element.sendKeys(keys);
	}

	
	public  void sendKeysText(WebElement element,String text) {
		element.click();
		element.clear();
		element.sendKeys(text);
	}
	public  void acceptAlert() {

		driver.switchTo().alert().accept();	
	}

	public  void closeWindowIndex(int index) {
		switchToWindowIndex(index);
		driver.close();
	}
	
public  void selectFirstRow(WebElement element) {
	element.click();
}	

public  String getTodaysDatewithTime() {
	
	DateFormat dateFormat = new SimpleDateFormat("_MM_dd_YYYY_HH:mm:ss");
	Date date1 = new Date();
	String date2= dateFormat.format(date1);
	return date2;

}
public  void clearText(WebElement element) {
	sendKeysText(element, Keys.chord(Keys.CONTROL+"A"));
	sendKeys(element,Keys.BACK_SPACE );

}

public  boolean iselementDisplayed(WebElement element) {
	return element.isDisplayed();
 
}
 
public  void rightClick(WebElement element)
{
	
action.contextClick(element).perform();
}

public  WebElement customElementWithText(String text) {
	
	return driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]"));
 
}


public  String getdefaultValuefromdropdown(String CaptionName) {
	
	Select select = new Select(driver.findElement(By.xpath("//span[text()='"+CaptionName+"']/../../div//select[not(contains(@disabled,'true'))]")));
	String defaultItem = select.getFirstSelectedOption().getText();
	return defaultItem;
	
}

public  String getInputValue(String CaptionName,String Attribute) {
	return driver.findElement(By.xpath("//span[text()='"+CaptionName+"']/../..//input[1]")).getAttribute(Attribute);

}

public  String getInputValueDescription(String CaptionName,String Attribute) {
	return driver.findElement(By.xpath("//span[text()='"+CaptionName+"']/../..//input[2]")).getAttribute(Attribute);

}

public  boolean isElementSelected(String CaptionName ) {
	
	return driver.findElement(By.xpath("(//span[text()='"+CaptionName+"']/../../div//input)[1]")).isSelected();

}
public  void moveToElement(WebElement element) {
	action.moveToElement(element).perform();
	
}
public  void doubleClick(WebElement element) {
	action.doubleClick(element).perform();
	
}
public  void ValidateAndAcceptAlert(int seconds,String expectedAlert) throws InterruptedException {
	waitForAlert(seconds);
	String getAlertText=getAlertText().trim();
	System.out.println(getAlertText);
	Assert.assertEquals(getAlertText, expectedAlert);
	acceptAlert();
	
	
}
public  void ValidateAndAcceptAlert(double seconds,String expectedAlert) throws InterruptedException {
	waitForAlert((int)seconds);
	String getAlertText=getAlertText().trim();
	System.out.println(getAlertText);
	Assert.assertTrue(getAlertText.contains(expectedAlert));
	acceptAlert();
	
	
}
}

//2024.01 - TCM-3032 - Create validatePurchaseType - ntripathi - 14October2024
//2024.02 -TCM-3035--Added Methods---aurehman--14/10/2024
//2024.03 -TCM-3054--Added Methods--ntripathi --22Oct2024
//2024.04--TCM-3055--Added Method---Move To Element---aurehman--21/10/2024