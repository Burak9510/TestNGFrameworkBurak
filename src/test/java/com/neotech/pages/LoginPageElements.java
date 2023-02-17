package com.neotech.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

public class LoginPageElements extends CommonMethods {

	@FindBy(id="txtUsername")
	public WebElement username;
	
	@FindBy(id="txtPassword")
	public WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement logInButton;
	
	@FindBy(id = "txtPassword-error")
	public WebElement passwordError;
	
	@FindBy(xpath = "//div[@class='toast-message']")
	public WebElement invalidMsg;
	
	public LoginPageElements()
	{
		PageFactory.initElements(driver, this);
	}
	
	// We can create methods that are related to this page
	public void adminLogin(){
		sendText(username, ConfigsReader.getProperty("username"));
		sendText(password, ConfigsReader.getProperty("password"));
		click(logInButton);
		}
	
}
