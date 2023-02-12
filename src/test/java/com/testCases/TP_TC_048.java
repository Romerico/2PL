package com.testCases;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MessagePage;
import com.pageObjects.ProfilePage;
import com.pageObjects.SearchService;
import com.pageObjects.ViewNeedPage;

/* Testcase Description: "This test case is whether the buyer gets notified when sends sends a bid through 'I can do this'
1. Login with Seller credentials 
2. On Live Feed page search for any NEED
3. Click on 'I can do this'
4. Enter your Price, Select your service and message 
5. Click Send Bid
6. Login as a Buyer
7. Go to Messages > Open New Bid
8. Click Make an Offer
9. Login as a Seller > Check Messages" */

/* Acceptance Criteria: "The following will happen for the test case to be considered successful
AC01-After click on Send button, Seller should have Bid sent mail in his inbox
AC02- Buyer should receive New Bid mail with 3 options - Decline Bid, Make an Offer, Accept
AC03-After Buyer makes an offer, seller should receive Bid offer mail with 3 options - Decline Bid, Accept and Make an Offer in the inbox" */

public class TP_TC_048 extends BaseClass {
	
	@Test(priority = 4801)
	public void BuyerLogin_CreateNeed() throws IOException, InterruptedException {
		logger.info("Started TP_TC_048");
		// Login as a buyer to create need
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickloginlandingbtn();
		logger.info("Logging In As a Buyer");
		loginPage.setUsername(username1);
		loginPage.setPassword(password);
		loginPage.clickloginbtn1();
		logger.info("Creating a new Need");
		CreateNeed createneed = new CreateNeed(driver);
		createneed.clkbtnCreateNew();
		createneed.clkbtnNeed();
		logger.info("Fill in Need form with test data");
		createneed.txtTitleField("Driving Instruction1");
		createneed.txtdescriptionField("Need Driving Instruction1");
		createneed.clktxtCategoryField();
		createneed.SelectdrpdownCategory("Automobile");
	//	createneed.clktxtsubCategoryField();
		createneed.selectdrpdownSubCategory("Aviation");
		createneed.txtPriceField("100");
		createneed.btnSubmitNeedPage();
		logger.info("Created Need Successfully");
		// Buyer logging out.
		logger.info("Buyer logging out");
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}
	// Login in as a Seller and search need and select 'I can do this'
			@Test(priority = 4802)
			public void SellerLogin_Searchneed_Icandothis() throws IOException, InterruptedException {
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username2);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as a Seller");

				// Searching a need previously created by Buyer
				logger.info("Searching a Need created by Buyer before");
				SearchService searchService = new SearchService(driver);
				searchService.clkserviceBox();
				searchService.clickNeeds();
				searchService.txtSearchValue("Driving Instruction1");
				searchService.clkSerachButton();
				searchService.clickLinkInResultTable();

				SoftAssert softAssertion = new SoftAssert();
				// verify "I can do this button is available on Need page and Clickable"
				logger.info("Verify That I can do this Button is displayed");
				ViewNeedPage viewNeedPage = new ViewNeedPage(driver);
				if (viewNeedPage.verifyICanDoThisBtn()) {
					softAssertion.assertTrue(true);
					logger.info("Test Passed: 'I Can Do This' Btn Is Displayed!");
				} else {
					softAssertion.assertTrue(false);
					logger.error("Test Failed: 'I Can Do This' Btn Is NOT Displayed!");
					captureScreen(driver, "VerifyICanDoThisButtonDisplayed");
				}

