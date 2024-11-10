package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_OrderGuide extends BasePage {

	public Page_OrderGuide(WebDriver driver) {
		super(driver);
		
	}


	@FindBy(xpath="//button[text()='Select']")
	WebElement btn_Select;
	
	

	public void clickOnSelectButton() {
		btn_Select.click();
	}
	
	
	
	
}
