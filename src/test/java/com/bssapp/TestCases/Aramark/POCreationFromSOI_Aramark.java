package com.bssapp.TestCases.Aramark;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_Favorites;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_ManageOrders;
import com.bssapp.PageObjects.Page_Notes;
import com.bssapp.PageObjects.Page_PO;
import com.bssapp.PageObjects.Page_POPrintwDetails;
import com.bssapp.PageObjects.Page_Purchasing;
import com.bssapp.PageObjects.Page_SOI;

import com.bssapp.TestBase.BaseClass;

public class POCreationFromSOI_Aramark extends BaseClass{

	String subID="641";
	String compID="931";
	String productName="Product Test for Automation";
	String orderQty="10";
	String UOM="BAG";
	String packSize ="6/20CT";
	String price = "100";
	String supplier="WAWA INC";
	String cateogary="Beverage";
	String Notes = "Test Notes BY Automation";
	String noteType="From buyer";
	@Test
	public void POCreationFromSOI() throws InterruptedException, IOException {
		home=new Page_Home(driver);
		purchasing=new Page_Purchasing(driver);
		fav= new Page_Favorites(driver);
		PO=new Page_PO(driver);
		orders=new Page_ManageOrders(driver);
		soi=new Page_SOI(driver);
		details=new Page_POPrintwDetails(driver);
		notes =new Page_Notes(driver);
		String item="Test"+ActionsClass.randomNumbers(5);
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
		ActionsClass.waitForElementClickable(purchasing.tile_SOI, 15);
		purchasing.clickOnSOITile();
		ActionsClass.switchToWindowIndex(1);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		logger.info("Navigated To SOI tile");
		String getSOITitle = driver.getTitle();
		Assert.assertEquals(getSOITitle, "Special Order Items - Temple Esposito Dining Center");
		soi.clickOnBtnSelectSupplier();
		ActionsClass.switchToFrame("formWindow");
		soi.inputSupplier(supplier);
		soi.clickOnBtnGoSupllierSearch(getSOITitle);
		soi.clickOnBtnLinkSelectSupplier();
		logger.info("Selected Supplier");
		ActionsClass.switchtoDefaultContent();
		ActionsClass.doubleClick(soi.txt_Item);
		soi.inputItemNo(item);
		soi.inputItemNo(Keys.TAB+"");
		soi.inputProductName(productName);
		soi.inputProductName(Keys.TAB+"");
		soi.inputOrderQty(orderQty);
		soi.inputOrderQty(Keys.TAB+"");
		soi.inputOrderUOM(UOM);
		soi.inputOrderUOM(Keys.TAB+"");
		soi.inputPackSize(packSize);
		soi.inputPackSize(Keys.TAB+"");
		soi.inputPrice(price);
		soi.inputPrice(Keys.TAB+"");
		soi.selectCategory(cateogary);
		Thread.sleep(1000);
		soi.clickOnBtnCreatePO();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		HashMap<String,String> PODetails=PO.createPOFromSOIAramark("No Approver");
		String poNumber=PODetails.get("PONumber");
		logger.info("The PO generated is :"+poNumber);
		String getPOAlertText=PODetails.get("PONumberAlert");
		if(!getPOAlertText.contains("Generated PO Number:")){
			Assert.fail("PO Generation Alert is not Matched,Please Validate it!!");
		}
		logger.info("PO Created");
		String POStatus=PODetails.get("POStatus");
		Assert.assertEquals(POStatus, "New");
		logger.info("Validated PO status as New");
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
		WebElement poNumberElement= ActionsClass.customElementWithText(poNumber);
		ActionsClass.rightClick(poNumberElement);
		orders.clickOnBtnRightClickEdit();
		ActionsClass.switchToWindowIndex(1);
		ActionsClass.waitForPageLoad(40);
        details.clickonNotesbtn();
		logger.info("clicked On Notes button");
		ActionsClass.switchToWindowIndex(2);
		ActionsClass.waitForPageLoad(40);
		String actNotesWindowTitle=driver.getTitle();
		String expNotesWindowTitle="Notes - Temple Esposito Dining Center";
		Assert.assertEquals(actNotesWindowTitle, expNotesWindowTitle);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.switchToFrame("EntryScreen");
		ActionsClass.switchToFrame("Entry");
		notes.inputTextareaNotes(Notes);
		notes.selectNoteType(noteType);
		notes.clickOnBtnSave();
        ActionsClass.waitForPageLoad(20);
        Thread.sleep(2000);
		
	    String notesId=notes.getNotesID();
		System.out.println(notesId);
		
		ActionsClass.switchtoDefaultContent();
		ActionsClass.switchToFrame("NavList");
		
		notes.selectNavlist("Notes ID");
		notes.inputSearchText(notesId);
		notes.inputSearchText(Keys.ENTER+"");
		
		boolean isNotesIdPresent=notes.notesId_row1.isDisplayed();
		Assert.assertTrue(isNotesIdPresent);
		ActionsClass.closeWindowIndex(2);
		ActionsClass.closeWindowIndex(1);
		ActionsClass.switchToWindowIndex(0);
		notes.clickOnIconRefresh();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		orders.clickOnIconPONumberFilter();
		ActionsClass.clearText(orders.input_Filter);
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		
		
		orders.clickOnIconNotes();
		ActionsClass.switchToWindowIndex(1);
		ActionsClass.waitForPageLoad(30);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		Thread.sleep(2000);
		actNotesWindowTitle=driver.getTitle();
		Assert.assertEquals(actNotesWindowTitle, expNotesWindowTitle);
		String getNotes=notes.getTextAddedNotes();
		Assert.assertEquals(getNotes, Notes);
	}
}






//TCM-3081--aurehman---30OCT