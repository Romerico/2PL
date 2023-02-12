package com.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.SignUpPage;

public class TP_TC_015 extends BaseClass {

	//Testcase Description: Testing the functionality by  leaving "Blank" Password column in  Sign Up page
	//Acceptance Criteria: The Application should display error message that "Invalid Password"
	
	@Test(priority = 1501)
	public void signupTest_BlankPassword() throws IOException, InterruptedException {
		logger.info("Started TP_TC_015");
		LandingPage lp = new LandingPage(driver);
		lp.JoinForFree();
		
		//Sign up page
		SignUpPage SignUpPage = new SignUpPage(driver);

		logger.info("providing user name");
		SignUpPage.setUsername(randomestring());

		logger.info("providing email");
		SignUpPage.setEmail(randomemail());

		SignUpPage.clkFinalSignUp();
		logger.info("clicking on signup button to access TwoPlugs main home page");
		
		//declare soft assertion
		SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertTrue(SignUpPage.getErrorMessageBlankPassword().contains("The password field is required"), "Test Failed");

		//Validating the signing up process if password is blank

		softAssertion.assertAll();
		logger.info("Test Passed: Sign up failed due to blank password!");

		logger.info("Completed TP_TC_015");
	}
}



