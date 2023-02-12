package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OtherUserPage {
	public WebDriver ldriver;
	//Constructor
	public OtherUserPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	//Capture Send Eeds link
	@FindBy (xpath = "//span[@class='w-icons-profileCtrl2']")
	@CacheLookup
	WebElement btnSendeeds;
	
	//Capture Amount (eeds) field
	@FindBy (name = "amount")
	@CacheLookup
	WebElement txtamount;
	
	//Capture Transfer button
	@FindBy (id = "transfer_id")
	@CacheLookup
	WebElement btntransfer;
	
	//Capture Confirm transfer button
	
	@FindBy (xpath = "//a[@id='btn_transfer']")
	@CacheLookup
	WebElement btnconfirmtransfer;
	
	//=======================================================================
	
	//Click on Send eeds link 
	public void clkbtnSendeeds()
	{
		btnSendeeds.click();
	}
	
	//Pass the amount value to the amount field
	public void settxtamount(String eeds)
	{
		txtamount.sendKeys(eeds);
	}
	
	//Click on Transfer button
	public void clkbtntransfer()
	{
		btntransfer.click();
	}
	
	//Click on Confirm transfer button
	public void clkbtnconfirmtransfer()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(btnconfirmtransfer));
		wait.until(ExpectedConditions.elementToBeClickable(btnconfirmtransfer));
		btnconfirmtransfer.click();
	}
	
	
}
