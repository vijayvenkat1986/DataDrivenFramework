package com.w2a.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class AddCustomerTest extends TestBase
{
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String, String> data) throws InterruptedException
	{
		if(!data.get("Runmode").equals("Y"))
		{
			throw new SkipException("Skipping the test case as the Runmode set to N");
		}
		
		/*driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);	
		driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(LastName);
		driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(PostCode);
		driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();*/
		
		click("addCustBtn_CSS");
		type("firstname_CSS",data.get("firstName"));
		type("lastname_XPATH",data.get("LastName"));
		type("postcode_CSS",data.get("PostCode"));
		click("addBtn_CSS");
			
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		alert.accept();	
		//Reporter.log("Customer successfully added");
	}
}
