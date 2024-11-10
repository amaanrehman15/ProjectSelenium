package com.bssapp.TestCases.Aramark;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.bssapp.PageObjects.Page_BrowseCatalogs;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_ManageOrders;
import com.bssapp.PageObjects.Page_PO;
import com.bssapp.PageObjects.Page_Purchasing;
import com.bssapp.PageObjects.Page_Receiving;
import com.bssapp.PageObjects.Page_SupplierPO;
import com.bssapp.PageObjects.Page_SupplierPortal;

import com.bssapp.TestBase.BaseClass;
import com.bssapp.Utilities.ExcelUtility;
import com.bssapp.Utilities.Locations;


public class CreatePOFromCatalog_Aramark extends BaseClass {


	String SubscriberId="641";
	String CompanyId="931";
	String Subject = "Test through Katalon Automation";
	String OrderQuantity = "10";
	String Date = "t";
	String option;
	String PurchaseType = "Food";
	String Item ="1/2 TRAY CHEESECAKE BROWNIE";
	String[] optionsInPurchType =  {"Select Entry", "Food", "Beverage", "General"};
	String ActCatalogWindowTitle = "BirchStreet Catalog";
	String DeptPanelId ="14383";
	String DeptCode = "QAAUTO2020";
	String POStatusbeforeAccept = "New";
	String ExpecPOStatusAfterSubmit = "Submitted";
	String SupplierCompanyId ="568";
	String SelectPOPanel ="All PO's Needing Response";
	String expPOAcceptanceAlert = "489- PO is Accepted.";
	String POStatusAfterAccept="Accepted by supplier";
	String POStatusAfterReceive="Receiving Complete";
	String Approver="No Approver";




	@Test
	public void CreatePOFromCatalog() throws IOException, InterruptedException {

		home= new Page_Home(driver);
		PO = new Page_PO(driver);
		purchasing = new Page_Purchasing(driver);
		browseCatalogs = new Page_BrowseCatalogs(driver);
		supplierPortal= new Page_SupplierPortal(driver);
		supplierPO = new Page_SupplierPO(driver);
		orders = new Page_ManageOrders(driver);
		receive = new Page_Receiving(driver);
		String location = Locations.locations();
		ExcelUtility xlutil=new ExcelUtility(location);


		logIn(SubscriberId);

		home.clickonCloseMyTaskbtn();

		switchToCompany(CompanyId);

		ActionsClass.waitForPageLoad(20);
		ActionsClass.switchtoDefaultContent();
		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForPageLoad(30);
		ActionsClass.waitForElementClickable(purchasing.tile_BrowseCatalogs,10);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 30);
		purchasing.clickOnBrowseCatalogsTile();
		ActionsClass.switchToWindowIndex(1);
		driver.manage().window().maximize();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 50);
		String getCatalogTitle = driver.getTitle();
		Assert.assertEquals(getCatalogTitle, "BirchStreet Catalog");
		browseCatalogs.browseCatalogAndEnterQty(Item, OrderQuantity);

		//========Create PO=============

		ActionsClass.waitForElementClickable(browseCatalogs.btn_CreatePO, 10);
		browseCatalogs.clickonCreatePObtn();
		String getPONumber = PO.createPOFromCatalog(Subject, Date, optionsInPurchType, PurchaseType, Approver,DeptPanelId, DeptCode);
		System.out.println(getPONumber);
		String getPOStatus = orders.getPOStatus();
		System.out.println(getPOStatus);
		Assert.assertEquals(getPOStatus, POStatusbeforeAccept);

		// Input PO number in Excel

		xlutil.setCellData("GlobalVariables",1,"PO Number","");
		xlutil.setCellData("GlobalVariables", 1, "PO Number", getPONumber);

		System.out.println(xlutil.getCellData("GlobalVariables",1,0));

		//===========Submit PO============

		orders.selectPO(getPONumber);
		String getActPOStatusAfterSubmit= PO.submitPO();

		getActPOStatusAfterSubmit = orders.getPOStatus();
		System.out.println(getActPOStatusAfterSubmit);
		Assert.assertEquals(getActPOStatusAfterSubmit,ExpecPOStatusAfterSubmit);

		ActionsClass.closeWindowIndex(2);
		ActionsClass.closeWindowIndex(1);
		ActionsClass.switchToWindowIndex(0);


		//=====Navigate to Supplier Side============

		switchToCompany(SupplierCompanyId);
		System.out.println(SelectPOPanel);
		System.out.println(getPONumber);

		//Accept PO
		String getAcceptPOAlertText = supplierPO.acceptPOfromSupplierPO(SelectPOPanel, getPONumber);

		System.out.println(getAcceptPOAlertText);
		assertSoft.assertEquals(getAcceptPOAlertText,expPOAcceptanceAlert);
		ActionsClass.acceptAlert();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.switchToWindowIndex(0);

		//======== Navigate to Buyer Side============

		switchToCompany(CompanyId);
		ActionsClass.switchtoDefaultContent();
		ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing, 10);
		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForPageLoad(30);
		ActionsClass.waitForElementClickable(purchasing.tile_ManageOrders, 30);

		purchasing.clickOnManageOrdersTile();
		ActionsClass.waitForPageLoad(30);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);

		//Filter PO Number
		orders.clickOnIconPONumberFilter();
		orders.inputinFilter(getPONumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 30);

		//Select PO  Number
		orders.selectPO(getPONumber);

		//Get PO Status

		String getPOStatusAfterAcceptPO = orders.getPOStatus();
		System.out.println(getPOStatusAfterAcceptPO);
		Assert.assertEquals(getPOStatusAfterAcceptPO, POStatusAfterAccept);

		//================= Receiving==================

		String getActRecevAlert = receive.receivePO(Date);

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

		//Validate Status on Manage Orders Screen

		String getPOStatusAfterReceive = orders.validatePOStatus(getPONumber);
		System.out.println(getPOStatusAfterReceive);
		Assert.assertEquals(getPOStatusAfterReceive,POStatusAfterReceive);
		assertSoft.assertAll();

	}
}




//2024.01 - TCM_3032_CreatePOFromCatalog_Aramark - ntripathi - 15October2024
