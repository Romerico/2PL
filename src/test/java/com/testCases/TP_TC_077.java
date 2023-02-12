package com.testCases;
//Testcase Description: Testing the functionality of the application by creating or editing the Need
//Acceptance Criteria: The Application should display all Created/edited data into  "Need details page"

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.EditNeedPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.NeedDetailPage;
import com.pageObjects.ProfilePage;
import com.pageObjects.ViewNeedPage;

public class TP_TC_077 extends BaseClass{
	
	@Test(priority=7701)
	public void Login() throws IOException, InterruptedException
	{
		logger.info("Started TP_TC_077");
		//Create Object of the Login Page
		LoginPage LoginPage = new LoginPage(driver);
		//Click on Login Button
		LoginPage.clickloginlandingbtn();
		logger.info("Click on LandingPage LoginButton ");
		//Enter UserName
		LoginPage.setUsername(username2);
		logger.info("Enter UserName");
		
		//Enter Password
		LoginPage.setPassword(password);
		logger.info("Enter Password");
		
		//Click on LoginButton
		LoginPage.clickloginbtn1();
		logger.info("Click on Login Button");
		
	}
	
	//Create a New Button
	@Test(priority=7702)
	public void ClickonCreateNewButton() throws IOException, InterruptedException
	{
		logger.info("Creating a new Need");
		CreateNeed createneed = new CreateNeed(driver);
		createneed.clkbtnCreateNew();
		createneed.clkbtnNeed();
		logger.info("Fill in Need form with test data");
		createneed.txtTitleField("MAC Make Up");
		createneed.txtdescriptionField("MAC Cosmetics, stylized as M·A·C, is a cosmetics manufacture");
		createneed.clktxtCategoryField();
		createneed.SelectdrpdownCategory("Beauty & Fashion");
		createneed.selectdrpdownSubCategory("Cosmetics & Make Up");
		createneed.txtPriceField("40");
		
		//Assigned the Path for Image 1
				String Path1=System.getProperty("user.dir")+"/Images/10.jpg";
				
				createneed.NeedFileUpload1(Path1);
				logger.info("Display Image 1");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				//Assigned the Path for Image 2
				String Path2=System.getProperty("user.dir")+"/Images/11.jpg";
				createneed.NeedFileUpload2(Path2);
				logger.info("Display Image 2");
				driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
				//Assigned the Path for Image 3
				String Path3=System.getProperty("user.dir")+"/Images/12.jpg";
				createneed.NeedFileUpload3(Path3);
				logger.info("Display Image 3");
				driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
				
		createneed.btnSubmitNeedPage();
		logger.info("Submitted Need form successfully");
		
		// Validate whether need is created successfully
				logger.info("Validation for the created need begins");
				SoftAssert softassert = new SoftAssert();
				NeedDetailPage needdetpg = new NeedDetailPage(driver);
				if (needdetpg.gettxtaddNeedcaption().contains("Need has been added")) {
					softassert.assertTrue(true);
					logger.info("Created Need Successfully");
				} else {
					softassert.assertTrue(false);
					logger.error("Need NOT created");
					captureScreen(driver, "CreateNeed");
				}

				ViewNeedPage ViewNeedPage = new ViewNeedPage(driver);
				//Validate the Title -Compare the Title with the text
				if(ViewNeedPage.NeedTitle.getText().equals("MAC MAKE UP"))
				{
					softassert.assertTrue(true);
					logger.info("Need Title Added Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"CreateNeed");
					logger.error("Create NeedTitle  Failed  : Screen shot taken");
					softassert.assertTrue(false);
				}
				
				//Refresh the page
				driver.navigate().refresh();
				
				//Go to Profile and select the latest need created
				Actions act = new Actions(driver);
				HomePage homepg = new HomePage(driver);
				act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
				
				//Click on the first need created
				ProfilePage profilepg = new ProfilePage(driver);
				profilepg.clktxtlatestneed();
				
				//Validate the description Message -Compare the text of the description
				if(ViewNeedPage.NeedDescription.getText().equals("MAC Cosmetics, stylized as M·A·C, is a cosmetics manufacture"))
				{
					softassert.assertTrue(true);
					logger.info("Need Description Added Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"CreateNeed");
					logger.error("Need Description  Failed  : Screen shot taken");
					softassert.assertTrue(false);
				}
				
				
					//Validate the Category-Compare the text of the Category
						if(ViewNeedPage.NeedCategory.getText().equals("Beauty & Fashion"))
						{
							softassert.assertTrue(true);
							logger.info("Need Category Added Sucessfully");
						}
						else
						{ 
							//Capture screen on failure of the test case
							captureScreen(driver,"CreateNeed");
							logger.error("Need Category  Failed  : Screen shot taken");
							softassert.assertTrue(false);
						}
						
						//Validate the SubCategory-Compare the text of the ServiceSubCategory
						if(ViewNeedPage.NeedSubCategory.getText().equals("Cosmetics & Make Up"))
						{
							softassert.assertTrue(true);
							logger.info("Need SubCategory Added Sucessfully");
							
						}
						else
						{ 
							//Capture screen on failure of the test case
							captureScreen(driver,"CreateNeed");
							logger.error("Need SubCategory  Failed  : Screen shot taken");
							softassert.assertTrue(false);
						}
						
						//Validate the Price-Compare the text of the Price
						if(ViewNeedPage.NeedPrice.getText().equals("40 (EEDS)"))
						{
							softassert.assertTrue(true);
							logger.info("Need Price Added Sucessfully");
							
						}
						else
						{ 
							//Capture screen on failure of the test case
							captureScreen(driver,"CreateNeed");
							logger.error("Need Price  Failed  : Screen shot taken");
							softassert.assertTrue(false);
						}

						
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
						//Validate the Image 1 Added or not
						if(ViewNeedPage.Image1.isDisplayed())
						{
							softassert.assertTrue(true);
							logger.info("Need Image1 Added Sucessfully");
							
						}
						else
						{ 
							//Capture screen on failure of the test case
							captureScreen(driver,"CreateNeed");
							logger.error("Need Image1  Failed  : Screen shot taken");
							softassert.assertTrue(false);
						}
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
						//Validate the Image 2 Added or not
						if(ViewNeedPage.Image2.isDisplayed())
						{
							softassert.assertTrue(true);
							logger.info("Need Image2 Added Sucessfully");
							
						}
						else
						{ 
							//Capture screen on failure of the test case
							captureScreen(driver,"CreateNeed");
							logger.error("Need Image2  Failed  : Screen shot taken");
							softassert.assertTrue(false);
						}
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
						//Validate the Image 3 Added or not
						if(ViewNeedPage.Image3.isDisplayed())
						{
							softassert.assertTrue(true);
							logger.info("Need Image3 Added Sucessfully");
							
						}
						else
						{ 
							//Capture screen on failure of the test case
							captureScreen(driver,"CreateNeed");
							logger.error("Need Image3  Failed  : Screen shot taken");
							softassert.assertTrue(false);
						}
					softassert.assertAll();	

	}
	
	//Click on Edit Button
	@Test(priority=7703)
	public void EditNeed() throws IOException, InterruptedException
	{
		ViewNeedPage ViewNeedPage = new ViewNeedPage(driver);
		ViewNeedPage.ClickonEditButton();
		logger.info("Click on Edit Button Sucessfully");
	
		//Create Object of the Create Service
		EditNeedPage EditNeedPage = new EditNeedPage(driver);
		SoftAssert softassert = new SoftAssert();
		
		//Edit Value of the TitleField
		EditNeedPage.EdittxtTitleField("Make up");
		logger.info("Update Title Field");
		//Edit Description
		EditNeedPage.EdittxtdescriptionField("Testing");
		logger.info("Update Description Field");
				
		//Edit Category Field
		EditNeedPage.EditselectCategoryField();
		logger.info("Update Select Category");
		
		//EditCategory
		EditNeedPage.SelectdrpdownCategory("Business");
		logger.info("Update Category");
		
		//Edit SubCategory
		EditNeedPage.selectdrpdownSubCategory("Marketing");
		logger.info("Update SubCategory");
		
		//Edit Price Field
		EditNeedPage.EdittxtPriceField("50");
		logger.info("Update Price Field");
		
		//Assigned Image path to Path1
		String Path1=System.getProperty("user.dir")+"/Images/13.jpg";
		EditNeedPage.EditFileUpload1(Path1);
		logger.info("Update Image Path1");
		
		//Assigned Image path to Path2
		String Path2=System.getProperty("user.dir")+"/Images/14.jpg";
		EditNeedPage.EditFileUpload2(Path2);
		logger.info("Update Image Path2");
		
		//Assigned Image path to Path3
		String Path3=System.getProperty("user.dir")+"/Images/15.jpg";
		EditNeedPage.EditFileUpload3(Path3);
		logger.info("Update Image Path3");
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		//Click on Submit Button
		EditNeedPage.EditbtnSubmitServicePage();
		logger.info("Click on Submit button to Save Data");
		logger.info("Update Data Sucessfully");
		
		//Validate the Title -Compare the Title with the text
		if(ViewNeedPage.NeedTitle.getText().equals("MAKE UP"))
		{
			softassert.assertTrue(true);
			logger.info("Need Title Update Sucessfully");
			
		}
		else
		{ 
			//Capture screen on failure of the test case
			captureScreen(driver,"EditNeed");
			logger.error("Update ServieTitle  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		//Validate the description Message -Compare the text of the description
		if(ViewNeedPage.NeedDescription.getText().equals("Testing"))
		{
			softassert.assertTrue(true);
			logger.info("Need Description Update Sucessfully");
			
		}
		else
		{ 
			//Capture screen on failure of the test case
			captureScreen(driver,"EditNeed");
			logger.error("Need Description update Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		
		
			//Validate the Category-Compare the text of the Category
				if(ViewNeedPage.NeedCategory.getText().equals("Business"))
				{
					softassert.assertTrue(true);
					logger.info("Need Category Update Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"EditNeed");
					logger.error("Need Category update  Failed  : Screen shot taken");
					softassert.assertTrue(false);
				}
				
				//Validate the SubCategory-Compare the text of the ServiceSubCategory
				if(ViewNeedPage.NeedSubCategory.getText().equals("Marketing"))
				{
					softassert.assertTrue(true);
					logger.info("Need SubCategory Update Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"EditNeed");
					logger.error("Need SubCategory Update  Failed  : Screen shot taken");
					softassert.assertTrue(false);
				}
				
				//Validate the Price-Compare the text of the Price
				if(ViewNeedPage.NeedPrice.getText().contains("50"))
				{
					softassert.assertTrue(true);
					logger.info("Need Price Update Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"EditNeed");
					logger.error("Need Price Update  Failed  : Screen shot taken");
					softassert.assertTrue(false);
				}

				
				//Validate the Image 1 Added or not
				if(ViewNeedPage.Image1.isDisplayed())
				{
					softassert.assertTrue(true);
					logger.info("Need Image1 Update Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"EditNeed");
					logger.error("Need Image1 update Failed  : Screen shot taken");
					softassert.assertTrue(false);
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
				//Validate the Image 2 Added or not
				if(ViewNeedPage.Image2.isDisplayed())
				{
					softassert.assertTrue(true);
					logger.info("Need Image2 Update Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"EditNeed");
					logger.error("Need Image2 update  Failed  : Screen shot taken");
					softassert.assertTrue(false);
				}
				
				//Validate the Image 3 Added or not
				if(ViewNeedPage.Image3.isDisplayed())
				{
					softassert.assertTrue(true);
					logger.info("Need Image3 Update Sucessfully");
					
				}
				else
				{ 
					//Capture screen on failure of the test case
					captureScreen(driver,"EditNeed");
					logger.error("Need Image3 update Failed  : Screen shot taken");
					softassert.assertTrue(false);
				}
				
				softassert.assertAll();
				
	}	
				
				@Test(priority = 7704)
				public void DeleteNeed() throws IOException, InterruptedException {
					HomePage homepg = new HomePage(driver);
					// Refresh the page
					driver.navigate().refresh();
					// Go to profile page and delete the need (created during this testcase)
					Actions act = new Actions(driver);
					act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
					ProfilePage profilepg = new ProfilePage(driver);
					profilepg.clkbtnDeleteNeed();
					profilepg.clkbtnIwantToDeleteNeed();
					NeedDetailPage svcdetpg = new NeedDetailPage(driver);
					SoftAssert softassert = new SoftAssert();
					if (svcdetpg.gettxtdelNeedcaption().equals("Need has been deleted")) {
						softassert.assertTrue(true);
						logger.info("Need has been deleted");
					} else {
						softassert.assertTrue(false);
						logger.info("Need NOT deleted");
						captureScreen(driver, "DeleteNeed");
					}

					softassert.assertAll();
					logger.info("Completed TP_TC_077");

				}
	
	}
	
	