package com.testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import com.base.BaseClass;
import com.pageObjects.LandingPage;

//Testcase Description: Testing the functionality by click on Service/Need features through "LIVE POSTINGS"  Button.
//Acceptance Criteria: The Application should display the Service/Need with a link to their Details page 

public class TP_TC_003 extends BaseClass {

	@Test(priority=301)
	public void testNeed() throws IOException
	{
	logger.info("Started TP_TC_003");
	SoftAssert softAssertion= new SoftAssert();
	LandingPage lp = new LandingPage(driver);
	lp.livePosting();//Clicking on LivePosting button
	
	logger.info("Clicked on Needs");
	lp.ClickNeedsBtn();
	if((driver.getCurrentUrl().contains("https://qatest.twoplugs.com/newsearchserviceneed?country=&state=&city=&sort=date&type=n&q")))
	{
		softAssertion.assertTrue(true);
		logger.info("Test Passed: Needs Page displayed!");
	}
	else
	{
		captureScreen(driver,"needPage");
		// to capture screen on failure and here after driver
		logger.error("Test Failed: Need Page not displayed!");
		softAssertion.assertTrue(false);
	}
	
	logger.info("Clicked on service");
	lp.ClickServicesBtn();
	if((driver.getCurrentUrl().contains("https://qatest.twoplugs.com/newsearchserviceneed?country=&state=&city=&sort=date&type=s&q")))
	{
		softAssertion.assertTrue(true);
		logger.info("Test Passed: Services Page displayed!");
	}
	else
	{
		captureScreen(driver,"searchPage");
		// to capture screen on failure and here after driver
		logger.error("Test Failed: Services Page NOT displayed");
		softAssertion.assertTrue(false);
	}
	softAssertion.assertAll();
	logger.info("Completed TP_TC_003");
	}

}
