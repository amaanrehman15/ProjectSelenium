package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_AccountPayable extends BasePage {

	public Page_AccountPayable(WebDriver driver) {
		super(driver);
		
	}
	
	
	@FindBy(xpath="//h5[contains(text(),'AP Invoice')]")
	public WebElement tile_APInvoice;
	
	@FindBy(xpath="//h5[contains(text(),'Invoice Maintenance')]")
	public WebElement tile_InoviceMaintenance;
	
	@FindBy(xpath="//h5[contains(text(),'AP Credit Memo')]")
	WebElement tile_APCreditMemo;
	
	
	public void clickonAPInvoiceTile() {
		
		tile_APInvoice.click();
	}
	
	public void clickonInvoiceMaintenanceTile() {
		
		tile_InoviceMaintenance.click();
	}
	
	public void clickonAPCreditMemoTile() {
		
		tile_APCreditMemo.click();
	}
	
	
	}

//2024.01 - Create Element and Method of Page Account Payable - ntripathi - 10October2024