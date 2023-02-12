package com.testCases;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.ServiceDetailPage;

//Testcase Description: Testing the functionality by click on "LIVE POSTINGS"  Link with current location.
//Acceptance Criteria: The Application link should display 'LIVE POSTINGS' with the current location

public class TP_TC_002 extends BaseClass {
	
	
	@Test (priority = 201)
	public void livePostings_currentlocation() throws IOException, InterruptedException
	{
	logger.info("Started TP_TC_002");
	LandingPage lp = new LandingPage(driver);
	SoftAssert softAssertion= new SoftAssert();
	lp.livePosting(); //clicking LivePosting Button
	logger.info("Clicked on live posting");
	
	
	if((lp.ldriver.getCurrentUrl().contains("https://qatest.twoplugs.com/newsearchserviceneed")))
	{
		softAssertion.assertTrue(true);
		logger.info("Test Passed: LivePosting page opened successfully!");
	}
	else
	{
		softAssertion.assertTrue(false);
		captureScreen(driver,"LivePosting");
		logger.error("Test Failed: Liveposting page failed to open!!");
	}
	
	/*	String place =lp.location();
		if (place.equalsIgnoreCase("Toronto,ON,Canada"))
		{
			softAssertion.assertTrue(true);
			logger.info("Test Passed: Correct location retrieved!");
		}
		else
		{
			softAssertion.assertTrue(false);
			captureScreen(driver,"location");
			logger.error("Test Failed: Wrong location retrieved!");
		}*/
	
	ServiceDetailPage ServiceDetailPage = new ServiceDetailPage(driver);
	ServiceDetailPage.locationsetting("Brampton");//Passing the Location Name in the Text Box
	logger.info("Entering new Location in Location Text Box");
	
	Thread.sleep(2000);
	
	Actions action = new Actions(driver);//Action to select the option from the drop down auto suggestion
	action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
	logger.info("Selecting New Location from the Drop Down");
	
	Thread.sleep(5000);
	
	ServiceDetailPage.searchClick();//Clicking on the Search button
	logger.info("Clicking on the Search button after selecting the new location");
	
	ServiceDetailPage.firstEntryTableResult();//Clicking on the First entry of the search result
	logger.info("Clicking on the First Entry of the Search Result");
	
	logger.info("Comparing Network Location Name");
	logger.info(ServiceDetailPage.getTextNetworkComp());//Fetching the expected name
	if(ServiceDetailPage.getTextNetworkComp().contains("Brampton")){//validating the expected and actual name
		softAssertion.assertTrue(true);
		logger.info("Test Passed!! Services/needs shown as per selected location!");
		
	}
	else{
		softAssertion.assertTrue(false);
		logger.error("Test failed!!");
		captureScreen(driver, "livePostings_currentlocation");
		
	}
					
	softAssertion.assertAll();
		
		logger.info("Completed TP_TC_002");
	}
	
   
}
