package com.testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import com.base.BaseClass;
import com.pageObjects.LandingPage;

//Testcase Description: Testing the functionality by clicking on Testimony images
/* Acceptance Criteria: The application should display the testimony content 
along with the name of the person when clicked on each of the image in the testimonial */

public class TP_TC_008 extends BaseClass{

	@Test (priority=801)
	public void testimony() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_008");
		LandingPage lp = new LandingPage(driver);
		SoftAssert softAssertion= new SoftAssert();
		lp.testiMony1();   //clicking on first Testimonial image...ie Kimberly's
		
		String person = lp.person_one_profile();//profile of Kimberly's   
		String persondet1 = lp.person_one_detail(); //Details of Kimberly's

		softAssertion.assertTrue((person.contains("Kimberly"))&& persondet1.contains("spend less of my paycheque and more of my talents"),
				"Test Failed");
		logger.info(person + " testimony has content");

	
		lp.testiMony2(); //clicking on 2nd Testimonial image...ie Ethan
		String person2 = lp.person_two_profile();  //profile of Ethan
		String persondet2 = lp.person_two_detail(); //Details of Ethan
		softAssertion.assertTrue((person2.contains("Ethan"))&& persondet2.contains(" Anybody else needs a ride?"),
					"Test Failed");
		logger.info(person2 + " testimony has content");
		
		lp.testiMony3();     //clicking on third Testimonial image...ie Liams
		String person3 = lp.person_three_profile();  //profile of Liam
		String persondet3 = lp.person_three_detail(); //Details of Liam
		softAssertion.assertTrue((person3.contains("Liam"))&& persondet3.contains("twoPLUGS has made me think about myself as a service provider."),
					"Test Failed");
		logger.info(person3 + " testimony has content");

	softAssertion.assertAll();
	logger.info("Test passed!");
	logger.info("Completed TP_TC_008");
}
}
