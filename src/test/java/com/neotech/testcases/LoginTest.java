package com.neotech.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.neotech.pages.DashboardPageElements;
import com.neotech.pages.LoginPageElements;
import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

public class LoginTest extends CommonMethods {

	@Test (groups ={"smoke",  "regression" })
	public void validLogin()
	{
		LoginPageElements login = new LoginPageElements();
		DashboardPageElements dashboard = new DashboardPageElements();
		
		
		test.info("Entering valid login credentials");
		
		//send username
		sendText(login.username, ConfigsReader.getProperty("username"));
		wait(1);
		//send password
		sendText(login.password, ConfigsReader.getProperty("password"));
		wait(1);
		//click on login
		//you can use jsClick() as well
		click(login.logInButton);
		wait(2);
		
		test.info("Verifying that user is logged in.");
		
		//verify username
		String expected  = "Jacqueline White";
		String actual = dashboard.accountName.getText();
		
		//assertion
		Assert.assertEquals(actual, expected,"The account name does NOT match!!!");
	}

	@Test (groups ="regression" )
	public void emptyPasswordLogin() {
		LoginPageElements login = new LoginPageElements();
//		DashboardPageElements dashboard = new DashboardPageElements();
		
		sendText(login.username, ConfigsReader.getProperty("username"));
		wait(1);
		
		click(login.logInButton);
		wait(2);
		
		String expectedText = "Password cannot be empty";
		String actualText = login.passwordError.getText();
		
	    Assert.assertEquals(actualText, expectedText, "The account name does NOT match!!");
	}
	
	@Test (groups ="regression" )
	public void invalidPasswordLogin() {
		LoginPageElements login = new LoginPageElements();
//		DashboardPageElements dashboard = new DashboardPageElements();
		
		sendText(login.username, ConfigsReader.getProperty("username"));
		wait(1);
	
		sendText(login.password, "wrong password");
		wait(1);
	
		click(login.logInButton);
		wait(2);
		
		String expectedText = "Invalid Credentials -  bla bla bla";
		String actualText = login.invalidMsg.getText();
	
		Assert.assertEquals(actualText, expectedText, "The error message does NOT match!!");
	}
}
