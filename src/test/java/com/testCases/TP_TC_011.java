package com.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LandingPage;

/* Testcase Description: Testing the functionality of Footer links - ABOUT, EEDS, TRUST & SAFETY, FAQ, HELP, TERMS and PRIVACY links */
/* Acceptance Criteria: "The application should take the user to targeted page when clicked on the following links:
1. ABOUT Link should navigate to About Page describing more details about Company
2. EEDS link should navigate to Power of Eeds page
3. Trust & Safety link should navigate to Trust Safety page
4. FAQ link should navigate to frequently asked questions page 
5. HELP  link should navigate the User to Support Center topics/Help page
6. TERMS,  Link should navigate to Terms of Use Page
7. PRIVACY  Link should navigate to Privacy Policy section of Terms of use page" */

public class TP_TC_011 extends BaseClass {

	@Test (priority=1101)
	public void BottomAboutTest() throws InterruptedException, IOException
	{
		logger.info("Started TP_TC_011");
		LandingPage lp = new LandingPage(driver);
		SoftAssert softAssertion= new SoftAssert();
		lp.trustsLink();           //Clicking "Trust" button
		String url1 = driver.getCurrentUrl();

		softAssertion.assertTrue(url1.contains("https://qatest.twoplugs.com/trustsafety"), "Test Failed");
		logger.info("Test Passed: Trust page opened!");

		driver.navigate().back();
		driver.navigate().refresh();

		lp.eedLink();                  // clicking "Eeds" button
		logger.info(driver.getCurrentUrl());
		String url2 = driver.getCurrentUrl();

		softAssertion.assertTrue(url2.contains("https://qatest.twoplugs.com/powerofeeds"), "Test Failed");
		logger.info("Test Passed: The power of eeds page displayed!");
		
		driver.navigate().back();
		driver.navigate().refresh();
		
		lp.aboutLink();  //Clicking "About" button 
		String url3 = driver.getCurrentUrl();

		softAssertion.assertTrue(url3.contains("https://qatest.twoplugs.com/about"), "Test Failed");
		logger.info("Test Passed: About page displayed");
		
		driver.navigate().back();
		driver.navigate().refresh();
		
		lp.helpLink();          //Clicking "Help" button
		String url4 = driver.getCurrentUrl();

		softAssertion.assertTrue(url4.contains("https://qatest.twoplugs.com/help"), "Test Failed");
		logger.info("Test Passed: Help page displayed!");

		driver.navigate().back();
		driver.navigate().refresh();
		
		lp.termsLink();            //Clicking "Terms" button
		String url5 = driver.getCurrentUrl();

		softAssertion.assertTrue(url5.contains("https://qatest.twoplugs.com/terms"), "Test Failed");
		logger.info("Test Passed: Terms of Use page displayed!");

		driver.navigate().back();
		driver.navigate().refresh();
		
		lp.privacyLink();         //Clicking "Privacy" button
		String url6 = driver.getCurrentUrl();

		softAssertion.assertTrue(url6.contains("https://qatest.twoplugs.com/terms#privacy"), "Test Failed");
		logger.info("Test Passed: Privacy page displayed!");
		
		driver.navigate().back();
		driver.navigate().refresh();
		
		lp.faqLink();       //Clicking "Frequently Asked Question" button
		String url7 = driver.getCurrentUrl();

		softAssertion.assertTrue(url7.contains("https://qatest.twoplugs.com/helparticles"), "Test Failed");
		logger.info("Test Passed: frequentlyAskedQues page");

		driver.navigate().back();
		driver.navigate().refresh();

		logger.info(driver.getCurrentUrl());

		lp.JoinForFree();    //Clicking "JoinForFree" button
		String url8 = driver.getCurrentUrl();

		softAssertion.assertTrue(url8.contains("https://qatest.twoplugs.com/signup"), "Test Failed");
		logger.info("Test Passed: JoinForFree page displayed!");

		softAssertion.assertAll();
		logger.info("Completed TP_TC_011");
	}


}
