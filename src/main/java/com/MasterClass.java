package com;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MasterClass {

	public static WebDriver driver;
	public static FileInputStream FIS;
	public Properties prop;
	public static Logger log = Logger.getLogger(MasterClass.class.getName());
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	// Public static

	public void loadProp() {
		prop = new Properties();
		String configFilePath = "E:\\Temp Eclipse Repository\\SimpleGitProject\\src\\main\\java\\com\\config\\config.properties";
		try {
			FIS = new FileInputStream(configFilePath);
			prop.load(FIS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Properties are loaded");
	}

	public void selectBrowser(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"E:\\Trainings\\Selenium\\Drivers\\chromedriver_win32_V2.33\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobar");
			driver = new ChromeDriver(options);
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"E:\\Trainings\\Selenium\\Drivers\\geckodriver-v0.19.0\\geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "c:\\temp\\logs.txt");
			driver = new FirefoxDriver();
		} else if(browser.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "E:\\Trainings\\Selenium\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		log.info(browser+" browser selected");
	}

	public void initBrowser() {
		loadProp();
		String browserName = prop.getProperty("browser");
		selectBrowser(browserName);
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		log.info("Page loaded");
	}

	@BeforeSuite
	public void loadLogProp() {
		String logFilePath="E:\\Temp Eclipse Repository\\SimpleGitProject\\Log4J.properties";
		PropertyConfigurator.configure(logFilePath);
		
		htmlReporter= new ExtentHtmlReporter("E:\\Temp Eclipse Repository\\SimpleGitProject\\test-output\\TigerCRMTestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("SIT Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	@AfterMethod() 
	public void getResult(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test case Passed", ExtentColor.GREEN));
		} else if (result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" TEst case Skipped",ExtentColor.AMBER));
			test.skip(result.getThrowable());
		}
	}
	
	@BeforeMethod()
	public void initExtent(Method method) {
		test= extent.createTest(method.getName());
	}
	
	@AfterTest
	public void tearDownExtent() {
		extent.flush();
	}
	
}
