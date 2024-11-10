package com.bssapp.TestCases.Aramark;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_Cart;
import com.bssapp.PageObjects.Page_Favorites;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_ManageOrders;
import com.bssapp.PageObjects.Page_PO;
import com.bssapp.PageObjects.Page_Purchasing;
import com.bssapp.PageObjects.Page_REQ;
import com.bssapp.PageObjects.Page_Requisitions;

import com.bssapp.TestBase.BaseClass;

public class AddToCartfromREQTab_Aramark extends BaseClass{
	String subID="641";
	String compID="931";
	String ItemName ="1/2 TRAY CHEESECAKE BROWNIE";
	WebElement productPath;
	boolean flag;
	String OG = "*AutomationOG1";
	String ExpextedItemRemoveMsg="8326- Are you sure you want to delete";
	String ActualCartAddAlertAlert ="553- Items copied to cart successfully.";
	@Test
	public void AddToCartFromREQtab() throws IOException, InterruptedException {
		home=new Page_Home(driver);
		purchasing=new Page_Purchasing(driver);
		fav= new Page_Favorites(driver);
		req=new  Page_REQ(driver);
		cart=new Page_Cart(driver);
		requisitions=new Page_Requisitions(driver);
		
		logger.info("*********************Test Case Started**********************");
		
		logIn(subID);
		logger.info("User is logIn successfully");
		ActionsClass.waitForElementClickable(home.btn_CloseMyTask, 10);
		home.clickonCloseMyTaskbtn();
		switchToCompany(compID);
		logger.info("switched To companyId 931");
		ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing,5);
		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.waitForElementClickable(purchasing.tile_Cart,10);
		purchasing.clickOnCartTile();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		String getCartTitle = driver.getTitle();
		Assert.assertEquals(getCartTitle, "Cart - Temple Esposito Dining Center");
		
		try{
			productPath=ActionsClass.customElementWithText(ItemName);
			flag=true;
			
			while(flag==true){
				
				productPath.click();
				
			    cart.clickOnBtnRemoveitem();;
			    ActionsClass.ValidateAndAcceptAlert(10.0, ExpextedItemRemoveMsg);
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
		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		ActionsClass.waitForElementClickable(purchasing.tile_Favourites,10);
		purchasing.clickOnFavouritesTile();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		String getFavTitle = driver.getTitle();
		Assert.assertEquals(getFavTitle, "Favorites - Temple Esposito Dining Center");
		fav.selectOrderGuideFromFavorites(OG);
		
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		fav.selectProductOnTileFav(ItemName);
		logger.info(ItemName+" is selected");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		HashMap<String,String> reqDetails=req.createREQfromFavourites("No Approver");
		String reqNumber=reqDetails.get("ReqNumber");
		logger.info("The Req generated is :"+reqNumber);
		String getReqAlertText=reqDetails.get("ReqNumberAlert");
		if(!getReqAlertText.contains("Generated REQ Number:")){
			Assert.fail("REQ Generation Alert is not Matched,Please Validate it!!");
		}
		String reqStatus=reqDetails.get("ReqStatus");
		Assert.assertEquals(reqStatus, "New");
		ActionsClass.switchToWindowIndex(0);
		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.waitForElementClickable(purchasing.tile_Requisition, 10);
	    purchasing.clickOnRequisitionTile();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 12);
		WebElement reqNumberElement= ActionsClass.customElementWithText(reqNumber);
		boolean isReqVisible= reqNumberElement.isDisplayed();
		Assert.assertTrue(isReqVisible);
		logger.info("The Req is present in requisition tile as expected");
		requisitions.selectREQ(reqNumber);
		requisitions.clickOnBtnActions();
		requisitions.clickOnBtnAddToCart();
		ActionsClass.ValidateAndAcceptAlert(10, ActualCartAddAlertAlert);
		String getWindowTitle = driver.getTitle();
		Assert.assertEquals(getWindowTitle, "Requisition - Temple Esposito Dining Center");
		ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing,5);
		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.waitForElementClickable(purchasing.tile_Cart,5);
		purchasing.clickOnCartTile();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		boolean isItemAdded=ActionsClass.customElementWithText(ItemName).isDisplayed();
		Assert.assertTrue(isItemAdded);
		logger.info("Item is Added to the cart");
		
		productPath=ActionsClass.customElementWithText(ItemName);
		productPath.click();
	    cart.clickOnBtnRemoveitem();
	    ActionsClass.ValidateAndAcceptAlert(10.0, ExpextedItemRemoveMsg);
	    ActionsClass.waitForPageLoad(10);
	    ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		
	    try {
	     boolean isProductVisible=ActionsClass.customElementWithText(ItemName).isDisplayed();
	        Assert.fail("Item is present in Favorite and has not been deleted!!!Please Validate It.");
	        }
	        catch(Exception e) {
	        	
	        	
	        	logger.info("Item is not present in Favorite and Has been successfully deleted");
	        	
	        }
	        logger.info("*********************Test Case Ended**********************");
		
		
	}
	
	
	
	
	
}
//TCM-3084--aurehman -04/11/2024
