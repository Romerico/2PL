package com.testCases;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;

// Testcase Description: Checks avatar is updated in following & followers
//Acceptance Criteria: This test case to be successful when  avatar has to be updated in following, followers
public class TP_TC_112 extends BaseClass {

	//User having avatars in following and followers
	@Test(priority = 11201)
	public void login() throws InterruptedException {
		logger.info("Started TP_TC_112");
		driver.get(baseURL);
		logger.info("Opened URL");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Logged in ");
	}

	// Check avatar is updated - Following & followers tab	
	@Test(priority = 11202)
	public void chkAvatarUpdation() throws InterruptedException, IOException {
		logger.info("Click on Profile ");
		HomePage homepg = new HomePage(driver);
		SoftAssert softassertion = new SoftAssert();
		// Check avatar is displayed in 'FOLLOWING' tab
		//Check whether the user is following any other user or not
		//if no user, then no avatar is displayed and screenshot is captured
		if(homepg.gettxtfollowingNo().contains("0"))
		{
			logger.info("Not following any user");
			softassertion.assertTrue(true);
			captureScreen(driver, "NotFollowing");
			logger.info("User is not following any other user!!");
		}
		//if user is following other users, no. of avatars of users following are displayed
		else 
		{
			logger.info("Avatar of Users I follow");
			softassertion.assertTrue(true);
			homepg.clkFollowingNos();
			logger.info("Avatars of users I follow displayed: " + homepg.getavatarfollowingsize());
			logger.info("Avatar is updated in 'following' tab");
			// Browse to previous page
			driver.navigate().back();
		}
		
		//check avatar is displayed in 'FOLLOWER' tab
		//Check whether the user is having any follower or not
		//If there are no followers, capture screenshot
		if(homepg.gettxtfollowerNo().contains("0"))
		{
			logger.info("No followers for this user");
			softassertion.assertTrue(true);
			captureScreen(driver, "NoFollowers");
			logger.info("User has no followers!!");
		}
		else
		{
			//If there are followers, no. of avatars are captured
			logger.info("Avatar of my followers");
			softassertion.assertTrue(true);
			homepg.clkFollowerNos();
			logger.info("Avatars of user who follow me: " +homepg.getavatarfollowersize());
			
			//Browse to previous page
			driver.navigate().back();
		}
		
		softassertion.assertAll();
		
		//Logout from the user
		logger.info("User logout");
		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

	//User having No followers or no following
	@Test(priority = 11203)
	public void login_nouser() throws InterruptedException {
		logger.info("Test for user who is having no followers or following!!");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username3);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Logged in ");
	}

	// Check avatar is updated - Following & followers tab	
	@Test(priority = 11204)
	public void chkAvatarUpdation_Nouser() throws InterruptedException, IOException {
		logger.info("Click on Profile ");
		HomePage homepg = new HomePage(driver);
		SoftAssert softassertion = new SoftAssert();
		// Check avatar is displayed in 'FOLLOWING' tab
		//Check whether the user is following any other user or not
		//if no user, then no avatar is displayed and screenshot is captured
		if(homepg.gettxtfollowingNo().contains("0"))
		{
			logger.info("Not following any user");
			softassertion.assertTrue(true);
			captureScreen(driver, "NotFollowing");
			logger.info("User is not following any other user!!");
		}
		//if user is following other users, no. of avatars of users following are displayed
		else 
		{
			logger.info("Avatar of Users I follow");
			softassertion.assertTrue(true);
			homepg.clkFollowingNos();
			logger.info("Avatars of users I follow displayed: " + homepg.getavatarfollowingsize());
			logger.info("Avatar is updated in 'following' tab");
			// Browse to previous page
			driver.navigate().back();
		}
		
		//check avatar is displayed in 'FOLLOWER' tab
		//Check whether the user is having any follower or not
		//If there are no followers, capture screenshot
		if(homepg.gettxtfollowerNo().contains("0"))
		{
			logger.info("No followers for this user");
			softassertion.assertTrue(true);
			captureScreen(driver, "NoFollowers");
			logger.info("User has no followers!!");
		}
		else
		{
			//If there are followers, no. of avatars are captured
			logger.info("Avatar of my followers");
			softassertion.assertTrue(true);
			homepg.clkFollowerNos();
			logger.info("Avatars of user who follow me: " +homepg.getavatarfollowersize());
			
			//Browse to previous page
			driver.navigate().back();
		}
		
		softassertion.assertAll();
		
		//Logout from the user
		logger.info("User logout");
		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();

logger.info("Completed TP_TC_112");
	}
	
}
