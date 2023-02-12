package com.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchNeed {

public WebDriver ldriver;
	
	// Constructor
	public SearchNeed (WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	//Click the created need link
		@FindBy(xpath = "//a[contains(text(),'Test user need')]")
		@CacheLookup
		WebElement btncreatedneed;
		
		//Capture 'I can Do this' option
		@FindBy (id = "bid_button")
		@CacheLookup
		WebElement btnIcandothis;
		
		//Capture the Price field
		@FindBy (id = "price")
		@CacheLookup
		WebElement txtprice;
		
		//Capture the dropdown button
		@FindBy (xpath = "//form[@id='purchase_contract_form']//div[@id='service-styler']//div[@class='jq-selectbox__trigger']")
		@CacheLookup
		WebElement drpdownsvc;
		
		//Capture the list of service name available
		@FindBy (xpath= "//ul[@style='position: relative; list-style: none; overflow: hidden auto; max-height: 250px;']/li")
		@CacheLookup
		List <WebElement> drpdownsvclist;
		
		//Capture the sale agreement check box
		@FindBy (xpath = "//div[@id='agreeterm-styler']")
		@CacheLookup
		WebElement boxsaleagreement;
		
		//Capture the Send button
		@FindBy(id = "contract_send")
		@CacheLookup
		WebElement btnSend;
		
		//Capture the Ok button
		@FindBy (xpath = "//a[@class='btn btn-success pull-right']")
		@CacheLookup
		WebElement btnOk;
		
		//==================================================================//
		
		public void clkbtncreatedneed()
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(btncreatedneed));
			wait.until(ExpectedConditions.elementToBeClickable(btncreatedneed));
			btncreatedneed.click();
		}
		
		//Click on 'I can do this' button
		public void clkbtnIcandothis()
		{
			btnIcandothis.click();
		}
		
		//Clear the value in price field
		public void clrtxtprice()
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(txtprice));
			Actions act = new Actions(ldriver);
			// Clearing text field 
						act.click(txtprice).keyDown(Keys.CONTROL)
						    .sendKeys("a")
						    .keyUp(Keys.CONTROL)
						    .sendKeys(Keys.BACK_SPACE)
						    .build()
						    .perform();
							}
		
		//Send the value to price field
		public void settxtprice(String pr)
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(txtprice));
			txtprice.sendKeys(pr);
		}
	
		//Click the service name dropdown to get the list 
				public void clkdrpdownsvc()
				{
					drpdownsvc.click();
				}
				
				//Select from Service name List
				public void SelectdrpdownSvcname(String svcName) {
					for(WebElement svc : drpdownsvclist)
				   {
					   if(svc.getText().equalsIgnoreCase(svcName))
					   {
						   svc.click();
						   break;
					   }
				   }
				}
			
		//Click the Sale agreement checkbox
		public void clkboxsaleagreement()
		{
			boxsaleagreement.click();
		}
		
		//Click on the Send button
		public void clkbtnSend()
		{
			btnSend.click();
		}
		
		//Click on Ok button
		public void clkbtnOk()
		{
			btnOk.click();
		}
		
}
