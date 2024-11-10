package com.bssapp.TestCases.Aramark;


import java.io.IOException;
import java.lang.reflect.Array;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bssapp.PageObjects.Page_APInvoice;
import com.bssapp.PageObjects.Page_AccountPayable;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_InvoiceMaintenance;

import com.bssapp.TestBase.BaseClass;
import com.bssapp.Utilities.ExcelUtility;
import com.bssapp.Utilities.Locations;

public class NonPOInvoiceCreationAtBuyerSide_Aramark extends BaseClass {

	String SubscriberId="641";
	String BuyerCompanyId= "931";
	String SupplierCompanyId = "781";
	String ExApInvoiceNumber = "[New]";
	String CaptionStatus="Status";
	String ExpecInvoiceStatus="New";
	String ExpecTransmissionStatus = "Select Entry";
	String CaptionTransmissionStatus = "Transmission status";
	String ExpecPOStatus = "Select Entry";
	String CaptionPOStatus = "PO Status";
	String ExpecPaymentMethodCode = "Select Entry";
	String CaptionPaymentMethodCode ="Payment method code";
	String ExpecReceiptOverriddenStatus = "Select Entry";
	String CaptionReceiptOverriddenStatus ="Receipt overridden status";
	String ExpecProjectInvoice = "Select Entry";
	String CaptionProjectsInvoice = "Projects invoice";
	String ExpecCancelPayment = "Select Entry";
	String CaptionCancelPayment ="Cancel payment";
	String CaptionHomeCurrency = "Home currency";
	String ExpecHomeCurrency = "USD";
	String ExpecHomeCurrencyDesc ="US Dollar";
	String CaptionNameSupplierCompanyId = "Supplier company ID";
	String Attribute = "value";
	String CaptionVendorCode = "Vendor code";
	String CaptionERPVendorSiteCode = "ERP vendor site code";
	String CaptionRemittoAddress ="Remit to address";
	String CaptionRemitToCity = "Remit to city";
	String CaptionTerms ="Terms";
	String ExpInvTerms = "10072";
	String ExInvTermsDesc = "NET 30";
	String Date="t";
	String InvoiceSubTotal = "1000";
	String TaxAmount = "12";
	String CaptionInvoiceGrandTotal = "Invoice grand total";
	String ExpItemPanelId = "11861";
	String CaptionLineType="Line type";
	String ExpLineType="Free form"; 
	String CaptionLineStatus = "Line status";
	String ExpLineStatus="New";
	String CaptionVariancePrice = "Variance price";
	String ExpVariancePrice = "$0.00";
	String Item ="0122699";
	String DeptCode = "TCOS";
	String GLAccount = "2010.320000";
	String CaptionItemDescription = "Item description";
	String CaptionCategoryID="Category ID";
	String CaptionDepartment = "Department";
	String CaptionGLAccount = "GL Account";
	String CaptionInvoiceUOM = "Invoice UOM";
	String CaptionRecalculateUseTaxRate = "Recalculate use tax rate";
	String CaptionOrderUOM = "Order UOM";
	String InvoiceQty="100";
	String InvoicePrice ="10";
	String AlertText = "Processing is done.";
	String InvoicePanel = "All";
	String ItemName = "TCOS (Total Cost of Sales)";

	@Test
	public void NonPOInvoiceCreationAtBuyerSide() throws IOException, InterruptedException {


		home= new Page_Home(driver);
		invoiceMaintenance = new Page_InvoiceMaintenance(driver);
		accountPayable= new Page_AccountPayable(driver);
		apInvoice = new Page_APInvoice(driver);

		String location = Locations.locations();
		ExcelUtility xlutil=new ExcelUtility(location);

		logger.info("******************Test Case Started*******************");
		logIn(SubscriberId);
		logger.info("LogIn into Application successfully");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);

