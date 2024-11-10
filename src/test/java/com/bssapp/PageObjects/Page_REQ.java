package com.bssapp.PageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;
import com.bssapp.TestBase.UserDefinedMethods;

public class Page_REQ extends BasePage{

	
	public Page_REQ(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//input[@id='subject']")
	WebElement input_Subject;


	@FindBy(xpath="//td[text()='Required delivery date	']/..//input")
	public
	WebElement input_RequiredDeliveryDate;

	@FindBy(xpath="//select[@id='approver']")
	WebElement dropdown_Approver;

	@FindBy(xpath="//textarea[@id='Note']")
	WebElement input_internalNote;

	@FindBy(xpath="//select[@id='prodType']")
	public WebElement dropdown_PurchaseType;

	@FindBy(xpath="//select[@id='ishipTo']")
	WebElement dropdown_ShipToLocation;

	@FindBy(xpath="//input[@id='shipto']")
	WebElement input_ShipToAttnName;


	@FindBy(xpath="//select[@id='ibillTo']")
	WebElement dropdown_billToLocation;


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
		input_internalNote.sendKeys(note);

	}

	public void selectPurchaseType(String purchaseType) {

		selectByVisibleText(dropdown_PurchaseType,purchaseType);
	}

	public void selectshipToLocation(String shipToLocation) {
		selectByVisibleText(dropdown_ShipToLocation,shipToLocation);
	}

	public void inputShipToAttnName(String shipToAttn) {
		input_ShipToAttnName.sendKeys(shipToAttn);;
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


public HashMap<String, String> createREQfromFavourites(String Approver) throws InterruptedException {
	BaseClass.fav=new Page_Favorites(driver);
	BaseClass.req=new  Page_REQ(driver);
	BaseClass.home=new Page_Home(driver);
	BaseClass.requisitions=new Page_Requisitions(driver);
    BaseClass.fav.clickOnBtnCreateREQ();
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 15);
	ActionsClass.switchToFrame("_dlgOpenerIframe1");
	String subject="Test REQ Subject "+ActionsClass.getTodaysDatewithTime();
	BaseClass.req.inputSubject(subject);
	BaseClass.req.selectApprover(Approver);
	BaseClass.req.inputRequiredDeliveryDate("t");
	ActionsClass.sendKeys(BaseClass.req.input_RequiredDeliveryDate, Keys.TAB);
	String[] optionsInPurchType={"Select Entry", "Food", "Beverage", "General"};
	String purchaseType="Food";
	UserDefinedMethods.validatePurchaseType(BaseClass.req.dropdown_PurchaseType,optionsInPurchType);
	BaseClass.req.selectPurchaseType(purchaseType);
	BaseClass.req.clickOnDeptLookUp();
	String deptPanelId="14383";
	String deptCode="TCOS";
	BaseClass.req.validatePanelId(BaseClass.req.lookup_DeptPanelId,deptPanelId);
	ActionsClass.switchtoDefaultContent();
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	BaseClass.req.clickonIconDeptCodeFilter();
	BaseClass.req.inputinFilter(deptCode);
	BaseClass.req.clickonApplyFilterbtn();
	WebElement deptElement=driver.findElement(By.xpath("//div[contains(text(),'"+deptCode+"')]"));
	ActionsClass.selectFirstRow(deptElement);
	BaseClass.req.clickonSelectbtn();
	ActionsClass.switchToFrame("_dlgOpenerIframe1");
	ActionsClass.waitForElementInvisible(BaseClass.home.icon_spinLoader, 10);
	BaseClass.req.clickOnOKBtn();
	ActionsClass.waitForAlert(10);
	String getReqAlertText= ActionsClass.getAlertText();
	Thread.sleep(1000);
	ActionsClass.acceptAlert();
	ActionsClass.switchToWindowIndex(1);
	ActionsClass.waitForPageLoad(30);
	String reqNumber=BaseClass.requisitions.getReqNumber();
	System.out.println(reqNumber);
	String reqStatus=BaseClass.requisitions.getReqStatus();
	System.out.println(reqStatus);
	ActionsClass.closeWindowIndex(1);
	HashMap<String,String> m=new HashMap<>();
	m.put("ReqNumber", reqNumber);
	m.put("ReqNumberAlert",getReqAlertText);
	m.put("ReqStatus", reqStatus);
	return m;


}
}
//TCM-3046--Created This Page---aurehman--16thOct20204