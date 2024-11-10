package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_POPrintwDetails extends BasePage {

	public Page_POPrintwDetails(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='notesBT']")
	WebElement btn_Notes;
	
	@FindBy(xpath="//input[@id='attachBT']")
	WebElement btn_Attachment;
	
	@FindBy(xpath="//input[@id='partialService']")
	WebElement btn_PartialService;
	
	@FindBy(xpath="closeBT")
	WebElement btn_Close;
	
	@FindBy(xpath="//input[@id='printBT']")
	WebElement btn_Print;
	
	@FindBy(xpath="//u[contains(text(),'Edit Line')]")
	WebElement hyperlink_EditLine;
	
	@FindBy(xpath="//u[contains(text(),'Edit Alloc')]")
	WebElement hyperlink_EditAlloc;
	
	public void clickonNotesbtn() {
		
		btn_Notes.click();
	}
	
	public void clickonAttachmentbtn() {
		
		btn_Attachment.click();
	}
	
	public void clickonPartialService() {
		
		btn_PartialService.click();
	}
	
	public void clickonclosebtn() {
		
		btn_Close.click();
	}
	
	public void printbtn() {
		
		btn_Print.click();
	}
	
	public void clickonHyperlinkEditLine() {
		hyperlink_EditLine.click();
		
	}
	
	public void clickonHyperlinkEditAlloc() {
		
		hyperlink_EditAlloc.click();
		
	}

}


//2024.01 - Create Element and Method of Page PO PrintwDetails- ntripathi - 10October2024