package com.bssapp.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.bssapp.TestBase.ActionsClass;
import com.bssapp.TestBase.BaseClass;
import com.bssapp.TestBase.UserDefinedMethods;

public class BasePage{

	 public WebDriver driver;
	 public ActionsClass ActionsClass;
	 public  UserDefinedMethods UserDefinedMethods;
	 public Actions action;
	 public BaseClass BaseClass;
	public BasePage(WebDriver driver) {
		
		
		this.driver =driver;
		PageFactory.initElements(driver, this);
		ActionsClass=new ActionsClass(driver);
	    UserDefinedMethods=new UserDefinedMethods(driver);
	    action = new Actions(driver);
	    BaseClass=new BaseClass();
	}	
	
	static public void selectByVisibleText(WebElement element,String text) {
	Select s=new Select(element);
	s.selectByVisibleText(text);
	}
	
	
	
	
}
	
	
	

//2024.01-TCM-3026--Created This Page--aurehman--10/10/2024