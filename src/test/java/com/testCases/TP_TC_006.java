package com.testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import com.base.BaseClass;
import com.pageObjects.LandingPage;

// Testcase Description: Testing the functionality of 'Video' Link on Landing page 
// Acceptance Criteria: The application should display by playing the Twoplugs Video

public class TP_TC_006 extends BaseClass {

	@Test(priority=601) 
	public void LandingPage_PlayVideo() throws InterruptedException, IOException {
			logger.info("Started TP_TC_006");
			LandingPage lp = new LandingPage(driver);
			SoftAssert softAssertion= new SoftAssert();
			logger.info("Click on Video link");
			lp.videoClick();  
			logger.info("Click on Play button");
			lp.playButton();

			Thread.sleep(2000);//video is playing

			String time = lp.playTime();         //checking the time change to make sure if video played

			softAssertion.assertTrue(!time.equals("0:00"), "Test Failed. Video is not playing");

			softAssertion.assertAll();
			logger.info("Test Passed: Video is playing!");

		logger.info("Completed TP_TC_006");
	}
}





