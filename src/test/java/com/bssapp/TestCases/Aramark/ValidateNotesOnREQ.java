package com.bssapp.TestCases.Aramark;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bssapp.PageObjects.Page_Favorites;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_Notes;
import com.bssapp.PageObjects.Page_Purchasing;
import com.bssapp.PageObjects.Page_REQ;
import com.bssapp.PageObjects.Page_REQHeaderUpdate;
import com.bssapp.PageObjects.Page_Requisitions;

import com.bssapp.TestBase.BaseClass;

public class ValidateNotesOnREQ extends BaseClass {

	
	String subID="641";
	String compID="931";
	String OG = "*AutomationOG1";
	String productName="1/2 TRAY CHEESECAKE BROWNIE";
	String Notes = "Test Notes BY Automation";
	String noteType="From buyer";
	@Test
	public void ValidateNotesOnREQMethod() throws IOException, InterruptedException {
		home=new Page_Home(driver);
		purchasing=new Page_Purchasing(driver);
		fav= new Page_Favorites(driver);
		notes =new Page_Notes(driver);
		req=new Page_REQ(driver);
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
		ActionsClass.moveToElement(home.icon_MainMenuPurchasing);
		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.waitForElementClickable(purchasing.tile_Requisition, 10);
		ActionsClass.moveToElement(purchasing.tile_Requisition);
		purchasing.clickOnRequisitionTile();
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		WebElement reqNumberElement= ActionsClass.customElementWithText(reqNumber);
		boolean isReqVisible= reqNumberElement.isDisplayed();
		logger.info("The Req is present in requisition tile as expected");
		Assert.assertEquals(isReqVisible, true);
		requisitions.selectREQ(reqNumber);
		requisitions.clickOnBtnInfo();
		requisitions.clickOnBtnNotesOnInfo();
		logger.info("clicked On Notes button");
		ActionsClass.switchToWindowIndex(1);
		ActionsClass.waitForPageLoad(40);
		String actNotesWindowTitle=driver.getTitle();
		String expNotesWindowTitle="Notes - Temple Esposito Dining Center";
		Assert.assertEquals(actNotesWindowTitle, expNotesWindowTitle);
		notes.clickOnBtnNew();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		notes.inputTextareaNotes(Notes);
		notes.selectNoteType(noteType);
		notes.clickOnBtnSave();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		notes.clickOnBtnCancel();
		Thread.sleep(2000);
	    ActionsClass.selectFirstRow(notes.txt_getAddedNotes);
		String getNotes=notes.getTextAddedNotes();
		Assert.assertEquals(getNotes, Notes);
		ActionsClass.closeWindowIndex(1);
		ActionsClass.switchToWindowIndex(0);
		notes.clickOnIconRefresh();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		requisitions.clickOnIconReqNumberFilter();
		requisitions.inputinFilter(reqNumber);
		requisitions.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader,10);
		requisitions.clickOnIconNotes();
		ActionsClass.switchToWindowIndex(1);
		ActionsClass.waitForPageLoad(30);
		Thread.sleep(2000);
		actNotesWindowTitle=driver.getTitle();
		Assert.assertEquals(actNotesWindowTitle, expNotesWindowTitle);
		getNotes=notes.getTextAddedNotes();
		Assert.assertEquals(getNotes, Notes);
		
	}
	
}
