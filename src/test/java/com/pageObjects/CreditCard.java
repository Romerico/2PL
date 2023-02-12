package com.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreditCard {

	public WebDriver ldriver;

	// Constructor
		public CreditCard(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// credit card popup
	@FindBy(xpath = ("//h4[contains(text(),'INSUFFICIENT EEDS')]"))
	@CacheLookup
	WebElement txtINSUFFICIENTEEDS;

	public String gettxtINSUFFICIENTEEDS() {
		return txtINSUFFICIENTEEDS.getText();
	}

	// set Credit card name

	@FindBy(xpath = ("//input[@id='name_on_card']"))
	@CacheLookup
	WebElement txtcreditcardname;

	public void setcreditcardname(String value) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(txtcreditcardname));
		txtcreditcardname.sendKeys(value);
	}

	// Set credit card number
	@FindBy(xpath = ("//input[@name = 'cardnumber']"))
	@CacheLookup
	WebElement txtcreditcardnumber;

	public void settxtcreditcardnumber(String value) {
		txtcreditcardnumber.clear();
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(txtcreditcardnumber));
		txtcreditcardnumber.sendKeys(value);
		JavascriptExecutor jse = (JavascriptExecutor) ldriver;
		jse.executeScript(String.format(value),txtcreditcardnumber);
	}

	// Set credit card month and year
	@FindBy(xpath = ("//input[@name='exp-date']"))
	@CacheLookup
	WebElement txtcreditcardmmyy;

	public void settxtcreditcardmmyy(String num) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(txtcreditcardmmyy));
		txtcreditcardmmyy.sendKeys(num);
	}

	// set credit card cvc
	@FindBy(xpath = ("//input[@name='cvc']"))
	@CacheLookup
	WebElement txtcreditcardcvc;

	public void settxtcreditcardcvc(String value) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(txtcreditcardcvc));
		txtcreditcardcvc.sendKeys(value);
	}

	// set credit card zip

	@FindBy(xpath = ("//input[@name='postal']"))
	@CacheLookup
	WebElement txtcreditcardzip;

	public void settxtcreditcardzip(String value) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(txtcreditcardzip));
		txtcreditcardzip.sendKeys(value);
	}

	// clicking pay button
	@FindBy(xpath = ("//*[@id='submitPayment']/span"))
	@CacheLookup
	WebElement clickpaybutton;

	public void clickpaybtn() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(clickpaybutton));
		clickpaybutton.click();
	}

	//Clicking on purchase ok button
	@FindBy(xpath = ("//span[contains(text(),'OK')]"))
	@CacheLookup
	WebElement clickpurchaseokbtn;

	public void clickpurchaseok() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(clickpurchaseokbtn));
		clickpurchaseokbtn.click();
	}

	// Get Purchase success text
	@FindBy(xpath = ("//h4[contains(text(),'PURCHASE SUCCEEDED')]"))
	@CacheLookup
	WebElement txtpurchasesuccessmsg;

	public String gettxtpurchasesuccessmsg() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(txtpurchasesuccessmsg));
		return txtpurchasesuccessmsg.getText();
	}

	//Get the error message when the card details are left blank
	@FindBy (id="card-errors")
	@CacheLookup
	WebElement txtcreditcardblankerror;
	
	public String gettxtcreditcardblankerror()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(txtcreditcardblankerror));
		return txtcreditcardblankerror.getText();
	}
	
	//Close the Insufficient Eeds pop up page
	@FindBy (xpath = "//div[@id='myModalPayment']//button[@class='close']")
	@CacheLookup
	WebElement btnClose;
	
	public void clkbtnClose()
	{
		btnClose.click();
	}
	
	//Get the error message when the card details are filled incorrectly
	@FindBy (id="card-errors")
	@CacheLookup
	WebElement txtincorrectcreditcarderror;
	
	public String gettxtincorrectcreditcarderror()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(txtincorrectcreditcarderror));
		return txtincorrectcreditcarderror.getText();
		
	}
	
	
	
	
	}
