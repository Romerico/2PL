package com.testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.time.Duration;
import com.base.BaseClass;
import com.pageObjects.LandingPage;


//Testcase Description: Testing the functionality of Join now link appearing at 7 places in the landing page
//Acceptance Criteria: The Application should point every Join now link to 'Sign up' page 

public class TP_TC_001 extends BaseClass {

	@Test(priority=101)
	public void test_SignUp() throws IOException	{
		logger.info("Started TP_TC_001");
		SoftAssert softAssert=new SoftAssert();
		
			LandingPage lp=new LandingPage(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
		
			lp.clickJoinNow();//Clicking on the First Join Now Button on the Landing Page top right
			logger.info("Clicking on First Join Now Button at Top Right");
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			logger.info(driver.getCurrentUrl());
			softAssert.assertTrue(driver.getCurrentUrl().contains("signup"),"Test Failed");
			logger.info("Validated, On the Join Now Page");
			logger.info("Navigating Passed");



			//Going back on the Landing page to search for the second "Join Now" button/link
			lp.twoPlugsClick();
			logger.info("Clicking on Two Plugs Image for going back on the Landing Page");
			
			js.executeScript("window.scrollBy(0,1000)");//Scrolling the page to find the second Join Now Button
			logger.info("Landing Page gets Scroll Down");
			
			lp.joinNowclick2();//Clicking on the Second Join Now Button
			logger.info("Clicking on Second Join Now Button on the Landing Page");
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			logger.info(driver.getCurrentUrl());
			softAssert.assertTrue(driver.getCurrentUrl().contains("signup"),"Test Failed");
			logger.info("Validated, On the Join Now Page");
			logger.info("Navigating Passed");



			lp.twoPlugs2Click(); //Going back on the Landing page to search for the Third "Join Now" button/link
			logger.info("Clicking on Two Plugs Image for going back on the Landing Page");

			js.executeScript("window.scrollBy(0,1000)");//Scrolling the page to find the Third Join Now Button
			logger.info("Landing Page gets Scroll Down");

			lp.nextClick();//Clicking the Next button for the second image
			logger.info("Clicking on Next Button on Image");

			lp.joinNowClick3();//Clicking on the Third Join Now Button
			logger.info("Clicking on Join Now After clicking on Next Button on Image");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			logger.info(driver.getCurrentUrl());
			softAssert.assertTrue(driver.getCurrentUrl().contains("signup"),"Test Failed");
			logger.info("Validated, On the Join Now Page");
			logger.info("Navigating Passed");

			lp.twoPlugs2Click(); //Going back on the Landing page to search for the fourth "Join Now" button/link
			logger.info("Clicking on Two Plugs Image for going back on the Landing Page");

			js.executeScript("window.scrollBy(0,1000)");//Scrolling the page to find the Fourth Join Now Button
			logger.info("Landing Page gets Scroll Down");

			lp.nextClick2Times();//Clicking the Next button 2 times for the third image
			logger.info("Clicking on the Next Button 2 Times");

			lp.joinNowClick4(); //Clicking the Join Now Button on the third Image
			logger.info("Clicking on the Join Now Button near the Third Image after clicking next button 2 times");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			logger.info(driver.getCurrentUrl());
			softAssert.assertTrue(driver.getCurrentUrl().contains("signup"),"Test Failed");
			logger.info("Validated, On the Join Now Page");
			logger.info("Navigating Passed");

			lp.twoPlugs2Click(); //Going back on the Landing page to search for the Fifth "Join Now" button/link
			logger.info("Clicking on Two Plugs Image for going back on the Landing Page");

			js.executeScript("window.scrollBy(0,1000)");//Scrolling the page to find the Fifth Join Now Button
			logger.info("Landing Page gets Scroll Down");

			lp.nextClick3Times(); //Clicking on the Next Button for 3 times for the fourth Image
			logger.info("Clicking on Next Button 3 Times");

			lp.joinNowClick5(); //Clicking on the Join Now button on the Fourth Image
			logger.info("Clicking on Join Now Button near the Fourth Image after Clicking next button 3 times");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			logger.info(driver.getCurrentUrl());
			softAssert.assertTrue(driver.getCurrentUrl().contains("signup"),"Test Failed");
			logger.info("Validated, On the Join Now Page");
			logger.info("Navigating Passed");

			lp.twoPlugs2Click(); //Going back on the Landing page to search for the Sixth "Join Now" button/link
			logger.info("Clicking on Two Plugs Image for going back on the Landing Page");

			js.executeScript("window.scrollBy(0,1000)");//Scrolling the page to find the Sixth Join Now Button
			logger.info("Landing Page gets Scroll Down");

			lp.nextClick4Times(); //Clicking on the Next Button for 4 times for the fifth Image
			logger.info("Clicking on Next Button 4 Times");

			lp.joinNowClick6(); //Clicking on the Join Now button on the Fifth Image
			logger.info("Clicking on Join Now Button near the Fifth Image after Clicking next button 4 times");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			logger.info(driver.getCurrentUrl());
			softAssert.assertTrue(driver.getCurrentUrl().contains("signup"),"Test Failed");
			logger.info("Validated, On the Join Now Page");
			logger.info("Navigating Passed");

			lp.twoPlugs2Click(); //Going back on the Landing page to search for the Seventh "Join Now" button/link
			logger.info("Clicking on Two Plugs Image for going back on the Landing Page");

			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");//Scrolling the page to find the Seventh Join Now Button at the bottom
			logger.info("Landing Page gets Scroll Down to bottom");

			lp.joinNowClick7();//Clicking on the Seventh Join Now Button at the bottom
			logger.info("Clicking on Seventh Join Now Button on the Landing Page");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			logger.info(driver.getCurrentUrl());
			softAssert.assertTrue(driver.getCurrentUrl().contains("signup"),"Test Failed");
			logger.info("Validated, On the Join Now Page");
			logger.info("Navigating Passed");
			
			softAssert.assertAll();
		logger.info("Completed TP_TC_001");

	}
}
