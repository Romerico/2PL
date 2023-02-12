package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewServicePage {
	public WebDriver ldriver;

	// Constructor

	public ViewServicePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// Find Element of the Edit Service Link
	@FindBy(xpath = "//a[contains(text(),'Edit')]")
	public WebElement EditServiceLink;

	// Find the element of the label
	@FindBy(xpath = "//div[@class='alert alert-default text-center']")
	@CacheLookup // is used to improve the performance
	public WebElement text;

	// Find the Element of the Title of Service Page
	@FindBy(xpath = "//div[@class='caption']")
	@CacheLookup // is used to improve the performance
	public WebElement ServiceTitle;

	// Find the Element of the Description of Service Page
	@FindBy(xpath = "//div[@class='body']/p[1]")
	@CacheLookup // is used to improve the performance
	public WebElement ServiceDescription;

	// Find the Element of the Category
	@FindBy(xpath = "//table[@class='table-Colorful dual-centered']//tbody/tr[4]/td[2]")
	@CacheLookup // is used to improve the performance
	public WebElement ServiceCategory;

	// Find the Element of the SubCategory
	@FindBy(xpath = "//table[@class='table-Colorful dual-centered']//tbody/tr[5]/td[2]")
	@CacheLookup // is used to improve the performance
	public WebElement ServiceSubCategory;

	// Find the Element of the Price
	@FindBy(xpath = "//ul[@class='dataPanel clearfix']/li[3]/div[1]/div")
	@CacheLookup // is used to improve the performance
	public WebElement ServicePrice;

	// Find the Element of the Refund
	@FindBy(xpath = "//table[@class='table-Colorful dual-centered']//tbody/tr[2]/td[2]")
	@CacheLookup // is used to improve the performance
	public WebElement ServiceRefund;

	// Find the Element of the RefundValid
	@FindBy(xpath = "//table[@class='table-Colorful dual-centered']//tbody/tr[3]/td[2]/b")
	@CacheLookup // is used to improve the performance
	public WebElement ServiceRefundValid;

	// Find the element of the Image1
	@FindBy(xpath = "//ul[@class='img-holder clearfix']/li[1]/a[1]")
	public WebElement Image1;

	// Find the Element of the Image2

	@FindBy(xpath = "//ul[@class='img-holder clearfix']/li[2]/a[1]")
	public WebElement Image2;

	// Find the Element of the Image3

	@FindBy(xpath = "//ul[@class='img-holder clearfix']/li[3]/a[1]")
	public WebElement Image3;
//======================================================================================//

	// Click on the Edit Button
	public void ClickonEditButton() {
		EditServiceLink.click();
	}
}
