package com.kagrana.base;

import org.testng.annotations.Test;

import com.kagrana.DTO.TestStep;

public class BaseActionsTest2 extends BaseActions {
	@Test
	public void reportingTest() throws InterruptedException{
		this.testCase.setTestCaseName("Search Test 1");
		TestStep testStep = new TestStep();
		testStep.setDescription("Description");
		testStep.setExceptedData("Expected Data");
		testStep.setActualData("Actual data");
		testStep.setImageName("Image Name");
		testStep.setImagePath("Image Path");
		testStep.setResult(true);
		testStep.setStepNumber("1");
		testCase.addTestSteps(testStep);
		TestStep testStep2 = new TestStep();
		testStep2.setDescription("Description");
		testStep2.setExceptedData("Expected Data");
		testStep2.setActualData("Actual data");
		testStep2.setImageName("Image Name");
		testStep2.setImagePath("Image Path");
		testStep2.setResult(true);
		testStep.setStepNumber("2");
		testCase.addTestSteps(testStep2);
	}
	@Test
	public void reportingTest2() throws InterruptedException{
		this.testCase.setTestCaseName("Search Test 2");
		TestStep testStep = new TestStep();
		testStep.setDescription("Description");
		testStep.setExceptedData("Expected Data");
		testStep.setActualData("Actual data");
		testStep.setImageName("Image Name");
		testStep.setImagePath("Image Path");
		testStep.setResult(true);
		testCase.addTestSteps(testStep);
		TestStep testStep2 = new TestStep();
		testStep2.setDescription("Description");
		testStep2.setExceptedData("Expected Data");
		testStep2.setActualData("Actual data");
		testStep2.setImageName("Image Name");
		testStep2.setImagePath("Image Path");
		testStep2.setResult(true);
		testStep.setStepNumber("2");
		testCase.addTestSteps(testStep2);
	}
	@Test
	public void reportingTest3() throws InterruptedException{
		this.testCase.setTestCaseName("Search Test 3");
		TestStep testStep = new TestStep();
		testStep.setDescription("Description");
		testStep.setExceptedData("Expected Data");
		testStep.setActualData("Actual data");
		testStep.setImageName("Image Name");
		testStep.setImagePath("Image Path");
		testStep.setResult(true);
		testCase.addTestSteps(testStep);
		TestStep testStep2 = new TestStep();
		testStep2.setDescription("Description");
		testStep2.setExceptedData("Expected Data");
		testStep2.setActualData("Actual data");
		testStep2.setImageName("Image Name");
		testStep2.setImagePath("Image Path");
		testStep2.setResult(true);
		testStep.setStepNumber("2");
		testCase.addTestSteps(testStep2);
	}
}
