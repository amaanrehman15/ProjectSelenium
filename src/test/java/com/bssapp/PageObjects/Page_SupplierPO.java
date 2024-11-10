package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;

public class Page_SupplierPO extends BasePage{

	public Page_SupplierPO(WebDriver driver) {
		super(driver);

	}


	@FindBy(xpath="//button[@id='AcceptPO']")
	WebElement btn_AcceptPO;

	@FindBy(xpath="//button[@id='RejectPO']")
	WebElement btn_RejectPO;

	@FindBy(xpath="//select[@id='multipanels']")
	public WebElement dropdown_POPanel;

	@FindBy(xpath="//button[contains(text(),'More')]")
	public WebElement btn_More;
	
	@FindBy(xpath="//button[contains(text(),'History')]")
	public WebElement btn_History;
	//Flip to Invoice in more
	@FindBy(xpath="//span[contains(text(),'Flip to Invoice')]")
	WebElement btn_MoreFliptoInvoice;

	@FindBy(xpath="//span[text()='Buyer PO number']/../..//span[@class='ag-icon ag-icon-menu']")
	WebElement icon_BuyerFilterPO;

	@FindBy(xpath="//button[text()='Flip to Invoice']")
	WebElement btn_FliptoInvoice;

	@FindBy(xpath="//a[text()='Supplier Invoice']")
	public WebElement tab_SupplierInvoice;

	@FindBy(xpath="//span[text()='Supplier invoice #' and contains(@id,'cap')]/../..//input")
	WebElement input_SupplierInvoice;

	@FindBy(xpath="//span[text()='Subject' and contains(@id,'cap')]/../..//input")
	public WebElement input_Subject;

	@FindBy(xpath="//a[contains(text(),'Detail')]")
	WebElement tab_Detail;

	@FindBy(xpath="//div[@row-index='0']/div[@aria-colindex='3']/span[contains(text(),'Catalog')]")
	public WebElement Select_DetailLine;

	@FindBy(xpath="//span[text()='Edit']")
	WebElement btn_RightClickEdit;

	@FindBy(xpath="//span[text()='Invoiced qty']/../../div/div/input")
	public WebElement input_InvoiceQuantity;

	@FindBy(xpath="//span[text()='Invoice unit price']/../../div/div/input")
	WebElement input_InvoiceUnitPrice;

	@FindBy(xpath="//button[@class= 'btn btn-sm btn-save'and contains(text(),'Save')]")
	WebElement btn_SaveDetail;

	@FindBy(xpath="//button[@id='SaveRecPanelToolTab']")
	WebElement btn_Save;

	@FindBy(xpath="//button[@class= 'ui-button ui-corner-all ui-widget btn btn-sm btn-inv-submit1' and @id='SubmitPanelToolTab']")
	WebElement btn_Submit;

	@FindBy(xpath="//span[text()='Status' and contains(@id,'cap')]/../..//select")
	WebElement get_SupplierInvoiceStatus;

	//span[text()='History']

	@FindBy(xpath="//span[text()='History']")
	WebElement get_TitleHistory;


	public void clickOnAcceptPObtn() {

		btn_AcceptPO.click();
	}


	public void clickOnRejectPObtn() {

		btn_RejectPO.click();
	}

	public void selectPOPanel(String PanelName) {

		Select select = new Select(dropdown_POPanel);

		select.selectByVisibleText(PanelName);
	}

	public void clickOnMorebtn() {

		btn_More.click();
	}

	public void clickOnMoreFliptoInvoicebtn() {

		btn_MoreFliptoInvoice.click();
	}

	public void clickOnIconBuyerPOFilter() {
		icon_BuyerFilterPO.click();
	}

	public void clickOnFliptoInvoicebtn() {

		btn_FliptoInvoice.click();
	}

	public void clickOnBtnHistory() {

		btn_History.click();
	}
	public void clickOnSupplierInvoicetab() {
		tab_SupplierInvoice.click();
	}

	public void input(String SupplierInvoiceNumber) {

		input_SupplierInvoice.sendKeys(SupplierInvoiceNumber);
	}

	public String getSupplierInvoiceSubject()
	{
		String Subject = input_Subject.getAttribute("value");
		return Subject;

	}
	public void clickOnDetailtab()
	{
		tab_Detail.click();
	}

	public void selectDetailLine() {
		Select_DetailLine.click();

	}

	public void clickOnRightClickEdit() {

		btn_RightClickEdit.click();
	}

	public void inputInvoiceQuantity(String InvoiceQuantity) {

		ActionsClass.sendKeysText(input_InvoiceQuantity, InvoiceQuantity);
		//input_InvoiceQuantity.sendKeys(InvoiceQuantity);


	}

