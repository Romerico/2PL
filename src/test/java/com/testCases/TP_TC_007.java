package com.testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import com.base.BaseClass;
import com.pageObjects.FbPage;
import com.pageObjects.LandingPage;

//Testcase Description: Testing the functionality by click on "Share Link" 
//Acceptance Criteria: "By click on "Share" link, the application should display all Social media Links (Facebook, Twitter, Google+)

public class TP_TC_007 extends BaseClass {

	@Test(priority=701) 
	public void socialMediaTest() throws InterruptedException, IOException {
		logger.info("Started TP_TC_007");
		LandingPage lp = new LandingPage(driver);
		SoftAssert softAssertion= new SoftAssert();
		FbPage fp = new FbPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		lp.videoClick();         //video got clicked on Landing Page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		lp.sharevideo();        // share the page on social medias
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String parent = driver.getWindowHandle();
		String url = fp.fbTest(); //************** Open Facebook in different tab
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		///

		softAssertion.assertTrue(url.contains("https://www.facebook.com/login.php"), "Test Failed");
		logger.info("Test Passed: Shared in Facebook page!");



		driver.switchTo().window(parent);
		String parents = driver.getWindowHandle();
		String url1 = fp.tweetTest(); //************** Open Twitter in different tab
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));


		softAssertion.assertTrue(url1.contains("https://twitter.com/intent"), "Test Failed");
		logger.info("Test Passed: Shared in Twitter page");

		driver.switchTo().window(parents);
		softAssertion.assertAll();
		logger.info("Test Passed!");


		logger.info("Completed TP_TC_007");
	}
}





