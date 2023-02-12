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
and seller offers to 'Accept' the bid
Step 1: Login as Buyer
Step 2: On Live Feed page search for any seller's service
Step 3: Click on 'Lets Negotiate'
Step 4: Enter Price and tick sale agreement
Step 5: Click Send
Step 6: Login as a Seller
Step 7: Go to Messages > Open New Bid mail
Step 8: Click to make an offer > Enter price and send
Step 9: Login as a buyer > Check Messages */

/* Acceptance Criteria: "The following will happen for the test case to be considered successful
AC01-Buyer should have 'Bid Sent' Mail in his inbox
AC02-Seller should receive 'New Bid' mail with 3 options - Decline Bid, Accept, Make an offer
AC03-Once seller makes an offer, buyer should receive Bid Offer mail in his inbox" */

public class TP_TC_038 extends BaseClass {
	
	// Login as a seller and create a service by name 
		@Test(priority = 3801)
		public void sellerloginnCreateService() throws InterruptedException {
			logger.info("Started TP_TC_038");
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
			createsvc.txtTitleField("Jazy Fashions");  //If changing testdata here, change it in all places in test case
			createsvc.txtdescriptionField("One stop Fashion boutique");
			createsvc.clktxtCategoryField();
			createsvc.SelectdrpdownCategory("Beauty & Fashion");
		//	createsvc.clktxtsubCategoryField();
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
			// Logout from the seller
			HomePage homepg = new HomePage(driver);
			logger.info("Seller logout");
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		}

