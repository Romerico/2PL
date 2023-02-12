package com.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MessagePage {
	public WebDriver ldriver;

	// Constructor

	public MessagePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// Pager elements on Messages tab
	@FindBy(xpath = "//ul[@class='pagination clearfix']/li/a/span")
	@CacheLookup
	List<WebElement> pagerlinksmsg;

	// Pager elements - help links (first,prev,next,last) on Message tab
	@FindBy(xpath = "//li[@class='help']")
	@CacheLookup
	List<WebElement> msghelplinks;

	// Click on start button
	@FindBy(xpath = "//span[contains(text(),'I«')]")
	@CacheLookup
	WebElement btnStart;

	// Click on previous button
	@FindBy(xpath = "//span[contains(text(),'«')]")
	@CacheLookup
	WebElement btnPrevious;

	// Click on next button
	@FindBy(xpath = "//span[contains(text(),'»')]")
	@CacheLookup
	WebElement btnNext;

	// Click on end button
	@FindBy(xpath = "//span[contains(text(),'»I')]")
	@CacheLookup
	WebElement btnEnd;

	// Find row count in table
	@FindBy(xpath = "//table[@class='table-message']/tbody/tr")
	@CacheLookup
	List<WebElement> txtTablerows;

	// Find column count in table
	@FindBy(xpath = "//table[@class='table-message']/tbody/tr[1]/td")
	@CacheLookup
	List<WebElement> txtTablecolumns;

	// Sale agreement checkbox on 'New Bid' Mail
	@FindBy(id = "Cagreeterm-styler")
	@CacheLookup
	WebElement chkboxSaleAgreement;
	
	//Sale agreement checkbox on 'Bid offer' mail
	@FindBy(id = "magreeterm-styler")
	@CacheLookup
	WebElement chkSaleAgreementBidOffer;

	// Make offer button
	@FindBy(id = "negotiateofferbtn")
	@CacheLookup
	WebElement btnMakeOffer;

	// Capture 'Submit your bid' popup
	@FindBy(xpath = "//h4[contains(text(),'SUBMIT YOUR BID')]")
	@CacheLookup
	WebElement popupSubmitbid;

	// Capture mandatory 'Price field' on 'Submit your bid' popup
	@FindBy(id = "price")
	@CacheLookup
	WebElement popupPrice;

	// Capture optional 'Messages' on 'Submit your bid' popup
	@FindBy(id = "message")
	@CacheLookup
	WebElement popupTextMessage;

	// Sale agreement disclaimer checkbox on 'Submit your bid' popup
	@FindBy(id = "pagreeterm-styler")
	@CacheLookup
	WebElement chkSaleAgreement;

	// Send Button on 'Submit your bid' popup
	@FindBy(xpath = "//button[@id='contract_sendN']//span[@class='help'][contains(text(),'send')]")
	@CacheLookup
	WebElement btnSendpopup;

	// Cancel button on 'Submit your bid' popup
	@FindBy(xpath = "//div[@class='form-group clearfix']//span[@class='help'][contains(text(),'cancel')]")
	@CacheLookup
	WebElement btnCancelpopup;
	
	// Accept button
	@FindBy(xpath = "//a[@id='seller_confirm_btn']")
	@CacheLookup
	WebElement btnAccept;

	//Accept button - Bid Offer message
	@FindBy(xpath = "//a[@id='seller_confirm_btn1']")
	@CacheLookup
	WebElement btnAcceptBidOffer;

	// Decline button
	@FindBy(xpath = "//a[contains(text(),'Decline Bid')]")
	@CacheLookup
	WebElement btnDecline;

	// Click on back button to go to previous page i.e. Messages page
	@FindBy(xpath = "//a[@class='btn w-btn-default']")
	@CacheLookup
	WebElement btnBack;

	// Find the latest entry in the messages and select the checkbox
	@FindBy(xpath = "//*[@id='inbox']/table/tbody/tr[1]/td[1]/div/label")
	@CacheLookup
	WebElement chkboxlatestentry;

	// Delete button
	@FindBy(xpath = "//span[contains(text(),'Delete')]")
	@CacheLookup
	WebElement btnDelete;

	// Get text from bid cancel/deny order message
	@FindBy(xpath = "//div[@class='commit']")
	@CacheLookup
	WebElement txtmessage;

	// Deny Purchase button
	@FindBy(xpath = "//a[@class='btn btn-default']")
	@CacheLookup
	WebElement btnDenyPurchase;

	// Confirm button when 'Confirm delivery' mail is opened
	@FindBy(xpath = "//a[@class='btn btn-success']")
	@CacheLookup
	WebElement btnConfirm;

	// Confirm button when 'Confirm delivery' mail is opened
		@FindBy(xpath = "//a[@class='btn btn-success']")
		@CacheLookup
		WebElement btnConfirm1;
		
	
	// First entry in the inbox
	@FindBy(xpath = "//table[@class='table-message']/tbody/tr[1]/td[2]/a")
	@CacheLookup
	WebElement txtfirstmail;

	// Second entry in the inbox
		@FindBy(xpath = "//table[@class='table-message']/tbody/tr[2]/td[2]/a")
		@CacheLookup
		WebElement txtsecondmail;
	
	
	//Get the text from the Transaction history body
		@FindBy (xpath = "//table[@class='table-message']/tbody/tr/td/h4")
		@CacheLookup
		WebElement txtMessagesInfo;
		
		//Get the size of the transaction history body
		@FindBy (xpath="//table[@class='table-message']/tbody/tr")
		@CacheLookup
		WebElement txtMessagesize;
		
		// Click message link "Confirm Delivery"
		@FindBy(linkText="Service Refund")
		@CacheLookup
		WebElement servicerefundlink;
		
		// Click Refund button
		@FindBy(xpath="//a[@class='btn btn-success']")
		@CacheLookup
		WebElement btnrefund;	
		
		// Confirm Refund
		@FindBy(xpath="//div[contains(text(),'The refund amount has been deducted from your bala')]")
		@CacheLookup
		WebElement cfmrefund;

		//Get the number of unread messages from Inbox
		@FindBy (xpath = "//span[@class='badge pull-right']")
		@CacheLookup
		WebElement txtunreadInbox;
		
		//Get the number of messages in each page
		@FindBy (xpath = "//table[@class='table-message']/tbody/tr")
		@CacheLookup
		List<WebElement> txttablerows;
		
		public int gettxttablerows()
		{
			return txttablerows.size();
		}
		
		//Locate the messages table 
		@FindBy(xpath=("//table[@class='table-message']"))
		@CacheLookup
		WebElement messageTable;

		//Locate the "review" link in the "Rate service" message
		@FindBy(xpath="//a[@id=\"rate_link\"]")
		@CacheLookup
		WebElement reviewLnk;

		//Locate the review Comment in the "review" message box
		@FindBy(xpath="//textarea[@id='content']")
		@CacheLookup
		WebElement reviewComment;
		
		//Locate the "Review Stars" in the "review" message box
		@FindBy(xpath="//div[@class='vote-active']")
		@CacheLookup
		WebElement reviewStarsSlider;

		//Locate the "Submit" button in the "review" message box
		@FindBy(xpath="//button[@id='btn_review']")
		@CacheLookup
		WebElement reviewSubmit;
		
		//Get the message when refund accepted
		@FindBy(xpath = "//div[@class='alert alert-default text-center']")
		@CacheLookup
		WebElement txtrefundacceptmsg;

		
	// ==========================================================================
	// Total No of pager elements found
	public int getpagerlinkssize() {
		return pagerlinksmsg.size();
	}

	// Returns the number of help links found i.e.4
	public int gethelplinkssize() {
		return msghelplinks.size();
	}

	//Start button is displayed
	public boolean displaysbtnStart()
	{
		return btnStart.isDisplayed();
	}
	
	// Click on start button
	public void clkbtnStart() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnStart));
		wait.until(ExpectedConditions.elementToBeClickable(btnStart));
		btnStart.click();
	}
	//End button is displayed
		public boolean displaysbtnEnd()
		{
			return btnEnd.isDisplayed();
		}
		

	// Click on end button
	public void clkbtnEnd() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnEnd));
		wait.until(ExpectedConditions.elementToBeClickable(btnEnd));
		btnEnd.click();
	}

	//Previous button is displayed
		public boolean displaysbtnPrevious()
		{
			return btnPrevious.isDisplayed();
		}
		
	// Click on previous button
	public void clkbtnPrevious() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnPrevious));
		wait.until(ExpectedConditions.elementToBeClickable(btnPrevious));
		btnPrevious.click();
	}

	//Next button is displayed
			public boolean displaysbtnNext()
			{
				return btnNext.isDisplayed();
			}
			
	// Click on next button
	public void clkbtnNext() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnNext));
		wait.until(ExpectedConditions.elementToBeClickable(btnNext));
		btnNext.click();
	}

	// Count the number of rows in the table
	public int getTablerowscount() {
		return txtTablerows.size();
	}

	// Count the number of columns in the table
	public int getTablecolumncount() {
		return txtTablecolumns.size();
	}

	// Checks whether sale agreement checkbox is displayed on 'New Bid' Mail
	public boolean displaysSaleAgreementbox() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(chkboxSaleAgreement));
		wait.until(ExpectedConditions.elementToBeClickable(chkboxSaleAgreement));
		return chkboxSaleAgreement.isDisplayed();
	}

	// Checks whether sale agreement checkbox is enabled on 'New Bid' Mail
	public boolean enabledSaleAgreementbox() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(chkboxSaleAgreement));
		wait.until(ExpectedConditions.elementToBeClickable(chkboxSaleAgreement));
		return chkboxSaleAgreement.isEnabled();
	}

	// Click on sale agreement checkbox on 'New Bid' Mail
	public void clkSaleAgreementbox() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(chkboxSaleAgreement));
		wait.until(ExpectedConditions.elementToBeClickable(chkboxSaleAgreement));
		chkboxSaleAgreement.click();
	}
	
	
	
	// Click on sale agreement checkbox on 'Bid offer' Mail
		public void clkSaleAgreementBidOffer() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(chkSaleAgreementBidOffer));
			wait.until(ExpectedConditions.elementToBeClickable(chkSaleAgreementBidOffer));
			chkSaleAgreementBidOffer.click();
		}
	
		// Checks whether sale agreement checkbox on 'Bid offer' Mailn is displayed
		public boolean displaysSaleAgreementBidOffer() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(chkSaleAgreementBidOffer));
			wait.until(ExpectedConditions.elementToBeClickable(chkSaleAgreementBidOffer));
			return chkSaleAgreementBidOffer.isDisplayed();
		}

		// Checks whether sale agreement checkbox on 'Bid offer' Mailn is enabled
		public boolean enabledSaleAgreementBidOffer() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(chkSaleAgreementBidOffer));
			wait.until(ExpectedConditions.elementToBeClickable(chkSaleAgreementBidOffer));
			return chkSaleAgreementBidOffer.isEnabled();
		}


	// Checks whether make offer button is displayed
	public boolean displaysbtnMakeOffer() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnMakeOffer));
		wait.until(ExpectedConditions.elementToBeClickable(btnMakeOffer));
		return btnMakeOffer.isDisplayed();
	}

	// Checks whether make offer button is enabled
	public boolean enabledbtnMakeOffer() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnMakeOffer));
		wait.until(ExpectedConditions.elementToBeClickable(btnMakeOffer));
		return btnMakeOffer.isEnabled();
	}

	// Click on Make offer button
	public void clkbtnMakeOffer() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnMakeOffer));
		wait.until(ExpectedConditions.elementToBeClickable(btnMakeOffer));
		btnMakeOffer.click();
	}

	// Capture the text value from the 'Submit your bid' Popup
	public String getSubmityourBidpopup() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(popupSubmitbid));
		wait.until(ExpectedConditions.elementToBeClickable(popupSubmitbid));
		return popupSubmitbid.getText();
	}

	// Check whether Price field is displayed on 'Submit your bid' Popup
	public boolean displayPricefield() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(popupPrice));
		wait.until(ExpectedConditions.elementToBeClickable(popupPrice));
		return popupPrice.isDisplayed();
	}

	// Check whether Price field is enabled on 'Submit your bid' Popup
	public boolean enabledPricefield() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(popupPrice));
		wait.until(ExpectedConditions.elementToBeClickable(popupPrice));
		return popupPrice.isEnabled();
	}
	
	//Clear the price that is already present in Popup
	public void clearPricefield()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(popupPrice));
		wait.until(ExpectedConditions.elementToBeClickable(popupPrice));
		popupPrice.clear();
	}
	
	//Input price on 'Submit your bid' popup
	public void txtPrice(String pr) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(popupPrice));
		wait.until(ExpectedConditions.elementToBeClickable(popupPrice));
		popupPrice.sendKeys(pr);
	}
	
	// Check whether Message field is displayed on 'Submit your bid' Popup
	public boolean displaytxtMessage() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(popupTextMessage));
		wait.until(ExpectedConditions.elementToBeClickable(popupTextMessage));
		return popupTextMessage.isDisplayed();
	}

	// Check whether Message field is enabled on 'Submit your bid' Popup
	public boolean enabledtxtMessage() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(popupTextMessage));
		wait.until(ExpectedConditions.elementToBeClickable(popupTextMessage));
		return popupTextMessage.isEnabled();
	}
	
	//Input Message(optional) on 'Submit your bid' popup
		public void txtOptionalMessage(String msg) {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(popupTextMessage));
			wait.until(ExpectedConditions.elementToBeClickable(popupTextMessage));
			popupTextMessage.sendKeys(msg);
		}

	// Check whether sale agreement disclaimer checkbox is displayed on 'Submit your bid' Popup
	public boolean displaySaleAgreementpopup() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(chkSaleAgreement));
		wait.until(ExpectedConditions.elementToBeClickable(chkSaleAgreement));
		return chkSaleAgreement.isDisplayed();
	}

	// Check whether sale agreement disclaimer checkbox is enabled on 'Submit your bid' Popup
	public boolean enabledSaleAgreementpopup() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(chkSaleAgreement));
		wait.until(ExpectedConditions.elementToBeClickable(chkSaleAgreement));
		return chkSaleAgreement.isEnabled();
	}
	//Click on Sale agreement disclaimer checkbox on 'Submit your bid' popup
	public void clkbtnSaleAgreementpopup() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(chkSaleAgreement));
		wait.until(ExpectedConditions.elementToBeClickable(chkSaleAgreement));
		chkSaleAgreement.click();
	}	
	
	// Check whether Send button is displayed on 'Submit your bid' popup
	public boolean displaybtnSendpopup() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnSendpopup));
		wait.until(ExpectedConditions.elementToBeClickable(btnSendpopup));
		return btnSendpopup.isDisplayed();
	}

	// Check whether Send button is enabled on 'Submit your bid' popup
	public boolean enabledbtnSendpopup() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnSendpopup));
		wait.until(ExpectedConditions.elementToBeClickable(btnSendpopup));
		return btnSendpopup.isEnabled();
	}
	
	//Click on Send button on 'Submit your bid' popup
		public void clkbtnSendpopup() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(btnSendpopup));
			wait.until(ExpectedConditions.elementToBeClickable(btnSendpopup));
			btnSendpopup.click();
		}	

	// Check whether Cancel button is displayed on 'Submit your bid' popup
	public boolean displaybtnCancelpopup() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnCancelpopup));
		wait.until(ExpectedConditions.elementToBeClickable(btnCancelpopup));
		return btnCancelpopup.isDisplayed();
	}

	// Check whether Cancel button is enabled on 'Submit your bid' popup
	public boolean enabledbtnCancelpopup() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnCancelpopup));
		wait.until(ExpectedConditions.elementToBeClickable(btnCancelpopup));
		return btnCancelpopup.isEnabled();
	}
	
	//Click on Cancel button on 'Submit your bid' popup
	public void clkbtnCancelpopup() {
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(btnCancelpopup));
				wait.until(ExpectedConditions.elementToBeClickable(btnCancelpopup));
				btnCancelpopup.click();
			}
	
	
	// Checks whether Accept button is displayed
	public boolean displaysbtnAccept() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnAccept));
		wait.until(ExpectedConditions.elementToBeClickable(btnAccept));
		return btnAccept.isDisplayed();
	}

	// Checks whether Accept button is enabled
	public boolean enabledbtnAccept() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnAccept));
		wait.until(ExpectedConditions.elementToBeClickable(btnAccept));
		return btnAccept.isEnabled();
	}

	// Click on Accept button
	public void clkbtnAccept() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnAccept));
		wait.until(ExpectedConditions.elementToBeClickable(btnAccept));
		btnAccept.click();
	}
	
	
	// Checks whether Accept button on Bid Offer message is displayed
		public boolean displaysbtnAcceptBidOffer() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(btnAcceptBidOffer));
			wait.until(ExpectedConditions.elementToBeClickable(btnAcceptBidOffer));
			return btnAcceptBidOffer.isDisplayed();
		}

		// Checks whether Accept button on Bid Offer message is enabled
		public boolean enabledbtnAcceptBidOffer() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(btnAcceptBidOffer));
			wait.until(ExpectedConditions.elementToBeClickable(btnAcceptBidOffer));
			return btnAcceptBidOffer.isEnabled();
		}


	// Checks whether Decline bid button is displayed
	public boolean displaysbtnDecline() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnDecline));
		wait.until(ExpectedConditions.elementToBeClickable(btnDecline));
		return btnDecline.isDisplayed();
	}

	// Checks whether Decline button is enabled
	public boolean enabledbtnDecline() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnDecline));
		wait.until(ExpectedConditions.elementToBeClickable(btnDecline));
		return btnDecline.isEnabled();
	}

	// Click on Decline Bid button
	public void clkbtnDecline() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnDecline));
		wait.until(ExpectedConditions.elementToBeClickable(btnDecline));
		btnDecline.click();
	}

	// Click on back button to return to Message page
	public void clkbtnBack() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnBack));
		wait.until(ExpectedConditions.elementToBeClickable(btnBack));
		btnBack.click();
	}

	// Click the latest checkbox
	public void clkLatestEntry() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(chkboxlatestentry));
		wait.until(ExpectedConditions.elementToBeClickable(chkboxlatestentry));
		chkboxlatestentry.click();
	}

	// Click on delete button
	public void clkbtnDelete() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(btnDelete));
		wait.until(ExpectedConditions.elementToBeClickable(btnDelete));
		btnDelete.click();
	}

	// Get the text from bid cancel/order denied mail message
	public String getTextMessage() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtmessage));
		wait.until(ExpectedConditions.elementToBeClickable(txtmessage));
		return txtmessage.getText();
	}

	// Click on Deny Purchase button
	public void clkbtnDenyPurchase() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnDenyPurchase));
		wait.until(ExpectedConditions.elementToBeClickable(btnDenyPurchase));
		btnDenyPurchase.click();
	}

	// Get string from latest mail (heading)
	public String gettxtfirstmail() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtfirstmail));
		wait.until(ExpectedConditions.elementToBeClickable(txtfirstmail));
		return txtfirstmail.getText();
	}

	// Click the first mail
	public void clktxtfirstmail() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtfirstmail));
		wait.until(ExpectedConditions.elementToBeClickable(txtfirstmail));
		txtfirstmail.click();
	}

	// Get string from second mail (heading)
		public String gettxtsecondmail() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(txtsecondmail));
			wait.until(ExpectedConditions.elementToBeClickable(txtsecondmail));
			return txtsecondmail.getText();
		}

		// Click the second mail
		public void clktxtsecondmail() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(txtsecondmail));
			wait.until(ExpectedConditions.elementToBeClickable(txtsecondmail));
			txtsecondmail.click();
		}
	
	// Click on Confirm button in Confirm Delivery mail
	public void clkbtnConfirm() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnConfirm));
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
	}
	
	// Click on Confirm button in Confirm Delivery mail (duplicate)
		public void clkbtnConfirm1() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(btnConfirm1));
			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm1));
			btnConfirm1.click();
		}
	
	public String gettxtMessagesInfo()
	{
		return txtMessagesInfo.getText();
	}

	public Object gettxtMessagesize()
	{
		return txtMessagesize.getSize();
		
	}
	public void clickServiceRefund()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(servicerefundlink));
		servicerefundlink.click();
	}
	public void clickRefundButton()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnrefund));
		wait.until(ExpectedConditions.elementToBeClickable(btnrefund));
		btnrefund.click();
	}
	public void confirmRefund()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(cfmrefund));
	}
	
	public String gettxtunreadInbox()
	{
		return txtunreadInbox.getText();
	}

	//Method to check if the duplicate "Confirm Order" message is present in the Seller's inbox page
	public boolean confirmOrderDuplicateMessagePresent()
	{
		String mName;
		boolean duplicatePresent = false;
		
		try {
			mName = messageTable.findElement(By.xpath("//table[@class='table-message']//tbody/tr[2]/td[2]")).getText();
			//logger.info("Message Name is: " + mName + " RowNum: "+ i);
		
			if(mName.equals("Confirm Order"))
			{
				duplicatePresent = true;
			}
		}catch(StaleElementReferenceException e) {
		}
		
		return duplicatePresent;
	}

	//Method to "Deny" the duplicate "Confirm Order" message is present in the Seller's inbox page
	public void confirmOrderDuplicateMessageDeny()
	{
		String mName;
		
		try {
			mName = messageTable.findElement(By.xpath("//table[@class='table-message']//tbody/tr[2]/td[2]")).getText();
			//logger.info("Message Name is: " + mName + " RowNum: "+ i);
		
			if(mName.equals("Confirm Order"))
			{
				messageTable.findElement(By.xpath("//table[@class='table-message']//tbody/tr[2]/td[2]/a")).click();
				if(btnDenyPurchase.isDisplayed()&&btnDenyPurchase.isEnabled())
				{
					clkbtnDenyPurchase();
				}
			}
		}catch(StaleElementReferenceException e) {
		}
	}
	
	//Method to open the required "Message Name" from the messages table in the "Inbox" page
	public void findMessageInInbox(String messageName)
	{	
		String mName;
		//messageTable.findElement(By.xpath("//table[@class='table-message']//tbody/tr[1]/td[2]/a")).click();
		
		for(int i=1; i<=gettxttablerows(); i++)
		{
			try {
				mName = messageTable.findElement(By.xpath("//table[@class='table-message']//tbody/tr[" + i + "]/td[2]")).getText();
				//logger.info("Message Name is: " + mName + " RowNum: "+ i);
			
				if(mName.equals(messageName))
				{
					messageTable.findElement(By.xpath("//table[@class='table-message']//tbody/tr[" + i + "]/td[2]/a")).click();
					break;
				}
			}catch(StaleElementReferenceException e) {
			}
		}
	}

	//Method to click on the "review" link in the "Rate service" message
		public void clkReviewLnk()
		{
			if(reviewLnk.isDisplayed()&&reviewLnk.isEnabled())
			{
				reviewLnk.click();
			}
		}
		
		//Method to enter the Review Comment in the "review" message box
		public void setReviewComment(String comment)
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(reviewComment));
			wait.until(ExpectedConditions.elementToBeClickable(reviewComment));
			reviewComment.sendKeys(comment);
		}

		//Method to select the "Number of Stars" in the "review" message box
		public void setReviewStarsSilder(int level)
		{
			int xoffset = level*16;
			WebElement ele=reviewStarsSlider;
			Actions act=new Actions(ldriver);
			
			act.moveToElement(ele).dragAndDropBy(ele, xoffset, 0).build().perform();
		}

		//Method to click on the "Submit" button in the "review" message box
		public void clkReviewSubmit()
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(reviewSubmit));
			wait.until(ExpectedConditions.elementToBeClickable(reviewSubmit));
			reviewSubmit.click();
		}
		
		//Get the text when refund is accepted
		public String gettxtrefundacceptmsg()
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(txtrefundacceptmsg));
			return txtrefundacceptmsg.getText();
		}
		
		
}
