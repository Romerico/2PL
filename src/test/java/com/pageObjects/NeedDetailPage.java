package com.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NeedDetailPage {
public WebDriver ldriver;
	
	//Constructor
	
	public NeedDetailPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	//Capture the modal popup text while adding the service
		@FindBy(xpath="//div[contains(text(),'Need has been added')]")
		@CacheLookup
		WebElement txtaddNeedcaption;

		//Capture the modal popup text while deleting the service
		@FindBy(xpath="//div[contains(text(),'Need has been deleted')]")
		@CacheLookup
		WebElement txtdelNeedcaption;
		
		//Get the Need name from the need detail page
		@FindBy (xpath = "//div[@class='caption'][1]")
		@CacheLookup
		WebElement txtneedname;
		
		//Locate the three link icon 
		@FindBy(xpath="//span[@class='w-icons-fork green']")
		@CacheLookup
		public WebElement threeLinkBtn;

		//Locate sharing options
		@FindBy(xpath="/html/body/div[7]/section/div/div[2]/div[2]/div[1]/div/div/div/ul/li/a")
		@CacheLookup
		public List<WebElement> shareNeedOptions;

		//Locate facebook element
		@FindBy(xpath="//span[@class='w-icons-fork face']")
		@CacheLookup
		public WebElement fbShare;
		
		
		//====================================================================//
		
		public String gettxtaddNeedcaption()
		{
			return txtaddNeedcaption.getText();
		}

		public String gettxtdelNeedcaption()
		{
			return txtdelNeedcaption.getText();
		}
		
		//Get the need name from the need detail page
		public String gettxtneedname()
		{
			return txtneedname.getText();
		}
		
		//Click on three icon link to open share needs
		public void clickThreeLinkIcon()
		{
			threeLinkBtn.click();
		}

		//Get the number of sharing options
		public int getShareNeedsOptions()
		{
			return shareNeedOptions.size();
			
		}

		// Click on fb sharing option
		public void clickFB()
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(fbShare));
			wait.until(ExpectedConditions.elementToBeClickable(fbShare));
			fbShare.click();
		}
		
	
	
	
}
