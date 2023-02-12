package com.testCases;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;


public class TP_TC_066 extends BaseClass{
	
	//Testcase Description: Testing the functionality of the application by clicking on  "Dropdown Menu Icon under User Menu"

	// Acceptance Criteria: By moving mouse on Hi User tab on Menubar, then the application should display the Dropdown list with "Profile, Setting, Sign Out and Help" features

	@Test(priority=6601)
	public void Login() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_066");
		//Create Object of the Login Page
		LoginPage LoginPage = new LoginPage(driver);
		
		//Click on the Login Button
		LoginPage.clickloginlandingbtn();
		logger.info("Click on LandingPage LoginButton ");
		//Enter UserName
		LoginPage.setUsername(username1);
		logger.info("Enter UserName");
		
		//Enter Password
		LoginPage.setPassword(password);
		logger.info("Enter Password");
		
		//Click on LoginButton
		LoginPage.clickloginbtn1();
		logger.info("Click on Login Button");
		
	}
	@Test(priority=6602)
	public void ClickonUserMenuDropdown() throws IOException, InterruptedException
	{
		//Create Object of the Home Page
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		SoftAssert softassert = new SoftAssert();
		//Click on the Plugs link of the Home page
		act.moveToElement(homepg.actWelcome()).build().perform();
		logger.info("Click on Plugs Link of Home Page ");
		//Verify Profile features is displayed or not 
		if(homepg.actProfile().isDisplayed())
		{
			softassert.assertTrue(true);
			logger.info("Test Passed: Profile Option Displayed Sucessfully");
			
		}
		else
		{ 
			//Capture screen on failure of the test case
			captureScreen(driver,"ClickonUserMenuDropdown");
			logger.error("Test Failed: Profile Option Failed to display : Screen shot taken");
			softassert.assertTrue(false);
		}
		
			//Verify Settings features is displayed or not 
				if(homepg.actSettings().isDisplayed())
				{
					softassert.assertTrue(true);
					logger.info("Test Passed:Settings Option Displayed Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"ClickonUserMenuDropdown");
					logger.error("Test Failed: Settings Option Failed to display : Screen shot taken");
					softassert.assertTrue(false);
				}
				
				//Verify SignOut features is displayed or not 
				if(homepg.actSignout().isDisplayed())
				{
					softassert.assertTrue(true);
					logger.info("Test Passed: SignOut Option Displayed Sucessfully");
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"ClickonUserMenuDropdown");
					logger.error("Test Failed: SignOut Option Failed to display : Screen shot taken");
					softassert.assertTrue(false);
				}
				//Verify Help features is displayed or not 
				if(homepg.actHelp().isDisplayed())
				{
					softassert.assertTrue(true);
					logger.info("Test Passed: Help Option Displayed Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"ClickonUserMenuDropdown");
					logger.error("Test Failed: Help Option Failed to display : Screen shot taken");
					softassert.assertTrue(false);
				}
				
				softassert.assertAll();
				logger.info("Completed TP_TC_066");
	}

}
