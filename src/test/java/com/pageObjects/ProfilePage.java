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

public class ProfilePage extends BaseClass {
	public WebDriver ldriver;

	// Constructor

	public ProfilePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// Capture gender
	@FindBy(xpath = "//ul[@class='profile clearfix']/li[3]")
	@CacheLookup
	WebElement gender;

	// Capture Female Avatar Image
	@FindBy(xpath = "//img[@src='/profileimage/tpfemale.png']")
	@CacheLookup
	WebElement imgFemale;

	// Capture Male Avatar Image
	@FindBy(xpath = "//img[@src='/profileimage/tpmale.png']")
	@CacheLookup
	WebElement imgMale;
	
	//Capture Undefined Avatar Image
	@FindBy(xpath = "//li[contains(text(),'Undefined')]")
	@CacheLookup
	WebElement imgUndefined;
	
	//Capture Delete service 
	@FindBy (xpath = "//tr[1]//td[4]//ul[1]//li[2]//a[1]//span[1]")
	@CacheLookup
	WebElement btndeleteService;
	
	//Capture 'I want to Delete' button (confirmation for deleting service)
	@FindBy(xpath = "//span[contains(text(),'I want to delete')]")
	@CacheLookup
	WebElement btnIwantToDelete;
	
	//Capture Delete need
	@FindBy (xpath = "//tr[1]//td[2]//ul[1]//li[2]//a[1]//span[1]")
	@CacheLookup
	WebElement btndeleteNeed;
	
	//Capture ''I want to Delete' button (confirmation for deleting Need)
	@FindBy(xpath = "//a[@id='btn_deleteService']")
	@CacheLookup
	WebElement btnIwantToDeleteNeed;
	
	//Get the Total Balance 
	@FindBy(xpath="//ul[@class='status-bar pull-right']/li[3]/div[2]")
	@CacheLookup
	WebElement totalbalance;
	
	//Capture the text after Deleting the service
	@FindBy(xpath = "//div[contains(text(),'Service has been deleted')]")
	@CacheLookup
	WebElement txtServiceDeleted;

	//Capture the latest service (i.e. first service from the top) in the Service table
	@FindBy(xpath = "//div[@class='box theme-2']//tr[1]//td[1]//div[1]//a[1]")
	@CacheLookup
	WebElement txtlatestsvc;
	
	//Capture the latest need (i.e. first need from the top) in the Needs table
	@FindBy(xpath = "//div[@class='box theme-1']//tr[1]//td[1]//div[1]//a[1]")
	@CacheLookup
	WebElement txtlatestneed;
	
	//Locate the "Services Bought" field
	@FindBy(xpath="/html/body//li[2]//div[2]")
	@CacheLookup
	WebElement servicesBought;

	//Locate the "Number of Active Needs" in the "Needs" table
	@FindBy(xpath="//div[1]/div[1]/div/div/span[@class='badge pull-right h-tooltip']")
	@CacheLookup
	WebElement noOfActiveNeeds;

	//Locate the "Needs" table rows
	@FindBy(xpath="//div[@class='box theme-1']//table//tbody//tr")
	@CacheLookup
	List<WebElement> needtblRows;
	
	//Locate the "Needs" table on the "Profile" page
	@FindBy(xpath="//div[@class='box theme-1']")
	@CacheLookup
	WebElement needTable;

	//Locate the "Number of Active Services" in the "Services" table
	@FindBy(xpath="//div[2]/div[1]/div/div/span[@class='badge pull-right h-tooltip']")
	@CacheLookup
	WebElement noOfActiveServices;

	//Locate the "Services" table on the "Profile" page	
	@FindBy(xpath="//div[@class='box theme-2']")
	@CacheLookup
	WebElement serviceTable;

	//Locate the "Services" table rows
		@FindBy(xpath="//div[@class='box theme-2']//table//tbody//tr")
		@CacheLookup
		List<WebElement> servicetblRows;

		//Locate the "Total Balance" field
		@FindBy(xpath=("/html/body//ul[2]/li[3]//div[2]/div"))
		@CacheLookup
		WebElement totalBalance;

		//Capture the send eeds button
		@FindBy(xpath="//span[@class='w-icons-profileCtrl2']")
		@CacheLookup
		WebElement btnsendeeds;
		
		//Locate the message in "About" field on the Profile Page
		@FindBy(xpath="//div[@id='bio_description']/p")
		@CacheLookup
		WebElement actualBioDescription;
		
