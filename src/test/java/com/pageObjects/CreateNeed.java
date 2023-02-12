package com.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNeed {

	public WebDriver ldriver;

	// Constructor
	public CreateNeed(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// ======Create a need=========//
	// Selecting create new button to select need
	@FindBy(xpath = "//span[contains(@class,\"w-icons-caret\")]")
	@CacheLookup // is used to improve the performance
	WebElement btnCreateNew;

	//// Selecting Need from drop down list
	@FindBy(xpath = "//a[contains(text(),'Need')]")
	@CacheLookup // is used to improve the performance
	WebElement btnNeed;

	// ==== Completing Add a Need page =====//

	// Send values to field title for adding title to page "Add a Need"
	@FindBy(name = "name")
	@CacheLookup // is used to improve the performance
	WebElement txtTitle;

	// Send values to description field
	@FindBy(id = "description")
	@CacheLookup // is used to improve the performance
	WebElement txtdescription;
	
	public String gettxtTitleValue()
	{
		return txtTitle.getAttribute("value");
	}
	
	public String gettxtdescription()
	{
		return txtdescription.getAttribute("value");
	}
	

	// ==========================================================//

	// Select category
	@FindBy(xpath = "//*[@id='category_id-styler']/div[1]/div[1]")
	@CacheLookup
	WebElement txtCategory;

	// Select from Category list
	@FindBy(xpath = "//div[@id='category_id-styler']//ul/li")
	@CacheLookup
	List<WebElement> drpdowncategoryList;

	// Select Sub-category field
	//@FindBy(xpath = "//div[@id='subcategory_id-styler']/div/div[1]")
	@FindBy(xpath = "//select[@id='subcategory_id']")
	@CacheLookup
	WebElement txtSubcategory;

	// Select from Sub-Category list
/*	@FindBy(xpath = "//div[@id='subcategory_id-styler']//ul/li")
	@CacheLookup
	List<WebElement> drpdownsubCategoryList;
*/
	// ==========================================================//

	// Updating price field
	@FindBy(xpath = "//input[@id='price']")
	@CacheLookup
	WebElement txtPrice;

	// Click Create button
	@FindBy(xpath = "//span[contains(text(),'create')]")
	@CacheLookup
	WebElement btnCreate;

	// Click Cancel button
	@FindBy(xpath = "//span[contains(text(),'CANCEL')]")
	@CacheLookup
	WebElement btnCancel;

	// Capture Warning text message
	@FindBy(xpath = "//div[@class='confirm-text']/p")
	@CacheLookup
	WebElement txtWarnMsg;

	// Click exit button
	@FindBy(xpath = "//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']")
	@CacheLookup
	WebElement btnExit;

	// ======= Log out ======//
	@FindBy(xpath = "//span[@class='caret']")
	@CacheLookup
	WebElement dorpDownLogout;

	@FindBy(xpath = "//span[contains(text(),'Sign Out')]")
	@CacheLookup
	WebElement btnSignout;

	// ================================================================//

	public void clkbtnCreateNew() {
		btnCreateNew.click();
	}

	public void clkbtnNeed() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(btnNeed));
		btnNeed.click();
	}

	public void txtTitleField(String uvalue) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(txtTitle));
		txtTitle.click();
		txtTitle.sendKeys(uvalue);
	}

	public void txtdescriptionField(String description) {
		txtdescription.sendKeys(description);
	}

	//========================================================================//
	
	//Click on category field to display options
		public void clktxtCategoryField() {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(txtCategory));
			wait.until(ExpectedConditions.elementToBeClickable(txtCategory));
			txtCategory.click();
		}

		//Select from Category List
			public void SelectdrpdownCategory(String catName) {
			//	WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
			//	wait.until(ExpectedConditions.visibilityOfAllElements(drpdowncategoryList));
				for(WebElement cat : drpdowncategoryList)
			   {
				   if(cat.getText().equalsIgnoreCase(catName))
				   {
					   cat.click();
					   break;
				   }
			   }
			}
		
		//Click SubCategory field to display options
/*		public void clktxtsubCategoryField() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(txtSubcategory));
			wait.until(ExpectedConditions.elementToBeClickable(txtSubcategory));
			txtSubcategory.click();
		}
*/
		//Select from Subcategory list
	/*	public void selectdrpdownSubCategory(String subcatName) {
		//	WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		//	wait.until(ExpectedConditions.visibilityOfAllElements(drpdownsubCategoryList));
			for(WebElement subcat : drpdownsubCategoryList)
		   {
			   if(subcat.getText().equalsIgnoreCase(subcatName))
			   {
				   subcat.click();
				   break;
			   }
		   }
		}
		
*/		
		//Select from subcategory
		public void selectdrpdownSubCategory(String subcatName) {
			
			Select sel = new Select(txtSubcategory);
			sel.selectByVisibleText(subcatName);
			
		}
//========================================================================//
	public void txtPriceField(String price) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(txtPrice));		
		txtPrice.sendKeys(price);
	}

	public void btnSubmitNeedPage() {
		btnCreate.click();
	}

	public void btnCancelNeedPage() {
		btnCancel.click();
	}

	public String txtWarningmsg() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(txtWarnMsg));
		return txtWarnMsg.getText();
	}

	public void clkbtnExit() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(btnExit));
		btnExit.click();
	}

	// ===============Log out====================== //
	public void clkdropDownLogout() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeClickable(dorpDownLogout));
		Thread.sleep(3000);
		dorpDownLogout.click();
	}

	public void clkbtnSignOut() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnSignout));
		wait.until(ExpectedConditions.elementToBeClickable(btnSignout));
		btnSignout.click();
	}
	//===================================================//
	
	//upload Image 1
			@FindBy (xpath="//input[@id='file1']")
			@CacheLookup
			WebElement File1;
		
			//upload Image 2
			@FindBy (xpath="//input[@id='file2']")
			@CacheLookup
			WebElement File2;
			
			//upload Image 3
			@FindBy (xpath="//input[@id='file3']")
			@CacheLookup
			WebElement File3;

	
	
	//Click on File Upload 1
	
			public void NeedFileUpload1(String Path1)
			{
				
				File1.sendKeys(Path1);
				
				
			}
			
			 //Click on File Upload 2
			public void NeedFileUpload2(String Path2)
			{
				
				File2.sendKeys(Path2);
				
				
			}
			
			//Click on File Upload 3
			public void NeedFileUpload3(String Path3)
			{
				
				File3.sendKeys(Path3);
			
				
			}


}
