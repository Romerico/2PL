package com.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.CreateService;
import com.pageObjects.LoginPage;

//Testcase Description: Testing the functionality by click on Select a Category  Button (For both Services and Need)
//Acceptance Criteria: The Application should display  " Select a Category " in Category box, and it should be clickable by drop down menu of all categories
 

public class TP_TC_078 extends BaseClass {
	
	@Test(priority=7801)
	public void Login() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_078");
		//Create Object of the Login Page
		LoginPage LoginPage = new LoginPage(driver);
		
		LoginPage.clickloginlandingbtn();
		logger.info("Click on LandingPage LoginButton ");
		//Enter UserName
		LoginPage.setUsername(username1);
		logger.info("Enter UserName");
		
		//Enter Password
		LoginPage.setPassword(password);
		logger.info("Enter Password");
		
		//Click on LoginButton
		LoginPage.clickloginbtn1();
		logger.info("Click on Login Button");
		
	}
	//Create a CreateNewButton
	@Test(priority=7802)
	public void ClickonCreateNewButton() throws IOException, InterruptedException
	{
		CreateService CreateService = new CreateService(driver);
		CreateService.clkbtnCreateNew();
		CreateService.clkbtnService();
		
	
	}
	//Click on Service Dropdown of Category
	@Test(priority=7803)
	public void ClickOnServiceDropdown() throws IOException, InterruptedException
	{
		SoftAssert softassert = new SoftAssert();

		//Create object of the CreateService Page
		CreateService CreateService = new CreateService(driver);
		 Select select = new Select(CreateService.optionlist);
		
		 List<WebElement> options = select.getOptions();
		 
		 List<String> all_elements_text=new ArrayList<String>();
		int count=0;
		 for(int l = 0; l < options.size(); l++)
			{
				all_elements_text.add(options.get(l).getText());
				//Click on the option
				options.get(l).click();
				//Verify Click on the all Categories 
				if(options.get(l).getText().equals(all_elements_text.get(l)))
				{
					
					softassert.assertTrue(true);
					logger.info("Click on  " + options.get(l).getText() + " Option Sucessfully");
					
					count++;
				}
				else
				{
					//Capture screen on failure of the test case
					captureScreen(driver,"ClickOnServiceDropdown");
					logger.error("Category list option not clicked successfully");
					softassert.assertTrue(false);
				}
				
			   
			}
		 
		 if (count == options.size()) {
			 
			 softassert.assertTrue(true);
			 logger.info("Test Passed: Click on all Service Dropdown option Sucessfully");
			 
		 }
		 else
		 {
		 
			 //Capture screen on failure of the test case
				captureScreen(driver,"ClickOnServiceDropdown");
				logger.error("Test Failed: Click on all Service Dropdown option not Successful");
				softassert.assertTrue(false);
		 
		 }
		softassert.assertAll(); 
		
	}
	//Click on the Need button
	@Test(priority=7804)
	public void Clickonneed() throws IOException, InterruptedException
	{
		CreateNeed createneed = new CreateNeed(driver);
		createneed.clkbtnCreateNew();
		createneed.clkbtnNeed();
		logger.info(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

	}
	
	//Click on the Dropdown of the Category
	@Test(priority=7805)
	public void ClickOnNeedDropdown() throws IOException, InterruptedException
	{
		SoftAssert softassert = new SoftAssert();
		
		CreateService CreateService = new CreateService(driver);
		
		
		 Select select = new Select(CreateService.optionlist);
		
		 List<WebElement> options = select.getOptions();
		 
		 List<String> all_elements_text=new ArrayList<String>();
		int count=0;
		 for(int l = 0; l < options.size(); l++)
			{
				all_elements_text.add(options.get(l).getText());
				//ele.click();
				options.get(l).click();
				//Vrify all Dropdown Category Selected
				if(options.get(l).getText().equals(all_elements_text.get(l)))
				{
					
					softassert.assertTrue(true);
					logger.info("Click on  " + options.get(l).getText() + " Option Sucessfully");
					
					count++;
				}
				else
				{
					//Capture screen on failure of the test case
					captureScreen(driver,"ClickOnNeedDropdown");
					logger.error("Test Failed: Category list option not clicked!");
					softassert.assertTrue(false);
				}
				
			   
			}
		 
		 if (count == options.size()) {
			 
			 softassert.assertTrue(true);
			 logger.info("Test Passed: Click on all Need Dropdown option Sucessfully");
			 
		 }
		 else
		 {
		 
			 //Capture screen on failure of the test case
				captureScreen(driver,"ClickOnNeedDropdown");
				logger.error("Test Failed: Click on Category list on need page failed");
				softassert.assertTrue(false);
		 
		 }
		 
		softassert.assertAll();
		logger.info("Completed TP_TC_078");
	}

}
