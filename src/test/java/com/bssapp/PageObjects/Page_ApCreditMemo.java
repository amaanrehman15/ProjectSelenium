package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bssapp.TestBase.BaseClass;

public class Page_ApCreditMemo extends BasePage {

	

	public Page_ApCreditMemo(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//button[@id='NewRecPanelToolTab']")
	WebElement btn_New;

	@FindBy(xpath="//button[@id='Undelete_APCM_recordPanelToolTab']")
	WebElement btn_UnDelete;

	@FindBy(xpath="//button[@id='CMPrintPanelToolTab']")
	WebElement btn_CMPrintView;

	@FindBy(xpath="//button[@id='APInvoices and Credit memo PrintViewPanelToolTab']")
	WebElement btn_ApPrintView;

	@FindBy(xpath="//button[@id='POPrintViewPanelToolTab']")
	WebElement btn_POPrintView;

	@FindBy(xpath="//button[@id='NotesPanelToolTab']")
	WebElement btn_Notes;

	@FindBy(xpath="//button[@id='AttachmentPanelToolTab']")
	WebElement bttn_Attach;
	
	@FindBy(xpath="//button[@id='InvoiceSendEmailPanelToolTab']")
	WebElement btn_Email;
	
	@FindBy(xpath="//button[@id='View Archon ImagePanelToolTab']")
	WebElement btn_ViewPDF;
	

	@FindBy(xpath="//button[normalize-space()='More']")
	WebElement btn_More;
	
	
	
	public void clickNewBtn() {
		btn_New.click();
	}

	public void clickUnDeleteBtn() {
		btn_UnDelete.click();
	}
	public void clickCMPrintViewBtn() {
		btn_CMPrintView.click();
	}
	public void clickApPrintViewBtn() {
		btn_ApPrintView.click();
	}
	public void clickPOPrintViewBtn() {
		btn_POPrintView.click();
	}
	public void clickNotesBtn() {
		btn_Notes.click();
	}
	public void clickAttachBtn() {
		bttn_Attach.click();
	}
	public void clickEmailBtn() {
		btn_Email.click();
	}
	public void clickViewPDFBtn() {
		btn_ViewPDF.click();
	}
	public void clickMoreBtn() {
		btn_More.click();
	}
	
	
	
	
	
	

	
}
//2024.01---TCM-3026--Created this Base Page ,Methods and Elements--aurehman