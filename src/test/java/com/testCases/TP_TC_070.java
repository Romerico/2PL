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


//Testcase Description: Testing the functionality to display no. of  Services bought in "SERVICE BOUGHT" box
/* Acceptance Criteria: The Application should display no.of  Services bought in "SERVICE BOUGHT" box 
   and this no. of Services bought in "SERVICE BOUGHT" box  should Update with according every very recent Purchased Service of the User */

public class TP_TC_070 extends BaseClass {

	public int serviceBoughtBefore;
	public int serviceBoughtAfter;
	public String servName = "NP Zoo Services";
	SoftAssert softassert = new SoftAssert();
	
	@Test(priority=7001)
	public void createServiceAsSeller() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_070");
		//Login as Seller
		LoginPage LoginPage = new LoginPage(driver);
		
		LoginPage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password of Seller");
		
		LoginPage.setUsername(username2);
		logger.info("providing user name of Seller");
		
		LoginPage.setPassword(password);
		logger.info("providing password of Seller");
		
		LoginPage.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");
		
		// Create a service
				CreateService createsvc = new CreateService(driver);
				createsvc.clkbtnCreateNew();
				createsvc.clkbtnService();
				logger.info("Fill in Service form with test data");
				createsvc.txtTitleField(servName);  
				createsvc.txtdescriptionField("NP Test for Zoo Services");
				createsvc.clktxtCategoryField();
				createsvc.SelectdrpdownCategory("Home & Child");
			//	createsvc.clktxtsubCategoryField();
				createsvc.selectdrpdownSubCategory("All");
				createsvc.txtPriceField("5");
				createsvc.SilderBarMaxLimit();
				createsvc.refundValidField("7");
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
				//Refresh the page
				driver.navigate().refresh();

		
		//Validate whether newly created Service is available in Profile page
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		logger.info("Navigate to Seller's Profile page");
		
		ProfilePage pp = new ProfilePage(driver);
		logger.info("Check if Service created on Seller's Page");
		
		if(pp.gettxtlatestsvc().equalsIgnoreCase(servName))
		{
			softassert.assertTrue(true);
			logger.info("Service created: " + servName);
		}
		else
		{
			captureScreen(driver,"createServiceAsSeller");
			logger.error("Service not created : Screen shot taken");
			softassert.assertTrue(false);
			
		}
		softassert.assertAll();
		//Refresh the page
				driver.navigate().refresh();
				// Logout from the seller
				HomePage homepg1 = new HomePage(driver);
				logger.info("Seller logout");
				Actions act1 = new Actions(driver);
				act1.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();	
	} 
	
	@Test(priority=7002)
	public void BuyerLogin_BuyService() throws InterruptedException, IOException
	{
		//Login as Buyer
		LoginPage LoginPage = new LoginPage(driver);
				
		LoginPage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password of Buyer");
		
		LoginPage.setUsername(username1);
		logger.info("providing user name of Buyer");
		
		LoginPage.setPassword(password);
		logger.info("providing password of Buyer");
		
		LoginPage.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");
		
		//Check Service Bought Before buying any new service
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		logger.info("Navigate to Buyer's Profile Page");
		
		ProfilePage pp = new ProfilePage(driver);
		serviceBoughtBefore = pp.printServiceBought();
		logger.info("Service Bought Before: " + serviceBoughtBefore);
		logger.info("Capturing the Service Bought value before buying Service");

		// Click on search box
				logger.info("Click on Search box");
				HomePage homepg1 = new HomePage(driver);
				homepg1.clkbtnSearch();
				// Search for the service based on text value
				logger.info("Search for the specific service");
				SearchService searchpg = new SearchService(driver);
				searchpg.txtSearchValue(servName);
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

		// Logout from this user 
				logger.info("Buyer logout");
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	}
		@Test(priority = 7003)
				public void SellerLogin_AcceptBid() throws IOException, InterruptedException {
	
			//Login as Seller
			LoginPage LoginPage = new LoginPage(driver);
			
			LoginPage.clickloginlandingbtn();
			logger.info("Clicking on Login button to enter user name and password of Seller");
			
			LoginPage.setUsername(username2);
			logger.info("providing user name of Seller");
			
			LoginPage.setPassword(password);
			logger.info("providing password of Seller");
			
			LoginPage.clickloginbtn1();
			logger.info("Clicking on login button to access TwoPlugs main home page");
		
		if(driver.getTitle().equals("twoPLUGS - A plug for your Service and another for your Need"))
		{
			softassert.assertTrue(true);
			logger.info("login success");
		}
		else
		{
			captureScreen(driver,"loginTest");
			logger.error("login is failed : Screen shot taken");
			softassert.assertTrue(false);
		}
		
		//Sell the Service through Email
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();
		
		MessagePage mp = new MessagePage(driver);
		logger.info("Navigate to the Seller's Inbox");
		
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
		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg2.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
		
		}
		
		@Test(priority = 7004)
		public void BuyerLogin_CheckServiceBought() throws IOException, InterruptedException {
		
		//Login as Buyer
		LoginPage LoginPage = new LoginPage(driver);
		
		LoginPage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password of Buyer");
		
		LoginPage.setUsername(username1);
		logger.info("providing user name of Buyer");
		
		LoginPage.setPassword(password);
		logger.info("providing password of Buyer");
		
		LoginPage.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");
		
		//Check Service Bought After
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		logger.info("Navigate to Buyer's Profile Page");
		
		ProfilePage pp = new ProfilePage(driver);
		serviceBoughtAfter = pp.printServiceBought();
		logger.info("Service Bought After: " + serviceBoughtAfter);
		logger.info("Capture Buyer's Services Bought after buying the Service");
		
		if(serviceBoughtAfter == (serviceBoughtBefore+1))
		{
			logger.info("Service Bought field updated as expected");
			softassert.assertTrue(true);
		}
		else
		{
			captureScreen(driver,"ServiceBoughtNotUpdated");
			logger.error("Service Bought field not updated : Screenshot taken");
			softassert.assertTrue(false);
		}
		
		//Logout
		logger.info("Buyer logout");
		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();

	}
	
		
	@Test(priority=7005)
	public void SellerLogin_DeleteService() throws InterruptedException, IOException
	{
	logger.info("Login as a seller");
	LoginPage lp = new LoginPage(driver);
	lp.clickloginlandingbtn();
	logger.info("Providing Login Details");
	lp.setUsername(username2);
	lp.setPassword(password);
	lp.clickloginbtn1();
	//Refresh the page
	driver.navigate().refresh();
	// Go to profile page and delete the service (created during this testcase)
	HomePage homepg = new HomePage(driver);
	Actions act = new Actions(driver);
	act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
	ProfilePage profilepg = new ProfilePage(driver);
	profilepg.clkbtnDeleteService();
	profilepg.clkbtnIwantToDelete();
	logger.info("Service has been deleted");
	logger.info("Service deleted successfully");
	// Go to Messages Page
	HomePage homepg1 = new HomePage(driver);
	homepg1.clkbtnMessages();
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
	// Logout from this user 
	logger.info("Seller logout");
	Actions act1 = new Actions(driver);
	act1.moveToElement(homepg.actWelcome()).moveToElement(homepg.actSignout()).click().build().perform();
	logger.info("Completed TP_TC_070");
	}
}
