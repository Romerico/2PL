package com.testCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MessagePage;
//Testcase Description: Testing the functionality of each page no. of  Messages  
/* Acceptance Criteria: The Application should display  equal no.of message in all pages and 
  should display order of the Pagination numbers at bottom of the page */
 

public class TP_TC_058 extends BaseClass {
	
	@Test(priority = 5801)
	public void MailPagePagination() throws IOException, InterruptedException {
	
		logger.info("Started TP_TC_058");
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
		homepage.clkbtnMessages();
		logger.info("Clicking on  messages to load mail page");

		//Verifying the number of mails in each page
		MessagePage messagepg = new MessagePage(driver);
		int noOfemails = messagepg.gettxttablerows();
		logger.info("Number of mails in first page : " +noOfemails);
		
		SoftAssert softAssertion= new SoftAssert();
		//Clicking on each pagination to verify the number of messages in each page
			for(int i=1;i<messagepg.getpagerlinkssize();i++) {
				
				WebElement links=driver.findElement(By.xpath("//span[@class='help'][contains(text(),'"+i+"')]"));
				links.click();
				logger.info("Clicking on each page to load mails in each page");
			
				int noOfemails1 = messagepg.gettxttablerows();
				logger.info("Number of mails in " +i+" page : " +noOfemails1);
				
				if(noOfemails==noOfemails1)
				{
				logger.info("Application displays equal number of messages in all pages");
				logger.info("Application displays order of pagination numbers at the bottom");
				softAssertion.assertTrue(true);
				}
				else
				{
				logger.error("Messages in all pages are not equal");
				softAssertion.assertTrue(false);
				captureScreen(driver,"EqualnumberMessage");
				}
				softAssertion.assertAll();
			
			}
			logger.info("Completed TP_TC_058");
			}
	}

