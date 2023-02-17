package com.neotech.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.neotech.pages.AddEmployeePageElements;
import com.neotech.pages.DashboardPageElements;
import com.neotech.pages.LoginPageElements;
import com.neotech.pages.PersonalDetailsPageElements;
import com.neotech.utils.CommonMethods;
import com.neotech.utils.ExcelUtility;

public class AddEmployeeTest extends CommonMethods {

//	Create AddEmployeeTest.java similar to Homework1.java under com/neotech/lesson04 package in TestNG project.
//	Follow Page Object Model design pattern, don't find any elements inside the test method!
//
//	Create a Test Method named addEmployee with the following steps:
//	Login into the application
//	Navigate to PIM and Add Employee
//	Provide First Name and Last Name
//	Create Login Details
//	Provide User Name and Password
//	Save the Employee
//	Verify Employee has been added successfully
//
//	This test method should belong to addEmp group.
//	By using @DataProvider, add 3 different employees using Excel.xlsx file.
//
//	Create an xml file named addEmp.xml similar to smoke.xml file and execute the xml file.
//
	@Test(dataProvider = "excelData", groups = { "addEmployee", "regression" })
  public void addEmployee(String firstName, String lastName, String username, String password  )
  {
	
		
	  LoginPageElements login = new LoginPageElements();
	  DashboardPageElements dashboard = new DashboardPageElements();
	  AddEmployeePageElements addEmployee = new AddEmployeePageElements();
	  PersonalDetailsPageElements personalDetails = new PersonalDetailsPageElements();
	  
	  System.out.println("Test Data: " + firstName + " " + lastName);
	  
	  test.info("Test Data: " + firstName + " " + lastName);
	  
	  //Login
	  test.info("I am logging...");
	  login.adminLogin();
	  
	  wait(1);
	 
	  dashboard.PIM.click();
	  dashboard.addEmployeeLink.click();
  
	  wait(1);
  
      //New Employee Data
	  sendText(addEmployee.firstName, firstName);
	  sendText(addEmployee.lastName, lastName);
	  
	  // Get EmployeeId for validation
	  String expectedEmpId = addEmployee.employeeId.getAttribute("value");
    
	  //We are selecting 'Canadian Regional HQ from location drop-down
	  selectDropdown(addEmployee.location, "Canadian Regional HQ");
	  
      wait(1);
      
      jsClick(addEmployee.checkboxLoginDetails);
         
      wait(1);
      
      // Provide 
      sendText(addEmployee.username, username);
      sendText(addEmployee.password, password);
      sendText(addEmployee.confirmPassword, password);
      
      wait(1);
      
      click(addEmployee.saveButton);
      
      wait(1);
      
      waitForVisibility(personalDetails.personalDetailsForm);
   
        // Validation
   		String actualEmpId = personalDetails.employeeId.getAttribute("value");
   		
   		Assert.assertEquals(actualEmpId, expectedEmpId, "Id's do NOT match!");
  }
      @DataProvider(name = "excelData")
	  public Object[][] createData(){
		  return ExcelUtility.excelIntoArray(System.getProperty("user.dir") + "/src/test/resources/testdata/Excel.xlsx", "Employee");
	  }
	
	
	
}
