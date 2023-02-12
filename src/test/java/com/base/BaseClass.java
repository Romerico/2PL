package com.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import com.utilities.ReadConfig;


import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



public class BaseClass 
{
	ReadConfig  readconfig = new ReadConfig(); // Creating object
	
						// Integrating data from ReadConfig
	public String baseURL=readconfig.getApplicationURL(); 
	public String username1=readconfig.getUsername1();
	public String username2=readconfig.getUsername2();
	public String username3=readconfig.getUsername3();
	public String username4=readconfig.getUsername4();
	public String username5=readconfig.getUsername5();
	public String username6=readconfig.getUsername6();
	public String password=readconfig.getPassword();
	public String creditcardname=readconfig.getcreditcardname();
	public String creditcardnumber=readconfig.getcreditcardnumber();
	public String creditcardmmyy=readconfig.getcreditcardmmyy();
	public String creditcardcvv=readconfig.getcreditcardcvc();
	public String creditcardzip=readconfig.getcreditcardzip();	
	
	public static WebDriver driver;
	
	public static Logger logger;
	

	@BeforeClass

	@Parameters("browser") // browser value into br
	public void setup(String br)
//	public void setup()
	
	{		
		// Initialization // Logger initiated within the setup method
		logger=Logger.getLogger("TwoPlugs");// Project Name 
		PropertyConfigurator.configure("log4j.properties"); // Added Logger
		
		logger.setLevel(Level.DEBUG); // to get the debug log
		logger.debug("Debug logging has started ");
		
	/*	System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
		driver = new ChromeDriver();
		//System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
		//driver = new FirefoxDriver();
		driver.get(baseURL);
        driver.manage().window().maximize();*/
        
        
	//	logger = Logger.getLogger("twoplugs");
	//	PropertyConfigurator.configure("Log4j.properties"); 
		

		if(br.equalsIgnoreCase("chrome"))
		{
										//Path from ReadConfig
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver=new ChromeDriver();		//getting Chrome path from readcong and configProperties
		driver.get(baseURL);
		logger.info("Opening base URL - in Chrome");
		
		}
		else if (br.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver=new FirefoxDriver();
			driver.get(baseURL);
			logger.info("Opening base URL - in FireFox");
		}
	/*	else if (br.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver=new InternetExplorerDriver();
			driver.get(baseURL);
			logger.info("Opening base URL - in Internet Explorer");
		}
		else if (br.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver=new FirefoxDriver();
			driver.get(baseURL);
			logger.info("Opening base URL - in FireFox");
		}
		*/
		// Global implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize(); 
			
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		logger.info("Screenshot taken");
	}
	public static String randomestring() 
	{
		String generatedString1 = RandomStringUtils.randomAlphabetic(5); // generate random char string with the specified values passed 
		return (generatedString1);										 
	}

	public static String randomeNum() 
	{
		String generatedString2 = RandomStringUtils.randomNumeric(4);// generate random digits with the specified values passed
		return (generatedString2);
	}
	
	public static String randomemail() 
	{
		String generatedString5 = RandomStringUtils.randomAlphanumeric(5); // generate random char/numeric string with the specified values passed 
		String userEmail = generatedString5 + "@gmail.com";
		return (userEmail);										 
	}

	
}