package com.kagrana.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.kagrana.DTO.TestCase;
import com.kagrana.DTO.TestStep;
import com.kagrana.DTO.TestSuite;
import com.kagrana.DTO.TestSuiteConfig;
import com.kagrana.reporting.ReportingType;
import com.thoughtworks.xstream.XStream;

public class Log {
	private boolean debugEnabled = true;
	private ReportingType reportingType;
	private TestSuite suite;
	private String reportDirectory;
	public Log(){
		suite = new TestSuite();
		reportingType = ReportingType.HTML;
	}
	/**
	 * This function helps setup test suite configuration.
	 * @param testSuiteConfig
	 */
	public void setTestSuiteConfig(TestSuiteConfig testSuiteConfig){
		suite.setTestSuiteName(testSuiteConfig.getTestSuiteName());
		suite.setEnvironment(testSuiteConfig.getEnvironment());
		suite.setExecutionType(testSuiteConfig.getExecutionType());
		suite.setTestDateTime(testSuiteConfig.getTestDateTime());
	}
	public String getReportDirectory() {
		return reportDirectory;
	}
	/**
	 * Set up report directory. Default is report;
	 * @param reportDirectory
	 */
	public void setReportDirectory(String reportDirectory){
		this.reportDirectory = reportDirectory;
	}
	/**
	 * This function will add TestCase to TestSuite
	 * @param testCase
	 */
	public void addTestCase(TestCase testCase){
		suite.addTestCases(testCase);
	}
	public Log(ReportingType reportingType){
		this();
		this.reportingType = reportingType;
	}
	
	public boolean isDebugEnabled() {
		return debugEnabled;
	}

	public void setDebugEnabled(boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
	}
	
	public void write(String message){
		if(isDebugEnabled())
			System.out.println(message);
	}
	public void write(Exception e){
		if(isDebugEnabled()){
			writeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void writeError(String message){
		if(isDebugEnabled())
			System.err.println(message);
	}
	public void writeReport() throws IOException{
		if(getReportDirectory() == null){
			File currentDir = new File(".");
			File reportDir = new File(currentDir.getAbsolutePath() + "\\"+ "reports"+File.separator);
			if(reportDir.exists()){
					FileUtils.cleanDirectory(reportDir);
					reportDir.delete();
					if(reportDir.mkdir())
						this.write("Report directory created!");
					else
						this.writeError("Error creating report directory");
				}
			else{
				if(reportDir.mkdir())
					this.write("Report directory created!");
				else
					this.writeError("Error creating report directory");
			}
			this.write(reportDir.getAbsolutePath());
			this.setReportDirectory(reportDir.getAbsolutePath());
		}
		if(reportingType.equals(ReportingType.HTML))
			writeHTMLReport(getReportDirectory());
		else
			writeExcelReport(getReportDirectory());
	}
	/**
	 * Generate report in Excel file.
	 */
	private void writeExcelReport(String reportDirectory){
		
		
	}
	/**
	 * Generate report in HTML file
	 */
	private void writeHTMLReport(String reportDirectory){
		try{
			FileOutputStream fos = new FileOutputStream(reportDirectory + "\\" + "report.xml");
			XStream xStream = new XStream();
			xStream.setMode(1001);
		    xStream.alias("TestSuite", TestSuite.class);
		    xStream.alias("TestCase", TestCase.class);
		    xStream.alias("TestStep", TestStep.class);
		    xStream.toXML(this.suite, fos);
		    fos.close();
		}catch(FileNotFoundException fnfe){
			this.write(fnfe);
		}catch(Exception e){
			this.write(e);
		}
	}


}
