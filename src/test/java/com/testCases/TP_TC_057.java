package com.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.TransHistoryPage;

/*Testcase Description: Test the functionality of displaying tooltips by moving mouse on 'Transaction, 
 *  Ask for a Refund, and File a Complaint' icons */

/* Acceptance Criteria: By moving mouse on each icon of Transaction, Ask for a Refund and File a Complaint, 
 * then  the tooltip box should be open and it has to give detailed functioning information about that word/function  */
 

public class TP_TC_057 extends BaseClass {
	@Test(priority=5701)
	  public void ValidateTransactionsLegend () throws InterruptedException, IOException {
		SoftAssert soft= new SoftAssert();
//Create object at LoginPage class
		LoginPage login = new LoginPage(driver);
		
//Login on TwoPlugs website		
			logger.info("Login");
	  		login.clickloginlandingbtn();
	  		login.setUsername(username2);
	  		login.setPassword(password);
	  		login.clickloginbtn1();
	  			  		
//Create object at TransHistoryPage	  		
	  	TransHistoryPage trans = new TransHistoryPage(driver);
//Click Transactions Link
	  	HomePage homepg = new HomePage(driver);
	  	homepg.clkbtnTransHist();
//Click transaction legend
	  		logger.info("Checking transaction legend");
	  		trans.clkTransactionsLegend();
//Check if transaction legend is displayed
	  		if (trans.isTransactionsLegendDisplayed()==true)
	  			{
	  			soft.assertTrue(true);
	  			logger.info("Transactions Legend is displayed");
	  		}

	  		else {
	  			captureScreen(driver, "ValidateTransactionsLegend");
	  			logger.error ("Transactions Legend is not displayed");
	  			soft.assertTrue(false);
	  			}
	  		
//Click ask for a refund legend
	  		trans=new TransHistoryPage(driver);
	  		logger.info("Checking ask for a refund legend");
	  		trans.clkAskforaRefundLegend();
	  		
//Check if refund legend is displayed
	  		if (trans.isAskforaRefundLegendDisplayed()==true)
	  			{
	  			soft.assertTrue(true);
	  			logger.info("Refund Legend is displayed");
	  		}

	  		else {
	  			captureScreen(driver, "ValidateTransactionsLegend");
	  			logger.error ("Refund Legend is not displayed");
	  			soft.assertTrue(false);
	  			}

//Click file a complaint legend
	  		trans=new TransHistoryPage(driver);
	  		logger.info("Checking file a complaint legend");
	  		trans.clkFileaComplaintLegend();
	  		
//Check if file a complaint legend is displayed
	  		if (trans.isFileaComplaintDisplayed()==true)
	  			{
	  			soft.assertTrue(true);
	  			logger.info("File a Complaint Legend is displayed");
	  		}

	  		else {
	  			captureScreen(driver, "ValidateTransactionsLegend");
	  			logger.error ("File a complaint legend is not displayed");
	  			soft.assertTrue(false);
	  			}
	  		soft.assertAll();
	}}

