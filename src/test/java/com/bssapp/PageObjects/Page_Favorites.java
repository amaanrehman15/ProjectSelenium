package com.bssapp.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Favorites extends BasePage{

	

	public Page_Favorites(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//button[@id='FavGenPO']")
	WebElement btn_CreatePO;
	
	@FindBy(xpath="//button[@id='FavAddPO']")
	WebElement btn_AddToPO;
	
	
	@FindBy(xpath="//button[@id='FavGenREQ']")
	WebElement btn_CreateREQ;
	
	@FindBy(xpath="//button[@id='FavAddREQ']")
	WebElement btn_AddToREQ;
	
	@FindBy(xpath="//button[@id='ItemSearch']")
	WebElement btn_ItemSearch;
	
	@FindBy(xpath="//button[@id='ClearAll']")
	WebElement btn_clearQty;
	@FindBy(xpath="//button[text()='Maintenance']")
	WebElement btn_Maintenance;
	
	@FindBy(xpath="//span[text()='Remove Item']")
	WebElement btn_RemoveItem;
	@FindBy(xpath="//button[normalize-space()='Actions']")
	WebElement btn_Actions;
	
	@FindBy(xpath="//div[text()='Select Entry']")
	WebElement value_SelectEntry;
	
	

	public void clickOnBtnCreatePO() {
		btn_CreatePO.click();
	}

	public void clickOnBtnAddToPO() {
		btn_AddToPO.click();
	}
	
	public void clickOnBtnCreateREQ() {
		btn_CreateREQ.click();
	}
	public void clickOnBtnAddToREQ() {
		btn_AddToREQ.click();
	
}
	
	public void clickOnBtnItemSearch() {
		btn_ItemSearch.click();
	}
	
	public void clickOnBtnClearQty() {
		btn_clearQty.click();
	}
	public void clickOnBtnActions() {
		btn_Actions.click();
}
	
	public void clickOnBtnMaintenence() {
		btn_Maintenance.click();
}
	

	public void clickOnBtnRemoveItem() {
		btn_RemoveItem.click();
}
	
	public void selectOrderGuideFromFavorites(String OG) throws InterruptedException {
		value_SelectEntry.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()='"+OG+"']")).click();
}

public void selectProductOnTileFav(String productName) {
	driver.findElement(By.xpath("//span/b[text()='"+productName+"']")).click();
}
}
//TCM-3046--Created This Page---aurehman--16thOct20204