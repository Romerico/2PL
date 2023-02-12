package com.testCases;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HelpPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;

//Testcase Description: Checks whether help pages are working correct
//Acceptance Criteria: "This testcase is successful if
//1. Help pages are displayed  when user clicks on help link"

public class TP_TC_113 extends BaseClass {

	@Test(priority = 11301)

	public void helpPage() throws InterruptedException, IOException {

		logger.info("Started TP_TC_113");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password");

		lp.setUsername(username1);
		logger.info("providing user name");

		lp.setPassword(password);
		logger.info("providing password");

		lp.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");

		logger.info("Clicking on help option");
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actHelp()).click().build().perform();

		//Verify the help page is displayed
		HelpPage helppg = new HelpPage(driver);
		String txtHelpmsg = helppg.gettxtHelpmsg();
		logger.info(txtHelpmsg);
		SoftAssert softAssertion = new SoftAssert();

		if (txtHelpmsg.contains("Support Center Topics")) {
			logger.info("Test Passed: Help Page displayed");
			softAssertion.assertTrue(true);

		} else {
			logger.error("Test Failed: Help page not displayed");
			captureScreen(driver, "helpPage");
			softAssertion.assertTrue(false);

		}
		softAssertion.assertAll();
		logger.info("Completed TP_TC_113");
	}

}
