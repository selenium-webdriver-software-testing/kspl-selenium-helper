package in.mayurshah.base;

import java.io.IOException;

public class BaseActionsTest extends BaseActions {
	//@Test
	public void reportingTest() throws InterruptedException{
		this.testCase.addTestSteps("Open URL", "URL opens", "URL oepns", true);
		this.testCase.addTestSteps("Login Test", "User logs in", "user logs in", false);
		this.testCase.addTestSteps("Open dashboard", "Dashboard opens", "dashboard does not opens", false);
		
	}
	//@Test
	public void reportingTest2() throws InterruptedException{
		this.testCase.addTestSteps("Open URL", "URL opens", "URL oepns", true);
		this.testCase.addTestSteps("Login Test", "User logs in", "user logs in", true);
		this.testCase.addTestSteps("Open dashboard", "Dashboard opens", "dashboard does not opens", true);
	}
	//@Test
	public void reportingTest3() throws InterruptedException, IOException{
		this.testCase.addTestSteps("Open URL", "URL opens", "URL oepns", true);
		this.testCase.addTestSteps("Login Test", "User logs in", "user logs in", false);
		this.testCase.addTestSteps("Open dashboard", "Dashboard opens", "dashboard does not opens", true);
		this.testCase.addTestStepWithImage(driver, "Open dashboard", "Dashboard opens", "dashboard does not opens", true);
		this.testCase.addTestStepWithImage(driver, "Open dashboard", "Dashboard opens", "dashboard does not opens", true);
		this.testCase.addTestStepWithImage(driver, "Open dashboard", "Dashboard opens", "dashboard does not opens", true);
	}
}
