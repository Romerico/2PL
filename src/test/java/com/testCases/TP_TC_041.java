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

/* Testcase Description: "This test is to verify whether the seller gets mail when buyer when buyer clicks 'I want this' option and seller offers to 'Deny' the bid
Step 1: Login as Buyer
Step 2: On Live Feed page search for seller service with no bidding option
Step 3: Click on 'I want this' > Buy > Ok
Step 4: Login as a Seller
Step 5: Go to Messages > Open Confirm Order mail
Step 6: Click Deny Purchase
Step 7: Login as a buyer > Check Messages" */

/* Acceptance Criteria: "The following will happen for the test case to be considered successful
AC01-The application should send 'Confirm Order' Mail with options - Deny Purchase & Accept to Seller
AC02- After seller selects to Deny Purchase, Buyer should receive 'Order Denied' mail" */

public class TP_TC_041 extends BaseClass {
 
		//Login as a seller  and create a service
		@Test(priority=4101)
		public void sellerloginAndCreateService2() throws InterruptedException
			{
			logger.info("Started TP_TC_041");
			LoginPage lp = new LoginPage(driver);
					lp.clickloginlandingbtn();
					logger.info("Providing Login Details");
					lp.setUsername(username1);
					lp.setPassword(password);
					lp.clickloginbtn1();
					logger.info("Log in as a Seller ");
					//Create a service
					CreateService createsvc = new CreateService(driver);
					createsvc.clkbtnCreateNew();
					createsvc.clkbtnService();
					logger.info("Fill in Service form with test data");
					createsvc.txtTitleField("Jazy Fashions");  //If changing testdata here, change it in line no.83 & 89 (search criteria)
					createsvc.txtdescriptionField("One stop Fashion boutique");
					createsvc.clktxtCategoryField();
					createsvc.SelectdrpdownCategory("Beauty & Fashion");
		//			createsvc.clktxtsubCategoryField();
					createsvc.selectdrpdownSubCategory("Clothing");
					createsvc.txtPriceField("350");
					createsvc.SilderBarMaxLimit();
					createsvc.refundValidField("35");
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
					}
					softassert.assertAll();
					//Refresh the page
					driver.navigate().refresh();
					
