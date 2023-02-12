package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HelpPage {
public WebDriver ldriver;
	
	//Constructor
	
	public HelpPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	//Get the text from Help Page
	@FindBy(xpath = "//div[contains(text(),'Support Center Topics')]")
	@CacheLookup
	WebElement txtHelpmsg;
	
	//========================================================================//
	
	public String gettxtHelpmsg()
	{
		return txtHelpmsg.getText();
	}

}
