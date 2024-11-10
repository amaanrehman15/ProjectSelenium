package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Attachments extends BasePage{

	
	public Page_Attachments(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//button[@accesskey='A']")
	WebElement btn_AddImage;
	
	@FindBy(xpath="//button[@id='DeleteAttachment']")
	WebElement btn_Delete;
	
	@FindBy(xpath="//td[text()='Document type']/following-sibling::td//select")
	WebElement dropdown_Documenttype;
	
	@FindBy(xpath="//input[@id='file']")
	WebElement btn_chooseFile;
	
	
	@FindBy(xpath="//input[@id='lblSubmit']")
	WebElement btn_Submit;
	
	@FindBy(xpath="//span[text()='Attachment list']/following-sibling::button")
	WebElement icon_close;
	
	@FindBy(xpath="//input[contains(text(),'OK')]")
	WebElement btn_OK;

	@FindBy(xpath="//a[contains(@onclick,'ViewAttachment')]/u")
	public
	WebElement link_viewAttachment;
	public void clickOnBtnAddImage() {
		btn_AddImage.click();
	}
	
	public void clickOnIconClose() {
		icon_close.click();
	}
	public void selectDocumentType(String documentType) {
		selectByVisibleText(dropdown_Documenttype, documentType);
		
	}
	public void uploadFile(String FileLoc) {
		btn_chooseFile.sendKeys(FileLoc);
	}
	public void clickOnBtnSubmit() {
		btn_Submit.click();
	}
	public void clickOnBtnDelete() {
		btn_Delete.click();
	}
	public void clickOnBtnOK() {
		btn_OK.click();
	}
}