					//Logout from the seller
					HomePage homepg = new HomePage(driver);
					logger.info("Seller logout");
					Actions act = new Actions(driver);
					act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
				}
		//Login as a buyer; search for a bid and select "I want this"
		@Test (priority=4102)
		public void BuyerSearchbidnSelectIwantThis() throws InterruptedException {
		//Login as a buyer 
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as Buyer");
		
		//Click on search box
		logger.info("Click on Search box");
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnSearch();
		
		//Search for the service based on text value
		logger.info("Search for the specific service");
		SearchService searchpg = new SearchService(driver);
		searchpg.txtSearchValue("Jazy Fashions");
		searchpg.clkSerachButton();
		
		//Click on the link containing the text value
		logger.info("click on the created service");
		searchpg.clkbtncreatedsvc();
		
		//Select 'I want this' option
		logger.info("Click on 'I want this' button");
		searchpg.btnIwantThis();
		
		//Select the checkbox and 'buy' button
		searchpg.clkDisclaimerOption();
		searchpg.clkBuy();
		searchpg.clkbtnOk();
		//Refresh the page
				driver.navigate().refresh();
		
		//Logout from this user (username/blue)
		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		}
		
		//Login as a seller (username/mars); check messages and Select to decline
		@Test (priority=4103)
		public void CheckSellerConfirmOrder() throws IOException, InterruptedException {
		//Login as a seller (username/mars)
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as seller");
		
		//Check messages in inbox
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();
		
		//Check whether seller gets Confirm order mail and click on it
		SoftAssert softassertion = new SoftAssert();
		MessagePage messagepg = new MessagePage(driver);
		if(messagepg.gettxtfirstmail().contains("Confirm Order"))
		{
			logger.info("Test Passed: Seller received Confirm order mail");
			softassertion.assertTrue(true);
			logger.info("Seller received Confirm Order mail!!");
			messagepg.clktxtfirstmail();
			logger.info("Click on deny purchase");
			messagepg.clkbtnDenyPurchase();
			
			//Refresh the page
			driver.navigate().refresh();
			
			//Check whether the seller gets the 'Confirm Order' mail in inbox and validate the message
			logger.info("Validation of 'Confirm Order' message begins");
			
			MessagePage messagepg1 = new MessagePage(driver);
			//click on the confirm order mail
			messagepg1.clktxtfirstmail();
			
			//Validate the 'Confirm Order' message in the mail (seller)
				if (messagepg.getTextMessage().contains("has been cancelled"))
					{
					logger.info("Test Passed: The mail in Seller inbox contains 'Service has been cancelled' message");
					softassertion.assertTrue(true);
					logger.info("The mail in Seller inbox contains 'Service has been cancelled' message!!");
					}
				else
					{
					logger.error("Test Failed: The mail in Seller inbox DOESNOT contain 'Service has been cancelled' message");
					softassertion.assertTrue(false);
					captureScreen(driver,"CheckSellerConfirmOrderMessage");
					logger.info("The mail in Seller inbox DOESNOT contain 'Service has been cancelled' message!!");
					}
			
				//Go back to Message Page
				logger.info("Click on message tab");
				MessagePage messagepg2 = new MessagePage(driver);
				messagepg2.clkbtnBack();
								
				//Go to profile page and delete the service (created during this testcase)
				Actions act = new Actions(driver);
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
				
				ProfilePage profilepg = new ProfilePage(driver);
				profilepg.clkbtnDeleteService();
				profilepg.clkbtnIwantToDelete();
				
				logger.info("Service has been deleted");
				logger.info("Service deleted successfully");
				
				//Refresh the page
				driver.navigate().refresh();
				
				//Go to Message Page
				HomePage homepg2 = new HomePage(driver);
				homepg2.clkbtnMessages();
				
				
				//Select the Confirm order message and delete it (created during this testcase)
				logger.info("Delete the Confirm order mail from inbox");
				MessagePage messagepg3 = new MessagePage(driver);
				messagepg3.clkLatestEntry();
				messagepg3.clkbtnDelete();
			
				}		
		else
		{
			logger.error("Seller DIDNOT receive Confirm order mail");
			softassertion.assertTrue(true);
			captureScreen(driver,"CheckSellerConfirmOrderMail");
			logger.info("Seller DIDNOT receive Confirm Order mail!!");
		}
		softassertion.assertAll();
		//Refresh the page
		driver.navigate().refresh();
		
		//Logout from this user (username/mars)
		logger.info("Seller logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		}
	
		//Login as a buyer and check Order Denied message in buyer inbox
		@Test (priority=4104)
		public void CheckBuyerOrderDenied() throws IOException, InterruptedException {
		//Login as a buyer 
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as buyer");
			
		//Check messages in inbox
		HomePage homepg1 = new HomePage(driver);
		homepg1.clkbtnMessages();
			
		SoftAssert softassertion = new SoftAssert();
		//Validate: The buyer gets the 'Order Denied' message in inbox
		MessagePage messagepg = new MessagePage(driver);
		
		if(messagepg.gettxtfirstmail().contains("Order Denied"))
		{
			logger.info("Test Passed: The buyer gets the Order Denied mail in Inbox");
			softassertion.assertTrue(true);
			logger.info("The buyer got the 'Order Denied' mail in his Inbox!!");
			//Click on the mail
			messagepg.clktxtfirstmail();
			//Validate the 'Order Denied' message in the mail (buyer)
			if (messagepg.getTextMessage().contains("Seller has denied your order"))
			{
				logger.info("Test Passed: The mail in buyer inbox contains 'Order Denied' message");
				softassertion.assertTrue(true);
				logger.info("The mail in buyer inbox contains 'Order Denied' message!!");
			}
			else
			{
				logger.error("Test Failed: The mail in buyer inbox DOESNOT contain 'Order Denied' message");
				softassertion.assertTrue(false);
				captureScreen(driver,"CheckBuyerOrderDeniedMessage");
				logger.info("The mail in buyer inbox DOESNOT contain 'Order Denied' message!!");
			}
			
			//Go back to Message page
			logger.info("Click on message tab");
			MessagePage messagepg1 = new MessagePage(driver);
			messagepg1.clkbtnBack();
			
			//Select the 'Order denied' mail and delete it (created during this testcase)
			messagepg.clkLatestEntry();
			messagepg.clkbtnDelete();
			
		}
		else
			{
			logger.error("Test Failed: The buyer DIDNOT get the 'Order Denied' mail in Inbox");
			softassertion.assertTrue(false);
			captureScreen(driver,"CheckBuyerOrderDeniedMail");
			logger.info("The buyer DIDNOT get the 'Order Denied' mail in his Inbox!!");
			}
		
		softassertion.assertAll();
		
		//Refresh the page
		driver.navigate().refresh();
		
		//Logout from this user (username/blue)
		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
		
		logger.info("Completed TP_TC_041");
		}
			
}	
		
		
		
		