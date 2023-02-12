package com.testCases;
//Testcase Description: Testing the functionality of the application by displaying "Created and Updated" Date and Time 
/* Acceptance Criteria: "In Services Search Result the Application should display to the User  that ""Created/Up dated Time"" with AM/PM (EST) of that Services 
In Needs Search Result the Application should display to the User  that  ""Created/Up dated Time""  AM/PM (EST) of that Needs" */ 

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.CreateNeed;
import com.pageObjects.CreateService;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.PlugsPage;
import com.pageObjects.ProfilePage;
import com.utilities.XLUtils;

public class TP_TC_028 extends BaseClass {

	@Test(dataProvider = "ServicesData", priority = 2801)
	public void created_Updated_Services_TimeandDate(String user, String pwd, String Title, String Descr,
			String CatName, String SubcatName, String Price, String RfdValid) throws IOException, InterruptedException, ParseException {

		logger.info("Started TP_TC_028");
		LoginPage LoginPage = new LoginPage(driver);
		PlugsPage PlugsPage = new PlugsPage(driver);
		CreateService CreateService = new CreateService(driver);
		SoftAssert softAssert = new SoftAssert();

		// Initializing Excel path for TesctCasesData Excel File
		String path = System.getProperty("user.dir") + "/src/test/java/com/testData/TestcasesData.xlsx";

		// Clicking on the Home page Login button
		LoginPage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password");

		// Entering text in username field
		LoginPage.setUsername(user);
		logger.info("providing username");

		// Entering text in password field
		LoginPage.setPassword(pwd);
		logger.info("providing password");

		// Clicking on the login button
		LoginPage.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");

		// Validating the login - "Home Page" title after logging into Sun
		softAssert.assertTrue(driver.getTitle().equals("twoPLUGS - A plug for your Service and another for your Need"),
				"login is failed");
		logger.info("login success");

		softAssert.assertAll();

		// ===================Creating New Service============================

		// Clicking on the create New Service
		CreateService.clkbtnCreateNew();
		logger.info("Clicking on Create New icon");

		// Clicking on button Service
		CreateService.clkbtnService();
		logger.info("Clicking on the Service dropdown");

		// Inputting Data into the Title Field
		CreateService.txtTitleField(Title);
		logger.info("inputting data into Title field");

		// Entering data into Description field
		CreateService.txtdescriptionField(Descr);
		logger.info("inputting data into description field");

		// Clicking on the Category field
		CreateService.clktxtCategoryField();
		logger.info("Clicking on the category drop down arrow");

		// Selecting from the Category drop down
		CreateService.SelectdrpdownCategory(CatName);
		logger.info("Selecting from the category dropdown menu");

		// Clicking on the Sub Category field
	//	CreateService.clktxtsubCategoryField();
	//	logger.info("Clicking on the Sub Category drop down Menu");

		// Selecting from Sub Category Drop Down
		CreateService.SelectdrpdownCategory(CatName);
		logger.info("Selecting from the Sub Category drop down Menu");

		// Entering data into the Price field
		CreateService.txtPriceField(Price);
		logger.info("Entering value into the Price Field");

		// Sliding the Refund% arrow
		CreateService.SilderBarMaxLimit();
		logger.info("Sliding the Refund percentage arrow");

		// Entering data into the Refund Valid field
		CreateService.refundValidField(RfdValid);
		logger.info("Entering value into the Refund Valid text box");

		// Clicking on the create button to create service
		CreateService.btnSubmitServicePage();
		logger.info("Clicking on the Create button to create Service");

		// ========Searching for the Newly Created Service=========//

		// Clicking on the Plug icon
		logger.info("Clicking on the Plugs Icon");
		HomePage homepage = new HomePage(driver);
		homepage.clkbtnPlugs();

		// Clicking on Services
		PlugsPage.ClickServicesBtn();
		logger.info("Clicking on Services");

		// Getting data from TestcaseData Excel Sheet
		String value = XLUtils.getCellData(path, "Services", 1, 2);
		// Inputing search text into the search box
		PlugsPage.SetSearch(value);
		logger.info("inputing value into the search box");

		// Clicking the search button
		PlugsPage.ClickSearchBtn();
		logger.info("Clicking the search button");

		// ====== Verifying Application Displays Created/Updated Time and Date

		String dateTime = PlugsPage.UpdatedDateTime(); // Capturing date/time from the webpage
		logger.info("Date & Time available on webpage" + dateTime);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm a"); // formating it to simple date format
		sdf.setLenient(false);
		try {
			Date d = sdf.parse(dateTime);
			String new_datetime = sdf.format(d);
			logger.info("Formatted Date & Time: " + new_datetime);

			softAssert.assertTrue(dateTime.equalsIgnoreCase(new_datetime),
					"Services Created/Updated Invalid time and date format not displayed");
			logger.info("Services Created/Updated Valid time and date format displayed");

			
		} catch (ParseException pe)



		{
			// Refresh the page
			driver.navigate().refresh();

		}

		softAssert.assertAll();

		// Deleting the new service created
		Actions act = new Actions(driver);
		act.moveToElement(homepage.actWelcome()).moveToElement(homepage.actProfile()).click().build().perform();
		ProfilePage profilepage = new ProfilePage(driver);
		profilepage.clkbtnDeleteService();
		profilepage.clkbtnIwantToDelete();

		// Refresh the page
		driver.navigate().refresh();

		// Logout from the user
		act.moveToElement(homepage.actWelcome()).moveToElement(homepage.actSignout()).click().build().perform();
	}

