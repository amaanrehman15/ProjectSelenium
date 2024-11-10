package com.bssapp.PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class Page_POHeaderUpdate extends BasePage {

	public Page_POHeaderUpdate(WebDriver driver) {
		super(driver);
		
	}
	
	
	
	
	@FindBy(xpath="//input[@id='subject']")
	public
	WebElement input_Subject;
	
	@FindBy(xpath="//button[@id='okclick']")
	WebElement btn_Save;
	
	@FindBy(xpath="//button[contains(text(),'Cancel')]")
	WebElement btn_Cancel;
	
	public void inputSubject(String Subject) {
		
		input_Subject.sendKeys(Subject);
		
	}
	
	public void clickOnSavebtn() {
		
		btn_Save.click();
	}

	public String getPOSubject() {
		return input_Subject.getAttribute("value");
	}
	
public void clickOnCancelbtn() {
		
	btn_Cancel.click();
	}


public void headerUpdate(String poNumber) throws InterruptedException {

	BaseClass.header=new Page_POHeaderUpdate(driver);
	BaseClass.orders=new Page_ManageOrders(driver);
	BaseClass.home=new Page_Home(driver);
	BaseClass.orders.clickOnBtnHeaderUpdate();
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	ActionsClass.switchToFrame("_dlgOpenerIframe1");
	String actSubject=BaseClass.header.getPOSubject();
	System.out.println(actSubject);
	String updatedSubject = "Subject update";
	ActionsClass.clearText(BaseClass.header.input_Subject);
	BaseClass.header.inputSubject(updatedSubject);
	ActionsClass.sendKeys(BaseClass.header.input_Subject, Keys.TAB);
	ActionsClass.waitForElementClickable(btn_Save,5);
	BaseClass.header.clickOnSavebtn();
	ActionsClass.waitForAlert(15);
	String headerUpdateAlertText=ActionsClass.getAlertText();
	System.out.println(headerUpdateAlertText);
	String expAlertMessage = "280- Header updated successfully.";
	Assert.assertEquals(headerUpdateAlertText, expAlertMessage);
	ActionsClass.acceptAlert();
	Thread.sleep(6000);
	ActionsClass.switchtoDefaultContent();
	BaseClass.orders.selectPO(poNumber);
	Thread.sleep(2000);
	BaseClass.orders.clickOnBtnHeaderUpdate();
	Thread.sleep(7000);
	ActionsClass.switchToFrame("_dlgOpenerIframe2");
	String getUpdatedSubject=BaseClass.header.getPOSubject();
	Assert.assertEquals(getUpdatedSubject, updatedSubject);
	BaseClass.header.clickOnCancelbtn();
}
}


//2024.01 - Create Element and Method of Page Header Update- ntripathi - 10October2024
//2024.02--TCM-3035--Added Header Update Method ---aurehman---15/oct/2024