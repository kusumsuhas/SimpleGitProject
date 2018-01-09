package com.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.MasterClass;
import com.pages.HomePage;
import com.pages.LeadsPage;
import com.pages.LoginPage;

public class LeadsPageTest extends MasterClass {

	LoginPage loginPage;
	HomePage homePage;
	LeadsPage leadsPage;
	
	public static Logger log = Logger.getLogger(LeadsPageTest.class.getName());
	
	@BeforeTest
	public void init() {
		initBrowser();
		leadsPage = new LeadsPage();
		loginPage = new LoginPage();
		homePage = loginPage.loginClick(prop.getProperty("username"), prop.getProperty("password"));
		log.info("LeadsPageTestcase - Loggged in successfully");
		leadsPage = homePage.clickonLeadsTab();
		log.info("LeadsPageTest - Navigated to LeadsPage");
	}
	
	@Test
	public void DisplayLeads() {
		log.info("Displaying Leads information: ");
		log.info(" ----------------------------------- ");
		leadsPage.displayLeads();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		log.info("Browser is closed");
	}
}
