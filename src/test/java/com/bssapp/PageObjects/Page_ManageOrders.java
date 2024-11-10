package com.bssapp.PageObjects;
 
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;
 
public class Page_ManageOrders extends BasePage {
 
	public Page_ManageOrders(WebDriver driver) {
		super(driver);
 
	}
	@FindBy(xpath="//button[@id='SubmitPO']")
	WebElement btn_Submit;
 
	@FindBy(xpath="//button[@id='LoadReceive']")
	WebElement btn_Receive;
 
 
	@FindBy(xpath="//button[@id='PrintPO']")
	WebElement btn_PrintwDetails;
 
	@FindBy(xpath="//button[@id='HeaderValue']")
	WebElement btn_HeaderUpdate;
 
	@FindBy(xpath="//button[@id='BulkReceive']")
	WebElement btn_BulkReceive;
 
	@FindBy(xpath="//button[@id='ReportMenuButton']")
	WebElement btn_Reports;
 
	@FindBy(xpath="//button[@id='BatchDelete']")
	WebElement btn_BatchDelete;
	@FindBy(xpath="//button[@id='SearchDocNum']")
	WebElement btn_DocSearch;
 
	@FindBy(xpath="//button[@id='receivingWorksheet']")
	WebElement btn_ReceivingWorksheet;
 
	@FindBy(xpath="//button[@id='corporatePO']")
	WebElement btn_MarkCorporatePO;
	@FindBy(xpath = "//div[@row-index='0']//div[@aria-colindex='3']")
	WebElement get_PONumber;
	@FindBy(xpath="//div[@row-index=0]//div[@aria-colindex='4']")
	WebElement get_POStatus;
	@FindBy(xpath="//span[text()='PO number']/../preceding-sibling::span")
	public WebElement icon_FilterPO;
	@FindBy(xpath="//input[@placeholder=\"Filter...\"]")
	public WebElement input_Filter;
	
	@FindBy(xpath="//button[text()='Apply Filter']")
	WebElement btn_ApplyFilter;
	
	@FindBy(xpath="//i[contains(text(),'refresh')]")
	WebElement icon_Refresh;
 
	@FindBy(xpath="//button[contains(text(),'Actions')]")
	WebElement btn_Actions;
	
	@FindBy(xpath="//span[contains(text(),'Delete PO')]")
	WebElement btn_deletePO;
	
	@FindBy(xpath="//Select[@id='multipanels']")
	public WebElement dropdown_Multipanels;
	
	@FindBy(xpath="//span[text()='Cancel PO']")
	WebElement btn_CancelPO;

	@FindBy(xpath="//button[text()='Info']")
	WebElement btn_Info;
	
	@FindBy(xpath="//span[text()='Edit']")
	WebElement btn_RightClickEdit;
	
	@FindBy(xpath="//span[text()='Add to cart']")
	WebElement btn_AddToCart;
	
	@FindBy(xpath="//span[text()='Copy PO']")
	WebElement btn_CopyPO;

	@FindBy(xpath="//span[text()='PO Recall']")
	WebElement btn_PORecall;
	@FindBy(xpath="//span[text()='Attachments']")
	WebElement btn_Attachments;
	@FindBy(xpath="//img[contains(@onclick,'FPShowNote')]")
	WebElement icon_Notes;

	@FindBy(xpath="//div[@row-index='0']//div[@aria-colindex='1']//img")
	public WebElement icon_AttachedAttachment;
	
	public void clickOnBtnSubmit() {
		btn_Submit.click();
	}
 
	public void clickOnBtnReceive() {
		btn_Receive.click();
	}
	public void clickOnBtnPrintViewDetails() {
		btn_PrintwDetails.click();
	}
	public void clickOnBtnHeaderUpdate() {
		btn_HeaderUpdate.click();
	}
	public void clickOnBtnBulkReceive() {
		btn_BulkReceive.click();
	}
	public void clickOnBtnReports() {
		btn_Reports.click();
	}
	public void clickOnBtnBatchDelete() {
		btn_BatchDelete.click();
	}
	public void clickOnBtnDocSearch() {
		btn_DocSearch.click();
	}
 
