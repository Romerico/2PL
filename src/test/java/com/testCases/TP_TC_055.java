package com.testCases;

	import java.io.IOException;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.time.Duration;
	import java.util.Date;
	import java.util.concurrent.TimeUnit;

	import org.testng.annotations.Test;
	import org.testng.asserts.SoftAssert;

	import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
	import com.pageObjects.TransHistoryPage;
	
	//Testcase Description: Testing the functionality lapsed Refund valid  days
	//Acceptance Criteria: The Application should not display  "Refund" icon on Transactions History page after valid refund days   


	public class TP_TC_055 extends BaseClass {

		@Test(priority = 5501)
		public void LoginTest() throws InterruptedException {

			logger.info("Started TP_TC_055");
			driver.get(baseURL);
			logger.info("Opened URL");
			LoginPage lp = new LoginPage(driver);
			lp.clickloginlandingbtn();
			logger.info("Providing Login Details");
			lp.setUsername(username2);
			lp.setPassword(password);
			lp.clickloginbtn1();
			logger.info("Successfully logged in!");
		}

		@Test(priority = 5502)
		public void RefundInvalid() throws ParseException, IOException {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));// implicit wait
			TransHistoryPage transHistoryPage = new TransHistoryPage(driver);// Creating object for trans History page
			logger.info("Clicking on transactions tab");
			HomePage homepg = new HomePage(driver);
			homepg.clkbtnTransHist();
			
			transHistoryPage.clktxtservicename();
			logger.info("clicking on service name in first row and column");
			String rv = transHistoryPage.refundDays.getAttribute("innerText");
			logger.info("Capturing refund valid days");
			logger.info("Refund Valid days: "+rv);
			String refundDays = rv.substring(0, 1);
			logger.info("Removing 'days' word from captured text");
			int refundDays1 = Integer.parseInt(refundDays);
			driver.navigate().back();
			logger.info("Navigating back to transactions page");
			int count = transHistoryPage.countActions();
			logger.info("counting icons under actions column in first row");
			logger.info(count);

			// Create object of SimpleDateFormat class and decide the format
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		      SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

			// get current date time with Date()
			Date date = new Date();
			

			// Now format the date
			String currentdate = dateFormat.format(date);
			logger.info("capturing current date");

			// Print the Date
			logger.info("currentdate" + currentdate);

			// Date of transaction
			String transDate = transHistoryPage.dateofTrans.getText();
			logger.info("transaction date: "+transDate);
			logger.info("Capturing transaction date and time ");
			String transTime=transDate.substring(0,4);
			
			logger.info("separating time form date");
			String transDate1= transDate.substring(7, 17);
			
			String transA= transDate.substring(4,6);
			logger.info("separating AM/PM from time");
			String transDateTime= transDate1+" "+transTime+" "+transA;
			Date d=parseFormat.parse(transDateTime);
			logger.info("parsing time and date to parse format object");
			String transDT= parseFormat.format(d);
			logger.info("formatting date and time to particular format");

			
		// Validating if refund icon and complaint icon is present or not, if count=2, both icons are present

			if (count == 2) 
		
					logger.info("*******Refund Icon and File complain options should present***********");
		
			
			else if (count == 1) 
				
					logger.info("********* only file complaint should be present************");

			
			else if (count == 0) 
				logger.info("**********Refund Icon and File complain options should not be there***********");
			

			


	// creating and initializing date objects
			
			Date d2 = null;
			Date d1=null;
			int diffDays = 0;
			// Using try and catch block to handle Stack trace exception

			try {
				d1 = dateFormat.parse(transDT);// formatting date
				d2 = dateFormat.parse(currentdate);

				// time in milliseconds
				long diff = d2.getTime() - d1.getTime();
				long l = diff / (24 * 60 * 60 * 1000);// converting time in days
				diffDays = (int) l;

				System.out.print(diffDays + " days, lapsed");
				logger.info("Calculating Lapse days after transaction");

			} catch (Exception e) {
				e.printStackTrace();
			}

		
			//********Validating if refund is still valid or not
			
			SoftAssert softAssert = new SoftAssert();// creating object for soft assertion

			if ((diffDays <= refundDays1) &&transHistoryPage.refund.isDisplayed()){
				
					softAssert.assertTrue(true);
					logger.info("******Refund is valid***************");
			}
					

			else  {
				softAssert.assertTrue(false);
			
				logger.info("**********Refund is invalid****************");
				captureScreen(driver, "refundInvalidTest");
			}
			
//			softAssert.assertAll();

		}

	}

	
	