		home.clickonCloseMyTaskbtn();
		logger.info("Close My Task");
		switchToCompany(BuyerCompanyId);
		logger.info("switched To Buyer CompanyId");
		ActionsClass.switchtoDefaultContent();
		logger.info("Switch to Default Content");
		home.clickonAccountPayableTile();
		logger.info("Click on Accounts Payable Tile");
		ActionsClass.waitForElementClickable(accountPayable.tile_InoviceMaintenance, 10);
		logger.info("Wait for Inovice Maintenance Tile Clickable");
		accountPayable.clickonInvoiceMaintenanceTile();
		logger.info("Click on Invoice Maintenance Tile");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		invoiceMaintenance.clickonNewbtn();
		logger.info("Click on New Button");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 30);

		String ActAPInvoiceNumberonLanding = invoiceMaintenance.getAPInvoiceNumber();
		logger.info("Get Default Invoice Number");
		System.out.println(ActAPInvoiceNumberonLanding);
		assertSoft.assertEquals(ActAPInvoiceNumberonLanding, ExApInvoiceNumber);
		String ActInvoiceStatusonLanding = invoiceMaintenance.getStatus(CaptionStatus);
		logger.info("Get Default Invoice Status");
		System.out.println(ActInvoiceStatusonLanding);
		assertSoft.assertEquals(ActInvoiceStatusonLanding, ExpecInvoiceStatus);

		String ActTransmissionStatusonLanding = invoiceMaintenance.getStatus(CaptionTransmissionStatus);
		logger.info("Get Default Transmission Status");
		System.out.println(ActTransmissionStatusonLanding);
		assertSoft.assertEquals(ActTransmissionStatusonLanding, ExpecTransmissionStatus);

		String ActPOStatusonLanding = ActionsClass.getdefaultValuefromdropdown(CaptionPOStatus);
		logger.info("Get Default PO Status");
		System.out.println(ActPOStatusonLanding);
		assertSoft.assertEquals(ActPOStatusonLanding, ExpecPOStatus);


		String ActPaymentMethodCodeonLanding = ActionsClass.getdefaultValuefromdropdown(CaptionPaymentMethodCode);
		logger.info("Get Default Payment Method Code");
		System.out.println(ActPaymentMethodCodeonLanding);
		assertSoft.assertEquals(ActPaymentMethodCodeonLanding,ExpecPaymentMethodCode);

		String ActReceiptOverriddenStatusonLanding = ActionsClass.getdefaultValuefromdropdown(CaptionReceiptOverriddenStatus);
		logger.info("Get Default Receipt Overridden Status");
		System.out.println(ActReceiptOverriddenStatusonLanding);
		assertSoft.assertEquals(ActReceiptOverriddenStatusonLanding,ExpecReceiptOverriddenStatus);

		String ActProjectInvoiceonLanding = ActionsClass.getdefaultValuefromdropdown(CaptionProjectsInvoice);
		logger.info("Get Default Project Invoice");
		System.out.println(ActProjectInvoiceonLanding);
		assertSoft.assertEquals(ActProjectInvoiceonLanding,ExpecProjectInvoice);


		String ActCancelPaymentonLanding =invoiceMaintenance.getStatus(CaptionCancelPayment);
		logger.info("Get Default Cancel Payment");
		System.out.println(ActCancelPaymentonLanding);
		assertSoft.assertEquals(ActCancelPaymentonLanding,ExpecCancelPayment);

		String ActHomeCurrency = ActionsClass.getInputValue(CaptionHomeCurrency, Attribute);
		logger.info("Get Default Home Currency");
		System.out.println(ActHomeCurrency);
		assertSoft.assertEquals(ActHomeCurrency,ExpecHomeCurrency);

		String ActHomeCurrencyDesc = ActionsClass.getInputValueDescription(CaptionHomeCurrency, Attribute);
		logger.info("Get Default Home Currency Description");
		System.out.println(ActHomeCurrencyDesc);
		assertSoft.assertEquals(ActHomeCurrencyDesc,ExpecHomeCurrencyDesc);

		invoiceMaintenance.inputSupplierCompanyID(SupplierCompanyId);
		logger.info("Enter Supplier Company ID");
		ActionsClass.sendKeys(invoiceMaintenance.input_SupplierCompanyId, Keys.ENTER);
		logger.info("Press Enter Key from KeyPad");
		Thread.sleep(2000);
		String randNum =  ActionsClass.randomNumbers(8);
		String SupplierInvoiceNo = "SUPPINV" + " "+randNum;

		invoiceMaintenance.inputSupplierInvoiceNumber(SupplierInvoiceNo);
		logger.info("Enter Supplier Invoice Number");
		boolean isSupplierCompanyIdEnabled = invoiceMaintenance.isSupplierCompanyIdEnabled();

		if(!isSupplierCompanyIdEnabled) {

			Assert.fail("Supplier Company Id Field is Disabled.Please Validate it!!");

		}

		boolean isSupplierCompanyIdDescEnabled = invoiceMaintenance.isSupplierCompanyIdDescriptionEnabled();
		System.out.println(isSupplierCompanyIdDescEnabled);
		if(isSupplierCompanyIdDescEnabled) {

			Assert.fail("Supplier Company Id Description Field is Enabled.Please Validate it!!");

		}

		String getActSupplierCompanyId = ActionsClass.getInputValue(CaptionNameSupplierCompanyId, Attribute);
		System.out.println(getActSupplierCompanyId);
		if(getActSupplierCompanyId==""||getActSupplierCompanyId==null||getActSupplierCompanyId=="undefined") {
			Assert.fail("Supplier Company Id is not as Expected,Please Validate it!!"+getActSupplierCompanyId);
			logger.info("Get Invalid Supplier Company Id");

		}


		String getActSupplierCompanyIdDesc = ActionsClass.getInputValueDescription(CaptionNameSupplierCompanyId, Attribute);
		System.out.println(getActSupplierCompanyIdDesc);
		if(getActSupplierCompanyIdDesc==""||getActSupplierCompanyIdDesc==null||getActSupplierCompanyIdDesc=="undefined") {
			Assert.fail("Supplier Company Id Descrption is not as Expected,Please Validate it!!"+getActSupplierCompanyIdDesc);
			logger.info("Get Invalid Supplier Company Id Description");
		}

		String getActVendorCode = ActionsClass.getInputValue(CaptionVendorCode, Attribute);
		System.out.println(getActVendorCode);
		if(getActVendorCode==""||getActVendorCode==null||getActVendorCode=="undefined") {
			Assert.fail("Vendor Code is not as Expected,Please Validate it!!"+getActVendorCode);
			logger.info("Get Invalid Vendor Code");
		}

		String getERPVendorSiteCode = ActionsClass.getInputValue(CaptionERPVendorSiteCode, Attribute);
		System.out.println(getERPVendorSiteCode);
		if(getERPVendorSiteCode==""||getERPVendorSiteCode==null||getERPVendorSiteCode=="undefined") {
			Assert.fail("ERP Site Vendor Code is not as Expected,Please Validate it!!"+getERPVendorSiteCode);
			logger.info("Get Invalid ERP Vendor Code");

		}

		//PMC -PaymentMethodCode 
		String ActPMCAfterSelectingSupplierCompanyID = ActionsClass.getdefaultValuefromdropdown(CaptionPaymentMethodCode);
		logger.info("Get Default Payment Method Code");
		System.out.println(ActPMCAfterSelectingSupplierCompanyID);
		assertSoft.assertEquals(ActPMCAfterSelectingSupplierCompanyID,ExpecPaymentMethodCode);


		String getRemitToAddressCode = ActionsClass.getInputValue(CaptionRemittoAddress, Attribute);
		System.out.println(getRemitToAddressCode);

		if(getRemitToAddressCode==""||getRemitToAddressCode==null||getRemitToAddressCode=="undefined") {
			Assert.fail("Remit to Address is not as Expected,Please Validate it!!");
			logger.info(" Remit To Address Code is not Auto populated");

		}

		String getRemitToCity = ActionsClass.getInputValue(CaptionRemitToCity, Attribute);
		System.out.println(getRemitToCity);
		if(getRemitToCity==""||getRemitToCity==null||getRemitToCity=="undefined") {
			Assert.fail("Remit to City is not as Expected,Please Validate it!!"+getRemitToCity);
			logger.info(" Remit To City is not Auto populated");
		}


		String getTerms = ActionsClass.getInputValue(CaptionTerms, Attribute);
		logger.info("Terms is not Auto populated");
		System.out.println(getTerms);
		assertSoft.assertEquals(getTerms,ExpInvTerms);

		String getTermsDesc = ActionsClass.getInputValueDescription(CaptionTerms, Attribute);
		logger.info("Terms Description is not Auto populated");
		System.out.println(getTermsDesc);
		assertSoft.assertEquals(getTermsDesc,ExInvTermsDesc);

		invoiceMaintenance.inputDate(Date);
		logger.info("Enter Date");
		invoiceMaintenance.inputInvoiceSubTotalAmount(InvoiceSubTotal);
		logger.info("Enter Invoice Sub Total");
		invoiceMaintenance.inputInvoicetaxAmount(TaxAmount);
		logger.info("Enter Tax Amount");

		String getInvoiceGradTotal = ActionsClass.getInputValue(CaptionInvoiceGrandTotal, Attribute);

		System.out.println(getInvoiceGradTotal);
		if(getInvoiceGradTotal==""||getInvoiceGradTotal==null||getInvoiceGradTotal=="undefined") {
			Assert.fail("Invoice Grand Total is not as Expected,Please Validate it!!");
			logger.info("Get Invalid Invoice Grand Total");

		}

		//============Add Detail Row==================

		invoiceMaintenance.clickonDetailTab();
		logger.info("Click on Detail Tab");

		String[] value = invoiceMaintenance.AddDetailRow(CaptionLineType,CaptionLineStatus,CaptionVariancePrice,Attribute,Item,DeptCode,GLAccount
				,CaptionItemDescription,CaptionCategoryID,CaptionDepartment,CaptionGLAccount,CaptionInvoiceUOM,CaptionOrderUOM,CaptionRecalculateUseTaxRate,InvoiceQty,InvoicePrice,TaxAmount);

		String ActPanelId = (String)Array.get(value, 0);
		assertSoft.assertEquals(ActPanelId,ExpItemPanelId);

		String ActLineType = (String)Array.get(value, 1);
		assertSoft.assertEquals(ActLineType,ExpLineType);
		String ActLineStatus = (String)Array.get(value, 2);
		assertSoft.assertEquals(ActLineStatus,ExpLineStatus);
		String ActVariancePrice = (String)Array.get(value, 3);
		assertSoft.assertEquals(ActVariancePrice,ExpVariancePrice);
		String ItemDescription = (String)Array.get(value, 4);
		if(ItemDescription==""||ItemDescription==null||ItemDescription=="undefined")
		{
			Assert.fail("Item Description is not Auto-populated,Please Validate it!!");
		}
		String CategoryID = (String)Array.get(value, 5);
		if(CategoryID==""||CategoryID==null||CategoryID=="undefined")
		{
			Assert.fail("Category ID is not Auto-populated,Please Validate it!!");
		}
		String CategoryIDDesc = (String)Array.get(value, 6);
		if(CategoryIDDesc==""||CategoryIDDesc==null||CategoryIDDesc=="undefined")
		{
			Assert.fail("Category ID Description is not Auto-populated,Please Validate it!!");
		}
		String Department = (String)Array.get(value, 7);
		if(Department==""||Department==null||Department=="undefined")
		{
			Assert.fail("Department is not populated,Please Validate it!!");
		}
		String DepartmentDesc = (String)Array.get(value, 8);
		if(DepartmentDesc==""||DepartmentDesc==null||DepartmentDesc=="undefined")
		{
			Assert.fail("Department Description is not populated,Please Validate it!!");
		}
		String GLAccount = (String)Array.get(value, 9);
		if(GLAccount==""||GLAccount==null||GLAccount=="undefined")
		{
			Assert.fail("GL Account is not populated,Please Validate it!!");
		}
		String GLAccountDesc = (String)Array.get(value, 10);
		if(GLAccountDesc==""||GLAccountDesc==null||GLAccountDesc=="undefined")
		{
			Assert.fail("GL Account Description is not populated,Please Validate it!!");
		}
		String InvoiceUOM = (String)Array.get(value, 11);
		if(InvoiceUOM==""||InvoiceUOM==null||InvoiceUOM=="undefined")
		{
			Assert.fail("Invoice UOM is not populated,Please Validate it!!");
		}

		String InvoiceUOMDesc = (String)Array.get(value, 12);
		if(InvoiceUOMDesc==""||InvoiceUOMDesc==null||InvoiceUOMDesc=="undefined")
		{
			Assert.fail("Invoice UOM Description is not populated,Please Validate it!!");
		}
		String OrderUOM = (String)Array.get(value, 13);

		if(OrderUOM==""||OrderUOM==null||OrderUOM=="undefined")
		{
			Assert.fail("Order UOM is not populated,Please Validate it!!");
		}

		//Delete Row

		invoiceMaintenance.DeleteDetailRow(ItemName);

		//Add Row

		invoiceMaintenance.AddDetailRow(CaptionLineType,CaptionLineStatus,CaptionVariancePrice,Attribute,Item,DeptCode,GLAccount,CaptionItemDescription,CaptionDepartment,CaptionCategoryID,CaptionGLAccount,CaptionInvoiceUOM,CaptionOrderUOM,CaptionRecalculateUseTaxRate,InvoiceQty,InvoicePrice,TaxAmount);
		invoiceMaintenance.clickOnSavebtn();
		logger.info("Click on Save Button");
		ActionsClass.waitForAlert(20);
		logger.info("Wait for Alert");
		String alertMsgonSave = ActionsClass.getAlertText();
		logger.info("Get Alert Text");
		System.out.println(alertMsgonSave);
		if(alertMsgonSave.contains("PO reference #"))
		{
			Assert.fail("PO Reference is Mandatory on AP Invoice Screen,It should be Non Mandatory.Please Validate it!!");
		}
		else {
			Assert.assertEquals(alertMsgonSave,AlertText);
			logger.info("Alert Text same as Expected");
			ActionsClass.acceptAlert();
			logger.info("Accept Alert");
		}

		//Submit AP Invoice

		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		invoiceMaintenance.clickOnSubmitbtn();
		logger.info("Click on Submit Button");
		ActionsClass.waitForAlert(10);
		logger.info("Wait for Alert");
		String alertMsgonSubmit = ActionsClass.getAlertText();
		logger.info("Get Alert Text After Submit");
		System.out.println(alertMsgonSubmit);
		Assert.assertEquals(alertMsgonSubmit,AlertText);
		ActionsClass.acceptAlert();
		logger.info("Accept Alert");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);

		invoiceMaintenance.clickOnInvoiceTab();
		logger.info("Click on Invoice Tab");
		String APInvoiceNumber = invoiceMaintenance.getAPInvoiceNumber();
		logger.info("Get AP Invoice Number");
		System.out.println(APInvoiceNumber);
		invoiceMaintenance.clickOnCancelbtn();
		logger.info("Click on Cancel Button");

		// Input PO number in Excel

		xlutil.setCellData("GlobalVariables",1,"NonPOInvoiceForAramark","");
		xlutil.setCellData("GlobalVariables", 1, "NonPOInvoiceForAramark", APInvoiceNumber);
		logger.info("SAP Invoice Save into Excel");

		System.out.println(xlutil.getCellData("GlobalVariables",1,1));
		ActionsClass.switchToWindowIndex(0);
		logger.info("Switch to Window Index 0");

		//Validate TransmissionDate on AP Invoice Tile

		home.clickonAccountPayableTile();
		logger.info("Click on Account Payable Tile");

		ActionsClass.waitForElementClickable(accountPayable.tile_APInvoice, 10);
		logger.info("Wait for AP Invoice Tile Clickable");
		accountPayable.clickonAPInvoiceTile();
		logger.info("Click on AP Invoice Tile");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);

		apInvoice.selectInvoicePanel(InvoicePanel);
		logger.info("Seleced Invoice Panel");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		apInvoice.clickOnSortingIcon();
		logger.info("Click on Sorting");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		String getTrsansmissionDate = apInvoice.getTransmissionDate();
		logger.info("Get Transmission Date");
		String dateFormatRegex="\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}";
		boolean isDateElementPresent = getTrsansmissionDate.matches(dateFormatRegex);
		System.out.println(isDateElementPresent);
		if(!isDateElementPresent){
			Assert.fail("Transmission Date is not Present as expected with time"+getTrsansmissionDate);

		}
		logger.info("Transmission Date is Present on Transmission Date Column");
		logger.info("******************Test Case Ended*******************");
	}
}


//2024.01 - TCM-3054 - NonPOInvoiceCreationAtBuyerSide - ntripathi - 5thNovember2024
//2024.02 - TCM-3086 - Add Logger - ntripathi - 7thNovember2024
