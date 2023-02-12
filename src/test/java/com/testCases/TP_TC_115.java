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
import com.pageObjects.TransHistoryPage;

/*Testcase Description: Testing the functionality to validate whether the account balance
  displayed in Profile Page gets updated while refund transaction */

/* Acceptance Criteria: "This testcase is successful if
1. Profile Total Balance gets updated with the amount while refund transaction" */

public class TP_TC_115 extends BaseClass {

	public String serviceName = "Home Services";
		
	public int totalBalanceBeforeUser1;  //Profile Total Balance for user 1 (buyer) before buying any service
	public int totalBalanceBeforeUser2;	 //Profile Total Balance for user 2 (seller) before selling any service

	public int totalBalanceAfterUser1;   //Profile Total Balance for user 1 (buyer) after buying a service
	public int totalBalanceAfterUser2;   //Profile Total Balance for user 2 (seller) after selling a service
	public int transactionAmountUser1;   //Transaction amount of latest transaction [Purchase] in case of user 1 (buyer)
	public int transactionAmountUser2;   //Transaction amount of latest transaction [Sale] in case of user 2 (seller)
	public int calculatedBalanceAfterUser1;  //calculatedBalanceAfterUser1 = totalBalanceBeforeUser1 + transactionAmountUser1
	public int calculatedBalanceAfterUser2;  //calculatedBalanceAfterUser2 = totalBalanceBeforeUser2 + transactionAmountUser2

	SoftAssert softassert = new SoftAssert();
	
