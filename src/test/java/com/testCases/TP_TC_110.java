package com.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.CreateService;
import com.pageObjects.LoginPage;

//Testcase Description: Checks whether a user is getting a warning message when clicking on cancel button either on Create Service page or create need page
//Acceptance Criteria: "This testcase to be successful
//1:User has to get a warning message about saving their changes"

public class TP_TC_110 extends BaseClass {
	

	@Test(priority=11001)
	public void login() throws InterruptedException {
		//Login to the application
		logger.info("TP_TC_110 Started");
		driver.get(baseURL);
		logger.info(" Opened URL");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Logged in ");
	}
	//Check whether clicking on Cancel button on Create Service Page throws Warning message 
	@Test(priority=11002)
	public void CancelServicePage() throws InterruptedException, IOException 
	{
		logger.info("Click on create new Service");
		CreateService createsvc = new CreateService(driver);
		createsvc.clkbtnCreateNew();
		createsvc.clkbtnService();
		
		logger.info("Fill in Service form with test data");
		createsvc.txtTitleField("Test Title");
		createsvc.txtdescriptionField("This is for testing purposes");
		createsvc.clktxtCategoryField();
		createsvc.SelectdrpdownCategory("Automobile");
	//	createsvc.clktxtsubCategoryField();
		createsvc.selectdrpdownSubCategory("Automobile");
		createsvc.txtPriceField("500");
		createsvc.SilderBarMaxLimit();
		createsvc.refundValidField("15"); 
		
		logger.info("Click on Cancel Button on Service Page");
		createsvc.btnCancelServicePage();
		
		// Validate whether a user is getting a warning message when clicking on cancel button
		logger.info("Check whether a user is getting a warning message when clicking on cancel button");
		
		SoftAssert softAssertion = new SoftAssert();
		
		if(createsvc.txtWarningmsg().contains("If you cancel now your work will be discarded"))
		  {
			logger.warn("Warning displayed: If you cancel now your work will be discarded. ");
			softAssertion.assertTrue(true);
			logger.info("User clicked on Cancel button");
		  }
		else
		  {
			logger.info(" No warning displayed ");
			captureScreen(driver,"CancelServicePage");
			softAssertion.assertTrue(false);
			logger.info("No warning message is displayed");
		  }
		softAssertion.assertAll();
		
		logger.info("Click on exit button");
		createsvc.clkbtnExit();
		
		} 
	//Check whether clicking on Cancel button on Create Need Page throws Warning message
	@Test(priority=11003)
	public void CancelNeedPage() throws InterruptedException, IOException
	{
		logger.info("Click on create new Need");
		CreateNeed createneed = new CreateNeed(driver);
		createneed.clkbtnCreateNew();
		createneed.clkbtnNeed();
		
		logger.info("Fill in Need form with test data");
		createneed.txtTitleField("Test Title");
		createneed.txtdescriptionField("This is for testing purposes");
		createneed.clktxtCategoryField();
		createneed.SelectdrpdownCategory("Business");
	/*	try {
			createneed.clktxtsubCategoryField();
			}
			catch (StaleElementReferenceException e)
			{
				CreateNeed createneed1 = new CreateNeed(driver);
				createneed1.clktxtsubCategoryField();
			}
		*/
		createneed.selectdrpdownSubCategory("Legal");
		createneed.txtPriceField("250");
		
		logger.info("Click on Cancel Button on Need Page");
		createneed.btnCancelNeedPage();
		
		// Validate whether a user is getting a warning message when clicking on cancel button
		logger.info("Check whether a user is getting a warning message when clicking on cancel button");
		SoftAssert softassertion = new SoftAssert();  
		if(createneed.txtWarningmsg().contains("If you cancel now your work will be discarded"))
		  {
			logger.warn("Warning displayed: If you cancel now your work will be discarded");
			softassertion.assertTrue(true);
			logger.info("User clicked on Cancel button");
		  }
		else
		  {
			logger.info("No warning message displayed");
			captureScreen(driver,"CancelNeedPage");
			softassertion.assertTrue(false);
			logger.info("No warning message displayed");
		  }
		softassertion.assertAll();
		logger.info("Click on exit button");
		createneed.clkbtnExit();	
		logger.info("TP_TC_110 Completed");
	}
	
}
	

