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

public class PlugsPage {
public WebDriver ldriver;
	
	//Constructor
	
	public PlugsPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	

	@FindBy (xpath ="//ul[@class='pagination clearfix']/li/a/span")
	@CacheLookup
	List <WebElement> pagerlinksplugs;
	
	@FindBy( xpath = "//li[@class='help']")
	@CacheLookup
	List <WebElement> plughelplinks;
	
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
	
	// Click on Services button
		@FindBy(xpath = "//ul[@class='menu-list clearfix']/li[2]")
		@CacheLookup
		WebElement Services;

		// Click on Needs button
		@FindBy(xpath = "//a[contains(text(),'Needs')]")
		@CacheLookup
		WebElement Needs;

		// Search Input box
		@FindBy(xpath = "//input[@id='searchInput']")
		WebElement SearchInput;
		
		// Search button
		@FindBy(xpath = "//span[contains(text(),'SEARCH')]")
		WebElement SearchButton;

		// Search location box
		@FindBy(xpath="//input[@id='autocomplete']")
		WebElement LocationInput;

		//Services and Needs Created/Updated date and time
		@FindBy(xpath="//table[@class='result-table']//tr[1]//div[2]//span")
		WebElement CreatedUpdatedTime;
		
		//Sort By arrow - Services
		@FindBy(xpath = "//div[@class='pull-right']//div[@class='jq-selectbox__trigger']")
		WebElement ServicesSortByArrow;

		// Click on Price on  DropDown menu
		@FindBy(xpath = "//li[contains(text(),'Price')]")
		WebElement Price;

		// Click on Recently Updated on DropDown menu
		@FindBy(xpath = "//li[contains(text(),'Recently Updated')]")
		WebElement RecentlyUpdated;

		// Click on Highest Rating on DropDown menu
		@FindBy(xpath = "//li[contains(text(),'Highest Rating')]")
		WebElement HighestRating;


		// Click on Name on DropDown menu
		@FindBy(xpath = "//li[contains(text(),'Name')]")
		WebElement Name;
		
		//Sort by arrow - Needs
		// Click on Sort By arrow
		@FindBy(xpath = "//div[@class='pull-right']//div[@class='jq-selectbox__trigger-arrow']")
		WebElement NeedsSortByArrow;

		//Click on the first available service/need
		@FindBy(xpath = "//tr[1]//td[1]//div[1]//div[2]//div[1]//a[1]")
		@CacheLookup
		WebElement Plugsfirstentry;
		
		

		
	//===============================================================//
	public int getpagerlinkssize()
	{
		return pagerlinksplugs.size();
	}
	
	public int gethelplinkssize()
	{
		return plughelplinks.size();
	}
	
	public void clkbtnStart()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnStart));
		wait.until(ExpectedConditions.elementToBeClickable(btnStart));
		btnStart.click();
	}
	public void clkbtnEnd()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnEnd));
		wait.until(ExpectedConditions.elementToBeClickable(btnEnd));
		btnEnd.click();
	}
	public void clkbtnPrevious()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnPrevious));
		wait.until(ExpectedConditions.elementToBeClickable(btnPrevious));
		btnPrevious.click();
	}
	public void clkbtnNext()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnNext));
		wait.until(ExpectedConditions.elementToBeClickable(btnNext));
		btnNext.click();
	}
	
	public void ClickServicesBtn() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(Services));
		wait.until(ExpectedConditions.elementToBeClickable(Services));
		Services.click();
	}
	
	public void ClickNeedsBtn() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(Needs));
		wait.until(ExpectedConditions.elementToBeClickable(Needs));
		Needs.click();
	}
	
	public void SetSearch(String search) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(SearchInput));
		Actions actions = new Actions(ldriver);
		// Clearing text field 
		actions.click(SearchInput)
		    .keyDown(Keys.CONTROL)
		    .sendKeys("a")
		    .keyUp(Keys.CONTROL)
		    .sendKeys(Keys.BACK_SPACE)
		    .click()
		    .sendKeys(search)
		    .build()
		    .perform();
	}

	public void ClickSearchBtn() {
		SearchButton.click();
	}

	public void SetLocation(String location) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(LocationInput));
		Actions actions = new Actions(ldriver);
		// Clearing text field 
		actions.click(LocationInput)
		    .keyDown(Keys.CONTROL)
		    .sendKeys("a")
		    .keyUp(Keys.CONTROL)
		    .sendKeys(Keys.BACK_SPACE)
		    .click()
		    .sendKeys(location)
		    .build()
		    .perform();
	}

	public String UpdatedDateTime() {
		String DateTime = CreatedUpdatedTime.getText();
		return DateTime; 
	}

	public void ClickServicesSortByArrow() {
		Actions actions = new Actions(ldriver);
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(ServicesSortByArrow));
		actions.moveToElement(ServicesSortByArrow);
		actions.build().perform();
		ServicesSortByArrow.click();
	}
	
	public void drpdownsortbyPrice() {
		Actions actions = new Actions(ldriver);
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(Price));
		actions.moveToElement(Price);
		actions.build().perform();
		Price.click();
	}
	
	public void drpdownsortbyDate() {
		Actions actions = new Actions(ldriver);
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(RecentlyUpdated));
		actions.moveToElement(RecentlyUpdated);
		actions.build().perform();
		RecentlyUpdated.click();
	}
	
	public void drpdownsortbyRating() {
		Actions actions = new Actions(ldriver);
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(HighestRating));
		actions.moveToElement(HighestRating);
		actions.build().perform();
		HighestRating.click();
	}
	
	public void drpdownsortbyName() {
		Actions actions = new Actions(ldriver);
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(Name));
		actions.moveToElement(Name);
		actions.build().perform();
		Name.click();
	}

	public void ClickNeedSortByArrow() {
		Actions actions = new Actions(ldriver);
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(NeedsSortByArrow));
		actions.moveToElement(NeedsSortByArrow);
		actions.build().perform();
		NeedsSortByArrow.click();
	}
	
	public void clkPlugsfirstentry()
	{
		Plugsfirstentry.click();
	}
	
}
