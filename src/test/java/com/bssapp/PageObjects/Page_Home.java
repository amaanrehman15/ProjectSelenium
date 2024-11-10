package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Home extends BasePage{

	public Page_Home(WebDriver driver) {
		super(driver);

	}


	@FindBy(xpath="//button[@id='closeTaskPanel']")
	public WebElement btn_CloseMyTask;

	@FindBy(xpath="//img[@src='Search.gif']")
	public
	WebElement icon_changeCompany;

	@FindBy(xpath="//input[@id='radio2']")
	WebElement radioBtn_CompanyId;

	@FindBy(xpath="//input[@id='InputValue']")
	WebElement input_CompanyId;

	@FindBy(xpath="//button[@id='btnOK']")
	WebElement btn_OK;

	@FindBy(xpath="//button[@id='btnCancel']")
	WebElement btn_Cancel;

	@FindBy(xpath="//span[contains(text(),'Purchasing')]")
	public WebElement icon_MainMenuPurchasing;
	
	@FindBy(xpath="//h5[contains(text(),'Purchasing')]")
	public WebElement tile_Purchasing;

	@FindBy(xpath="//span[contains(text(),'Accounts Payable')]")
	WebElement tile_AccountsPayable;
	
	@FindBy(xpath="//span[contains(text(),'Supplier Portal')]")
	public WebElement icon_SupplierPortalMainMenu;
	
	
	@FindBy(xpath="//div[@class='loading-spinner-holder']")
	public WebElement icon_spinLoader;
	
	@FindBy(xpath="//button[contains(text(),'Anurag Kushwaha')]")
	public WebElement btn_Display;
	
	@FindBy(xpath="//a[@onclick='Inbox()']")
	public WebElement btn_Inbox;
	
	@FindBy(xpath="//a[@onclick='MySettings()']")
	public WebElement btn_MySetting;
	
	@FindBy(xpath="//a[@onclick='LogOut()']")
	public WebElement btn_LogOut;
	
	




	public void clickonCloseMyTaskbtn() {

		btn_CloseMyTask.click();
	}

	public void clickonChangeCompanyIcon() {

		icon_changeCompany.click();
	}

	public void clickonChangeCompanyIdRadiobtn() {

		radioBtn_CompanyId.click();
	}

	public void inputCompanyId(String CompanyId) {

		input_CompanyId.sendKeys(CompanyId);
	}

	public void clickonOKbtn() {
		btn_OK.click();

	}

	public void clickonCancelbtn() {

		btn_Cancel.click();
	}

	public void clickonMainMenuPurchasingIcon() {

		icon_MainMenuPurchasing.click();
	}
	
	public void clickonAccountPayableTile() {

		tile_AccountsPayable.click();
	}
	
	public void clickonSupplierPortalfromMainMenu() {
		
		icon_SupplierPortalMainMenu.click();
	}
	
	public void clickOnPurchasingTile() {
		tile_Purchasing.click();
	}
	
	public void clickOnDisplayBtn() {
		btn_Display.click();
	}

	public void clickOnInboxBtn() {
		btn_Inbox.click();
	}
	
	public void clickOnMySettingBtn() {
		btn_MySetting.click();
	}

	public void clickOnLogOut() {
		btn_LogOut.click();
		
	}
}


//2024.01 - Create Element and Method - ntripathi - 10October2024
//2024.02 - Create Element and Add Method - ntripathi - 6thNovember2024