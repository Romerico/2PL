package com.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateService {
	public WebDriver ldriver;

	// Constructor
	public CreateService(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// ======Create a service=========//

	// Selecting create new button to select service need
	@FindBy(xpath = "//span[contains(@class,\"w-icons-caret\")]")
	@CacheLookup // is used to improve the performance
	WebElement btnCreateNew;

	// Selecting Service from drop down list
	@FindBy(xpath = "//a[contains(text(),'Service')]")
	@CacheLookup // is used to improve the performance
	WebElement btnService;

	// ==== Completing Add a service page =====//

	// Send values to field title for adding title to page "Add a Service"
	@FindBy(xpath = "//input[@id='name']")
	@CacheLookup // is used to improve the performance
	WebElement txtTitle;

	// Send values to description field
	@FindBy(xpath = "//textarea[@id='description']")
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
	
	//Option list
			@FindBy(xpath="//select[@id='category_id']")
			@CacheLookup
			public
			WebElement optionlist;


	//============================================================//
	// Select category
	@FindBy(xpath = "//div[@class='jq-selectbox__select-text placeholder']")
	@CacheLookup
	WebElement txtCategory;

	// Select from Category list
		@FindBy(xpath = "//div[@id='category_id-styler']//ul/li")
		@CacheLookup
		List<WebElement> drpdowncategoryList;

	// Select Sub-category field
	@FindBy(xpath = "//select[@id='subcategory_id']")
	@CacheLookup
	WebElement txtSubcategory;

	//===========================================================//
	
	// Updating price field
	@FindBy(xpath = "//input[@id='price']")
	@CacheLookup
	WebElement txtPrice;

	// Refund Bar
	@FindBy(xpath = "//div[@id='slider-range-max3']")
	@CacheLookup
	WebElement sliderBar;

	// Refund valid field
	@FindBy(xpath = "//input[@id='refund_valid']")
	@CacheLookup
	WebElement txtrefundValid;

	// Click Submit button
	@FindBy(xpath = "//button[contains(@type,'submit')]")
	@CacheLookup
	WebElement btnSubmit;

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

	// ============Delete Need icon=========//
	@FindBy(xpath = ("//div[4]/div[2]/div/div/table/tbody/tr[1]/td[2]/ul[1]/li[2]/a[1]"))
	@CacheLookup
	WebElement delNeedIcon;

	@FindBy(xpath = "//*[@id='btn_deleteService']/span")
	@CacheLookup
	WebElement IwantToDelBtn;

	// ========================================================================//

	public void clkbtnCreateNew() {
		btnCreateNew.click();
	}

	public void clkbtnService() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(btnService));
		btnService.click();
	}

	public void txtTitleField(String uvalue) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtTitle));
		txtTitle.click();
		txtTitle.sendKeys(uvalue);
	}

	public void txtdescriptionField(String description) {
		txtdescription.sendKeys(description);
	}

	//Click on category field to display options
	public void clktxtCategoryField() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(txtCategory));
		wait.until(ExpectedConditions.elementToBeClickable(txtCategory));
		txtCategory.click();
	}

	//Select from Category List
		public void SelectdrpdownCategory(String catName) {
			for(WebElement cat : drpdowncategoryList)
		   {
			   if(cat.getText().equalsIgnoreCase(catName))
			   {
				   cat.click();
				   break;
			   }
		   }
		}

	//Select from subcategory
	public void selectdrpdownSubCategory(String subcatName) {
		
		Select sel = new Select(txtSubcategory);
		sel.selectByVisibleText(subcatName);
	}
	
	public void txtPriceField(String price) {
		txtPrice.sendKeys(price);
	}

	public void SilderBarMaxLimit() {
		// Selecting the maximum option in the slider bar
		WebElement ele = sliderBar;
		Actions act = new Actions(ldriver);
		act.moveToElement(ele).dragAndDropBy(ele, 400, 0).build().perform();

	}

	public void refundValidField(String days) {
		txtrefundValid.sendKeys(days);
	}

	public void btnSubmitServicePage() {
		btnSubmit.click();
	}

	public void btnCancelServicePage() {
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

	// ===================================================//

	// To delete Need
	public void clkDelNeedIcon() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(90));
		wait.until(ExpectedConditions.visibilityOf(delNeedIcon));
		delNeedIcon.click();
	}

	// Click on I want to delete
	public void clkIwantToDel() {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(IwantToDelBtn));
		wait.until(ExpectedConditions.elementToBeClickable(IwantToDelBtn));
		IwantToDelBtn.click();
	}
	
	//Enter the Image Path of the File Upload 1
	public void FileUpload1(String Path1)
	{
		
		File1.sendKeys(Path1);
		
		
	}
	//Enter the Image Path of the File Upload 2
	public void FileUpload2(String Path2)
	{
		
		File2.sendKeys(Path2);
		
		
	}
	//Enter the Image Path of the File Upload 3
	public void FileUpload3(String Path3)
	{
		
		File3.sendKeys(Path3);
	

	
	}	

}
