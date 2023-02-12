package com.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditServicePage {
public WebDriver ldriver;
JavascriptExecutor js;

	//Constructor
	
	public EditServicePage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

		// Send values to field title for adding title to page "Add a Service"
		@FindBy(xpath="//input[@id='name']")
		@CacheLookup // is used to improve the performance 
		WebElement txtEditTitle;
		
		// Send values to description field 
		@FindBy(xpath="//textarea[@id='description']")
		@CacheLookup // is used to improve the performance 
		WebElement txtEditdescription;
				
		@FindBy(xpath="//div[@class='jq-selectbox__select-text']")
		@CacheLookup 
		WebElement EditselectCategory;
		
		// Select from Category list
		@FindBy(xpath = "//div[@id='category_id-styler']//ul/li")
		@CacheLookup
		List<WebElement> EditCategoryList;
		
		// Select from Sub-Category list
		@FindBy(xpath = "//select[@id='subcategory_id']")
		@CacheLookup
		WebElement EditsubCategoryList;
	
		// Updating price field 
		@FindBy(xpath="//input[@id='price']")
		@CacheLookup
		WebElement EdittxtPrice;
		
		@FindBy(xpath="//select[@name='category_id']")
		@CacheLookup
		WebElement Drp1;
		
		// Refund Bar
		@FindBy(xpath="//div[@id='slider-range-max3']")
		@CacheLookup
		WebElement EditsliderBar;
		
		//Refund valid field
		@FindBy (xpath="//input[@id='refund_valid']")
		@CacheLookup
		WebElement EdittxtrefundValid;

		@FindBy (xpath="//input[@id='file1']")
		@CacheLookup
		WebElement EditFile1;

		@FindBy (xpath="//input[@id='file2']")
		@CacheLookup
		WebElement EditFile2;

		@FindBy (xpath="//input[@id='file3']")
		@CacheLookup
		WebElement EditFile3;

		@FindBy (xpath="//button[contains(@type,'submit')]")
		@CacheLookup
		WebElement btnSubmit;
		
		//Update Title
		public void EdittxtTitleField(String uvalue)
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(txtEditTitle));
			txtEditTitle.click();
			txtEditTitle.clear();
			txtEditTitle.sendKeys(uvalue);
		}
		
		//Update Description
		public void EdittxtdescriptionField(String description)
		{
			txtEditdescription.click();
			txtEditdescription.clear();
			txtEditdescription.sendKeys(description);
		}
		
		//Edit Category
		public void EditselectCategoryField()
		{
			EditselectCategory.click();
		}

		public void SelectdrpdownCategory(String catName) {
		for(WebElement cat : EditCategoryList)
		   {
			   if(cat.getText().equalsIgnoreCase(catName))
			   {
				   cat.click();
				   break;
			   }
		   }
		}
		
		//Edit SubCategoryList
		public void selectdrpdownSubCategory(String subcatName) 
			{
			Select sel = new Select(EditsubCategoryList);
			sel.selectByVisibleText(subcatName);
			}
		
		//Edit Service Price value
		public void EdittxtPriceField(String price)
		{
			EdittxtPrice.click();
			EdittxtPrice.clear();
			EdittxtPrice.sendKeys(price);
		}
		
		//Edit SliderValue
		public void EditSilderBarMaxLimit()
		{
			Actions act=new Actions(ldriver);
			act.moveToElement(EditsliderBar).dragAndDropBy(EditsliderBar, 50, 0).build().perform();
		}
		
		//Edit refund Value 
		public void EditrefundValidField (String days)
		{
			EdittxtrefundValid.click();
			EdittxtrefundValid.clear();
			EdittxtrefundValid.sendKeys(days);
		}
		
		//Click on the Submit button
		public void EditbtnSubmitServicePage()
		{
			btnSubmit.click();
		}
		
		//Edit the Image Path of the File Upload 1
		public void EditFileUpload1(String Path1)
		{
			EditFile1.sendKeys(Path1);
		}
		
		//Edit the Image Path of the File Upload 2
		public void EditFileUpload2(String Path2)
		{
			
			EditFile2.sendKeys(Path2);
		}

		//Edit the Image Path of the File Upload 
		public void EditFileUpload3(String Path3)
		{
		EditFile3.sendKeys(Path3);
		}

}
