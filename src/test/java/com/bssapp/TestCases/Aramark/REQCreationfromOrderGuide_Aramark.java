package com.bssapp.TestCases.Aramark;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_Favorites;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_Purchasing;
import com.bssapp.PageObjects.Page_REQ;
import com.bssapp.PageObjects.Page_REQHeaderUpdate;
import com.bssapp.PageObjects.Page_Requisitions;

import com.bssapp.TestBase.BaseClass;
import com.bssapp.TestBase.UserDefinedMethods;

public class REQCreationfromOrderGuide_Aramark extends BaseClass {

	String subID="641";
	String compID="931";
	String OG = "*AutomationOG1";
	String productName="1/2 TRAY CHEESECAKE BROWNIE";
	String expDeleteAlertText = "135- Do you want to delete this document from the database?";
	String expDeleteAlertText1 = "740- The REQ is marked as deleted.";
	String expectedCopyReqAlert="Click on Header Update to assign current information to new REQ.";
	HashMap<String,String> expReqDelAlerts=new HashMap<>();
	
	
	@Test
	public void REQCreationfromOrderGuide() throws IOException, InterruptedException {
		home=new Page_Home(driver);
		purchasing=new Page_Purchasing(driver);
		fav= new Page_Favorites(driver);
		req=new  Page_REQ(driver);
		requisitions=new Page_Requisitions(driver);
		reqHeader=new Page_REQHeaderUpdate(driver);

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
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		WebElement reqNumberElement= ActionsClass.customElementWithText(reqNumber);
		boolean isReqVisible= reqNumberElement.isDisplayed();
		logger.info("The Req is present in requisition tile as expected");
		Assert.assertEquals(isReqVisible, true);
		ActionsClass.rightClick(reqNumberElement);
		requisitions.clickOnBtnrightClickViews();
		requisitions.clickOnBtnrightClickPrintView();
		ActionsClass.switchToWindowIndex(1);
		ActionsClass.waitForPageLoad(40);
		Thread.sleep(3000);
		reqNumberElement= ActionsClass.customElementWithText(reqNumber);
		isReqVisible= reqNumberElement.isDisplayed();
		logger.info("The Req number is present in requisition Print view Page");
		Assert.assertEquals(isReqVisible, true);
		ActionsClass.closeWindowIndex(1);
		ActionsClass.switchToWindowIndex(0);
		requisitions.selectREQ(reqNumber);
		reqHeader.headerUpdate(reqNumber);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		logger.info("REQ header update is completed");
		ActionsClass.switchtoDefaultContent();
		String actualCopyReqAlert=requisitions.copyReq();
		boolean isCopyAlertPresent=actualCopyReqAlert.contains(expectedCopyReqAlert);
		Assert.assertTrue(isCopyAlertPresent);
		ActionsClass.switchToWindowIndex(0);
		requisitions.selectREQ(reqNumber);
		HashMap<String,String>reqDelAlerts=requisitions.deleteReqFromActions();
		expReqDelAlerts.put("ExpectedReqAlertDatabase",expDeleteAlertText);
		expReqDelAlerts.put("ExpectedReqDeleteAlert",expDeleteAlertText1);
		boolean areREQDeleteAlertsAsExpected=expReqDelAlerts.size() == reqDelAlerts.size() &&  expReqDelAlerts.values().stream().allMatch(value -> reqDelAlerts.containsValue(value));
		Assert.assertTrue(areREQDeleteAlertsAsExpected);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		boolean isREQVisisble=UserDefinedMethods.validateReqisDeleted(reqNumber);
		Assert.assertTrue(isREQVisisble);
		logger.info("REQ is Deleted");
		logger.info("*********************Test Case Ended**********************");

	}

}
//TCM-3046---aurehman 21/oct/2024