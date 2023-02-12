package com.testCases;

import java.io.IOException;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.SignUpPage;

/* Testcase Criteria: Testing the functionality by Entering only "Symbols and Letters"  from 8-16 characters 
 *  into Password column in  Sign Up page */

/* Acceptance Criteria: The Application should display error message that "Invalid Password" */

public class TP_TC_019 extends BaseClass {

	@Test(priority = 1901)
	public void signupTest_InvalidPassword_OnlyLettersSpecialChars() throws IOException, InterruptedException {
		logger.info("Started TP_TC_019");
		LandingPage lp = new LandingPage(driver);
		lp.JoinForFree();
		
		//Sign up page
		SignUpPage SignUpPage = new SignUpPage(driver);

		//enter randomly generated string in user name field
		logger.info("providing user name");
		SignUpPage.setUsername(randomestring());

		//enter randomly generated string in email field
		logger.info("providing email");
		SignUpPage.setEmail(randomemail());
		
		//generating a string with letters and special characters 
		String chars019 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()"; //initializing string including letters and special characters
		int length019 = (int) (Math.random() * 8 + 8); // generating random number between 8 and 16 inclusive
		String generatedstring019 = RandomStringUtils.random(length019, chars019);
		
		//enter generated string into the password field
		logger.info("providing password");
		SignUpPage.setPassword(generatedstring019); 
		
		//clicking on sign up page after completing the user information
		SignUpPage.clkFinalSignUp();
		logger.info("clicking on signup button to access TwoPlugs main home page");
		
		
		//declare soft assertion
		SoftAssert softAssertion= new SoftAssert();
  
		//declare and initialize string url1 to get current url
		String url1 = driver.getCurrentUrl();       
		//printing password to console
		logger.info("The length of password is " + length019 + " " 
				+ "and the password is " + generatedstring019);
		
		// Validating the signing up process 
		// If sign up is not successful, current url is the same as before

		softAssertion.assertTrue(url1.equals("https://qatest.twoplugs.com/signup"), "Test Failed");
		
		softAssertion.assertAll();
		logger.info("Test Passed: Sign up failed since password contains only letters and special characters!");

		logger.info("Completed TP_TC_019");
	}
}


