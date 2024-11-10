package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_SupplierInvoice extends BasePage {

	public Page_SupplierInvoice(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath="//button[@id='NewRecPanelToolTab']")
	WebElement btn_New;

	@FindBy(xpath="//span[text()='Supplier invoice #']/../following-sibling::div//input")
	WebElement input_SupplierInvoiceNumber;

	@FindBy(xpath="//span[text()='Invoice Date']/../..//input")
	WebElement input_InvoiceDate;

	@FindBy(xpath="//span[text()='Trx currency']/../..//input")
	WebElement input_TrxCurrency;

	@FindBy(xpath="//span[text()='Buyer company ID']/../..//input[not(contains(@id,'Desc'))]")
	WebElement input_BuyerCompanyId;

	@FindBy(xpath="//span[text()='Buyer company ID']/../..//input[contains(@id,'Desc')]")
	WebElement get_BuyerCompanyIdDesc;

	@FindBy(xpath="//a[text()='Detail']")
	WebElement tab_Detail;

	@FindBy(xpath="//button[@id='AddRow0']")
	public WebElement btn_AddRow;

	@FindBy(xpath="//span[contains(text(),'Line type')][contains(@id,'cap')]//..//..//select")
	public WebElement dropDown_LineType;

	@FindBy(xpath="//span[text()='Item #' and contains(@id,'cap')]/../..//input")
	WebElement input_Item;
	
	@FindBy(xpath="//span[text()='Item #' and contains(@id,'cap')]/../..//button")
	WebElement lookUp_Item;

	@FindBy(xpath="//span[text()='Item #' and @aria-colindex='1']/../..//span[@class='ag-icon ag-icon-menu']")
	WebElement icon_Filter;

	@FindBy(xpath="//input[@placeholder=\"Filter...\"]")
	WebElement input_Filter;

	@FindBy(xpath="//button[text()='Apply Filter']")
	WebElement ApplyFilterbtn;

	@FindBy(xpath="(//div[@row-index='1']/div[@aria-colindex='1'])[2]")
	WebElement select_Item;
	
	@FindBy(xpath="//span[text()='Invoiced qty']/../..//input")
	WebElement input_InvoiceQuantity;


	@FindBy(xpath="//button[contains(text(),'Select')]")
	WebElement btn_Select;


	@FindBy(xpath="//button[@class= 'ui-button ui-corner-all ui-widget btn btn-sm btn-save'and contains(text(),'Save')]")
	WebElement btn_SaveDetail;


	@FindBy(xpath="//button[@id='SaveRecPanelToolTab']")
	WebElement btn_Save;

	@FindBy(xpath="//button[@id='SubmitPanelToolTab'][contains(@class,'btn-inv-submit1')]")
	WebElement btn_Submit;


	@FindBy(xpath="//button[@class='ui-button ui-corner-all ui-widget btn btn-sm font-weight-bold' and contains(text(),'Cancel')]")
	WebElement btn_Cancel;

	@FindBy(xpath="//select[@id='multipanels']")
	WebElement dropdown_InvoicePanel;
	
	@FindBy(xpath="//span[text()='Supplier invoice #']/../..//span[@class='ag-icon ag-icon-menu']")
	public WebElement icon_SupplierInvoiceFilter;

	@FindBy(xpath="//div[@row-index='0']/div[@aria-colindex='3']")
	WebElement get_InvoiceStatus;



	public void clickOnNewbtn() {
		btn_New.click();
	}

	public void inputSupplierInvoiceNumber(String SupplierInvoieNumber) {
		input_SupplierInvoiceNumber.sendKeys(SupplierInvoieNumber);;
	}

	public void inputInvoiceDate(String Date) {
		input_InvoiceDate.sendKeys(Date);
	}
	
	public void clickOnDate() {
		input_InvoiceDate.click();
	}

	public void inputTrxCurrency(String TrxCurrency) {
		input_TrxCurrency.sendKeys(TrxCurrency);
	}


	public void inputBuyerCompanyId(String BuyerCompanyId) {
		input_BuyerCompanyId.sendKeys(BuyerCompanyId);
	}


	public String getCompanyId() {
		return input_BuyerCompanyId.getAttribute("value");
	}


	public String getCompanyIdDesc() {
		return get_BuyerCompanyIdDesc.getAttribute("value");
	}

	public void clickOnDetailTab() {
		tab_Detail.click();
	}


	public void clickOnAddRowbtn() {
		btn_AddRow.click();
	}


	public void selectLineType(String LineType) {
		selectByVisibleText(dropDown_LineType, LineType);
	}
	
	public boolean isItemEnabled() {
		return input_Item.isEnabled();
	}

	public String getItemPanel() {
		return lookUp_Item.getAttribute("panelId");
	}

	public void clickOnlookupItem() {
		lookUp_Item.click();
	}


	public void clickOnItemFilterIcon() {
		icon_Filter.click();
	}



	public void inputFilter(String Item) {
		input_Filter.sendKeys(Item);
	}


	public void clickOnApplyFilterbtn() {
		ApplyFilterbtn.click();
	}

	public void selectItem() {
		select_Item.click();
	}
	
	public void inputInvoiceQuantity(String InvoiceQuantity) {
		input_InvoiceQuantity.sendKeys(InvoiceQuantity);
	}

	public void clickOnSelectbtn() {
		btn_Select.click();
	}

	public void clickOnSaveDetailbtn() {
		btn_SaveDetail.click();
	}

	public void clickOnSavebtn() {
		btn_Save.click();
	}

	public void clickOnSubmitbtn() {
		btn_Submit.click();
	}

	public void clickOnCancelbtn() {
		btn_Cancel.click();
	}

	public void selectInvoicePanel(String InvoicePanel) {
		selectByVisibleText(dropdown_InvoicePanel, InvoicePanel);
	}
	
	public void clickOnSupplierInvoiceFilterIcon() {
		icon_SupplierInvoiceFilter.click();
	}

	public String  getInvoiceStatus() {
		return get_InvoiceStatus.getText();
	}


}





//2024.01 - TCM-3050 - Add Page Factory and its Method - ntripathi - 18October2024