	//Login as a buyer, Search for a bid and Select "Lets Negotiate"
		@Test(priority=3802)
		public void BuyerBidsearchnNegotiate() throws InterruptedException {
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
		searchpg.textPriceBoxCharValue("150");
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
		
		//Login as a seller; check new bid mail and select 'Make an Offer' option
		@Test(priority=3803)
		public void CheckMailToMakeanOffer() throws IOException, InterruptedException {
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
			//Check whether Seller gets New Bid mail and click on the new bid offer by the buyer and Select to 'Make an Offer'
			logger.info("Click on the new bid by the buyer");
			if(messagepg.gettxtfirstmail().contains("New Bid"))
			{
				logger.info("Test Passed: Seller received New Bid mail");
				softassertion.assertTrue(true);
				messagepg.clktxtfirstmail();
				logger.info("Seller received New Bid mail!!");
				logger.info("Select to Make an Offer");
				messagepg.clkbtnMakeOffer();
				//Validate - Whether 'Submit your bid' Pops up
				if(messagepg.getSubmityourBidpopup().contains("SUBMIT YOUR BID"))
				{
					logger.info("Test Passed: Submit your bid popup appears");
					softassertion.assertTrue(true);
					logger.info("'Submit your bid' pop-up appears!!");
					//Validate - Whether popup has mandatory 'Price' field is displayed
					if(messagepg.displayPricefield() == true)
					{
						logger.info("Test Passed: Price field is displayed");
						softassertion.assertTrue(true);
						logger.info("Price field is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Price field is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplayPriceField");
						logger.info("Price field is NOT displayed in the popup");
					}
					//Validate - Whether popup has mandatory 'Price' field is enabled
					if(messagepg.enabledPricefield() == true)
					{
						logger.info("Test Passed: Price field is enabled");
						softassertion.assertTrue(true);
						logger.info("Price field is enabled in the popup");
						
						//Pass test data to make an offer of price 250
						messagepg.clearPricefield();
						messagepg.txtPrice("250");
					}
					else
					{
						logger.error("Test Failed: Price field is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledPriceField");
						logger.info("Price field is NOT enabled in the popup");
					}
					//Validate - whether popup has optional Message field is displayed
					if(messagepg.displaytxtMessage() == true)
					{
						logger.info("Test Passed: Message field is displayed");
						softassertion.assertTrue(true);
						logger.info("Message field is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Message field is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplayMessageField");
						logger.info("Message field is NOT displayed in the popup");
					}
					//Validate - Whether popup has mandatory 'Message' field is enabled
					if(messagepg.enabledtxtMessage() == true)
					{
						logger.info("Test Passed: Message field is enabled");
						softassertion.assertTrue(true);
						logger.info("Message field is enabled in the popup");
						
						//Pass optional message in the popup
						messagepg.txtOptionalMessage("This is optional message!!");
					}
					else
					{
						logger.error("Test Failed: Message field is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledMessageField");
						logger.info("Message field is NOT enabled in the popup");
					}
					//Validate - whether sale agreement checkbox is displayed
					if(messagepg.displaySaleAgreementpopup() == true)
					{
						logger.info("Test Passed: Sale agreement checkbox is displayed");
						softassertion.assertTrue(true);
						logger.info("Sale agreement checkbox is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Sale agreement checkbox is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplaySaleAgreementPopup");
						logger.info("Sale agreement checkbox is NOT displayed in the popup");
					}
					//Validate - whether sale agreement checkbox is enabled
					if(messagepg.enabledSaleAgreementpopup() == true)
					{
						logger.info("Test Passed: Sale agreement checkbox is enabled");
						softassertion.assertTrue(true);
						logger.info("Sale agreement checkbox is enabled in the popup");
						//Click on sale agreement checkbox
						messagepg.clkbtnSaleAgreementpopup();
					}
					else
					{
						logger.error("Test Failed: Sale agreement checkbox is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledSaleAgreementPopup");
						logger.info("Sale agreement checkbox is NOT enabled in the popup");
					}
					
					//Validate - whether 'Cancel' button is displayed
					if(messagepg.displaybtnCancelpopup() == true)
					{
						logger.info("Test Passed: Cancel button is displayed");
						softassertion.assertTrue(true);
						logger.info("Cancel button is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Cancel button is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplayCancelButtonPopup");
						logger.info("Cancel button is NOT displayed in the popup");
					}
					//Validate - whether 'Cancel' button is enabled
					if(messagepg.enabledbtnCancelpopup() == true)
					{
						logger.info("Test Passed: Cancel button is enabled");
						softassertion.assertTrue(true);
						logger.info("Cancel button is enabled in the popup");
					}
					else
					{
						logger.error("Test Failed: Cancel button is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledCancelButtonPopup");
						logger.info("Cancel button is NOT enabled in the popup");
					}
					//Validate - whether 'Send' button is displayed
					if(messagepg.displaybtnSendpopup() == true)
					{
						logger.info("Test Passed: Send button is displayed");
						softassertion.assertTrue(true);
						logger.info("Send button is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Send button is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplaySendButtonPopup");
						logger.info("Send button is NOT displayed in the popup");
					}
					//Validate - whether 'Send' button is enabled
					if(messagepg.enabledbtnSendpopup() == true)
					{
						logger.info("Test Passed: Send button is enabled");
						softassertion.assertTrue(true);
						logger.info("Send button is enabled in the popup");
						
						//Click on send button to send the updated bid to buyer
						messagepg.clkbtnSendpopup();
					}
					else
					{
						logger.error("Test Failed: Send button is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledSendButtonPopup");
						logger.info("Send button is NOT enabled in the popup");
					}
					//Refresh page
					driver.navigate().refresh();
					//Delete 'New Bid' mail received (created during this testcase)
					MessagePage messagepg1 = new MessagePage(driver);
					logger.info("Delete the New Bid  from inbox");
					messagepg1.clkLatestEntry();
					messagepg1.clkbtnDelete();
				}
				else
				{
					logger.error("Test Failed: Submit your bid popup DOESNOT appears");
					softassertion.assertTrue(false);
					captureScreen(driver,"SubmityourBidPopup");					
					logger.info("'Submit your bid' pop-up DOESNOT appear!!");
				}
		}
		else
		{
			logger.error("Test Failed: Seller DIDNOT receive New Bid mail");
			softassertion.assertTrue(false);
			messagepg.clktxtfirstmail();
			logger.info("Seller DIDNOT receive New Bid mail!!");
		}
		softassertion.assertAll();
		//Refresh the page
		driver.navigate().refresh();
		//Logout from this user
		logger.info("Seller logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		}
		
		//Login as a buyer
		@Test(priority = 3804)
		public void CheckBuyerBidOffer() throws IOException, InterruptedException {
			//Login as a buyer 
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
			
			MessagePage messagepg = new MessagePage(driver);
			SoftAssert softassertion = new SoftAssert();
			//Check whether buyer received 'Bid Offer' mail and click on it
			if(messagepg.gettxtfirstmail().contains("Bid Offer"))
			{
				logger.info("Test Passed: Buyer received 'Bid offer' mail");
				softassertion.assertTrue(true);
				logger.info("Buyer received 'Bid offer' mail!!");
				
				//Click on bid offer mail
				messagepg.clktxtfirstmail();
				
				//Select on sale agreement and select to 'Make an Offer'
				messagepg.clkSaleAgreementBidOffer();
				messagepg.clkbtnMakeOffer();
				
				//Validate - Whether 'Submit your bid' Pops up
				if(messagepg.getSubmityourBidpopup().contains("SUBMIT YOUR BID"))
				{
					logger.info("Test Passed: Submit your bid popup appears");
					softassertion.assertTrue(true);
					logger.info("'Submit your bid' pop-up appears!!");
					
					//Validate - Whether popup has mandatory 'Price' field is displayed
					if(messagepg.displayPricefield() == true)
					{
						logger.info("Test Passed: Price field is displayed");
						softassertion.assertTrue(true);
						logger.info("Price field is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Price field is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplayPriceFieldBidOffer");
						logger.info("Price field is NOT displayed in the popup");
					}
					//Validate - Whether popup has mandatory 'Price' field is enabled
					if(messagepg.enabledPricefield() == true)
					{
						logger.info("Test Passed: Price field is enabled");
						softassertion.assertTrue(true);
						logger.info("Price field is enabled in the popup");
					}
					else
					{
						logger.error("Test Failed: Price field is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledPriceFieldBidOffer");
						logger.info("Price field is NOT enabled in the popup");
					}
					//Validate - whether popup has optional Message field is displayed
					if(messagepg.displaytxtMessage() == true)
					{
						logger.info("Test Passed: Message field is displayed");
						softassertion.assertTrue(true);
						logger.info("Message field is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Message field is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplayMessageFieldBidOffer");
						logger.info("Message field is NOT displayed in the popup");
					}
					//Validate - Whether popup has mandatory 'Message' field is enabled
					if(messagepg.enabledtxtMessage() == true)
					{
						logger.info("Test Passed: Message field is enabled");
						softassertion.assertTrue(true);
						logger.info("Message field is enabled in the popup");
					}
					else
					{
						logger.error("Test Failed: Message field is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledMessageFieldBidOffer");
						logger.info("Message field is NOT enabled in the popup");
					}
					//Validate - whether sale agreement checkbox is displayed
					if(messagepg.displaySaleAgreementpopup() == true)
					{
						logger.info("Test Passed: Sale agreement checkbox is displayed");
						softassertion.assertTrue(true);
						logger.info("Sale agreement checkbox is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Sale agreement checkbox is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplaySaleAgreementPopupBidOffer");
						logger.info("Sale agreement checkbox is NOT displayed in the popup");
					}
					//Validate - whether sale agreement checkbox is enabled
					if(messagepg.enabledSaleAgreementpopup() == true)
					{
						logger.info("Test Passed: Sale agreement checkbox is enabled");
						softassertion.assertTrue(true);
						logger.info("Sale agreement checkbox is enabled in the popup");
					}
					else
					{
						logger.error("Test Failed: Sale agreement checkbox is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledSaleAgreementPopupBidOffer");
						logger.info("Sale agreement checkbox is NOT enabled in the popup");
					} 
					//Validate - whether 'Send' button is displayed
					if(messagepg.displaybtnSendpopup() == true)
					{
						logger.info("Test Passed: Send button is displayed");
						softassertion.assertTrue(true);
						logger.info("Send button is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Send button is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplaySendButtonPopupBidOffer");
						logger.info("Send button is NOT displayed in the popup");
					}
					//Validate - whether 'Send' button is enabled
					if(messagepg.enabledbtnSendpopup() == true)
					{
						logger.info("Test Passed: Send button is enabled");
						softassertion.assertTrue(true);
						logger.info("Send button is enabled in the popup");
					}
					else
					{
						logger.error("Test Failed: Send button is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledSendButtonPopupBidOffer");
						logger.info("Send button is NOT enabled in the popup");
					}
					//Validate - whether 'Cancel' button is displayed
					if(messagepg.displaybtnCancelpopup() == true)
					{
						logger.info("Test Passed: Cancel button is displayed");
						softassertion.assertTrue(true);
						logger.info("Cancel button is displayed in the popup");
					}
					else
					{
						logger.error("Test Failed: Cancel button is NOT displayed");
						softassertion.assertTrue(false);
						captureScreen(driver,"DisplayCancelButtonPopupBidOffer");
						logger.info("Cancel button is NOT displayed in the popup");
					}
					//Validate - whether 'Cancel' button is enabled
					if(messagepg.enabledbtnCancelpopup() == true)
					{
						logger.info("Test Passed: Cancel button is enabled");
						softassertion.assertTrue(true);
						logger.info("Cancel button is enabled in the popup");
					}
					else
					{
						logger.error("Test Failed: Cancel button is NOT enabled");
						softassertion.assertTrue(false);
						captureScreen(driver,"EnabledCancelButtonPopupBidOffer");
						logger.info("Cancel button is NOT enabled in the popup");
					}
					
				}
			else
			{
					logger.error("Test Failed: Submit your bid popup DOESNOT appears");
					softassertion.assertTrue(false);
					captureScreen(driver,"SubmityourBidPopup");					
					logger.info("'Submit your bid' pop-up DOESNOT appear!!");
			}
				
				//refresh the page
				driver.navigate().refresh();
				//Go to Messages page
				HomePage hp = new HomePage(driver);
				hp.clkbtnMessages();
				//Click on the bid offer mail to decline bid (to delete the service created during testcase)
				MessagePage messagepg3 = new MessagePage(driver);
				messagepg3.clktxtfirstmail();
				messagepg3.clkbtnDecline();
				//Refresh the page
				driver.navigate().refresh();
				
				// Go to Messages Page
				HomePage homepg1 = new HomePage(driver);
				homepg1.clkbtnMessages();
				//Delete the bid offer mail and bid sent mail (created during this test case)
				MessagePage messagepg1 = new MessagePage(driver);
				logger.info("Delete the bid canceled mail  from inbox");
				messagepg1.clkLatestEntry();
				messagepg1.clkbtnDelete();
				driver.navigate().refresh();
				logger.info("Delete the bid offer mail  from inbox");
				MessagePage messagepg4 = new MessagePage(driver);
				messagepg4.clkLatestEntry();
				messagepg4.clkbtnDelete();
				driver.navigate().refresh();
				logger.info("Delete bid sent message");
				MessagePage messagepg2 = new MessagePage(driver);
				messagepg2.clkLatestEntry();
				messagepg2.clkbtnDelete();
	
				}
		else
		{
			logger.info("Test Failed: Buyer DIDNOT receive 'Bid offer' mail");
			softassertion.assertTrue(false);
			captureScreen(driver,"CheckBuyerBidOfferMail");
			logger.info("Buyer DIDNOT receive 'Bid offer' mail!!");
		}
		softassertion.assertAll();
		//Refresh the page
		driver.navigate().refresh();
		//Logout from this user 
		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		}
		
		//Login as a seller to check mail and delete the mails created during this testcase
		@Test(priority=3806)
		public void CheckSellerMail() throws InterruptedException {
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
			//Refresh the page
			driver.navigate().refresh();
			//Delete the bid offer mail and bid sent mail (created during this test case)
			MessagePage messagepg1 = new MessagePage(driver);
			logger.info("Delete the bid offer mail  from inbox");
			messagepg1.clkLatestEntry();
			messagepg1.clkbtnDelete();
			driver.navigate().refresh();
			logger.info("Delete the bid sent mail from inbox");
			MessagePage messagepg2 = new MessagePage(driver);
			messagepg2.clkLatestEntry();
			messagepg2.clkbtnDelete();
			driver.navigate().refresh();
			
			// Go to profile page and delete the service (created during this testcase)
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
			ProfilePage profilepg = new ProfilePage(driver);
			profilepg.clkbtnDeleteService();
			profilepg.clkbtnIwantToDelete();
			logger.info("Service has been deleted");
			logger.info("Service deleted successfully");
			//Refresh the page
			driver.navigate().refresh();
			//Logout from this user
			logger.info("Seller logout");
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
			
	logger.info("Completed TP_TC_038");
		}
		
		

}
