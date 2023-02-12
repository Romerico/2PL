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

/* TEST CASE DESCRIPTION: "This test case is verifying the functionality of all the features of 'New bid' message in a Bidding workflow.
Steps to be followed:
a) Search for a service to buy.
b) Hit on  the 'Bid' button and enter the desired bidding amount.
c) If 'New bid' message comes back in the inbox, open the message to view it.
*/

/* Acceptance Criteria: "The test case to be considered successful, following should happen:
a) The 'New bid' message have three buttons ('Decline bid', 'Make an offer' and 'Accept') - which should be displayed and enabled.
b) There is a check-box for sale agreement" */ 


public class TP_TC_035 extends BaseClass{
	
	//Login as a seller (username/mars) and create a service 
	@Test(priority=3501)
	public void sellerlogin_CreateService()  throws InterruptedException
	{
		logger.info("Started TP_TC_035");
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
		
		//Logout from the seller
		HomePage homepg = new HomePage(driver);
		logger.info("Seller logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}
	
	//Login as a buyer (username=blue); search for a bid and select 'Lets Negotiate'
	@Test(priority=3502)
	public void BuyerloginandSearchBid() throws IOException, InterruptedException {
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
		
		//Logout from this user
		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}
	
	//login as service provider (seller)(username: mars)
	@Test(priority=3503)
	public void loginAsSeller2() throws InterruptedException {
		logger.info("Login as a seller");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as Seller");
	}
	
	//Check New Bid Mail in the seller Inbox and validate Sale agreement checkbox, Accept button, Decline bid and Make Offer button is displayed and enabled
	@Test(priority=3504)
	public void CheckSellerNewBid() throws IOException
	{
		logger.info("Click on message tab");
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();
		MessagePage messagepg = new MessagePage(driver);
		SoftAssert softassertion = new SoftAssert();
		//Check whether seller received new bid offer by the buyer and click on the mail
		logger.info("Click on the new bid by the buyer");
		if(messagepg.gettxtfirstmail().contains("New Bid"))
		{
			logger.info("Test Passed: Seller received New Bid mail");
			softassertion.assertTrue(true);
			logger.info("Seller received new bid mail!!");
			messagepg.clktxtfirstmail();    //Click on the first mail
			
		//1. Validate - Sale agreement check box is displayed and enabled
			logger.info("Validating whether Sale agreement check box is displayed & enabled");
			
			//Check whether sale agreement check box is displayed
			if(messagepg.displaysSaleAgreementbox() == true)
			{
				logger.info("Test Passed: Sale agreement check box is displayed");
				softassertion.assertTrue(true);
				logger.info("Sale Agreement checkbox is displayed!!");
			}
			else
			{
				logger.error("Test Failed: Sale agreement check box is NOT displayed");
				captureScreen(driver,"CheckSaleAgreementboxDisplayed");
				softassertion.assertTrue(false);
				logger.info("Sale Agreement checkbox is NOT displayed!!");
			}
			
			//Check whether sale agreement check box is enabled
			if(messagepg.enabledSaleAgreementbox() == true)
			{
				logger.info("Test Passed: Sale agreement check box is enabled");
				softassertion.assertTrue(true);
				logger.info("Sale Agreement checkbox is enabled!!");
			}
			else
			{
				logger.error("Test Failed: Sale agreement check box is NOT enabled");
				captureScreen(driver,"CheckSaleAgreementboxEnabled");
				softassertion.assertTrue(false);
				logger.info("Sale Agreement checkbox is NOT enabled!!");
			}
		
		//2. Validate - Accept button is displayed and enabled
			logger.info("Validating whether Accept button is displayed & enabled");
			
			//Check whether Accept button is displayed
			if(messagepg.displaysbtnAccept() == true)
			{
				logger.info("Test Passed: Accept button is displayed");
				softassertion.assertTrue(true);
				logger.info("Accept button is displayed!!");
			}
			else
			{
				logger.error("Test Failed: Accept button is NOT displayed");
				captureScreen(driver,"CheckAcceptButtonDisplayed");
				softassertion.assertTrue(false);
				logger.info("Accept button is NOT displayed!!");
			}
			//Check whether Accept button is enabled
			if(messagepg.enabledbtnAccept() == true)
			{
				logger.info("Test Passed: Accept button is enabled");
				softassertion.assertTrue(true);
				logger.info("Accept button is enabled!!");
			}
			else
			{
				logger.error("Test Failed: Accept button is NOT enabled");
				captureScreen(driver,"CheckAcceptButtonEnabled");
				softassertion.assertTrue(false);
				logger.info("Accept button is NOT enabled!!");
			}
		
		//3. Validate - Make Offer button is displayed and enabled
			logger.info("Validating whether 'Make offer button' is displayed & enabled");
			//Check whether Make Offer button is displayed
			if(messagepg.displaysbtnMakeOffer() == true)
			{
				logger.info("Test Passed: Make Offer button is displayed");
				softassertion.assertTrue(true);
				logger.info("Make Offer button is displayed!!");
			}
			else
			{
				logger.error("Test Failed: Make Offer button is NOT displayed");
				captureScreen(driver,"CheckMakeOfferButtonDisplayed");
				softassertion.assertTrue(false);
				logger.info("Make Offer button is NOT displayed!!");
			}
			//Check whether Make offer button is enabled
			if(messagepg.enabledbtnMakeOffer() == true)
			{
				logger.info("Test Passed: Make Offer button is enabled");
				softassertion.assertTrue(true);
				logger.info("Make Offer button is enabled!!");
			}
			else
			{
				logger.error("Test Failed: Make Offer button is NOT enabled");
				captureScreen(driver,"CheckMakeOfferButtonEnabled");
				softassertion.assertTrue(false);
				logger.info("Make Offer button is NOT enabled!!");
			}
			
			//4. Validate - Decline button is displayed and enabled & signout 
		  		logger.info("Validating whether Decline button is displayed & enabled");
		  		//Check whether Decline button is displayed
				if(messagepg.displaysbtnDecline() == true)
				{
					logger.info("Test Passed: Decline button is displayed");
					softassertion.assertTrue(true);
					logger.info("Decline button is displayed!!");
				}
				else
				{
					logger.error("Test Failed: Decline button is NOT displayed");
					captureScreen(driver,"CheckDeclineButtonDisplayed");
					softassertion.assertTrue(false);
					logger.info("Decline button is NOT displayed!!");
				}
				//Check whether Decline button is enabled
				if(messagepg.enabledbtnDecline() == true)
				{
					logger.info("Test Passed: Decline button is enabled");
					softassertion.assertTrue(true);
					logger.info("Decline button is enabled!!");
					
					//Click on decline button
					logger.info("Selected Decline button");
					messagepg.clkbtnDecline();
					
						
				}
				else
				{
					logger.error("Test Failed: Decline button is NOT enabled");
					captureScreen(driver,"CheckDeclineButtonEnabled");
					softassertion.assertTrue(false);
					logger.info("Decline button is NOT enabled!!");
				}
				softassertion.assertAll();	
				
				//Refresh the page
				driver.navigate().refresh();
				
				//Go back to Messages page
				HomePage homepg1 = new HomePage(driver);
				homepg1.clkbtnMessages();		
				
				//Select the new bid and bid canceled mail and delete them (created during this testcase)
				logger.info("Delete Bid canceled mail");
				MessagePage messagepg1 = new MessagePage(driver);
				messagepg1.clkLatestEntry();
				messagepg1.clkbtnDelete();
				driver.navigate().refresh();
				MessagePage messagepg2 = new MessagePage(driver);
				logger.info("Delete new bid mail");
				messagepg2.clkLatestEntry();
				messagepg2.clkbtnDelete();
				
		}
		else
		{
			logger.error("Test Failed: Seller DIDNOT receive New Bid mail");
			softassertion.assertTrue(false);
			captureScreen(driver,"CheckSellerNewBidMail");
			logger.info("Seller DIDNOT receive new bid mail!!");
		}
		softassertion.assertAll();

		//Refresh the page
		driver.navigate().refresh();
		
		//Go to profile page and delete the service (created during this testcase)
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		
		ProfilePage profilepg = new ProfilePage(driver);
		profilepg.clkbtnDeleteService();
		profilepg.clkbtnIwantToDelete();
		
		logger.info("Service has been deleted");
		logger.info("Service deleted successfully");

		//refresh the page
		driver.navigate().refresh();
		
		//Logout from this user
		logger.info("Seller logout");
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
}
	
	//Login as a buyer (username: blue) to delete the bid sent created
		@Test(priority=3505)
		public void LoginAsBuyerToDeleteBid() throws InterruptedException {
		
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as a Buyer ");
		
		//Click on Messages
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();
		
		//Delete the Bid sent mail (created during the testcase)
		MessagePage messagepg = new MessagePage(driver);
		messagepg.clkLatestEntry();
		messagepg.clkbtnDelete();
		//Refresh the page
		driver.navigate().refresh();
		
		//logout from the user
		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();

logger.info("Completed TP_TC_035");
		}
		
	}
