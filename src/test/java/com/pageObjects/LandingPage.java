package com.pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import static com.base.BaseClass.driver;

public class LandingPage extends BaseClass {
public WebDriver ldriver;
	
	//Constructor
	
	public LandingPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	SoftAssert softAssert = new SoftAssert();




	//LivePosting element
	By lposting = By.xpath("//a[@href='/newsearchserviceneed']");
	
	By place = By.xpath("//input[@placeholder='Toronto,ON,Canada']");

	By freeLogin = By.xpath("//a[@href = '/signup']");// locator for 'joinForFree icon on LandingPage...Used in 
	//Click on Contact Link
	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	@CacheLookup
	WebElement btnContact;
	
	//Check email field on Contact us page
	@FindBy(name = "email")
	@CacheLookup
	WebElement txtmail;
	
	//Check subject field on Contact us page
	@FindBy(id = "contactSubject")
	@CacheLookup
	WebElement txtsubject;
	
	//Check message field on Contact us page
		@FindBy(id = "contactContent")
		@CacheLookup
		WebElement txtmessage;
	
		//Check submit button
		@FindBy (xpath="//span[contains(text(),'Submit')]")
		@CacheLookup
		WebElement btnSubmit;


		public boolean txtmailIsDisplayed()
		{
			return txtmail.isDisplayed();
		}
		
		public boolean txtmailIsEnabled()
		{
			return txtmail.isEnabled();
		}
		
		public boolean txtsubjectIsDisplayed()
		{
			return txtsubject.isDisplayed();
		}
		
		public boolean txtsubjectIsEnabled()
		{
			return txtsubject.isEnabled();
		}
		
		public boolean txtmessageIsDisplayed()
		{
			return txtmessage.isDisplayed();
		}
		
		public boolean txtmessageIsEnabled()
		{
			return txtmessage.isEnabled();
		}
			
		public boolean btnSubmitisDisplayed()
		{
			return btnSubmit.isDisplayed();
		}
		
		public boolean btnSubmitisEnabled()
		{
			return btnSubmit.isEnabled();
		}
		
		public void clkbtnSubmit()
		{
			btnSubmit.click();
		}


		//getting the list of sign up links
		@FindBy(xpath = "//a[@href='/signup']")
		@CacheLookup
		List<WebElement> joinButton;

		@FindBy(xpath = "//h3[text()='Log In']")
		@CacheLookup
		WebElement title;