	@Test(dataProvider = "NeedsData", priority = 2802)
	public void created_Updated_Needs_TimeandDate(String user, String pwd, String Title, String Descr, String Cat,
			String Subcat, String Price) throws IOException, InterruptedException, ParseException {

		LoginPage LoginPage = new LoginPage(driver);
		PlugsPage PlugsPage = new PlugsPage(driver);
		CreateNeed createneed = new CreateNeed(driver);
		SoftAssert softAssert = new SoftAssert();

		// Initialising Excel path for TesctCasesData Excel File
		String path = System.getProperty("user.dir") + "/src/test/java/com/testData/TestcasesData.xlsx";

		// Clicking on the Home page Login button
		LoginPage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password");

		// Entering text in username field
		LoginPage.setUsername(user);
		logger.info("providing username");

		// Entering text in password field
		LoginPage.setPassword(pwd);
		logger.info("providing password");

		// Clicking on the login button
		LoginPage.clickloginbtn1();
		logger.info("Clicking on login button to access TwoPlugs main home page");

		// Validating the login - "Home Page" title after logging into Sun
		softAssert.assertTrue(driver.getTitle().equals("twoPLUGS - A plug for your Service and another for your Need"),
				"login is failed");
		logger.info("login success");

		softAssert.assertAll();

		// ===================Creating New Need============================

		// Clicking on the create New Need
		createneed.clkbtnCreateNew();
		logger.info("Clicking on Create New icon");

		// Clicking on button Need
		createneed.clkbtnNeed();
		logger.info("Clicking on the Needs dropdown");

		// Inputting Data into the Title Field
		createneed.txtTitleField(Title);
		logger.info("inputting data into Title field");

		// Entering data into Description field
		createneed.txtdescriptionField(Descr);
		logger.info("inputting data into description field");

		// Clicking on the Category field
		createneed.clktxtCategoryField();
		logger.info("Clicking on the category drop down arrow");

		// Selecting from the Category drop down
		createneed.SelectdrpdownCategory(Cat);
		logger.info("Selecting from the category dropdown menu");

		// Clicking on the Sub Category drop down Menu
		
/*		try {
		createneed.clktxtsubCategoryField();
		}
		catch (StaleElementReferenceException e)
		{
			CreateNeed createneed1 = new CreateNeed(driver);
			createneed1.clktxtsubCategoryField();
		}
		logger.info("Clicking on the Sub Category drop down Menu");
*/
		// Selecting from Sub Category Drop Down
		createneed.selectdrpdownSubCategory(Subcat);
		logger.info("Selecting from the Sub Category drop down Menu");

		// Entering data into the Price field
		createneed.txtPriceField(Price);
		logger.info("Entering value into the Price Field");

		// Clicking on the create button to create Needs
		createneed.btnSubmitNeedPage();
		logger.info("Clicking on the Create button to create Needs");

		// ==============Searching for the Newly Created Needs
		// =============================//

		// Clicking on the Plug icon
		logger.info("Clicking on the Plugs Icon");
		HomePage homepage = new HomePage(driver);
		homepage.clkbtnPlugs();

		// Clicking on Needs icon
		PlugsPage.ClickNeedsBtn();
		logger.info("Clicking on the Needs Icon");

		// Getting data from TestcaseData Excel Sheet
		String value = XLUtils.getCellData(path, "Needs", 1, 2);
		// Inputing search text into the search box
		PlugsPage.SetSearch(value);
		logger.info("inputing value into the search box");

		// Clicking the search button
		PlugsPage.ClickSearchBtn();
		logger.info("Clicking the search button");

		// ====== Verifying Application Displays Created/Updated Time and Date

		String dateTime = PlugsPage.UpdatedDateTime(); // Capturing date/time from the webpage
		logger.info("Date & Time available on webpage" + dateTime);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm a"); // formating it to simple date format
		sdf.setLenient(false);
		try {
			Date d = sdf.parse(dateTime);
			String new_datetime = sdf.format(d);
			logger.info("Formatted Date & Time: " + new_datetime);

			softAssert.assertTrue(dateTime.equalsIgnoreCase(new_datetime),
					"Services Created/Updated Invalid time and date format not displayed");
			logger.info("Services Created/Updated Valid time and date format displayed");

			
		} catch (ParseException pe)

		{
			// Refresh the page
			driver.navigate().refresh();

		}

		softAssert.assertAll();

		// Deleting the new need created
		Actions act = new Actions(driver);
		act.moveToElement(homepage.actWelcome()).moveToElement(homepage.actProfile()).click().build().perform();
		ProfilePage profilepage = new ProfilePage(driver);
		profilepage.clkbtnDeleteNeed();
		profilepage.clkbtnIwantToDeleteNeed();

		// Refresh the page
		driver.navigate().refresh();

		// Logout from the user
		act.moveToElement(homepage.actWelcome()).moveToElement(homepage.actSignout()).click().build().perform();
		logger.info("Completed TP_TC_028");
	}

