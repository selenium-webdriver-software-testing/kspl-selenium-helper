package com.kagrana.DTO;

public class TestSuiteConfig {
	private String TestSuiteName;
	private String Environment;
	private String ExecutionType;
	private String TestDateTime;
	
	public String getTestSuiteName() {
		return TestSuiteName;
	}
	public void setTestSuiteName(String testSuiteName) {
		TestSuiteName = testSuiteName;
	}
	public String getEnvironment() {
		return Environment;
	}
	public void setEnvironment(String environment) {
		Environment = environment;
	}
	public String getExecutionType() {
		return ExecutionType;
	}
	public void setExecutionType(String executionType) {
		ExecutionType = executionType;
	}
	public String getTestDateTime() {
		return TestDateTime;
	}
	public void setTestDateTime(String testDateTime) {
		TestDateTime = testDateTime;
	}

}
