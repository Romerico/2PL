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
import com.pageObjects.TransHistoryPage;

//Testcase Description: Transaction History Page > Refund – icon (Positive)
/*Acceptance Criteria : "The Application should display tooltip box by moving mouse on the icon and by clicking on that icon Refund, then the application  should display ""Refund Service"" popup with 2 options - I want a Refund & Cancel.
When user clicks on 'I want a Refund', refund message should be sent successfully.
When user clicks on 'Cancel', no message is sent. " */ 

public class TP_TC_054 extends BaseClass {
	
		@Test(priority = 5401)
		public void SellerLogin_CreateService() throws InterruptedException, IOException {
			logger.info("Started TP_TC_054");
			driver.get(baseURL);
			logger.info("Opened URL");
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
			createsvc.txtTitleField("Maxx Fashions"); // If changing testdata here, change it in all places in test case
			createsvc.txtdescriptionField("One stop Fashion boutique");
			createsvc.clktxtCategoryField();
			createsvc.SelectdrpdownCategory("Beauty & Fashion");
		//	createsvc.clktxtsubCategoryField();
			createsvc.selectdrpdownSubCategory("Clothing");
			createsvc.txtPriceField("50");
			createsvc.SilderBarMaxLimit();
			createsvc.refundValidField("5");
			createsvc.btnSubmitServicePage();
			logger.info("Created Service Successfully");

			// Logout from the seller
			HomePage homepg = new HomePage(driver);
			logger.info("Seller logout");
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		}
	  		
		// Login with other user to buy the service created
		@Test(priority = 5402)
		public void BuyerLogin_SearchService() throws InterruptedException {

			LoginPage lp = new LoginPage(driver);
			lp.clickloginlandingbtn();
			logger.info("Providing Login Details");
			lp.setUsername(username2);
			lp.setPassword(password);
			lp.clickloginbtn1();
			logger.info("Log in as a Buyer ");

			// Click on search box
			logger.info("Click on Search box");
			HomePage homepg = new HomePage(driver);
			homepg.clkbtnSearch();
			// Search for the service based on text value
			logger.info("Search for the specific service");
			SearchService searchpg = new SearchService(driver);
			searchpg.txtSearchValue("Maxx Fashions");
			searchpg.clkSerachButton();
			// Click on the link containing the text value
			logger.info("click on the created service");
			searchpg.clkbtncreatedsvc();
			
			searchpg.btnIwantThis();
			searchpg.clkDisclaimerOption();
			logger.info("Buying the service");
			searchpg.clkBuy();

			logger.info("Buyer logout");
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		}
	  		
