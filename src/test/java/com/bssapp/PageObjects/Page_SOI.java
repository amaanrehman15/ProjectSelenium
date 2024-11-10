package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bssapp.TestBase.ActionsClass;

public class Page_SOI extends BasePage{

	public Page_SOI(WebDriver driver) {
		super(driver);
	}



	@FindBy(xpath="//button[@id='SelectSupp']")
	WebElement btn_SelectSupplier;



	@FindBy(xpath="//input[@id='catsearchtext']")
	WebElement input_Supplier;


	@FindBy(xpath="//button[@id='Search']")
	WebElement btn_GOSupplierSearch;

	@FindBy(xpath="//button[@id='RetSupp']")
	WebElement btn_linkSelectSupplier;

	@FindBy(xpath="//button[@id='CreatePO']")
	WebElement btn_createPO;
	
	@FindBy(xpath="//div[@row-index='0']//div[@col-id='SUPPLIER_SKU']/..//input[@type='text']")
	public WebElement input_Item;

	@FindBy(xpath="//div[@row-index='0']//div[@col-id='SUPPLIER_SKU']")
	public WebElement txt_Item;
	
	@FindBy(xpath="//div[@row-index='0']//div[@col-id='PRODUCTNAME']/div/div/div/input")
	WebElement input_ProductName;

	@FindBy(xpath="//div[@row-index='0']//div[@col-id='ORDER_QTY']/..//input[@type='text']")
	WebElement input_OrderQty;

	@FindBy(xpath="//input[@id='OUOM0']")
	WebElement input_UOM;
	@FindBy(xpath="//div[@row-index='0']//div[@col-id='PACK_SIZE']/..//input[@type='text']")
	WebElement input_PackSize;

	@FindBy(xpath="//div[@row-index='0']//div[@col-id='PRICE']/..//input[@type='text']")
	WebElement input_Price;

	@FindBy(xpath="//input[@id='CatID0']")
	WebElement input_catId;

	
	@FindBy(xpath="//button[@id='CatID0ZM']")
	WebElement zoom_catId;
	
	@FindBy(xpath="//span[contains(text(),'Beverage')]")
	WebElement select_CatBeverage;
	
	@FindBy(xpath="//button[(text()='Select')]")
	WebElement btn_SelectCat;

	
	public void selectCategory(String cat) {

		zoom_catId.click();
		ActionsClass.customElementWithText(cat).click();
		btn_SelectCat.click();

	}
	
	
	public void inputItemNo(String itemNo) {

		input_Item.sendKeys(itemNo);

	}


	public void inputProductName(String productName) {

		input_ProductName.sendKeys(productName);

	}

	public void inputOrderQty(String orderQty) {

		input_OrderQty.sendKeys(orderQty);

	}
	public void inputOrderUOM(String orderUOM) {

		input_UOM.sendKeys(orderUOM);

	}

	public void inputPackSize(String packSize) {

		input_PackSize.sendKeys(packSize);

	}
	public void inputPrice(String packSize) {

		input_Price.sendKeys(packSize);

	}

	public void inputCatId(String cat) {

		input_catId.sendKeys(cat);

	}
	public void clickOnBtnSelectSupplier() {

		btn_SelectSupplier.click();

	}

	public void inputSupplier(String supplier) {

		input_Supplier.sendKeys(supplier);

	}


	public void clickOnBtnGoSupllierSearch(String supplier) {

		btn_GOSupplierSearch.click();

	}
	public void clickOnBtnLinkSelectSupplier() {

		btn_linkSelectSupplier.click();

	}
	public void clickOnBtnCreatePO() {

		btn_createPO.click();

	}
}
//TCM-3081--Created this page added locators and methods--aurehman