package com.kagrana.base;

import org.testng.annotations.Test;

import com.kagrana.DTO.TestStep;

public class BaseActionsTest extends BaseActions {
	@Test
	public void reportingTest(){
		TestStep testStep = new TestStep();
		testStep.setDescription("Description");
		testStep.setExceptedData("Expected Data");
		testStep.setActualData("Actual data");
		testStep.setImageName("Image Name");
		testStep.setImagePath("Image Path");
		testStep.setResult(true);
		testStep.setStepNumber("1");
		testCase.addTestSteps(testStep);
	}
	@Test
	public void reportingTest2(){
		TestStep testStep = new TestStep();
		testStep.setDescription("Description");
		testStep.setExceptedData("Expected Data");
		testStep.setActualData("Actual data");
		testStep.setImageName("Image Name");
		testStep.setImagePath("Image Path");
		testStep.setResult(true);
		testStep.setStepNumber("2");
		testCase.addTestSteps(testStep);
	}
	@Test
	public void reportingTest3(){
		TestStep testStep = new TestStep();
		testStep.setDescription("Description");
		testStep.setExceptedData("Expected Data");
		testStep.setActualData("Actual data");
		testStep.setImageName("Image Name");
		testStep.setImagePath("Image Path");
		testStep.setResult(true);
		testStep.setStepNumber("3");
		testCase.addTestSteps(testStep);
	}
}
