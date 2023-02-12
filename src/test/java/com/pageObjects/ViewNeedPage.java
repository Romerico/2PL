package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ViewNeedPage {
public WebDriver ldriver;
	
	//Constructor
	
	public ViewNeedPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	 @FindBy(xpath=("//div[7]/section/div/div[3]/div/a/span"))
	    @CacheLookup
	    WebElement ICanDoThisBtn;
	    
	    //On Submit You Bid Page
	    @FindBy(xpath =("//*[@id='agreeterm-styler']"))
	    @CacheLookup
	    WebElement saleAgreement;
	    
	    @FindBy(xpath=("//*[@id=\"contract_send\"]/span"))
	    @CacheLookup
	    WebElement sendBtn;
	    //Txt msg on bidding Sent
	    @FindBy(xpath=("//*[@id='collapseOne']/div[2]/div")) 
	    @CacheLookup
	    WebElement txtmsg;
	    
	    @FindBy(xpath=("/html/body/div[7]/section/div/div[3]/a"))
	    @CacheLookup
	    WebElement okBtn;
	    
		//Find the Element of the price textbox
		@FindBy(xpath="//input[@id='price']")
		public WebElement txtPrice;
		
		//Find the Element of the Label
		@FindBy(xpath="//div[@id='termreminder']/strong/font")
		public WebElement txtNegPrice;

		//Find the Element of the Title of Service Page
				@FindBy(xpath="//div[@class='caption']")
				@CacheLookup // is used to improve the performance 
				public WebElement NeedTitle;
				
				//Find the Element of the Description of Service Page
				@FindBy(xpath="//div[@class='body']/p[1]")
				@CacheLookup // is used to improve the performance 
				public WebElement NeedDescription;
				
				
				//Find the Element of the Category
				@FindBy(xpath="//table[@class='table-Colorful dual-centered']//tbody/tr[4]/td[2]")
				@CacheLookup // is used to improve the performance 
				public WebElement NeedCategory;
					
				//Find the Element of the SubCategory
					@FindBy(xpath="//table[@class='table-Colorful dual-centered']//tbody/tr[5]/td[2]")
					@CacheLookup // is used to improve the performance 
					public WebElement NeedSubCategory;
					
					//Find the Element of the Price
					@FindBy(xpath="//ul[@class='dataPanel clearfix']/li[3]/div[1]/div[1]")
					@CacheLookup // is used to improve the performance 
					public WebElement NeedPrice;
						
					
					//Find the element of the Image1
					@FindBy(xpath="//ul[@class='img-holder clearfix']/li[1]/a[1]")
					public WebElement Image1;
					
					//Find the Element of the Image2

					@FindBy(xpath="//ul[@class='img-holder clearfix']/li[2]/a[1]")
					public WebElement Image2;
					
					//Find the Element of the Image3

					@FindBy(xpath="//ul[@class='img-holder clearfix']/li[3]/a[1]")
					public WebElement Image3;
					
					//Find Element of the Edit Service Link
					@FindBy(xpath="//a[contains(text(),'Edit')]")
					public WebElement EditServiceLink;



	    
	    //==================================================================================//
	    
	    public void clickOk()
	    {
	    	WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(okBtn));
	       	okBtn.click();
	    }
	    
	    
	    
	    
	    public boolean verifyICanDoThisBtn()
	    {
	    	return(ICanDoThisBtn.isDisplayed());
	    }
	        public boolean verifyICanDoThis()
	    {
	    	return(ICanDoThisBtn.isEnabled());
	    }
	    public void clickICanDoThisBtn()
	    {
	    	ICanDoThisBtn.click();
	    }
	    public void clickSaleAgreement()
	    {
	     	WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(80));
			wait.until(ExpectedConditions.visibilityOf(saleAgreement));
	    	saleAgreement.click();
	    }
	    
	    public void clickSend()
	    {
	    	WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(80));
			wait.until(ExpectedConditions.visibilityOf(sendBtn));
			wait.until(ExpectedConditions.elementToBeClickable(sendBtn));
	    	sendBtn.click();
	    }
	    
	    public boolean verifyBiddingSentMsg()
	    {
	    	WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(txtmsg));
	       	return(txtmsg.getText().contains("has been sent"));
	    }
		
	  //This method is for Enter Negative Value in the Price Textbox
		public void SubmitvalueBid(String negValue)
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(35));
			wait.until(ExpectedConditions.visibilityOf(txtPrice));
			//Clear the Price Textbox
			txtPrice.clear();
			txtPrice.sendKeys(negValue);
		}

		//Get text when negative/alphabets/special characters are entered in price field
		public String gettxtPriceNegTest()
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(35));
			wait.until(ExpectedConditions.visibilityOf(txtNegPrice));
			return txtNegPrice.getText();
		}
		
		//Click on the Edit link
		public void ClickonEditButton()
		{
			EditServiceLink.click();
		}

		
	}


