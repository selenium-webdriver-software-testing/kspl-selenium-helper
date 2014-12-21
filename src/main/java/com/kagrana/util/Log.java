package com.kagrana.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;

import com.kagrana.DTO.TestCase;
import com.kagrana.DTO.TestStep;
import com.kagrana.DTO.TestSuite;
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
	public void setTestSuiteName(String testSuiteName){
		this.suite.setTestSuiteName(testSuiteName);
	}
	public String getReportDirectory() throws IOException {
		if(reportDirectory == null){
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
		String xmlFileName = reportDirectory + "\\" + "report.xml";
		String htmlFileName = reportDirectory + "\\" + "report.html";
		try{
			
			FileOutputStream fos = new FileOutputStream(xmlFileName);
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
	    try
	    {
	    	PropertyFileManager pfm = new PropertyFileManager(this);
	      TransformerFactory tFactory = TransformerFactory.newInstance();
	      Transformer transformer = null;
	      Source xslFileSource = null;
	      String xslFileName = pfm.getProperty("reportXSLFileName");
	      InputStream inputStream = new FileInputStream(xslFileName);
	      xslFileSource = new StreamSource(inputStream);
	      transformer = tFactory.newTransformer(xslFileSource);
	      transformer.transform(new StreamSource(
	        xmlFileName), new StreamResult(
	        new FileOutputStream(htmlFileName)));
	    }
	    catch (TransformerConfigurationException e)
	    {
	      this.write(e);
	    }
	    catch (FileNotFoundException e)
	    {
	    	this.write(e);
	    }
	    catch (TransformerException e)
	    {
	    	this.write(e);
	    }
	    catch (Exception e)
	    {
	    	this.write(e);
	    }
	}
	

}
