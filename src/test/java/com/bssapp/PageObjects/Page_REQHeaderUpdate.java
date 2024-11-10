package com.bssapp.PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class Page_REQHeaderUpdate extends BasePage {


	public Page_REQHeaderUpdate(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
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
	
public void clickOnbtnCancel() {
		
	btn_Cancel.click();
	}

public void headerUpdate(String reqNumber) throws InterruptedException {
	BaseClass.requisitions=new Page_Requisitions(driver);
	BaseClass.reqHeader=new Page_REQHeaderUpdate(driver);
	BaseClass.home=new Page_Home(driver);
	BaseClass.requisitions.clickOnHeaderUpdateBtn();
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	ActionsClass.switchToFrame("_dlgOpenerIframe1");
	String actSubject=BaseClass.reqHeader.getPOSubject();
	System.out.println(actSubject);
	String updatedSubject = "Subject update";
	ActionsClass.clearText(BaseClass.reqHeader.input_Subject);
	BaseClass.reqHeader.inputSubject(updatedSubject);
	ActionsClass.sendKeys(BaseClass.reqHeader.input_Subject, Keys.TAB);
	ActionsClass.waitForElementClickable(btn_Save,5);
	BaseClass.reqHeader.clickOnSavebtn();
	ActionsClass.waitForAlert(15);
	String headerUpdateAlertText=ActionsClass.getAlertText();
	System.out.println(headerUpdateAlertText);
	String expAlertMessage = "513- REQ header updated successfully.";
	ActionsClass.acceptAlert();
	Assert.assertEquals(headerUpdateAlertText, expAlertMessage);
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	ActionsClass.switchtoDefaultContent();
	BaseClass.requisitions.selectREQ(reqNumber);
	BaseClass.requisitions.clickOnHeaderUpdateBtn();
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	ActionsClass.switchToFrame("_dlgOpenerIframe2");
	String getUpdatedSubject=BaseClass.reqHeader.getPOSubject();
	Assert.assertEquals(getUpdatedSubject, updatedSubject);
	BaseClass.reqHeader.clickOnbtnCancel();
	//--Need to Do Header Update with Date Also when Calendar utility Will be written
}

	
}
//TCM-3046---Created this page----aurehman----17thOct2024