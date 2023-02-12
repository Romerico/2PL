package com.testCases;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MessagePage;

// Testcase description: Testing the functionality of   "Message icon" indicating unread  Message on Menu bar
// Acceptance Criteria: The Application should display  Unread message in Inbox and menu bar message icon unread messages number should equal   

public class TP_TC_056 extends BaseClass {
	
	@Test(priority = 5601)
	public void MessageIcon() throws IOException, InterruptedException {
		
		logger.info("Started TP_TC_056");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password");

		lp.setUsername(username2);                     //Enter username from file
		logger.info("providing user name");

		lp.setPassword(password);					//Enter password from file
		logger.info("providing password");

		lp.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");

		HomePage homepage = new HomePage(driver);
		//capture the unread messages number  from message icon 
		String unreadmsg_Menubar = homepage.gettxtunreadmsg();
		logger.info("Unread messages from Menu Bar on Home Page: " +unreadmsg_Menubar);
		
		//Go to Messages Tab
		homepage.clkbtnMessages();
		
		MessagePage messagepg = new MessagePage(driver);
		//capture the unread message number from inbox icon
		String unreadmsg_Inbox = messagepg.gettxtunreadInbox();
		logger.info("Unread messages from Inbox: " +unreadmsg_Inbox);
		
		SoftAssert softassertion = new SoftAssert();
		//verify whether the values are same
		if(unreadmsg_Menubar.equals(unreadmsg_Inbox))
		{
			logger.info("Test Passed: Application displays equal number of unread messages in Inbox and Menu bar message Icon");
			softassertion.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed: Application doesnot display equal number of unread messages in Inbox and Menu bar message Icon");
			softassertion.assertTrue(false);
			captureScreen(driver,"UnreadMessages");
		}
		
		softassertion.assertAll();
		logger.info("Completed TP_TC_056");
	}
}
