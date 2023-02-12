package com.pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FbPage {
public WebDriver ldriver;
	
	//Constructor
	
	public FbPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy (xpath="//a[@class='ytp-share-panel-service-button ytp-button']")
	@CacheLookup
	WebElement facebook;//

	@FindBy (xpath="//a[@aria-label='Twitter']")
	@CacheLookup
	WebElement tweeter;
	
	@FindBy (xpath="//iframe[@class='fancybox-iframe']")
	@CacheLookup
	WebElement newFrame;

	//==========================================================================//
	
	public String fbTest() throws InterruptedException //method to open facebook page to share twoplugsvideo
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(facebook));
		facebook.click();
		Set<String> windows = ldriver.getWindowHandles();
		for(String win:windows)
		{
			ldriver.switchTo().window(win);	
			ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		}
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String url = ldriver.getCurrentUrl();
		return url;
	}
	
	public String tweetTest()   ////method to open twitter page to share twoplugsvideo
	{
		ldriver.switchTo().frame(newFrame);
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(tweeter));
		tweeter.click();
		ArrayList<String> child = new ArrayList<String>(ldriver.getWindowHandles());
		ldriver.switchTo().window(child.get(1));
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String url = ldriver.getCurrentUrl();
		return url;
	}

	@FindBy(xpath="//div[contains(text(),'Log into your Facebook account to share.')]")
	@CacheLookup
	WebElement fbTitle;
	@FindBy(xpath = "//*[@id='u_0_t']/div[2]/div[2]")
	@CacheLookup
	public WebElement appLinkOnFB;

	@FindBy(id = "email")
	@CacheLookup
	WebElement emailBox;

	@FindBy(id = "pass")
	@CacheLookup
	WebElement passBox;
	@FindBy(xpath = "//input[@name='login']")
	@CacheLookup
	WebElement loginbtn;
	
	
	
	@FindBy(id="u_0_25")
	@CacheLookup
	WebElement postToFBLink;
	@FindBy (xpath="//*[@id=\"u_ps_0_0_3\"]/div/div/div[2]/span/div/a")
	@CacheLookup
	WebElement twoPlugLinkOnFb;
	
	
	public String getFbTitle() {
		
	return	fbTitle.getText();
	}
	
	
	public void clickappLinkFB() {
		appLinkOnFB.click();
	}

	public void txtEmail() {
		emailBox.sendKeys("mikehm122019@gmail.com");
	}

	public void txtPass() {
		passBox.sendKeys("icanwin12$");
	}

	public void clickLoginBtn() {
		loginbtn.click();

	}
	
	public void clickPostToFb() {
		postToFBLink.click();
	}
	public void clickOnTwoPlugLink() {
		
		twoPlugLinkOnFb.click();
	}


	
	
	
}
