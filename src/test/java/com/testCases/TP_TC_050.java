package com.testCases;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.CreateService;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.NeedDetailPage;
import com.pageObjects.ProfilePage;
import com.pageObjects.ServiceDetailPage;

//Testcase Description:  Service / Need detail page > "Share Service" icons 
//Acceptance Criteria: Testing the functionality  of "Share Service/Need icon on Service/Need detail page


public class TP_TC_050 extends BaseClass {

	@Test(priority = 5001)
	public void Login_CreateService() throws InterruptedException, IOException {

		logger.info("Started TP_TC_050");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Login Successful!");
		// Create a service
		CreateService createsvc = new CreateService(driver);
		createsvc.clkbtnCreateNew();
		createsvc.clkbtnService();
		logger.info("Fill in Service form with test data");
		createsvc.txtTitleField("Jazy1 Fashions"); 
		createsvc.txtdescriptionField("One stop Fashion boutique");
		createsvc.clktxtCategoryField();
		createsvc.SelectdrpdownCategory("Beauty & Fashion");
	//	createsvc.clktxtsubCategoryField();
		createsvc.selectdrpdownSubCategory("Clothing");
		createsvc.txtPriceField("50");
		createsvc.btnSubmitServicePage();
		logger.info("Service Created Successfully");

		// Validate whether service is created successfully
		SoftAssert softassert = new SoftAssert();
		ServiceDetailPage servicedetpg = new ServiceDetailPage(driver);
		if (servicedetpg.gettxtaddSvccaption().contains("Service has been added")) {
			softassert.assertTrue(true);
			logger.info("Created Service Successfully");
		} else {
			softassert.assertTrue(false);
			logger.error("Service NOT created");
		}
		softassert.assertAll();

		// Refresh the page
		driver.navigate().refresh();
	}

	@Test(priority = 5002)
	public void ServiceDetailPage_ShareService() throws IOException, InterruptedException {
		// Go to Profile Page to view the newly created service
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();

		// Open the newly created service to view the service details
		ProfilePage profilepg = new ProfilePage(driver);
		profilepg.clktxtlatestsvc();

		// Assert whether Service details page has correctly opened
		SoftAssert softassert = new SoftAssert();
		ServiceDetailPage svcdetpg = new ServiceDetailPage(driver);
		if (svcdetpg.gettxtservicename().equalsIgnoreCase("Jazy1 Fashions")) {
			softassert.assertTrue(true);
			logger.info("Service Details page opened correctly");
		} else {
			softassert.assertTrue(false);
			logger.error("Service Details page INCORRECTLY opened!!");
			captureScreen(driver, "IncorrectServiceDetailsPage");
		}

		svcdetpg.clickThreeLinkIcon();
		logger.info("clicking on service share icon");
		int shareServiceCount = svcdetpg.getShareServicesOptions();

		// **************Validations**************************
		SoftAssert softAssert = new SoftAssert();// creating soft assertion object
		// ************checking if three link icon is clickable or not
		if (svcdetpg.threeLinkBtn.isEnabled()) {

			softAssert.assertTrue(true);
			logger.info("threeLinkicon is Enabled");

		} else {
			softAssert.assertTrue(false);
			logger.error("threeLinkicon is NOT Enabled");
			captureScreen(driver, "3linkNotEnabled");
		}

// ***********getting count and names of share options*********

		ArrayList<String> shareOptions = new ArrayList<String>(5);

		for (int i = 1; i <= shareServiceCount; i++) {

			shareOptions.add(driver
					.findElement(By.xpath(
							"/html/body/div[7]/section/div/div[2]/div[2]/div[1]/div/div/div/ul/li[" + i + "]/a/span"))
					.getAttribute("class"));

			logger.info(shareOptions);

		}

		String shareByEmailLink = shareOptions.get(0);
		String shareByTwitterLink = shareOptions.get(1);
		String shareByFbLink = shareOptions.get(2);

		logger.info(shareByEmailLink);
		logger.info(shareByTwitterLink);
		logger.info(shareByFbLink);
		// **************validating email logo is present or not****************
		if (shareByEmailLink.contains("mail")) {

			softAssert.assertTrue(true);
			logger.info("*************** Email logo is present*********** ");
		}

		else {
			softAssert.assertTrue(false);
			logger.error("*************** Email logo is not present*********** ");
			captureScreen(driver, "EmailIconNotpresent");
		}
		// **************validating Twitter logo is present or not****************

		if (shareByTwitterLink.contains("tweet")) {

			softAssert.assertTrue(true);
			logger.info("Tweeter logo is present");
		}

		else {
			softAssert.assertTrue(false);
			logger.error("Tweeter logo is not present");
			captureScreen(driver, "TwitterIconNotPresent");
		}

		// **************validating facebook logo is present or not****************
		if (shareByFbLink.contains("face")) {

			softAssert.assertTrue(true);
			logger.info("Facebook logo is present");
		}

		else {
			softAssert.assertTrue(false);
			logger.error("Facebook logo is not present");
			captureScreen(driver, "FbIconNotPresent");
		}

		softAssert.assertAll();

		// Refresh the page
		driver.navigate().refresh();

		logger.info("Service Details Page Validated");

		// Go to profile page and delete the service (created during this testcase)
		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();

		ProfilePage profilepg1 = new ProfilePage(driver);
		profilepg1.clkbtnDeleteService();
		profilepg1.clkbtnIwantToDelete();

		logger.info("Service has been deleted");
		logger.info("Service deleted successfully");

	}

