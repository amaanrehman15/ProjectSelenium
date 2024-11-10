package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Inbox extends BasePage{

	public Page_Inbox(WebDriver driver) {
		super(driver);
		
	}
	
	
	@FindBy(xpath="//select[@id='multipanels']")
	WebElement select_dropdown;
	
	@FindBy(xpath="(//div[@row-index='0'])[2]/div[@aria-colindex='1']/span")
	public WebElement select_SentMsg;
	
	@FindBy(xpath="//span[contains(text(),'View')]")
	WebElement btn_View;
	
	@FindBy(xpath="//input[@id='subject']")
	WebElement get_Subject;
	
	@FindBy(xpath="//button[@id='okclick']")
	public WebElement btn_OK;
	
	
	public void selectPanel(String Panel) {
		
		selectByVisibleText(select_dropdown, Panel);
	}

	public void selectSentMsg()
	{
		select_SentMsg.click();
	}
	
	public String getReadInboxSubject() {
		return select_SentMsg.getText();
	}
	
	public void clickOnViewbtn() {
		
		btn_View.click();
	}
	
	public String getSubject() {
		return get_Subject.getAttribute("value");
	}
	
	public void clickOnOKbtn() {
		btn_OK.click();
	}
}


//2024.01 -  Create Element and Add Method - ntripathi - 6thNovember2024