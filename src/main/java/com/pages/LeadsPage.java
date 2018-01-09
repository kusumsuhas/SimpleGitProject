package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MasterClass;

public class LeadsPage extends MasterClass{

	@FindBy(xpath="//table[@class='lvt small']/tbody")
	WebElement eleTable;
	
	public LeadsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void displayLeads() {
		int noofRows= eleTable.findElements(By.tagName("tr")).size();
		int noofCols = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr")).size();
		System.out.println("Number of Rows are "+noofRows);
		
		////table[@class='lvt small']/tbody/tr[1]/td[2]
		
		String firstXPath = "//table[@class='lvt small']/tbody/tr[";
		String secondXpath = "]/td[";
		String finalXPath="";
		
		for (int i =3; i<=noofRows; i++ ) {
			for (int j=2; j<=8; j++ ) {
				finalXPath=firstXPath+i+secondXpath+j+"]";
				//System.out.println(finalXPath);
				System.out.print(driver.findElement(By.xpath(finalXPath)).getText()+" ! ");
		}
			System.out.println();
	}
}
}
