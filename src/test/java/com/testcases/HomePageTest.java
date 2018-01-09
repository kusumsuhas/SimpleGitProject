package com.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.MasterClass;
import com.pages.HomePage;
import com.pages.LeadsPage;
import com.pages.LoginPage;

public class HomePageTest extends MasterClass {

	LoginPage loginPage;
	HomePage homePage;
	LeadsPage leadsPage;
	public static Logger log = Logger.getLogger(HomePageTest.class.getName());
	
	@BeforeTest
	public void inti() {
		initBrowser();
		loginPage = new LoginPage();
		leadsPage = new LeadsPage();
		homePage = loginPage.loginClick(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority=1)
	public void HomePageTitleValidation() {
	Assert.assertEquals(driver.getTitle(), "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM");	
	}
	
	@AfterTest
	public void teardown() {
	driver.quit();	
	}
}
