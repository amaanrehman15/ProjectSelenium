package com.bssapp.TestCases.Aramark;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_BrowseCatalogs;
import com.bssapp.PageObjects.Page_Cart;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_ManageOrders;
import com.bssapp.PageObjects.Page_PO;
import com.bssapp.PageObjects.Page_POEditLine;
import com.bssapp.PageObjects.Page_POHeaderUpdate;
import com.bssapp.PageObjects.Page_POPrintwDetails;
import com.bssapp.PageObjects.Page_Purchasing;

import com.bssapp.TestBase.BaseClass;
import com.bssapp.TestBase.DataBaseQueries;


public class CreatePOFromCartAndOtherPOOperation_Aramark extends BaseClass {
	String subID="641";
	String compID="931";
	String ItemName ="1/2 TRAY CHEESECAKE BROWNIE";
	String qty="10";
	String expDeleteAlertText = "135- Do you want to delete this document from the database?";
	String expDeleteAlertText1 = "618- The PO is marked as deleted.";
	HashMap<String,String> expPODelAlerts=new HashMap<>();
	@Test
	public void CreatePOFromCartAndOtherPOOperation() throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		home=new Page_Home(driver);
		browseCatalogs=new Page_BrowseCatalogs(driver);
		cart=new Page_Cart(driver);
		PO=new Page_PO(driver);
		purchasing=new Page_Purchasing(driver);
		orders=new Page_ManageOrders(driver);
		header=new Page_POHeaderUpdate(driver);
		editLine=new Page_POEditLine(driver);
		details =new Page_POPrintwDetails(driver);
		DataBaseQueries DataBaseQueries=new DataBaseQueries();
		logger.info("*********************Test Case Started**********************");
		DataBaseQueries.updateFlagsAdminCompanyProfile("amer6", "TRANSMISSION_METHOD", "641", "781", "5");
		logIn(subID);
		logger.info("User is logIn successfully");
		ActionsClass.waitForElementClickable(home.btn_CloseMyTask, 10);
		home.clickonCloseMyTaskbtn();
		switchToCompany(compID);
		logger.info("switched To companyId 931");
	     browseCatalogs.addToCartFromBrowseCatalog(ItemName, qty);
		logger.info("Item is added to the Cart from Browse Catalog");
		purchasing.clickOnCartTile();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		cart.selectItemWithTextCart(ItemName);
		String item_num=cart.getitemNum();
		System.out.println(item_num);
		String productName=cart.getPrductName();
		System.out.println(productName);
		String UOM=cart.getUOM();
		System.out.println(UOM);
		String ORDER_Qty=cart.getOrderQty();
		System.out.println(ORDER_Qty);
		String unit_Price=cart.getunitPrice();
		System.out.println(unit_Price);
		 cart.clickOnCreatePOBtn();
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
	    home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		purchasing.clickOnManageOrdersTile();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		orders.clickOnIconPONumberFilter();
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		orders.selectPO(poNumber);
		header.headerUpdate(poNumber);
		logger.info("PO Header Update is Done");
		editLine.editQtyOnPrintViewEditLine("15.00");
		logger.info("PO Qty is Updated through Edit Line");
		ActionsClass.switchToWindowIndex(0);
		orders.selectPO(poNumber);
		HashMap<String,String>PODelAlerts=	orders.deletePOFromActions();
		expPODelAlerts.put("ExpectedPOAlertDatabase",expDeleteAlertText);
		expPODelAlerts.put("ExpectedPODeleteAlert",expDeleteAlertText1);
		boolean arePODeleteAlertsAsExpected=expPODelAlerts.size() == PODelAlerts.size() &&  expPODelAlerts.values().stream().allMatch(value -> PODelAlerts.containsValue(value));
		Assert.assertTrue(arePODeleteAlertsAsExpected);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		boolean isPOVisisble=UserDefinedMethods.ValidatePOisDeleted(poNumber);
		Assert.assertTrue(isPOVisisble);
		logger.info("PO is Deleted");
		logger.info("*********************Test Case Ended**********************");
	}


	
	
	
}
//TCM-3035--aurehman---16/10/2024