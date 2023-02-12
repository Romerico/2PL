package com.testCases;

import java.io.IOException;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateService;
import com.pageObjects.CreditCard;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MessagePage;
import com.pageObjects.OtherUserPage;
import com.pageObjects.ProfilePage;
import com.pageObjects.SearchService;
import com.pageObjects.ServiceDetailPage;

/* Testcase Description: "This test case is verifying the functionality of the 'Credit card' workflow.
Steps to be followed:
a) The 'Insufficient eeds' message pops-up while bidding/buying with less or zero eeds
b) Enter correct and complete Credit card details and push 'Submit payment' button to proceed" */

/*Acceptance Criteria: "The test case to be considered successful, following should happen:
a) The transaction confirmation appears on screen
b) The seller recieves 'Confirm order' message" */

public class TP_TC_104 extends BaseClass {
	@Test(priority = 10401)
	public void SellerLogin_CreateService() throws InterruptedException, IOException {

		logger.info("Started TP_TC_104");
		LoginPage LoginPage1 = new LoginPage(driver);
		LoginPage1.clickloginlandingbtn();
		LoginPage1.setUsername(username1);
		logger.info("Providing user name");
		logger.info("Providing password");
		LoginPage1.setPassword(password);
		LoginPage1.clickloginbtn1();
		logger.info("Clicking on login button");
		// Create a service
		CreateService createsvc = new CreateService(driver);
		createsvc.clkbtnCreateNew();
		createsvc.clkbtnService();
		logger.info("Fill in Service form with test data");
		createsvc.txtTitleField("Jazy Fashions"); // If changing testdata here, change it in all places in test case
		createsvc.txtdescriptionField("One stop Fashion boutique");
		createsvc.clktxtCategoryField();
		createsvc.SelectdrpdownCategory("Beauty & Fashion");
	//	createsvc.clktxtsubCategoryField();
		createsvc.selectdrpdownSubCategory("Clothing");
		createsvc.txtPriceField("200");
		createsvc.SilderBarMaxLimit();
		createsvc.refundValidField("20");
		createsvc.btnSubmitServicePage();
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
		// Logout from the seller
		HomePage homepg = new HomePage(driver);
		logger.info("Seller logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

	// Login as a buyer; search service and select 'I want this'
	// Buy bid with insufficient balance
	// Purchase eeds pop up opens
	@Test(priority = 10402)
	public void Buyerlogin_BuyBidwithInsufficientBalance() throws IOException, InterruptedException {
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
		if (totalbal > 0) {
			logger.info("Account balance is greater than zero");
			// Click on search box
			logger.info("Make the account balance zero");
			logger.info("Click on Search box");
			homepg.clkbtnSearch();
			// Search for the service based on text value
			logger.info("Search for the specific service");
			SearchService searchpg = new SearchService(driver);
			searchpg.txtSearchValue("Jazy Fashions");
			searchpg.clkSerachButton();
			// Click on the link containing the text value
			logger.info("click on the created service");
			searchpg.clkbtncreatedsvc();
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

			// Click on Logo to go back to buyer account
			logger.info("Click on twoPlugs logo to go back to buyer account");
			LoginPage loginpg = new LoginPage(driver);
			loginpg.clkimgLogo();

		}

		// Refresh the page
		driver.navigate().refresh();
		HomePage homepg1 = new HomePage(driver);
		logger.info("Click on Search box");
		homepg1.clkbtnSearch();
		// Search for the service based on text value
		logger.info("Search for the specific service");
		SearchService searchpg1 = new SearchService(driver);
		searchpg1.txtSearchValue("Jazy Fashions");
		searchpg1.clkSerachButton();

		// Click on the link containing the text value
		logger.info("click on the created service");
		searchpg1.clkbtncreatedsvc();
		// Select 'I want this' option
		logger.info("Click on 'I want this' button");
		searchpg1.btnIwantThis();
		// Select the checkbox and 'buy' button
		searchpg1.clkDisclaimerOption();
		searchpg1.clkBuy();

		CreditCard creditcard = new CreditCard(driver);
		logger.info(" Clicked on Buy button without Eeds");
		SoftAssert softassert = new SoftAssert();
		// Check whether buying a service with insufficient eeds displays error message
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
			if (creditcard.gettxtpurchasesuccessmsg().contains("PURCHASE SUCCEEDED")) {
				softassert.assertTrue(true);
				logger.info("Test Passed: Transaction Confirmation - Purchase of eeds is successful!");
				creditcard.clickpurchaseok();
			} else {
				softassert.assertTrue(false);
				logger.error("Test Failed: Transaction Confirmation - Purchase of eeds NOT successful!");
				captureScreen(driver, "TransactionConfirmation");
			}
		} else {
			softassert.assertTrue(false);
			logger.error("Test Failed: INSUFFICIENT EEDS page NOT displayed");
			captureScreen(driver, "InsufficientEeds");
		}
		// Refresh the page
		driver.navigate().refresh();

		// Click again on I want this button
		logger.info("Click again on 'I want this' button");
		SearchService searchpg2 = new SearchService(driver);
		searchpg2.btnIwantThis();
		searchpg2.clkDisclaimerOption();
		searchpg2.clkBuy();
		searchpg2.clkbtnOk();

		// Refresh the page
		driver.navigate().refresh();

		// Logout from this user
		logger.info("Buyer logout");
		Actions act2 = new Actions(driver);
		act2.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

	// Login as a Seller to decline the offer
	@Test(priority = 10403)
	public void SellerLogin_Declinebid() throws IOException, InterruptedException {
		logger.info("Login as a seller");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as Seller");

		// Go to Messages
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();

		// Check whether seller received Confirm order mail
		MessagePage messagepg = new MessagePage(driver);
		SoftAssert softassert = new SoftAssert();
		if (messagepg.gettxtfirstmail().contains("Confirm Order")) {
			logger.info("Test Passed: The seller received Confirm order mail in Inbox");
			softassert.assertTrue(true);
			logger.info("Seller received 'Confirm order' mail in Inbox!!");

			// Click on the message - 'Confirm Order'
			MessagePage messagepg1 = new MessagePage(driver);
			messagepg1.clktxtfirstmail();
			Thread.sleep(1000);
			messagepg1.clkbtnDenyPurchase();

			// Delete the confirm order mail after denying purchase
			logger.info("Delete the confirm order mail after Denying the bid!");
			messagepg.clkLatestEntry();
			messagepg.clkbtnDelete();

		} else {
			logger.error("Test Failed: The seller DIDNOT receive Confirm order mail in Inbox");
			softassert.assertTrue(false);
			logger.info("Seller DIDNOT receive 'Confirm order' mail in Inbox!!");
			captureScreen(driver, "ConfirmOrderSellerLogin");
		}

		// Go to profile page and delete the service (created during this testcase)
		Actions act = new Actions(driver);
		HomePage homepg1 = new HomePage(driver);
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actProfile()).click().build().perform();

		// Logout from this user
		logger.info("Seller logout");
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();

	}

	// Login as a buyer to delete the bid sent created
	@Test(priority = 10404)
	public void BuyerLogin_DeleteMails() throws InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username4);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as a Buyer ");

		// Click on Messages
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();

		// Delete the Bid sent mail (created during the testcase)
		MessagePage messagepg = new MessagePage(driver);
		messagepg.clkLatestEntry();
		messagepg.clkbtnDelete();
		// Refresh the page
		driver.navigate().refresh();

		// logout from the user
		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

	@Test(priority = 10405)
	public void SellerLogin_DeleteService() throws InterruptedException {
		logger.info("Login as a seller");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as Seller");

		// Go to Profile
		ProfilePage profilepg = new ProfilePage(driver);
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		profilepg.clkbtnDeleteService();
		profilepg.clkbtnIwantToDelete();
		logger.info("Service has been deleted");
		
		// Refresh the page
		driver.navigate().refresh();

		// logout from the user
		logger.info("Seller logout");

		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();

		logger.info("Completed TP_TC_104");
	}

}
