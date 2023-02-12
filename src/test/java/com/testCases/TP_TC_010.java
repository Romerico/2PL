package com.testCases;

import java.io.IOException;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pageObjects.LandingPage;

//Testcase Description: Testing the functionality by click  on icons of FACEBOOK, TWITTER, INSTAGRAM
/* Acceptance Criteria: When user  clicked  on  FACEBOOK, TWITTER, INSTAGRAM icons, 
then the application should navigate the user to targeted sites */

public class TP_TC_010 extends BaseClass {

	@Test(priority=1001)
	public void socialMediaIconTest() throws InterruptedException, IOException
	{
		logger.info("Started TP_TC_010");
		LandingPage lp = new LandingPage(driver);
		SoftAssert softAssertion= new SoftAssert();

		/*clicking facebook icon and going to facebook page
		using windowhandle to manage tabs*/
		
		String  oldTab = driver.getWindowHandle();
		lp.facebookLink();          //clicking Facebook
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);

		driver.switchTo().window(newTab.get(0));
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		//Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		driver.switchTo().window(oldTab);

		softAssertion.assertTrue(url.contains("https://www.facebook.com/twoplug"), "Test Failed");
		logger.info("got facebook page");
		
		/*clicking twitter icon and going to facebook page
		using windowhandle to manage tabs*/

		String  oldTab1 = driver.getWindowHandle();
         lp.twitterLink();   //Clicking Twitter
		ArrayList<String> newTab1 = new ArrayList<String>(driver.getWindowHandles());
		newTab1.remove(oldTab1);
		driver.switchTo().window(newTab1.get(1));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String url1 = driver.getCurrentUrl();

		softAssertion.assertTrue(url1.contains("https://twitter.com/twoplugsin"), "Test Failed");
		logger.info("got twitter page");



		driver.switchTo().window(oldTab);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		
		
		/*clicking instagram icon and going to instagram page
		using windowhandle to manage tabs*/
		
		String  oldTab2 = driver.getWindowHandle();
        lp.instaGramLink();
		ArrayList<String> newTab2 = new ArrayList<String>(driver.getWindowHandles());
		newTab2.remove(oldTab);
		driver.switchTo().window(newTab2.get(2));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		String url2 = driver.getCurrentUrl();
		softAssertion.assertTrue(url2.contains("https://www.instagram.com/twoplug"), "Test Failed");
		logger.info("got instagram page");


		driver.switchTo().window(oldTab2);
		softAssertion.assertAll();
		logger.info("Completed TP_TC_010");
	}

}
