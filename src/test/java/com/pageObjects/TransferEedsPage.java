package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferEedsPage {
	
	public WebDriver ldriver;
	//Constructor
	public TransferEedsPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	//Locate the "Amount" field
	@FindBy(xpath="//input[@id='transferAmount']")
	@CacheLookup
	WebElement transferAmount;
	
	//Click on Transfer button
	@FindBy(xpath = "//span[contains(text(),'TRANSFER')]")
	@CacheLookup
	WebElement btnTransfer;

	//Confirm Transfer
	@FindBy(xpath="//a[@id='btn_transfer']/span")
	@CacheLookup
	WebElement btnConfirmTransfer;
	
	//Capture the text from credit success message
	@FindBy(xpath = "/html[1]/body[1]/div[7]/div[1]/div[1]")
	@CacheLookup
	WebElement trfeedsmsg;
	
	
	//Method to enter the required "Transfer Amount"
	public void setTransferAmount(String amount)
	{
		transferAmount.sendKeys(amount);
	}
	
	//Click on transfer eeds button
	public void clkbtnTransfer()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnTransfer));
		wait.until(ExpectedConditions.elementToBeClickable(btnTransfer));
		btnTransfer.click();
	}
	
	//Click on confirm transfer eeds
	public void clkbtnConfirmTransfer()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnConfirmTransfer));
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmTransfer));
		btnConfirmTransfer.click();
	}
	
	//Capture the credit transfer message
	public String gettrfeedsmsg()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(trfeedsmsg));
		return trfeedsmsg.getText();
	}
	
	
	
	

}
