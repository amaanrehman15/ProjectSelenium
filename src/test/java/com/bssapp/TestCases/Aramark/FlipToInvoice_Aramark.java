package com.bssapp.TestCases.Aramark;

import java.io.IOException;
import java.lang.reflect.Array;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_InvoiceMaintenance;
import com.bssapp.PageObjects.Page_ManageOrders;
import com.bssapp.PageObjects.Page_PO;
import com.bssapp.PageObjects.Page_SupplierPO;
import com.bssapp.PageObjects.Page_SupplierPortal;

import com.bssapp.TestBase.BaseClass;

import com.bssapp.Utilities.ExcelUtility;
import com.bssapp.Utilities.Locations;

public class FlipToInvoice_Aramark extends BaseClass {

	String SubscriberId="641";
	String BuyerCompanyId= "931";
	String SupplierCompanyId="568";
	String SelectPOPanel ="All PO's";
	String ExpecSupplierInvoiceStatus="30";
	String ExpecSubject = "Test through Katalon Automation";
	String InvoiceQuantity = "10";
	String InvoicePrice = "20";
	String expInvoiceAlert = "Processing is done.";
	String NotMatchedInvoicePanel = "Not Matched";
	String ExpectedDeleteReqMsg ="135- Do you want to delete this document from the database?";
	String RecycleBinInvoicePanel = "Recycle bin";

	@Test
	public void FlipToInvoice() throws IOException, InterruptedException
	{

		home= new Page_Home(driver);
		supplierPortal= new Page_SupplierPortal(driver);
		supplierPO = new Page_SupplierPO(driver);
		PO = new Page_PO(driver);
		orders=new Page_ManageOrders(driver);
		invoiceMaintenance = new Page_InvoiceMaintenance(driver);


		String location = Locations.locations();

		ExcelUtility xlutil=new ExcelUtility(location);

		//Fetch Data from Excel

		String getPONumberfromExcel = xlutil.getCellData("GlobalVariables",1,0);
		System.out.println(getPONumberfromExcel);

		logIn(SubscriberId);

		home.clickonCloseMyTaskbtn();

		switchToCompany(SupplierCompanyId);

		ActionsClass.switchtoDefaultContent();
		ActionsClass.waitForElementClickable(home.icon_SupplierPortalMainMenu, 20);
		home.clickonSupplierPortalfromMainMenu();
		Thread.sleep(1000);
		supplierPortal.clickOnSupplierPOTile();
		Thread.sleep(1000);
		supplierPO.selectPOPanel(SelectPOPanel);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		supplierPO.clickOnIconBuyerPOFilter();
		PO.inputinFilter(getPONumberfromExcel);
		PO.clickonApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		orders.selectPO(getPONumberfromExcel);


		//Create Flip to Invoice from Supplier PO

		boolean isbntMoreVisible = 	ActionsClass.iselementDisplayed(supplierPO.btn_More);
		if(isbntMoreVisible) {
			supplierPO.clickOnMorebtn();
			supplierPO.clickOnMoreFliptoInvoicebtn();
		}
		else{

			supplierPO.clickOnFliptoInvoicebtn();
		}

		String[] value = supplierPO.createFliptoInvoice(InvoiceQuantity,InvoicePrice);

		//get Value from createFliptoInvoice method

		String SupplierInvoiceNumber =(String)Array.get(value, 0);
		System.out.println(SupplierInvoiceNumber);
		String getSubject = (String)Array.get(value, 1); ;
		System.out.println(getSubject);
		String getActAlertAfterSaveSupplierInvoice=(String)Array.get(value, 2);
		System.out.println(getActAlertAfterSaveSupplierInvoice);
		String getActAlertAfterSubmitSupplierInvoice = (String)Array.get(value, 3);
		System.out.println(getActAlertAfterSubmitSupplierInvoice);
		String getSupplierInvoiceStatus=(String)Array.get(value, 4);

		System.out.println(getSupplierInvoiceStatus);


		//Validate Subject which drive from PO Subject

		assertSoft.assertEquals(getSubject, ExpecSubject);

		//Validate Alert Text

		assertSoft.assertEquals(getActAlertAfterSaveSupplierInvoice, expInvoiceAlert);
		assertSoft.assertEquals(getActAlertAfterSubmitSupplierInvoice, expInvoiceAlert);

		//Validate Status PO Supplier Invoice
		//30 - Transmitted to buyer 
		Assert.assertEquals(getSupplierInvoiceStatus,ExpecSupplierInvoiceStatus);

		ActionsClass.closeWindowIndex(1);
		ActionsClass.switchToWindowIndex(0);

		//Navigate to Buyer Side

		switchToCompany(BuyerCompanyId);

		ActionsClass.switchtoDefaultContent();
		home.clickonAccountPayableTile();

		ActionsClass.waitForElementClickable(accountPayable.tile_InoviceMaintenance, 10);
		accountPayable.clickonInvoiceMaintenanceTile();
		ActionsClass.waitForElementClickable(home.icon_SupplierPortalMainMenu, 10);
		invoiceMaintenance.selectInvoicePanel(NotMatchedInvoicePanel);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		invoiceMaintenance.clickOnFilterSupplierInvoiceIcon();
		invoiceMaintenance.inputinFilter(SupplierInvoiceNumber);
		ActionsClass.sendKeys(invoiceMaintenance.input_Filter,Keys.ENTER);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);

		WebElement getInvoiceNumberelement = ActionsClass.customElementWithText(SupplierInvoiceNumber);
		System.out.println(getInvoiceNumberelement);

		boolean isSupplierInvoiceNumberVisible = ActionsClass.iselementDisplayed(getInvoiceNumberelement);
		if(!isSupplierInvoiceNumberVisible) {

			Assert.fail("Supplier Invoice Number is not Visible in Not Matched Panel, Please Validate it!!");

		}
		invoiceMaintenance.selectInvoice(SupplierInvoiceNumber);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);

		//Delete Invoice


		ActionsClass.rightClick(getInvoiceNumberelement);

		String getDeleteInvoiceAlertText= UserDefinedMethods.DeleteInvoiceRightClick(RecycleBinInvoicePanel, SupplierInvoiceNumber);

		Assert.assertEquals(getDeleteInvoiceAlertText,ExpectedDeleteReqMsg);

		WebElement getInvoiceNumberelementonRecyclebin = ActionsClass.customElementWithText(SupplierInvoiceNumber);
		System.out.println(getInvoiceNumberelementonRecyclebin);

		boolean isSupplierInvNumberVisibleinRecycleBin = ActionsClass.iselementDisplayed(getInvoiceNumberelementonRecyclebin);
		if(!isSupplierInvNumberVisibleinRecycleBin)
		{
			Assert.fail("Supplier Invoice Number is not Visible in Recycle Bin, Please Validate it!!");
		}


	}

}


//2024.01 - TCM-3043_FlipToInvoice - ntripathi - 17October2024