	public void inputInvoiceUnitPrice(String InvoiceUnitPrice) {

		ActionsClass.sendKeysText(input_InvoiceUnitPrice, InvoiceUnitPrice);
	}

	public void clickOnSaveBtnOnDetailTab() {

		btn_SaveDetail.click();
	}


	public void clickOnSavebtn() {

		btn_Save.click();
	}


	public void clickOnSubmitbtn() {

		btn_Submit.click();

	}

	public String getSupplierInvoiceStatus()
	{
		String Status = get_SupplierInvoiceStatus.getAttribute("value");
		return Status;
	}



	public String acceptPOfromSupplierPO(String SelectPOPanel,String getPONumber ) throws InterruptedException {
		BaseClass.home=new Page_Home(driver);
		BaseClass.supplierPortal=new Page_SupplierPortal(driver);
		BaseClass.supplierPO=new Page_SupplierPO(driver);
		BaseClass.PO=new Page_PO(driver);
		BaseClass.orders=new Page_ManageOrders(driver);
		ActionsClass.waitForElementClickable(BaseClass.home.icon_SupplierPortalMainMenu, 10);
		BaseClass.home.clickonSupplierPortalfromMainMenu();
		BaseClass.supplierPortal.clickOnSupplierPOTile();
		BaseClass.supplierPO.selectPOPanel(SelectPOPanel);
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 20);
		BaseClass.supplierPO.clickOnIconBuyerPOFilter();
		BaseClass.PO.inputinFilter(getPONumber);
		BaseClass.PO.clickonApplyFilterbtn();
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 20);
		BaseClass.orders.selectPO(getPONumber);
		BaseClass.supplierPO.clickOnAcceptPObtn();
		ActionsClass.waitForAlert(20);
		String getAcceptPOAlertText= ActionsClass.getAlertText();
		return getAcceptPOAlertText;
	}


	public String[] createFliptoInvoice(String InvoiceQuantity,String InvoicePrice) throws InterruptedException
	{
		BaseClass.home=new Page_Home(driver);
		BaseClass.supplierPortal=new Page_SupplierPortal(driver);
		BaseClass.supplierPO=new Page_SupplierPO(driver);
		BaseClass.PO=new Page_PO(driver);
		ActionsClass.switchToWindowIndex(1);

		ActionsClass.waitForElementClickable(BaseClass.supplierPO.tab_SupplierInvoice, 20);

		String randNum =  ActionsClass.randomNumbers(8);
		String SupplierInvoiceNo = "SUPPINV" + " "+randNum;
		BaseClass.supplierPO.input(SupplierInvoiceNo);

		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 20);
		String getSubject = BaseClass.supplierPO.getSupplierInvoiceSubject();
		System.out.println(getSubject);

		//Edit Detail Line through Right Click

		BaseClass.supplierPO.clickOnDetailtab();
		BaseClass.supplierPO.selectDetailLine();
		ActionsClass.rightClick(BaseClass.supplierPO.Select_DetailLine);
		BaseClass.supplierPO.clickOnRightClickEdit();

		ActionsClass.waitForElementClickable(BaseClass.supplierPO.input_InvoiceQuantity, 10);
		BaseClass.supplierPO.inputInvoiceQuantity(InvoiceQuantity);
		BaseClass.supplierPO.inputInvoiceUnitPrice(InvoicePrice);
		BaseClass.supplierPO.clickOnSaveBtnOnDetailTab();

		//Save Supplier Invoice
		BaseClass.supplierPO.clickOnSavebtn();
		ActionsClass.waitForAlert(10);
		String getActAlertAfterSaveSupplierInvoice = ActionsClass.getAlertText();
		ActionsClass.acceptAlert();
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 20);

		//Submit Supplier Invoice

		BaseClass.supplierPO.clickOnSubmitbtn();

		ActionsClass.waitForAlert(10);
		String getActAlertAfterSubmitSupplierInvoice = ActionsClass.getAlertText();
		ActionsClass.acceptAlert();
		ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
		BaseClass.supplierPO.clickOnSupplierInvoicetab();
		String getSupplierInvoiceStatus = BaseClass.supplierPO.getSupplierInvoiceStatus();

		String [] createFliptoInvoice = {SupplierInvoiceNo,getSubject,getActAlertAfterSaveSupplierInvoice,getActAlertAfterSubmitSupplierInvoice,getSupplierInvoiceStatus};

		return createFliptoInvoice;
	}
	
	public String getTitleHistory() {
		
		return get_TitleHistory.getText();
		
	}
	

}



//2024.01 - Create Element and Method of Page Header Supplier PO- ntripathi - 10October2024
//2024.02 - Create Method acceptPOfromSupplierPO - ntripathi - 15October2024
//2024.02 - Create Method createFliptoInvoice - ntripathi - 17October2024
//2024.03---Added Methods and Elements-----------aurehman 05/11/2024