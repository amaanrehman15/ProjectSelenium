package com.bssapp.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Purchasing extends BasePage{

	public Page_Purchasing(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(xpath="//h5[contains(text(),'Browse Catalogs')]")
	public WebElement tile_BrowseCatalogs;
	
	@FindBy(xpath="//h5[contains(text(),'Cart')]")
	public
	WebElement tile_Cart;
	
	@FindBy(xpath="//h5[contains(text(),'Manage Orders')]")
	public WebElement tile_ManageOrders;
	
	
	@FindBy(xpath="//h5[contains(text(),'Favorites')]")
	public WebElement tile_Favourites;
	
	@FindBy(xpath="//h5[contains(text(),'Special Order Items')]")
	public WebElement tile_SOI;
	
	@FindBy(xpath="//h5[contains(text(),'Requisition')]")
	public WebElement tile_Requisition;
	
	
	public void clickOnBrowseCatalogsTile() {
		tile_BrowseCatalogs.click();
	}

	public void clickOnCartTile() {
		tile_Cart.click();
	}
	public void clickOnManageOrdersTile() {
		tile_ManageOrders.click();
	}
	public void clickOnFavouritesTile() {
		tile_Favourites.click();
	}
	public void clickOnSOITile() {
		tile_SOI.click();
	}
	public void clickOnRequisitionTile() {
		tile_Requisition.click();
	
	}
	
	
	
	
	
	
	
}