	public void clickOnBtnReceivingWorksheet() {
		btn_ReceivingWorksheet.click();
	}
	public void clickOnBtnMarkCorporate() {
		btn_MarkCorporatePO.click();
	}
	public  String getPONumber() {
		String getPONumber = get_PONumber.getText();
		return getPONumber;
	}
 
	public String getPOStatus() {
		String getPOStatus=	get_POStatus.getText();
		return getPOStatus;
	}
	
	public void clickOnIconPONumberFilter() {
 
		icon_FilterPO.click();
	}
 
	public void inputinFilter(String Value) {
		input_Filter.sendKeys(Value);
 
	}
	public void clickOnApplyFilterbtn() {
		btn_ApplyFilter.click();
	}
	public void clickOnRefreshIcon() {
		icon_Refresh.click();
	}
	
	
	public void clickOnBtnActions() {
		btn_Actions.click();
	}
 
	
	public void clickOnbtnDeletePO() {
		btn_deletePO.click();
	}
	
	
	public void clickOnBtnInfo() {
		btn_Info.click();
	}
	
	public void clickOnBtnAttachments() {
		btn_Attachments.click();
	}
	public void clickOnBtnCopyPO() {
		btn_CopyPO.click();
	}
	
	public void clickOnBtnPORecall() {
		btn_PORecall.click();
	}
	public void selectDropdownMultiPanels(String text) {
		
		
		selectByVisibleText(dropdown_Multipanels, text);
		
		
	}
	
	public String validatePOStatus(String getPONumber) throws InterruptedException {
		BaseClass.home=new Page_Home(driver);
		BaseClass.orders=new Page_ManageOrders(driver);
		ActionsClass.waitForElementClickable(BaseClass.home.icon_MainMenuPurchasing, 10);
		BaseClass.home.clickonMainMenuPurchasingIcon();
		ActionsClass.waitForPageLoad(30);
		ActionsClass.waitForElementClickable(BaseClass.purchasing.tile_ManageOrders, 10);
		BaseClass.purchasing.clickOnManageOrdersTile();
		ActionsClass.waitForPageLoad(30);
		Thread.sleep(5000);
		
		//Filter PO
		
		BaseClass.orders.clickOnIconPONumberFilter();
		BaseClass.orders.inputinFilter(getPONumber);
		BaseClass.orders.clickOnApplyFilterbtn();
		Thread.sleep(5000);
		BaseClass.orders.selectPO(getPONumber);

		String getPOStatusAfterReceive = BaseClass.orders.getPOStatus();
		
		return getPOStatusAfterReceive;
		
	}
	public  HashMap<String, String> deletePOFromActions() throws InterruptedException {
        BaseClass.orders=new Page_ManageOrders(driver);
		ActionsClass.switchToWindowIndex(0);
		BaseClass.orders.clickOnBtnActions();
		BaseClass.orders.clickOnbtnDeletePO();
		ActionsClass.waitForAlert(20);
		String actDeleteAlertText = ActionsClass.getAlertText();
		ActionsClass.acceptAlert();
		ActionsClass.waitForAlert(5);
		String actDeleteAlertText1 = ActionsClass.getAlertText();
		ActionsClass.acceptAlert();
		HashMap<String,String> m=new HashMap<>();
		m.put("DeleteReqAlertTextDatabse", actDeleteAlertText);
		m.put("ReqDeletedAlertText",actDeleteAlertText1);
		return m;

	}
	public  void selectPO(String PONumber) {
		driver.findElement(By.xpath("//span[contains(text(),'"+PONumber+"')]")).click();
	}
	public  void clickOnBtnCanelPO() {
		btn_CancelPO.click();
	}
	
	public void clickOnIconAttachedAttachment() {
		icon_AttachedAttachment.click();
	}
	public void clickOnBtnRightClickEdit() {
		btn_RightClickEdit.click();
	}
	
	
	
	public void clickOnIconNotes() {
		icon_Notes.click();
	}
	
	public void clickOnBtnAddtoCart() {
		btn_AddToCart.click();
	}
}
//2024.01-TCM-3026--Created This Page,methods,elements--aurehman--10/10/2024
//2024.02 TCM-3032 - Add Methods and Modified Method Name - ntripathi - 14October2024
//TCM-3081--Added Multiple ORs, Methods---aurehman