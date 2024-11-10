package com.bssapp.PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class Page_POEditLine extends BasePage{

	public Page_POEditLine(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='ORDER_QTY']")
	public WebElement input_OrderQty;
	
	@FindBy(xpath="//button[@id='okclick']")
	WebElement btn_Save;
	
	@FindBy(xpath="//button[@id='CloseID']")
	WebElement btn_Close;
	
	
	public void inputOrderQuantity(String OrderQuantity) {
		
		input_OrderQty.sendKeys(OrderQuantity);
	}
	
	public void clickOnSavebtn() {
		btn_Save.click();
		
	}
	
	public void clickOnCancelbtn() {
		
		btn_Close.click();
	}
	public String getOrderQty() {
		return input_OrderQty.getAttribute("value");
	}
	
	
	
	public void editQtyOnPrintViewEditLine(String changeEditLineQty) throws InterruptedException {
	
		BaseClass.orders=new Page_ManageOrders(driver);
		BaseClass.home=new Page_Home(driver);
		BaseClass.editLine=new Page_POEditLine(driver);
		BaseClass.details=new Page_POPrintwDetails(driver);
		ActionsClass.switchtoDefaultContent();
		BaseClass.orders.clickOnBtnPrintViewDetails();
		ActionsClass.switchToWindowIndex(1);
		ActionsClass.waitForPageLoad(40);
		Thread.sleep(2000);
		BaseClass.details.clickonHyperlinkEditLine();
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		ActionsClass.switchToFrame("_dlgOpenerIframe1");
		ActionsClass.clearText(BaseClass.editLine.input_OrderQty);
		ActionsClass.clearText(BaseClass.editLine.input_OrderQty);
		Thread.sleep(2000);
		
		ActionsClass.sendKeys(BaseClass.editLine.input_OrderQty, Keys.TAB);
		BaseClass.editLine.inputOrderQuantity(changeEditLineQty);
		Thread.sleep(1000);
		BaseClass.editLine.clickOnSavebtn();
		ActionsClass.waitForAlert(30);
		String actUpdateAlertText = ActionsClass.getAlertText();
		ActionsClass.acceptAlert();
		String expUpdateAlertText = "Click ‘Ok’ to confirm the Supplier tab in the LIM, Price Group and Catalog get updated, Click ‘Cancel’ to confirm the PO document changes but not the Supplier tab in the LIM, Price Group or the Catalog.";
	
		Assert.assertEquals(actUpdateAlertText,expUpdateAlertText);
		Thread.sleep(2000);
		ActionsClass.switchtoDefaultContent();	
		BaseClass.details.clickonHyperlinkEditLine();
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		ActionsClass.switchToFrame("_dlgOpenerIframe1");
		String getUpdatedEditLineQty=BaseClass.editLine.getOrderQty();
		System.out.println(getUpdatedEditLineQty);
		Assert.assertEquals(changeEditLineQty,getUpdatedEditLineQty);
		ActionsClass.closeWindowIndex(1);

	}
	
	

}


//2024.01 - Create Element and Method of Page Edit Line - ntripathi - 10October2024
//2024.02---Added editQtyOnPrintViewAndEditLine method --aurehman--15/10/2024