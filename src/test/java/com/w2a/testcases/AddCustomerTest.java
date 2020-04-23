package com.w2a.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class AddCustomerTest extends TestBase
{
	@Test(dataProvider = "getData")
	public void addCustomer(String firstName, String LastName, String PostCode, String alerttext) throws InterruptedException
	{
		
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);	
		driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(LastName);
		driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(PostCode);
		driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
		System.out.println("Button Clicked");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(alerttext));
		
		Thread.sleep(3000);
		alert.accept();		
		
		//Reporter.log("Customer successfully added");
		Assert.fail("Customer not added successfully");
	}
	
	@DataProvider
	public Object[][] getData()
	{
		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];
		

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
	
	

}
