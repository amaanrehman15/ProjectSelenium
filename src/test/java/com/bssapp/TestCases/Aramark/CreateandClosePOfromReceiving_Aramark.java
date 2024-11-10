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
import com.bssapp.PageObjects.Page_Receiving;

import com.bssapp.TestBase.BaseClass;

public class CreateandClosePOfromReceiving_Aramark extends BaseClass {

	String subID="641";
	String compID="931";
	String OG = "*AutomationOG1";
	String productName="3/8 OZ CREAMERS";
	String ExpecPOStatusAfterSubmit="Fax/email/csv accepted";
	String Date = "t";
	String expecProcessingAlert = "Processing is done.";
	String expectedPOCloseStatus="PO Closed";
	@Test
	public void CreateandClosePOfromReceiving() throws IOException, InterruptedException {
		home=new Page_Home(driver);
		purchasing=new Page_Purchasing(driver);
		fav= new Page_Favorites(driver);
		orders = new Page_ManageOrders(driver);
		receive = new Page_Receiving(driver);
		PO = new Page_PO(driver);
		logger.info("*********************Test Case Started**********************");
		logIn(subID);
		logger.info("User is logIn successfully");
		ActionsClass.waitForElementClickable(home.btn_CloseMyTask, 10);
		home.clickonCloseMyTaskbtn();
		switchToCompany(compID);
		logger.info("switched To companyId 931");
		ActionsClass.switchToWindowIndex(0);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing,10);
		home.clickonMainMenuPurchasingIcon();
		logger.info("Navigated To Purchasing tile");
		ActionsClass.waitForElementVisible(purchasing.tile_Favourites,10);
		ActionsClass.waitForElementClickable(purchasing.tile_Favourites, 10);
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
		orders.clickOnBtnReceive();
		ActionsClass.switchToWindowIndex(1);
		ActionsClass.waitForPageLoad(30);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 30);
		receive.inputinReceiveDate(Date);
		receive.clickOnCheckboxClosePO();
		receive.clickOnBtnSave();
		ActionsClass.ValidateAndAcceptAlert(5, expecProcessingAlert);
		Thread.sleep(2000);
		receive.clickOnIconRefresh();
		receive.clickOnLineItemTab();
		receive.clickOnReceiveAcceptAllbtn();
		ActionsClass.waitForElementClickable(receive.btn_Receive, 5);
		receive.clickOnReceivebtn();
		ActionsClass.waitForAlert(25);
		String getActRecevAlert = ActionsClass.getAlertText();
		//Validate Receiving Alert

		if(!getActRecevAlert.contains("Receiving Number :"))
		{
			Assert.fail("Receiving Number is not generated,Please Validate it!!");
		}
		ActionsClass.acceptAlert();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		receive.clickOnCancelbtn();
		ActionsClass.closeWindowIndex(1);
		ActionsClass.switchToWindowIndex(0);
		orders.clickOnRefreshIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		orders.clickOnIconPONumberFilter();
		ActionsClass.clearText(orders.input_Filter);
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		orders.selectPO(poNumber);
		String ActualCancelledPOStatus=orders.getPOStatus();
		System.out.println(ActualCancelledPOStatus);
		Assert.assertEquals(ActualCancelledPOStatus, expectedPOCloseStatus);
		logger.info("PO is Closed");
		logger.info("*********************Test Case Ended**********************");

	}







}
//TCM-3075--Aurehman 29/10/2024