		@Test(priority = 5403)
		public void SellerLogin_Accept() throws IOException, InterruptedException {

		//Login with user who is selling the service
		logger.info("Login as a seller");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as Seller");

		//Confirm the service order
		// Check messages in inbox
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();
		// Check whether seller gets Confirm order mail and click on it
		SoftAssert softassertion = new SoftAssert();
		MessagePage messagepg = new MessagePage(driver);
		if (messagepg.gettxtfirstmail().contains("Confirm Order")) {
			logger.info("Seller received Confirm order mail");
			softassertion.assertTrue(true);
			logger.info("Seller received Confirm Order mail!!");
			messagepg.clktxtfirstmail();
			logger.info("Click on Accept");
			messagepg.clkSaleAgreementbox();
			messagepg.clkbtnAccept();
			// Check whether the seller gets the 'Confirm Delivery' mail in inbox and
			// validate the message
			logger.info("Validation of 'Confirm Delivery' message begins");
			MessagePage messagepg1 = new MessagePage(driver);
			// click on the confirm delivery mail
			messagepg1.clktxtfirstmail();
			// Validate the 'Confirm Delivery' message in the mail (seller)
			if (messagepg.getTextMessage().contains("Please confirm delivery of service")) {
				logger.info("The mail in Seller inbox contains Confirm delivery message");
				softassertion.assertTrue(true);
				logger.info("The mail in Seller inbox contains Confirm delivery message!!");
			} else {
				logger.error("The mail in Seller inbox DOESNOT contain Confirm delivery message");
				softassertion.assertTrue(false);
				captureScreen(driver, "CheckSellerConfirmDeliveryMessage");
				logger.info("The mail in Seller inbox DOESNOT contain Confirm delivery message!!");
			}
			// Go back to Messages Page
			messagepg.clkbtnBack();
		} else {
			logger.error("Seller DIDNOT receive Confirm order mail");
			softassertion.assertTrue(true);
			captureScreen(driver, "CheckSellerConfirmDeliveryMail");
			logger.info("Seller DIDNOT receive Confirm Order mail!!");
		}
		softassertion.assertAll();
		// Refresh the page
		driver.navigate().refresh();
		// Logout from this user (username/mars)
		logger.info("Seller logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

		
		@Test(priority = 5404)
		public void BuyerLogin_Refund_Positive() throws IOException, InterruptedException {
			LoginPage lp = new LoginPage(driver);
			lp.clickloginlandingbtn();
			logger.info("Providing Login Details");
			lp.setUsername(username2);
			lp.setPassword(password);
			lp.clickloginbtn1();
			logger.info("Log in as buyer");

			HomePage homepage = new HomePage(driver);
			//Create object at TransHistoryPage	  		
			TransHistoryPage trans = new TransHistoryPage(driver);
			//Find the dollar tool tip for a refund
	 		logger.info("Checking dollar tool tip");
	  		homepage.clkbtnTransHist();
	  		trans.clkandholddollartooltip();
	  		SoftAssert assertion = new SoftAssert();
	  		//Check if the tool tip box is displayed	 
	  		if (trans.isRefundMessageDisplayed()==true) {
	  			assertion.assertTrue(true);
	  			logger.info("Dollar tool tip is displayed");}
	  		else {
	  			assertion.assertTrue(false);
	  			captureScreen(driver, "ValidateRefundIcon");
	  			logger.error("Dollar tool tip is not displayed");
	  		}

	  		//Click dollar tool tip for a refund  		
	  		logger.info("Clicking dollar tool tip");
	  		trans = new TransHistoryPage(driver);
	  		trans.clkdollartooltip();
	  		
	  		//Check if refund popup is opened	  		
	  		logger.info("Checking refund service popup is displayed");
	  		if (trans.isRefundServicePopupDisplayed()==true) {
	  			assertion.assertTrue(true);
	  			logger.info("Refund Popup is displayed");}
	  			else {
		  			assertion.assertTrue(false);
		  			captureScreen(driver, "ValidateRefundIcon");
		  			logger.error("Refund Popup is displayed");
	  			}
//Click Cancel Button			
	  		logger.info("Clicking cancel button");
	  		trans.clkCancelButton();
	  		logger.info("Checking if returns to the correct page");
//Check if returns to Transaction page after click on Cancel Button  		
	  		if (trans.isTransHistoryPageDisplayed()==true) {
	  			assertion.assertTrue(true);
	  			logger.info("Cancel button is ok. Page is displayed");}
	  			else {
	  				assertion.assertTrue (false);
	  				captureScreen(driver, "ValidateRefundIcon");
	  				logger.error("Page is not displayed");}
	  			
	  		//Refresh the page
	  		driver.navigate().refresh();
	  		
//Click dollar tool tip for a refund	
	  		trans = new TransHistoryPage(driver);
	  		trans.clkdollartooltip();
//Confirm refund	  		
	  		logger.info("Clicking I want to refund");
	  		trans.clkIwantarefundButton();

//Check if message that confirms the refund is displayed
	  		logger.info("Checking if message is displayed");
	  		if(trans.isConfirmRefundMessageDisplayed()==true) {
	  			assertion.assertTrue(true);
	  			logger.info("Message is displayed");}
	  			else {
	  				logger.error("Message is not displayed");
	  				captureScreen(driver, "ValidateRefundIcon");
	  				assertion.assertTrue(false);
	  				}
	  		
	  		assertion.assertAll();
	  	//Refresh the page
			driver.navigate().refresh();
			
			// Logout from this user (username/blue)
			logger.info("Buyer logout");
			Actions act = new Actions(driver);
			HomePage homepg = new HomePage(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();

		}
		
		//Login as a Seller and delete the mail and service created
				@Test (priority = 5405)
				public void Seller_DeleteMailandServiceCreated() throws InterruptedException {
				
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username1);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as Seller");
				HomePage homepg = new HomePage(driver);
				// Go to profile page and delete the service (created during this testcase)
							Actions act = new Actions(driver);
							act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
							ProfilePage profilepg = new ProfilePage(driver);
							profilepg.clkbtnDeleteService();
							profilepg.clkbtnIwantToDelete();
							logger.info("Service has been deleted");
							logger.info("Service deleted successfully");
				
				homepg.clkbtnMessages();
				
							// Select the confirm delivery message and New bid message to delete it (created during this testcase)
				MessagePage messagepg = new MessagePage(driver);
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
							// Logout from this user (username/mars)
							logger.info("Seller logout");
							Actions act1 = new Actions(driver);
							act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
								}
				
				//Login as a Buyer and delete the mail received
				@Test (priority = 5406)
				public void Buyer_DeleteMail() throws InterruptedException {
				
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username2);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as Buyer");
				//Go to Messages
				HomePage homepg = new HomePage(driver);
				homepg.clkbtnMessages();
				// Select the Seller confirmation mail to delete it (created during this testcase)
				MessagePage messagepg = new MessagePage(driver);
							logger.info("Delete the Seller Confirmation mail from inbox");
							messagepg.clkLatestEntry();
							messagepg.clkbtnDelete();
							driver.navigate().refresh();
							
							//Refresh the page
							driver.navigate().refresh();
							// Logout from this user (username/blue)
							logger.info("Buyer logout");
							Actions act1 = new Actions(driver);
							act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
							
							logger.info("Completed TP_TC_054");
								}

				}
