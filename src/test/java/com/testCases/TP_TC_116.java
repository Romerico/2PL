package com.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.ProfilePage;
import com.pageObjects.TransHistoryPage;
import com.pageObjects.TransferEedsPage;

/* Testcase Description: Testing the functionality to validate whether the account balance displayed in Profile Page 
  gets updated during transfer eeds transaction */
 
/* Acceptance Criteria: "This testcase is successful if
1. Profile Total Balance gets updated with the amount while transferring eeds" */

public class TP_TC_116 extends BaseClass {
	
	public int totalBalanceBeforeUser1;
	public int totalBalanceBeforeUser2;
	public int totalBalanceAfterUser1;
	public int totalBalanceAfterUser2;
	public int transactionAmountUser1;
	public int transactionAmountUser2;
	public int calculatedBalanceAfterUser1;
	public int calculatedBalanceAfterUser2;
	
	SoftAssert softassert = new SoftAssert();
	
	@Test(priority=11601)
	public void totalBalanceUser1BeforeTransfer() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_116");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Successfully logged in as a user1");

		//Click on Profile
		Actions act = new Actions(driver);
		HomePage homepg1 = new HomePage(driver);
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actProfile()).click().build().perform();
		
		//Note the "Total Balance" of the user1 before transferring the eeds
		ProfilePage pp = new ProfilePage(driver);
		totalBalanceBeforeUser1 = pp.printTotalBalance();
		logger.info("ProfilePage Total Balance (Before) for User1: " + totalBalanceBeforeUser1);
		
		// Logout from the user
		HomePage homepg2 = new HomePage(driver);
		logger.info("User logout");
		act.moveToElement(homepg2.actWelcome()).moveToElement(homepg2.actSignout()).click().build().perform();

	}
	
	@Test(priority=11602)
	public void totalBalanceUser2BeforeTransfer() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Successfully logged in as a user2");
		
		//Click on Profile
		Actions act = new Actions(driver);
		HomePage homepg1 = new HomePage(driver);
		act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actProfile()).click().build().perform();
		
		//Note the "Total Balance" of the user1 before transferring the eeds
		ProfilePage pp = new ProfilePage(driver);
		totalBalanceBeforeUser2 = pp.printTotalBalance();
		logger.info("ProfilePage Total Balance (Before) for User2: " + totalBalanceBeforeUser2);
				
		// Logout from the user
		HomePage homepg2 = new HomePage(driver);
		logger.info("User logout");
		act.moveToElement(homepg2.actWelcome()).moveToElement(homepg2.actSignout()).click().build().perform();
	}
	
	@Test(priority=11603)
	public void transferFromUser1ToUser2() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Successfully logged in as a user1");

		//Search for User2 from User1's Search box & Navigate to the User2's Profile Page for the Transfer
		HomePage hp = new HomePage(driver);
		hp.settxtsearch("blue");
		hp.clksearchedtxt();
						
		//Click on the "Transfer Eeds" icon, on the User2's profile page
		ProfilePage pp = new ProfilePage(driver);
		pp.clkbtnsendeeds();
		logger.info("Click on Send eeds button");
		
		//Navigate to the "Transfer Eeds" page
		TransferEedsPage tep = new TransferEedsPage(driver);
		tep.setTransferAmount("20");
		tep.clkbtnTransfer();
		tep.clkbtnConfirmTransfer();
		
		//Validate whether eeds transfer is successful
		if(tep.gettrfeedsmsg().contains("Credit Transfer was successful"))
		{
			softassert.assertTrue(true);
			logger.info("Test Passed: Transfer of eeds successful");
		}
		else
		{
			softassert.assertTrue(false);
			logger.error("Test Failed: Eeds Transfer NOT successful");
			captureScreen(driver,"transferFromUser1ToUser2");
		}
		softassert.assertAll();
		
		//Refresh the page
		driver.navigate().refresh();
	}
	
	@Test(priority=11604)
	public void calculateTotalBalanceUser1() throws IOException, InterruptedException
	{
		//Click on Profile
				Actions act = new Actions(driver);
				HomePage homepg1 = new HomePage(driver);
				act.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actProfile()).click().build().perform();
				
		//Note the "Total Balance" for User1, after the Transfer of Eeds
		ProfilePage pp = new ProfilePage(driver);
		totalBalanceAfterUser1 = pp.printTotalBalance();
		logger.info("ProfilePage Total Balance (After) for User1: " + totalBalanceAfterUser1);

		//Note the Transaction Amount for User1
				homepg1.clkbtnTransHist();
				logger.info("Navigate to Buyer's Transaction History Page");
				TransHistoryPage thp = new TransHistoryPage(driver);
				transactionAmountUser1 = thp.gettransAmt();
				logger.info("Total Balance Before User1: " +totalBalanceBeforeUser1);
				logger.info("Transaction Amount User1: " +transactionAmountUser1);
				
				//Calculate the "Total Balance" of the Buyer after the Sale of the Service
				calculatedBalanceAfterUser1 = totalBalanceBeforeUser1 + transactionAmountUser1;
				logger.info("Calculated Balance After User1: " +calculatedBalanceAfterUser1);
				
				//If Calculated Total = Actual Total, then test passed
				if(totalBalanceAfterUser1==calculatedBalanceAfterUser1)
				{
					softassert.assertTrue(true);
					logger.info("Test Passed!!User1 (After) Calculated & Actual Total Match");
					logger.info("User1 Expected After Total: " + calculatedBalanceAfterUser1 + " Actual Total: " + totalBalanceAfterUser1);
				}
				else
				{
					captureScreen(driver,"calculateTotalBalanceUser1");
					logger.error("User1 (After) Calculated & Actual Total do not tally : Screen shot taken");
					softassert.assertTrue(false);
				}
				softassert.assertAll();
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//Refresh the page
				//driver.navigate().refresh();
				
				homepg1.clkbtnLogo();
				
				// Logout from this user 
				logger.info("Buyer logout");
				Actions act1 = new Actions(driver);
				HomePage homepg2 = new HomePage(driver);
				act1.moveToElement(homepg2.actWelcome()).moveToElement(homepg2.actSignout()).click().build().perform();
	}
	
	@Test(priority=11605)
	public void calculateTotalBalanceUser2() throws IOException, InterruptedException
	{
		logger.info("Login as a User2");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		
		//Navigate to User2's Profile Page
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		logger.info("Navigating to User2's Profile Page");
		
		//Note the "Total Balance" for User2, after the Transfer of Eeds
		ProfilePage pp = new ProfilePage(driver);
		totalBalanceAfterUser2 = pp.printTotalBalance();
		logger.info("ProfilePage Total Balance (After) for User2: " + totalBalanceAfterUser2);
		
		//Note the Transaction Amount for User2
		homepg.clkbtnTransHist();
		logger.info("Navigate to User2's Transaction History Page");

		TransHistoryPage thp = new TransHistoryPage(driver);
		transactionAmountUser2 =  thp.gettransAmt();
		logger.info("Total Balance Before User1: " +totalBalanceBeforeUser1);
		logger.info("Transaction Amount User1: " +transactionAmountUser1);
		logger.info("Transaction Amount for User2: " + transactionAmountUser2);
		
		//Calculate the "Total Balance" for User2, after the Transfer of Eeds
		calculatedBalanceAfterUser2 = totalBalanceBeforeUser2 + transactionAmountUser2;
		logger.info("Calculated Balance After User2: " +calculatedBalanceAfterUser2);
		//If Calculated Total = Actual Total, Test is passed
		if(totalBalanceAfterUser2==calculatedBalanceAfterUser2)
		{
			softassert.assertTrue(true);
			logger.info("Test Passed!!User2 (After) Calculated & Actual Total Match");
			logger.info("User2 After Expected Total: " + calculatedBalanceAfterUser2 + " Actual Total: " + totalBalanceAfterUser2);
		}
		else
		{
			captureScreen(driver,"calculateTotalBalanceUser2");
			logger.error("Test Failed!! User2 (After) Calculated & Actual Total do not tally : Screen shot taken");
			softassert.assertTrue(false);
		}

		softassert.assertAll();
		//Refresh the page
		driver.navigate().refresh();
		
		//User Logout
		logger.info("User logout");
		Actions act1 = new Actions(driver);
		HomePage homepg1 = new HomePage(driver);
		act1.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actSignout()).click().build().perform();
}

}