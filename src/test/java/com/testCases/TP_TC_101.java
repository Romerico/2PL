package com.testCases;

import java.io.IOException;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.CreateService;
import com.pageObjects.CreditCard;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MessagePage;
import com.pageObjects.NeedDetailPage;
import com.pageObjects.OtherUserPage;
import com.pageObjects.ProfilePage;
import com.pageObjects.SearchNeed;
import com.pageObjects.SearchService;
import com.pageObjects.ServiceDetailPage;
import com.pageObjects.TransHistoryPage;

/* Testcase Description: Checks the functionality of Credit card workflow 
 * when buyer tries to make an offer in bid offer mail received by Seller */
 
/* Acceptance Criteria: "The following will happen the test case to be successful 
1: The credit card will pop up with stripe payment and he can enter the details of credit card 
2: The transaction should be updated with the payment EEDS purchase" */

public class TP_TC_101 extends BaseClass {

	// Login as a buyer and create a need
	@Test(priority = 10101)
	public void BuyerLogin_CreateNeed() throws InterruptedException, IOException {

		logger.info("Started TP_TC_101");
		LoginPage LoginPage1 = new LoginPage(driver);
		LoginPage1.clickloginlandingbtn();
		logger.info("Login as a buyer");
		LoginPage1.setUsername(username4);
		logger.info("Providing user name");
		logger.info("Providing password");
		LoginPage1.setPassword(password);
		LoginPage1.clickloginbtn1();
		logger.info("Clicking on login button");

		// Create a need
		logger.info("Creating a new Need");
		CreateNeed createneed = new CreateNeed(driver);
		createneed.clkbtnCreateNew();
		createneed.clkbtnNeed();
		logger.info("Fill in Need form with test data");
		createneed.txtTitleField("Test user need");
		createneed.txtdescriptionField("Test user need");
		createneed.clktxtCategoryField();
		createneed.SelectdrpdownCategory("Beauty & Fashion");
	//	createneed.clktxtsubCategoryField();
		createneed.selectdrpdownSubCategory("Clothing");
		createneed.btnSubmitNeedPage();
		// Validate whether need is created successfully
		NeedDetailPage needdetpg = new NeedDetailPage(driver);
		SoftAssert softassert = new SoftAssert();
		if (needdetpg.gettxtaddNeedcaption().contains("Need has been added")) {
			softassert.assertTrue(true);
			logger.info("Need created successfully!!");
		} else {
			softassert.assertTrue(false);
			logger.error("Need NOT created!!");
			captureScreen(driver, "NeedNotCreated");
		}
		// Refresh the page
		driver.navigate().refresh();

		// Buyer logging out.
		logger.info("Buyer logging out");
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

	// Login as a seller and create a service to match the buyer's need
	// Search for the buyer need created in previous test method
	// Select the 'I can do this' option
	@Test(priority = 10102)
	public void SellerLogin_CreateService() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as a Seller");

		// Create a service
		CreateService createsvc = new CreateService(driver);
		createsvc.clkbtnCreateNew();
		createsvc.clkbtnService();
		logger.info("Fill in Service form with test data");
		createsvc.txtTitleField("Test user service"); 
		createsvc.txtdescriptionField("Test Service Provider");
		createsvc.clktxtCategoryField();
		createsvc.SelectdrpdownCategory("Beauty & Fashion");
	//	createsvc.clktxtsubCategoryField();
		createsvc.selectdrpdownSubCategory("Clothing");
		createsvc.btnSubmitServicePage();
		// Validate whether service is created successfully
		SoftAssert softassert = new SoftAssert();
		ServiceDetailPage servicedetpg = new ServiceDetailPage(driver);
		if (servicedetpg.gettxtaddSvccaption().contains("Service has been added")) {
			softassert.assertTrue(true);
			logger.info("Created Service Successfully");
		} else {
			softassert.assertTrue(false);
			logger.error("Service NOT created");
			captureScreen(driver, "ServiceNOTcreated");
		}
		softassert.assertAll();
		
		//Click on search box
				logger.info("Click on Search box");
				HomePage homepg = new HomePage(driver);
				homepg.clkbtnSearch();
		

		// Search the need based on the text value

		logger.info("Search for the specific need");
		SearchService searchpg1 = new SearchService(driver);
		searchpg1.txtSearchValue("Test user need");
		searchpg1.clkSerachButton();

		// Click on the link containing the text value
		logger.info("click on the created need");
		SearchNeed searchndpg = new SearchNeed(driver);
		searchndpg.clkbtncreatedneed();
		searchndpg.clkbtnIcandothis();
		searchndpg.clrtxtprice();
		searchndpg.settxtprice("25");
		searchndpg.clkdrpdownsvc();
		searchndpg.SelectdrpdownSvcname("Test user service");
		searchndpg.clkboxsaleagreement();
		searchndpg.clkbtnSend();
		searchndpg.clkbtnOk();

		// Refresh the page
		driver.navigate().refresh();

		// Logout from the seller
		HomePage homepg1 = new HomePage(driver);
		logger.info("Seller logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
	}

	// Login as a buyer
	// Check the account balance in the profile page; if account balance > 0 then
	// make it zero
	// Go to messages and check for the new bid message received
	// In the new bid message, accept the bid (with zero eeds)
	// Validate whether it opens the Insufficient bid pop up
	@Test(priority = 10103)
	public void BuyerLogin_ConfirmOrderMail_BidwithZeroEeds() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username4);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as a Buyer ");
		// Go to Profile and get the account balance of the buyer
		logger.info("Check the account balance in the profile");
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();

