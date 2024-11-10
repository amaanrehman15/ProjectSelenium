package com.bssapp.PageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class Page_Requisitions extends BasePage {

	
	public Page_Requisitions(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath = "//div[@row-index='0']//div[@aria-colindex='3']")
	WebElement get_ReqNumber;
	
	@FindBy(xpath="//div[@row-index=0]//div[@aria-colindex='7']")
	WebElement get_ReqStatus;
	
	@FindBy(xpath="//span[text()='Views']")
	WebElement rightClick_Views;
	
	@FindBy(xpath="//span[text()='Print / View / Edit']")
	WebElement rightClick_PrintViewEdit;
	
	@FindBy(xpath="//button[@id='HeaderValue']")
	WebElement btn_HeaderUpdate;
	
	@FindBy(xpath="//button[normalize-space()='Actions']")
	WebElement btn_Actions;
	
	@FindBy(xpath="//span[text()='Copy REQ']")
	WebElement btn_CopyReq;
	@FindBy(xpath="//span[contains(text(),'Delete REQ')]")
	WebElement btn_DeleteReq;
	
	@FindBy(xpath="//Select[@id='multipanels']")
	public WebElement dropdown_Multipanels;
	
	@FindBy(xpath="//span[text()='REQ number']/../preceding-sibling::span")
	public WebElement icon_FilterReq;
	@FindBy(xpath="//input[@placeholder=\"Filter...\"]")
	WebElement input_Filter;
	
	@FindBy(xpath="//button[text()='Apply Filter']")
	WebElement btn_ApplyFilter;
	
	@FindBy(xpath="//i[contains(text(),'refresh')]")
	WebElement icon_Refresh;
	
	@FindBy(xpath="//button[contains(text(),'Info')]")
	WebElement btn_Info;
	
	@FindBy(xpath="//span[contains(text(),'Notes')]")
	WebElement btn_NotesOnInfo;
	@FindBy(xpath="//img[contains(@onclick,'FPShowNote')]")
	WebElement icon_Notes;
	@FindBy(xpath="//span[contains(text(),'Add to cart')]")
	WebElement btn_AddToCart;
	public void clickOnHeaderUpdateBtn() {
		btn_HeaderUpdate.click();
	}
	
	public  String getReqNumber() {
		String getREQNumber = get_ReqNumber.getText();
		return getREQNumber;
	}
 
	public String getReqStatus() {
		String getREQStatus=	get_ReqStatus.getText();
		return getREQStatus;
	}
	public void clickOnBtnrightClickViews() {
		rightClick_Views.click();
	}
	
	public void clickOnBtnrightClickPrintView() {
		rightClick_PrintViewEdit.click();
	}
	
	public void clickOnBtnActions() {
		btn_Actions.click();
}
	
	public void clickOnBtnCopyReq() {
		btn_CopyReq.click();
}
	
	
	public void clickOnBtnInfo() {
		btn_Info.click();
}

	public void clickOnBtnNotesOnInfo() {
		btn_NotesOnInfo.click();
}
	public String copyReq() throws InterruptedException {
		BaseClass.requisitions.clickOnBtnActions();
		Thread.sleep(500);
		BaseClass.requisitions.clickOnBtnCopyReq();
		ActionsClass.waitForAlert(15);
		String CopyReqMsg=ActionsClass.getAlertText();
		ActionsClass.acceptAlert();
		return CopyReqMsg;
	}
	
	
	public  HashMap<String, String> deleteReqFromActions() throws InterruptedException {

		ActionsClass.switchToWindowIndex(0);
		BaseClass.requisitions.clickOnBtnActions();
		BaseClass.requisitions.clickOnDeleteReqBtn();
		ActionsClass.waitForAlert(30);
		String actDeleteAlertText = ActionsClass.getAlertText();
		ActionsClass.acceptAlert();
		ActionsClass.waitForAlert(5);
		String actDeleteAlertText1 = ActionsClass.getAlertText();
		ActionsClass.acceptAlert();
		HashMap<String,String> m=new HashMap<>();
		m.put("DeleteReqAlertTextDatabse", actDeleteAlertText);
		m.put("ReqDeletedAlertText",actDeleteAlertText1);
		return m;

	}

	private void clickOnDeleteReqBtn() {
		btn_DeleteReq.click();
		
	}
	
public void selectDropdownMultiPanels(String text) {
		
		
		selectByVisibleText(dropdown_Multipanels, text);
		
		
	}
	
public void clickOnIconReqNumberFilter() {
	 
	icon_FilterReq.click();
}

public void inputinFilter(String Value) {
	input_Filter.sendKeys(Value);

}
public void clickOnApplyFilterbtn() {
	btn_ApplyFilter.click();
}
public void clickOnRefreshIcon() {
	icon_Refresh.click();
}
public void selectREQ(String reqNumber) {
	
	driver.findElement(By.xpath("//span[contains(text(),'"+reqNumber+"')]")).click();
}
public void clickOnIconNotes() {
	icon_Notes.click();
}

public void clickOnBtnAddToCart() {
	btn_AddToCart.click();
	
}
	
}
//TCM-3046--Created This Page---aurehman--17thOct20204