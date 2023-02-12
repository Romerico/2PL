package com.testCases;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.FbPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.PlugsPage;
import com.pageObjects.ServiceDetailPage;

//Testcase Description: Testing the functionality of Application by Click on Facebook logo in Service Detail Page
//Acceptance Criteria: The Application should be able to click this Logo and navigate to User's Facebook link. 
//So then the User click on Facebook's twoPLUGS link it should be display the User's Service Details page from  twoPLUGs Website


public class TP_TC_051_1 extends BaseClass{

	@Test(priority = 5101)
	public void Userlogin_SearchService() throws InterruptedException {
		logger.info("Started TP_TC_051_1");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Login Successful!");
		
		//Search for the service from Plugs page
		HomePage homepg = new HomePage(driver);
		logger.info("Click on Plugs!");
		homepg.clkbtnPlugs();
		
		//Click on Services tab
		PlugsPage plugspg = new PlugsPage(driver);
		logger.info("Click on Services tab");
		plugspg.ClickServicesBtn();
		
		//Select the first available service
		logger.info("Select the first service available in Plugs tab");
		plugspg.clkPlugsfirstentry();
		
	}

	@Test(priority = 5102)
	public void ServicePageThreeIcon() throws IOException, InterruptedException {
	
		ServiceDetailPage svcdetpg = new ServiceDetailPage(driver);
				logger.info("Click on 3 link sharing options");
				svcdetpg.clickThreeLinkIcon();

				logger.info("Click on Facebook share option");
				svcdetpg.clickFB();
	
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;//implicit wait

		// using window handles to switch to different tabs
		Set<String> windowhandles = driver.getWindowHandles();
		
		for(String i : windowhandles)
		{
		String windowtitle = driver.switchTo().window(i).getTitle();
		logger.info(windowtitle);
		}
			
		logger.info("Switch to fb tab");

		String Actual_title = driver.getTitle();
		logger.info("Get title of page");
		logger.info(Actual_title);

		//Validating if user is able to navigate on fb page

		SoftAssert softAssert = new SoftAssert();
		if (Actual_title.equals("Facebook")) {
			softAssert.assertTrue(true);
			logger.info("Navigated to facebook page");
		} else {
			softAssert.assertTrue(false);
			logger.error("DidNot navigated to Facebook page");
			captureScreen(driver,"DidnotOpenFacebook");
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
				
				for(String i : windowhandles1)
				{
				String windowtitle1 = driver.switchTo().window(i).getTitle();
				logger.info(windowtitle1);
				}
					
				logger.info("Switch to twoPLUGS page");
				SoftAssert softassert1 = new SoftAssert();
				if(driver.getTitle().equalsIgnoreCase("twoPLUGS - A plug for your Service and another for your Need"))
				{
					softassert1.assertTrue(true);
					logger.info("Test Passed: Service Posted Successfully on Facebook page");
					
				}
				else
				{
					softassert1.assertTrue(false);
					logger.error("Test Failed: Service NOT Posted on Facebook page");
					captureScreen(driver,"FBServiceTestFailed");
				}	
				
				softassert1.assertAll();
				
				logger.info("Completed TP_TC_051_1");
				
		}
	
	}


