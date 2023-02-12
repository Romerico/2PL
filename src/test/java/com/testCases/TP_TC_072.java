package com.testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;
import com.pageObjects.EditProfilePage;
import com.pageObjects.LoginPage;

/* Testcase Description: Testing the functionality by click on Edit Page link on User's Profile page */
/* Acceptance Criteria: The Application should display "Edit Page" on user's "Profile page"  and when user click on the "Edit Page " link, 
  should be navigate to the Edit Profile > page to edit the usre's "Username and Profile picture" */
 



public class TP_TC_072 extends BaseClass {

	String testUName = "goofy123";
	SoftAssert softassert = new SoftAssert();
	
	@Test(priority=7201)
	public void updateUserName() throws IOException, InterruptedException
	{
		//Login as default user
		logger.info("Started TP_TC_072");
		driver.get(baseURL);
		logger.info("Opened URL");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username3);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Successfully logged in!");
		
		//Click on Edit Page link
		logger.info("Navigate to the Edit Page");		
		EditProfilePage epp = new EditProfilePage(driver);
		epp.clkLnkEditPage();
		
		//Update the "UserName" on the page
		logger.info("Updating UserName on Edit Page");
		epp.setUserName(testUName);
		logger.info("Username updated to: " + testUName);
		epp.clkSaveEditPage();
		logger.info("Updated User logs out");
		
		//User logs out
		epp.clkdropDownLogout();
		epp.clkbtnSignOut();
	}
	
	@Test(priority=7202)
	public void loginAsUpdatedUser() throws IOException, InterruptedException
	{
		//Login as updated user
		LoginPage LoginPage = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		LoginPage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password of Updated User");
		
		LoginPage.setUsername(testUName);
		logger.info("providing user name of updated user");
		
		LoginPage.setPassword(password);
		logger.info("providing password of updated user");
		
		LoginPage.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");
		
		if(driver.getTitle().equals("twoPLUGS - A plug for your Service and another for your Need"))
		{
			softassert.assertTrue(true);
			logger.info("login success");
		}
		else
		{
			captureScreen(driver,"loginTest");
			logger.error("login is failed : Screen shot taken");
			softassert.assertTrue(false);
		}

		//Click on Edit Page link
		logger.info("Navigate to Edit Page");
		EditProfilePage epp = new EditProfilePage(driver);
		epp.clkLnkEditPage();
		
		//Update the "UserName" back to the default value
		logger.info("Updating UserName to default username on Edit Page");
		epp.setUserName(username3);
		logger.info("Username updated back to default username: " + username1);
		epp.clkSaveEditPage();
		
		//User logs out
		logger.info("Default user logs out");
		epp.clkdropDownLogout();
		epp.clkbtnSignOut();
	}
	
	@Test(priority=7203)
	public void loginAsDefaultUser() throws IOException, InterruptedException
	{
		//Login as default user, to ensure that the UserName has been reset to the original value
		LoginPage LoginPage = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		LoginPage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password of Default User");
		
		LoginPage.setUsername(username3);
		logger.info("providing user name of default user");
		
		LoginPage.setPassword(password);
		logger.info("providing password of default user");
		
		LoginPage.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");
		
		if(driver.getTitle().equals("twoPLUGS - A plug for your Service and another for your Need"))
		{
			softassert.assertTrue(true);
			logger.info("login success");
		}
		else
		{
			captureScreen(driver,"loginTest");
			logger.error("login is failed : Screen shot taken");
			softassert.assertTrue(false);
		}
		
		//User logs out
		logger.info("Default user logs out");
		LoginPage.clkdropDownLogout();
		LoginPage.clkbtnSignOut();
		
		logger.info("Completed TP_TC_072");
	}
	
}
