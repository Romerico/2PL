package com.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.EditProfilePage;
import com.pageObjects.LoginPage;

//Testcase Description: Testing the functionality to display "Profile Table/Box" on  profile page

/* Acceptance Criteria: This Table/Box should display with  "Profile" header including with "Edit Profile" icon with link, 
 it should be clickable and table should display with First Name, Last Name, City/Town, Zip Code, Address Line 1, Address Line 2, and Network */
 
public class TP_TC_073 extends BaseClass {
	
	SoftAssert softassert = new SoftAssert();

	@Test(priority=7301)
	public void checkEditProfilePageItems() throws IOException, InterruptedException
	{
		//Login as default user
				logger.info("Started TP_TC_073");
				driver.get(baseURL);
				logger.info("Opened URL");
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username3);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Successfully logged in!");
				
				
		//Click on "Edit Page" link & Navigate to the "Edit Profile" page
		logger.info("Navigate to Edit Profile page");
		EditProfilePage epp = new EditProfilePage(driver);
		epp.clkLnkEditPage();
		epp.clkEditProfileMenuItem();
		
		//Verify that the First Name is displayed & enabled
		logger.info("Verify if First Name is displayed & enabled");
		softassert.assertEquals(epp.isDisplayedEnabledFirstName(), true);
		logger.info("First Name displayed & enabled: " + epp.isDisplayedEnabledFirstName());
		
		//Verify that the Last Name is displayed & enabled
		logger.info("Verify if Last Name is displayed & enabled");
		softassert.assertEquals(epp.isDisplayedEnabledLastName(), true);
		logger.info("Last Name displayed & enabled: " + epp.isDisplayedEnabledLastName());
		
		//Verify that the Address1 is dispalyed & enabled
		logger.info("Verify if Address1 is displayed & enabled");
		softassert.assertEquals(epp.isDisplayedEnabledAddress1(), true);
		logger.info("Address1 displayed & enabled: " + epp.isDisplayedEnabledAddress1());
		
		//Verify that the Address2 is displayed & enabled
		logger.info("Verify if Address2 is displayed & enabled");
		softassert.assertEquals(epp.isDisplayedEnabledAddress2(), true);
		logger.info("Address2 displayed & enabled: " + epp.isDisplayedEnabledAddress2());
		
		//Verify that the Postal Code is displayed & enabled
		logger.info("Verify if Postal Code is displayed & enabled");
		softassert.assertEquals(epp.isDisplayedEnabledPostalCode(), true);
		logger.info("Postal Code displayed & enabled: " + epp.isDisplayedEnabledPostalCode());
		
		//Verify that the Network Country is displayed & enabled
		logger.info("Verify if Network Country is displayed & enabled");
		softassert.assertEquals(epp.isDisplayedEnabledNtwkCountry(), true);
		logger.info("Network Country displayed & enabled: " + epp.isDisplayedEnabledNtwkCountry());
		
		//Verify that the Network Province is displayed & enabled
		logger.info("Verify if Network Province is displayed & enabled");
		softassert.assertEquals(epp.isDisplayedEnabledNtwkProvince(), true);
		logger.info("Network Province displayed & enabled: " + epp.isDisplayedEnabledNtwkProvince());
		
		//Verify that the Network City is displayed & enabled
		logger.info("Verify if Network City is displayed & enabled");
		softassert.assertEquals(epp.isDisplayedEnabledNtwkCity(), true);
		logger.info("Network City displayed & enabled: " + epp.isDisplayedEnabledNtwkCity());
		
		//Default user logs out
		logger.info("Default user logs out");
		epp.clkdropDownLogout();
		epp.clkbtnSignOut();
		
		logger.info("Completed TP_TC_073");
	}
	
}