		public void joinTest() throws StaleElementReferenceException {
			for (int x = 0; x < joinButton.size(); x++) {
				List<WebElement> elements = joinButton;
				elements.get(x).click();
				WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(title));

				ldriver.navigate().to("https://qatest.twoplugs.com");
				ldriver.navigate().refresh();

			}
		}

		// Clicking on Join Now on different location of the Landing Page
		@FindBy(xpath="//body[@class='main-template']/div[@class='wrapper']/header/div[@class='container']/ul[@class='control-bar']/li/a[@class='btn green']/span[1]")
		WebElement JoinNowBtn1;
		
		@FindBy(xpath="//a[@class='navbar-brand']//img")
		WebElement ClickTwoPlugsImg;

		@FindBy(xpath="//li[@id='centered-btns2_s0']//span[@class='help'][contains(text(),'JOIN NOW')]")
		WebElement JoinNowBtn2;
		
		@FindBy(xpath="//a[@class='centered-btns_nav centered-btns2_nav next']")
		WebElement ClickNext;
		
		@FindBy(xpath="//li[@id='centered-btns2_s1']//span[@class='help'][contains(text(),'JOIN NOW')]")
		WebElement JoinNowBtn3;
		
		@FindBy(xpath="//li[@id='centered-btns2_s2']//span[@class='help'][contains(text(),'JOIN NOW')]")
		WebElement JoinNowBtn4;
		
		@FindBy(xpath="//li[@id='centered-btns2_s3']//span[@class='help'][contains(text(),'JOIN NOW')]")
		WebElement JoinNowBtn5;
		
		@FindBy(xpath="//li[@id='centered-btns2_s4']//span[@class='help'][contains(text(),'JOIN NOW')]")
		WebElement JoinNowBtn6;
		
		@FindBy(xpath="//div[@class='copy-info']//span[@class='help'][contains(text(),'Join now for free')]")
		WebElement JoinNowBtn7;
		
		@FindBy(xpath="//h4[contains(text(),'Sign Up')]")
		WebElement TextSignup;
		
		
		
		public void clickJoinNow() {
			JoinNowBtn1.click(); //Clicking on the First Join Now which is located at the Top left of the page
		}
		
		public void twoPlugsClick(){
			ClickTwoPlugsImg.click();// Clicking on the TwoPlugs Image for going back to the Landing Page
		}
		
		public void joinNowclick2(){
			JoinNowBtn2.click(); //Clicking on the Second Join Now which is at the center of the page
		}
		
		public void nextClick(){
			ClickNext.click(); //Clicking on Next Button to open the next image
		}
		
		public void joinNowClick3(){
			JoinNowBtn3.click();//Clicking on the Third Join Now which is at the center of the page
		}
		
		public void nextClick2Times(){
			for(int i=1;i<3;i++){
				ClickNext.click(); //Clicking on next button twice to reach to the third image
			}
		}
		
		public void joinNowClick4(){
			JoinNowBtn4.click(); //Clicking on the Fourth Join Now which is at the center of the page
		}
		
		public void nextClick3Times(){
			for(int j=1;j<4;j++){
				ClickNext.click(); //Clicking on the next button thrice to reach to the fourth image
			}
		}
		
		public void joinNowClick5(){
			JoinNowBtn5.click(); //Clicking on the Fifth Join Now which is at the center of the page
		}
		
		public void nextClick4Times(){
			for(int k=1;k<5;k++){
				ClickNext.click(); //Clicking on the next button 4 times to reach on the Fifth image
			}
		}
		
		public void joinNowClick6(){
			JoinNowBtn6.click(); //Clicking on the Sixth Join Now which is at the center of the page
		}
		
		public void twoPlugs2Click(){
			ClickTwoPlugsImg.click(); //Clicking on the TwoPlugs Image for going back to the Landing Page
		}
		
		public void joinNowClick7(){
			JoinNowBtn7.click();//Clicking on the Seventh Join Now which is located at the bottom of the page
			
		}
		
		public String getSignUpText(){
			return TextSignup.getText(); //Getting the Text of Sign Up to validate the navigation of the page to Sign Up page
			
		}

	

		// Click on Services button
			@FindBy(xpath = "//ul[@class='menu-list clearfix']/li[2]")
			@CacheLookup
			WebElement Services;

			// Click on Needs button
			@FindBy(xpath = "//a[contains(text(),'Needs')]")
			@CacheLookup
			WebElement Needs;

			//Sort By arrow - Services
			@FindBy(xpath = "//div[@class='pull-right']//div[@class='jq-selectbox__trigger']")
			WebElement ServicesSortByArrow;

			// Click on Price on  DropDown menu
			@FindBy(xpath = "//li[contains(text(),'Price')]")
			WebElement Price;

			// Click on Recently Updated on DropDown menu
			@FindBy(xpath = "//li[contains(text(),'Recently Updated')]")
			WebElement RecentlyUpdated;

			// Click on Highest Rating on DropDown menu
			@FindBy(xpath = "//li[contains(text(),'Highest Rating')]")
			WebElement HighestRating;


			// Click on Name on DropDown menu
			@FindBy(xpath = "//li[contains(text(),'Name')]")
			WebElement Name;
			
			//Sort by arrow - Needs
			// Click on Sort By arrow
			@FindBy(xpath = "//div[@class='pull-right']//div[@class='jq-selectbox__trigger-arrow']")
			WebElement NeedsSortByArrow;

			//TwoPlugs logo
			@FindBy(xpath="//a[@class='logo w-icons-logotype']//img")
			@CacheLookup
			WebElement ImgLogo;
			
			
			//Click on Video on landing page
			@FindBy (css ="body.main-template:nth-child(2) div.wrapper:nth-child(1) section.section-1:nth-child(2) div.container div.helper div.text-center:nth-child(2) > a.youTube_btn.fancybox-media")
			@CacheLookup
			WebElement video;
			
			@FindBy (xpath="//iframe[@class='fancybox-iframe']")
			@CacheLookup
			WebElement newFrame; //new iframe
			
			//Click on Play button on landing page
			@FindBy (xpath="//button[@aria-label='Play']")
			@CacheLookup
			WebElement play;
			
			// showing the time string comes down the video window
			@FindBy (xpath="//span[@class='ytp-time-current']")
			@CacheLookup
			WebElement times;
			
			@FindBy (xpath="//button[@aria-label='Share']")
			@CacheLookup
			WebElement btnshare;// This icon is on the frame to share video on social media
			
				
			//The following locators are for 'Testimonial Images' and the persons name occupation and details
			//The Test class for this is TP_TC_009_Testimonial

			@FindBy (xpath="//img[@src='/newlayout/images/main/person-1.jpg']")
			@CacheLookup
			WebElement testimony1;      //testimonial image for Kimberly

			@FindBy (xpath="//img[@src='/newlayout/images/main/person-2.jpg']")
			@CacheLookup
			WebElement testimony2;		//testimonial image for Ethan	
			
			@FindBy (xpath="//img[@src='/newlayout/images/main/person-3.jpg']")
			@CacheLookup
			WebElement testimony3;      //testimonial image for Liam
			
			@FindBy (xpath="//*[@id='rslides1_s0']/div")
			@CacheLookup
			WebElement person1;         //Kimberly name and occupation
			
			@FindBy (xpath="//*[@id='rslides1_s1']/div")////*[@id="rslides1_s1"]/div
			@CacheLookup
			WebElement person2;         //Ethan name and occupation
			
			@FindBy (xpath="//*[@id='rslides1_s2']/div")
			@CacheLookup
			WebElement person3;        //Liam name and occupation
			
			@FindBy (xpath="//*[@id='rslides1_s0']/p")
			@CacheLookup
			WebElement person1Detail; //Kimberly details
			
			@FindBy (xpath="//*[@id='rslides1_s1']/p")
			@CacheLookup
			WebElement person2Detail; // Ethan details
			
			@FindBy (xpath="//*[@id='rslides1_s2']/p")
			@CacheLookup
			WebElement person3Detail;  // Liam  details
			
			
			//The following locators  are for "Frequently Asked Question"...The test class for all questions is TP_TC_010_FreqAskQues.

			@FindBy (xpath = "//nav[@class='nav navbar-nav']//a[contains(text(),'FAQ')]")
			@CacheLookup
			WebElement btnFaq;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dt[1]/span")//Question1
			@CacheLookup
			WebElement question1;

			@FindBy (xpath="//*[@id='Zebra_Accordion']/dd[1]/p")//Answer1
			@CacheLookup
			WebElement answer1;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dt[2]/span")  //Question2
			@CacheLookup
			WebElement question2;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dd[2]/p")  //Answer2
			@CacheLookup
			WebElement answer2;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dt[3]/span")  //Question3
			@CacheLookup
			WebElement question3;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dd[3]/p")  //Answer3
			@CacheLookup
			WebElement answer3;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dt[4]/span")  //Question4
			@CacheLookup
			WebElement question4;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dd[4]/p")  //Answer4
			@CacheLookup
			WebElement answer4;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dt[5]/span") //Question5
			@CacheLookup
			WebElement question5;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dd[5]/p")  //Answer5
			@CacheLookup
			WebElement answer5;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dt[6]/span") //Question6
			@CacheLookup
			WebElement question6;
			
			@FindBy (xpath="//*[@id='Zebra_Accordion']/dd[6]/p")  //Answer6
			@CacheLookup
			WebElement answer6;
			
			@FindBy (xpath="//dl[@id='Zebra_Accordion']/dt[7]/span") //Question7
			@CacheLookup
			WebElement question7;

			@FindBy (xpath="//*[@id='Zebra_Accordion']/dd[7]/p")  //Answer7
			@CacheLookup
			WebElement answer7;
			
			//Footer links
			@FindBy (xpath="//a[@href='/about']")
			@CacheLookup
			WebElement about;
			
			@FindBy (xpath="//a[@href='/help']")
			@CacheLookup
			WebElement help;
			
			@FindBy (xpath="//*[@href='/powerofeeds']")
			@CacheLookup
			WebElement eed;
			
			@FindBy (xpath="//a[@href='/trustsafety']")
			@CacheLookup
			WebElement trusts;
			
			@FindBy (xpath="//*[@href='/terms']")
			@CacheLookup
			WebElement terms;
			
			@FindBy (xpath="//*[@href='/terms#privacy']")
			@CacheLookup
			WebElement privacy;
			
			@FindBy (xpath="//*[@href='/helparticles/2']")
			@CacheLookup
			WebElement faq;
			
			@FindBy (xpath="//*[@href='/signup']")
			@CacheLookup
			WebElement join;	
			
			@FindBy (xpath="//a[@href='https://twitter.com/twoplugsinc']")
			@CacheLookup
			WebElement twitterIcon;//twitter Icon
			
			@FindBy (xpath="//a[@class='w-icons-socFace']")
			@CacheLookup
			WebElement facebookIcon;//Facebook icon
			
			@FindBy (xpath="//a[@class='w-icons-socInst']")
			@CacheLookup
			WebElement instagramIcon;//Instagram icon

	//=========================================================================//
			//Click on Join now for free(Sign up)
			public void loginFree()
			{
				ldriver.findElement(freeLogin).click();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			}

			//Click on LivePosting on Landing page
			public void livePosting()
			{
				WebElement searches = ldriver.findElement(lposting);
				searches.click();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			}

			public String location()
			{
				WebElement loc = ldriver.findElement(place);
				String area = loc.getAttribute("placeholder");
				return area;
			}
			
			public void clkbtnContact()
			{
				btnContact.click();
			}
			
			
			public void ClickServicesBtn() {
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(Services));
				wait.until(ExpectedConditions.elementToBeClickable(Services));
				Services.click();
			}
			
			public void ClickNeedsBtn() {
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(Needs));
				wait.until(ExpectedConditions.elementToBeClickable(Needs));
				Needs.click();
			}
	
			public void ClickServicesSortByArrow() {
				Actions actions = new Actions(ldriver);
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(ServicesSortByArrow));
				actions.moveToElement(ServicesSortByArrow);
				actions.build().perform();
				ServicesSortByArrow.click();
			}
			
			public void drpdownsortbyPrice() {
				Actions actions = new Actions(ldriver);
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(Price));
				actions.moveToElement(Price);
				actions.build().perform();
				Price.click();
			}
			
			public void drpdownsortbyDate() {
				Actions actions = new Actions(ldriver);
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(RecentlyUpdated));
				actions.moveToElement(RecentlyUpdated);
				actions.build().perform();
				RecentlyUpdated.click();
			}
			
			public void drpdownsortbyRating() {
				Actions actions = new Actions(ldriver);
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(HighestRating));
				actions.moveToElement(HighestRating);
				actions.build().perform();
				HighestRating.click();
			}
			
			public void drpdownsortbyName() {
				Actions actions = new Actions(ldriver);
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(Name));
				actions.moveToElement(Name);
				actions.build().perform();
				Name.click();
			}
	
			public void ClickNeedSortByArrow() {
				Actions actions = new Actions(ldriver);
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(NeedsSortByArrow));
				actions.moveToElement(NeedsSortByArrow);
				actions.build().perform();
				NeedsSortByArrow.click();
			}	
	
			public void clkimgLogo()
			{
				ImgLogo.click();
			}
			
			public void videoClick()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(video));
				video.click();
			}
			
			public void playButton()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				ldriver.switchTo().frame(newFrame);
				wait.until(ExpectedConditions.visibilityOf(play));
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				play.click();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			}

			public String playTime() 
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(times));
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				String value= times.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				logger.info("video is running for "+value+"seconds");
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				return value;
			}
			
			public void sharevideo()
			{
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				ldriver.switchTo().frame(newFrame);
				wait.until(ExpectedConditions.visibilityOf(btnshare));
				btnshare.click();
			}

			public void testiMony1()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(testimony1));
				testimony1.click();
			}

			public void testiMony2()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(testimony2));
				testimony2.click();
			}
			public void testiMony3()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(testimony3));
				testimony3.click();
			}

			public String person_one_profile()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(person1));

				String personName = person1.getText();
				logger.info(personName);
				return personName;
			}
			public String person_two_profile()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(person2));

				String personName = person2.getText();
				logger.info(personName);
				return personName;
			}
			public String person_three_profile()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(person3));

				String personName = person3.getText();
				logger.info(personName);
				return personName;
			}
			public String person_one_detail()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(person1Detail));

				String persondetail = person1Detail.getText();
				logger.info("Testimony content: " +persondetail);
				return persondetail;
			}
			public String person_two_detail()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(person2Detail));

				String persondetail = person2Detail.getText();
				logger.info("Testimony content: " +persondetail);
				return persondetail;
			}
			public String person_three_detail()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(person3Detail));

				String persondetail = person3Detail.getText();
				logger.info("Testimony content: " +persondetail);
				return persondetail;
			}

			//Click on Faq at the top of the Landing Page
			public void clkbtnFaq()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(btnFaq));
				wait.until(ExpectedConditions.elementToBeClickable(btnFaq));
				btnFaq.click();
			}
			

			public String firstQuestion()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(question1));
				wait.until(ExpectedConditions.elementToBeClickable(question1));
				
				question1.click();
				String quest = question1.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return quest;
			}
			public String firstAnswer()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(answer1));
				String ans = answer1.getText();
				return ans;
			}
			public String secQuestion()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(question2));
				wait.until(ExpectedConditions.elementToBeClickable(question2));
				question2.click();
				String quest = question2.getText();
				return quest;
			}
			public String secAnswer()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(answer2));
				String ans = answer2.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return ans;
			}
			public String thirdQuestion()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(question3));
				wait.until(ExpectedConditions.elementToBeClickable(question3));
				question3.click();
				String quest = question3.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return quest;
			}
			public String thirdAnswer()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(answer3));
				

				String ans = answer3.getText();
				return ans;
			}


			public String fourthQuestion() 
			{ 
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOf(question4)); //question1.click();
			wait.until(ExpectedConditions.elementToBeClickable(question4));
			question4.click(); 
			String quest = question4.getText();
			return quest; 
			} 
			public String fourthAnswer() throws InterruptedException
			{ 
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(answer4)); //question1.click();
				
				String ans = answer4.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return ans;
			}

			public String fifthQuestion()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(question5));
				wait.until(ExpectedConditions.elementToBeClickable(question5));
				question5.click();
				String quest = question5.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return quest;
			}
			public String fifthAnswer() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(answer5));
				String ans = answer5.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return ans;
			}
			public String sixthQuestion()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(question6));
				wait.until(ExpectedConditions.elementToBeClickable(question6));
				question6.click();
				String quest = question6.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return quest;
			}
			public String sixthAnswer() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(answer6));
				
				String ans = answer6.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return ans;
			}
			public String seventhQuestion()
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(25));
				wait.until(ExpectedConditions.visibilityOf(question7));
				wait.until(ExpectedConditions.elementToBeClickable(question7));
				question7.click();
				String quest = question7.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return quest;
			}
			public String seventhAnswer() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(answer7));
				
				String ans = answer7.getText();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				return ans;
			}
			
			public void aboutLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(about));
				wait.until(ExpectedConditions.elementToBeClickable(about));
				about.click();


				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}
			public void helpLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(help));
				wait.until(ExpectedConditions.elementToBeClickable(help));
				help.click();


				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}
			public void eedLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(eed));
				wait.until(ExpectedConditions.elementToBeClickable(eed));
				eed.click();


				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}
			public void trustsLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(trusts));
				trusts.click();


				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}

			public void termsLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(terms));
				terms.click();


				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}
			public void privacyLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(privacy));
				privacy.click();


				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}
			public void faqLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(faq));
				faq.click();


				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}
			public void JoinForFree() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(join));
				join.click();
				ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			}
			
			//method for facebook icon
			public void facebookLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(facebookIcon));
				facebookIcon.click();


				//ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}    //method for twitter icon
			public void twitterLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(twitterIcon));
				twitterIcon.click();
				//ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}

			//method for instagram icon
			public void instaGramLink() throws InterruptedException
			{
				WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.visibilityOf(instagramIcon));
				instagramIcon.click();
				//ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			}

}


