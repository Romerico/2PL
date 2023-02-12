package com.testCases;
//Testcase Description: Testing the functionality by displaying Highlighted Menu tabs when user hover the mouse on them  in Menu bar
/* Acceptance Criteria: " The Application should display all the Menu tab (+ Create , Plugs, Transactions, Messages, Hi User) 
with highlighted  color, when the user hover  the mouse on  select Menu tab in Menu Bar.
 For Ex:-  If User hovers the mouse on messages, Menu messages should be highlighted with different color and rest of them
  with other same color" */


import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;

public class TP_TC_025 extends BaseClass {

	// To Verify that Menu Tabs icons are highlighted when mouse hover over them

	@Test(priority = 2501)
	public void highlightedMenuTab() throws IOException, InterruptedException {
		logger.info("Started TP_TC_025");
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

		// Plugs Icon
		// Before mouse hover on Plugs - Capture Color
		logger.info("Capturing the Plugs icon color before mouse hover");
		String beforehoverPlugsColor = homepage.beforehoverPlugsColor();
		logger.info("Plugs color before mouse hover: " + beforehoverPlugsColor);

		// AFter mouse hover on Plugs - Capture Color

		homepage.hoverPlugsIcon();
		String afterhoverPlugsColor = homepage.afterhoverPlugsColor();

		logger.info("Plugs color after mouse hover: " + afterhoverPlugsColor);
		// Assertion: To check whether the icon is getting highlighted when hovering the
		// mouse

		softAssert.assertFalse(beforehoverPlugsColor.equalsIgnoreCase(afterhoverPlugsColor), "Plug Icon color NOT highlighted!");
		logger.info("Test Passed: Plug Icon color Highlighted");

		// Click on the twoPlugs logo
		HomePage homepage1 = new HomePage(driver);
		homepage1.clkbtnLogo();

		// Transaction Icon
		// Before mouse hover on Transaction - Capture Color
		logger.info("Capturing the Transaction icon color before mouse hover");
		String beforehoverTransactionColor = homepage.beforehoverTransactionColor();
		logger.info("Transaction color before mouse hover: " + beforehoverTransactionColor);

		// AFter mouse hover on Transaction - Capture Color

		homepage.hoverTransactionIcon();
		String afterhoverTransactionColor = homepage.afterhoverTransactionColor();

		logger.info("Transaction color after mouse hover: " + afterhoverTransactionColor);
		// Assertion: To check whether the icon is getting highlighted when hovering the
		// mouse
		softAssert.assertFalse(beforehoverTransactionColor.equalsIgnoreCase(afterhoverTransactionColor), "Test Passed: Transaction Icon color Highlighted");


		// Click on the twoPlugs logo
		HomePage homepage2 = new HomePage(driver);
		homepage2.clkbtnLogo();

		// Messages Icon
		// Before mouse hover on Messages - Capture Color
		logger.info("Capturing the Messages icon color before mouse hover");
		String beforehoverMessagesColor = homepage.beforehoverMessagesColor();
		logger.info("Messages color before mouse hover: " + beforehoverMessagesColor);

		// AFter mouse hover on Messages - Capture Color

		homepage.hoverMessagesIcon();
		String afterhoverMessagesColor = homepage.afterhoverMessagesColor();

		logger.info("Messages color after mouse hover: " + afterhoverMessagesColor);
		// Assertion: To check whether the icon is getting highlighted when hovering the
		// mouse
		softAssert.assertFalse(beforehoverMessagesColor.equalsIgnoreCase(afterhoverMessagesColor), "Test Passed: Messages Icon color Highlighted");


		// Click on the twoPlugs logo
		HomePage homepage3 = new HomePage(driver);
		homepage3.clkbtnLogo();

		// Hi,user Icon
		// Before mouse hover on Hi User Icon - Capture Color
		logger.info("Capturing the Hi icon color before mouse hover");
		String beforehoverHiUserColor = homepage.beforehoverHiUserColor();
		logger.info("Hi User color before mouse hover: " + beforehoverMessagesColor);

		// AFter mouse hover on Hi User Icon - Capture Color

		homepage.hoverHiUserIcon();
		String afterhoverHiUserColor = homepage.afterhoverHiUserColor();

		logger.info("Hi User color after mouse hover: " + afterhoverHiUserColor);

		// Assertion: To check whether the icon is getting highlighted when hovering the
		// mouse
		softAssert.assertFalse(beforehoverHiUserColor.equalsIgnoreCase(afterhoverHiUserColor), "Test Passed: Hi User Icon color Highlighted");


		softAssert.assertAll();
		logger.info("Icon colors are highlighted");
		logger.info("Completed TP_TC_025");
	}

}
