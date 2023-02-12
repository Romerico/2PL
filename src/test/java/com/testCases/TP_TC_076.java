package com.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateService;
import com.pageObjects.EditServicePage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.ProfilePage;
import com.pageObjects.ServiceDetailPage;
import com.pageObjects.ViewServicePage;

public class TP_TC_076 extends BaseClass {
	// Testcase Description: Testing the functionality of the application by
	// creating or editing the Service
	// Acceptance Criteria: The Application should display all Created/edited data
	// into "Service details page

	@Test(priority = 7601)
	public void Login() throws IOException, InterruptedException {
		logger.info("Started TP_TC_076");
		// Create Object of the Login Page
		LoginPage LoginPage = new LoginPage(driver);
		// Click on Login Button
		LoginPage.clickloginlandingbtn();
		logger.info("Click on LandingPage LoginButton ");
		// Enter UserName
		LoginPage.setUsername(username1);
		logger.info("Enter UserName");
		// Enter Password
		LoginPage.setPassword(password);
		logger.info("Enter Password");
		// Click on LoginButton
		LoginPage.clickloginbtn1();
		logger.info("Click on Login Button");
	}

	// Create a Service
	@Test(priority = 7602)
	public void CreateService() throws IOException, InterruptedException {
		// Create a service
		CreateService createsvc = new CreateService(driver);
		createsvc.clkbtnCreateNew();
		createsvc.clkbtnService();
		logger.info("Fill in Service form with test data");
		createsvc.txtTitleField("HomeMadePizza");
		createsvc.txtdescriptionField("Pizza is a savory dish of Italian origin");
		createsvc.clktxtCategoryField();
		createsvc.SelectdrpdownCategory("Food & Beverage");
		createsvc.selectdrpdownSubCategory("Bake");
		createsvc.txtPriceField("30");
		createsvc.SilderBarMaxLimit();
		createsvc.refundValidField("5");
		// Assigned the Path for Image 1
		String Path1 = System.getProperty("user.dir") + "/Images/4.jpg";
		createsvc.FileUpload1(Path1);
		logger.info("Display Image 1");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		// Assigned the Path for Image 2
		String Path2 = System.getProperty("user.dir") + "/Images/5.jpg";
		createsvc.FileUpload2(Path2);
		logger.info("Display Image 2");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		// Assigned the Path for Image 3
		String Path3 = System.getProperty("user.dir") + "/Images/6.jpg";
		createsvc.FileUpload3(Path3);
		logger.info("Display Image 3");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// Click on Submit button
		logger.info("Service form submitted");
		createsvc.btnSubmitServicePage();

		// Validate whether service is created successfully
		logger.info("Validation for the created service begins");
		SoftAssert softassert = new SoftAssert();
		ServiceDetailPage servicedetpg = new ServiceDetailPage(driver);
		if (servicedetpg.gettxtaddSvccaption().contains("Service has been added")) {
			softassert.assertTrue(true);
			logger.info("Created Service Successfully");
		} else {
			softassert.assertTrue(false);
			logger.error("Service NOT created");
			captureScreen(driver, "CreateService");
		}

		ViewServicePage ViewServicePage = new ViewServicePage(driver);
		// Validate the Title -Compare the Title with the text
		if (ViewServicePage.ServiceTitle.getText().equals("HOMEMADEPIZZA")) {
			softassert.assertTrue(true);
			logger.info("Service Title Added Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Create ServiceTitle  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		
		//Refresh the page
		driver.navigate().refresh();
		
		//Go to Profile and select the latest service created
		Actions act = new Actions(driver);
		HomePage homepg = new HomePage(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		
		//Click on the first service created
		ProfilePage profilepg = new ProfilePage(driver);
		profilepg.clktxtlatestsvc();
		
		// Validate the description Message -Compare the text of the description
		if (ViewServicePage.ServiceDescription.getText().equals("Pizza is a savory dish of Italian origin")) {
			softassert.assertTrue(true);
			logger.info("Service Description Added Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Service Description  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}

		// Validate the Category-Compare the text of the Category
		if (ViewServicePage.ServiceCategory.getText().contains("Food & Beverage")) {
			softassert.assertTrue(true);
			logger.info("Service Category Added Successfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Service Category  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}

		// Validate the ServiceSubCategory-Compare the text of the ServiceSubCategory
		if (ViewServicePage.ServiceSubCategory.getText().contains("Bake")) {
			softassert.assertTrue(true);
			logger.info("Service SubCategory Added Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Service SubCategory  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}

		// Validate the Price-Compare the text of the Price
		if (ViewServicePage.ServicePrice.getText().contains("30")) {
			softassert.assertTrue(true);
			logger.info("Service Price Added Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Service Price  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}

		// Validate the Refund-Compare the text of the Refund
		if (ViewServicePage.ServiceRefund.getText().contains("30")) {
			softassert.assertTrue(true);
			logger.info("Service Refund Added Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Service Refund  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		// Validate the RefundValid-Compare the text of the RefundValid
		if (ViewServicePage.ServiceRefundValid.getText().contains("5")) {
			softassert.assertTrue(true);
			logger.info("Service RefundValid Added Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Service RefundValid  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Validate the Image 1 Added or not
		if (ViewServicePage.Image1.isDisplayed()) {
			softassert.assertTrue(true);
			logger.info("Service Image1 Added Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Service Image1  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Validate the Image 2 Added or not
		if (ViewServicePage.Image2.isDisplayed()) {
			softassert.assertTrue(true);
			logger.info("Service Image2 Added Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Service Image2  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Validate the Image 3 Added or not
		if (ViewServicePage.Image3.isDisplayed()) {
			softassert.assertTrue(true);
			logger.info("Service Image3 Added Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "CreateService");
			logger.error("Service Image3  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		softassert.assertAll();

	}

	// Click on Edit Button
	@Test(priority = 7603)
	public void EditService() throws IOException, InterruptedException {
		ViewServicePage ViewServicePage = new ViewServicePage(driver);
		ViewServicePage.ClickonEditButton();
		logger.info("Click on Edit Button Sucessfully");

		// Create object of the EditService Page
		EditServicePage EditServicePage = new EditServicePage(driver);

		SoftAssert softassert = new SoftAssert();

		// Edit Value of the TitleField
		EditServicePage.EdittxtTitleField("Thin Crust Pizza");
		logger.info("Update Title Field");
		// Edit Description
		EditServicePage.EdittxtdescriptionField("Thin crust pizzas are slimmer in the center");
		logger.info("Update Description Field");
	
		// Edit Category Field
		EditServicePage.EditselectCategoryField();
		logger.info("Update Select Category");
	
		// EditCategory
		EditServicePage.SelectdrpdownCategory("Business");
		logger.info("Update Category");

		EditServicePage EditServicePage1 = new EditServicePage(driver);
		// Edit SubCategory
		EditServicePage1.selectdrpdownSubCategory("Marketing");
		logger.info("Update SubCategory");
	
		// Edit Price Field
		EditServicePage1.EdittxtPriceField("20");
		logger.info("Update Price Field");
	
		// Edit SliderBar
		EditServicePage1.EditSilderBarMaxLimit();
		logger.info("Update Slider bar");

		// Edit refundvalue
		EditServicePage1.EditrefundValidField("2");
		logger.info("Update Refund Value");

		// Assigned Image path to Path1
		String Path1 = System.getProperty("user.dir") + "/Images/7.jpg";
		EditServicePage1.EditFileUpload1(Path1);
		logger.info("Update Image Path1");

		// Assigned Image path to Path2
		String Path2 = System.getProperty("user.dir") + "/Images/8.jpg";
		EditServicePage1.EditFileUpload2(Path2);
		logger.info("Update Image Path2");

		// Assigned Image path to Path3
		String Path3 = System.getProperty("user.dir") + "/Images/9.jpg";
		EditServicePage1.EditFileUpload3(Path3);
		logger.info("Update Image Path3");

		// Click on Submit Button
		EditServicePage1.EditbtnSubmitServicePage();
		logger.info("Click on Submit button to Save Data");
		logger.info("Update Data Sucessfully");

		ViewServicePage ViewServicePage1 = new ViewServicePage(driver);
		// Validate the Title -Compare the Title with the text
		if (ViewServicePage1.ServiceTitle.getText().equals("THIN CRUST PIZZA")) {
			softassert.assertTrue(true);
			logger.info("Service Title Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Update ServieTitle  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		// Validate the description Message -Compare the text of the description
		if (ViewServicePage1.ServiceDescription.getText().equals("Thin crust pizzas are slimmer in the center")) {
			softassert.assertTrue(true);
			logger.info("Service Description Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Service Description update Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}

		// Validate the Category-Compare the text of the Category
		if (ViewServicePage1.ServiceCategory.getText().equals("Business")) {
			softassert.assertTrue(true);
			logger.info("Service Category Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Service Category update  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}

		// Validate the ServiceSubCategory-Compare the text of the ServiceSubCategory
		if (ViewServicePage1.ServiceSubCategory.getText().equals("Marketing")) {
			softassert.assertTrue(true);
			logger.info("Service SubCategory Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Service SubCategory Update  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}

		// Validate the Price-Compare the text of the Price
		if (ViewServicePage1.ServicePrice.getText().equals("20 (EEDS)")) {
			softassert.assertTrue(true);
			logger.info("Service Price Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Service Price Update  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}

		// Validate the Refund-Compare the text of the Refund
		if (ViewServicePage1.ServiceRefund.getText().contains("12")) {
			softassert.assertTrue(true);
			logger.info("Service Refund Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Service Refund update Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		// Validate the RefundValid-Compare the text of the RefundValid
		if (ViewServicePage1.ServiceRefundValid.getText().contains("2 days")) {
			softassert.assertTrue(true);
			logger.info("Service RefundValid Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Service RefundValid Update Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Validate the Image 1 Added or not
		if (ViewServicePage1.Image1.isDisplayed()) {
			softassert.assertTrue(true);
			logger.info("Service Image1 Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Service Image1 update Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Validate the Image 2 Added or not
		if (ViewServicePage1.Image2.isDisplayed()) {
			softassert.assertTrue(true);
			logger.info("Service Image2 Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Service Image2 update  Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Validate the Image 3 Added or not
		if (ViewServicePage1.Image3.isDisplayed()) {
			softassert.assertTrue(true);
			logger.info("Service Image3 Update Sucessfully");

		} else {
			// Capture screen on failure of the test case
			captureScreen(driver, "EditService");
			logger.error("Service Image3 update Failed  : Screen shot taken");
			softassert.assertTrue(false);
		}
		softassert.assertAll();
	}

	@Test(priority = 7604)
	public void DeleteSvc() throws IOException, InterruptedException {
		HomePage homepg = new HomePage(driver);
		// Refresh the page
		driver.navigate().refresh();
		// Go to profile page and delete the service (created during this testcase)
		Actions act = new Actions(driver);
		act.moveToElement(homepg.actWelcome()).moveToElement(homepg.actProfile()).click().build().perform();
		ProfilePage profilepg = new ProfilePage(driver);
		profilepg.clkbtnDeleteService();
		profilepg.clkbtnIwantToDelete();
		ServiceDetailPage svcdetpg = new ServiceDetailPage(driver);
		SoftAssert softassert = new SoftAssert();
		if (svcdetpg.gettxtdelSvccaption().equals("Service has been deleted")) {
			softassert.assertTrue(true);
			logger.info("Service has been deleted");
		} else {
			softassert.assertTrue(false);
			logger.info("Service NOT deleted");
			captureScreen(driver, "ServiceNotDeleted");
		}

		softassert.assertAll();
		logger.info("Completed TP_TC_076");

	}

}
