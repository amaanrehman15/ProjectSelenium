package com.bssapp.TestCases.Aramark;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_SupplierInvoice;
import com.bssapp.PageObjects.Page_SupplierPortal;
import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class InvoiceCreationAtSupplierSide_Aramark extends BaseClass {

	String SubscriberId="641";
	String BuyerCompanyId= "931";
	String SupplierCompanyId="568";
	String ExpecSupplierInvoiceTitle = "Supplier Invoice - CITY BAKING";
	String Date = "t";
	String CompanyIdDesc = "Temple Esposito Dining Center";
	String Currency="USD";
	String LineType="Catalog";
	String ExpecPanelId ="11861";
	String Item="ABW";
	String InvoiceQuantity="10";
	String expInvoiceAlert = "Processing is done.";
	String AllInvoicePanel ="All Invoices";
	String InvoiceStatus ="Transmitted to buyer";

	@Test
	public void InvoiceCreationAtSupplierSide() throws IOException, InterruptedException {

		home= new Page_Home(driver);
		supplierPortal= new Page_SupplierPortal(driver);
		supplierInvoice= new Page_SupplierInvoice(driver);



		logIn(SubscriberId);

		home.clickonCloseMyTaskbtn();

		switchToCompany(SupplierCompanyId);

		// Navigate to Supplier Invoice Screen

		ActionsClass.switchtoDefaultContent();
		ActionsClass.waitForElementClickable(home.icon_SupplierPortalMainMenu, 20);
		home.clickonSupplierPortalfromMainMenu();
		ActionsClass.waitForElementClickable(supplierPortal.tile_SupplierInvoice, 10);
		supplierPortal.clickOnSupplierInvoice();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);

		//Validate Window Title

		String getSupplierInvoiceTitle = driver.getTitle();
		Assert.assertEquals(getSupplierInvoiceTitle, ExpecSupplierInvoiceTitle);

		String randNum =  ActionsClass.randomNumbers(8);
		String SupplierInvoiceNo = "SUPPINV" + " "+randNum;

		//Fill Supplier Invoice

		supplierInvoice.clickOnNewbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		supplierInvoice.inputSupplierInvoiceNumber(SupplierInvoiceNo);
		supplierInvoice.inputInvoiceDate(Date);
		supplierInvoice.inputTrxCurrency(Currency);
		supplierInvoice.inputBuyerCompanyId(BuyerCompanyId);
		String getCompanyId= supplierInvoice.getCompanyId();
		assertSoft.assertEquals(getCompanyId, BuyerCompanyId);

		supplierInvoice.clickOnDate();
		Thread.sleep(3000);
		String getBuyerCompanyIdDesc = supplierInvoice.getCompanyIdDesc();
		System.out.println(getBuyerCompanyIdDesc);
		assertSoft.assertEquals(getBuyerCompanyIdDesc,CompanyIdDesc);


		//Add Details

		supplierInvoice.clickOnDetailTab();
		ActionsClass.waitForElementClickable(supplierInvoice.btn_AddRow, 10);
		supplierInvoice.clickOnAddRowbtn();
		ActionsClass.waitForElementClickable(supplierInvoice.dropDown_LineType, 10);
		supplierInvoice.selectLineType(LineType);
		
		String ActItemPanelId = supplierInvoice.getItemPanel();
		System.out.println(ActItemPanelId);
		assertSoft.assertEquals(ActItemPanelId, ExpecPanelId);
		boolean isItemInputFieldEnabled=supplierInvoice.isItemEnabled();
		if(isItemInputFieldEnabled) {

			Assert.fail("Item Input Field is Enabled it should be disable,Please Validate it!!");
		}
		supplierInvoice.clickOnlookupItem();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		supplierInvoice.clickOnItemFilterIcon();
		supplierInvoice.inputFilter(Item);
		supplierInvoice.clickOnApplyFilterbtn();
		supplierInvoice.selectItem();
		supplierInvoice.clickOnSelectbtn();
		supplierInvoice.inputInvoiceQuantity(InvoiceQuantity);
		supplierInvoice.clickOnSaveDetailbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);

		//Save Supplier Invoice

		supplierInvoice.clickOnSavebtn();
		ActionsClass.waitForAlert(10);
		String getActAlertAfterSaveSupplierInvoice = ActionsClass.getAlertText();
		assertSoft.assertEquals(getActAlertAfterSaveSupplierInvoice, expInvoiceAlert);
		ActionsClass.acceptAlert();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);

		//Submit Supplier Invoice

		supplierInvoice.clickOnSubmitbtn();

		ActionsClass.waitForAlert(10);
		String getActAlertAfterSubmitSupplierInvoice = ActionsClass.getAlertText();
		assertSoft.assertEquals(getActAlertAfterSubmitSupplierInvoice, expInvoiceAlert);
		ActionsClass.acceptAlert();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		supplierInvoice.clickOnCancelbtn();

		//Navigate to Supplier Invoice

		ActionsClass.waitForElementClickable(home.icon_SupplierPortalMainMenu, 10);
		home.clickonSupplierPortalfromMainMenu();
		ActionsClass.waitForElementClickable(supplierPortal.tile_SupplierInvoice, 10);
		supplierPortal.clickOnSupplierInvoice();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		supplierInvoice.selectInvoicePanel(AllInvoicePanel);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 50);
		//ActionsClass.waitForElementClickable(supplierInvoice.icon_SupplierInvoiceFilter, 30);
		supplierInvoice.clickOnSupplierInvoiceFilterIcon();
		supplierInvoice.inputFilter(SupplierInvoiceNo);
		supplierInvoice.clickOnApplyFilterbtn();
		
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);

		WebElement getSupplierInvoiceNumberElement = ActionsClass.customElementWithText(SupplierInvoiceNo);
		System.out.println(getSupplierInvoiceNumberElement);

		boolean isSupplierInvNumberVisibleinSupplierInvoice = ActionsClass.iselementDisplayed(getSupplierInvoiceNumberElement);
		if(!isSupplierInvNumberVisibleinSupplierInvoice)
		{
			Assert.fail("Supplier Invoice Number is not Visible in Supplier Invoice Screen, Please Validate it!!");
		}

		String getInvoiceStatus = supplierInvoice.getInvoiceStatus();
		Assert.assertEquals(getInvoiceStatus,InvoiceStatus);
        assertSoft.assertAll();
	}

}


//2024.01 - TCM-3050 - InvoiceCreationAtSupplierSide - ntripathi - 18October2024