package com.testCases;

import java.io.IOException;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.SignUpPage;

/* Testcase Description: Test the functionality by givng Special characters / 
Character length less than 2 or more than 15 / leaving Blank  in User Name column */

/* Acceptance Criteria: The Application should display error message, when User push "Sign Up" button. */

public class TP_TC_013 extends BaseClass {
	  
	@Test(priority = 1301)
	public void signupTest_Invalidusername() throws IOException, InterruptedException {
		logger.info("Started TP_TC_013");
		LandingPage lp = new LandingPage(driver);
		lp.JoinForFree();
		
		// Sign up page
		SignUpPage SignUpPage = new SignUpPage(driver);
		//generating a random string of length 8 (including special characters)
		int count = 6;
		String generatedstring1 = RandomStringUtils.randomAscii(count);
		
		logger.info("Provide username");
		//entering random string (including special characters) in username column
		SignUpPage.setUsername(generatedstring1+"$#"); 
	
		logger.info("Provide email");
		SignUpPage.setEmail(randomemail());

		logger.info("Provide password");
		SignUpPage.setPassword(password);

		SignUpPage.clkFinalSignUp();
		logger.info("Click on signup button to access TwoPlugs main home page");

		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertTrue(SignUpPage.invalidUsername.isDisplayed(), "Test Failed");

		softAssertion.assertAll();
		logger.info("Test Passed: Signup failed due to Invalid username!");

		logger.info("Completed TP_TC_013");
		
	}
}
