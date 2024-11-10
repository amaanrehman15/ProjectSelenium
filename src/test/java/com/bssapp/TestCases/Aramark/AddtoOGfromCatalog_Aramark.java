package com.bssapp.TestCases.Aramark;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_BrowseCatalogs;
import com.bssapp.PageObjects.Page_Cart;
import com.bssapp.PageObjects.Page_Favorites;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_OrderGuide;
import com.bssapp.PageObjects.Page_PO;
import com.bssapp.PageObjects.Page_Purchasing;

import com.bssapp.TestBase.BaseClass;

public class AddtoOGfromCatalog_Aramark extends BaseClass{

	String subID="641";
	String compID="931";
	String ItemName ="1/2 TRAY CHEESECAKE BROWNIE";
	String qty="10";
	String OG = "*AutomationOG";
	String expedtedOGAlert="452- Selected items have been added to your Order Guide.";
	boolean flag;      
	String ExpextedItemRemoveMsg="379- Do you want to delete selected items?";
	WebElement productPath;
	@Test
	public void AddtoOGfromCatalog() throws IOException, InterruptedException {
	home=new Page_Home(driver);
	browseCatalogs=new Page_BrowseCatalogs(driver);
	cart=new Page_Cart(driver);
	PO=new Page_PO(driver);
	pageOg=new Page_OrderGuide(driver);
	purchasing=new Page_Purchasing(driver);
	fav= new Page_Favorites(driver);
	logger.info("*********************Test Case Started**********************");
	logIn(subID);
	logger.info("User is logIn successfully");
	ActionsClass.waitForElementClickable(home.btn_CloseMyTask, 10);
	home.clickonCloseMyTaskbtn();
	switchToCompany(compID);
	logger.info("switched To companyId 931");
	ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing,5);
	home.clickonMainMenuPurchasingIcon();
	ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
	ActionsClass.waitForElementClickable(purchasing.tile_Favourites,10);
	purchasing.clickOnFavouritesTile();
	ActionsClass.waitForPageLoad(40);
	ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
	String getFavTitle = driver.getTitle();
	Assert.assertEquals(getFavTitle, "Favorites - Temple Esposito Dining Center");
	fav.selectOrderGuideFromFavorites(OG);
	try{
		productPath=ActionsClass.customElementWithText(ItemName);
        flag=true;
		
		while(flag==true){
			
			productPath.click();
			fav.clickOnBtnMaintenence();
		    fav.clickOnBtnRemoveItem();
		    ActionsClass.ValidateAndAcceptAlert(15, ExpextedItemRemoveMsg);
		    ActionsClass.waitForPageLoad(10);
		    ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		    
			try{
				 productPath=ActionsClass.customElementWithText(ItemName);
			}
			catch(Exception e){

				flag=false;
			}
		}
		
	}
		catch(Exception e){
			flag=false;
			logger.info("No Product Is there by the NAME "+ ItemName);
		}
	Assert.assertFalse(flag);
	ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing,5);
	home.clickonMainMenuPurchasingIcon();
	ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
	ActionsClass.waitForElementClickable(purchasing.tile_BrowseCatalogs,10);
	purchasing.clickOnBrowseCatalogsTile();
	ActionsClass.switchToWindowIndex(1);
	logger.info("switched To Browse Catalog Window");
	ActionsClass.waitForPageLoad(40);
	ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
	String getCatalogTitle = driver.getTitle();
	Assert.assertEquals(getCatalogTitle, "BirchStreet Catalog");
	browseCatalogs.browseCatalogAndEnterQty(ItemName,qty);
	ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
	browseCatalogs.clickonAddToFavbtn();
	ActionsClass.waitForElementInvisible(home.icon_spinLoader, 5);
	logger.info("Clicked On add to fav button");
	ActionsClass.customElementWithText(OG).click();
	pageOg.clickOnSelectButton();
	ActionsClass.ValidateAndAcceptAlert(15, expedtedOGAlert);
	Thread.sleep(2000);
	ActionsClass.closeWindowIndex(1);
	ActionsClass.switchToWindowIndex(0);
	purchasing.clickOnFavouritesTile();
	ActionsClass.waitForPageLoad(40);
	ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
	fav.selectOrderGuideFromFavorites(OG);
	ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
	boolean isProductVisible=ActionsClass.customElementWithText(ItemName).isDisplayed();
	Assert.assertTrue(isProductVisible);
	 productPath=ActionsClass.customElementWithText(ItemName);
	 productPath.click();
	fav.clickOnBtnMaintenence();
    fav.clickOnBtnRemoveItem();
    ActionsClass.ValidateAndAcceptAlert(15, ExpextedItemRemoveMsg);
    ActionsClass.waitForPageLoad(10);
    ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
    
    try {
    isProductVisible=ActionsClass.customElementWithText(ItemName).isDisplayed();
    Assert.fail("Item is present in Favorite and has not been deleted!!!Please Validate It.");
    }
    catch(Exception e) {
    	
    	
    	logger.info("Item is not present in Favorite and Has been successfully deleted");
    	
    }
    logger.info("*********************Test Case Ended**********************");
	}
	}
//TCM-3065---AUREHMAN---23/10/2024