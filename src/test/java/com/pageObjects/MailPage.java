package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailPage {
public WebDriver ldriver;
	
	//Constructor
	
	public MailPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath="//div[@class='jq-selectbox__select-text']")
	@CacheLookup
	WebElement btnSortBy;
	
	@FindBy(xpath="//*[@id=\"sort-styler\"]/div[2]/ul/li[2]")
	@CacheLookup
	WebElement sortByList;
	
	@FindBy(xpath="//*[@id=\"sort-styler\"]/div[2]/ul/li[1]")
	@CacheLookup
	WebElement sortByListDate;
	
	//===============================================================================//
	
	public void clickSortBy()
	{
	btnSortBy.click();
	}
	
	public void selectSortByName()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(sortByList));
		sortByList.click();
	}
	
	public void selectSortByDate()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(sortByListDate));
		sortByListDate.click();
	}
	
}
