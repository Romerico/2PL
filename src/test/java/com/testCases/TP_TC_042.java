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

/* Testcase Description: "This test is to verify whether the seller gets mail when buyer when buyer clicks 'I want this' option and seller offers to 'Accept' the bid
Step 1: Login as Buyer
Step 2: On Live Feed page search for seller service with no bidding option
Step 3: Click on 'I want this' > Buy > Ok
Step 4: Login as a Seller
Step 5: Go to Messages > Open Confirm Order mail
Step 6: Click Accept
Step 7: Login as a buyer > Check Messages" */

/* Acceptance Criteria: "The following will happen for the test case to be considered successful
AC01-The application should send 'Confirm Order' Mail with options - Deny Purchase & Accept to Seller
AC02- After seller accepts, the application sends Seller Confirmation mail to Buyer
AC03-Once confirmed, buyer receives Service Delivery and Rate service mail. " */

public class TP_TC_042 extends BaseClass {

	// Login as a seller and create a service
	@Test(priority = 4201)
	public void sellerloginnCreateService2() throws InterruptedException {
		logger.info("Started TP_TC_042");
		
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as a Seller ");
		// Create a service
		CreateService createsvc = new CreateService(driver);
		createsvc.clkbtnCreateNew();
		createsvc.clkbtnService();
		logger.info("Fill in Service form with test data");
		createsvc.txtTitleField("Jazy Fashions");  
		createsvc.txtdescriptionField("One stop Fashion boutique");
		createsvc.clktxtCategoryField();
		createsvc.SelectdrpdownCategory("Beauty & Fashion");
		CreateService createsvc1 = new CreateService(driver);
		createsvc1.selectdrpdownSubCategory("Clothing");
		createsvc1.txtPriceField("350");
		createsvc1.SilderBarMaxLimit();
		createsvc1.refundValidField("35");
		createsvc1.btnSubmitServicePage();
		logger.info("Service form submitted");
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

	// Login as a buyer; search for a bid and select "I want this"
	@Test(priority = 4202)
	public void BuyerSearchbidnSelectIwantThis() throws InterruptedException {
		// Login as a buyer
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
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
		searchpg.txtSearchValue("Jazy Fashions");
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
	
	// Login as a seller; check messages and Select to Accept
	@Test(priority = 4203)
	public void CheckSellerConfirmDelivery() throws IOException, InterruptedException {
		// Login as a seller
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as seller");
		// Check messages in inbox
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();
		// Check whether seller gets Confirm order mail and click on it
		SoftAssert softassertion = new SoftAssert();
		MessagePage messagepg = new MessagePage(driver);
		if (messagepg.gettxtfirstmail().contains("Confirm Order")) {
			logger.info("Test Passed: Seller received Confirm order mail");
			softassertion.assertTrue(true);
			logger.info("Seller received Confirm Order mail!!");
			messagepg.clktxtfirstmail();
			logger.info("Click on Accept");
			messagepg.clkSaleAgreementbox();
			messagepg.clkbtnAccept();
			// Check whether the seller gets the 'Confirm Delivery' mail in inbox and validate the message
			logger.info("Validation of 'Confirm Delivery' message begins");
			MessagePage messagepg1 = new MessagePage(driver);
			// click on the confirm delivery mail
			messagepg1.clktxtfirstmail();
			// Validate the 'Confirm Delivery' message in the mail (seller)
			if (messagepg.getTextMessage().contains("Please confirm delivery of service")) {
				logger.info("Test Passed: The mail in Seller inbox contains Confirm delivery message");
				softassertion.assertTrue(true);
				logger.info("The mail in Seller inbox contains Confirm delivery message!!");
				} else {
				logger.error("Test Failed: The mail in Seller inbox DOESNOT contain Confirm delivery message");
				softassertion.assertTrue(false);
				captureScreen(driver, "CheckSellerConfirmDeliveryMessage");
				logger.info("The mail in Seller inbox DOESNOT contain Confirm delivery message!!");
			}
			//Go back to Messages Page
			messagepg.clkbtnBack();
		} else {
			logger.error("Test Failed: Seller DIDNOT receive Confirm order mail");
			softassertion.assertTrue(true);
			captureScreen(driver, "CheckSellerConfirmDeliveryMail");
			logger.info("Seller DIDNOT receive Confirm Order mail!!");
		}
		softassertion.assertAll();
		// Refresh the page
		driver.navigate().refresh();
		// Logout from this user 
		logger.info("Seller logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

	// Login as a buyer and check Seller Confirmation mail in buyer inbox
	@Test(priority = 4204)
	public void CheckSellerConfirmation() throws InterruptedException, IOException {
		// Login as a buyer
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as buyer");
		// Check messages in inbox
		HomePage homepg1 = new HomePage(driver);
		homepg1.clkbtnMessages();
		SoftAssert softassertion = new SoftAssert();
		// Validate: The buyer gets the 'Seller confirmation' message in inbox
		MessagePage messagepg = new MessagePage(driver);
		if (messagepg.gettxtfirstmail().contains("Seller Confirmation")) {
			logger.info("Test Passed: The buyer gets the Seller Confirmation mail in Inbox");
			softassertion.assertTrue(true);
			logger.info("The buyer got the 'Seller Confirmation' mail in his Inbox!!");
			// Click on the mail
			messagepg.clktxtfirstmail();
			// Validate the 'Seller Confirmation' message in the mail (buyer)
			if (messagepg.getTextMessage().contains("has already confirmed your purchase")) {
				logger.info("Test Passed: The mail in buyer inbox contains 'Order confirmation' message");
				softassertion.assertTrue(true);
				logger.info("The mail in buyer inbox contains 'Order confirmation' message!!");
			} else {
				logger.error("Test Failed: The mail in buyer inbox DOESNOT contain 'Order confirmation' message");
				softassertion.assertTrue(false);
				captureScreen(driver, "CheckSellerConfirmationMessage");
				logger.info("The mail in buyer inbox DOESNOT contain 'Order confirmation' message!!");
			}
		} else {
			logger.error("Test Failed: The buyer DIDNOT get the 'Seller Confirmation' mail in Inbox");
			softassertion.assertTrue(false);
			captureScreen(driver, "CheckSellerConfirmationMail");
			logger.info("The buyer DIDNOT get the 'Seller Confirmation' mail in his Inbox!!");
		}
		softassertion.assertAll();
		// Refresh the page
		driver.navigate().refresh();
		// Logout from this user 
		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
	}
	
	//Login as a seller to confirm the service delivery and delete the mail
		@Test (priority = 4205)
		public void SellerMailConfirmAndServiceDeletion() throws InterruptedException, IOException
		{
			logger.info("Login as a seller");
			LoginPage lp = new LoginPage(driver);
			lp.clickloginlandingbtn();
			logger.info("Providing Login Details");
			lp.setUsername(username1);
			lp.setPassword(password);
			lp.clickloginbtn1();
			logger.info("Log in as Seller");
			// Check messages in inbox
			logger.info("Click on message tab");
			HomePage homepg = new HomePage(driver);
			homepg.clkbtnMessages();
			MessagePage messagepg = new MessagePage(driver);
			//Click on 'Confirm Delivery' Mail
			messagepg.clktxtfirstmail();
			messagepg.clkbtnConfirm();
			logger.info("Confirm the mail!");
			//Refresh the page
			driver.navigate().refresh();
			// Go to profile page and delete the service (created during this testcase)
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
			ProfilePage profilepg = new ProfilePage(driver);
			profilepg.clkbtnDeleteService();
			profilepg.clkbtnIwantToDelete();
			ServiceDetailPage svcdetpg = new ServiceDetailPage(driver);
			SoftAssert softassert = new SoftAssert();
			if(svcdetpg.gettxtdelSvccaption().equals("Service has been deleted"))
			{
				softassert.assertTrue(true);
				logger.info("Service has been deleted");
			}
			else
			{
				softassert.assertTrue(false);
				logger.info("Service NOT deleted");
				captureScreen(driver,"ServiceNotDeleted");
			}
			softassert.assertAll();
			
			// Go to Messages Page
			HomePage homepg1 = new HomePage(driver);
			homepg1.clkbtnMessages();
			// Select the confirm delivery message and New bid message to delete it (created during this testcase)
			logger.info("Delete the Confirm Delivery mail from inbox");
			messagepg.clkLatestEntry();
			messagepg.clkbtnDelete();
			driver.navigate().refresh();
			logger.info("Delete the Confirm Order mail from inbox");
			MessagePage messagepg2 = new MessagePage(driver);
			messagepg2.clkLatestEntry();
			messagepg2.clkbtnDelete();
			//Refresh the page
			driver.navigate().refresh();
			// Logout from this user 
			logger.info("Seller logout");
			Actions act1 = new Actions(driver);
			act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
			}
		
		//Login as a buyer to check the mails and delete them 
		@Test(priority=4206)
		public void BuyerCheckMailAndDelete() throws InterruptedException, IOException
		{
			// Login as a buyer
			LoginPage lp = new LoginPage(driver);
			lp.clickloginlandingbtn();
			logger.info("Providing Login Details");
			lp.setUsername(username2);
			lp.setPassword(password);
			lp.clickloginbtn1();
			logger.info("Log in as Buyer");
			// Go to Message Page
			HomePage homepg = new HomePage(driver);
			logger.info("Click on Messages tab");
			homepg.clkbtnMessages();
			// Select Rate Service mail, Service Delivery mail, seller confirmation and bid sent mail and delete them (created during this testcase)
			logger.info("Delete Rate Service mail");
			MessagePage messagepg1 = new MessagePage(driver);
			messagepg1.clkLatestEntry();
			messagepg1.clkbtnDelete();
			logger.info("Delete Service Delivery mail");
			MessagePage messagepg2 = new MessagePage(driver);
			messagepg2.clkLatestEntry();
			messagepg2.clkbtnDelete();
			logger.info("Delete the 'Seller Confirmation' mail  from inbox");
			MessagePage messagepg3 = new MessagePage(driver);
			messagepg3.clkLatestEntry();
			messagepg3.clkbtnDelete();
			driver.navigate().refresh();
			// Logout from this user 
			logger.info("Buyer logout");
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
			
			logger.info("Completed TP_TC_042");
		}

	
	
}
