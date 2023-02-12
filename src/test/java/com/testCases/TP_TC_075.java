package com.testCases;

import java.io.IOException;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateService;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MessagePage;
import com.pageObjects.ProfilePage;
import com.pageObjects.SearchService;
import com.pageObjects.ServiceDetailPage;

/* Testcase Description: Testing the functionality of the application  by entering "Review" in  "Need/Service Details" page */
/* Acceptance Criteria: The Application should display  the  "Reviews and Ratings" under Reviews tab in Need/Service Details page */

public class TP_TC_075 extends BaseClass {
	
	SoftAssert softassert = new SoftAssert();
	public String serviceName = "NP Zenith Services";
	String reviewComment = serviceName +  " Test Comment 123 " + randomestring();
	int reviewStars = 3;
	
	@Test(priority=7501)
	public void SellerLogin_CreateService() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_075");
		//Login as Seller
		LoginPage LoginPage = new LoginPage(driver);
				
		LoginPage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password of Seller");
		
		LoginPage.setUsername(username2);
		logger.info("providing user name of Seller");
		
		LoginPage.setPassword(password);
		logger.info("providing password of Seller");
		
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
		// Create a service
				CreateService createsvc = new CreateService(driver);
				createsvc.clkbtnCreateNew();
				createsvc.clkbtnService();
				logger.info("Fill in Service form with test data");
				createsvc.txtTitleField(serviceName);  
				createsvc.txtdescriptionField(serviceName + " Description");
				createsvc.clktxtCategoryField();
				createsvc.SelectdrpdownCategory("Home & Child");
		//		createsvc.clktxtsubCategoryField();
				createsvc.selectdrpdownSubCategory("All");
				createsvc.txtPriceField("5");
				createsvc.SilderBarMaxLimit();
				createsvc.refundValidField("7");
				createsvc.btnSubmitServicePage();
				logger.info("Service Created Successfully");
				//Validate whether service is created successfully
				SoftAssert softassert = new SoftAssert();
				ServiceDetailPage servicedetpg = new ServiceDetailPage(driver);
				if(servicedetpg.gettxtaddSvccaption().contains("Service has been added"))
				{
					softassert.assertTrue(true);
					logger.info("Created Service Successfully");	
				}
				else
				{
					softassert.assertTrue(false);
					logger.error("Service NOT created");
					captureScreen(driver,"ServiceNotCreated");
				}
				softassert.assertAll();

