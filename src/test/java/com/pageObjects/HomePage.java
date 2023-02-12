package com.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	public WebDriver ldriver;

	// Constructor

	public HomePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	By clkWelcome = By.xpath("//span[contains(text(),'Hi')]");

	public WebElement actWelcome() {
		return ldriver.findElement(clkWelcome);
	}

	By clkProfile = By.xpath("//span[contains(text(),'Profile')]");

	public WebElement actProfile() {
		return ldriver.findElement(clkProfile);
	}

	By clkSignOut = By.xpath("//span[contains(text(),'Sign Out')]");

	public WebElement actSignout() {
		return ldriver.findElement(clkSignOut);
	}
	
	By clkHelp = By.xpath("//span[contains(text(),'Help')]");
	
	public WebElement actHelp() {
		return ldriver.findElement(clkHelp);
	}
	
	By clkSettings = By.xpath("//span[contains(text(),'Settings')]");
	public WebElement actSettings() {
		return ldriver.findElement(clkSettings);
	}
	//Set text on search box
	@FindBy(name = "q")
	@CacheLookup
	WebElement txtsearch;
	
	//Capture
	@FindBy(xpath= "//div[2]//p[1]/strong")
	@CacheLookup
	WebElement clksearchedtxt;
		
	// Click on twoPlugs logo
	@FindBy(xpath = "//a[@class='navbar-brand']//img")
	@CacheLookup
	WebElement btnlogo;

	// Click on search button
	@FindBy(xpath = "//button[@class='btn-search']")
	@CacheLookup
	WebElement btnSearch;

	// Click on Plugs tab
	@FindBy(xpath = "//span[contains(text(),'Plugs')]")
	@CacheLookup
	WebElement btnPlugs;

	// Click on Transaction history tab
	@FindBy(xpath = "//span[contains(text(),'Transactions')]")
	@CacheLookup
	WebElement btnTransHist;

	// Click on Message tab
	@FindBy(xpath = "//span[contains(text(),'Messages')]")
	@CacheLookup
	WebElement btnMessages;

	// Get no of users following
	@FindBy(xpath = "//ul[@class='info clearfix pull-left']//li")
	@CacheLookup
	WebElement txtfollowingNos;

	// Capture following number
	@FindBy(id = "followingsNumber")
	@CacheLookup
	WebElement followingNos;

	// Capture list of avatars following
	@FindBy(xpath = "//div[@class='img']")
	@CacheLookup
	List<WebElement> avatarFollowing;

	// Get no of followers
	@FindBy(xpath = "//ul[@class='info clearfix pull-left']//li[2]")
	@CacheLookup
	WebElement txtfollowerNos;

	// Capture follower number
	@FindBy(id = "followersNumber")
	@CacheLookup
	WebElement followerNos;

	// Capture list of avatars of followers
	@FindBy(xpath = "//div[@class='img']")
	@CacheLookup
	List<WebElement> avatarFollowers;

	// Pending Transactions - More Button
	@FindBy(xpath = "/html/body/div[7]/div/div[1]/div[1]/div[1]/div[1]/div/a")
	@CacheLookup
	WebElement PendingTranMoreBtn;

	// Counting the number of rows on Pending Transaction Table
	@FindBy(xpath = "/html/body/div[7]/div/div/div/div/div[2]/div[1]/table/tbody/tr/td[1]")
	@CacheLookup
	List<WebElement> PendingTransTable;

	// Live Update in My Network - More Button
	@FindBy(xpath = "/html/body/div[7]/div/div[1]/div[2]/div/div/div/a")
	@CacheLookup
	WebElement LiveUpdNetworkMoreBtn;

	// Getting the number of rows on Live Updates in My Network Table
	@FindBy(xpath = "/html/body/div[7]/div/div/div/div/table/tbody/tr/td[1]")
	@CacheLookup
	List<WebElement> LiveUpdNetworkTable;

	// Clicking the Live Update from My Users More icon
	@FindBy(xpath = "/html/body/div[7]/div/div[1]/div[1]/div[2]/div[1]/div/a")
	@CacheLookup
	WebElement LiveUpdFromUserMoreBtn;

	// Getting the number of rows on Live Updates from Users Table
	@FindBy(xpath = "/html/body/div[7]/div/div/div/div/table/tbody/tr/td[1]")
	@CacheLookup
	List<WebElement> LiveUpdFromUserTable;

	// Getting the number of rows on the Top 5 services
	@FindBy(xpath = "/html/body/div[7]/div/div[2]/div[1]/div/table/tbody/tr/td[1]")
	@CacheLookup
	List<WebElement> Top5Services;

	// Getting the number of rows on the Top 5 Traders
	@FindBy(xpath = "html/body/div[7]/div/div[2]/div[2]/div/table/tbody/tr/td[1]")
	@CacheLookup
	List<WebElement> Top5Traders;
	
	//Get the unread message numbers from Menu Bar on HomePage
	@FindBy (xpath = "//span[@class='counter']")
	@CacheLookup
	WebElement txtunreadmsg;
	
	// Taking value of "Profile-information" for time stamp verification
	@FindBy(xpath="//div[7]/section[1]/div[1]/div[1]/div[2]/ul[1]/li[2]/span[1]")
	@CacheLookup
	WebElement timeDisplay;

	
	
	// ============================================================================//
	public void clkbtnLogo() {
		btnlogo.click();
	}

	public void clkbtnSearch() {
		btnSearch.click();
	}

	public void clkbtnPlugs() {
		btnPlugs.click();
	}

	public void clkbtnTransHist() {
		btnTransHist.click();
	}

	//Search the user/service/need
		public void settxtsearch(String value)
		{
			txtsearch.sendKeys(value);
		}
		
		//Click on searched text
		public void clksearchedtxt()
		{
			clksearchedtxt.click();
		}
	
	public void clkbtnMessages() {
		//WebDriverWait wait = new WebDriverWait(ldriver, 45);
		//WebElement followingtab = wait.until(ExpectedConditions.visibilityOf(btnMessages));
		btnMessages.click();
	}

	public String gettxtfollowingNo() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
		WebElement followingtab = wait.until(ExpectedConditions.visibilityOf(txtfollowingNos));
		return followingtab.getText();

	}

	public void clkFollowingNos() {
		followingNos.click();
	}

	public int getavatarfollowingsize() {
		return avatarFollowing.size();
	}

	public String gettxtfollowerNo() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
		WebElement followertab = wait.until(ExpectedConditions.visibilityOf(txtfollowerNos));
		return followertab.getText();
	}

	public void clkFollowerNos() {
		followerNos.click();
	}

	public int getavatarfollowersize() {
		return avatarFollowers.size();
	}

	public void hoverPlugsIcon() {
		Actions actions = new Actions(ldriver);
		actions.moveToElement(btnPlugs).build().perform();

	}

	public void hoverTransactionIcon() {
		Actions actions = new Actions(ldriver);
		actions.moveToElement(btnTransHist).build().perform();

	}

	public void hoverMessagesIcon() {
		Actions actions = new Actions(ldriver);
		actions.moveToElement(btnMessages).build().perform();
	}

	public void hoverHiUserIcon() {
		Actions actions = new Actions(ldriver);
		actions.moveToElement(actWelcome()).build().perform();
	}

	public String beforehoverPlugsColor() {
		String color = btnPlugs.getCssValue("color");
		return color;
	}

	public String afterhoverPlugsColor() {
		String color = btnPlugs.getCssValue("color");
		return color;
	}

	public String beforehoverTransactionColor() {
		String color = btnTransHist.getCssValue("color");
		return color;
	}

	public String afterhoverTransactionColor() {
		String color = btnTransHist.getCssValue("color");
		return color;
	}

	public String beforehoverMessagesColor() {
		String color = btnMessages.getCssValue("color");
		return color;
	}

	public String afterhoverMessagesColor() {
		String color = btnMessages.getCssValue("color");
		return color;
	}

	public String beforehoverHiUserColor() {
		String color = actWelcome().getCssValue("color");
		return color;
	}

	public String afterhoverHiUserColor() {
		String color = actWelcome().getCssValue("color");
		return color;
	}

	public void pendingTransMoreBtn() {
		PendingTranMoreBtn.click();
	}

	public int PendingTranRows() {
		int row = PendingTransTable.size();
		return row;
	}

	public void liveUpdNetworkMoreBtn() {
		LiveUpdNetworkMoreBtn.click();
	}

	public void liveUpdFromUserMoreBtn() {
		LiveUpdFromUserMoreBtn.click();
	}

	public int LiveUpdNetworkRows() {
		int row = LiveUpdNetworkTable.size();
		return row;
	}

	public int LiveUpdFromUserRows() {
		int row = LiveUpdFromUserTable.size();
		return row;
	}

	public int Top5ServicesRows() {
		int row = Top5Services.size();
		return row;
	}

	public int TopTraderRows() {
		int row = Top5Traders.size();
		return row;
	}
	
	public String gettxtunreadmsg() {
		return txtunreadmsg.getText();
	}
	
	// Get-text value of current time and place
	public void timeDisplay()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(timeDisplay));
		timeDisplay.getText();
		//logger.info("time from timedisplay() " + timeDisplay.getText());
	}

}