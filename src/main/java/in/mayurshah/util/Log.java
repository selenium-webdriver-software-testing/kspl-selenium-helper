package in.mayurshah.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import in.mayurshah.DTO.TestStep;
import org.apache.commons.io.FileUtils;

import in.mayurshah.DTO.TestCase;
import in.mayurshah.DTO.TestSuite;
import in.mayurshah.reporting.ReportingType;
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
				File reportDir = new File(currentDir.getAbsolutePath() + File.separator+ "reports"+File.separator);
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
	 * @param reportDirectory -Contains location where reports has to be stored
	 */
	public void setReportDirectory(String reportDirectory){
		this.reportDirectory = reportDirectory;
	}
	/**
	 * This function will add TestCase to TestSuite
	 * @param testCase -Initiate the test case
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
		String xmlFileName = reportDirectory + File.separator+ "report.xml";
		String htmlFileName = reportDirectory + File.separator + "report.html";
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
	public void zipAndEmailFile() throws IOException{
		File directoryToZip = new File(getReportDirectory());
		ZipUtil zipUtil = new ZipUtil(this);
		List<File> fileList = new ArrayList<File>();
		this.write(("---Getting references to all files in: " + directoryToZip.getCanonicalPath()));
		zipUtil.getAllFiles(directoryToZip, fileList);
		this.write("---Creating zip file");
		String fileAttachment = zipUtil.writeZipFile(directoryToZip, fileList);
		this.write("---Done");
		EmailUtil emailUtil = new EmailUtil(this);
		emailUtil.sendEmail("mail@mayurshah.in", fileAttachment);
	}

}
