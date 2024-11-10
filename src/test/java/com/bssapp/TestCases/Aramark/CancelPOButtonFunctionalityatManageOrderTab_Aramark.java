package com.bssapp.TestCases.Aramark;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_Favorites;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_ManageOrders;
import com.bssapp.PageObjects.Page_PO;
import com.bssapp.PageObjects.Page_Purchasing;
import com.bssapp.PageObjects.Page_REQHeaderUpdate;

import com.bssapp.TestBase.BaseClass;

public class CancelPOButtonFunctionalityatManageOrderTab_Aramark extends BaseClass{
	String subID="641";
	String compID="931";
	String OG = "*AutomationOG1";
	String productName="3/8 OZ CREAMERS";
	String Expected_POCancelAlert="414- Do you want to cancel the PO?";
	String expectedProcessingAlert="204- Processing is done.";
	String ExpectedCancelledPOStatus="Buyer cancelled";
	String ExpecPOStatusAfterSubmit="Fax/email/csv accepted";
	@Test
	public void cancelPOButtonFunctionalityatManageOrderTab() throws InterruptedException, IOException {
		
		home=new Page_Home(driver);
		purchasing=new Page_Purchasing(driver);
		fav= new Page_Favorites(driver);
		PO=new Page_PO(driver);
		orders=new Page_ManageOrders(driver);
		

		logger.info("*********************Test Case Started**********************");
		logIn(subID);
		logger.info("User is logIn successfully");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.waitForElementClickable(home.btn_CloseMyTask, 10);
		home.clickonCloseMyTaskbtn();
		switchToCompany(compID);
		Thread.sleep(1000);
		logger.info("switched To companyId 931");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing,10);
		home.clickonMainMenuPurchasingIcon();
		logger.info("Navigated To Purchasing tile");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		ActionsClass.waitForElementClickable(purchasing.tile_Favourites, 15);
		purchasing.clickOnFavouritesTile();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		logger.info("Navigated To Favorites tile");
		fav.selectOrderGuideFromFavorites(OG);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		fav.selectProductOnTileFav(productName);
		logger.info(productName+" is selected");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
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
		Thread.sleep(1000);
		orders.selectPO(poNumber);
		String getActPOStatusAfterSubmit= PO.submitPO();
		getActPOStatusAfterSubmit = orders.getPOStatus();
		System.out.println(getActPOStatusAfterSubmit);
		Assert.assertEquals(getActPOStatusAfterSubmit,ExpecPOStatusAfterSubmit);
		orders.selectPO(poNumber);
		orders.clickOnBtnActions();
		orders.clickOnBtnCanelPO();
		ActionsClass.ValidateAndAcceptAlert(10,Expected_POCancelAlert);
		ActionsClass.ValidateAndAcceptAlert(10,expectedProcessingAlert);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		orders.clickOnIconPONumberFilter();
		ActionsClass.clearText(orders.input_Filter);
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		orders.selectPO(poNumber);
		String ActualCancelledPOStatus=orders.getPOStatus();
		System.out.println(ActualCancelledPOStatus);
		Assert.assertEquals(ActualCancelledPOStatus, ExpectedCancelledPOStatus);
	}
	
	
	
	
}
//TCM--3062--aurehman--21/10/2024