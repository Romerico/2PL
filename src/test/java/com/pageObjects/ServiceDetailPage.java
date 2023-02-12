package com.pageObjects;



import java.time.Duration;
import java.util.List;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ServiceDetailPage extends BaseClass {
public WebDriver ldriver;
	
	//Constructor
	
	public ServiceDetailPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	//Capture the modal popup text while adding the service
	@FindBy(xpath="//div[contains(text(),'Service has been added')]")
	@CacheLookup
	WebElement txtaddSvccaption;

	//Capture the modal popup text while deleting the service
	@FindBy(xpath="//div[contains(text(),'Service has been deleted')]")
	@CacheLookup
	WebElement txtdelSvccaption;
	
	//Get the username from the Service detail page 
	@FindBy (xpath = "//table[@class='table-Colorful dual-centered']/tbody/tr[1]/td[2]/a")
	@CacheLookup
	WebElement txtusername;
	
	//Get the service name from the service detail page
	@FindBy (xpath = "//div[@class='caption'][1]")
	@CacheLookup
	WebElement txtservicename;
	
	//Locate the three link icon 
	@FindBy(xpath="//span[@class='w-icons-fork green']")
	@CacheLookup
	public WebElement threeLinkBtn;

	//Locate sharing options
	@FindBy(xpath="/html/body/div[7]/section/div/div[2]/div[2]/div[1]/div/div/div/ul/li/a")
	@CacheLookup
	public List<WebElement> shareServiceOptions;
	
	//Locate facebook element
	@FindBy(xpath="//span[@class='w-icons-fork face']")
	@CacheLookup
	public WebElement fbShare;

	//Locate twitter element
	@FindBy(xpath="//span[@class='w-icons-fork tweet']")
	@CacheLookup
	public WebElement twitterShare;

	//Locate the rows in the table in the "Reviews" section
	@FindBy(xpath="//table[@class='table-Colorful special']//tbody/tr")
	@CacheLookup
	List<WebElement> reviewRows;

	//Locate the table in the "Reviews" section
	@FindBy(xpath="//table[@class='table-Colorful special']")
	@CacheLookup
	WebElement reviewTable;

	@FindBy(xpath="//input[@id='autocomplete']")
	WebElement TextBoxLocation;
	
	@FindBy(xpath="//button[@class='btn btn-filter']")
	WebElement ClickSearch;
	
	@FindBy(xpath="//table[@class='result-table']/tbody/tr[1]/td/div/div[2]/div[1]/a[1]")
	WebElement TableFirstEntry;
	
	@FindBy(xpath="//table[@class='table-Colorful dual-centered']/tbody/tr[7]/td[2]")
	WebElement NetworkTextComp;

	
	
	//===================================================================//
	public String gettxtaddSvccaption()
	{
		return txtaddSvccaption.getText();
	}

	public String gettxtdelSvccaption()
	{
		return txtdelSvccaption.getText();
	}

	//Click the username on the service detail page
	public void clktxtusername()
	{
		txtusername.click();
	}
	
	//Get the service name from the service detail page
	public String gettxtservicename()
	{
		return txtservicename.getText();
	}
	
	//Click on three icon link to open share services
	public void clickThreeLinkIcon()
	{
		threeLinkBtn.click();
	}

	//Get the number of sharing options
	public int getShareServicesOptions()
	{
		return shareServiceOptions.size();
		
	}

	// Click on fb sharing option
	public void clickFB()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(fbShare));
		wait.until(ExpectedConditions.elementToBeClickable(fbShare));
		fbShare.click();
	}
	// Click on twitter sharing option
	public void clickTwitter()
	{
		twitterShare.click();
	}

	//Method to get the number of rows in the "Reviews" table
		public int getNoOfReviewRows()
		{
			return (reviewRows.size());
		}
	
	//Method to verify that the Review table contains the required user, comment & number of stars
	public boolean verifyReview(String user, String comment, int numStars)
	{
	//	boolean ratingUserPresent = false;
	//	boolean ratingCommentPresent = false;
		boolean ratingStarsPresent = false;
		boolean rating3ElementsPresent = false;
		
		for(int i=1; i<=getNoOfReviewRows(); i++)
		{
			WebElement revUser = reviewTable.findElement(By.xpath("//table[@class='table-Colorful special']//tbody/tr[" + i + "]/td[1]/a[1]"));
			WebElement revComm = reviewTable.findElement(By.xpath("//table[@class='table-Colorful special']//tbody/tr[" + i + "]/td[2]"));
			WebElement ratingStars = reviewTable.findElement(By.xpath("//table[@class='table-Colorful special']//tbody/tr[" + i + "]/td[3]/div/div/img["+ numStars + "]"));

			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(revUser));
			String revUserStr = revUser.getText();
			
			wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(revComm));
			String revCommStr = revComm.getText();
			
			wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(ratingStars));
			ratingStarsPresent = ratingStars.isDisplayed();
						
			if((revUserStr.equals(user))&&(revCommStr.equals(comment))&&(ratingStarsPresent==true))
			{
				logger.info("Expected Review User: " + revUserStr + " " + "Actual Review User: " + user);
				logger.info("Expected Review Comment: " + revCommStr + " " + "Actual Review Comment: " + comment);
				logger.info("Expected Rating Stars: " + numStars + " " + "Actual Review Stars: " + ratingStarsPresent);

				rating3ElementsPresent = true;
				break;
			}
		}
		return rating3ElementsPresent;
	}
	
	public void locationsetting(String locname) throws InterruptedException{
		
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(TextBoxLocation));
		TextBoxLocation.sendKeys(locname);//Sending the name of the location
	}

	public void searchClick(){
		ClickSearch.click();//Clicking on the Search Button
	
	}
	
	public void firstEntryTableResult(){
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(TableFirstEntry));
		TableFirstEntry.click();//Clicking on the first link of the search result
	}

	public String getTextNetworkComp(){
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(NetworkTextComp));	
		return NetworkTextComp.getText();//Getting the text of Network to compare with the actual value
	}

	
}
