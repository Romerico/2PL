package com.testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.SignUpPage;

//Testcase Description: Testing the functionality by entering valid data in all columns in Sign Up page
/* Acceptance Criteria: The application should display that it has sent the activation link to the registed mail id */

public class TP_TC_012 extends BaseClass {
	
	@Test(priority = 1201)
	public void signupTest_ValidData() throws IOException, InterruptedException {
		logger.info("Started TP_TC_012");
		LandingPage lp = new LandingPage(driver);
		lp.JoinForFree();

		// Signup page
		SignUpPage SignUpPage = new SignUpPage(driver);
		logger.info("Providing email"); //entering email
		SignUpPage.setUsername(randomemail());

		logger.info("Providing password");  //entering password
		SignUpPage.setPassword(password);

		SignUpPage.clkFinalSignUp();        //clicking sign up button
		logger.info("Click on signup button to access sign up verification page");
		
		SoftAssert softAssertion= new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Validating the signing up process	
		logger.info("Validating page URL - Sign Up verification");
		String url1 = driver.getCurrentUrl();  //getting current url as String

		softAssertion.assertTrue(url1.equals("https://qatest.twoplugs.com/emailsend"),
				"Test Failed, the URL is " + driver.getCurrentUrl());

		softAssertion.assertAll();
		logger.info("Test Passed: Signup successful!");
		
		logger.info("Completed TP_TC_012");
	}
	
}