package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Page_SupplierPortal extends BasePage {

	public Page_SupplierPortal(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath="//h5[contains(text(),'Supplier PO')]")
	public WebElement tile_SupplierPO;

	@FindBy(xpath="//h5[contains(text(),'Supplier Invoice')]")
	public WebElement tile_SupplierInvoice;



	public void clickOnSupplierPOTile() {

		tile_SupplierPO.click();
	}

	public void clickOnSupplierInvoice() {

		tile_SupplierInvoice.click();
	}


}



//2024.01 - Create Element and Method of Page Header Supplier Portal- ntripathi - 10October2024