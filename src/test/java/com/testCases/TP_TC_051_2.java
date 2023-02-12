package com.testCases;

import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.FbPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.NeedDetailPage;
import com.pageObjects.PlugsPage;


//Testcase Description: Testing the functionality of Application by Click on Facebook logo in Need Detail Page
//Acceptance Criteria: The Application should be able to click this Logo and navigate to User's Facebook link. 
//So then the User click on Facebook's twoPLUGS link it should be display the User's Need Details page from  twoPLUGs Website

public class TP_TC_051_2 extends BaseClass {

	@Test(priority = 5103)
	public void UserLogin_CreateNeed() throws InterruptedException {
		logger.info("Started TP_TC_051_2");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickloginlandingbtn();
		loginPage.setUsername(username1);
		loginPage.setPassword(password);
		loginPage.clickloginbtn1();

		// Search for a need from Plugs page
		HomePage homepg = new HomePage(driver);
		logger.info("Click on Plugs!");
		homepg.clkbtnPlugs();

		// Click on Needs tab
		PlugsPage plugspg = new PlugsPage(driver);
		logger.info("Click on Needs tab");
		plugspg.ClickNeedsBtn();

		// Click on the first need available
		logger.info("Select the first need available in Plugs tab");
		plugspg.clkPlugsfirstentry();
	}

	@Test(priority = 5104)
	public void NeedPageThreeIcon() throws IOException, InterruptedException {

		NeedDetailPage needdetpg = new NeedDetailPage(driver);
		logger.info("Click on 3 link sharing options");
		needdetpg.clickThreeLinkIcon();

		logger.info("Click on Facebook share option");
		needdetpg.clickFB();

		// driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;//implicit
		// wait

		// using window handles to switch to different tabs
		Set<String> windowhandles = driver.getWindowHandles();

		for (String i : windowhandles) {
			String windowtitle = driver.switchTo().window(i).getTitle();
			logger.info(windowtitle);
		}

		logger.info("Switch to fb tab");

		String Actual_title = driver.getTitle();
		logger.info("Get title of page");
		logger.info(Actual_title);

		// Validating if user is able to navigate on fb page

		SoftAssert softAssert = new SoftAssert();
		if (Actual_title.equals("Facebook")) {
			softAssert.assertTrue(true);
			logger.info("Navigated to facebook page");
		} else {
			softAssert.assertTrue(false);
			logger.error("DidNot navigated to Facebook page");
			captureScreen(driver, "DidnotOpenFacebook");
		}

		FbPage fbPage = new FbPage(driver);// creating fb page object
		logger.info("Create fb page object");

// entering fb credentials
		fbPage.txtEmail();
		fbPage.txtPass();
		fbPage.clickLoginBtn();
		logger.info("Entering fb credentials and login");

		fbPage.clickPostToFb();
		logger.info("post link on facebook");

		// creating and using action class to open new tab
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();

		logger.info("Creating action class to open new tab");

		driver.get("https://www.facebook.com");// opening fb url
		logger.info("Navigated to facebook");

		Actions act1 = new Actions(driver);
		act1.sendKeys(Keys.ESCAPE).build().perform();// to dismiss pop-up notification

		fbPage.clickOnTwoPlugLink();// calling method to click on two plugs link need on fb
		logger.info("clicking on two plugs link");

		// using window handles to switch to twoPLUGS page
		Set<String> windowhandles1 = driver.getWindowHandles();

		for (String i : windowhandles1) {
			String windowtitle1 = driver.switchTo().window(i).getTitle();
			logger.info(windowtitle1);
		}

		logger.info("Switch to twoPLUGS page");

		SoftAssert softassert1 = new SoftAssert();
		if (driver.getTitle().equalsIgnoreCase("twoPLUGS - A plug for your Service and another for your Need")) {
			softassert1.assertTrue(true);
			logger.info("Test Passed: Need Posted Successfully on Facebook page");

		} else {
			softassert1.assertTrue(false);
			logger.error("Test Failed: Need NOT Posted on Facebook page");
			captureScreen(driver, "FBNeedTestFailed");
		}

		softassert1.assertAll();
		logger.info("Completed TP_TC_051_2");

	}

}