	@Test(priority = 5003)
	public void CreateNeed() throws InterruptedException {
		// Click on twoplugs logo to go back to homepage
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnLogo();

		logger.info("Creating a new Need");
		CreateNeed createneed = new CreateNeed(driver);
		createneed.clkbtnCreateNew();
		createneed.clkbtnNeed();
		logger.info("Fill in Need form with test data");
		createneed.txtTitleField("Auto services");
		createneed.txtdescriptionField("Need Driving Instruction1");
		createneed.clktxtCategoryField();
		createneed.SelectdrpdownCategory("Automobile");
	//	createneed.clktxtsubCategoryField();
		createneed.selectdrpdownSubCategory("Car Share");
		createneed.txtPriceField("100");
		createneed.btnSubmitNeedPage();
		logger.info("Created Need Successfully");

		// Validate whether service is created successfully
		SoftAssert softassert = new SoftAssert();
		NeedDetailPage needdetpg = new NeedDetailPage(driver);
		if (needdetpg.gettxtaddNeedcaption().contains("Need has been added")) {
			softassert.assertTrue(true);
			logger.info("Created Need Successfully");
		} else {
			softassert.assertTrue(false);
			logger.error("Need NOT created");
		}
		softassert.assertAll();
		// Refresh the page
		driver.navigate().refresh();
	}

	@Test(priority = 5004)
	public void NeedDetailsPage_Shareneed() throws IOException, InterruptedException {
		// Go to Profile Page to view the newly created need
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();

		// Open the newly created need to view the Need details
		ProfilePage profilepg = new ProfilePage(driver);
		profilepg.clktxtlatestneed();

		// Assert whether Service details page has correctly opened
		SoftAssert softassert = new SoftAssert();
		NeedDetailPage needdetpg = new NeedDetailPage(driver);
		if (needdetpg.gettxtneedname().equalsIgnoreCase("Jazy1 Fashions")) {
			softassert.assertTrue(true);
			logger.info("Need Details page opened correctly");
		} else {
			softassert.assertTrue(false);
			logger.error("Need Details page INCORRECTLY opened!!");
			captureScreen(driver, "IncorrectNeedDetailsPage");
		}

		needdetpg.clickThreeLinkIcon();
		logger.info("clicking on need share icon");
		int shareNeedCount = needdetpg.getShareNeedsOptions();

//**************Validations**************************
		SoftAssert softAssert = new SoftAssert();// creating soft assertion object
//************checking if three link icon is clickable or not
		if (needdetpg.threeLinkBtn.isEnabled()) {

			softAssert.assertTrue(true);
			logger.info("threeLinkicon is Enabled");

		} else {
			softAssert.assertTrue(false);
			logger.error("threeLinkicon is NOT Enabled");
			captureScreen(driver, "3LinkiconNotEnabled");
		}

//***********getting count and names of share options*********

		ArrayList<String> shareOptions = new ArrayList<String>(5);

		for (int i = 1; i <= shareNeedCount; i++) {

			shareOptions.add(driver
					.findElement(By.xpath(
							"/html/body/div[7]/section/div/div[2]/div[2]/div[1]/div/div/div/ul/li[" + i + "]/a/span"))
					.getAttribute("class"));

			logger.info(shareOptions);

		}

		String shareByEmailLink = shareOptions.get(0);
		String shareByTwitterLink = shareOptions.get(1);
		String shareByFbLink = shareOptions.get(2);

		logger.info(shareByEmailLink);
		logger.info(shareByTwitterLink);
		logger.info(shareByFbLink);
//**************validating email logo is present or not****************
		if (shareByEmailLink.contains("mail")) {

			softAssert.assertTrue(true);
			logger.info("Email logo is present");
		}

		else {
			softAssert.assertTrue(false);
			logger.error("Email logo is not present");
			captureScreen(driver, "EmailIconNotpresent");
		}
//**************validating Twitter logo is present or not****************

		if (shareByTwitterLink.contains("tweet")) {

			softAssert.assertTrue(true);
			logger.info("Tweeter logo is present");
		}

		else {
			softAssert.assertTrue(false);
			logger.error("Tweeter logo is not present");
			captureScreen(driver, "TwitterIconNotPresent");
		}

//**************validating facebook logo is present or not****************
		if (shareByFbLink.contains("face")) {

			softAssert.assertTrue(true);
			logger.info("Facebook logo is present");
		}

		else {
			softAssert.assertTrue(false);
			logger.error("Facebook logo is not present");
			captureScreen(driver, "FbIconNotPresent");
		}

		softAssert.assertAll();

//Refresh the page
		driver.navigate().refresh();

		logger.info("Need Details Page Validated");

		// Go to Profile page
		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();

		// Delete the need created during this testcase
		ProfilePage profilepg1 = new ProfilePage(driver);
		profilepg1.clkbtnDeleteNeed();
		profilepg1.clkbtnIwantToDeleteNeed();
		logger.info("Need has been deleted");
		logger.info("Need deleted successfully");

		logger.info("Completed TP_TC_050");
	}

}
