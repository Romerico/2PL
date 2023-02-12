package com.testCases;

import java.io.IOException;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.SignUpPage;

/* Testcase Description: Testing the functionality by Entering only Numbers/Letters from 8-16 characters  
into Password column in  Sign Up page */

//Acceptance Criteria: The Application should display error message that "Invalid Password"

public class TP_TC_021 extends BaseClass {

	@Test(priority = 2101)
	public void signupTest_InvalidPassword_OnlyLetters() throws IOException, InterruptedException {
		logger.info("Started TP_TC_021");
		LandingPage lp = new LandingPage(driver);
		lp.JoinForFree();
		
		// Sign up page
		SignUpPage SignUpPage = new SignUpPage(driver);

		// enter randomly generated string in user name field
		logger.info("Provide user name");
		SignUpPage.setUsername(randomestring());

		// enter randomly generated string in email field
		logger.info("Provide email");
		SignUpPage.setEmail(randomemail());

		// generating a string with only numbers or letters of length 8-16
		int count021 = (int) (Math.random() * 8 + 8); // generating random number between 8 and 16 inclusive
		boolean useLetters = true; // string containing only letters in this case
		boolean useNumbers = false; // change false to true for generating string with only numbers
		String generatedstring021 = RandomStringUtils.random(count021, useLetters, useNumbers);
		
		// providing a random password of length 8-16 with only numbers/letters
		logger.info("providing password");
		SignUpPage.setPassword(generatedstring021);

		//clicking on sign up page after completing the user information
		SignUpPage.clkFinalSignUp();
		logger.info("clicking on signup button to access TwoPlugs main home page");

		// declare soft assertion
		SoftAssert softAssertion = new SoftAssert();
		
		// declare and initialize string url1 to get current url
		String url1 = driver.getCurrentUrl();
		// printing password to console
		logger.info("The length of password is " + count021 + " " + "and the password is " + generatedstring021);

		// Validating the signing up process if password contains only letters or
		// numbers with length between 8-16
		// in case, sign up is not successful
		softAssertion.assertTrue(url1.equals("https://qatest.twoplugs.com/signup"), "Test Failed");
		
		softAssertion.assertAll();
		logger.info("Test Passed: Signup Failed since Password contains only letters!");
		
	}
	
	@Test(priority=2102)
	public void signupTest_InvalidPassword_OnlyNumbers() throws IOException, InterruptedException {
			
		//Refresh the page
		driver.navigate().refresh();
		
		SignUpPage SignUpPage = new SignUpPage(driver);
		// enter randomly generated string in user name field
				logger.info("Provide user name");
				SignUpPage.setUsername(randomestring());

				// enter randomly generated string in email field
				logger.info("Provide email");
				SignUpPage.setEmail(randomemail());
		
				int count021 = (int) (Math.random() * 100000000 * (10000000 - 1));
				// providing a random password of length 8-16 with only numbers/letters
				logger.info("providing password");
				SignUpPage.setPassword(Integer.toString(count021));
				
				//clicking on sign up page after completing the user information
				SignUpPage.clkFinalSignUp();
				logger.info("clicking on signup button to access TwoPlugs main home page");
				
				String url1 = driver.getCurrentUrl();
				// printing password to console
				logger.info("The password is " + count021);
				SoftAssert softAssertion = new SoftAssert();

				softAssertion.assertTrue(url1.equals("https://qatest.twoplugs.com/signup"), "Test Failed");
				
				
		softAssertion.assertAll();
		logger.info("Test Passed: Signup Failed since Password contains only numbers!");

		logger.info("Completed TP_TC_021");
	}
}
