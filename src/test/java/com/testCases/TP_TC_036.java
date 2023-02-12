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

/* Testcase Description: "This test is to verify whether the seller gets mail when buyer sends a bid through 'Lets Negotiate'
and seller offers to 'Decline' the bid
Step 1: Login as Buyer
Step 2: On Live Feed page search for any seller's service
Step 3: Click on 'Lets Negotiate'
Step 4: Enter Price and tick sale agreement
Step 5: Click Send
Step 6: Login as a Seller
Step 7: Go to Messages > Open New Bid mail
Step 8: Click Decline
Step 9: Login as a buyer > Check Messages" */

/* Acceptance Criteria: "The following will happen for the test case to be considered successful
AC01-Buyer should have 'Bid Sent' Mail in his inbox
AC02-Seller should receive 'New Bid' mail with 3 options - Decline Bid, Accept, Make an offer
AC03-Once seller declines the bid, Both Buyer and seller should receive Bid Canceled Mail in their inbox" */

public class TP_TC_036 extends BaseClass {

			//Login as a seller and create a service 
			@Test(priority=3601)
			public void sellerloginAndCreateService() throws InterruptedException
			{
				logger.info("Started TP_TC_036");
				driver.get(baseURL);
				logger.info("Opened URL");
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
				createsvc.txtTitleField("Jazy Fashions");
				createsvc.txtdescriptionField("One stop Fashion boutique");
				createsvc.clktxtCategoryField();
				createsvc.SelectdrpdownCategory("Beauty & Fashion");
			//	createsvc.clktxtsubCategoryField();
				createsvc.selectdrpdownSubCategory("Clothing");
				createsvc.txtPriceField("350");
				createsvc.SilderBarMaxLimit();
				createsvc.refundValidField("35");
				createsvc.btnSubmitServicePage();
				logger.info("Created Service Successfully");
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
		
		
		//Login as a buyer, Search for a bid and Select "Lets Negotiate"
		@Test(priority=3602)
		public void BuyerBidsearchAndNegotiate() throws InterruptedException, IOException
		{
			LoginPage lp = new LoginPage(driver);
			lp.clickloginlandingbtn();
			logger.info("Providing Login Details");
			lp.setUsername(username2);
			lp.setPassword(password);
			lp.clickloginbtn1();
			logger.info("Log in as a Buyer ");
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
			//Click on negotiate button
			logger.info("click on negotiate and enter values");
			searchpg.btnLetsNegotiate();
			searchpg.txtPriceBox();
			searchpg.textPriceBoxCharValue("200");
			searchpg.clkSaleAgreement();
			searchpg.btnSend();
			searchpg.clkbtnOk();
			//Refresh the page
			driver.navigate().refresh();
			//Logout from this user
			logger.info("Buyer logout");
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		}
			
		//Login as a seller; check messages in inbox and click on new bid offer
		//Select to Decline the New Bid, check whether the seller gets Bid canceled Mail in his inbox and validate the message contained in Mail
		@Test(priority=3603)
		public void CheckSellerNewbidCancel() throws InterruptedException, IOException
			{
			logger.info("Login as a seller");
			LoginPage lp = new LoginPage(driver);
			lp.clickloginlandingbtn();
			logger.info("Providing Login Details");
			lp.setUsername(username1);
			lp.setPassword(password);
			lp.clickloginbtn1();
			logger.info("Log in as Seller");
			
			//Check messages in inbox
			logger.info("Click on message tab");
			HomePage homepg = new HomePage(driver);
			homepg.clkbtnMessages();
			MessagePage messagepg = new MessagePage(driver);
			
			SoftAssert softassertion = new SoftAssert();
			//Check whether Seller gets New Bid mail and click on the new bid offer by the buyer and Select to Decline Bid
			logger.info("Click on the new bid by the buyer");
			if(messagepg.gettxtfirstmail().contains("New Bid"))
			{
				logger.info("Test Passed: Seller received New Bid mail");
				softassertion.assertTrue(true);
				messagepg.clktxtfirstmail();
				logger.info("Seller received New Bid mail!!");
				logger.info("Select Decline Bid");
				messagepg.clkbtnDecline();
				
				//Refresh the page
				driver.navigate().refresh();
				
				//Go to Messages Page
				logger.info("Click on message button");
				HomePage homepg1 = new HomePage(driver);
				homepg1.clkbtnMessages();
				
				//Validate : The seller gets the 'Bid canceled' mail in inbox 
				logger.info("Validation of 'Bid Canceled' mail in seller inbox begins");
				MessagePage messagepg1 = new MessagePage(driver);
				if(messagepg1.gettxtfirstmail().contains("Bid Canceled"))
				{
				logger.info("Test Passed: The seller received the bid canceled mail in Inbox");
				softassertion.assertTrue(true);
				logger.info("Seller received 'bid canceled' mail in Inbox!!");
				
				//Click on the message - 'Bid Canceled'
				messagepg1.clktxtfirstmail();
				
				//Validate the 'Bid cancel' message in the mail (seller)
				if (messagepg1.getTextMessage().contains("You have successfully denied the bid"))
					{
						logger.info("Test Passed: The mail in Seller inbox contains 'bid canceled' message");
						softassertion.assertTrue(true);
						logger.info("The mail in Seller inbox contains 'bid canceled' message!!");
					}
				else
					{
						logger.error("Test Failed: The mail in Seller inbox DOESNOT contain 'bid canceled' message");
						softassertion.assertTrue(false);
						captureScreen(driver,"CheckSellerNewbidCancelMessage");
						logger.info("The mail in Seller inbox DOESNOT contain 'bid canceled' message!!");
					}
				//Go back to Message page
				messagepg1.clkbtnBack();
				
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
				
				//Select the bid cancelled message and New bid message to delete it (created during this testcase)
				logger.info("Delete the bid cancelled from inbox");
				MessagePage messagepg2 = new MessagePage(driver);
				messagepg2.clkLatestEntry();
				messagepg2.clkbtnDelete();
				driver.navigate().refresh();
				logger.info("Delete the new bid from inbox");
				MessagePage messagepg3 = new MessagePage(driver);
				messagepg3.clkLatestEntry();
				messagepg3.clkbtnDelete();
				}
				else
				{
					logger.error("Test Failed: The seller DIDNOT get the bid cancelled mail in Inbox");
					softassertion.assertTrue(false);
					captureScreen(driver,"CheckSellerNewbidCancelMail");
					logger.info("The seller DIDNOT get the 'Bid cancelled' mail in Inbox!!");
				}
			}
			else
			{
				logger.error("Test Failed: Seller DIDNOT receive New Bid mail");
				softassertion.assertTrue(false);
				captureScreen(driver,"CheckNewbidmail");
				logger.info("Seller didnot receive New Bid mail!!");
			}
						
			softassertion.assertAll();
			//Refresh the page
			driver.navigate().refresh();
					
			//Logout from this user (username/mars)
			logger.info("Seller logout");
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
			}
			
			//Login as a buyer and check the Bid cancelled message in buyer inbox
			@Test(priority=3604)
			public void CheckBuyerNewBidCancel() throws InterruptedException, IOException
			{
				//Login as a buyer (username/blue)
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username2);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as Buyer");
				
				 //Go to Message Page
				HomePage homepg = new HomePage(driver);
				logger.info("Click on Messages tab");
				homepg.clkbtnMessages();
								
				SoftAssert softassertion = new SoftAssert();
				//Validate: The buyer gets the 'Bid cancelled' mail in inbox
				MessagePage messagepg = new MessagePage(driver);
				if(messagepg.gettxtfirstmail().contains("Bid Canceled"))
				{
				logger.info("The buyer gets the bid canceled mail in Inbox");
				softassertion.assertTrue(true);
				logger.info("The buyer got the 'Bid cancelled' mail in Inbox!!");
				//Click on the mail
				messagepg.clktxtfirstmail();
				//Validate the 'Bid cancel' message in the mail (buyer)
				if (messagepg.getTextMessage().contains("has been canceled"))
					{
						logger.info("Test Passed: The mail in buyer inbox contains 'bid canceled' message");
						softassertion.assertTrue(true);
						logger.info("The mail in buyer inbox contains 'bid canceled' message!!");
					}
				else
					{
						logger.error("Test Failed: The mail in buyer inbox DOESNOT contain 'bid canceled' message");
						softassertion.assertTrue(false);
						captureScreen(driver,"CheckBuyerBidCancelMessage");
						logger.info("The mail in buyer inbox DOESNOT contain 'bid canceled' message!!");
					}
				//Go back to Message Page
				messagepg.clkbtnBack();
				
				//Select the bid cancelled and bid sent message and delete it (created during this testcase)
				logger.info("Delete the bid cancelled  from inbox");
				messagepg.clkLatestEntry();
				messagepg.clkbtnDelete();
				driver.navigate().refresh();
				logger.info("Delete bid sent message");
				MessagePage messagepg1 = new MessagePage(driver);
				messagepg1.clkLatestEntry();
				messagepg1.clkbtnDelete();
				}
				else
				{
					logger.error("Test Failed: The buyer DIDNOT get the bid cancelled mail in Inbox");
					softassertion.assertTrue(false);
					captureScreen(driver,"CheckBuyerBidCancelMail");
					logger.info("The buyer DIDNOT get the 'Bid cancelled' mail in his Inbox!!");
				}
				softassertion.assertAll();
				
				//refresh the page
				driver.navigate().refresh();			
				//Logout from this user (username/blue)
				logger.info("Buyer logout");
				Actions act = new Actions(driver);
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
				
				logger.info("Completed TP_TC_036");
			}
		
	
}
