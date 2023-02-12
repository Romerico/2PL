package com.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.SignUpPage;

public class TP_TC_014 extends BaseClass {
	
	//test the functionality by entering invalid email
	@Test(priority = 1401)
	public void signupTest_InvalidEmail() throws IOException, InterruptedException {
		logger.info("Started TP_TC_014");
		LandingPage lp = new LandingPage(driver);
		lp.JoinForFree();
				
		// Signup page
		SignUpPage SignUpPage = new SignUpPage(driver);
		logger.info("Provide username");
		SignUpPage.setUsername(randomestring());

		logger.info("Provide email id");
		SignUpPage.setEmail(randomestring());

		logger.info("Provide password");
		SignUpPage.setPassword(password);

		SignUpPage.clkFinalSignUp();
		logger.info("clicking on signup button to access TwoPlugs main home page");
		
		SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertTrue(SignUpPage.invalidEmailIsDisplayed(), "Test Failed");

		softAssertion.assertAll();
		logger.info("Test Passed: Signup failed due to Invalid Email id!");

		logger.info("Completed TP_TC_014");

}
}
