package com.bssapp.PageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;
import com.bssapp.TestBase.UserDefinedMethods;

public class Page_PO extends BasePage {

	public Page_PO(WebDriver driver) {
		super(driver);

	}


	@FindBy(xpath="//input[@id='confirmPO']")
	WebElement checkbox_ConfirmingPO;

	@FindBy(xpath="//input[@id='subject']")
	WebElement input_Subject;


	@FindBy(xpath="//td[text()='Required delivery date	']/..//input")
	public
	WebElement input_RequiredDeliveryDate;

	@FindBy(xpath="//select[@id='approver']")
	WebElement dropdown_Approver;

	@FindBy(xpath="//textarea[@id='Note']")
	WebElement input_NoteToSupplier;

	@FindBy(xpath="//select[@id='prodType']")
	public WebElement dropdown_PurchaseType;

	@FindBy(xpath="//input[@id='PartialShip']")
	WebElement checkbox_AllowPartialShip;
	@FindBy(xpath="//select[@id='ShipVia']")
	WebElement dropdown_ShipVia;

	@FindBy(xpath="//select[@id='ishipTo']")
	WebElement dropdown_ShipToLocation;

	@FindBy(xpath="//input[@id='shipto']")
	WebElement input_ShipToAttnName;


	@FindBy(xpath="//select[@id='ibillTo']")
	WebElement dropdown_billToLocation;

	@FindBy(xpath="//input[@id='CostCenter']")
	WebElement input_CostCenter;

	@FindBy(xpath="//input[@id='department']")
	WebElement input_Department;

	@FindBy(xpath="//input[@id='dep_desc']")
	WebElement input_DepartmentDesc;

	@FindBy(xpath="//input[@id='glaccount']")
	WebElement input_GlAccount;

	@FindBy(xpath="//input[@id='gla_desc']")
	WebElement input_GLAccountDesc;

	@FindBy(xpath="//button[@id='okclick']")
	public WebElement btn_OK;

	@FindBy(xpath="//button[@id='Cancelclick']")
	WebElement btn_Cancel;

	@FindBy(xpath="//img[@id='zoom_dep']")
	public WebElement lookup_DeptPanelId;

	@FindBy(xpath="(//span[text()='Dept code']/../..//span[@class='ag-icon ag-icon-menu'])[1]")
	public WebElement icon_DeptCodeFilter;


	@FindBy(xpath="//input[@placeholder=\"Filter...\"]")
	WebElement input_Filter;
	
	@FindBy(xpath="//button[text()='Apply Filter']")
	WebElement ApplyFilterbtn;
	
	@FindBy(xpath="(//div[@row-index='0'])[5]/div")
	public WebElement SelectFirstRow;
	
	@FindBy(xpath="//img[@id='zoom_gl']")
	public WebElement lookup_GLPanelId;
	
	@FindBy(xpath="//button[text()='Select']")
	WebElement btn_Select;


	public void clickConfirmingPOCheckbox() {
		checkbox_ConfirmingPO.click();
	}

	public void inputSubject(String subject) {

		input_Subject.sendKeys(subject);
	}

	public void inputRequiredDeliveryDate(String requiredDate) {
		input_RequiredDeliveryDate.sendKeys(requiredDate);

	}

	public void selectApprover(String approver) {
		selectByVisibleText(dropdown_Approver,approver);

	}

	public void inputNoteToSupplier(String note) {
		input_NoteToSupplier.sendKeys(note);

	}

	public void selectPurchaseType(String purchaseType) {

		selectByVisibleText(dropdown_PurchaseType,purchaseType);
	}



	public void selectshipVia(String shipVia) {
		selectByVisibleText(dropdown_ShipVia,shipVia);
	}

	public void selectshipToLocation(String shipToLocation) {
		selectByVisibleText(dropdown_ShipToLocation,shipToLocation);
	}

	public void inputShipToAttnName(String shipToAttn) {
		input_ShipToAttnName.sendKeys(shipToAttn);;
	}

	public void inputCostCenter(String costCeneter) {
		input_CostCenter.sendKeys(costCeneter);

	}
	public void inputepartment(String departmenet) {
		input_Department.sendKeys(departmenet);

	}
	public void inputGlAccount(String glAccount) {
		input_GlAccount.sendKeys(glAccount);

	}
	public void clickOnOKBtn() {
		btn_OK.click();
	}

	public void clickOnCancelBtn() {
		btn_Cancel.click();
	}
	
	public void clickOnDeptLookUp() {
		
		lookup_DeptPanelId.click();
	}

