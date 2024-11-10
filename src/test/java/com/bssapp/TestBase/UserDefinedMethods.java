package com.bssapp.TestBase;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_InvoiceMaintenance;
import com.bssapp.PageObjects.Page_ManageOrders;
import com.bssapp.PageObjects.Page_Requisitions;

public class UserDefinedMethods extends BaseClass {


	WebDriver driver;
	public UserDefinedMethods(WebDriver driver){
		this.driver=driver;
	}

	public static void validatePurchaseType(WebElement PurchseType,String[] optionsInPurchType) {


		Select s = new Select(PurchseType);

		List <WebElement> op =s.getOptions();


		int ActnumOfOptInPurchType = op.size();
		List<String> options=new ArrayList<>();
		for(int i=0;i<ActnumOfOptInPurchType;i++){


			String option=op.get(i).getText();
			System.out.println(option);
			options.add(op.get(i).getText());
			System.out.println(options);

		}

		for(int i = 0; i < optionsInPurchType.length; i++){
			if (options.contains(optionsInPurchType[i]))
				continue;

			else{

				Assert.fail("The missing option in the Purchase  Type is : "+optionsInPurchType[i]);
			}
		}

		for(int j=0; j<options.size();j++){
			if (optionsInPurchType[j].contains(options.get(j)))
				continue;
			else
			{

				Assert.fail("The new option in the Purchase  Type is : "+options.get(j));
			}
		}


	}




	public  boolean ValidatePOisDeleted(String poNumber) throws InterruptedException {
		orders=new Page_ManageOrders(driver);
		home=new Page_Home(driver);
		ActionsClass=new ActionsClass(driver);
		orders.clickOnIconPONumberFilter();
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		
		WebElement POEle;
		try {
			POEle=ActionsClass.customElementWithText(poNumber);
			Assert.fail("PO is present in ManageOrder");
		}
		catch(Exception e) {
			
			System.out.println("PO is Not present in ManageOrder");
		}
		orders.selectDropdownMultiPanels("My Recycle Bin PO's");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		orders.clickOnIconPONumberFilter();
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		POEle=ActionsClass.customElementWithText(poNumber);
		boolean isPOVisisble=POEle.isDisplayed();
		return isPOVisisble;


	}



public  String DeleteInvoiceRightClick(String RecycleBinInvoicePanel,String SupplierInvoiceNumber ) {
		
	    invoiceMaintenance=new Page_InvoiceMaintenance(driver);
	    home=new Page_Home(driver);
	    ActionsClass=new ActionsClass(driver);
		ActionsClass.waitForElementClickable(BaseClass.invoiceMaintenance.btn_Delete, 10);
		invoiceMaintenance.clickOnDeletebtn();
		ActionsClass.waitForAlert(10);
		String getDeletInvoiceAlertText	= ActionsClass.getAlertText();
		System.out.println(getDeletInvoiceAlertText);
		
		ActionsClass.acceptAlert();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		invoiceMaintenance.selectInvoicePanel(RecycleBinInvoicePanel);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		
		invoiceMaintenance.clickOnFilterSupplierInvoiceIcon();
		invoiceMaintenance.inputinFilter(SupplierInvoiceNumber);
		ActionsClass.sendKeys(invoiceMaintenance.input_Filter,Keys.ENTER);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		return getDeletInvoiceAlertText	;
			
		
	}

public  boolean validateReqisDeleted(String reqNumber) throws InterruptedException {
requisitions=new Page_Requisitions(driver);
home=new Page_Home(driver);
ActionsClass=new ActionsClass(driver);
requisitions.selectDropdownMultiPanels("Recycle Bin REQ's");
ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
requisitions.clickOnIconReqNumberFilter();
requisitions.inputinFilter(reqNumber);
requisitions.clickOnApplyFilterbtn();
ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader,4);
WebElement ReqEle=ActionsClass.customElementWithText(reqNumber);
return ReqEle.isDisplayed();

}
}
//TCM-3035--Created This Page, Added Methods---aurehman---15/10/2024--aurehman
//TCM-3043 - Created Method - DeleteInvoiceRightClick - ntripathi - 17October2024