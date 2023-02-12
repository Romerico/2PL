package com.testCases;

import java.io.IOException;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.EditProfilePage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.ProfilePage;

// Testcase Description: Testing the functionality to display "About Table/Box" on  profile page
// Acceptance Criteria: This Table/Box should display table with  "About" header including with "Edit Bio" icon and it should be clickable and  this able has to be contain with pics which are added by the User
 
public class TP_TC_090 extends BaseClass {
	
	SoftAssert softassert = new SoftAssert();
	String testDesc = "Twoplugs Test " + randomestring() + " " + randomeNum();
	
	@Test(priority=9001)
	public void editBioDescription() throws IOException, InterruptedException
	{
		//Login as default user
		logger.info("Started TP_TC_090");
		driver.get(baseURL);
		logger.info("Opened URL");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Successfully logged in!");

		//Click on "Edit Page" link, and Navigate to "Edit Bio" page
		logger.info("Navigate to Edit Bio Page");
		EditProfilePage epp = new EditProfilePage(driver);
		epp.clkLnkEditPage();
		epp.clkEditBioMenuItem();
		
		//Update the "Bio Description" on the "Edit Bio" page
		logger.info("Updating Bio Description on Edit Bio Page");
		epp.setBioDescription(testDesc);
		
		epp.clkSaveBioButton();
		
		//Navigate to the user's Profile Page & get the actual "Bio Description" field value from the Profile Page
		logger.info("Verify if updated Bio Description appears on Profile Page");
		ProfilePage pp = new ProfilePage(driver);
		String act_BioDesc = pp.actualBioDescription();
		
		//If the actual value = expected value, Test is Passed
		softassert.assertEquals(act_BioDesc, testDesc, "Message Updated");
		logger.info("Actual Bio Description: " + act_BioDesc);
		logger.info("Expected Bio Description: " + testDesc);
		
		//User logs out
		HomePage homepg = new HomePage(driver);
		logger.info("User logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();

	}

}
