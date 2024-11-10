package com.bssapp.PageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class Page_BrowseCatalogs extends BasePage{

	public Page_BrowseCatalogs(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath="//input[@id='menusearchvalue']")
	public WebElement input_Searchmenu;

	@FindBy(xpath="//input[@class='qty']")
	public WebElement input_Qty;


	@FindBy(xpath="//button[@id='searchBtn']")
	WebElement btn_Search;

	@FindBy(xpath="//button[@id='AddFav']")
	WebElement btn_AddToFav;


	@FindBy(xpath="//button[@id='AddCart']")
	WebElement btn_AddToCart;

	@FindBy(xpath="//button[@id='CreatePO']")
	public WebElement btn_CreatePO;

	@FindBy(xpath="//button[@id='AddPo']")
	WebElement btn_AddToPO;

	@FindBy(xpath="//input[@class='block-content-search-txt']")
	WebElement input_SupplierSearch;

	@FindBy(xpath="//select[@id='menuselection']")
	WebElement dropdown_MenuSelection;

	@FindBy(xpath="//span[@class='ag-menu-option-text title']")
	public WebElement txt_ContextMenuTitle;
	
	@FindBy(xpath="//span[@data-menu-id='View Detail']")
	public WebElement  txt_ContextMenuViewDetail;

	public void inputValueinSearchMenu(String CatalogItem) {

		input_Searchmenu.sendKeys(CatalogItem);
	}
	
	public void clickonSearchMenu() {

		input_Searchmenu.click();
	}


	public void inputValueinOrderQty(String OrderQty) {

		input_Qty.sendKeys(OrderQty);
	}


	public void clickonSearchbtn() {

		btn_Search.click();
	}


	public void clickonAddToFavbtn() {

		btn_AddToFav.click();
	}


	public void clickonAddToCartbtn() {

		btn_AddToCart.click();
	}


	public void clickonCreatePObtn() {

		btn_CreatePO.click();
	}


	public void clickonAddToPObtn() {

		btn_AddToPO.click();
	}


	public void inputSupplierNameinSupplierSearch(String SupplierName) {

		input_SupplierSearch.sendKeys(SupplierName);
	}


	public void SelectMenu(String Menu) {

		Select select = new Select(dropdown_MenuSelection);
		select.selectByVisibleText(Menu);
	}
	
	public  void browseCatalogAndEnterQty(String item,String OrderQuantity) throws InterruptedException 
	{
	
		BaseClass.browseCatalogs=new Page_BrowseCatalogs(driver);
	
		BaseClass.home=new Page_Home(driver);
		BaseClass.browseCatalogs.clickonSearchMenu();
		BaseClass.browseCatalogs.inputValueinSearchMenu(item);
		BaseClass.browseCatalogs.clickonSearchbtn();
		ActionsClass.waitForPageLoad(30);
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 20);
		ActionsClass.switchToFrame("TopCatalog");
		BaseClass.browseCatalogs.inputValueinOrderQty(OrderQuantity);
	}

	public void addToCartFromBrowseCatalog(String itemName,String qty) throws IOException, InterruptedException {

		BaseClass.browseCatalogs=new Page_BrowseCatalogs(driver);
	
		BaseClass.home=new Page_Home(driver);
		BaseClass.purchasing=new Page_Purchasing(driver);
		ActionsClass.waitForElementClickable(BaseClass.home.icon_MainMenuPurchasing,5);
		BaseClass.home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		ActionsClass.waitForElementClickable(BaseClass.purchasing.tile_BrowseCatalogs,10);
		BaseClass.purchasing.clickOnBrowseCatalogsTile();
		ActionsClass.switchToWindowIndex(1);
		driver.manage().window().maximize();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		String getCatalogTitle = driver.getTitle();
		Assert.assertEquals(getCatalogTitle, "BirchStreet Catalog");
		BaseClass.browseCatalogs.browseCatalogAndEnterQty(itemName,qty);
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		BaseClass.browseCatalogs.clickonAddToCartbtn();
		ActionsClass.waitForAlert(10);
		String actAddAlert=ActionsClass.getAlertText();
		String expectedAddAlert="450- Selected items have been added to your Cart.";
		Assert.assertEquals(actAddAlert, expectedAddAlert);
		ActionsClass.acceptAlert();	
		ActionsClass.closeWindowIndex(1);
		ActionsClass.switchToWindowIndex(0);


	}

}



//2024.01 - Create Element and Method of Page Browse Catalog- ntripathi - 10October2024