		ProfilePage profilepg = new ProfilePage(driver);

		int totalbal = profilepg.getTotalBalance();
		logger.info("Total Balance: " + totalbal);
		String amountinaccount = Integer.toString(totalbal);
		if (totalbal > 0) 
		{
			logger.info("Account balance is greater than zero");
			// Click on search box
			logger.info("Make the account balance zero");
			logger.info("Click on Search box");
			homepg.clkbtnSearch();
			// Search for the service based on text value
			logger.info("Search for the specific service");
			SearchService searchpg = new SearchService(driver);
			searchpg.txtSearchValue("Test user service");
			searchpg.clkSerachButton();
			// Click on the link containing the text value
			logger.info("click on the created service");
			searchpg.clkbtncreatedsvc1();
			// Click on username to transfer eeds available
			logger.info("Click on the user by whom the service is provided");
			ServiceDetailPage servicedetpg = new ServiceDetailPage(driver);
			servicedetpg.clktxtusername();

			// Click to send eeds to other user
			logger.info("Click on Send Eeds link");
			OtherUserPage otheruserpg = new OtherUserPage(driver);
			otheruserpg.clkbtnSendeeds();
			logger.info("Fill in the total balance of the user in the Amount (eeds) field");
			otheruserpg.settxtamount(amountinaccount);
			logger.info("Click on Transfer!");
			otheruserpg.clkbtntransfer();
			otheruserpg.clkbtnconfirmtransfer();
			logger.info("Amount is transfered and the account balance is Zero");
			
			//Go to messages and delete the transfer mail
			HomePage homepg1 = new HomePage(driver);
			homepg1.clkbtnMessages();
			MessagePage messagepg = new MessagePage(driver);
			messagepg.clkLatestEntry();
			messagepg.clkbtnDelete();
			driver.navigate().refresh();
		}
			// Click on Logo to go back to buyer account
			logger.info("Click on twoPlugs logo to go back to buyer account");
			LoginPage loginpg = new LoginPage(driver);
			loginpg.clkimgLogo();

			// Go to messages
			HomePage homepg1 = new HomePage(driver);
			homepg1.clkbtnMessages();

			SoftAssert softassertion = new SoftAssert();
			MessagePage messagepg = new MessagePage(driver);
			logger.info("Click on the new bid by the buyer");
			if (messagepg.gettxtfirstmail().contains("New Bid")) {
				logger.info("Buyer received New Bid mail");
				softassertion.assertTrue(true);
				logger.info("Buyer received New Bid mail!!");
				// Click on the new bid mail
				messagepg.clktxtfirstmail();

				// Click to accept the bid
				messagepg.clkSaleAgreementbox();
				messagepg.clkbtnAccept();

				// VAlidate whether the Insufficient Eeds Popup appears while trying to accept
				// the bid with no eeds
				CreditCard creditcard = new CreditCard(driver);
				logger.info("VALIDATE - Accepting the bid through New Bid Mail displays Insufficient EEDS popup!!");
				SoftAssert softassert = new SoftAssert();
				// Check whether accepting the bid with insufficient eeds displays error message
				if (creditcard.gettxtINSUFFICIENTEEDS().contains("INSUFFICIENT EEDS")) {
					softassert.assertTrue(true);
					logger.info("Test Passed: INSUFFICIENT EEDS page is displayed");

					logger.info("Provide credit card information");
					creditcard.setcreditcardname(creditcardname);
					// Switch to frame
					driver.switchTo().frame("__privateStripeFrame5");
					creditcard.settxtcreditcardnumber(creditcardnumber);
					creditcard.settxtcreditcardmmyy(creditcardmmyy);
					creditcard.settxtcreditcardcvc(creditcardcvv);
					creditcard.settxtcreditcardzip(creditcardzip);
					// Switch back to default form
					driver.switchTo().defaultContent();
					creditcard.clickpaybtn();

					// Validate whether there is a popup message 'Purchase succeeded'
					logger.info("VALIDATE - Purchase Succeeded Pop up Appears!!");
					if (creditcard.gettxtpurchasesuccessmsg().contains("PURCHASE SUCCEEDED")) {
						softassert.assertTrue(true);
						logger.info("Test Passed: Transaction Confirmation - Purchase of eeds is successful!");
						creditcard.clickpurchaseok();

						// Refresh the page
						driver.navigate().refresh();

						// Go to Transaction history page
						logger.info("Go to transaction history page");
						HomePage homepg2 = new HomePage(driver);
						homepg2.clkbtnTransHist();

						// Validate whether the transaction history page shows 'Eeds Purchase' entry
						TransHistoryPage transhist = new TransHistoryPage(driver);
						logger.info("VALIDATE - Transaction history shows Eeds Purchase Entry!!");
						if (transhist.gettxtfirstentry().contains("Eeds Purchase")) {
							softassert.assertTrue(true);
							logger.info("Test Passed: The transaction history is updated with Eeds Purchase!!");
						} else {
							softassert.assertTrue(false);
							logger.error("Test Failed: The transaction history is updated with Eeds Purchase!!");
							captureScreen(driver, "TransactionHistoryNotupdated");
						}

					} else {
						softassert.assertTrue(false);
						logger.error("Test Failed: Transaction Confirmation - Purchase of eeds NOT successful!");
						captureScreen(driver, "EedsPurchaseNotSuccessful");
					}
				} else {
					softassert.assertTrue(false);
					logger.error("Test Failed: INSUFFICIENT EEDS page NOT displayed");
					captureScreen(driver, "InsufficientEedsPageNotDisplayed");
				}

			} else {
				logger.error("Buyer DIDNOT receive New Bid mail");
				softassertion.assertTrue(false);
				captureScreen(driver, "BuyerDidnotgetNewbidMail");
				logger.info("Buyer DIDNOT receive New Bid mail!!");
			}
			softassertion.assertAll();

