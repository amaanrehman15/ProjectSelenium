package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_MySetting extends BasePage {

	public Page_MySetting(WebDriver driver) {
		super(driver);
		
	}
	
	
	@FindBy(xpath="//span[text()='Message delivery option']/../..//select")
	WebElement select_DeliveryOption;
	
	@FindBy(xpath="//button[@id='SaveRecPanelToolTab']")
	WebElement btn_Save;
	
	public void selectMessageDeliveryOption(String Text) {
		selectByVisibleText(select_DeliveryOption, Text);
		
	}
	
	public String getMessageDeliveryOption() {
		return select_DeliveryOption.getText();
		
	}
	
	public void clickOnSavebtn() {
		btn_Save.click();
	}
	


}


//2024.01 -  Create Element and Add Method - ntripathi - 6thNovember2024