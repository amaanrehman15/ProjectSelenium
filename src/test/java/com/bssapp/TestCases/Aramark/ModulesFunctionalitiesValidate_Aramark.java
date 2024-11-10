package com.bssapp.TestCases.Aramark;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_APInvoice;
import com.bssapp.PageObjects.Page_AccountPayable;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_Inbox;
import com.bssapp.PageObjects.Page_InvoiceMaintenance;
import com.bssapp.PageObjects.Page_MySetting;
import com.bssapp.TestBase.BaseClass;


public class ModulesFunctionalitiesValidate_Aramark extends BaseClass {

	String SubscriberId="641";
	String BuyerCompanyId= "931";
	String ExpecInboxWinTitle ="Inbox - Temple Esposito Dining Center";
	String SelectInboxPanel = "Read in box";
	String Frame ="_dlgOpenerIframe1";
	String ExpecMySettingWinTitle ="My Settings - Temple Esposito Dining Center";
	String drpdwn ="Email only ";
	String ExpalertLogOut ="109- Are you sure you want to logout?";
	String ExpURLAfterLogout = "https://uat.birchstreetsystems.com/j4/login.jsp";

	@Test
	public void ModulesFunctionalitiesValidate() throws IOException {

		home= new Page_Home(driver);
		invoiceMaintenance = new Page_InvoiceMaintenance(driver);
		accountPayable= new Page_AccountPayable(driver);
		apInvoice = new Page_APInvoice(driver);
		inbox = new Page_Inbox(driver);
		mySetting = new Page_MySetting(driver);

		logger.info("******************Test Case Started*******************");

		logIn(SubscriberId);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		logger.info("LogIn into Application successfully");

		home.clickonCloseMyTaskbtn();
		logger.info("Close My Task");

		switchToCompany(BuyerCompanyId);
		logger.info("switched To Buyer CompanyId");

		ActionsClass.switchtoDefaultContent();
		logger.info("Switch to Default Content");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		ActionsClass.waitForElementClickable(home.tile_Purchasing,10);
		logger.info("Wait for Purchasing Tile Clickable");
		
		// ========== Validate Subject from Inbox===============

		home.clickOnDisplayBtn();
		logger.info("Click on Dispaly Button");

		ActionsClass.waitForElementClickable(home.btn_Inbox,10);
		home.clickOnInboxBtn();
		logger.info("Click on Inbox Button");

		ActionsClass.switchToWindowIndex(1);
		logger.info("Switch to WindowIndex 1");

		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		String getWindowTitle = driver.getTitle();
		logger.info("Ge tWindow Title");

		Assert.assertEquals(getWindowTitle,ExpecInboxWinTitle);

		String getSubjectfromRow = inbox.getReadInboxSubject();
		inbox.selectSentMsg();
		logger.info("Select Message");
		ActionsClass.rightClick(inbox.select_SentMsg);
		logger.info("Right Click");
		inbox.clickOnViewbtn();
		logger.info("Click on View Button");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		ActionsClass.switchToFrame(Frame);
		logger.info("Switch to Frame");
		ActionsClass.waitForElementClickable(inbox.btn_OK, 10);
		logger.info("Wait f0r OK button Clickbale");
		String getSubjectfromMesageBox = inbox.getSubject();
		boolean isSubjectContains = getSubjectfromRow.contains(getSubjectfromMesageBox);
		if(!isSubjectContains){
			Assert.fail("Subject of Message is not Matched,Please Validate it!!");

		}
		inbox.clickOnOKbtn();
		logger.info("Click on OK Button");

		ActionsClass.closeWindowIndex(1);
		logger.info("Close Window Index 1");
		ActionsClass.switchToWindowIndex(0);
		logger.info("Close Window Index 0");

		//======= Update Message Delivery Option =======================

		home.clickOnDisplayBtn();
		logger.info("Click on Display Button");
		ActionsClass.waitForElementClickable(home.btn_MySetting,10);
		home.clickOnMySettingBtn();
		logger.info("Click on My Setting Button");
		ActionsClass.switchToWindowIndex(1);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		String getMySettingWindowTitle = driver.getTitle();

		Assert.assertEquals(getMySettingWindowTitle,ExpecMySettingWinTitle);

		mySetting.selectMessageDeliveryOption(drpdwn);
		logger.info("Selected Message Delivery Option");
		String msgDeliveryType = mySetting.getMessageDeliveryOption();
		System.out.println(msgDeliveryType);

		mySetting.clickOnSavebtn();
		logger.info("Click on Save Button");

		//====== Validate ALert After Update Message Delivery Option=================

		ActionsClass.waitForAlert(10);
		logger.info("Wait for Alert");

		String getAlertText = ActionsClass.getAlertText();
		logger.info("Get Alert Text after Click on Save Button");
		System.out.println(getAlertText);
		if (!getAlertText.contains("properties/units, we will notify you once the update process is completed")) {

			Assert.fail("Unable to get Alert of Message delivery option Updation");
		}

		ActionsClass.acceptAlert();
		logger.info("Accept Alert");
		ActionsClass.waitForPageLoad(30);
		logger.info("Wait for Page load");
		ActionsClass.closeWindowIndex(1);
		logger.info("Close WindowIndex 1");
		ActionsClass.switchToWindowIndex(0);
		logger.info("Switch to WindowIndex 0");

		//=========Validate Updated Message Delivery Option=============

		home.clickOnDisplayBtn();
		logger.info("Click on Display Button");
		ActionsClass.waitForElementClickable(home.btn_MySetting,10);
		logger.info("Wait for Element Clickable");
		home.clickOnMySettingBtn();
		logger.info("Click on My Setting Button");
		ActionsClass.switchToWindowIndex(1);
		logger.info("Switch to WindowIndex 1");
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		String UpdatedmsgDeliveryType = mySetting.getMessageDeliveryOption();
		logger.info("Get Message Delivery Option Text");
		System.out.println(UpdatedmsgDeliveryType);

		Assert.assertEquals(UpdatedmsgDeliveryType, msgDeliveryType);

		ActionsClass.closeWindowIndex(1);
		logger.info("Close WindowIndex 1");
		ActionsClass.switchToWindowIndex(0);
		logger.info("Switch to WindowIndex 0");

		//=============Validate Logout Functionality===============

		home.clickOnDisplayBtn();
		logger.info("Click on Display Button");
		ActionsClass.waitForElementClickable(home.btn_LogOut,10);
		logger.info("Wait for Logout Button Clickable");
		home.clickOnLogOut();
		logger.info("Click on Log Out Button");

		ActionsClass.waitForAlert(10);
		logger.info("Wait for Alert");

		String ActLogoutAlert = ActionsClass.getAlertText();
		logger.info("Get Alert Text after Click on Logout Button");
		Assert.assertEquals(ActLogoutAlert, ExpalertLogOut);
		ActionsClass.acceptAlert();
		logger.info("Accept Alert");

		//=========Validate URL after Logout==============

		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		String ActualAlertAfterLogout =driver.getCurrentUrl();
		logger.info("Get Currennt URL");
		Assert.assertEquals(ActualAlertAfterLogout, ExpURLAfterLogout);


		logger.info("******************Test Case Ended*******************");

	}

}


//2024.01 - ModulesFunctionalitiesValidate -  ntripathi - 7thNovember2024
