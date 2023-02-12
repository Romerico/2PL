package com.testCases;
//Testcase Description: Testing the functionality of Up Dates at Dash Board and "more" buttons at all Tables Headers

/* Acceptance Criteria: "The Application should display description of current updates in all table at Dash Board and Tables Headers 'more' buttons should take the user in different page to see the complete uapdates from his network and user follow
1.  Pending Transactions – """"More"""" button ( It should display all in completed Selling Services and Buying Needs Transactions) 
2.  Live update from users I follow  – """"More"""" button ( It should display all updated postings of Services and Needs from Users those who ""User Following""
3.   Live update in my Network  – """"More"""" button  ( It should display all updated postings of Services and Needs from Users those who from ""User's same Network"" ) 
4.  Top 5 Trades in my  Network  ( It should display first Top most 5 Traders those who sold most Service in User's same Network)
5. Top 5 services in  my Network ( It should display first Top most 5 Services which sold in high quantity in the User's same Network)

By clicking on ""More"" button, the Application should display rest of details  in new page for with Pagination and each page should contain 10 transactions" */

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;

public class TP_TC_026 extends BaseClass {

	// To Verify DashBoard Updates

	@Test(priority = 2601)
	public void DashBoardUpdates() throws InterruptedException, IOException {
		logger.info("Started TP_TC_026");
		LoginPage loginpage = new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		SoftAssert softAssert = new SoftAssert();

		// Clicking on the Home page Login button
		loginpage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password");

		// Entering text in username field
		loginpage.setUsername(username1);
		logger.info("providing username");

		// Entering text in password field
		loginpage.setPassword(password);
		logger.info("providing password");

		// Clicking on the login button
		loginpage.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");

		// Clicking the More Button on the Pending Transaction Table
		homepage.pendingTransMoreBtn();
		logger.info("Clicking on Pending Transaction More Button");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Verifying the Pending transaction on the page displays 10 transactions

		softAssert.assertTrue(homepage.PendingTranRows() == 10,
				"Not Displaying 10 Transactions on the Pending Transaction Table : Screen shot taken");
		logger.info("Displaying 10 Transactions on the Pending Transaction Table");


		// Clicking on the Home Button icon to go to previous Home page
		logger.info("Clicking the Home botton to go back to previous Home page");
		driver.navigate().back();

		// Clicking the More Button icon on the Live Update in my Network Table
		homepage.liveUpdNetworkMoreBtn();
		logger.info("Clicking on Live Update in my Network More button");

		// Verifying the Live Update in my network on the page displays 10 updates
		softAssert.assertTrue(homepage.LiveUpdNetworkRows() == 10,
				"Not Displaying 10 updates on the Live Update in my network Table: Screen shot taken");
		logger.info("Displaying 10 updates on the Live Update in my network Table");


		// Clicking on the Home Button icon to go to Home previous page
		logger.info("Clicking the Home botton to go back to previous Home page");
		driver.navigate().back();

		// Clicking More Button icon on the Live update from user i follow table
		homepage.liveUpdFromUserMoreBtn();
		logger.info("Clicking on Live Update from user i follow More botton");

		// Verifying that Live Update from User I follow on the page displays 10 updates
		softAssert.assertTrue(homepage.LiveUpdFromUserRows() == 10,
				"Not Displaying 10 updates on the Live Updates from user i Follow Table : Screen shot taken");
		logger.info("Displaying 10 updates on the Live Updates from user i Follow Table");


		// Clicking on the Home Button icon to go to previous Home page
		logger.info("Clicking the Home botton to go back to previous Home page");
		driver.navigate().back();

		// Verifying Top 5 Trades in My Network displays 1st top 5
		softAssert.assertTrue(homepage.TopTraderRows() == 5,
				"Not Displaying 5 Top Trades in my Network : Screen shot taken");

		logger.info("Displaying 5 Top Trades in my Network");


		// Verifying Top 5 Services in My Network displays 1st top 5
		softAssert.assertTrue(homepage.Top5ServicesRows() == 5,
				"Not Displaying 5 Top services in my Network : Screen shot taken");
		logger.info("Displaying 5 Top services in my Network");

		softAssert.assertAll();
		logger.info("Completed TP_TC_026");
	}

}
