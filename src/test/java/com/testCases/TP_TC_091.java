package com.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.CreateService;
import com.pageObjects.LoginPage;

//Testcase Description: Checks whether service/need title is accepting max 50 chars
/* Acceptance Criteria: This test case to be successful when service/need title is accepting max 50 chars */


public class TP_TC_091 extends BaseClass {

	@Test(priority=9101)
	public void ServiceNameLength() throws InterruptedException, IOException {
		
		logger.info("Started TP_TC_091");
		
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password");

		lp.setUsername(username1);    
		logger.info("providing user name");

		lp.setPassword(password);		
		logger.info("providing password");

		lp.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");

		//Go to create service page
		CreateService createsvc=new CreateService(driver);
		createsvc.clkbtnCreateNew();
		logger.info("Click on Create new button");

		createsvc.clkbtnService();
		logger.info("Click on Service option");
		
		//enter max of 50 characters in title field
		CreateService service=new CreateService(driver);
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(51, useLetters, useNumbers);
		service.txtTitleField(generatedString);
		logger.info("Random string of 51 characters generated: " +generatedString);
		
		//get length of typed values
		int size = createsvc.gettxtTitleValue().length();
		logger.info("Size of the service title entered: "+size);
		
		//verify that title field accepts max 50 characters
		SoftAssert softAssertion= new SoftAssert();

		if(size<=50)
		{
		logger.info("Test Passed: Service title is accepting max 50 characters");
		softAssertion.assertTrue(true);
		logger.info("Service title printed: " +createsvc.gettxtTitleValue());
		}
		else
		{
		logger.error("Test Failed: Service title is accepting more than 50 characters");
		captureScreen(driver,"ServiceFieldLength");
		softAssertion.assertTrue(false);	
		logger.info("Service title printed: " +createsvc.gettxtTitleValue());
		}
		softAssertion.assertAll();
		
		//Exiting the Create Service page
		createsvc.btnCancelServicePage();
		createsvc.clkbtnExit();
		}
	
	//Check whether Need title is accepting maximum of 50 characters
		@Test(priority=9102)
		public void NeedNameLength() throws InterruptedException, IOException {
		
		//Refresh the page
		driver.navigate().refresh();
			
		//Go to create need page
		CreateNeed createneed = new CreateNeed(driver);
		createneed.clkbtnCreateNew();
		logger.info("Click on Create new button");

		createneed.clkbtnNeed();
		logger.info("Click on Need option");
		
		//enter max of 50 characters in title field
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(51, useLetters, useNumbers);
		createneed.txtTitleField(generatedString);
		logger.info("Random string of 51 characters generated: " +generatedString);
		
		//get length of typed values
		int size = createneed.gettxtTitleValue().length();
		logger.info("Size of the Need title entered: "+size);
		
		//verify that title field accepts max 50 characters
		SoftAssert softAssertion= new SoftAssert();

		if(size<=50)
		{
		logger.info("Test Passed: Need title is accepting max 50 characters");
		softAssertion.assertTrue(true);
		logger.info("Need title printed: " +createneed.gettxtTitleValue());
		}
		else
		{
		logger.error("Test Failed: Need title is accepting more than 50 characters");
		captureScreen(driver,"NeedFieldLength");
		softAssertion.assertTrue(false);	
		logger.info("Need title printed: " +createneed.gettxtTitleValue());
		}
		softAssertion.assertAll();
		logger.info("Completed TP_TC_091");
		}
	

}


