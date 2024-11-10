package com.bssapp.TestCases.Aramark;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bssapp.PageObjects.Page_BrowseCatalogs;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_Purchasing;

import com.bssapp.TestBase.BaseClass;

public class ToValidateContextMenuInBrowseCatalogs_Aramark extends BaseClass {

	String subID="641";
	String compID="931";
	String ItemName ="1/2 TRAY CHEESECAKE BROWNIE";
	
	
	@Test
	public void ToValidateContextMenuInBrowseCatalogs() throws IOException, InterruptedException{
		home=new Page_Home(driver);
		browseCatalogs=new Page_BrowseCatalogs(driver);
		purchasing=new Page_Purchasing(driver);
		
		logger.info("*********************Test Case Started**********************");
		logIn(subID);
		logger.info("User is logIn successfully");
		ActionsClass.waitForElementClickable(home.btn_CloseMyTask, 10);
		home.clickonCloseMyTaskbtn();
		switchToCompany(compID);
		logger.info("switched To companyId 931");
		ActionsClass.waitForElementClickable(home.icon_MainMenuPurchasing,5);
		home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 15);
		ActionsClass.waitForElementClickable(purchasing.tile_BrowseCatalogs,10);
		purchasing.clickOnBrowseCatalogsTile();
		ActionsClass.switchToWindowIndex(1);
		logger.info("switched To Browse Catalog Window");
		ActionsClass.waitForPageLoad(40);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 10);
		String getCatalogTitle = driver.getTitle();
		Assert.assertEquals(getCatalogTitle, "BirchStreet Catalog");
	    browseCatalogs.clickonSearchMenu();
		browseCatalogs.inputValueinSearchMenu(ItemName);
		browseCatalogs.clickonSearchbtn();
		ActionsClass.waitForPageLoad(30);
		ActionsClass.waitForElementInvisible(home.icon_spinLoader, 20);
		ActionsClass.switchToFrame("TopCatalog");
		ActionsClass.rightClick(browseCatalogs.input_Qty);
		String contextMenuTitle=browseCatalogs.txt_ContextMenuTitle.getText();
		String contextMenuViewDetail=browseCatalogs.txt_ContextMenuViewDetail.getText();
		Assert.assertEquals(contextMenuTitle, ItemName);
		Assert.assertEquals(contextMenuViewDetail, "View Detail");	
	}
	
	
	
	
	
	
	
	
	
}


//TCM--3071//aurehman---28thOct