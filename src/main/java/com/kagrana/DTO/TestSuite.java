package com.kagrana.DTO;

import java.util.ArrayList;
import java.util.List;

public class TestSuite
{
  private String TestSuiteName;
  private String Environment;
  private String ExecutionType;
  private String TestDateTime;
  private List<TestCase> TestCases;
  
  public TestSuite(){
	  TestSuiteName = "";
	  Environment = "";
	  ExecutionType = "";
	  TestDateTime = "";
	  TestCases = new ArrayList<TestCase>();	  
  }
  public final String getTestSuiteName()
  {
    return this.TestSuiteName;
  }
  
  public final void setTestSuiteName(String value)
  {
    this.TestSuiteName = value;
  }
  
  public final String getEnvironment()
  {
    return this.Environment;
  }
  
  public final void setEnvironment(String value)
  {
    this.Environment = value;
  }
  
  public final String getTestDateTime()
  {
    return this.TestDateTime;
  }
  
  public final void setTestDateTime(String value)
  {
    this.TestDateTime = value;
  }
  
  public final String getExecutionType()
  {
    return this.ExecutionType;
  }
  
  public final void setExecutionType(String executionType)
  {
    this.ExecutionType = executionType;
  }
  public List<TestCase> getTestCases() {
		return TestCases;
  }

  public void addTestCases(TestCase testCase) {
		TestCases.add(testCase);
  }
}
