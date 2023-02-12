package com.testCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateService;
import com.pageObjects.FileComplaintPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MessagePage;
import com.pageObjects.ProfilePage;
import com.pageObjects.SearchService;
import com.pageObjects.TransHistoryPage;

//Testcase Description: Testing the functionality of "Complaint icon"
/* Acceptance Criteria: "The Application should display tooltip box by moving mouse on Complaint icon and  by clicking on that icon,
then the Application should display File a Complaint page and user should file Complaint succefully by pushing "SUBMIT COMPLAINT" button" */

public class TP_TC_053 extends BaseClass {
	@Test(priority = 5301)
	public void SellerLogin_CreateService() throws InterruptedException, IOException {
		logger.info("Started TP_TC_053");
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
		createsvc.txtTitleField("Jazy Fashions"); // If changing testdata here, change it in all places in test case
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

		// Logout from the seller
		HomePage homepg = new HomePage(driver);
		logger.info("Seller logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}

	// Login with other user to buy the service created
	@Test(priority = 5302)
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
		HomePage homepage = new HomePage(driver);
		homepage.clkbtnSearch();
		// Search for the service based on text value
		logger.info("Search for the specific service");
		SearchService searchpg = new SearchService(driver);
		searchpg.txtSearchValue("Jazy Fashions");
		searchpg.clkSerachButton();

		// Click on the link containing the text value
		logger.info("click on the service link");
		driver.findElement(By.xpath("//a[contains(text(),'Jazy Fashions')]")).click();

		searchpg.btnIwantThis();
		searchpg.clkDisclaimerOption();
		logger.info("Buying the service");
		searchpg.clkBuy();

		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepage.actWelcome()).moveToElement(homepage.actSignout()).click().build().perform();
	}

		@Test(priority = 5303)
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

	@Test(priority = 5304)
	public void BuyerLogin_FileComplaint() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as buyer");

		//Create object at TransHistoryPage class
		TransHistoryPage trans = new TransHistoryPage(driver);
		//Click and hold flag tool tip 	 
		HomePage homepage = new HomePage(driver);
		homepage.clkbtnTransHist();
		trans.clkandholdflagtooltip();
		SoftAssert assertion = new SoftAssert();
		logger.info("Checking if complaint message is displayed");
		if (trans.isComplaintMessageDisplayed() == true) {
			Assert.assertTrue(true);
			logger.info("Complaint messase is displayed");
		} else {
			assertion.assertTrue(false);
			captureScreen(driver, "ComplaintIcon");
			logger.error("Complaint message is not displayed");
		}
		//Click flag tool tip
		logger.info("Clicking flag tool tip");
		trans.clkflagtooltip();

		//Create object at FileComplaintPage class	  		
		FileComplaintPage filecomppage = new FileComplaintPage(driver);

		//Check if file complaint page is opened
		logger.info("Checking if file complaint page is displayed");
		if (filecomppage.isPageOpened() == true) {
			Assert.assertTrue(true);
			logger.info("File complaint is displayed");
		} else {
			captureScreen(driver, "ComplaintIcon");
			logger.error("File complaint is not displayed");
			assertion.assertTrue(false);
		}

		//Fill out the file complaint and submit	  	
		logger.info("Filling out the complaint page");
		filecomppage.setSubject("test");
		filecomppage.setContent("test");
		filecomppage.clkSubmitComplaint();

		//Check if Complain Success Message is displayed
		logger.info("Checking if complaint sucess message is displayed");
		if (filecomppage.isComplainSucessMsgShown() == true) {
			assertion.assertTrue(true);
			logger.info("Complaint sucess message is displayed");
		} else {
			captureScreen(driver, "ComplaintIcon");
			logger.error("complaint sucess message not displayed");
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
		@Test (priority = 5305)
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
		@Test (priority = 5306)
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
					
					logger.info("Completed TP_TC_053");
		}
	}