	@Test(priority=11501)
	public void SellerLogin_CreateSvc() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_115");
		// Login as a seller
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username1);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as seller");
			
				// Create a service
				CreateService createsvc = new CreateService(driver);
				createsvc.clkbtnCreateNew();
				createsvc.clkbtnService();
				logger.info("Fill in Service form with test data");
				createsvc.txtTitleField(serviceName);  
				createsvc.txtdescriptionField(serviceName + " Description");
				createsvc.clktxtCategoryField();
				createsvc.SelectdrpdownCategory("Home & Child");
		//		createsvc.clktxtsubCategoryField();
				createsvc.selectdrpdownSubCategory("All");
				createsvc.txtPriceField("10");
				createsvc.SilderBarMaxLimit();
				createsvc.refundValidField("10");
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
					captureScreen(driver,"ServiceNotCreated");
				}
				softassert.assertAll();
				//Refresh the page
				driver.navigate().refresh();

				//Click on Profile
				Actions act = new Actions(driver);
				HomePage homepg1 = new HomePage(driver);
				act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actProfile()).click().build().perform();

				// Logout from this user 
				HomePage homepg = new HomePage(driver);
				logger.info("Seller logout");
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}
	
	@Test(priority=11502)
	public void BuyerLogin_BuySvc() throws IOException, InterruptedException
	{

		// Login as a buyer
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username2);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as Buyer");
				
				// Click on search box
				logger.info("Click on Search box");
				HomePage homepg = new HomePage(driver);
				homepg.clkbtnSearch();
				// Search for the service based on text value
				logger.info("Search for the specific service");
				SearchService searchpg = new SearchService(driver);
				searchpg.txtSearchValue(serviceName);
				searchpg.clkSerachButton();
				// Click on the link containing the text value
				logger.info("click on the created service");
				searchpg.clkbtncreatedsvc();
				// Select 'I want this' option
				logger.info("Click on 'I want this' button");
				searchpg.btnIwantThis();
				// Select the checkbox and 'buy' button
				searchpg.clkDisclaimerOption();
				searchpg.clkBuy();
				searchpg.clkbtnOk();

		//Navigate to User's Profile Page
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		logger.info("Navigating to User's Profile Page");
		
		// Logout from this user 
		logger.info("Buyer logout");
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}
	
	@Test(priority=11503)
	public void SellerLogin_Accept() throws IOException, InterruptedException
	{
		// Login as a seller
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as seller");
	
		//Navigate to the Seller's Inbox
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();
		logger.info("Go to Messages");
		
		//Sell the service as Seller through Email
		MessagePage mp = new MessagePage(driver);
		if(mp.confirmOrderDuplicateMessagePresent()==true)
		{
			mp.confirmOrderDuplicateMessageDeny();
			logger.info("Deny the duplicate Confirm Order mail");
		}
		
		mp = new MessagePage(driver);
		HomePage homepg1 = new HomePage(driver);
		homepg1.clkbtnMessages();
		logger.info("Navigate to the Seller's Inbox again");
		
		mp.findMessageInInbox("Confirm Order");
		mp.clkSaleAgreementbox();
		mp.clkbtnAccept();
		logger.info("Open Confirm Order mail and confirm sale");
				
		HomePage homepg2 = new HomePage(driver);
		homepg2.clkbtnMessages();
		logger.info("Navigate to the Seller's Inbox again");
		
		mp.clktxtfirstmail();
		mp.clkbtnConfirm();
		logger.info("Open Confirm Delivery mail and deliver the Service");
				
		//Refresh the page
				driver.navigate().refresh();
				
				//Seller logout
				logger.info("Seller logout");
				HomePage homepg3 = new HomePage(driver);
				Actions act1 = new Actions(driver);
				act1.moveToElement(homepg3.actWelcome()).moveToElement(homepg3.actSignout()).click().build().perform();

	}
	
	@Test(priority=11504)
	public void BuyerLogin_refundRequest() throws IOException, InterruptedException
	{
		// Login as a buyer
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Log in as buyer");

		//Note the total balance from Buyer Profile Page before Refund
		logger.info("Go to Buyer profile page to check the total balance");
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		ProfilePage pp = new ProfilePage(driver);
		totalBalanceBeforeUser2 = pp.printTotalBalance();
		logger.info("Total Profile Balance for Buyer before Refund: " +totalBalanceBeforeUser2);
		
		//Navigate to Buyer's Transactions Page
		homepg.clkbtnTransHist();
		
		//Get the Service Name for the first Refund available & click on the corresponding "Refund" icon
		TransHistoryPage thp = new TransHistoryPage(driver);
		logger.info("Click on refund icon");
		thp.clkbtnrefund();
		
		//Click on "I Want A Refund" button
		logger.info("Click on Confirm refund popup");
		thp.clkIwantarefundButton();
		
		//Validate whether the refund request has been sent successfully
		if(thp.gettxtrefundmsg().contains("refund request has been sent"))
		{
			softassert.assertTrue(true);
			logger.info("Refund submitted successfully");
		}
		else
		{
			softassert.assertTrue(false);
			logger.error("Refund NOT submitted!!");
			captureScreen(driver,"RefundNotSubmitted");
		}
		//Refresh the page
		driver.navigate().refresh();
		
		// Logout from this user 
				logger.info("Buyer logout");
				HomePage homepg1 = new HomePage(driver);
				Actions act1 = new Actions(driver);
				act1.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
	
	}
	
	@Test(priority=11505)
	public void SellerLogin_ApproveRefund() throws IOException, InterruptedException
	{
		// Login as a seller
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username1);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as seller");

				//Note the total balance from Seller Profile Page before confirming Refund
				logger.info("Go to Seller profile page to check the total balance");
				HomePage homepg = new HomePage(driver);
				Actions act = new Actions(driver);
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
				ProfilePage pp = new ProfilePage(driver);
				totalBalanceBeforeUser1 = pp.printTotalBalance();
				logger.info("Total Profile Balance for Seller before Refund: " +totalBalanceBeforeUser1);
				

		//Navigate to the Seller's Inbox
		homepg.clkbtnMessages();
		logger.info("Navigate to the Seller's Inbox");

		//Open Service Refund Message & Approve the Refund
		MessagePage mp = new MessagePage(driver);
		logger.info("Click on Service Refund mail and Confirm refund");
		mp.clktxtfirstmail();
		mp.clickRefundButton();
		
		//Validate 
	/*	if(mp.gettxtrefundacceptmsg().contains("The refund amount has been deducted"))
		{
			softassert.assertTrue(true);
			logger.info("Refund is accepted and the amount is deducted from seller account");
		}
		else
		{
			softassert.assertTrue(false);
			logger.error("Refund not accepted");
			captureScreen(driver,"RefundNotProcessed");
		}
		softassert.assertAll();
	*/
		//Refresh the page
		driver.navigate().refresh();
		
		//Seller logout
		logger.info("Seller logout");
		Actions act1 = new Actions(driver);
		HomePage homepg1 = new HomePage(driver);
		act1.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
	}
	
	@Test(priority=11506)
	public void BuyerLogin_calculateTotalBalance() throws IOException, InterruptedException
	{
		// Login as a buyer
				LoginPage lp = new LoginPage(driver);
				lp.clickloginlandingbtn();
				logger.info("Providing Login Details");
				lp.setUsername(username2);
				lp.setPassword(password);
				lp.clickloginbtn1();
				logger.info("Log in as buyer");

				//Navigate to User2's Profile Page
				HomePage homepg = new HomePage(driver);
				Actions act = new Actions(driver);
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
				logger.info("Navigating to User2's Profile Page");
				
				//Note the "Total Balance" of the Buyer after buying the Service
				ProfilePage pp = new ProfilePage(driver);
				totalBalanceAfterUser2 = pp.printTotalBalance();
				logger.info("Total Profile Balance after Refund for User2: " +totalBalanceAfterUser2);
						
				//Note the Transaction Amount for User2
				homepg.clkbtnTransHist();
				logger.info("Navigate to Buyer's Transaction History Page");
				TransHistoryPage thp = new TransHistoryPage(driver);
				transactionAmountUser2 = thp.gettransAmt();
				logger.info("Total Balance Before User2: " +totalBalanceBeforeUser2);
				logger.info("Transaction Amount User2: " +transactionAmountUser2);
				
				//Calculate the "Total Balance" of the Buyer after the Sale of the Service
				calculatedBalanceAfterUser2 = totalBalanceBeforeUser2 + transactionAmountUser2;
				logger.info("Calculated Balance After Refund for User2: " +calculatedBalanceAfterUser2);
				
				//If Calculated Total = Actual Total, then test passed
				if(totalBalanceAfterUser2==calculatedBalanceAfterUser2)
				{
					softassert.assertTrue(true);
					logger.info("Test Passed!!User2 (After) Calculated & Actual Total Match");
					logger.info("Test Passed!!User2 (After) Calculated & Actual Total Match");
				}
				else
				{
					captureScreen(driver,"calculateTotalBalanceUser2");
					logger.error("User2 (After) Calculated & Actual Total do not tally : Screen shot taken");
					softassert.assertTrue(false);
				}
				softassert.assertAll();
				
				//Refresh the page
				driver.navigate().refresh();
				
				// Logout from this user 
						logger.info("Buyer logout");
						Actions act1 = new Actions(driver);
						HomePage homepg1 = new HomePage(driver);
						act1.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();

	}
	
	@Test(priority=11507)
	public void SellerLogin_calculateTotalBalance() throws IOException, InterruptedException
	{
		logger.info("Login as a seller");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		
		//Navigate to User1's Profile Page
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		logger.info("Navigating to User1's(Seller's) Profile Page");
		
		//Note the "Total Balance" of the Seller after selling the Service
		ProfilePage pp = new ProfilePage(driver);
		totalBalanceAfterUser1 = pp.printTotalBalance();
		logger.info("Total Profile Balance After Refund for User1: " + totalBalanceAfterUser1);
		
		//Note the Transaction Amount for User1
		homepg.clkbtnTransHist();
		logger.info("Navigate to User1's Transaction History Page");

		TransHistoryPage thp = new TransHistoryPage(driver);
		transactionAmountUser1 =  thp.gettransAmt();
		logger.info("Total Balance Before Refund for User1: " +totalBalanceBeforeUser1);
		logger.info("Transaction Amount User1: " +transactionAmountUser1);
		
		
		//Calculate the "Total Balance" for User1 after the Sale of the Service
		calculatedBalanceAfterUser1 = totalBalanceBeforeUser1 + transactionAmountUser1;
		logger.info("Calculated Balance After Refund for User1: " +calculatedBalanceAfterUser1);
		//If Calculated Total = Actual Total, Test is passed
		if(totalBalanceAfterUser1==calculatedBalanceAfterUser1)
		{
			softassert.assertTrue(true);
			logger.info("Test Passed!!User1 (After) Calculated & Actual Total Match");
			logger.info("Test Passed!!User1 (After) Calculated & Actual Total Match");
		}
		else
		{
			captureScreen(driver,"calculateTotalBalanceUser1");
			logger.error("Test Failed!! User1 (After) Calculated & Actual Total do not tally : Screen shot taken");
			softassert.assertTrue(false);
		}

		softassert.assertAll();
		//Refresh the page
		driver.navigate().refresh();
		
	}

	@Test(priority=11508)
	public void SellerLogin_DeleteSvc() throws IOException, InterruptedException
	{
		//Delete Service from the Seller's Profile Page
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		logger.info("Navigating to the Seller's Profile Page");
		
		ProfilePage profilepg = new ProfilePage(driver);
		profilepg.clkbtnDeleteService();
		profilepg.clkbtnIwantToDelete();
		ServiceDetailPage svcdetpg = new ServiceDetailPage(driver);
		SoftAssert softassert = new SoftAssert();
		if(svcdetpg.gettxtdelSvccaption().equals("Service has been deleted"))
		{
			softassert.assertTrue(true);
			logger.info("Service has been deleted");
		}
		else
		{
			softassert.assertTrue(false);
			logger.info("Service NOT deleted");
			captureScreen(driver,"ServiceNotDeleted");
		}
		softassert.assertAll();	
	logger.info("Completed TP_TC_115");	
	}

	
	
}
