package com.pageObjects;

import com.base.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.base.BaseClass.randomemail;
import static com.base.BaseClass.randomestring;

public class SignUpPage {
public WebDriver ldriver;
	
	//Constructor
	
	public SignUpPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// Finding the text box for inputting User Name
	@FindBy(id = "signInEmail")
	@CacheLookup // is used to improve the performance
	public WebElement txtSignUpUser;



	// Finding the text box for inputting Password
	@FindBy(id = "signInPassword")
	@CacheLookup // is used to improve the performance
	public WebElement txtSignUpPassword;

	
	// Finding the button Sign Up after entering the Sign Up information
	@FindBy(xpath = "//a[@href=\"signup\"]")
	@CacheLookup // is used to improve the performance
	public WebElement signUp;

	// Finding the error message displayed when user name is invalid
	@FindBy(xpath = "//p[contains(text(),' Username must start and end with an alphanumeric character and may also contain underscores or dashes')]")
	@CacheLookup // is used to improve the performance
	public WebElement invalidUsername;

	// Finding the error message displayed when email is invalid
	@FindBy(xpath = "//p[contains(text(),'The email format is invalid.')]")
	@CacheLookup // is used to improve the performance
	public WebElement invalidEmail;
	
	// Finding the error message displayed when password is blank
	@FindBy(xpath = "//p[contains(text(),'The password field is required.')]")
	@CacheLookup // is used to improve the performance
	public WebElement blankPassword;

	//Get message displayed after sign up is successfully done
	@FindBy (xpath= "//div[@class='undismiss alert-shown']//div[@class='alert alert-default text-center']")
	@CacheLookup
	public WebElement signupMessage;
	
	//==============================================================================================//


	public boolean invalidEmailIsDisplayed() throws NoSuchElementException {
		return invalidEmail.isDisplayed();
	}
	// Method to enter User Name in the text box
	public void setUsername(String uname) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtSignUpUser));


		txtSignUpUser.sendKeys(uname);
	}

	// Method to enter Email in the text box
	public void setEmail(String email) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtSignUpUser));

		txtSignUpUser.sendKeys(email);

	}

	// Method to enter Password in the text box
	public void setPassword(String pass) {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(txtSignUpPassword));

		txtSignUpPassword.sendKeys(pass);
	}

	// after entering the information, clicking on Sign Up button to complete the
	// sign up process
	public void clkFinalSignUp() {
		signUp.click();
	}

	// method to return error message for invalid user name as String
	public String getErrorMessageInvalidUser() {
		return invalidUsername.getText();
	}
	
	// method to return error message for invalid email as String
		public String getErrorMessageInvalidEmail() {
			return invalidEmail.getText();
		}	

		//method to return error message for Blank Password as string
		public String getErrorMessageBlankPassword() {
			return blankPassword.getText();
			
		}
		
		//Get the text message once sign up is done
		public String getsignupmessage()
		{
			return signupMessage.getText();
		}
}
