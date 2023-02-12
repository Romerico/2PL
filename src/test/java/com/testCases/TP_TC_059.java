package com.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MailPage;

//Testcase Description: Testing the functionality of  Sort by  > drop down list 
//Acceptance Criteria: The Application should display the Message Trail according the selected option from the Sort By dropdown list by order NAME/DATE

public class TP_TC_059 extends BaseClass {

	@Test(priority = 5901)
	public void MailPageSortByName() throws InterruptedException, IOException {
	
		logger.info("Started TP_TC_059");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password");

		lp.setUsername(username2);                    
		logger.info("providing user name");

		lp.setPassword(password);					
		logger.info("providing password");

		lp.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");
		
		//Loading messages page
		logger.info("Clicking on  messages to load mail page");
		HomePage homepage = new HomePage(driver);
		homepage.clkbtnMessages();
		
		MailPage mail=new MailPage(driver);
		
		//Click on Sort dropdown and select NAME
				mail.clickSortBy();
				mail.selectSortByName();
				
				logger.info("Sort using Name option");
				//Validate whether the mails are sorted by Name
				SoftAssert softassertion = new SoftAssert();
				if(driver.getCurrentUrl().contains("https://qatest.twoplugs.com/message/984/1/name"))
				{
					softassertion.assertTrue(true);
					logger.info("Test Passed: Mails are sorted in the ascending order of Name");
				}
				else
				{
					softassertion.assertTrue(false);
					logger.error("Test Failed: Mails are NOT sorted in the ascending order of Name");
					captureScreen(driver,"MailSortByName");
				}
				softassertion.assertAll();
				
				//Refresh the page
				driver.navigate().refresh();  
			}
				@Test(priority = 5902)
				public void MailPageSortByDate() throws InterruptedException, IOException {
				
				MailPage mail1=new MailPage(driver);
				//Select option DATE
				mail1.clickSortBy();
				mail1.selectSortByDate();
				logger.info("Sort using Date option");
				//Validate whether the mails are sorted by Date
				SoftAssert softassertion = new SoftAssert();
				if(driver.getCurrentUrl().contains("https://qatest.twoplugs.com/message/984/1/date"))
				{
					softassertion.assertTrue(true);
					logger.info("Test Passed: Mails are sorted in the descending order of Date");
				}
				else
				{
					softassertion.assertTrue(false);
					logger.error("Test Failed: Mails are NOT sorted in the descending order of Date");
					captureScreen(driver,"MailSortByDate");
				}
				
				softassertion.assertAll();	
				logger.info("Completed TP_TC_059");

}
}
