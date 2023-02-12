package com.testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import com.base.BaseClass;
import com.pageObjects.LandingPage;

//Testcase Description: Testing the functionality of Search Result Display
/* Acceptance Criteria: The application should be able to sort 3 tabs - All, Services, Needs- based on Price(lowest-highest),
Highest Rating(highest-lowest rating), Name(ascending) and Recently Updated(created/updated date/time).  */

public class TP_TC_004 extends BaseClass{

	@Test(priority=401)
	public void LivePosting_Sort_SearchResult() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_004");
		SoftAssert softAssert= new SoftAssert();
		//Click on LivePosting page
		LandingPage lp = new LandingPage(driver);
		lp.livePosting();
		
		//Test Services Page
		logger.info("Click on Services");
		lp.ClickServicesBtn();

		lp.ClickServicesSortByArrow();
		logger.info("Clicking on the Sort By Arrow");

		// Click to Select sort by Price
		lp.drpdownsortbyPrice();

		// Validating whether the page is sorting the page by Price
		if (driver.getCurrentUrl().contains("sort=price")) {
			softAssert.assertTrue(true);
			logger.info("Page sorting services in the ascending order of Price!!");
			logger.info("Service Page sorted by Price in ascending order!!");
		} else {
			softAssert.assertFalse(false);
			captureScreen(driver, "SortByPrice");
			logger.error("Page NOT sorting services in the ascending order of Price!!");
			logger.info("Service Page NOT sorted by Price in ascending order!!");
		}

		driver.navigate().refresh();

		// Click on Sort By Arrow
		lp.ClickServicesSortByArrow();
		// Click to Select sort by Highest Rating
		lp.drpdownsortbyRating();

		// Validating whether the page is sorting the page by Highest Rating
		if (driver.getCurrentUrl().contains("sort=rating")) {
			softAssert.assertTrue(true);
			logger.info("Page sorting services in the order of Highest Rating!!");
			logger.info("Service Page sorted by Highest Rating!!");
		} else {
			softAssert.assertFalse(false);
			captureScreen(driver, "SortByHighestRating");
			logger.error("Page NOT sorting services in the order of Highest Rating!!");
			logger.info("Service Page NOT sorted by Highest Rating!!");
		}

		driver.navigate().refresh();

		// Click on Sort By Arrow
		lp.ClickServicesSortByArrow();
		// Click to Select sort by Name
		lp.drpdownsortbyName();

		// Validating whether the page is sorting the page by Name
		if (driver.getCurrentUrl().contains("sort=name")) {
			softAssert.assertTrue(true);
			logger.info("Page sorting services in the order of Name!!");
			logger.info("Service Page sorted by Name!!");
		} else {
			softAssert.assertFalse(false);
			captureScreen(driver, "SortByName");
			logger.error("Page NOT sorting services in the order of Name!!");
			logger.info("Service Page NOT sorted by Name!!");
		}

		driver.navigate().refresh();
		// Click on Sort By Arrow
		lp.ClickServicesSortByArrow();
		// Click to Select sort by Recently Updated(Date)
		lp.drpdownsortbyDate();

		// Validating whether the page is sorting the page by Recently Updated(Date)
		if (driver.getCurrentUrl().contains("sort=date")) {
			softAssert.assertTrue(true);
			logger.info("Page sorting services in the order of recently updated!!");
			logger.info("Service Page sorted by Recently Updated!!");
		} else {
			softAssert.assertFalse(false);
			captureScreen(driver, "SortByRecentlyUpdated");
			logger.error("Page NOT sorting services in the order of Recently Updated!!");
			logger.info("Service Page NOT sorted by Recently Updated!!");
		}
		softAssert.assertAll();
		
		//Test Need Page
		logger.info("Click on Needs");
		lp.ClickNeedsBtn();

		lp.ClickNeedSortByArrow();
		logger.info("Clicking on the Sort By Arrow");

		// Click to Select sort by Price
		lp.drpdownsortbyPrice();

		// Validating whether the page is sorting the page by Price
		if (driver.getCurrentUrl().contains("sort=price")) {
			softAssert.assertTrue(true);
			logger.info("Page sorting services in the ascending order of Price!!");
			logger.info("Service Page sorted by Price in ascending order!!");
		} else {
			softAssert.assertFalse(false);
			captureScreen(driver, "SortByPrice");
			logger.error("Page NOT sorting services in the ascending order of Price!!");
			logger.info("Service Page NOT sorted by Price in ascending order!!");
		}

		driver.navigate().refresh();

		// Click on Sort By Arrow
		lp.ClickServicesSortByArrow();
		// Click to Select sort by Highest Rating
		lp.drpdownsortbyRating();

		// Validating whether the page is sorting the page by Highest Rating
		if (driver.getCurrentUrl().contains("sort=rating")) {
			softAssert.assertTrue(true);
			logger.info("Page sorting services in the order of Highest Rating!!");
			logger.info("Service Page sorted by Highest Rating!!");
		} else {
			softAssert.assertFalse(false);
			captureScreen(driver, "SortByHighestRating");
			logger.error("Page NOT sorting services in the order of Highest Rating!!");
			logger.info("Service Page NOT sorted by Highest Rating!!");
		}

		driver.navigate().refresh();

		// Click on Sort By Arrow
		lp.ClickServicesSortByArrow();
		// Click to Select sort by Name
		lp.drpdownsortbyName();

		// Validating whether the page is sorting the page by Name
		if (driver.getCurrentUrl().contains("sort=name")) {
			softAssert.assertTrue(true);
			logger.info("Page sorting services in the order of Name!!");
			logger.info("Service Page sorted by Name!!");
		} else {
			softAssert.assertFalse(false);
			captureScreen(driver, "SortByName");
			logger.error("Page NOT sorting services in the order of Name!!");
			logger.info("Service Page NOT sorted by Name!!");
		}

		driver.navigate().refresh();
		// Click on Sort By Arrow
		lp.ClickServicesSortByArrow();
		// Click to Select sort by Recently Updated(Date)
		lp.drpdownsortbyDate();

		// Validating whether the page is sorting the page by Recently Updated(Date)
		if (driver.getCurrentUrl().contains("sort=date")) {
			softAssert.assertTrue(true);
			logger.info("Page sorting services in the order of recently updated!!");
			logger.info("Service Page sorted by Recently Updated!!");
		} else {
			softAssert.assertFalse(false);
			captureScreen(driver, "SortByRecentlyUpdated");
			logger.error("Page NOT sorting services in the order of Recently Updated!!");
			logger.info("Service Page NOT sorted by Recently Updated!!");
		}
		softAssert.assertAll();
		
		logger.info("Completed TP_TC_004");
	}
	
	
	
	
	
	
	
}



