package com.bssapp.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Cart extends BasePage {

	public Page_Cart(WebDriver driver) {
		super(driver);
		
	}

	
	@FindBy(xpath="//button[@id='GenPO']")
	WebElement btn_CreatePO;
	
	@FindBy(xpath="//button[@id='AddPO']")
	WebElement btn_AddToPO;
	
	
	@FindBy(xpath="//button[@id='GenFav']")
	WebElement btn_AddToOG;
	
	@FindBy(xpath="//button[@id='GenREQ']")
	WebElement btn_CreateREQ;
	
	@FindBy(xpath="//button[@id='AddREQ']")
	WebElement btn_AddToREQ;
	
	@FindBy(xpath="//button[@id='Update']")
	WebElement btn_Update;
	
	@FindBy(xpath="//button[@id='DelItem']")
	WebElement btn_RemoveItem;
	
	@FindBy(xpath="//button[text()='More']")
	WebElement btn_More;
	
	@FindBy(xpath="//input[@id='QUANTITY0']")
	WebElement inpput_Qty;
	
	@FindBy(xpath="//div[@tabindex='-1' and @col-id='21920']/span/b")
	WebElement text_itemNum;
	
	@FindBy(xpath="//div[@tabindex='-1' and @col-id='21677']/span/b")
	WebElement text_productName;
	

	@FindBy(xpath="//div[@tabindex='-1' and @col-id='21675']")
	WebElement text_UOM;
	
	@FindBy(xpath="//div[@tabindex='-1' and @col-id='21670']")
	WebElement text_orderQty;
	
	@FindBy(xpath="//div[@tabindex='-1' and @col-id='21670']")
	WebElement text_unitPrice;

	public void clickOnCreatePOBtn() {
		btn_CreatePO.click();
	}

	public void clickOnAddToPOBtn() {
		btn_AddToPO.click();
	}
	public void clickOnAddToOGBtn() {
		btn_AddToOG.click();
	}
	public void clickOnCreateREQBtn() {
		btn_CreateREQ.click();
	}
	public void clickOnAddToREQBtn() {
		btn_AddToREQ.click();
	}
	public void clickOnUpdateBtn() {
		btn_Update.click();
	}
	public void clickOnBtnRemoveitem() {
		btn_RemoveItem.click();
	}
	public void clickOnMoreBtn() {
		btn_More.click();
	}
	
	public void inputQty(String qty) {
		inpput_Qty.sendKeys(qty);
	}
	
	public void selectItemWithTextCart(String text) {
		driver.findElement(By.xpath("//b[contains(text(),'"+text+"')]")).click();
	}	
	
public String getitemNum() {
	return text_itemNum.getText();
}
public String getPrductName() {
	return text_productName.getText();
}
public String getUOM() {
	return text_UOM.getText();
}
public String getOrderQty() {
	return text_orderQty.getText();
}
public String getunitPrice() {
	return text_unitPrice.getText();
}

}
//2024.01--TCM-Created This Page, Elements, METHODS----------aurehman--10/10/2024