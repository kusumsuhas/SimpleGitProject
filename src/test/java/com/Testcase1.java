package com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Testcase1 {

	public WebDriver driver;
	
	@Test
	public void TestMethod1() {
		System.out.println("Test Method is Passed");
	}
	
	@Test
	public void TestMethod2() {
		System.out.println("Test Method 2 is Updated;");
	}	
	
	@Test
	public void OpenBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "E:\\Trainings\\Selenium\\Drivers\\chromedriver_win32_V2.33\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disbale-infobars");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://mvnrepository.com/");
		System.out.println(driver.getTitle());
		driver.close();
	}
}
