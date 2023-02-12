package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditProfilePage {
public WebDriver ldriver;
	
	//Constructor
	
	public EditProfilePage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	//Locate the "Edit Page" link
	@FindBy(linkText="Edit Page")
	@CacheLookup
	WebElement lnkEditPage;

	//Locate the "Username" field
	@FindBy(name="username")
	@CacheLookup
	WebElement userName;
	
	//Locate the "Cancel" button 
	@FindBy(xpath="//button[@class='btn btn-default btn-border']")
	@CacheLookup
	WebElement cancelEditPage;
	
	//Locate the "Save Changes" button
	@FindBy(xpath="//span[contains(text(),'SAVE CHANGES')]")
	@CacheLookup
	WebElement saveEditPage;
	
	//Locate the "Edit Profile" menu item
	@FindBy(xpath="//form[@class='profileRoom']//span[@class='help'][contains(text(),'Profile')]")
	@CacheLookup
	WebElement editProfileMenuItem;
	
	//Locate the "First Name" field
	@FindBy(name="firstname")
	@CacheLookup
	WebElement firstName;
	
	//Locate the "Last Name" field
	@FindBy(name="lastname")
	@CacheLookup
	WebElement lastName;
	
	//Locate the "Address Line 1" field
	@FindBy(name="addr1")
	@CacheLookup
	WebElement address1;

	//Locate the "Address Line 2" field
	@FindBy(name="addr2")
	@CacheLookup
	WebElement address2;

	//Locate the "Postal Code" field
	@FindBy(name="postalcode")
	@CacheLookup
	WebElement postalCode;

	//Locate the "Network Country" dropdown
	@FindBy(xpath="//*[@id=\"countryDropdown-styler\"]")
	@CacheLookup
	WebElement networkCountry;

	//Locate the "Network Province/State" dropdown
	@FindBy(xpath="//*[@id=\"stateDropdown-styler\"]")
	@CacheLookup
	WebElement networkProvinceState;

	//Locate the "Network City/Town" dropdown
	@FindBy(xpath="//*[@id=\"cityDropdown-styler\"]")
	@CacheLookup
	WebElement networkCityTown;
	
	//Locate the "Bio" menu item
	@FindBy(xpath="//span[contains(text(),'Bio')]")
	@CacheLookup
	WebElement editBioMenuItem;
	
	//Locate the "Description" field on the Bio page
	@FindBy(xpath="//textarea[@name='tagline']")
	@CacheLookup
	WebElement bioDescription;
	
	//Locate the 'Add your pics" on the bio page
	@FindBy(xpath="//input[@id='file1']")
	@CacheLookup
	WebElement bioaddpic;
	
	//Locate the "Cancel" button on the Bio page
	@FindBy(xpath="button[@class='btn btn-default btn-border']")
	@CacheLookup
	WebElement cancelBioButton;
	
	//Locate the "Save Changes" button on the Bio page
	@FindBy(xpath="//span[contains(text(),'SAVE CHANGES')]")
	@CacheLookup
	WebElement saveBioButton;
	
	//======= Log out ======//
	//Locate the "Dropdown" menu item
	@FindBy(xpath="//span[@class='caret']")
	@CacheLookup
	WebElement dropDownLogout;
	
	//Locate the "Sign Out" menu item
	@FindBy(xpath="//span[contains(text(),'Sign Out')]")
	@CacheLookup
	WebElement btnSignout;

	
	//Actions
	//Method to click on the "Edit Page" link
	public void clkLnkEditPage()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.elementToBeClickable(lnkEditPage));
		lnkEditPage.click();
	}
	
	//Method to enter the "Username" field
	public void setUserName(String uName)
	{
		userName.clear();
		userName.sendKeys(uName);
	}
	
	//Method to click on the "Cancel" button
	public void clkCancelEditPage()
	{
		cancelEditPage.click();
	}

	//Method to click on the "Save Changes" button
	public void clkSaveEditPage()
	{
		saveEditPage.click();
	}
	
	//Method to click on the "Edit Profile" menu item
	public void clkEditProfileMenuItem()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.elementToBeClickable(editProfileMenuItem));
		editProfileMenuItem.click();
	}
	
	//Method to check in "First Name" is displayed & enabled
	public boolean isDisplayedEnabledFirstName()
	{
		return (firstName.isDisplayed()&&firstName.isEnabled());
	}
	
	//Method to check if "Last Name" is displayed & enabled
	public boolean isDisplayedEnabledLastName()
	{
		return (lastName.isDisplayed()&& lastName.isEnabled());
	}
	
	//Method to check if "Address Line 1" is displayed & enabled
	public boolean isDisplayedEnabledAddress1()
	{
		return (address1.isDisplayed()&&address1.isEnabled());
	}
	
	//Method to check if "Address Line 2" is displayed & enabled
	public boolean isDisplayedEnabledAddress2()
	{
		return (address2.isDisplayed()&&address2.isEnabled());
	}
	
	//Method to check if "Postal Code" is displayed & enabled
	public boolean isDisplayedEnabledPostalCode()
	{
		return (postalCode.isDisplayed()&&postalCode.isEnabled());
	}
	
	//Method to check in "Network Country" is displayed & enabled
	public boolean isDisplayedEnabledNtwkCountry()
	{
		return (networkCountry.isDisplayed()&&networkCountry.isEnabled());
	}
	
	//Method to check if "Network Provice/State" is displayed & enabled
	public boolean isDisplayedEnabledNtwkProvince()
	{
		return (networkProvinceState.isDisplayed()&&networkProvinceState.isEnabled());
	}
	
	//Method to check if "Network City/Town" is displayed & enabled
	public boolean isDisplayedEnabledNtwkCity()
	{
		return (networkCityTown.isDisplayed()&&networkCityTown.isEnabled());
	}
	
	//Click on "Edit Bio" menu item
	public void clkEditBioMenuItem()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.elementToBeClickable(editBioMenuItem));
		editBioMenuItem.click();
	}
	
	//Method to enter the "Bio Description" field
	public void setBioDescription(String description)
	{
		bioDescription.clear();
		bioDescription.sendKeys(description);
	}
	
	//Click on the add your pics to add a file
	public void clkbioaddpic()
	{
		bioaddpic.click();
	}
	
	//Method to click on the "Cancel" button on the "Bio" page
	public void clkCancelBioButton()
	{
		cancelBioButton.click();
	}
	
	//Method to click on the "Save Changes" button on the "Bio" page
	public void clkSaveBioButton()
	{
		saveBioButton.click();
	}
		
	// ===============Log out====================== //
	//Method to select the "Dropdown" menu item
	public void clkdropDownLogout() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeClickable(dropDownLogout));
		dropDownLogout.click();
	}
	
	//Method to select the "Sign Out" menu item
	public void clkbtnSignOut()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnSignout));
		wait.until(ExpectedConditions.elementToBeClickable(btnSignout));
		btnSignout.click();
	}
	
}
