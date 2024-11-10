package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Page_LogIn extends BasePage{

	public Page_LogIn(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath="//input[@id='auth_dlg_uid']")
	WebElement input_LogInId;

	@FindBy(xpath="//input[@id='auth_dlg_pw']")
	WebElement input_Password;

	@FindBy(xpath="//input[@id='auth_dlg_sid']")
	WebElement input_SubscriberId;

	@FindBy(xpath="//button[@id='submitLogin']")
	WebElement btn_LogIn;

	@FindBy(xpath="//select[@id='langIDSel']")
	WebElement dropdown_Language;
	@FindBy(xpath="//*[@id = 'loginAsID']")
	WebElement input_LogInAs;

	@FindBy(xpath="//span[@id='forgetPassWordCaption']")
	WebElement hyperlink_ForgotPwd;

	@FindBy(xpath="//span[@id='securityPasswordCaption']")
	WebElement hyperlink_ReactivateAccount;



	public void inputLogInId(String Username) {

		input_LogInId.sendKeys(Username);

	}

	public void inputPassword(String password) {
		input_Password.sendKeys(password);
	}

	public void inputSubscriberId(String SubId) {

		input_SubscriberId.sendKeys(SubId);

	}

	public void inputLogInAs(String LogInAs) {

		input_LogInAs.sendKeys(LogInAs);
	}

	public void clickOnLogInBtn() {

		btn_LogIn.click();
	}

	public void clickOForgotPaswordLink() {
		hyperlink_ForgotPwd.click();
	}


	public void clickOnReactivateAccountLink() {
		hyperlink_ReactivateAccount.click();
	}


	public void selectLanguage(String language) {
		selectByVisibleText(dropdown_Language,language);
	}


}
//2024.02--automation02---Created This Page,Elements,methods---aurehman---10/10/2024