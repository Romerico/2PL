package com.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MessagePage;
import com.pageObjects.PlugsPage;
import com.pageObjects.TransHistoryPage;

//Testcase Description: Checks whether a pagination is having a start and end button and a previous and next page button
/* Acceptance Criteria: "This testcase to be successful 
1:The user can move  pages to previous and next pages. And it has a start and end button." */

public class TP_TC_097 extends BaseClass {

	@Test(priority = 9701)
	public void login() throws InterruptedException {
		logger.info("Started TP_TC_097");
		driver.get(baseURL);
		logger.info("Opened URL");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username1);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Logged in");
	}

	// Checks whether a pagination is having a start,end,previous and next page
	// button on Plugs,Messages and transactions Page
	@Test(priority = 9702)
	public void CheckPaginationPlugs() throws InterruptedException, IOException {
		// Pagination on Plugs page
		logger.info("Pagination in Plugs Page ");
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnPlugs();
		PlugsPage plugpg = new PlugsPage(driver);

		// Click on next button to enable previous and first button
		logger.info("Click on Next button");
		plugpg.clkbtnNext();

		// Total pager elements are captured
		logger.info("Capture count of links on the Paging element");
		logger.info("Plugs Page links: " + plugpg.getpagerlinkssize());

		SoftAssert softassertion = new SoftAssert();
		// Validate whether there are any pager links available
		logger.info("Validation of Plugs page links begins");
		if (plugpg.getpagerlinkssize() > 0) {
			logger.info("Links are present");
			softassertion.assertTrue(true);
			logger.info("Links are present!!");

			// click on all the links present in the page
			logger.info("Click on the page numbered links");
			for (int i = 1; i < plugpg.getpagerlinkssize() - 4; i++) // removed 4 links - Next,Previous,Last,First
			{
				WebElement plugpglink = driver.findElement(
						By.xpath("//ul[@class='pagination clearfix']/li/a/span[contains(text(),'" + i + "')]"));
				plugpglink.click();
			}

			// Capture 4 help links - first last previous next
			logger.info("Capture 4 help links - Next, Last, Previous, Next");
			logger.info("Plugs page help links: " + plugpg.gethelplinkssize()); // returns 4 links

			driver.navigate().refresh();

			// Check whether the user is able to click on all 4 help links
			try {
				plugpg.clkbtnNext();
			} catch (StaleElementReferenceException e) // Identifying the Next button again to catch
														// StaleElementReferenceException
			{
				logger.info("Capture Next button again and click on it");
				PlugsPage plugpg1 = new PlugsPage(driver);
				plugpg1.clkbtnNext();
				logger.info("Clicked on Next button");
			}

			logger.info("Click on Start button");
			plugpg.clkbtnStart();
			logger.info("Clicked on Start button");

			logger.info("Click on End button");
			plugpg.clkbtnEnd();
			logger.info("Clicked on End button");

			logger.info("Click on Previous button");
			plugpg.clkbtnPrevious();
			logger.info("Clicked on Previous button");

			logger.info("Pagination test on Plugs page is successful");
		} else {
			softassertion.assertTrue(false);
			logger.error("No links present");
			captureScreen(driver, "CheckPaginationPlugs");
			logger.info("No links are present!!");
		}
		softassertion.assertAll();

		driver.navigate().refresh(); // refreshes the current page
	}

	// Pagination in Transaction history page
	@Test(priority = 9703)
	public void CheckPaginationTransHistory() throws IOException, InterruptedException {
		logger.info("Pagination in Transaction History Page ");
		HomePage homepg = new HomePage(driver);
		TransHistoryPage transhist = new TransHistoryPage(driver);
		homepg.clkbtnTransHist();
		SoftAssert softassertion = new SoftAssert();

		if (transhist.getTablerowscount() > 2) {
			// Click on next button to enable first and previous button
			logger.info("Click on Next button");
			transhist.clkbtnNext();

			// Total pager elements are captured
			logger.info("Capture count of links on the Paging element");
			logger.info("Transaction History Page links: " + transhist.getpagerlinkssize());

			// Validate whether there are any pager links available
			logger.info("Validation of links begins");
			if (transhist.getpagerlinkssize() > 0) {
				logger.info("Links are present");
				softassertion.assertTrue(true);
				logger.info("Links are present!!");

				// click on all the links present in the page
				logger.info("Click on the page numbered links");
				for (int i = 1; i < transhist.getpagerlinkssize() - 4; i++) // Removed 4 links -
																			// Next,Previous,Last,First
				{
					WebElement txnpglink = driver.findElement(
							By.xpath("//ul[@class='pagination clearfix']/li/a/span[contains(text(),'" + i + "')]"));
					txnpglink.click();
				}

				// Capture 4 help links - first last previous next
				logger.info("Capture 4 help links - Next, Last, Previous, Next");
				logger.info("Transaction history help links: " + transhist.gethelplinkssize()); // returns 4
																										// links

				driver.navigate().refresh();

				// Check whether the user is able to click on all help links
				if (transhist.displaysbtnStart()) {
					logger.info("Click on Start button");
					transhist.clkbtnStart();
					logger.info("Clicked on Start button");
				}

				if (transhist.displaysbtnEnd()) {
					logger.info("Click on End button");
					transhist.clkbtnEnd();
					logger.info("Clicked on End button");
				}
				if (transhist.displaysbtnPrevious()) {
					logger.info("Click on Previous button");
					transhist.clkbtnPrevious();
					logger.info("Clicked on Previous button");
				}
				logger.info("Pagination Test for Transaction history page is successful");
			}
		} else if (transhist.gettxtnotransaction().contains("no transactions")) {
			softassertion.assertTrue(true);
			logger.info("No links present!! User has done no Transactions!!");
			logger.info("No links are present!! User has performed no Transactions yet!!");
			logger.info("Pagination Test for Transaction history page is successful");
		} else {
			softassertion.assertTrue(false);
			logger.error("No links/No Message available for Transactions");
			captureScreen(driver, "CheckTransactionHistory");
			logger.info("Error in Transaction History Page!!");
		}

		softassertion.assertAll();

		driver.navigate().refresh(); // refreshes the current page

	}

	// Pagination in Messages page
	@Test(priority = 9704)
	public void CheckPaginationMessage() throws IOException, InterruptedException {
		logger.info("Pagination in Messages Page ");
		HomePage homepg = new HomePage(driver);
		homepg.clkbtnMessages();

		MessagePage messagepg = new MessagePage(driver);
		SoftAssert softassertion = new SoftAssert();

		if (messagepg.getTablerowscount() > 2) {

			// Click on next button to enable first and previous button
			logger.info("Click on Next button");
			messagepg.clkbtnNext();

			// Total pager elements are captured
			logger.info("Capture count of links on the Paging element");
			logger.info("Message Page links: " + messagepg.getpagerlinkssize());

			// Validate whether there are any pager links available
			logger.info("Validation of links begins");
			if (messagepg.getpagerlinkssize() > 0) {
				logger.info("Links are present");
				softassertion.assertTrue(true);
				logger.info("Links are present!!");

				// click on all the links present in the page
				logger.info("Click on the page numbered links");
				for (int i = 1; i < messagepg.getpagerlinkssize() - 4; i++) // removed 4 links -
																			// Next,Previous,Last,First
				{
					WebElement txnpglink = driver.findElement(
							By.xpath("//ul[@class='pagination clearfix']/li/a/span[contains(text(),'" + i + "')]"));
					txnpglink.click();
				}

				// Capture 4 help links - first last previous next
				logger.info("Capture 4 help links - Next, Last, Previous, Next");
				logger.info("Message Page Help links: " + messagepg.gethelplinkssize()); // returns 4 links

				driver.navigate().refresh();

				// Check whether the user is able to click on all 4 help links

				logger.info("Click on Start button");
				messagepg.clkbtnStart();

				logger.info("Clicked on Start button");

				try {
					messagepg.clkbtnNext();
				} catch (StaleElementReferenceException e) // Identifying the next button again to capture the
															// StaleElementReferenceException
				{
					logger.info("Click on Next button");
					MessagePage messagepg1 = new MessagePage(driver);
					messagepg1.clkbtnNext();
					logger.info("Clicked on Next button");
				}
				logger.info("Click on End button");
				messagepg.clkbtnEnd();
				logger.info("Clicked on End button");
				logger.info("Click on Previous button");
				messagepg.clkbtnPrevious();
				logger.info("Clicked on Previous button");
				logger.info("Pagination Test for Message page is successful");
			}
		} else if (messagepg.getTablerowscount() <= 1) {
			softassertion.assertTrue(true);
			logger.info("No links present!! User has no Messages!!");
			logger.info("No links are present!! User has no messages!!");
			logger.info("Pagination Test for Message page is successful");
		} else {
			softassertion.assertTrue(false);
			logger.error("No links/No Message available for Messages");
			captureScreen(driver, "CheckPaginationMessage");
			logger.info("Error in Messages Page!!");
		}

		softassertion.assertAll();

		logger.info("TP_TC_097 Completed!");
	}

}
