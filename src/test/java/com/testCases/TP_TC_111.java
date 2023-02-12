package com.testCases;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.ProfilePage;

//Testcase Description: Checks whether an avatar is displaying when a user does not provide a profile picture
//Acceptance Criteria: "This test case to be successful
//1: A green avatar must display for a male user and yellow avatar must display for a female user"

public class TP_TC_111 extends BaseClass {
	
	//Login as a user with default male avatar
	@Test(priority = 11101)
	public void login() throws InterruptedException {
		logger.info("Started TP_TC_111 ");
		driver.get(baseURL);
		logger.info(" Opened URL");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username4);		
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Logged in ");
	}

	@Test (priority=11102)
	public void CheckMaleAvatar() throws IOException, InterruptedException {
		logger.info("Click on Profile");
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		// Validation: Whether Green avatar is displayed for Gender Male and yellow avatar is displayed for female user
		ProfilePage profilepg = new ProfilePage(driver);
		logger.info("Validate whether Green avatar is displayed for Male and yellow avatar is displayed for female user");
		SoftAssert softassertion = new SoftAssert();
		//Checks whether female avatar and gender matches
		if (profilepg.captureGender().contains("Female"))	
		{
			if (profilepg.femaleAvatar()) 
			{
				logger.info(" Yellow avatar for Female user is displayed");
				softassertion.assertTrue(true);
				logger.info("Yellow avatar for Female user is displayed!!");
			}
		}
		//Checks whether Male avatar and gender matches
		else if (profilepg.captureGender().contains("Male")) 
		{
			if (profilepg.maleAvatar())
			{
				logger.info(" Green avatar for Male user is displayed");
				softassertion.assertTrue(true);
				logger.info("Green avatar for Male user is displayed!!");
			}
		}
		//Checks whether Undefined gender displays male avatar as default
		else if (profilepg.captureGender().contains("Undefined")) 	
		{
			if (profilepg.undefinedAvatar())
			{
				logger.info(" Gender is undefined| Default Green Male avatar is displayed");
				softassertion.assertTrue(true);
				logger.info("Green avatar for Undefined user is displayed!!");
			}
		}
		//In case any of the above condition mismatches
		else			
		{
			logger.info(" Gender and Avatar mismatch");
			captureScreen(driver,"CheckAvatar");
			softassertion.assertTrue(false);
			logger.info("Either - User has uploaded Profile Picture (or) Wrong gender and avatar combination!!");	
		}
		
		softassertion.assertAll();
		
		//Logout from user
				logger.info("User logout");
				Actions act1 = new Actions(driver);
				act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();

	}

	//Login as a user with default female avatar
		@Test(priority = 11103)
		public void userfemalelogin() throws InterruptedException {
			LoginPage lp = new LoginPage(driver);
			lp.clickloginlandingbtn();
			logger.info("Providing Login Details");
			lp.setUsername(username5);		
			lp.setPassword(password);
			lp.clickloginbtn1();
			logger.info("Logged in");
		}
	
	@Test (priority=11104)
	public void CheckFemaleAvatar() throws IOException, InterruptedException {
		logger.info("Click on Profile");
		HomePage homepg = new HomePage(driver);
		//homepg.clkHiUser();
		//homepg.clkProfile();
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		// Validation: Whether Green avatar is displayed for Gender Male and yellow avatar is displayed for female user
		ProfilePage profilepg = new ProfilePage(driver);
		logger.info("Validate whether Green avatar is displayed for Male and yellow avatar is displayed for female user");
		SoftAssert softassertion = new SoftAssert();
		//Checks whether female avatar and gender matches
		if (profilepg.captureGender().contains("Female"))	
		{
			if (profilepg.femaleAvatar()) 
			{
				logger.info(" Yellow avatar for Female user is displayed");
				softassertion.assertTrue(true);
				logger.info("Yellow avatar for Female user is displayed!!");
			}
		}
		//Checks whether Male avatar and gender matches
		else if (profilepg.captureGender().contains("Male")) 
		{
			if (profilepg.maleAvatar())
			{
				logger.info(" Green avatar for Male user is displayed");
				softassertion.assertTrue(true);
				logger.info("Green avatar for Male user is displayed!!");
			}
		}
		//Checks whether Undefined gender displays male avatar as default
		else if (profilepg.captureGender().contains("Undefined")) 	
		{
			if (profilepg.undefinedAvatar())
			{
				logger.info(" Gender is undefined| Default Green Male avatar is displayed");
				softassertion.assertTrue(true);
				logger.info("Green avatar for Undefined user is displayed!!");
			}
		}
		//In case any of the above condition mismatches
		else			
		{
			logger.info(" Gender and Avatar mismatch");
			captureScreen(driver,"CheckAvatar");
			softassertion.assertTrue(false);
			logger.info("Either - User has uploaded Profile Picture (or) Wrong gender and avatar combination!!");	
		}
		softassertion.assertAll();
		
		//Logout from user
		logger.info("User logout");
		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

	//Login as a user with default female avatar
	@Test(priority = 11105)
	public void userUndefinedlogin() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username6);		
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Logged in");
	}

@Test (priority=11106)
public void CheckUndefinedAvatar() throws IOException, InterruptedException {
	logger.info("Click on Profile");
	HomePage homepg = new HomePage(driver);
	Actions act = new Actions(driver);
	act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
	// Validation: Whether Green avatar is displayed for Gender Male and yellow avatar is displayed for female user
	ProfilePage profilepg = new ProfilePage(driver);
	logger.info("Validate whether Green avatar is displayed for Male and yellow avatar is displayed for female user");
	SoftAssert softassertion = new SoftAssert();
	//Checks whether female avatar and gender matches
	if (profilepg.captureGender().contains("Female"))	
	{
		if (profilepg.femaleAvatar()) 
		{
			logger.info(" Yellow avatar for Female user is displayed");
			softassertion.assertTrue(true);
			logger.info("Yellow avatar for Female user is displayed!!");
		}
	}
	//Checks whether Male avatar and gender matches
	else if (profilepg.captureGender().contains("Male")) 
	{
		if (profilepg.maleAvatar())
		{
			logger.info(" Green avatar for Male user is displayed");
			softassertion.assertTrue(true);
			logger.info("Green avatar for Male user is displayed!!");
		}
	}
	//Checks whether Undefined gender displays male avatar as default
	else if (profilepg.captureGender().contains("Undefined")) 	
	{
		if (profilepg.undefinedAvatar())
		{
			logger.info(" Gender is undefined| Default Green Male avatar is displayed");
			softassertion.assertTrue(true);
			logger.info("Green avatar for Undefined user is displayed!!");
		}
	}
	//In case any of the above condition mismatches
	else			
	{
		logger.info(" Gender and Avatar mismatch");
		captureScreen(driver,"CheckAvatar");
		softassertion.assertTrue(false);
		logger.info("Either - User has uploaded Profile Picture (or) Wrong gender and avatar combination!!");	
	}
	softassertion.assertAll();
	
	//Logout from user
	logger.info("User logout");
	Actions act1 = new Actions(driver);
	act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	
	logger.info("Completed TP_TC_111");
} 
}
