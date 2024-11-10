package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class Page_Receiving extends BasePage {

	public Page_Receiving(WebDriver driver) {
		super(driver);
		
	}
	
	
	
	@FindBy(xpath="//span[text()='Received date' and contains(@id,'cap')]/../..//input")
	WebElement input_ReceivedDate;
	
	@FindBy(xpath="//a[contains(text(),'Line items')]")
	WebElement tab_LineItem;
	
	@FindBy(xpath="//input[@id='clickedAllChBox2']")
	WebElement checkBox_ReceiveAcceptAll;
	

	@FindBy(xpath="(//span[text()='Close PO'])[2]/../../div/input")
	WebElement checkBox_ClosePO;
	
	@FindBy(xpath="//button[@id='SaveRecPanelToolTab']")
	WebElement btn_Save;
	
	@FindBy(xpath="//input[@id='FIELD3']/..//button[2]/i")
	WebElement icon_Refresh;
	
	
@FindBy(xpath="//button[@class='ui-button ui-corner-all ui-widget btn btn-sm btn-inv-receive1' and contains(text(),'Receive')]")
public WebElement btn_Receive;

@FindBy(xpath="//button[@class='ui-button ui-corner-all ui-widget btn btn-sm font-weight-bold' and contains(text(),'Cancel')]")
WebElement btn_Cancel;

public void inputinReceiveDate(String ReceivedDate) {
	input_ReceivedDate.sendKeys(ReceivedDate);
		
}

public void clickOnLineItemTab() {
	
	tab_LineItem.click();
	
}

public void clickOnCheckboxClosePO() {
	
	checkBox_ClosePO.click();
	
}


public void clickOnBtnSave() {
	
	btn_Save.click();
	
}

public void clickOnReceiveAcceptAllbtn()
{
	checkBox_ReceiveAcceptAll.click();
	
}
public void clickOnIconRefresh() {
	
	icon_Refresh.click();
	
}

public void clickOnReceivebtn() {
	
	btn_Receive.click();
	
}

public void clickOnCancelbtn() {
	
	btn_Cancel.click();
}

public String receivePO(String Date) throws InterruptedException {
	BaseClass.receive=new Page_Receiving(driver);
	BaseClass.orders=new Page_ManageOrders(driver);
	BaseClass.orders.clickOnBtnReceive();
	ActionsClass.switchToWindowIndex(1);
	ActionsClass.waitForPageLoad(30);
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 30);
	BaseClass.receive.inputinReceiveDate(Date);
	BaseClass.receive.clickOnLineItemTab();
	BaseClass.receive.clickOnReceiveAcceptAllbtn();
	BaseClass.receive.clickOnReceivebtn();
	ActionsClass.waitForAlert(15);
	String getActRecevAlert = ActionsClass.getAlertText();
	return getActRecevAlert;
}

}


//2024.01 - TCM-3032 - Create Method of Receiving Page - ntripathi - 14October2024