	public void validatePanelId(WebElement PanelId,String ExpectedPanel) {

		String getActualPanelId = PanelId.getAttribute("panelId");
		Assert.assertEquals(getActualPanelId, ExpectedPanel);
	}	

	public void clickonIconDeptCodeFilter() {

		icon_DeptCodeFilter.click();
	}

	public void inputinFilter(String Value) {
		input_Filter.sendKeys(Value);

	}
	
	public void clickonApplyFilterbtn() {
		
		ApplyFilterbtn.click();
	}
	
public void clickonGLLookUp() {
		
		lookup_GLPanelId.click();
	}

public void clickonSelectbtn() {
	
	btn_Select.click();
}

public HashMap<String, String> createPOAramark(String Approver) throws InterruptedException {
	
	BaseClass.fav=new Page_Favorites(driver);
	BaseClass.PO=new Page_PO(driver);
	BaseClass.home=new Page_Home(driver);
	BaseClass.orders=new Page_ManageOrders(driver);
	ActionsClass.switchToFrameBy("//iframe[contains(@name,'_dlgOpenerIframe')]");
	String subject="Test PO Subject "+ActionsClass.getTodaysDatewithTime();
	BaseClass.PO.inputSubject(subject);
	BaseClass.PO.selectApprover(Approver);
	BaseClass.PO.inputRequiredDeliveryDate("t");
	ActionsClass.sendKeys(BaseClass.PO.input_RequiredDeliveryDate, Keys.TAB);
	String[] optionsInPurchType={"Select Entry", "Food", "Beverage", "General"};
	String purchaseType="Food";
	UserDefinedMethods.validatePurchaseType(BaseClass.PO.dropdown_PurchaseType,optionsInPurchType);
	BaseClass.PO.selectPurchaseType(purchaseType);
	BaseClass.PO.clickOnDeptLookUp();
	String deptPanelId="14383";
	String deptCode="TCOS";
	BaseClass.PO.validatePanelId(BaseClass.PO.lookup_DeptPanelId,deptPanelId);
	ActionsClass.switchtoDefaultContent();
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	BaseClass.PO.clickonIconDeptCodeFilter();
	BaseClass.PO.inputinFilter(deptCode);
	BaseClass.PO.clickonApplyFilterbtn();
	WebElement deptElement=driver.findElement(By.xpath("//div[contains(text(),'"+deptCode+"')]"));
	ActionsClass.selectFirstRow(deptElement);
	BaseClass.PO.clickonSelectbtn();
	ActionsClass.switchToFrameBy("//iframe[contains(@name,'_dlgOpenerIframe')]");
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	BaseClass.PO.clickOnOKBtn();
	ActionsClass.waitForAlert(10);
	String getPOAlertText= ActionsClass.getAlertText();
	Thread.sleep(1000);
	ActionsClass.acceptAlert();
	ActionsClass.switchToWindowIndex(1);
	ActionsClass.waitForPageLoad(30);
	String poNumber=BaseClass.orders.getPONumber();
	System.out.println(poNumber);
	String poStatus=BaseClass.orders.getPOStatus();
	System.out.println(poStatus);
	ActionsClass.closeWindowIndex(1);
	HashMap<String,String> m=new HashMap<>();
	m.put("PONumber", poNumber);
	m.put("PONumberAlert",getPOAlertText);
	m.put("POStatus", poStatus);
	return m;


}



public String createPOFromCatalog(String Subject,String Date,String[] optionsInPurchType,String PurchaseType,String Approver,String DeptPanelId,String DeptCode) throws InterruptedException {
	
	BaseClass.fav=new Page_Favorites(driver);
	BaseClass.PO=new Page_PO(driver);
	BaseClass.home=new Page_Home(driver);
	BaseClass.orders=new Page_ManageOrders(driver);
	ActionsClass.switchToFrame("_dlgOpenerIframe1");
	BaseClass.PO.inputSubject(Subject);
	BaseClass.PO.inputRequiredDeliveryDate(Date);
	UserDefinedMethods.validatePurchaseType(BaseClass.PO.dropdown_PurchaseType,optionsInPurchType);
	BaseClass.PO.selectPurchaseType(PurchaseType);
	BaseClass.PO.selectApprover(Approver);
	BaseClass.PO.validatePanelId(BaseClass.PO.lookup_DeptPanelId,DeptPanelId);
	BaseClass.PO.clickOnDeptLookUp();
	Thread.sleep(1000);

	ActionsClass.switchtoDefaultContent();
	ActionsClass.switchToFrame("TopCatalog");

	BaseClass.PO.clickonIconDeptCodeFilter();
	BaseClass.PO.inputinFilter(DeptCode);

	BaseClass.PO.clickonApplyFilterbtn();
	Thread.sleep(1000);
	ActionsClass.selectFirstRow(BaseClass.PO.SelectFirstRow);
	Thread.sleep(1000);
	BaseClass.PO.clickonSelectbtn();
	ActionsClass.switchToFrame("_dlgOpenerIframe1");
	ActionsClass.waitForElementClickable(BaseClass.PO.btn_OK, 10);
	BaseClass.PO.clickOnOKBtn();

	ActionsClass.waitForAlert(10);
	String getPOAlertText= ActionsClass.getAlertText();
	if(!getPOAlertText.contains("Generated PO Number:")){

		Assert.fail("PO Generation Alert is not Matched,Please Validate it!!");
	}

	ActionsClass.acceptAlert();
	Thread.sleep(1000);
	ActionsClass.switchToWindowIndex(2);
	ActionsClass.waitForPageLoad(30);
	Thread.sleep(10000);
	String  getPONumber = BaseClass.orders.getPONumber();
	System.out.println(getPONumber);
	
return getPONumber ;
	
	
}

public HashMap<String, String> createPOFromSOIAramark(String Approver) throws InterruptedException {
	
	BaseClass.fav=new Page_Favorites(driver);
	BaseClass.PO=new Page_PO(driver);
	BaseClass.home=new Page_Home(driver);
	ActionsClass.switchToFrameBy("//iframe[contains(@name,'_dlgOpenerIframe')]");
	String subject="Test PO Subject "+ActionsClass.getTodaysDatewithTime();
	BaseClass.PO.inputSubject(subject);
	BaseClass.PO.selectApprover(Approver);
	BaseClass.PO.inputRequiredDeliveryDate("t");
	ActionsClass.sendKeys(BaseClass.PO.input_RequiredDeliveryDate, Keys.TAB);
	String[] optionsInPurchType={"Select Entry", "Food", "Beverage", "General"};
	String purchaseType="Food";
	UserDefinedMethods.validatePurchaseType(BaseClass.PO.dropdown_PurchaseType,optionsInPurchType);
	BaseClass.PO.selectPurchaseType(purchaseType);
	BaseClass.PO.clickOnDeptLookUp();
	String deptPanelId="14383";
	String deptCode="TCOS";
	BaseClass.PO.validatePanelId(BaseClass.PO.lookup_DeptPanelId,deptPanelId);
	ActionsClass.switchtoDefaultContent();
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	BaseClass.PO.clickonIconDeptCodeFilter();
	BaseClass.PO.inputinFilter(deptCode);
	BaseClass.PO.clickonApplyFilterbtn();
	WebElement deptElement=driver.findElement(By.xpath("//div[contains(text(),'"+deptCode+"')]"));
	ActionsClass.selectFirstRow(deptElement);
	BaseClass.PO.clickonSelectbtn();
	ActionsClass.switchToFrameBy("//iframe[contains(@name,'_dlgOpenerIframe')]");
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	BaseClass.PO.clickOnOKBtn();
	ActionsClass.waitForAlert(10);
	String getPOAlertText= ActionsClass.getAlertText();
	ActionsClass.acceptAlert();
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	ActionsClass.switchToWindowIndex(2);
	ActionsClass.waitForPageLoad(30);
	String poNumber=BaseClass.orders.getPONumber();
	System.out.println(poNumber);
	String poStatus=BaseClass.orders.getPOStatus();
	System.out.println(poStatus);
	ActionsClass.closeWindowIndex(2);
	ActionsClass.closeWindowIndex(1);
	HashMap<String,String> m=new HashMap<>();
	m.put("PONumber", poNumber);
	m.put("PONumberAlert",getPOAlertText);
	m.put("POStatus", poStatus);
	return m;


}


public String submitPO() throws InterruptedException {
	BaseClass.orders=new Page_ManageOrders(driver);
	BaseClass.orders.clickOnBtnSubmit();
	Thread.sleep(5000);

	String getActPOStatusAfterSubmit = BaseClass.orders.getPOStatus();

	int i=1;
	while(getActPOStatusAfterSubmit.equals("Submitting new PO") && i<=7) {


		BaseClass.orders.clickOnRefreshIcon();
		Thread.sleep(5000);
		getActPOStatusAfterSubmit = BaseClass.orders.getPOStatus();

		i++;
	}
	
	return getActPOStatusAfterSubmit;
}



}








//2024.01-TCM-3026--Created This Page--aurehman,Methods ,Elements--10/10/2024
//2024.02 - TCM-3032 - Add Methods - ntripathi - 14October2024