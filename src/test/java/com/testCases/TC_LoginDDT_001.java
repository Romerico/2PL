package com.testCases;

import java.io.IOException;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pageObjects.LoginPage;
import com.utilities.XLUtils;

public class TC_LoginDDT_001 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void loginTest(String user, String pwd) throws InterruptedException, IOException {
		
				// Login page 
				LoginPage LoginPage = new LoginPage(driver);
				
				
				LoginPage.clickloginlandingbtn();
				logger.info("Clicking on Login button to enter user name and password");
				
				Thread.sleep(2000);
				LoginPage.setUsername(user);
				logger.info("providing user name");
				
				logger.info("providing password");
				LoginPage.setPassword(pwd);
				
				LoginPage.clickloginbtn1();
				logger.info("Clicking on login button to access TwoPlugs main home page");
				Thread.sleep(5000);
			/*	
				// Validating the login - "Home Page" title
				// after logging into Sun
				Thread.sleep(3000);
				if(driver.getTitle().equals("twoPLUGS - A plug for your Service and another for your Need"))
				{
					Assert.assertTrue(true);
					logger.info("login success");
						
				}
				else
				{
				//	captureScreen(driver,"loginTest");
					// to capture screen on failure and here after driver
								// we use the name of the current test method i.e. loginTest
					logger.error("login is failed : Screen shot taken");
					Assert.assertTrue(false);
				}
				logger.info(" " + "Page Title  : " + driver.getTitle());
				logger.info("Vaidating title page - Home page, after login");
				*/
			} 
	
			
		

	
	// Data provider will always return String type of data
	// Two dimensional string type array
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		
		String path=System.getProperty("user.dir")+"/src/test/java/com/testData/LoginData.xlsx";
	
		//Read data 
		//no of rows  in the XL data sheet
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		// no of columns in the XL data sheet
		int colcount=XLUtils.getCellCount(path,"Sheet1",1); // At-least specify one row to count the no of cols
																	// present inside the row
		// Create a two dimensional string array
		// Should be the same size of that of the XL sheet
		String logindata[][]=new String[rownum][colcount]; // rownum and colcount give the exact no of values in the XL sheet
														// that is passed in logindata[][] // now the data size and array size both are equal
		// Read data and store it in a 2 dimensional array
		
		// Starting from 1 since index 0 is the header part
		for(int i=1;i<=rownum;i++)
		{
			// increment the columns
			for(int j=0;j<colcount;j++)// Since the col values start from index 0
			{
				// Extract data from XL
				// Since the data starts from index 1 for rows and 0 for col in XL sheet
				// We need to store the same value in the array
				// so the value index value for row will be i-1 since the array will 
				// store the data from and it will not be taking the header values of the XL sheet
				// for col its same as, the col reads from index 0 and saves it in the array in index 0
				
				// Get data from Xl and store in a 2 dim array
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);// i is row and j is col
				
			}
			
		}
		return logindata; // returning 2 dim arrary
	} 


}
