package in.mayurshah.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import in.mayurshah.DTO.WebDriverConfig;
import in.mayurshah.util.Log;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import in.mayurshah.DTO.TestCase;

public abstract class BaseActions {
	protected WebDriver driver;
	protected static Log log;
	protected WebDriverConfig config;
	protected String baseURL;
	protected TestCase testCase;
	private static int testCaseCount = 1;
	
	/**
	 * This gets invoked even before suite starts.
	 * @param ReportLocation - Provide location where you want to store the report
	 */
	@Parameters({"ReportLocation"})
	@BeforeSuite
	public void beforeSuite(@Optional String ReportLocation){
		log = new Log();
		log.setReportDirectory(ReportLocation);
	}
	@BeforeMethod
	@Parameters({ "remoteURL", "baseURL", "OS", "browser",
			"version", "internal" })
	public void beforeTest(String remoteURL, String baseURL,
			String OS, String browser, String version, String internal)
			throws IOException {
		this.testCase = new TestCase();
		this.testCase.setExecutionEnvironment("{browser:"+browser+",browserVersion:"+version+",OperatingSystem:"+OS+"}");
		this.testCase.setParentURL(baseURL);
		this.testCase.setTestCaseId("KT"+testCaseCount++);
		this.testCase.setScreenshotDirectory(log.getReportDirectory()+"\\images");
		config = new WebDriverConfig();
		config.setRemoteURL(remoteURL);
		this.baseURL = baseURL;
		config.setOS(OS);
		config.setBrowserName(browser);
		config.setBrowserVersion(version);
		config.setIntenal(Boolean.parseBoolean(internal));
		driver = xRemoteWebDriver.getInstance(config, log);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(this.baseURL);
	}
	
	@AfterMethod
	public void afterTest(ITestResult itr) {
		testCase.setExecutionTime((itr.getEndMillis() - itr.getStartMillis()));
		testCase.setTestCaseName(itr.getName());
		log.addTestCase(testCase);
		try {
			driver.close();
		} catch (Exception ignore) {

		}
		try {
			driver.quit();
		} catch (Exception ignore) {

		}
	}
	@AfterSuite
	public void afterSuite(ITestContext itc) throws IOException{
		log.setTestSuiteName(itc.getSuite().getName());
		log.writeReport();
		log.zipAndEmailFile();
	}

}
