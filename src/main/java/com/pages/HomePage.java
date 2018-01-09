package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MasterClass;

public class HomePage extends MasterClass {

	@FindBy(linkText="Leads")
	WebElement eleLeadsTab;
	
	@FindBy(linkText="Organizations")
	WebElement eleOrgTab;
	
	@FindBy(linkText="Contacts")
	WebElement eleContactsTab;
	
	@FindBy(linkText="Products")
	WebElement eleProductsTab;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public LeadsPage clickonLeadsTab() {
		eleLeadsTab.click();
		return new LeadsPage();
	}
}
