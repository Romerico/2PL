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

public class EditNeedPage {
public WebDriver ldriver;
	
	//Constructor
	
	public EditNeedPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// On add a Need Page
		@FindBy (xpath="//*[@id=\"name\"]")
		@CacheLookup
		WebElement titleTextBox;
		
		@FindBy(xpath="//*[@id=\"description\"]")
		@CacheLookup
		WebElement descriptionTextArea;
		
		@FindBy(xpath="//*[@id=\"category_id-styler\"]/div[1]/div[1]")
		@CacheLookup
		WebElement category;
		
		@FindBy(xpath = "//*[@id=\"category_id-styler\"]/div[2]/ul/li[5]")
		@CacheLookup
		WebElement automobileCategory;
		
		@FindBy(xpath="//*[@id=\"subcategory_id-styler\"]/div[1]/div[1]")
		@CacheLookup
		WebElement subCategoryAll;
		
		@FindBy(xpath = "//*[@id=\"price\"]")
		@CacheLookup
		WebElement priceTextBox;
		
		@FindBy(xpath="//*[@id=\"imagegroup\"]/div[5]/ul/li[2]/button/span")
		@CacheLookup
		WebElement createBtn;
		
		@FindBy(xpath="//div[8]/section/div/div[1]/div[1]/div/div[2]")
		@CacheLookup
		WebElement needTitle;
		
		@FindBy(xpath="//input[@id='name']")
		@CacheLookup // is used to improve the performance 
		WebElement txtEditTitle;
		
		// Send values to description field 
		@FindBy(xpath="//textarea[@id='description']")
		@CacheLookup // is used to improve the performance 
		WebElement txtEditdescription;
				
		//Select Category
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
		
		//Updating File 1
		@FindBy (xpath="//input[@id='file1']")
		@CacheLookup
		WebElement EditFile1;

		//Updating File 2
		@FindBy (xpath="//input[@id='file2']")
		@CacheLookup
		WebElement EditFile2;

		//Updating File 3
		@FindBy (xpath="//input[@id='file3']")
		@CacheLookup
		WebElement EditFile3;

		
		//Element of the Submit Button
		@FindBy (xpath="//button[contains(@type,'submit')]")
		@CacheLookup
		WebElement btnSubmit;
		
		//======================================================================================//
		public void addTitle(String need)
		{
			titleTextBox.sendKeys(need);
		}
		
		public void addDescription(String desc)
		{
			descriptionTextArea.sendKeys(desc);
		}
		
		public void clickCategory()
		{
			category.click();
		}
		
		public void selectAutomobileCategory()
		{
			automobileCategory.click();
		}

		public void selectSubCategoryAll()
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
			wait.until(ExpectedConditions.visibilityOf(subCategoryAll));	
			subCategoryAll.click();
		}
		public void addPrice(String price)
		{
			priceTextBox.sendKeys(price);
		}
		
		public void clickCreate()
		{
			createBtn.click();
		}
		
		public boolean confirmNeedCreated(String needName)
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(needTitle));
			return needTitle.getText().contains(needName);
		}
		

		//update Title
		public void EdittxtTitleField(String uvalue)
		{
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(txtEditTitle));
			
			txtEditTitle.click();
			txtEditTitle.clear();
			txtEditTitle.sendKeys(uvalue);
		}
		//update description
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

		//Edit Category List
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
		
		//Edit price
		public void EdittxtPriceField(String price)
		{
			EdittxtPrice.click();
			EdittxtPrice.clear();
			
			EdittxtPrice.sendKeys(price);
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
		//Edit the Image Path of the File Upload 3
		public void EditFileUpload3(String Path3)
		{
			
			EditFile3.sendKeys(Path3);
		
		}


}
