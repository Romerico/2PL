package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig 
{
	// Properties Class
	Properties pro; 
	
	// Constructor
	public ReadConfig()
	{
		File src = new File("./Configuration/config.properties");
			// Creating File object
		try 
		{
			// Open FileInputStream and Read data 
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis); // Load config.properties file
		} 
		catch (Exception e) 
		{	
			// If config.properties file is not available it will throw an exception
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	// Methods to read data from config.properties 
	public String getApplicationURL()                            // ok - add new url in config.properties
	{
		String url=pro.getProperty("baseURL"); // Value from config.properties stored in url variable
		return url;
	}
	public String getUsername1() 
	{
		String username1=pro.getProperty("username1");
		return username1;
	}
	public String getUsername2() 
	{
		String username2=pro.getProperty("username2");
		return username2;
	}
	public String getUsername3() 
	{
		String username3=pro.getProperty("username3");
		return username3;
	}
	public String getUsername4() 
	{
		String username4=pro.getProperty("username4");
		return username4;
	}
	public String getUsername5() 
	{
		String username5=pro.getProperty("username5");
		return username5;
	}
	
	public String getUsername6()  
	{
		String username6=pro.getProperty("username6");
		return username6;
	}
		
	public String getPassword() 
	{
	String password=pro.getProperty("password");
	return password;
	}
	
	public String getChromePath() 
	{
	String chromepath=pro.getProperty("chromepath");
	return chromepath;
	}
	
	public String getIEPath() 
	{
	String iepath=pro.getProperty("iepath");
	return iepath;
	}
	
	public String getFirefoxPath() 
	{
	String firefoxpath=pro.getProperty("firefoxpath");
	return firefoxpath;
	}
	
	public String getcreditcardname()
	{
	String creditcardname = pro.getProperty("creditcardname");
			return creditcardname;
	}
	
	public String getcreditcardnumber()
	{
	String creditcardnumber = pro.getProperty("creditcardnumber");
			return creditcardnumber;
	}
	
	public String getcreditcardmmyy()
	{
	String creditcardmmyy = pro.getProperty("creditcardmmyy");
			return creditcardmmyy;
	}

	public String getcreditcardcvc()
	{
	String creditcardcvc = pro.getProperty("creditcardcvc");
			return creditcardcvc;
	}
	
	public String getcreditcardzip()
	{
	String creditcardzip = pro.getProperty("creditcardzip");
			return creditcardzip;
	}
	
	
	
	
}
