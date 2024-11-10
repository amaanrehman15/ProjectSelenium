package com.bssapp.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class Page_InvoiceMaintenance extends BasePage {

	public Page_InvoiceMaintenance(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath="//button[@id='NewRecPanelToolTab']")
	WebElement btn_New;

	@FindBy(xpath="//span[text()='Supplier company ID']/../../div/div/input[@class='Textbox form-control form-control-sm']")
	public WebElement input_SupplierCompanyId;

	@FindBy(xpath="(//span[contains(text(),'Supplier company ID')]/..//../div/.//input)[2]")
	WebElement input_SupplierCompanyIdDescription;


	@FindBy(xpath="//span[contains(text(),'Supplier invoice number')]//..//..//input")
	public WebElement input_SupplierInvoiceNumber;

	@FindBy(xpath="//span[contains(text(),'Invoice date')]//..//..//input")
	WebElement input_InvoiceDate;

	@FindBy(xpath="//span[text()='Invoice subtotal amount']/../..//input[@class=\"TextboxR\"]")
	WebElement input_InvoiceSubTotal;

	@FindBy(xpath="//span[contains(text(),'Invoice tax amount')]//..//..//input")
	WebElement input_InvoiceTaxAmount;

	@FindBy(xpath="//li[@id='tab4']")
	WebElement tab_Detail;

	@FindBy(xpath="//button[@id='AddRow0']")
	public WebElement btn_AddRow;

	@FindBy(xpath="//span[contains(text(),'Item #')]//..//..//button")
	public WebElement lookup_Item;

	@FindBy(xpath="//span[text()='Item #' and @aria-colindex='1']/../..//span[@class='ag-icon ag-icon-menu']")
	WebElement icon_Filter;

	@FindBy(xpath="//span[text()='Item #' and contains(@id,'cap')]/../..//input")
	WebElement input_Item;

	@FindBy(xpath="//div[@class='ui-dialog-buttonset']//button[contains(text(),'Select')]")
	WebElement btn_Select;

	@FindBy(xpath="//span[text()='Department'][contains(@id,'cap')]/../..//div//button")
	WebElement lookup_Department;

	@FindBy(xpath="(//span[text()='Dept code']/../..//span[@class='ag-icon ag-icon-menu'])[2]")
	WebElement icon_DeptCodeFilter;


	@FindBy(xpath="//input[@placeholder=\"Filter...\"]")
	public WebElement input_Filter;

	@FindBy(xpath="//button[text()='Apply Filter']")
	WebElement ApplyFilterbtn;

	@FindBy(xpath="(//div[@row-index='0']/div[@aria-colindex='1'])[2]")
	WebElement select_Item;


	@FindBy(xpath="(//span[text()='GL Account'][contains(@id,'cap')]/../..//div//button)[1]")
	WebElement lookup_GLAccount;

	@FindBy(xpath="(//span[text()='GL Account']/../../span/span[@class='ag-icon ag-icon-menu'])[2]")
	WebElement icon_GLAccountFilter;

	@FindBy(xpath="//span[contains(text(),'Invoiced quantity')]//..//..//input")
	WebElement input_InvoiceQuantity;

	@FindBy(xpath="//span[contains(text(),'Invoice unit price')]//..//..//input")
	WebElement input_InvoiceUnitPrice;

	@FindBy(xpath="(//span[text()='Tax amount'])[2]/../../div/div/input")
	WebElement input_TaxAmount;

	@FindBy(xpath="//button[@class= 'ui-button ui-corner-all ui-widget btn btn-sm btn-save'and contains(text(),'Save')]")
	WebElement btn_SaveDetail;

	@FindBy(xpath="//div[@row-index='0']//div[@aria-colindex='2']/span[contains(text(),'TCOS')]")
	public WebElement select_Row;

	@FindBy(xpath="//span[contains(text(),'Delete Row')]")
	WebElement btn_DeleteRow;

	@FindBy(xpath="//button[@id='SaveRecPanelToolTab']")
	WebElement btn_Save;

	@FindBy(xpath="(//button[@id='SubmitAPInvPanelToolTab'])[2]")
	WebElement btn_Submit;

	@FindBy(xpath="//li[@id='tab1']")
	WebElement tab_Invoice;

	@FindBy(xpath="//span[text()='AP invoice number']/../..//input[@class='Disabled form-control form-control-sm pkField']")
	WebElement input_APInvoiceNumber;

	@FindBy(xpath="(//button[text()='Cancel'])[1]")
	WebElement btn_Cancel;

	@FindBy(xpath="//select[@id='multipanels']")
	WebElement dropdown_InvoicePanel;

	@FindBy(xpath="//span[text()='Status']/../../div//select")
	WebElement select_Status;


	@FindBy(xpath="//span[text()='Supplier inv #']/../../span/span[@class='ag-icon ag-icon-menu']")
	public WebElement icon_FilterSupplierInvoice;

	@FindBy(xpath="//span[text()='Delete']")
	public WebElement btn_Delete;
	/*
	@FindBy(xpath="//span[text()='Transmission status']/../../div//select")
	WebElement select_TransmissionStatus;

	@FindBy(xpath="(//span[text()='PO Status']/../../div//select)[2]")
	WebElement select_POStatus;

	@FindBy(xpath="//span[text()='Payment method code']/../../div//select")
	WebElement select_PaymentMethodCode;

	@FindBy(xpath="//span[text()='Receipt overridden status']/../../div//select")
	WebElement select_ReceiptOverridenStatus;

	@FindBy(xpath="//span[text()='Projects invoice']/../..//div/select")
	WebElement select_ProjectInvoice;

	@FindBy(xpath="//span[text()='Cancel payment']/../..//div/select")
	WebElement select_CancelPayment;

	@FindBy(xpath="//span[text()='Home currency']/../..//div//input[1]")
	WebElement input_HomeCurrency;

	@FindBy(xpath="//span[text()='Home currency']/../..//div//input[2]")
	WebElement input_HomeCurrencyDescription;

	@FindBy(xpath="//span[text()='Vendor code']/../..//input")
	WebElement input_VendorCode;

	@FindBy(xpath="//span[text()='ERP vendor site code']/../..//input")
	WebElement input_ERPVendorSiteCode;


	@FindBy(xpath="//span[text()='Remit to address']/../..//input")
	WebElement input_RemitToAddress;


	@FindBy(xpath="//span[text()='Remit to city']/../..//input")
	WebElement input_RemitToCity;


	@FindBy(xpath="//span[text()='Terms']/../..//input[1]")
	WebElement input_Terms;


	@FindBy(xpath="//span[text()='Terms']/../..//input[2]")
	WebElement input_TermsDescription;


	@FindBy(xpath="(//span[text()='Invoice grand total']/../..//input)[1]")
	WebElement input_InvoiceGrandTotal;

	@FindBy(xpath="//span[contains(text(),'Item #')]//..//..//button")
	WebElement lookup_ItemPanelId;

	@FindBy(xpath="//span[text()='Line type']/../../div//select")
	WebElement select_LineType;

	@FindBy(xpath="//span[text()='Variance price']/../../div//input")
	WebElement input_Variance;

	@FindBy(xpath="(//span[text()='Item description']/../../div//input)[1]")
	WebElement input_ItemDescription;

	@FindBy(xpath="(//span[text()='Category ID']/../../div//input)[1]")
	WebElement input_CategoryId;

	@FindBy(xpath="(//span[text()='Category ID']/../../div//input)[2]")
	WebElement input_CategoryIdDescription;

	@FindBy(xpath="(//span[text()='Department']/../../div//input)[1]")
	WebElement input_Department;

	@FindBy(xpath="(//span[text()='Department']/../../div//input)[2]")
	WebElement input_DepartmentDescription;

	@FindBy(xpath="(//(//span[text()='GL Account']/../../div//input)[1]")
	WebElement input_GLAccount;

	@FindBy(xpath="(//span[text()='GL Account']/../../div//input)[2]")
	WebElement input_GLAccounDescription;

	@FindBy(xpath="(//span[text()='Invoice UOM']/../../div//input)[1]")
	WebElement input_InvoiceUOM;

	@FindBy(xpath="(//span[text()='Invoice UOM']/../../div//input)[2]")
	WebElement input_InvoiceUOMDescription;

	@FindBy(xpath="(//span[text()='Recalculate use tax rate']/../../div//input)[1]")
	WebElement checkBox_RecalculateUseTaxRate;

	@FindBy(xpath="(//span[text()='Order UOM']/../../div//input)[1]")
	WebElement input_OrderUOM;


	 */






	public void clickonNewbtn() {

		btn_New.click();
	}


	public void inputSupplierCompanyID(String SupplierCompanyID) {

		input_SupplierCompanyId.sendKeys(SupplierCompanyID);
	}

	public String getSupplierCompanyIdDescription() {

		String SupplierCompanIdDesc= input_SupplierCompanyIdDescription.getAttribute("value");
		return SupplierCompanIdDesc;

	}

	public String getStatus(String CaptionName) {

		Select select = new Select(driver.findElement(By.xpath("//span[text()='"+CaptionName+"']/../../div//select")));
		String defaultItem = select.getFirstSelectedOption().getText();
		return defaultItem;

	}

	public void inputSupplierInvoiceNumber(String SupplierInvoiceNumber) {

		input_SupplierInvoiceNumber.sendKeys(SupplierInvoiceNumber);
	}

	public void inputDate(String Date) {

		input_InvoiceDate.sendKeys(Date);
	}

	public void inputInvoiceSubTotalAmount(String InvoiceSubTotalAmount) {

		input_InvoiceSubTotal.sendKeys(InvoiceSubTotalAmount);
	}

	public void inputInvoicetaxAmount(String InvoiceTaxAmount) {

		input_InvoiceTaxAmount.sendKeys(InvoiceTaxAmount);
	}

	public void clickonDetailTab() {

		tab_Detail.click();
	}

	public void clickonAddRowbtn() {

		btn_AddRow.click();
	}

	public String getPanel() {

		return lookup_Item.getAttribute("panel");
	}

	public boolean isItemEnabled() {
		return input_Item.isEnabled();
	}

	public void clickOnItemFilterIcon() {
		icon_Filter.click();
	}


	public boolean isSupplierCompanyIdEnabled() {

		return input_SupplierCompanyId.isEnabled();
	}

	public boolean isSupplierCompanyIdDescriptionEnabled() {

		return input_SupplierCompanyIdDescription.isEnabled();
	}

	public void clickonlookupItem() {

		lookup_Item.click();
	}

	public void clickonSelectbtn() {

		btn_Select.click();
	}

	public void clickonlookupDepartment() {

		lookup_Department.click();
	}

	public void clickonIconDeptCodeFilter() {

		icon_DeptCodeFilter.click();
	}

	public void inputinFilter(String Value) {
		input_Filter.sendKeys(Value);

	}

	public void clickOnApplyFilterbtn() {
		ApplyFilterbtn.click();
	}

	public void selectItem() {
		select_Item.click();
	}
	public void clickonlookUpGlAccount() {

		lookup_GLAccount.click();

	}

	public void clickonIconGlFilter() {

		icon_GLAccountFilter.click();
	}

	public void inputInvoiceQuantity(String InvoiceQauantity) {
		input_InvoiceQuantity.sendKeys(InvoiceQauantity);
	}

	public void inputTaxAmount(String TaxAmount) {

		input_TaxAmount.sendKeys(TaxAmount);
	}

	public void inputUnitPrice(String InvoiceUnitPrice) {

		input_InvoiceUnitPrice.sendKeys(InvoiceUnitPrice);
	}

	public void inputDetailTaxAmount(String TaxAmount) {

		input_TaxAmount.sendKeys(TaxAmount);
	}


	public void clickOnDetailSavebtn() {

		btn_SaveDetail.click();;
	}


	public void selectRow() {

		select_Row.click();;
	}


	public void clickOnDeleteRow() {

		btn_DeleteRow.click();
	}


	public void clickOnSavebtn() {

		btn_Save.click();
	}


	public void clickOnSubmitbtn() {

		btn_Submit.click();;
	}


	public void clickOnInvoiceTab() {

		tab_Invoice.click();;
	}


	public String getAPInvoiceNumber() {

		String getAPInvoiceNumber = input_APInvoiceNumber.getAttribute("value");
		return getAPInvoiceNumber;
	}


	public void clickOnCancelbtn() {

		btn_Cancel.click();
	}

	public void selectInvoicePanel(String Panel) {

		selectByVisibleText(dropdown_InvoicePanel, Panel);
	}

	public String getInvoiceStatus() {

		String getInvoiceStatus = select_Status.getAttribute("value");
		return getInvoiceStatus;
	}

	public void clickOnFilterSupplierInvoiceIcon() {
		icon_FilterSupplierInvoice.click();
	}

	public void clickOnDeletebtn() {

		btn_Delete.click();
	}
	public  void selectInvoice(String InvoiceNumber) {
		driver.findElement(By.xpath("//div[contains(text(),'"+InvoiceNumber+"')]")).click();
	}


	public  String[] AddDetailRow(String CaptionLineType,String CaptionLineStatus,String CaptionVariancePrice,String Attribute,String Item,String DeptCode,String GLAccount
			,String CaptionItemDescription,String CaptionCategoryID,String CaptionDepartment,String CaptionGLAccount,String CaptionInvoiceUOM,String CaptionOrderUOM,String CaptionRecalculateUseTaxRate,String InvoiceQty,String InvoicePrice,String TaxAmount) throws InterruptedException {

		BaseClass.invoiceMaintenance = new Page_InvoiceMaintenance(driver);
		BaseClass.home =  new Page_Home(driver);
		BaseClass.logger = Logger.getLogger(getClass());
		
		ActionsClass.waitForElementClickable(BaseClass.invoiceMaintenance.btn_AddRow, 10);
		BaseClass.logger.info("Wait for Add Row button Clickbale");
		BaseClass.invoiceMaintenance.clickonAddRowbtn();
		BaseClass.logger.info("Click on Add Row Button");
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 20);
		Thread.sleep(2000);
		String ActPanelId = BaseClass.invoiceMaintenance.getPanel();
		BaseClass.logger.info("Get Panel Id");
		String ActLineType =ActionsClass.getdefaultValuefromdropdown(CaptionLineType);
		BaseClass.logger.info("Get Default Line Type");
		System.out.println(ActLineType);

		String ActLineStatus = BaseClass.invoiceMaintenance.getStatus(CaptionLineStatus);
		BaseClass.logger.info("Get Default Line Status");
		System.out.println(ActLineStatus);


		String ActVariancePrice = ActionsClass.getInputValue(CaptionVariancePrice, Attribute);
		BaseClass.logger.info("Get Default Variance Price");
		System.out.println(ActVariancePrice);

		boolean isItemInputFieldEnabled=BaseClass.invoiceMaintenance.isItemEnabled();
		if(!isItemInputFieldEnabled) {

			Assert.fail("Item Input Field is Enabled it should be disable,Please Validate it!!");
			
		}

		ActionsClass.waitForElementClickable(BaseClass.invoiceMaintenance.lookup_Item, 10);
		BaseClass.logger.info("Wait for Look Up Item");
		BaseClass.invoiceMaintenance.clickonlookupItem();
		BaseClass.logger.info("Click on Item Look Up");
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		BaseClass.invoiceMaintenance.clickOnItemFilterIcon();
		BaseClass.logger.info("Click on Filter Icon");
		BaseClass.invoiceMaintenance.inputinFilter(Item);
		BaseClass.logger.info("Enter Item");
		BaseClass.invoiceMaintenance.clickOnApplyFilterbtn();
		BaseClass.logger.info("Click on Apply Button");
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		BaseClass.invoiceMaintenance.selectItem();
		BaseClass.logger.info("Click on Slect Item");
		BaseClass.invoiceMaintenance.clickonSelectbtn();
		BaseClass.logger.info("Click on Select Button");
		BaseClass.invoiceMaintenance.clickonlookupDepartment();
		BaseClass.logger.info("Click on Look up Department Icon");
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		BaseClass.invoiceMaintenance.clickonIconDeptCodeFilter();
		BaseClass.logger.info("Click on Dept Code Filter Icon");
		BaseClass.invoiceMaintenance.inputinFilter(DeptCode);
		BaseClass.logger.info("Enter Department Code");
		BaseClass.invoiceMaintenance.clickOnApplyFilterbtn();
		BaseClass.logger.info("Click on Apply Filter Button");
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		BaseClass.invoiceMaintenance.selectItem();
		BaseClass.logger.info("Select Item");
		BaseClass.invoiceMaintenance.clickonSelectbtn();
		BaseClass.logger.info("Click on Select Button");
		BaseClass.invoiceMaintenance.clickonlookUpGlAccount();
		BaseClass.logger.info("Click on GL Account look Up");
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		BaseClass.invoiceMaintenance.clickonIconGlFilter();
		BaseClass.logger.info("Click on GL Filter Icon");
		BaseClass.invoiceMaintenance.inputinFilter(GLAccount);
		BaseClass.logger.info("Enter GL Account");
		BaseClass.invoiceMaintenance.clickOnApplyFilterbtn();
		BaseClass.logger.info("Click on Apply Filter Button");
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		BaseClass.invoiceMaintenance.selectItem();
		BaseClass.logger.info("Select Item");
		BaseClass.invoiceMaintenance.clickonSelectbtn();
		BaseClass.logger.info("Click on Select Button");

		String ItemDescription = ActionsClass.getInputValue(CaptionItemDescription , Attribute);
		BaseClass.logger.info("Get Item Description");
		System.out.println(ItemDescription);

		String CategoryID = ActionsClass.getInputValue(CaptionCategoryID , Attribute);
		BaseClass.logger.info("Get Category Id");
		System.out.println(CategoryID);

		String CategoryIDDesc = ActionsClass.getInputValueDescription(CaptionCategoryID, Attribute);
		System.out.println(CategoryIDDesc);
		BaseClass.logger.info("Get Category Id Description");

		String Department = ActionsClass.getInputValue(CaptionDepartment , Attribute);
		System.out.println(Department);
		BaseClass.logger.info("Get Department");

		String DepartmentDesc = ActionsClass.getInputValueDescription(CaptionDepartment , Attribute);
		System.out.println(DepartmentDesc);
		BaseClass.logger.info("Get Department Description");

		String getGLAccount = ActionsClass.getInputValue(CaptionGLAccount , Attribute);
		BaseClass.logger.info("Get GL Account");
		System.out.println(getGLAccount);


		String GLAccountDesc = ActionsClass.getInputValueDescription(CaptionGLAccount , Attribute);
		BaseClass.logger.info("Get GL Account Description");
		System.out.println(GLAccountDesc);

		String InvoiceUOM = ActionsClass.getInputValue(CaptionInvoiceUOM , Attribute);
		BaseClass.logger.info("Get Invoice UOM");
		System.out.println(InvoiceUOM);


		String InvoiceUOMDesc = ActionsClass.getInputValueDescription(CaptionInvoiceUOM , Attribute);
		BaseClass.logger.info("Get Invoice UOM Description");
		System.out.println(InvoiceUOMDesc);

		String OrderUOM = ActionsClass.getInputValue(CaptionOrderUOM , Attribute);
		BaseClass.logger.info("Get Order UOM");
		System.out.println(OrderUOM);

		boolean isCheckBoxChecked = ActionsClass.isElementSelected(CaptionRecalculateUseTaxRate);

		if(!isCheckBoxChecked) {
			Assert.fail("Recalculate use tax rate is not Auto checked");
		}

		BaseClass.invoiceMaintenance.inputInvoiceQuantity(InvoiceQty);
		BaseClass.logger.info("Enter Invoice Quantity");
		BaseClass.invoiceMaintenance.inputUnitPrice(InvoicePrice);
		BaseClass.logger.info("Enter Invoice Price");
		BaseClass.invoiceMaintenance.inputDetailTaxAmount(TaxAmount);
		BaseClass.logger.info("Enter Tax Amount");
		BaseClass.invoiceMaintenance.clickOnDetailSavebtn();
		BaseClass.logger.info("Click on Save Button");
		Thread.sleep(2000);

		String[] value= {ActPanelId,ActLineType,ActLineStatus,ActVariancePrice,ItemDescription,CategoryID,CategoryIDDesc,Department,DepartmentDesc,getGLAccount,GLAccountDesc,InvoiceUOM,InvoiceUOMDesc,OrderUOM};
		System.out.println(value);
		return value;
	}


	public String DeleteDetailRow (String Item) {
		
		BaseClass.invoiceMaintenance = new Page_InvoiceMaintenance(driver);
		BaseClass.home =  new Page_Home(driver);
		BaseClass.logger = Logger.getLogger(getClass());
		
		
		BaseClass.invoiceMaintenance.selectRow();
		BaseClass.logger.info("Select Row");

		ActionsClass.rightClick(BaseClass.invoiceMaintenance.select_Row);
		BaseClass.logger.info("Right Click on Selected Row");
		BaseClass.invoiceMaintenance.clickOnDeleteRow();
		BaseClass.logger.info("Click on Delete Button");
		
		//System.out.println(Row);
		//boolean isRowDisplayed = ActionsClass.iselementDisplayed(Row);
	
		/*
		if(isRowDisplayed) {
			Assert.fail("Detail Row is not deleted,Please Validate it!! ");
		}
		 */
		return Item;
		 
	}
	
}


//2024.01 - Create Element and Method of Page Invoice Maintenance- ntripathi - 11October2024