		//Capture following no.
		@FindBy(xpath="//ul[@class='info clearfix pull-left']//li[1]")
		@CacheLookup
		WebElement txtfollowingno;
		
		//locating following number link
		@FindBy(id="followingsNumber")
		@CacheLookup
		WebElement lnkfollowingNo;

		//number of users I follow
		@FindBy(xpath="//div[@class='container mobile-grid-table']/ul/li")
		@CacheLookup
		List<WebElement> userIFollow;

		//locating user I follow page
		@FindBy(xpath="//span[contains(text(),'Users I Follow')]")
		@CacheLookup
		public WebElement usersIFollowPage;
		
		//locating landing profile name
		@FindBy(xpath="//div[@class='name']")
		@CacheLookup
		WebElement landingProfile;

			
	//==================================================================================//
	

	public String captureGender() {
		return gender.getText();
	}

	public boolean femaleAvatar() {
		return imgFemale.isDisplayed();

	}

	public boolean maleAvatar() {
		return imgMale.isDisplayed();
	}

	public boolean undefinedAvatar() {
		return imgUndefined.isDisplayed();
	}

	public void clkbtnDeleteService() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btndeleteService));
		wait.until(ExpectedConditions.elementToBeClickable(btndeleteService));
		btndeleteService.click();
	}
	public void clkbtnIwantToDelete() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnIwantToDelete));
		wait.until(ExpectedConditions.elementToBeClickable(btnIwantToDelete));
		btnIwantToDelete.click();
	}
	
	public void clkbtnDeleteNeed() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btndeleteNeed));
		wait.until(ExpectedConditions.elementToBeClickable(btndeleteNeed));
		btndeleteNeed.click();
	}
	public void clkbtnIwantToDeleteNeed() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnIwantToDeleteNeed));
		wait.until(ExpectedConditions.elementToBeClickable(btnIwantToDeleteNeed));
		btnIwantToDeleteNeed.click();
	}

	public int getTotalBalance() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(totalbalance));
		String total = totalbalance.getText();
		String[] str_array = total.split("\\s+");
		String numberoftrans = str_array[0]; 
		String eeds = str_array[1];
		logger.info(numberoftrans);
		logger.info(eeds);			
		int totaltrans = Integer.parseInt(numberoftrans);
		return totaltrans ;
	}		

	//Get the text message from the service deleted confirmation message
	public String gettxtServiceDeleted()
	{
		return txtServiceDeleted.getText();
	}

	//Click on the latest service (i.e. first service from the top) in the Service table to view the service details
	public void clktxtlatestsvc() {
	WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
	wait.until(ExpectedConditions.visibilityOf(txtlatestsvc));
	wait.until(ExpectedConditions.elementToBeClickable(txtlatestsvc));
	txtlatestsvc.click();
	}
	
	//Get the text from the latest service (i.e. first service from the top) in the Service table to view the service details
		public String gettxtlatestsvc() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtlatestsvc));
		wait.until(ExpectedConditions.elementToBeClickable(txtlatestsvc));
		return txtlatestsvc.getText();
		}
	
	//Click on the latest need (i.e. first need from the top) in the Needs table to view the Needs details
		public void clktxtlatestneed() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtlatestneed));
		wait.until(ExpectedConditions.elementToBeClickable(txtlatestneed));
		txtlatestneed.click();
		}

		//Get the text from the latest need (i.e. first need from the top) in the Needs table to view the Needs details
				public String gettxtlatestneed() {
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(txtlatestneed));
				wait.until(ExpectedConditions.elementToBeClickable(txtlatestneed));
				return txtlatestneed.getText();
				}
				
		//Method to return the "Number of Services Bought"
		public int printServiceBought()
		{
			return Integer.parseInt(servicesBought.getText());
		}

		//Method to return the "Number of Active Needs"
		public int getNoOfActiveNeeds()
		{
			String needs = noOfActiveNeeds.getText();
			return Integer.parseInt(needs);
		}
		
		//Method to check if the "Number of Active Needs" is displayed
		public boolean noOfActiveNeedsDisplayed()
		{
			return noOfActiveNeeds.isDisplayed();
		}
		
		//Method to return the number of rows in the "Needs" table
		public int getNoOfNTRows()
		{
			return (needtblRows.size());
		}
		
		
		//Method to verify if the required need displays the required icons in the "Needs" table
		public boolean verifyNeedsTableIcons(String needName)
		{
			boolean iconsPresent = false;
			
			for(int i=1; i<=getNoOfNTRows(); i++)
			{
				String nName = needTable.findElement(By.xpath("//div[@class='box theme-1']//table//tbody/tr[" + i + "]/td[1]")).getText();
				WebElement editIcon = needTable.findElement(By.xpath("//div[@class='box theme-1']//table//tbody/tr[" + i + "]/td[2]//span[@class='w-icons-edit']"));
				WebElement deleteIcon = needTable.findElement(By.xpath("//div[@class='box theme-1']//table//tbody/tr[" + i + "]/td[2]//span[@class='w-icons-delete']"));
							
				if(nName.equals(needName.toUpperCase()))
				{
					if(editIcon.isDisplayed()&&deleteIcon.isDisplayed())
					{
						iconsPresent = true;
						logger.info("Need Edit Icon: " + editIcon.isDisplayed());
						logger.info("Need Delete Icon: " + deleteIcon.isDisplayed());
						logger.info("NeedName local variable: " + nName);
					}
					else
					{
					iconsPresent = false;
					}
					break;
				}
			}
			return iconsPresent;
		}
		
		//Method to check if the "Number of Active Services" is displayed
		public boolean noOfActiveServicesDisplayed()
		{
			return noOfActiveServices.isDisplayed();
		}
	
		//Method to return the "Number of Active Services"
		public int getNoOfActiveServices()
		{
			String services = noOfActiveServices.getText();
			return Integer.parseInt(services);
		}
		
		//Method to return the number of rows in the "Services" table
		public int getNoOfSTRows()
		{
			return (servicetblRows.size());
		}
		
				
		//Method to verify if the required service displays the required icons in the "Services" table
		public boolean verifyServicesTableIcons(String serviceName)
		{
			boolean iconsPresent = false;
			
			for(int i=1; i<=getNoOfSTRows(); i++)
			{
				String sName = serviceTable.findElement(By.xpath("//div[@class='box theme-2']//table//tbody/tr[" + i + "]/td[1]")).getText();
				WebElement ratingStars = serviceTable.findElement(By.xpath("//div[@class='box theme-2']//table//tbody/tr[" + i + "]/td[2]/div[1]/div[1]"));
				WebElement eeds = serviceTable.findElement(By.xpath("//div[@class='box theme-2']//table//tbody/tr[" + i + "]/td[3]/div[1]/div[1]"));
				WebElement editIcon = serviceTable.findElement(By.xpath("//div[@class='box theme-2']//table//tbody/tr[" + i + "]/td[4]//span[@class='w-icons-edit']"));
				WebElement deleteIcon = serviceTable.findElement(By.xpath("//div[@class='box theme-2']//table//tbody/tr[" + i + "]/td[4]//span[@class='w-icons-delete']"));
							
				if(sName.equals(serviceName.toUpperCase()))
				{
					if(ratingStars.isDisplayed()&&eeds.isDisplayed()&&editIcon.isDisplayed()&&deleteIcon.isDisplayed())
					{
						iconsPresent = true;
						logger.info("Service RatingStars Icon: " + ratingStars.isDisplayed());
						logger.info("Service Eeds Amount Icon: " + eeds.isDisplayed());
						logger.info("Service Edit Icon: " + editIcon.isDisplayed());
						logger.info("Service Delete Icon: " + deleteIcon.isDisplayed());
						logger.info("ServiceName local variable: " + sName);
					}
					else
					{
					iconsPresent = false;
					}
					break;
				}
			}		
			return iconsPresent;		
		}
		
		//Method to return the "Total Balance"
		public int printTotalBalance()
		{
			return Integer.parseInt(totalBalance.getText());
		}

		//Click on the send eeds button
		public void clkbtnsendeeds()
		{
			btnsendeeds.click();
		}

		//Method to return the "About" field text, on the "Profile" page
		public String actualBioDescription()
		{
			return actualBioDescription.getText();
		}

		//Get the text from the following no
		public String gettxtfollowingno()
		{
			return txtfollowingno.getText();
		}
		
		// method to click on Following No. link
		public void clickfollowingNo() {
		lnkfollowingNo.click();
		}
		
		//method to get count of users following
		public int getFollowingCount() {
			return userIFollow.size();
		}
		
		//method to get name of profile page user landing
		public String getLandingProfileNme() {
			return landingProfile.getAttribute("innerText");
		}

}

