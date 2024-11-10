package com.bssapp.TestCases.Aramark;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_Attachments;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_ManageOrders;
import com.bssapp.PageObjects.Page_Purchasing;

import com.bssapp.TestBase.BaseClass;

public class AttachmentButtonFunctionalityatPOTab_Aramark extends BaseClass {

	String subID="641";
	String compID="931";
	String expattchmentWindowTitle="Select Upload File";
	String expDeleteattchmentWindowTitle="Confirm Delete File";
	@Test
	public void AttachmentButtonFunctionalityatPOTab() throws IOException, InterruptedException {
		home=new Page_Home(driver);
		purchasing=new Page_Purchasing(driver);
		orders=new Page_ManageOrders(driver);
		attachments=new Page_Attachments(driver);

		logger.info("*********************Test Case Started**********************");
		logIn(subID);
		logger.info("User is logIn successfully");
		ActionsClass.waitForElementClickable(home.btn_CloseMyTask, 10);
		home.clickonCloseMyTaskbtn();
		ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing,10);
		switchToCompany(compID);
		logger.info("switched To companyId 931");

		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		ActionsClass.waitForElementClickable(purchasing.tile_ManageOrders,5);
		purchasing.clickOnManageOrdersTile();
		logger.info("switched To manage Orders tile");
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		String poNumber=orders.getPONumber();
		System.out.println(poNumber);
		orders.clickOnIconPONumberFilter();
		orders.inputinFilter(poNumber);
		orders.clickOnApplyFilterbtn();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		orders.selectPO(poNumber);
		orders.clickOnBtnInfo();
		orders.clickOnBtnAttachments();
		ActionsClass.waitForPageLoad(15);
		ActionsClass.switchToFrame("_dlgOpenerIframe1");
		attachments.clickOnBtnAddImage();
		ActionsClass.switchToWindowIndex(1);
		String attachmentUploadFileWindow=driver.getTitle();
		System.out.println(attachmentUploadFileWindow);
		Assert.assertEquals(attachmentUploadFileWindow, expattchmentWindowTitle);
		String file_location=getAttachmentFilePath();
		attachments.selectDocumentType("Other");
		attachments.uploadFile(file_location);
		attachments.clickOnBtnSubmit();
		Alert isAlertPresent=null;
		try {
			ActionsClass.switchToWindowIndex(1);
	        isAlertPresent=ActionsClass.waitForAlert(5);
			ActionsClass.acceptAlert();
			ActionsClass.switchToWindowIndex(0);
			ActionsClass.switchToFrame("_dlgOpenerIframe1");
			attachments.link_viewAttachment.click();
			attachments.clickOnBtnDelete();
			Thread.sleep(1000);
			ActionsClass.switchToWindowIndex(1);
			String attachmentDeleteFileWindow=driver.getTitle();
			System.out.println(attachmentDeleteFileWindow);
			Assert.assertEquals(attachmentDeleteFileWindow, expDeleteattchmentWindowTitle);
			attachments.clickOnBtnOK();
			Thread.sleep(1000);
			ActionsClass.switchToWindowIndex(0);
			ActionsClass.switchToFrame("_dlgOpenerIframe1");
			try {
		    boolean isAttachmentVisible=attachments.link_viewAttachment.isDisplayed();
		    Assert.fail("Attachment is not deleted"+isAttachmentVisible);
			}
			catch (Exception e) {
				
	            logger.info("Attachment is deleted");
	   
	        } 
		    attachments.clickOnBtnAddImage();
			ActionsClass.switchToWindowIndex(1);
			System.out.println(attachmentUploadFileWindow);
			attachments.selectDocumentType("Other");
			attachments.uploadFile(file_location);
			attachments.clickOnBtnSubmit();
		}
		catch (Exception e) {
			System.out.println(isAlertPresent);
            logger.info("No alert present, continuing...");
   
        } 
		ActionsClass.switchToWindowIndex(0);
		attachments.clickOnIconClose();
		orders.clickOnRefreshIcon();
		ActionsClass.waitForPageLoad(15);
		boolean isAttachmentVisible=orders.icon_AttachedAttachment.isDisplayed();
		Assert.assertTrue(isAttachmentVisible);
		orders.clickOnIconAttachedAttachment();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		ActionsClass.switchToFrame("_dlgOpenerIframe2");
		String fileText= attachments.link_viewAttachment.getText();
		System.out.println(fileText);
		Assert.assertEquals(fileText, fileName);
		logger.info("image is Attached");
		logger.info("*********************Test Case Ended**********************");

	}
}
