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

import com.bssapp.TestBase.BaseClass;
@Test
public class AddToCartFromPOTab_Aramark extends BaseClass {
	String subID="641";
	String compID="931";
	String ItemName ="3/8 OZ CREAMERS";
	WebElement productPath;
	boolean flag;
	String OG = "*AutomationOG1";
	String ExpextedItemRemoveMsg="8326- Are you sure you want to delete";
	String ActualCartAddAlertAlert ="553- Items copied to cart successfully.";
	@Test
	public void AddToCartFromPOTab() throws IOException, InterruptedException {
		home=new Page_Home(driver);
		cart=new Page_Cart(driver);
		PO=new Page_PO(driver);
		fav= new Page_Favorites(driver);
		purchasing=new Page_Purchasing(driver);
		orders=new Page_ManageOrders(driver);
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
		fav.clickOnBtnCreatePO();
		HashMap<String,String> PODetails=PO.createPOAramark("No Approver");
		String poNumber=PODetails.get("PONumber");
		logger.info("The PO generated is :"+poNumber);
		String getPOAlertText=PODetails.get("PONumberAlert");
		if(!getPOAlertText.contains("Generated PO Number:")){
			Assert.fail("PO Generation Alert is not Matched,Please Validate it!!");
		}
		String POStatus=PODetails.get("POStatus");
		Assert.assertEquals(POStatus, "New");
		ActionsClass.switchToWindowIndex(0);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.moveToElement(home.icon_MainMenuPurchasing);
	    home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		ActionsClass.waitForElementClickable(purchasing.tile_ManageOrders,5);
		purchasing.clickOnManageOrdersTile();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		orders.clickOnIconPONumberFilter();
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		orders.selectPO(poNumber);
		orders.clickOnBtnActions();
		orders.clickOnBtnAddtoCart();
		ActionsClass.ValidateAndAcceptAlert(10, ActualCartAddAlertAlert);
		String getWindowTitle = driver.getTitle();
		Assert.assertEquals(getWindowTitle, "Manage Orders - Temple Esposito Dining Center");
		ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing,5);
		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.waitForElementClickable(purchasing.tile_Cart,5);
		purchasing.clickOnCartTile();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		Thread.sleep(2000);
		boolean isItemAdded=ActionsClass.customElementWithText(ItemName).isDisplayed();
		Assert.assertTrue(isItemAdded);
		logger.info("Item is Added to the cart");
		
		productPath=ActionsClass.customElementWithText(ItemName);
		productPath.click();
	    cart.clickOnBtnRemoveitem();;
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
//TCM-3082--aurehman 01/11/24
