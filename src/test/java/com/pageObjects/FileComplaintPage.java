package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FileComplaintPage {
public WebDriver ldriver;
	
	//Constructor
	
	public FileComplaintPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	//Check if Complain page is opened
	@FindBy(xpath=("//span[contains(text(),'File a Complaint')]"))
	@CacheLookup
	WebElement pagetitle; 
	
	//input a subject
	@FindBy(id=("reportSubject"))
	@CacheLookup
	WebElement txtsubject; 	
	
	//input a content
	@FindBy(xpath=("//textarea[contains(@placeholder,'Complaint content')]"))
	@CacheLookup
	WebElement txtcontent; 
	
	//Click Submit Complaint
	@FindBy(xpath=("//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']"))
	@CacheLookup
	WebElement btnsubmitcomplaint;
	
	//Check if success complaint message is shown
	@FindBy(xpath=("//div[contains(text(),'Your complaint has been submitted. You will be con')]"))
	@CacheLookup
	WebElement msgsucesscomplaint;
	
	public boolean isPageOpened()
	{
		return pagetitle.isDisplayed();	
	}	
	public void setSubject(String subject)
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtsubject));
		Actions actions = new Actions(ldriver);
		actions.click(txtsubject)
		    .click()
		    .sendKeys(subject)
		    .build()
		    .perform();
	}
	public void setContent(String content)
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtcontent));
		Actions actions = new Actions(ldriver);
		actions.click(txtcontent)
		    .click()
		    .sendKeys(content)
		    .build()
		    .perform();
	}
	public void clkSubmitComplaint()
	{
		btnsubmitcomplaint.click();
	}
	public boolean isComplainSucessMsgShown()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(msgsucesscomplaint));
		return msgsucesscomplaint.isDisplayed();
	}

}
