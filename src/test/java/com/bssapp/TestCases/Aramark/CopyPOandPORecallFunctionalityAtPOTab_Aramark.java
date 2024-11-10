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
import com.bssapp.PageObjects.Page_SupplierPO;
import com.bssapp.PageObjects.Page_SupplierPortal;
import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class CopyPOandPORecallFunctionalityAtPOTab_Aramark extends BaseClass {

	String subID="641";
	String compID="931";
	String ItemName ="1/2 TRAY CHEESECAKE BROWNIE";
	WebElement productPath;
	String OG = "*AutomationOG1";
	String poRecallAlert="1377- Do you want to recall the selected PO?";
	String Expected_PORecallMsg="PO is recalled and is now in the Recycle Bin.";
	String supplierPortalID = "568";
	String panelPO="All PO's";
	String ExpecHistoryWinTitle = "History";
	String ExpecPOStatusAfterSubmit = "Submitted";
	@Test
	public void CopyPOandPORecallFunctionalityAtPOTab() throws IOException, InterruptedException {
		home=new Page_Home(driver);
		cart=new Page_Cart(driver);
		PO=new Page_PO(driver);
		fav= new Page_Favorites(driver);
		purchasing=new Page_Purchasing(driver);
		orders=new Page_ManageOrders(driver);
		supplierPortal= new Page_SupplierPortal(driver);
		supplierPO = new Page_SupplierPO(driver);
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
		logger.info("Navigated To Manage Order Tile");
		orders.clickOnIconPONumberFilter();
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		orders.selectPO(poNumber);
		String getActPOStatusAfterSubmit= PO.submitPO();

		getActPOStatusAfterSubmit = orders.getPOStatus();
		System.out.println(getActPOStatusAfterSubmit);
		Assert.assertEquals(getActPOStatusAfterSubmit,ExpecPOStatusAfterSubmit);
		orders.selectPO(poNumber);
		orders.clickOnBtnActions();
		orders.clickOnBtnCopyPO();
		ActionsClass.waitForAlert(10);
		String POCopyMsg=ActionsClass.getAlertText();
		String copiedPONumber=POCopyMsg.substring(10, 18);
		System.out.println(copiedPONumber);
		ActionsClass.acceptAlert();
		logger.info("The Copied PO generated is :"+copiedPONumber);
		orders.clickOnRefreshIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		orders.clickOnIconPONumberFilter();
		ActionsClass.clearText(orders.input_Filter);
		orders.inputinFilter(copiedPONumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		String poStatusCopiedPO=orders.getPOStatus();
		System.out.println(poStatusCopiedPO);
		Assert.assertEquals(poStatusCopiedPO, "New");
		orders.clickOnRefreshIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		orders.clickOnIconPONumberFilter();
		ActionsClass.clearText(orders.input_Filter);
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		orders.selectPO(poNumber);
		orders.clickOnBtnActions();
		orders.clickOnBtnPORecall();
		ActionsClass.ValidateAndAcceptAlert(10, poRecallAlert);
		ActionsClass.ValidateAndAcceptAlert(5, Expected_PORecallMsg);
		orders.selectDropdownMultiPanels("My Recycle Bin PO's");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		orders.clickOnIconPONumberFilter();
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		WebElement POEle=ActionsClass.customElementWithText(poNumber);
		boolean isPOVisisble=POEle.isDisplayed();
		Assert.assertTrue(isPOVisisble);
		logger.info("PO is Recalled");
		switchToCompany(supplierPortalID);
		ActionsClass.waitForElementClickable(home.icon_SupplierPortalMainMenu,5);
		home.clickonSupplierPortalfromMainMenu();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		supplierPortal.clickOnSupplierPOTile();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		supplierPO.selectPOPanel(panelPO);
		Thread.sleep(2000);
		supplierPO.clickOnIconBuyerPOFilter();
		
		PO.inputinFilter(poNumber);
		PO.clickonApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		orders.selectPO(poNumber);
		supplierPO.clickOnBtnHistory();
		String titleHistory=supplierPO.getTitleHistory();
		Assert.assertEquals(titleHistory, "History");
		logger.info("**************************************Test Case Ended*********************************");
}
}