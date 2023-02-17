package com.neotech.testbase;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.neotech.utils.CommonMethods;

public class Listener implements ITestListener {

	public void onStart(ITestContext context) {
		System.out.println("Functionality Test Started");
	}

	public void onFinish(ITestContext context) {
		System.out.println("Functionality Test Finished");
	}

	public void onTestStart(ITestResult result) {
		//printing logs to console
		System.out.println("Test Will Start: " + result.getName());
		// we want create a test report --- so that we output logs on the ExtentReport
		
		BaseClass.test = BaseClass.report.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		//printing logs to the console
		System.out.println("Test Passed: " + result.getName());
		
		//print test passed on the report
		BaseClass.test.pass("Test Passed:" + result.getName());
		
		//get screenshot and add it on the report 
		BaseClass.test.addScreenCaptureFromPath(CommonMethods.takeScreenshot("passed/"+result.getName()));
	}

	public void onTestFailure(ITestResult result) {
		//printing logs to the console
		System.out.println("Test Failed: " + result.getName());
		
		//print test passed on the report
		BaseClass.test.fail("Test Failed:" + result.getName());
		
		//get screenshot and add it on the report
		BaseClass.test.addScreenCaptureFromPath(CommonMethods.takeScreenshot("failed/"+result.getName()));
	}
}
