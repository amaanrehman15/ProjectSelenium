package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_APInvoice extends BasePage {

	public Page_APInvoice(WebDriver driver) {
		super(driver);

	}



	@FindBy(xpath="//select[@id='multipanels']")
	WebElement dropdown_InvoicePanel;

	@FindBy(xpath="(//span[text()='Transmission date']/..)[1]")
	WebElement icon_SortingTransmission;

	@FindBy(xpath="//div[@row-id='0']//div[@aria-colindex='11']")
	WebElement column_Transmission;


	public void selectInvoicePanel(String Panel) {

		selectByVisibleText(dropdown_InvoicePanel, Panel);
	}


	public void clickOnSortingIcon() {

		icon_SortingTransmission.click();
	}


	public String getTransmissionDate() {

		String getTrasmissionDate = column_Transmission.getText();
		return getTrasmissionDate;
	}
}


//2024.01 - TCM-3054 - Create Methods on AP Invoice Page -  ntripathi - 22October2024
