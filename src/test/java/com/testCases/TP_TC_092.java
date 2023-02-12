package com.testCases;

import java.io.IOException;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.CreateService;
import com.pageObjects.LoginPage;

// Testcase Description: Checks whether service/need description is accepting max 256 chars
// Acceptance Criteria: This test case to be successful when service/need  description is accepting max 256 chars

public class TP_TC_092 extends BaseClass {
	
	@Test (priority = 9201)
	public void ServiceDescLength() throws InterruptedException, IOException {
		
		logger.info("Started TP_TC_092");
		
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password");

		lp.setUsername(username1);    
		logger.info("providing user name");

		lp.setPassword(password);		
		logger.info("providing password");

		lp.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");

		//create new service
		CreateService createsvc=new CreateService(driver);
		createsvc.clkbtnCreateNew();
		logger.info("Click on Create new button");

		createsvc.clkbtnService();
		logger.info("Click on Service option");

		//enter description field with 250 characters
		logger.info("Entering more than 256 characters to description field");
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(257, useLetters, useNumbers);
		createsvc.txtdescriptionField(generatedString);
		
		logger.info("Random string of 257 characters generated: " +generatedString);
		
		//get length of typed values
		int size = createsvc.gettxtdescription().length();
		logger.info("Size of the description entered: "+size);
		
		//verify that description field accepts value less than or equal to 256 characters
		SoftAssert softAssertion= new SoftAssert();

		if(size<=256)
		{
			logger.info("Test Passed: Service Description is accepting maximum 256 characters ");
			softAssertion.assertTrue(true);
			logger.info("Service Description printed: " +createsvc.gettxtdescription());
		}
		else
		{
			logger.error("Test Failed: Service description is accepting more than 256 characters");
			softAssertion.assertTrue(false);
			captureScreen(driver,"ServiceDescriptionLength");
			logger.info("Service Description printed: " +createsvc.gettxtdescription());
		}
		softAssertion.assertAll();
		
		//Exiting the Create Service page
		createsvc.btnCancelServicePage();
		createsvc.clkbtnExit();
		}
		
	//Check whether Need description is accepting maximum of 256 characters
	@Test (priority = 9202)
	public void NeedDescLength() throws InterruptedException, IOException {
		
		//create new service
		CreateNeed createneed = new CreateNeed(driver);
		createneed.clkbtnCreateNew();
		logger.info("Click on Create new button");

		createneed.clkbtnNeed();
		logger.info("Click on Need option");

		//enter description field with 250 characters
		logger.info("Entering more than 256 characters to description field");
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(257, useLetters, useNumbers);
		createneed.txtdescriptionField(generatedString);
		
		logger.info("Random string of 257 characters generated: " +generatedString);
		
		//get length of typed values
		int size = createneed.gettxtdescription().length();
		logger.info("Size of the description entered: "+size);
		
		//verify that description field accepts value less than or equal to 256 characters
		SoftAssert softAssertion= new SoftAssert();

		if(size<=256)
		{
			logger.info("Test Passed: Need Description is accepting maximum 256 characters ");
			softAssertion.assertTrue(true);
			logger.info("Need Description printed: " +createneed.gettxtdescription());
		}
		else
		{
			logger.error("Test Failed: Need description is accepting more than 256 characters");
			softAssertion.assertTrue(false);
			captureScreen(driver,"NeedDescriptionLength");
			logger.info("Need Description printed: " +createneed.gettxtdescription());
		

			}
		softAssertion.assertAll();
		logger.info("Completed TP_TC_092");
		}

	}



