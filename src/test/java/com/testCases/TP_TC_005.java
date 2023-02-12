package com.testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;

public class TP_TC_005 extends BaseClass {
	
	/* Testcase Description: Testing the functionality by click on twoPLUGS Logo – Before Login.
	Testing the functionality by click on twoPLUGS Logo – After  Login. */
	
	/* Acceptance Criteria: "By  click on twoPLUGS Logo – Before Login should be navigate to Landing page
	By click on twoPLUGS Logo – After  Login should be navigate to HOME page" */

//	@Test(priority=501)
//	public void testLogoBeforelogin() throws IOException, InterruptedException
//	{
//		logger.info("Started TP_TC_005");
//		LandingPage lp = new LandingPage(driver);
//		SoftAssert softAssertion= new SoftAssert();
//		logger.info("Clicking on twoplugs login");
//		lp.clkimgLogo();
//
//		softAssertion.assertTrue(driver.getCurrentUrl().equals("https://qatest.twoplugs.com/"), "Test Failed");
//
//		softAssertion.assertAll();
//		logger.info("User is redirected to a required page");
//
//		}


	
	@Test(priority=502)
	public void testLogoAfterLogin() throws InterruptedException, IOException
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickloginlandingbtn();
		logger.info("Providing Login Details");
		loginpage.setUsername(username2);
		loginpage.setPassword(password);
		loginpage.clickloginbtn1();
		logger.info("User logged in");

		//Click on twoplugs logo
		loginpage.clkimgLogo();

		SoftAssert softAssertion = new SoftAssert();

		softAssertion.assertTrue(driver.getCurrentUrl().contains("https://qatest.twoplugs.com/home"), "Test Failed");


		softAssertion.assertAll();
		logger.info("User is redirected to a required page");


		logger.info("Completed TP_TC_005");
	}
}


