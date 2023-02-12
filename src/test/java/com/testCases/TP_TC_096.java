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
import com.pageObjects.SearchService;
import com.pageObjects.ServiceDetailPage;
import com.pageObjects.TransHistoryPage;
//Testcase Description: Checks whether transaction table is displaying an entry with price 0 when buyer buys service with zero price
/* Acceptance Criteria: "This test case to be successful
1: The transaction table must display an entry with zero price when buyer is buying service with 0 price" */

public class TP_TC_096 extends BaseClass {
	
	//Check whether Seller is able to create service with Zero Price
	@Test(priority = 9601)
	public void SellerLogin_CreateServiceWithZeroPrice() throws InterruptedException {
	logger.info("Started TP_TC_096");
	LoginPage lp = new LoginPage(driver);
	lp.clickloginlandingbtn();
	logger.info("Login as seller");
	lp.setUsername(username1);              
	logger.info("providing user name");
	lp.setPassword(password);				
	logger.info("providing password");
	lp.clickloginbtn1();
	
	//Create new service page
	logger.info("Create new service");
	CreateService home=new CreateService(driver);
	home.clkbtnCreateNew();
	logger.info("Click on Create new button");
	home.clkbtnService();
	logger.info("Click on Service option");
	
	CreateService createsvc=new CreateService(driver);
	logger.info("Fill in Service form with test data");
	createsvc.txtTitleField("Jazy Fashions"); 
	createsvc.txtdescriptionField("One stop Fashion boutique");
	createsvc.clktxtCategoryField();
	createsvc.SelectdrpdownCategory("Beauty & Fashion");
	createsvc.selectdrpdownSubCategory("Clothing");
	createsvc.txtPriceField("0");
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
	//Logout from the seller
	HomePage homepg = new HomePage(driver);
	logger.info("Seller logout");
	Actions act = new Actions(driver);
	act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}
	
	//Check whether the buyer is able to buy service with Zero Price
	@Test(priority = 9602)
	public void BuyerLogin_SelectServiceWithZeroPrice() throws InterruptedException, IOException
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
	
		//Click on I want this option and buy
		searchpg.btnIwantThis();
		searchpg.clkDisclaimerOption();
		searchpg.clkBuy();
		searchpg.clkbtnOk();
		
		logger.info("Buyer is able to buy the service created with zero price!");
		
		//Logout from this user
		logger.info("Buyer logout");
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	
	}		
	
	//Seller accepts the bid offer and delivers the service
	@Test(priority = 9603)
	public void SellerLogin_AcceptOffer() throws InterruptedException, IOException
	{
		logger.info("Login as a seller");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();

		// Check messages in inbox
		logger.info("Click on message tab");
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();
		MessagePage messagepg = new MessagePage(driver);
		SoftAssert softassertion = new SoftAssert();
		// Check whether seller gets Confirm order mail and click on it
					if (messagepg.gettxtfirstmail().contains("Confirm Order")) {
					logger.info("Seller received Confirm order mail");
					softassertion.assertTrue(true);
					logger.info("Seller received Confirm Order mail!!");
					messagepg.clktxtfirstmail();
					logger.info("Click on Accept");
					messagepg.clkSaleAgreementbox();
					messagepg.clkbtnAccept();
					// Check whether the seller gets the 'Confirm Delivery' mail in inbox and validate the message
					logger.info("Validation of 'Confirm Delivery' message begins");
					MessagePage messagepg1 = new MessagePage(driver);
					// click on the confirm delivery mail
					messagepg1.clktxtfirstmail();
					// Validate the 'Confirm Delivery' message in the mail (seller)
					if (messagepg.getTextMessage().contains("Please confirm delivery of service")) {
						logger.info("The mail in Seller inbox contains Confirm delivery message");
						softassertion.assertTrue(true);
						logger.info("The mail in Seller inbox contains Confirm delivery message!!");
						messagepg.clkbtnConfirm();
						} else {
						logger.error("The mail in Seller inbox DOESNOT contain Confirm delivery message");
						softassertion.assertTrue(false);
						captureScreen(driver, "CheckSellerConfirmDeliveryMessage");
						logger.info("The mail in Seller inbox DOESNOT contain Confirm delivery message!!");
					}
				
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

	//Check whether the buyer is able to see transaction with Zero(0) eeds
	@Test(priority=9604)
	public void BuyerLogin_CheckTranHist_ZeroPrice() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as Buyer");	
	
		logger.info("Clicking on Transcation link on the homepage");
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnTransHist();
	 	
		// verifying the transaction with zero price is displayed in transaction table
		TransHistoryPage transhist = new TransHistoryPage(driver);
		int value = transhist.gettransAmt_zero();
		logger.info("Value is " +value);
		
		SoftAssert softassertion = new SoftAssert();
		if(value == 0)
		{
			logger.info(" Transaction table displays needy buying service with price zero ");
			softassertion.assertTrue(true);
			logger.info("Transaction table displays needy buying service with price zero");//

		}
		else
		{
			logger.info(" Transaction table displays needy buying service with price zero ");
			softassertion.assertTrue(true);
		}
		softassertion.assertAll();
		// Refresh the page
		driver.navigate().refresh();
		// Logout from this user (username/blue)
		logger.info("Buyer logout");
		HomePage homepg1 = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
		logger.info("Completed TP_TC_096");
	}
	
		}

