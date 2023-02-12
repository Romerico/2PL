package com.testCases;

//Testcase Description: Testing the functionality of  Application by clicking on Drop Down Menu -- Icon 'SORT BY' in Services and Needs
/* Acceptance Criteria: "For Price -- should display page with Low to High price of Services or Needs
For recently updated -- should display Services or Needs (most recent first)
For Highest rating  --  should display page with High to Low Rating of Services or Needs
For Name --   should display page in the alphabetical order of Services or Needs" */

import java.io.IOException;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.PlugsPage;

public class TP_TC_029 extends BaseClass {

	@Test(priority = 2901)
	public void plugsPageServiceSortBy() throws InterruptedException, IOException {


		PlugsPage plugspage = new PlugsPage(driver);
		SoftAssert softAssert = new SoftAssert();

		// ============Login into browser==========

		logger.info("Started TP_TC_029");
		driver.get(baseURL);
		logger.info("Opened URL");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();

		softAssert.assertTrue(driver.getTitle().equals("twoPLUGS - A plug for your Service and another for your Need"),
				"login is failed");
		logger.info("login success");


		logger.info(" " + "Page Title  : " + driver.getTitle());
		logger.info("Vaidating title page - Home page, after login");

		// ==========================================================//

		// Clicking on the Plug icon
		HomePage homepage = new HomePage(driver);
		homepage.clkbtnPlugs();
		logger.info("Clicking on the Plugs Icon");

		// ======Testing the Drop Down Menu -- Icon 'SORT BY' in Services Table========

		// Click on Services
		plugspage.ClickServicesBtn();
		logger.info("Clicking on Services");

		// Click on Sort By Arrow
		plugspage.ClickServicesSortByArrow();
		logger.info("Clicking on the Sort By Arrow");

		// Click to Select sort by Price
		plugspage.drpdownsortbyPrice();

		// Validating whether the page is sorting the page by Price

		softAssert.assertTrue(driver.getCurrentUrl().contains("sort=price"),
				"Page NOT sorting services in the ascending order of Price!!");
		logger.info("Page sorting services in the ascending order of Price!!");
		logger.info("Service Page sorted by Price in ascending order!!");


		driver.navigate().refresh();

		// Click on Sort By Arrow
		plugspage.ClickServicesSortByArrow();
		// Click to Select sort by Highest Rating
		plugspage.drpdownsortbyRating();

		// Validating whether the page is sorting the page by Highest Rating

		softAssert.assertTrue(driver.getCurrentUrl().contains("sort=rating"),
				"Page NOT sorting services in the order of Highest Rating!!");
		logger.info("Page sorting services in the order of Highest Rating!!");


		driver.navigate().refresh();

		// Click on Sort By Arrow
		plugspage.ClickServicesSortByArrow();
		// Click to Select sort by Name
		plugspage.drpdownsortbyName();

		// Validating whether the page is sorting the page by Name
		softAssert.assertTrue(driver.getCurrentUrl().contains("sort=name"),
				"Page NOT sorting services in the order of Name!!");
		logger.info("Page sorting services in the order of Name!!");


		driver.navigate().refresh();
		// Click on Sort By Arrow
		plugspage.ClickServicesSortByArrow();
		// Click to Select sort by Recently Updated(Date)
		plugspage.drpdownsortbyDate();

		// Validating whether the page is sorting the page by Recently Updated(Date)
		softAssert.assertTrue(driver.getCurrentUrl().contains("sort=date"),
				"Page NOT sorting services in the order of Recently Updated!!");
		logger.info("Page sorting services in the order of recently updated!!");

		softAssert.assertAll();
		// ===============Logging Out==============
		logger.info("Logout from this user");
		Actions act = new Actions(driver);
		act.moveToElement(homepage.actWelcome()).moveToElement(homepage.actSignout()).click().build().perform();
	}

	
	@Test(priority=2902) 
	public void plugsPageNeedsSortBy() throws InterruptedException, IOException {
	 	  	   
	   // ========Login into Home Page=========
	   
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username2);
		lp.setPassword(password);
		lp.clickloginbtn1();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(driver.getTitle().equals("twoPLUGS - A plug for your Service and another for your Need"),
				"login is failed");
		logger.info("login success");

		logger.info(" " + "Page Title  : " + driver.getTitle());
		logger.info("Vaidating title page - Home page, after login");


	   // ======Testing the Drop Down Menu -- Icon 'SORT BY' in Needs Table========
		// Clicking on the Plug icon
		HomePage homepage = new HomePage(driver);
		homepage.clkbtnPlugs();
		logger.info("Clicking on the Plugs Icon");

		// ======Testing the Drop Down Menu -- Icon 'SORT BY' in Services Table========

		// Click on Needs
		PlugsPage plugspage = new PlugsPage(driver);
		plugspage.ClickNeedsBtn();
		logger.info("Clicking on Needs");

		// Click on Sort By Arrow 
		plugspage.ClickNeedSortByArrow();
		logger.info("Clicking on the Sort By Arrow");

		// Click to Select sort by Price
		plugspage.drpdownsortbyPrice();

		// Validating whether the page is sorting the page by Price
		softAssert.assertTrue(driver.getCurrentUrl().contains("sort=price"),
				"Page NOT sorting needs in the ascending order of Price!!");
		logger.info("Page sorting needs in the ascending order of Price!!");


		driver.navigate().refresh();

		// Click on Sort By Arrow
		plugspage.ClickNeedSortByArrow();
		// Click to Select sort by Highest Rating
		plugspage.drpdownsortbyRating();

		// Validating whether the page is sorting the page by Highest Rating
		softAssert.assertTrue(driver.getCurrentUrl().contains("sort=rating"),
				"Page NOT sorting needs in the order of Highest Rating!!");
		logger.info("Page sorting needs in the order of Highest Rating!!");


		driver.navigate().refresh();

		// Click on Sort By Arrow
		plugspage.ClickNeedSortByArrow();
		// Click to Select sort by Name
		plugspage.drpdownsortbyName();

		// Validating whether the page is sorting the page by Name
		softAssert.assertTrue(driver.getCurrentUrl().contains("sort=name"),
				"Page NOT sorting needs in the order of Name!!");
		logger.info("Page sorting needs in the order of Name!!");


		driver.navigate().refresh();
		// Click on Sort By Arrow
		plugspage.ClickNeedSortByArrow();
		// Click to Select sort by Recently Updated(Date)
		plugspage.drpdownsortbyDate();

		// Validating whether the page is sorting the page by Recently Updated(Date)
		softAssert.assertTrue(driver.getCurrentUrl().contains("sort=date"),
				"Page NOT sorting needs in the order of Recently Updated!!");
		logger.info("Page sorting needs in the order of recently updated!!");

		softAssert.assertAll();
		// ===============Logging Out==============
		logger.info("Logout from this user");
		Actions act = new Actions(driver);
		act.moveToElement(homepage.actWelcome()).moveToElement(homepage.actSignout()).click().build().perform();
		
		logger.info("Completed TP_TC_029");
	   
	 }
}
