//Testcase Description: Checks the functionality of credit card workflow when buyer does not have money while bidding
//Acceptance criteria: 1.The credit card will pop up with stripe payemnt and he can enter the details of credit card 
//2. The transaction should be updated with the payment EEDS purchase"

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
import com.pageObjects.OtherUserPage;
import com.pageObjects.ProfilePage;
import com.pageObjects.SearchService;
import com.pageObjects.ServiceDetailPage;
import com.pageObjects.TransHistoryPage;

public class TP_TC_099 extends BaseClass {

	@Test(priority = 9901)
	public void SellerLogin_CreateService() throws InterruptedException, IOException {

		logger.info("Started TP_TC_099");
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
		// Validate whether service is created successfully
		SoftAssert softassert = new SoftAssert();
		ServiceDetailPage servicedetpg = new ServiceDetailPage(driver);
		if (servicedetpg.gettxtaddSvccaption().contains("Service has been added")) {
			softassert.assertTrue(true);
			logger.info("Created Service Successfully");
		} else {
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

	// Login as a buyer; search for a bid and select to bid i.e. 'Lets Negotiate'
	@Test(priority = 9902)
	public void Buyerlogin_SearchBid_NegotiateWithoutEeds() throws IOException, InterruptedException {
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
		// Click on negotiate button
		logger.info("click on negotiate and enter values");
		searchpg1.btnLetsNegotiate();
		searchpg1.txtPriceBox();
		searchpg1.textPriceBoxCharValue("150");
		searchpg1.clkSaleAgreement();
		searchpg1.btnSend();

		CreditCard creditcard = new CreditCard(driver);
		logger.info("VALIDATE - Negotiating without Eeds displays Insufficient EEDS popup!!");
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
		// Refresh the page
		driver.navigate().refresh();

		// Logout from this user
		logger.info("Buyer logout");
		Actions act2 = new Actions(driver);
		act2.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

	@Test(priority = 9903)
	public void SellerLogin_DeleteService() throws IOException, InterruptedException {
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

		// Refresh the page
		driver.navigate().refresh();

		// Logout from this user
		logger.info("Seller logout");
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();

		logger.info("Completed TP_TC_099");
	}

}