package com.testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.CreateService;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.NeedDetailPage;
import com.pageObjects.ProfilePage;
import com.pageObjects.ServiceDetailPage;

//Testcase Description: Testing the functionality to display "NEEDS and Services Table/Box" on  profile page

/* Acceptance Criteria: 
"This Tables/Boxes should display with  ""Needs/Services"" header including with No. of Active ""Needs/Services""
Needs: All  Needs should be display in ""Needs Table""  and this should be created by ""NEW NEED"" button located under Needs table or ""CREATE NEW NEED"" Link located in Header Menu
In Needs table all Needs columns should contain with ""EDIT and DELETE"" icons

Services:   All  Services should be display in ""Services Table""  and this should be created by ""NEW SERVICES"" button located under Services table or ""CREATE NEW SERVICE"" Link located in Header Menu
In Services table all Service columns should contain with ""RATING Stars, SERVICES SOLD NO., EDIT and DELETE icons"""

*/

public class TP_TC_074 extends BaseClass {

	String needName1 = "NP Cleaning Need";
	String needName2 = "NP Cooking Need";
	String serviceName1 = "NP Sewing Services";
	String serviceName2 = "NP Tree Planting Services";
	SoftAssert softassert = new SoftAssert();
	
	@Test(priority=7401)
	public void UserLogin_Create2needs() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_074");
		//Login as a user
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username3);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Login successful");
		//Create Need1
		CreateNeed cn = new CreateNeed(driver);
		cn.clkbtnCreateNew();
		cn.clkbtnNeed();
		logger.info("Creating Need1: " + needName1);
		cn.txtTitleField(needName1);
		logger.info("Enter Need Title");
		
		cn.txtdescriptionField(needName1 + " Description");
		logger.info("Enter Need Description");
		cn.clktxtCategoryField();
		cn.SelectdrpdownCategory("Arts & Photography");
	//	cn.clktxtsubCategoryField();
		cn.selectdrpdownSubCategory("Photography");
		cn.txtPriceField("5");
		logger.info("Enter Need Price");
			
		cn.btnSubmitNeedPage();
		logger.info("Create Need1");
		
		//Validate whether need1 is created successfully
				
				NeedDetailPage needdetpg = new NeedDetailPage(driver);
				if(needdetpg.gettxtaddNeedcaption().contains("Need has been added"))
				{
					softassert.assertTrue(true);
					logger.info("Created Need1 Successfully");	
				}
				else
				{
					softassert.assertTrue(false);
					logger.error("Need1 NOT created");
					captureScreen(driver,"Need1NotCreated");
				}
		//Refresh the page
		driver.navigate().refresh();
		
		//Create Need2
		CreateNeed cn1 = new CreateNeed(driver);
		cn1 = new CreateNeed(driver);
		cn1.clkbtnCreateNew();
		cn1.clkbtnNeed();
		logger.info("Creating Need2: " + needName2);
		cn1.txtTitleField(needName2);
		logger.info("Enter Need Title");
		
		cn1.txtdescriptionField(needName2 + " Description");
		logger.info("Enter Need Description");
		
		cn1.clktxtCategoryField();
		cn1.SelectdrpdownCategory("Home & Child");
	//	cn1.clktxtsubCategoryField();
		cn1.selectdrpdownSubCategory("All");
		cn1.txtPriceField("5");
		logger.info("Enter Need Price");
			
		cn1.btnSubmitNeedPage();
		logger.info("Create Need2");
		//Validate whether need2 is created successfully
		SoftAssert softassert = new SoftAssert();
		NeedDetailPage needdetpg1 = new NeedDetailPage(driver);
		if(needdetpg1.gettxtaddNeedcaption().contains("Need has been added"))
		{
			softassert.assertTrue(true);
			logger.info("Created Need2 Successfully");	
		}
		else
		{
			softassert.assertTrue(false);
			logger.error("Need2 NOT created");
			captureScreen(driver,"Need2NotCreated");
		}
	softassert.assertAll();
		//Refresh the page
		driver.navigate().refresh();
	}
	
	@Test(priority=7402)
	public void create2Services() throws IOException, InterruptedException
	{		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Create Service1
		CreateService cs = new CreateService(driver);
		cs.clkbtnCreateNew();
		cs.clkbtnService();
		logger.info("Creating Service1: " + serviceName1);
		
		cs.txtTitleField(serviceName1);
		logger.info("Enter Service Title");
		
		cs.txtdescriptionField(serviceName1 + " Description");
		logger.info("Enter Service Description");
		cs.clktxtCategoryField();
		cs.SelectdrpdownCategory("Home & Child");
	//	cs.clktxtsubCategoryField();
		cs.selectdrpdownSubCategory("All");
		
		cs.txtPriceField("5");
		logger.info("Enter Service Price");
		
		cs.SilderBarMaxLimit();
		cs.refundValidField("7");
		logger.info("Enter Service Refund details");
		
		cs.btnSubmitServicePage();
		logger.info("Create Service1");
		
		//Validate whether service1 is created successfully
				SoftAssert softassert = new SoftAssert();
				ServiceDetailPage servicedetpg = new ServiceDetailPage(driver);
				if(servicedetpg.gettxtaddSvccaption().contains("Service has been added"))
				{
					softassert.assertTrue(true);
					logger.info("Created Service1 Successfully");	
				}
				else
				{
					softassert.assertTrue(false);
					logger.error("Service NOT created");
					captureScreen(driver,"Service1NotCreated");
				}
		//Refresh the page
				driver.navigate().refresh();
	
		//Create Service2
		CreateService cs1 = new CreateService(driver);
		cs1 = new CreateService(driver);
		cs1.clkbtnCreateNew();
		cs1.clkbtnService();
		logger.info("Creating Service2: " + serviceName2);
		
		cs1.txtTitleField(serviceName2);
		logger.info("Enter Service Title");
		
		cs1.txtdescriptionField(serviceName2 + " Description");
		logger.info("Enter Service Description");
		cs1.clktxtCategoryField();
		cs1.SelectdrpdownCategory("DIY & Landscaping");
	//	cs1.clktxtsubCategoryField();
		cs1.selectdrpdownSubCategory("All");
			
		cs1.txtPriceField("5");
		logger.info("Enter Service Price");
		
		cs1.SilderBarMaxLimit();
		cs1.refundValidField("7");
		logger.info("Enter Service Refund details");
		
		cs1.btnSubmitServicePage();
		logger.info("Create Service2");
		//Validate whether service1 is created successfully
		ServiceDetailPage servicedetpg1 = new ServiceDetailPage(driver);
		if(servicedetpg1.gettxtaddSvccaption().contains("Service has been added"))
		{
			softassert.assertTrue(true);
			logger.info("Created Service2 Successfully");	
		}
		else
		{
			softassert.assertTrue(false);
			logger.error("Service2 NOT created");
			captureScreen(driver,"Service2NotCreated");
		}
		softassert.assertAll();
		//Refresh the page
		driver.navigate().refresh();
	}
	
	@Test(priority=7403)
	public void verifyNeedsTable() throws IOException
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Verify Needs Table on Profile Page
		HomePage homepg1 = new HomePage(driver);
		Actions act1 = new Actions(driver);
		act1.moveToElement(homepg1.actWelcome()).moveToElement(homepg1.actProfile()).click().build().perform();
		logger.info("Navigate to Profile page");
		ProfilePage pp = new ProfilePage(driver);
		boolean noOfNeedsDisplayed = pp.noOfActiveNeedsDisplayed();
		logger.info("Check if No. of Active Needs is displayed");
		
		if(noOfNeedsDisplayed==true)
		{
			softassert.assertTrue(true);
			logger.info("No Of Active Needs: " + pp.getNoOfActiveNeeds());
		}
		else
		{
			captureScreen(driver,"verifyNeedsTable");
			logger.error("Active Needs No. not displayed");
			softassert.assertTrue(false);
		}
		
		boolean need1Icons = pp.verifyNeedsTableIcons(needName1);
		logger.info("Check if Edit/Delete icons are displayed for Need1");
		
		if(need1Icons==true)
		{
			softassert.assertTrue(true);
			logger.info("Need1 displayed all the Edit/Delete icons");
		}
		else
		{
			captureScreen(driver,"verifyNeedsTable");
			logger.error("Need1 Edit/Delete icons not displayed");
			softassert.assertTrue(false);
		}
		
		boolean need2Icons = pp.verifyNeedsTableIcons(needName2);
		logger.info("Check if Edit/Delete icons are displayed for Need2");
		
		if(need2Icons==true)
		{
			softassert.assertTrue(true);
			logger.info("Need2 displayed all the Edit/Delete icons");
		}
		else
		{
			captureScreen(driver,"verifyNeedsTable");
			logger.error("Need2 Edit/Delete icons not displayed");
			softassert.assertTrue(false);
		}
		softassert.assertAll();
		//Refresh the page
		driver.navigate().refresh();
	}
	
	@Test(priority=7404)
	public void verifyServicesTable() throws IOException, InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Verify Services Table on Profile Page
		ProfilePage pp = new ProfilePage(driver);
		boolean noOfServicesDisplayed = pp.noOfActiveServicesDisplayed();
		logger.info("Check if No. of Active Services is displayed");
		
		if(noOfServicesDisplayed==true)
		{
			softassert.assertTrue(true);
			logger.info("No Of Active Services: " + pp.getNoOfActiveServices());
		}
		else
		{
			captureScreen(driver,"verifyServicesTable");
			logger.error("Active Services No. not displayed");
			softassert.assertTrue(false);
		}
		
		boolean service1Icons = pp.verifyServicesTableIcons(serviceName1);
		logger.info("Check if Icons are present for Service1");
		
		if(service1Icons==true)
		{
			softassert.assertTrue(true);
			logger.info("Service1 displayed all the Rating Stars/Eeds Amount/Edit/Delete icons");
		}
		else
		{
			captureScreen(driver,"verifyServicesTable");
			logger.error("Service1 Rating Stars/Eeds Amount/Edit/Delete icons not displayed");
			softassert.assertTrue(false);
		}
		
		boolean service2Icons = pp.verifyServicesTableIcons(serviceName2);
		logger.info("Check if Icons are present for Service2");
		
		if(service2Icons==true)
		{
			softassert.assertTrue(true);
			logger.info("Service2 displayed all the Rating Stars/Eeds Amount/Edit/Delete icons");
		}
		else
		{
			captureScreen(driver,"verifyServicesTable");
			logger.error("Service2 Rating Stars/Eeds Amount/Edit/Delete icons not displayed");
			softassert.assertTrue(false);
		}
		softassert.assertAll();
		//Refresh the page
				driver.navigate().refresh();
				
				// Logout from this user 
				logger.info("User logout");
				HomePage homepg4 = new HomePage(driver);
				Actions act4 = new Actions(driver);
				act4.moveToElement(homepg4.actWelcome()).moveToElement(homepg4.actSignout()).click().build().perform();
				
	}
	
	@Test(priority=7405)
	public void UserLogin_deleteNeedsServices() throws IOException, InterruptedException
	{
		//Login again to delete needs/services created during this testcase
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Providing Login Details");
		lp.setUsername(username3);
		lp.setPassword(password);
		lp.clickloginbtn1();
		logger.info("Login successful");
	
		//Delete Need2
		HomePage homepg = new HomePage(driver);
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		logger.info("Navigate to Profile page");
				
		ProfilePage pp = new ProfilePage(driver);
		logger.info("Delete the latest need i.e need2 first");
		
		pp.clkbtnDeleteNeed();
		pp.clkbtnIwantToDeleteNeed();
		NeedDetailPage needdetpg = new NeedDetailPage(driver);
		if(needdetpg.gettxtdelNeedcaption().equals("Need has been deleted"))
		{
			logger.info("Need2 deleted from the profile page");
			softassert.assertTrue(true);
		}
		else
		{
			captureScreen(driver,"deleteNeedsServices");
			logger.error("Need2 was not available on profile page");
			softassert.assertTrue(false, "Need2NOTPresent");
		}
		
		//Refresh the page
		driver.navigate().refresh();
		
		logger.info("Delete the latest need now available i.e need1");
		ProfilePage pp1 = new ProfilePage(driver);
		pp1.clkbtnDeleteNeed();
		pp1.clkbtnIwantToDeleteNeed();
		NeedDetailPage needdetpg1 = new NeedDetailPage(driver);
		if(needdetpg1.gettxtdelNeedcaption().equals("Need has been deleted"))
		{
			logger.info("Need1 deleted from the profile page");
			softassert.assertTrue(true);
		}
		else
		{
			captureScreen(driver,"deleteNeedsServices");
			logger.error("Need1 was not available on profile page");
			softassert.assertTrue(false, "Need1NOTPresent");
		}
		
		//Refresh the page
				driver.navigate().refresh();
				
				ProfilePage pp3 = new ProfilePage(driver);
				logger.info("Delete the latest service i.e Service2");
				pp3.clkbtnDeleteService();
				pp3.clkbtnIwantToDelete();
				ServiceDetailPage svcdetpg = new ServiceDetailPage(driver);
				if(svcdetpg.gettxtdelSvccaption().equals("Service has been deleted"))
				{
					logger.info("Service2 deleted from the profile page");
					softassert.assertTrue(true);
				}
				else
				{
					captureScreen(driver,"deleteNeedsServices");
					logger.error("Service2 was not available on profile page");
					softassert.assertTrue(false, "Service2NOTPresent");
				}
				//Refresh the page
				driver.navigate().refresh();

				ProfilePage pp4 = new ProfilePage(driver);
				logger.info("Delete the latest service now available i.e service1");
				pp4.clkbtnDeleteService();
				pp4.clkbtnIwantToDelete();
				ServiceDetailPage svcdetpg1 = new ServiceDetailPage(driver);
				if(svcdetpg1.gettxtdelSvccaption().equals("Service has been deleted"))
				{
					logger.info("Service1 deleted from the profile page");
					softassert.assertTrue(true);
				}
				else
				{
					captureScreen(driver,"deleteNeedsServices");
					logger.error("Service1 was not available on profile page");
					softassert.assertTrue(false, "Service1NOTPresent");
				}
	softassert.assertAll();
		//Refresh the page
		driver.navigate().refresh();
		// Logout from this user 
		logger.info("User logout");
		HomePage homepg4 = new HomePage(driver);
		Actions act4 = new Actions(driver);
		act4.moveToElement(homepg4.actWelcome()).moveToElement(homepg4.actSignout()).click().build().perform();
	
		logger.info("Completed TP_TC_074");
	}
}
