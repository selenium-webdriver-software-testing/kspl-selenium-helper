package in.mayurshah.DTO;

import java.util.ArrayList;
import java.util.List;

import in.mayurshah.util.MiscellaneousFunctions;

public class TestSuite
{
  private String TestSuiteName;
  private String TestDateTime;
  private List<TestCase> TestCases;
  
  public TestSuite(){
	  TestSuiteName = "";
	  TestDateTime = MiscellaneousFunctions.getTimeStamp();
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
  
  public final String getTestDateTime()
  {
    return this.TestDateTime;
  }
  
  public final void setTestDateTime(String value)
  {
    this.TestDateTime = value;
  }
  
  public List<TestCase> getTestCases() {
		return TestCases;
  }

  public void addTestCases(TestCase testCase) {
		TestCases.add(testCase);
  }
}