				logger.info("Verify That I can do this Button is clickable");
				if (viewNeedPage.verifyICanDoThis()) {
					softAssertion.assertTrue(true);
					logger.info("Test Passed: 'I Can Do This' Btn Is Clickable");
				} else {
					softAssertion.assertTrue(false);
					logger.error("Test Failed: 'I Can Do This' Btn Is NOT Clickable");
					captureScreen(driver, "VerifyICanDoThisButtonClickable");
				}
				// Clicking On I can do this Button
				logger.info("Clicking On I can do this Button");
				viewNeedPage.clickICanDoThisBtn();
				// Click On Sale Agreement and Send Bid
				logger.info("Clicking On Sale Agreement");
				viewNeedPage.clickSaleAgreement();
				logger.info("Clicking On Send Button");
				viewNeedPage.clickSend();
				viewNeedPage.clickOk();

				// Logout from Seller
				HomePage homepg = new HomePage(driver);
				logger.info("Seller logout");
				Actions act = new Actions(driver);
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
			}

			@Test(priority=4803)
			public void BuyerLogin_CheckNewBidMsg_MakeAnOffer() throws IOException, InterruptedException {
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username1);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as a Buyer");

				HomePage homepage = new HomePage(driver);
				logger.info("Clicking On messages");
				homepage.clkbtnMessages();
				// open an email with subject Bid Sent
				logger.info("Opening an Email with subject New Bid");
				MessagePage messagepg = new MessagePage(driver);

				SoftAssert softAssertion = new SoftAssert();
				// Validate whether the buyer received the new bid mail sent by the seller
				logger.info("Click on the New bid mail sent by the seller");
				if (messagepg.gettxtfirstmail().contains("New Bid")) {
					logger.info("Test Passed: Buyer received New Bid mail");
					softAssertion.assertTrue(true);
					messagepg.clktxtfirstmail();
					logger.info("Buyer received New Bid mail!");

					// Make an offer to the seller
					logger.info("Select to Make an Offer");
					messagepg.clkSaleAgreementbox();
					messagepg.clkbtnMakeOffer();
					//Submit bid details
					messagepg.clearPricefield();
					messagepg.txtPrice("8");
					messagepg.clkbtnSaleAgreementpopup();
					messagepg.clkbtnSendpopup();
					
				} else {
					logger.error("Test Failed: Buyer DIDNOT receive New Bid mail");
					softAssertion.assertTrue(false);
					captureScreen(driver,"NewBidMail");
					logger.info("Buyer DIDNOT receive New Bid mail!");
				}

				softAssertion.assertAll();
				// Refresh the page
				driver.navigate().refresh();

				// Logout from the buyer
				Actions act = new Actions(driver);
				logger.info("Buyer logging out");
				HomePage homepg1 = new HomePage(driver);
				act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
			}

			@Test(priority = 4804)
			public void SellerLogin_CheckBidOfferMail() throws IOException, InterruptedException {
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username2);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as a Seller");

				// Verify whether Seller received Bid offer mail sent by the Buyer
				// Go to Messages
				logger.info("Go to Messages");
				HomePage homepg = new HomePage(driver);
				homepg.clkbtnMessages();
				MessagePage messagepg = new MessagePage(driver);
				//Verify whether the Seller has received Bid Offer mail from the buyer
				SoftAssert softAssertion = new SoftAssert();
				if (messagepg.gettxtfirstmail().contains("Bid Offer")) 
				{
					logger.info("Test Passed: Seller received Bid Offer Mail");
					softAssertion.assertTrue(true);
					logger.info("Seller received Bid Offer Mail");
					
					//Click on the Bid Offer mail to read the contents
					logger.info("Click on Bid Offer Mail");
					messagepg.clktxtfirstmail();

					//Check whether the bid offer mail contains Sale agreement checkbox with 3 buttons - Accept, Decline, Make an Offer
					logger.info("Check whether Bid offer mail has Sale agreement checkbox with 3 buttons - Accept, Decline, Make an Offer!");
					
					//1. Validate - Sale agreement check box is displayed and enabled
					logger.info("Validating whether Sale agreement check box is displayed & enabled");
					
					//Check whether sale agreement check box is displayed
					if(messagepg.displaysSaleAgreementBidOffer() == true)
					{
						logger.info("Test Passed: Sale agreement check box is displayed");
						softAssertion.assertTrue(true);
						logger.info("Sale Agreement checkbox is displayed!!");
					}
					else
					{
						logger.error("Test Failed: Sale agreement check box is NOT displayed");
						captureScreen(driver,"CheckSaleAgreementboxDisplayed");
						softAssertion.assertTrue(false);
						logger.info("Sale Agreement checkbox is NOT displayed!!");
					}
					
					//Check whether sale agreement check box is enabled
					if(messagepg.enabledSaleAgreementBidOffer() == true)
					{
						logger.info("Test Passed: Sale agreement check box is enabled");
						softAssertion.assertTrue(true);
						logger.info("Sale Agreement checkbox is enabled!!");
					}
					else
					{
						logger.error("Test Failed: Sale agreement check box is NOT enabled");
						captureScreen(driver,"CheckSaleAgreementboxEnabled");
						softAssertion.assertTrue(false);
						logger.info("Sale Agreement checkbox is NOT enabled!!");
					}
				
					//Refresh the page
					driver.navigate().refresh();
					
				//2. Validate - Make Offer button is displayed and enabled
					logger.info("Validating whether 'Make offer button' is displayed & enabled");
					//Check whether Make Offer button is displayed
					if(messagepg.displaysbtnMakeOffer() == true)
					{
						logger.info("Test Passed: Make Offer button is displayed");
						softAssertion.assertTrue(true);
						logger.info("Make Offer button is displayed!!");
					}
					else
					{
						logger.error("Test Failed: Make Offer button is NOT displayed");
						captureScreen(driver,"CheckMakeOfferButtonDisplayed");
						softAssertion.assertTrue(false);
						logger.info("Make Offer button is NOT displayed!!");
					}
					
					//Check whether Make offer button is enabled
					if(messagepg.enabledbtnMakeOffer() == true)
					{
						logger.info("Test Passed: Make Offer button is enabled");
						softAssertion.assertTrue(true);
						logger.info("Make Offer button is enabled!!");
					}
					else
					{
						logger.error("Test Failed: Make Offer button is NOT enabled");
						captureScreen(driver,"CheckMakeOfferButtonEnabled");
						softAssertion.assertTrue(false);
						logger.info("Make Offer button is NOT enabled!!");
					}
					
					//Refresh the page
					driver.navigate().refresh();
					
					//3. Validate - Accept button is displayed and enabled
					logger.info("Validating whether Accept button is displayed & enabled");
						
						//Check whether Accept button is displayed
						if(messagepg.displaysbtnAcceptBidOffer() == true)
						{
							logger.info("Test Passed: Accept button is displayed");
							softAssertion.assertTrue(true);
							logger.info("Accept button is displayed!!");
						}
						else
						{
							logger.error("Test Failed: Accept button is NOT displayed");
							captureScreen(driver,"CheckAcceptButtonDisplayed");
							softAssertion.assertTrue(false);
							logger.info("Accept button is NOT displayed!!");
						}
						//Check whether Accept button is enabled
						if(messagepg.enabledbtnAcceptBidOffer() == true)
						{
							logger.info("Test Passed: Accept button is enabled");
							softAssertion.assertTrue(true);
							logger.info("Accept button is enabled!!");
						}
						else
						{
							logger.error("Test Failed: Accept button is NOT enabled");
							captureScreen(driver,"CheckAcceptButtonEnabled");
							softAssertion.assertTrue(false);
							logger.info("Accept button is NOT enabled!!");
						}
				
						//Refresh the page
						driver.navigate().refresh();
						
					
					//4. Validate - Decline button is displayed and enabled & signout 
				  		logger.info("Validating whether Decline button is displayed & enabled");
				  		//Check whether Decline button is displayed
						if(messagepg.displaysbtnDecline() == true)
						{
							logger.info("Test Passed: Decline button is displayed");
							softAssertion.assertTrue(true);
							logger.info("Decline button is displayed!!");
						}
						else
						{
							logger.error("Test Failed: Decline button is NOT displayed");
							captureScreen(driver,"CheckDeclineButtonDisplayed");
							softAssertion.assertTrue(false);
							logger.info("Decline button is NOT displayed!!");
						}
						//Check whether Decline button is enabled
						if(messagepg.enabledbtnDecline() == true)
						{
							logger.info("Test Passed: Decline button is enabled");
							softAssertion.assertTrue(true);
							logger.info("Decline button is enabled!!");
							
							//Click on decline button 
							logger.info("Selected Decline button");
							messagepg.clkbtnDecline();
						}
						else
						{
							logger.error("Test Failed: Decline button is NOT enabled");
							captureScreen(driver,"CheckDeclineButtonEnabled");
							softAssertion.assertTrue(false);
							logger.info("Decline button is NOT enabled!!");
						}
				}	else
					{
						softAssertion.assertTrue(false);
						logger.error("Test Failed: Seller DIDNOT Bid Offer Mail");
						captureScreen(driver, "BidOfferSellerMail");
					}
			
				softAssertion.assertAll();

				// Refresh the page
				driver.navigate().refresh();

				// Logout from Seller
				logger.info("Seller logout");
				Actions act = new Actions(driver);
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
			}

			@Test(priority = 4805)
			public void BuyerLogin_DeleteCreatedNeedandMsgs() throws IOException, InterruptedException {
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username1);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as a Buyer");
				
				// Go to Profile page
				HomePage homepg = new HomePage(driver);
				Actions act = new Actions(driver);
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();

				// Delete the need created during this testcase
				ProfilePage profilepg = new ProfilePage(driver);
				profilepg.clkbtnDeleteNeed();
				profilepg.clkbtnIwantToDeleteNeed();
				logger.info("Need has been deleted");
				logger.info("Need deleted successfully");

				//Delete the mails generated during this testcase
				logger.info("Go to Messages");
				HomePage homepg1 = new HomePage(driver);
				homepg1.clkbtnMessages();
				logger.info("Delete Bid Canceled from inbox");
				MessagePage messagepg2 = new MessagePage(driver);
				messagepg2.clkLatestEntry();
				messagepg2.clkbtnDelete();
				driver.navigate().refresh();
				logger.info("Delete New Bid from inbox");
				MessagePage messagepg3 = new MessagePage(driver);
				messagepg3.clkLatestEntry();
				messagepg3.clkbtnDelete();
								
				// Refresh the page
				driver.navigate().refresh();

				// Logout from Buyer
				logger.info("Buyer logout");
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
			}

			@Test(priority = 4806)
			public void SellerLogin_DeleteMails() throws InterruptedException {
			LoginPage lp = new LoginPage(driver);
			lp.clickloginlandingbtn();
			logger.info("Providing Login Details");
			lp.setUsername(username2);
			lp.setPassword(password);
			lp.clickloginbtn1();
			logger.info("Log in as a Seller");

			// Delete the mails generated during this testcase
			logger.info("Go to Messages");
			HomePage homepg = new HomePage(driver);
			homepg.clkbtnMessages();

			logger.info("Delete the Bid Canceled from inbox");
			MessagePage messagepg4 = new MessagePage(driver);
			messagepg4.clkLatestEntry();
			messagepg4.clkbtnDelete();
			driver.navigate().refresh();
			logger.info("Delete the Bid Offer from inbox");
			MessagePage messagepg2 = new MessagePage(driver);
			messagepg2.clkLatestEntry();
			messagepg2.clkbtnDelete();
			driver.navigate().refresh();
			logger.info("Delete the bid sent from inbox");
			MessagePage messagepg3 = new MessagePage(driver);
			messagepg3.clkLatestEntry();
			messagepg3.clkbtnDelete();

			// Refresh the page
			driver.navigate().refresh();

			// Logout from Seller
			logger.info("Seller logout");
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
			logger.info("Completed TP_TC_048");
			}
		}

