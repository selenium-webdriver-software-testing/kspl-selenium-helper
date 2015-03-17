package com.kagrana.DTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.kagrana.util.MiscellaneousFunctions;

public class TestCase {
	private String TestCaseName;
	private String TestCaseId;
	private String TestCaseDescription;
	private String ParentURL;
	private String ExecutionEnvironment;
	private long ExecutionTime;
	private List<TestStep> TestSteps;
	private int testStepsCounter;
	private String screenshotDirectory;

	public TestCase() {
		this.testStepsCounter = 1;
		this.TestCaseName = "";
		this.TestCaseId = "";
		this.TestCaseDescription = "";
		this.ParentURL = "";
		this.TestSteps = new ArrayList<TestStep>();
	}

	public final String getTestCaseName() {
		return this.TestCaseName;
	}

	public final void setTestCaseName(String value) {
		this.TestCaseName = value;
	}

	public final String getTestCaseId() {
		return this.TestCaseId;
	}

	public final void setTestCaseId(String value) {
		this.TestCaseId = value;
	}

	public final String getTestCaseDescription() {
		return this.TestCaseDescription;
	}

	public final void setTestCaseDescription(String value) {
		this.TestCaseDescription = value;
	}

	public final String getParentURL() {
		return this.ParentURL;
	}

	public final void setParentURL(String value) {
		this.ParentURL = value;
	}

	public final List<TestStep> getTestSteps() {
		return this.TestSteps;
	}

	private final TestStep getTestStep(String description, String expected,
			String actual, boolean result) {
		TestStep testStep = new TestStep();
		testStep.setStepNumber(testStepsCounter++);
		testStep.setDescription(description);
		testStep.setExceptedData(expected);
		testStep.setActualData(actual);
		testStep.setResult(result);
		return testStep;
	}
	private final TestStep getTestStepWithImage(WebDriver driver, String description, String expected,
			String actual, boolean result ) throws IOException{
		TestStep testStep = this.getTestStep(description, expected, actual, result);
		testStep.setImageName("image");
		testStep.setImagePath(MiscellaneousFunctions.captureScreenshot(driver, getScreenshotDirectory()));
		return testStep;
	}
	public final void addTestSteps(String description, String expected,
			String actual, boolean result) {
		this.TestSteps.add(this.getTestStep(description, expected, actual,
				result));
	}
	public final void addTestStepWithImage(WebDriver driver, String description, String expected,
			String actual, boolean result) throws IOException{
		this.TestSteps.add(this.getTestStepWithImage(driver, description, expected, actual, result));
	}

	public long getExecutionTime() {
		return ExecutionTime;
	}

	public void setExecutionTime(long executionTime) {
		ExecutionTime = executionTime;
	}

	public String getExecutionEnvironment() {
		return ExecutionEnvironment;
	}

	public void setExecutionEnvironment(String executionEnvironment) {
		ExecutionEnvironment = executionEnvironment;
	}

	public String getScreenshotDirectory() {
		return screenshotDirectory;
	}

	public void setScreenshotDirectory(String screenshotDirectory) throws IOException {
		if(this.screenshotDirectory == null){
			File file = new File(screenshotDirectory);
			if(!file.exists()){
				file.mkdir();
			}
		}
		this.screenshotDirectory = screenshotDirectory;
	}
}
