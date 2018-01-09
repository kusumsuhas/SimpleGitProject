package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MasterClass;

public class LoginPage extends MasterClass {

	
	@FindBy(name="user_name")
	WebElement eleUserName;
	
	@FindBy(name="user_password")
	WebElement elePassword;
	
	@FindBy(id="submitButton")
	WebElement eleSubmittton;
	

	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage loginClick(String strUserName, String strPassword) {
		eleUserName.sendKeys(strUserName);
		elePassword.sendKeys(strPassword);
		eleSubmittton.click();
		return new HomePage();
	}
	

}
