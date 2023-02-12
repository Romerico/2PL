package com.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LandingPage;

public class TP_TC_009 extends BaseClass {

	//Testcase Description: Testing the functionality of dropdown buttons in FAQ page
	//Acceptance Criteria: All buttons should be clickable and display answers in drop down pages.

	@Test(priority=901)
	public void questions() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_009");
		LandingPage lp = new LandingPage(driver);	
		logger.info("Click on FAQ at the top of the page");
		lp.clkbtnFaq();
		SoftAssert softAssertion= new SoftAssert();
		
		String ques1 = lp.firstQuestion();	//clicking on Question1
		String ans1 = lp.firstAnswer();		//clicking on Answer1

		softAssertion.assertTrue(ques1.contains("What do I need to sign up")
				&& ans1.contains("You need a facebook account or a valid email"), "Test Failed");
		logger.info("got Question1 and its answer ");


		
		String ques2 = lp.secQuestion();	//clicking on Question2
		String ans2 = lp.secAnswer();		//clicking on Answer2

		softAssertion.assertTrue(ques2.contains("What is eeds?")
				&& ans2.contains("eeds is an international barter currency developed by twoPLUGS."), "Test Failed");
		logger.info("got Question2 and its answer ");

		
		
		String ques3 = lp.thirdQuestion();	//clicking on Question3
		String ans3 = lp.thirdAnswer();		//clicking on Answer3

		softAssertion.assertTrue(ques3.contains("How much should I sell my service (skill)" )
				&& ans3.contains("The prices of services will be gradually determined by buyers and sellers"), "Test Failed");
		logger.info("got Question3 and its answer ");

		
		String ques4 = lp.fourthQuestion();	//clicking on Question4
		String ans4 = lp.fourthAnswer();    //clicking on Answer4
		softAssertion.assertTrue(ques4.contains("How do I trust a Seller" )
				&& ans4.contains("Check out the seller experience ranking, the ratings"), "Test Failed");
		logger.info("got Question4 and its answer ");

		
		String ques5 = lp.fifthQuestion();		//clicking on Question5
		String ans5 = lp.fifthAnswer();			//clicking on Answer5

		softAssertion.assertTrue(ques5.contains("What kind of service (skill) can I sell" )
				&& ans5.contains("You can sell any kind of service"), "Test Failed");
		logger.info("got Question5 and its answer ");

		
		String ques6 = lp.sixthQuestion();		//clicking on Question6
		String ans6 = lp.sixthAnswer();

		softAssertion.assertTrue(ques6.contains("Can I use twoPLUGS" )
				&& ans6.contains("twoPLUGS is currently only available in US and Canada"), "Test Failed");
		logger.info("got Question6 and its answer ");


		
		String ques7 = lp.seventhQuestion();	//clicking on Question7
		String ans7 = lp.seventhAnswer();

		softAssertion.assertTrue(ques7.contains("How do I trust a Buyer to pay me?" )
				&& ans7.contains("All purchases use a payment type of \"twoplugs\""), "Test Failed");
		logger.info("Got Question7 and its answer ");
		

				
		softAssertion.assertAll();
		logger.info("Completed TP_TC_009");
	}
}

