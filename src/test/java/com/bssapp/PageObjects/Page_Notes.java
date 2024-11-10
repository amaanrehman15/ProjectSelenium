package com.bssapp.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Notes extends BasePage{

	
	
	public Page_Notes(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//button[@id='NewRecPanelToolTab']")
	WebElement btn_New;
	
	@FindBy(xpath="//*[text()='Notes']/../..//textarea")
	WebElement txtarea_Notes;
	
	@FindBy(xpath="//*[text()='Note type']/../..//select")
	WebElement dropdown_NoteType;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement btn_Cancel;
	
	@FindBy(xpath="//button[@id='SaveRecPanelToolTab']")
	WebElement btn_Save;
	
	@FindBy(xpath="//*[text()='Notes ID']/../..//input")
	WebElement input_NotesId;
	
	@FindBy(xpath="(//div[@row-index='0'][@aria-rowindex='2'])[2]/div[@aria-colindex='3']")
	public WebElement txt_getAddedNotes;
	
	@FindBy(xpath="//i[contains(text(),'refresh')]")
	WebElement icon_Refresh;
	

	@FindBy(xpath="//select[@id='SearchField']")
	WebElement dropdown_SearchColoumn;

	@FindBy(xpath="//input[@id='SearchValue']")
	WebElement input_SearchText;
	
	@FindBy(xpath="//tr[@id='tb1']")
	public WebElement notesId_row1;
	public By getAddedNotesLocator() {
		 return By.xpath("(//div[@row-index='0'][@aria-rowindex='2'])[2]/div[@aria-colindex='3']");
		
	}
	
	public void clickOnBtnNew() {
		btn_New.click();
	}
	
	public void inputTextareaNotes(String text) {
		txtarea_Notes.click();
		txtarea_Notes.sendKeys(text);
}
	
	public void clickOnBtnCancel() {
		btn_Cancel.click();
}
	
	public void clickOnBtnSave() {
		btn_Save.click();
}
	
	
	public String getTextAddedNotes() {
	return txt_getAddedNotes.getText();
}

	public void clickOnIconRefresh() {
		icon_Refresh.click();
}
	public void selectNoteType(String text) {
		selectByVisibleText(dropdown_NoteType,text);;
}
	
	public String getNotesID() {
		return input_NotesId.getAttribute("value");
}
	
	public void selectNavlist(String text) {
		selectByVisibleText(dropdown_SearchColoumn, text);;
}
	public void inputSearchText(String text) {
		input_SearchText.sendKeys(text);
}
}