	@DataProvider(name = "ServicesData")
	public String[][] grabData() throws IOException, InterruptedException {

		// Data Provider will always return String type data Two dimensional String type
		// Array
		// Declaring Excel String path
		String path = System.getProperty("user.dir") + "/src/test/java/com/testData/TestcasesData.xlsx";

		// To Read data from Excel File
		// Reading number of rows
		int rownum = XLUtils.getRowCount(path, "Services");

		// Reading number of colums
		int colnum = XLUtils.getCellCount(path, "Services", 0);

		// rownum and colcount give the exact no of values in the Excel sheet
		// which is passed in logindata[][]
		// now the data size and array size both will be equal
		String servicedata[][] = new String[rownum][colnum];

		// Reading data and storing in a 2 dimensional array
		// Starting from 1 since index 0 is the header part
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) { // Since the colunm values start from index 0

				// Extract data from XL
				// Since the data starts from index 1 for rows and 0 for col in XL sheet
				// We need to store the same value in the array
				// so the value index value for row will be i-1 since the array will
				// store the data from and it will not be taking the header values of the XL
				// sheet
				// for col its same as, the col reads from index 0 and saves it in the array in
				// index 0
				servicedata[i - 1][j] = XLUtils.getCellData(path, "Services", i, j);
			}
		}
		return servicedata; // returning 2 dim arrary

	}

	@DataProvider(name = "NeedsData")
	public String[][] catchData() throws IOException, InterruptedException {

		// Data Provider will always return String type data Two dimensional String type
		// Array
		// Declaring Excel String path
		String path = System.getProperty("user.dir") + "/src/test/java/com/testData/TestcasesData.xlsx";

		// To Read data from Excel File
		// Reading number of rows
		int rownum = XLUtils.getRowCount(path, "Needs");

		// Reading number of colums
		int colnum = XLUtils.getCellCount(path, "Needs", 0);

		// rownum and colcount give the exact no of values in the Excel sheet
		// which is passed in logindata[][]
		// now the data size and array size both will be equal
		String needsdata[][] = new String[rownum][colnum];

		// Reading data and storing in a 2 dimensional array
		// Starting from 1 since index 0 is the header part
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) { // Since the colunm values start from index 0

				// Extract data from XL
				// Since the data starts from index 1 for rows and 0 for col in XL sheet
				// We need to store the same value in the array
				// so the value index value for row will be i-1 since the array will
				// store the data from and it will not be taking the header values of the XL
				// sheet
				// for col its same as, the col reads from index 0 and saves it in the array in
				// index 0
				needsdata[i - 1][j] = XLUtils.getCellData(path, "Needs", i, j);
			}
		}
		return needsdata; // returning 2 dim arrary

	}

}
