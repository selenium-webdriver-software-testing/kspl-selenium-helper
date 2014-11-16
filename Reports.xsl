<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited by XMLSpy® -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <head><title><xsl:value-of select="TestSuite/TestSuiteName"/> - Report</title></head>
  <body>
  <h2><xsl:value-of select="TestSuite/TestSuiteName"/></h2>
  <p>Start time: <xsl:value-of select="TestSuite/TestDateTime"/></p>
  <p><xsl:value-of select="count(TestSuite/TestCases/TestCase)"></xsl:value-of> tests.</p>
  <hr />
  <xsl:for-each select="TestSuite/TestCases/TestCase">
  	<p>Test Case ID: <xsl:value-of select="TestCaseId"/></p>
    <p>Test case name: <xsl:value-of select="TestCaseName"/></p>
    <p>Test Case Status: 
    	<xsl:choose>
    	  <xsl:when test="count(TestSteps/TestStep[Result='false']) > 0">
    	     <font color="red">Failed</font>
    	  </xsl:when>
   		  <xsl:otherwise><font color="green">Passed</font></xsl:otherwise>
   		</xsl:choose>
    </p>
    <p>Execution time: <xsl:value-of select="ExecutionTime div 1000"></xsl:value-of> sec</p>
    <p>Test Case Description: <xsl:value-of select="TestCaseDescription"/></p>
    <p>Execution Environment: <xsl:value-of select="ExecutionEnvironment"/></p>
    <p>Application Base URL: <xsl:value-of select="ParentURL"/></p>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th style="text-align:left">StepNumber</th>
        <th style="text-align:left">Description</th>
        <th style="text-align:left">Expected Data</th>
        <th style="text-align:left">Actual data</th>
        <th style="text-align:left">Screenshot</th>
        <th style="text-align:left">Result</th>
      </tr>
      <xsl:for-each select="TestSteps/TestStep">
      <tr>
        <td><xsl:value-of select="StepNumber"/></td>
        <td><xsl:value-of select="Description"/></td>
        <td><xsl:value-of select="ExceptedData"/></td>
        <td><xsl:value-of select="ActualData"/></td>
        
        <td><a href="{ImagePath}"><xsl:value-of select="ImageName"/></a></td>
        <td>
	        <xsl:choose>
	    	  <xsl:when test="Result='false'">
	    	     <font color="red">Failed</font>
	    	  </xsl:when>
	   		  <xsl:otherwise><font color="green">Passed</font></xsl:otherwise>
	   		</xsl:choose>
        </td>
      </tr>
      </xsl:for-each>
    </table>
    <hr />
   </xsl:for-each>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>