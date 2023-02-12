package com.pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransHistoryPage {
public WebDriver ldriver;
	
	//Constructor
	
	public TransHistoryPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	@FindBy (xpath ="//ul[@class='pagination clearfix']/li/a/span")
	@CacheLookup
	List <WebElement> pagerlinkstxn;
	
	@FindBy( xpath = "//li[@class='help']")
	@CacheLookup
	List <WebElement> txnhelplinks;
	
	@FindBy (xpath = "//span[contains(text(),'I«')]")
	@CacheLookup
	WebElement btnStart;
	
	@FindBy (xpath = "//span[contains(text(),'«')]")
	@CacheLookup
	WebElement btnPrevious;
	
	@FindBy (xpath = "//span[contains(text(),'»')]")
	@CacheLookup
	WebElement btnNext;
	
	@FindBy (xpath = "//span[contains(text(),'»I')]")
	@CacheLookup
	WebElement btnEnd;
	
	// Find row count in table
	@FindBy(xpath = "//table[@class='table-Colorful color-3 stacktable large-only']/tbody/tr")
	@CacheLookup
	List<WebElement> txtTablerows;
	
	//To check whether there are no transactions
	@FindBy (xpath = "//table[@class='table-Colorful color-3 stacktable large-only']//tbody/tr[2]/td/h4")
	@CacheLookup
	WebElement txtNoTransaction;
	
	//Click flag tool tip for a complaint
		@FindBy(xpath=("//table[@class='table-Colorful color-3 stacktable large-only']//i[@class='w-icons-flag']"))
		@CacheLookup
		WebElement clkflagtooltip;
		
		//Check if the complaint tool tip box is opened
		@FindBy(xpath=("//table[@class='table-Colorful color-3 stacktable large-only']//i[@class='w-icons-flag tooltip-active']"))
		@CacheLookup
		WebElement complaintmessage;

		//Click dollar tool tip for a refund
		@FindBy(xpath=("//table[@class='table-Colorful color-3 stacktable large-only']//i[@class='w-icons-dollar']"))
		@CacheLookup
		WebElement clkdollartooltip;

		//Check if the dollar tool tip box is opened
		@FindBy(xpath=("//table[@class='table-Colorful color-3 stacktable large-only']//i[@class='w-icons-dollar tooltip-active']"))
		@CacheLookup
		WebElement refundmessage;
		
		//Check if refund popup is displayed
		@FindBy(xpath=("//div[@id='servicerefundmodal']//div[@class='modal-dialog']"))
		@CacheLookup
		WebElement refundpopup;

		//Click Cancel Button from refund popup
		@FindBy(xpath=("//button[@class='btn btn-border w-btn-default']"))
		@CacheLookup
		WebElement clkcancelbutton;
		
		//Check if Transaction History Page is displayed after click on Cancel Button from refund popup
		@FindBy(xpath=("//span[contains(text(),'Transaction History')]"))
		@CacheLookup
		WebElement transhistorypage;

		//Click "I want a refund" from refund popup
		@FindBy(xpath=("//button[@id='btn_refundService']"))
		@CacheLookup
		WebElement clkIwantarefundbutton;
		
		//Check if a success message is displayed after click on "I want a refund" from refund popup  
		@FindBy(xpath=("//div[@class='wrapper']//div[@id='refundmessage']/div/div"))
		@CacheLookup
		WebElement cfmrefundmessage;
		
		//Check dollar tool tip is not displayed
		@FindBy(xpath=("//table[@class='table-Colorful color-3 stacktable large-only']/tbody/tr[2]/td[7]/a/i"))
		@CacheLookup
		WebElement chkdollartooltip;
		
		//Count table rows
		@FindBy(xpath=("//table[@class='table-Colorful color-3 stacktable large-only']/tbody/tr"))
		@CacheLookup
		List<WebElement> cnttablerows;

		//Click Transaction Legend
		@FindBy(xpath=("//div[@class='container mobile-grid-table']//li[1]/div/span"))
		@CacheLookup
		WebElement clktranslegend;	
		
		//Confirm Transaction Legend
		@FindBy(xpath=("//div[@id='c-tooltip']"))
		@CacheLookup
		WebElement cfmtranslegend;	
		
		//Click Ask for a Refund Legend
		@FindBy(xpath=("//div[@class='container mobile-grid-table']//li[2]/div/span"))
		@CacheLookup
		WebElement clkaskforarefundlegend;	
		
		//Confirm Ask for a Refund Legend
		@FindBy(xpath=("//div[@id='c-tooltip']"))
		@CacheLookup
		WebElement cfmaskforarefundlegend;	
		
		//Click File a Complaint Legend
		@FindBy(xpath=("//div[@class='container mobile-grid-table']//li[3]/div/span"))
		@CacheLookup
		WebElement clkfileacomplaintlegend;	
		
		//Confirm File a complaint Legend
		@FindBy(xpath=("//div[@id='c-tooltip']"))
		@CacheLookup
		WebElement cfmfileacomplaintlegend;	
		
		//Get the transaction amount (EEDS) from the transaction table
		@FindBy(xpath = "//table[@class='table-Colorful color-3 stacktable large-only']/tbody/tr[1]/td[5]")
		@CacheLookup
		WebElement txttransAmt;
		
		//Get the Service name latest entry
		@FindBy (xpath = "//table[@class='table-Colorful color-3 stacktable large-only']/tbody/tr[1]/td[1]/div/div/a")
		@CacheLookup
		WebElement txtservicename;
		
		//Get the latest transaction (non clickable entry - e.g. Eeds purchase, Transfer)
		@FindBy(xpath = "//tr[1]//td[1]//div[1]//div[1]")
		@CacheLookup
		WebElement txtfirstentry;
		
		// capturing refund days
		@FindBy(xpath = "//table[@class='table-Colorful dual-centered']/tbody/tr[3]/td[2]/b")
		@CacheLookup
		public WebElement refundDays;

		//// locating actions column
		@FindBy(xpath = "//table[@class='table-Colorful color-3 stacktable large-only']/tbody/tr/td[7]/a")
		@CacheLookup
		public List<WebElement> Actions;

	    //Locating date of transaction
		@FindBy(xpath = "//table[@class='table-Colorful color-3 stacktable large-only']/tbody/tr[1]/td[6]/div")
		@CacheLookup
		public WebElement dateofTrans;
		
		// locating refund in actions row1
		@FindBy(xpath = "//tr[1]//td[7]//a[1]//i[1]")
		@CacheLookup
		public WebElement refund;
		
		//Get the refund message content
		@FindBy(xpath = "//div[@id='refundmessagecontent']")
		@CacheLookup
		public WebElement txtrefundmsg;
		
		//Locate the "Transactions" table
		@FindBy(xpath=("//table[@class='table-Colorful color-3 stacktable large-only']"))
		@CacheLookup
		WebElement transTable;


		
			//================================================================
	
	public int getpagerlinkssize()
	{
		return pagerlinkstxn.size();
	}
	public int gethelplinkssize()
	{
		return txnhelplinks.size();
	}
	
	public boolean displaysbtnStart()
	{
		return btnStart.isDisplayed();
	}
	
	public void clkbtnStart()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnStart));
		wait.until(ExpectedConditions.elementToBeClickable(btnStart));
		btnStart.click();
	}
	
	public boolean displaysbtnEnd()
	{
		return btnEnd.isDisplayed();
	}
	
	public void clkbtnEnd()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnEnd));
		wait.until(ExpectedConditions.elementToBeClickable(btnEnd));
		btnEnd.click();
	}
	
	public boolean displaysbtnPrevious()
	{
		return btnPrevious.isDisplayed();
	}
		
	public void clkbtnPrevious()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnPrevious));
		wait.until(ExpectedConditions.elementToBeClickable(btnPrevious));
		btnPrevious.click();
	}
	
	public boolean displaysbtnNext()
	{
		return btnNext.isDisplayed();
	}
	
	public void clkbtnNext()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnNext));
		wait.until(ExpectedConditions.elementToBeClickable(btnNext));
		btnNext.click();
	}
	
	public int getTablerowscount() {
			return txtTablerows.size();
		}

	public String gettxtnotransaction()
	{
		return txtNoTransaction.getText();
	}

	// Click and hold to see the tool tip box message
	public void clkandholdflagtooltip()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(clkflagtooltip));
		Actions actions = new Actions(ldriver);
		actions.moveToElement(clkflagtooltip);
		actions.build().perform();
	}
	
	public boolean isComplaintMessageDisplayed()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(complaintmessage));
		return complaintmessage.isDisplayed();
		
	}	
	public void clkflagtooltip()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.elementToBeClickable(clkflagtooltip));
		clkflagtooltip.click();
	}
	
	// Click and hold to see the dollar tool tip message
	public void clkandholddollartooltip()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(clkdollartooltip));
		Actions actions = new Actions(ldriver);
		actions.clickAndHold(clkdollartooltip);
		actions.build().perform();
	}

	public boolean isRefundMessageDisplayed()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(refundmessage));
		return refundmessage.isDisplayed();
	}
	public void clkdollartooltip()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.elementToBeClickable(clkdollartooltip));
		clkdollartooltip.click();
	}
	public boolean isRefundServicePopupDisplayed()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(refundpopup));
		return refundpopup.isDisplayed();
	}

	public void clkCancelButton()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.elementToBeClickable(refundpopup));
		clkcancelbutton.click();
	}
	public boolean isTransHistoryPageDisplayed()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(transhistorypage));
		return transhistorypage.isDisplayed();
	}
	public void clkIwantarefundButton()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.elementToBeClickable(refundpopup));
		clkIwantarefundbutton.click();
	}
	public boolean isConfirmRefundMessageDisplayed()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(cfmrefundmessage));
		return cfmrefundmessage.isDisplayed();
	}
	public boolean isRefundIconNotDisplayed()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(chkdollartooltip));
		return chkdollartooltip.isEnabled();
	}

	public void clkRefund()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.elementToBeClickable(clkdollartooltip));
		clkdollartooltip.click();
	}

			 
	public void clkTransactionsLegend() {
		Actions act= new Actions(ldriver);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].scrollIntoView()",clktranslegend);
		act.click(clktranslegend);
		act.build().perform();
	}
			
	public boolean isTransactionsLegendDisplayed(){
		return cfmtranslegend.isDisplayed();
	}		
	public void clkAskforaRefundLegend() {
		Actions act= new Actions(ldriver);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].scrollIntoView()",clkaskforarefundlegend);
		act.click(clkaskforarefundlegend);
		act.build().perform();
	}
			
	public boolean isAskforaRefundLegendDisplayed(){
		return cfmaskforarefundlegend.isDisplayed();
	}	
	public void clkFileaComplaintLegend() {
		Actions act= new Actions(ldriver);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].scrollIntoView()",clkfileacomplaintlegend);
		act.click(clkfileacomplaintlegend);
		act.build().perform();
	}
			
	public boolean isFileaComplaintDisplayed(){
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(cfmfileacomplaintlegend));
		return cfmfileacomplaintlegend.isDisplayed();
	}	

	//Return integer value of Transaction eeds from the transaction table
	public int gettransAmt()
	{
		String transamt1 = txttransAmt.getText();
		String sign = transamt1.substring(0, 1);
		int transamt = Integer.parseInt(transamt1.substring(2)); 
			
			if(sign.equals("-"))
			{
				return (-1 * transamt);
			}
			else
			{
				return transamt;
			}
	}
	
	//Return integer value of Transaction eeds from the transaction table
		public int gettransAmt_zero()
		{
			String transamt1 = txttransAmt.getText();
			int transamt = Integer.parseInt(transamt1); 
			return transamt;
		
		}
	
	
	//Get the service name from the transaction table
	public String gettxtservicename()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtservicename));
		return txtservicename.getText();
	}
	
	//Click on service name
	public void clktxtservicename()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtservicename));
		wait.until(ExpectedConditions.elementToBeClickable(txtservicename));
		txtservicename.click();
	}
	
	//Get the first entry (if not interactive entry) from the transaction table
	public String gettxtfirstentry()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtfirstentry));
		return txtfirstentry.getText();
	}
	
	// counting actions available under actions tab
		public int countActions() {
			return Actions.size();
		}

		public void clkbtnrefund()
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(refund));
			wait.until(ExpectedConditions.elementToBeClickable(refund));
			refund.click();
		}
		
		public String gettxtrefundmsg()
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(txtrefundmsg));
			return txtrefundmsg.getText();
			
		}
		
}