			// Refresh the page
			driver.navigate().refresh();

			// Logout from buyer
			logger.info("Buyer logout");
			Actions act2 = new Actions(driver);
			act2.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		}
	
	// Login as a Seller
	// Delete the service and mail created during this testcase
	@Test(priority = 10104)
	public void SellerLogin_DeleteServiceandMail() throws InterruptedException {
		logger.info("Login as a seller");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as Seller");

		// Go to profile page and delete the service (created during this testcase)
		Actions act = new Actions(driver);
		HomePage homepg1 = new HomePage(driver);
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actProfile()).click().build().perform();

		ProfilePage profilepg = new ProfilePage(driver);
		profilepg.clkbtnDeleteService();
		profilepg.clkbtnIwantToDelete();

		// Validate whether service has been deleted
		SoftAssert softassert = new SoftAssert();
		ServiceDetailPage searchdetpg = new ServiceDetailPage(driver);
		if (searchdetpg.gettxtdelSvccaption().contains("Service has been deleted")) {
			softassert.assertTrue(true);
			logger.info("Service has been deleted");
		} else {
			softassert.assertTrue(false);
			logger.error("Service has not been deleted");
		}
		softassert.assertAll();
		
		//Go to Messages
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();
		
		// Select the bid sent message to delete it
				logger.info("Delete the Bid Sent message from inbox");
				MessagePage messagepg1 = new MessagePage(driver);
				messagepg1.clkLatestEntry();
				messagepg1.clkbtnDelete();

		// Refresh the page
		driver.navigate().refresh();

		// Logout from this user
		logger.info("Seller logout");
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
	}
	
	// Login as a Buyer
	// Delete the need and mails created during this testcase
	@Test(priority = 10105)
	public void BuyerLogin_DeleteNeedandMail() throws InterruptedException {
			
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username4);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as Buyer");
						
		// Go to Message Page
		HomePage homepg = new HomePage(driver);
		logger.info("Click on Messages tab");
		homepg.clkbtnMessages();
		
		//Decline the bid
		MessagePage messagepg = new MessagePage(driver);
		messagepg.clktxtfirstmail();
		messagepg.clkbtnDecline();
		
		// Go to profile page and delete the need (created during this testcase)
		Actions act = new Actions(driver);
		HomePage homepg1 = new HomePage(driver);
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actProfile()).click().build().perform();

		ProfilePage profilepg = new ProfilePage(driver);
		profilepg.clkbtnDeleteNeed();
		profilepg.clkbtnIwantToDeleteNeed();

		// Validate whether service has been deleted
		SoftAssert softassert = new SoftAssert();
		NeedDetailPage needdetpg = new NeedDetailPage(driver);
		if (needdetpg.gettxtdelNeedcaption().contains("Need has been deleted")) {
			softassert.assertTrue(true);
			logger.info("Need has been deleted");
		} else {
			softassert.assertTrue(false);
			logger.error("Need has not been deleted");
		}
		softassert.assertAll();
		
		//Go to Messages to Delete the messages
		HomePage homepg2 = new HomePage(driver);
		logger.info("Click on Messages tab");
		homepg2.clkbtnMessages();
		
		// Select the new bid and bid canceled message to delete it
		logger.info("Delete the Bid Canceled message from inbox");
		MessagePage messagepg1 = new MessagePage(driver);
		messagepg1.clkLatestEntry();
		messagepg1.clkbtnDelete();
		driver.navigate().refresh();
		logger.info("Delete the new bid mail from inbox");
		MessagePage messagepg2 = new MessagePage(driver);
		messagepg2.clkLatestEntry();
		messagepg2.clkbtnDelete();
		
		//Refresh the page
		driver.navigate().refresh();
		
		//Logout from the buyer
		
		logger.info("Buyer logout");
		Actions act2 = new Actions(driver);
		act2.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
		
		
		logger.info("Completed TP_TC_101");
		
		
	}
	
	
	
}
