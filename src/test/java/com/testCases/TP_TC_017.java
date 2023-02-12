package com.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.SignUpPage;

//Testcase Description: Testing the functionality by Entering only Numbers  "1 - 7  characters"  into Password column in  Sign Up page
//Acceptance Criteria: The Application should display error message that "Invalid Password"

public class TP_TC_017 extends BaseClass {

	@Test(priority = 1701)
	public void signupTest_InvalidPassword_LessThan7Char() throws IOException, InterruptedException {
		logger.info("Started TP_TC_017");
		LandingPage lp = new LandingPage(driver);
		lp.JoinForFree();
		
		// Sign up page
		SignUpPage SignUpPage = new SignUpPage(driver);

		// enter randomly generated string in user name field
		logger.info("providing user name");
		SignUpPage.setUsername(randomestring());

		// enter randomly generated string in email field
		logger.info("providing email");
		SignUpPage.setEmail(randomemail());

		// generating a string with only numbers 1-7
		int count017 = (int) (Math.random() * (10000000));
				
		// enter generated string in the password field
		logger.info("providing password");
		SignUpPage.setPassword(Integer.toString(count017));

		// clicking on sign up page after completing the user information
		SignUpPage.clkFinalSignUp();
		logger.info("clicking on signup button to access TwoPlugs main home page");

		// declare soft assertion
		SoftAssert softAssertion = new SoftAssert();
		
		// declare and initialize string url1 to get current url
		String url1 = driver.getCurrentUrl();
		// printing password to console
		logger.info("The password is " + count017);

		softAssertion.assertTrue(url1.equals("https://qatest.twoplugs.com/signup"), "Test Failed");

		// Validating the signing up process
		// If sign up is not successful, current url is the same as before
	
		softAssertion.assertAll();
		logger.info("Test Passed: Signup failed since password <= 7 numbers");

		logger.info("Completed TP_TC_017");
	}
}