				//Refresh the page
				driver.navigate().refresh();
				// Logout from the seller
				HomePage homepg = new HomePage(driver);
				logger.info("Seller logout");
				Actions act = new Actions(driver);
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}
	
	@Test(priority=7502)
	public void BuyerLogin_BuyService() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as Buyer");
		// Click on search box
		logger.info("Click on Search box");
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnSearch();
		// Search for the service based on text value
		logger.info("Search for the specific service");
		SearchService searchpg = new SearchService(driver);
		searchpg.txtSearchValue(serviceName);
		searchpg.clkSerachButton();
		// Click on the link containing the text value
		logger.info("click on the created service");
		searchpg.clkbtncreatedsvc();
		// Select 'I want this' option
		logger.info("Click on 'I want this' button");
		searchpg.btnIwantThis();
		// Select the checkbox and 'buy' button
		searchpg.clkDisclaimerOption();
		searchpg.clkBuy();
		searchpg.clkbtnOk();
		//Refresh the page
		driver.navigate().refresh();
		// Logout from this user 
		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();		
	}
	
	@Test(priority=7503)
	public void SellerLogin_Accept() throws IOException, InterruptedException
	{
		// Login as a seller
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username2);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as seller");
				
				// Check messages in inbox
				HomePage homepg = new HomePage(driver);
				homepg.clkbtnMessages();
				
		
				MessagePage mp = new MessagePage(driver);
				logger.info("Navigate to the Seller's Inbox");
				
				if(mp.confirmOrderDuplicateMessagePresent()==true)
				{
					mp.confirmOrderDuplicateMessageDeny();
					logger.info("Deny the duplicate Confirm Order mail");
				}
		
				mp = new MessagePage(driver);
				HomePage homepg1 = new HomePage(driver);
				homepg1.clkbtnMessages();
				logger.info("Navigate to the Seller's Inbox again");
		
				mp.findMessageInInbox("Confirm Order");
				mp.clkSaleAgreementbox();
				mp.clkbtnAccept();
				logger.info("Open Confirm Order mail and confirm sale");
						
				HomePage homepg2 = new HomePage(driver);
				homepg2.clkbtnMessages();
				logger.info("Navigate to the Seller's Inbox again");
		
				mp.clktxtfirstmail();
				mp.clkbtnConfirm();
				logger.info("Open Confirm Delivery mail and deliver the Service");
				
				//Refresh the page
				driver.navigate().refresh();
				
				//Seller logout
				logger.info("Seller logout");
				Actions act1 = new Actions(driver);
				act1.moveToElement(homepg2.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
	}
	
	@Test(priority=7504)
	public void BuyerLogin_RateService() throws IOException, InterruptedException
	{		
		// Login as a buyer
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username1);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as Buyer");
				
		//Navigate to the Buyer's Inbox
				logger.info("Click on message tab");
				HomePage homepg = new HomePage(driver);
				homepg.clkbtnMessages();

		//Rate the Service as a Buyer through the "Rate Service" email message
		MessagePage mp = new MessagePage(driver);
		mp.findMessageInInbox("Rate service");
		logger.info("Open the Rate Service mail");
		mp.clkReviewLnk();
		logger.info("Open the Review Link");
		mp.setReviewComment(reviewComment);
		logger.info("Enter Review Comment");
		mp.setReviewStarsSilder(reviewStars);
		logger.info("Enter Review Stars");
		mp.clkReviewSubmit();
		logger.info("Submit the Rating Review");
		
		// Logout from this user 
				logger.info("Buyer logout");
				Actions act = new Actions(driver);
				HomePage homepg1 = new HomePage(driver);
				act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();	
	
	}

	@Test(priority=7505)
	public void verifyTheRatingAsSeller() throws IOException, InterruptedException
	{
		logger.info("Login as a seller");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
			
		//Navigate to the Seller's Profile Page
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		logger.info("Navigating to the Seller's Profile Page");
		
		//Open the Service Details Page
		ProfilePage pp = new ProfilePage(driver);
		pp.clktxtlatestsvc();
		
		/*if(pp.searchService(serviceName))
		{
			pp.openServicePage(serviceName);
			logger.info("Open the Service Details Page, if the Service is present");
		}
		*/
		//Check if the Service Rating is displayed
		ServiceDetailPage sdp = new ServiceDetailPage(driver);
		boolean ratingCorrect = sdp.verifyReview(username1, reviewComment, reviewStars);
		logger.info("Rating of Service: " + ratingCorrect);
		
		//If "Rating" comment & stars are correctly displayed, Test is Passed
		if(ratingCorrect)
		{
			softassert.assertTrue(true);
			logger.info("Review for " + serviceName + " is correctly displayed");
		}
		else
		{
			captureScreen(driver,"verifyTheRatingAsSeller");
			logger.error("Rating not displayed : Screen shot taken");
			softassert.assertTrue(false);	
		}
		softassert.assertAll();
		
		//Seller log out
		logger.info("Seller logout");
		Actions act1 = new Actions(driver);
		HomePage homepg1 = new HomePage(driver);
		act1.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
	}
	
	@Test(priority=7506)
	public void SellerLogin_DeleteService() throws IOException, InterruptedException
	{
		logger.info("Login as a seller");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		// Go to profile page and delete the service (created during this testcase)
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		ProfilePage profilepg = new ProfilePage(driver);
		profilepg.clkbtnDeleteService();
		profilepg.clkbtnIwantToDelete();
		ServiceDetailPage sdp = new ServiceDetailPage(driver);
		if(sdp.gettxtdelSvccaption().equalsIgnoreCase("Service has been deleted"))
		{
		softassert.assertTrue(true);
		logger.info("Service has been deleted");
		}
		else
		{
			softassert.assertTrue(false);
			logger.error("Service NOT deleted");
			captureScreen(driver,"ServiceNOTdeleted");
		}
		//Refresh the page
		driver.navigate().refresh();
		// Logout from this user 
		logger.info("Seller logout");
		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		logger.info("Completed TP_TC_075");
	}
}
