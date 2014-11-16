package com.kagrana.base;

import org.testng.annotations.Test;

public class BaseActionsTest1 extends BaseActions {
	@Test
	public void reportingTest() throws InterruptedException{
		this.testCase.addTestSteps("Open URL", "URL opens", "URL oepns", true);
		this.testCase.addTestSteps("Login Test", "User logs in", "user logs in", true);
		this.testCase.addTestSteps("Open dashboard", "Dashboard opens", "dashboard does not opens", true);
	}
	@Test
	public void reportingTest2() throws InterruptedException{
		this.testCase.addTestSteps("Open URL", "URL opens", "URL oepns", true);
		this.testCase.addTestSteps("Login Test", "User logs in", "user logs in", true);
		this.testCase.addTestSteps("Open dashboard", "Dashboard opens", "dashboard does not opens", true);
	}
	@Test
	public void reportingTest3() throws InterruptedException{
		this.testCase.addTestSteps("Open URL", "URL opens", "URL oepns", true);
		this.testCase.addTestSteps("Login Test", "User logs in", "user logs in", false);
		this.testCase.addTestSteps("Open dashboard", "Dashboard opens", "dashboard does not opens", false);
	}
}
