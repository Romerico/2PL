package com.testCases;

import java.io.IOException;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateService;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.ProfilePage;
import com.pageObjects.ServiceDetailPage;

//Testcase Description: Checks whether a user can post a service with zero price
//Acceptance Criteria: "This testcase to be successful
//1:User can post a service with zero price"

public class TP_TC_095 extends BaseClass{
		@Test(priority = 9501)
		public void SellerLogin_CreateServiceWithZeroPrice() throws InterruptedException, IOException {
		logger.info("Started TP_TC_095");
		LoginPage lp = new LoginPage(driver);
		lp.clickloginlandingbtn();
		logger.info("Login as seller");
		lp.setUsername(username1);              
		logger.info("providing user name");
		lp.setPassword(password);				
		logger.info("providing password");
		lp.clickloginbtn1();
		
		//Create new service page
		logger.info("Create new service");
		CreateService home=new CreateService(driver);
		home.clkbtnCreateNew();
		logger.info("Click on Create new button");
		home.clkbtnService();
		logger.info("Click on Service option");
		
		CreateService createsvc=new CreateService(driver);
		logger.info("Fill in Service form with test data");
		createsvc.txtTitleField("Jazy Fashions");  //If changing testdata here, change it in all places in test case
		createsvc.txtdescriptionField("One stop Fashion boutique");
		createsvc.clktxtCategoryField();
		createsvc.SelectdrpdownCategory("Beauty & Fashion");
	//	createsvc.clktxtsubCategoryField();
		createsvc.selectdrpdownSubCategory("Clothing");
		createsvc.txtPriceField("0");
		createsvc.btnSubmitServicePage();
		logger.info("Created Service Successfully");

		//Verify that service has been added using alert message
		ServiceDetailPage servicedetpg = new ServiceDetailPage(driver);
		String captionaddsvctext = servicedetpg.gettxtaddSvccaption();
		logger.info(captionaddsvctext);
		SoftAssert softAssertion= new SoftAssert();

		if(captionaddsvctext.equals("Service has been added"))
		{
		logger.info("Test Passed: User can post a service with zero price!");
    	softAssertion.assertTrue(true);
    	logger.info("Service Added successfully with Zero Price!");
		}
		else
		{
		logger.error("Test Failed: User cannot post a service with zero price!");
		captureScreen(driver,"ZerPriceServiceBuy");
		softAssertion.assertTrue(false);
		logger.info("Service NOT Added with Zero Price!");
		}
		
		softAssertion.assertAll();
		}
		
		//Delete the service that is created
		@Test(priority=9502)
		public void DeleteService()
		{
			//Refresh the page
			driver.navigate().refresh();
			
			//Go to profile page and delete the service (created during this testcase)
			HomePage homepg = new HomePage(driver);
			Actions act = new Actions(driver);
			act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
			
			ProfilePage profilepg = new ProfilePage(driver);
			profilepg.clkbtnDeleteService();
			profilepg.clkbtnIwantToDelete();
			
			ServiceDetailPage servicedetpg = new ServiceDetailPage(driver);
			String captiondelsvctext = servicedetpg.gettxtdelSvccaption();
			logger.info(captiondelsvctext);
			SoftAssert softassertion = new SoftAssert();
			if(captiondelsvctext.contains("deleted"))
			{			
			logger.info("Service has been deleted");
			softassertion.assertTrue(true);
			logger.info("Service deleted successfully");
			}
			else
			{
				softassertion.assertTrue(false);
				logger.error("Service has not been deleted");
				logger.info("Service NOT deleted");
			}
			
			//Refresh the page
			driver.navigate().refresh();
			logger.info("Completed TP_TC_095");
			
		}
		
}