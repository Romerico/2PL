package com.testCases;

import java.io.IOException;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.PlugsPage;
import com.pageObjects.ViewNeedPage;

public class TP_TC_046 extends BaseClass{

	/* Testcase Description: "This testcase is to test  whether the price field accepts negative/special characters/alphabets in the price field of Submit your bid popup
	Step 1:Select 'Plugs' tab from twoplugs Home Page
	Step 2:Select Need from Needs Page displayed on the screen 
	Step 3: Enter Negative values/Special Characters/Alphabets on 'SUBMIT YOUR BID' popup > Click Send" */
	
	/*Acceptance Criteria: "The following will happen for the test case to be considered successful
	AC01-The application should display "Error Message" */
	
	// priority 1: Login In to Website	
	@Test(priority=4601)
	public void Login() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_046");
		//Create Object of the Login Page
		LoginPage LoginPage = new LoginPage(driver);
		
		//Click on the Login Button
		LoginPage.clickloginlandingbtn();
		logger.info("Click on LandingPage LoginButton ");
		//Enter UserName
		LoginPage.setUsername(username1);
		logger.info("Enter UserName");
		
		//Enter Password
		LoginPage.setPassword(password);
		logger.info("Enter Password");
		
		//Click on LoginButton
		LoginPage.clickloginbtn1();
		logger.info("Click on Login Button");
		
	}
	@Test(priority=4602)
	public void ClickonPlugs() throws IOException, InterruptedException
	{
		//Create Object of the Home Page
		HomePage HomePage = new HomePage(driver);
		//Click on the Plugs link of the Home page
		HomePage.clkbtnPlugs();
		logger.info("Click on Plugs Link of Home Page ");
		
	}
	@Test(priority=4603)
	public void ClickonNeeds() throws IOException, InterruptedException
	{
		//Create Object of the Plugs Page
		PlugsPage PlugsPage = new PlugsPage(driver);
		//Click on the Needs link
		PlugsPage.ClickNeedsBtn();
		logger.info("Click on Needs Link ");
		
	}
	
	@Test(priority=4604)
	public void ClickonNeedsName() throws IOException, InterruptedException
	{
		//Create Object of the Home Page
		PlugsPage PlugsPage = new PlugsPage(driver);
		//Click on the first entry on Needs Page
		PlugsPage.clkPlugsfirstentry();
		logger.info("Click on Needs Name ");
		
	}
	@Test(priority=4605)
	public void VerifyPriceValue() throws IOException, InterruptedException
	{
		SoftAssert softassert = new SoftAssert();
		//Create Object of the ServiceDetail Page
		ViewNeedPage ViewNeedPage = new ViewNeedPage(driver);
		//Click on the Bid Button
		ViewNeedPage.clickICanDoThisBtn();
		logger.info("Click on I can do this Button ");
		//Switch to Dialogbox
	//	driver.switchTo().activeElement();
		//logger.info("Switch to the Dialog box ");
		
		//Method call for  Enter Negative values
		ViewNeedPage.SubmitvalueBid("-50");
		ViewNeedPage.clickSaleAgreement();
		ViewNeedPage.clickSend();
		logger.info("Submit bid with the Negative Value");
		
		
		//Validate the Error Message -Compare the Error Message with the text
		logger.info("Validation for passing negative price begins");
		if(ViewNeedPage.gettxtPriceNegTest().equals("Price cannot be negative"))
		{
			softassert.assertTrue(true);
			logger.info("Test Passed: Error Message for Negative Price Displayed Sucessfully");
		}
		else
		{ 
			//Capture screen on failure of the test case
			captureScreen(driver,"VerifyPriceValue");
			logger.error("Test Failed: Error Message not display Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		
		//Refresh the page
		driver.navigate().refresh();
		
		//Click again on I can do this button
		ViewNeedPage ViewNeedPage1 = new ViewNeedPage(driver);
		//Click on the Bid Button
		ViewNeedPage1.clickICanDoThisBtn();
		logger.info("Click on I can do this Button ");
		//Enter Alphabets in Price field
		logger.info("Entering the alphabets in price field");
		ViewNeedPage1.SubmitvalueBid("aBcD");
		ViewNeedPage1.clickSaleAgreement();
		ViewNeedPage1.clickSend();
		logger.info("Submit bid with the Alphabetical Value");
				
		//Validate the Error Message -Compare the Error Message with the text
		logger.info("Validation for entering the alphabets in Price field begins");
		if(ViewNeedPage.gettxtPriceNegTest().equals("Price can only be a number"))
		{
			softassert.assertTrue(true);
			logger.info("Test Passed: Error Message for entering alphabets in Price field Displayed Sucessfully");
		}
		else
		{ 
			//Capture screen on failure of the test case
			captureScreen(driver,"VerifyPriceValue");
			logger.error("Test Failed: Error Message not display Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		
		//Refresh the page
				driver.navigate().refresh();
				
				//Click again on I can do this button
				ViewNeedPage ViewNeedPage2 = new ViewNeedPage(driver);
				//Click on the Bid Button
				ViewNeedPage2.clickICanDoThisBtn();
				logger.info("Click on I can do this Button ");
				//Enter Special Characters in Price field
				logger.info("Entering special characters in price field");
				ViewNeedPage2.SubmitvalueBid("@$%&");
				ViewNeedPage2.clickSaleAgreement();
				ViewNeedPage2.clickSend();
				logger.info("Submit bid with the Alphabetical Value");
		
		//Validate the Error Message -Compare the Error Message with the text
		if(ViewNeedPage.gettxtPriceNegTest().equals("Price can only be a number"))
		{
			softassert.assertTrue(true);
			logger.info("Test Passed: Error Message for entering Special characters Displayed Sucessfully");
		}
		else
		{ 
			//Capture screen on failure of the test case
			captureScreen(driver,"VerifyPriceValue");
			logger.error("Test Failed: Error Message not display Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		
		softassert.assertAll();
		logger.info("Completed TP_TC_046");
	}		
		
	
	}
	
	

