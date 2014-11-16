package com.kagrana.DTO;

public class TestStep
{
  private int StepNumber;
  private String Description;
  private String ExceptedData;
  private String ActualData;
  private String ImageName;
  private String ImagePath;
  private boolean Result;
  
  public TestStep(){
	  this.Description = "";
	  this.ExceptedData = "";
	  this.ActualData = "";
	  this.ImageName = "";
	  this.ImagePath = "";
	  this.Result = true;
  }
  public final int getStepNumber()
  {
    return this.StepNumber;
  }
  
  public final void setStepNumber(int value)
  {
    this.StepNumber = value;
  }
  
  public final String getDescription()
  {
    return this.Description;
  }
  
  public final void setDescription(String value)
  {
    this.Description = value;
  }
  
  public final String getExceptedData()
  {
    return this.ExceptedData;
  }
  
  public final void setExceptedData(String value)
  {
    this.ExceptedData = value;
  }
  
  public final String getActualData()
  {
    return this.ActualData;
  }
  
  public final void setActualData(String value)
  {
    this.ActualData = value;
  }
  
  public final String getImageName()
  {
    return this.ImageName;
  }
  
  public final void setImageName(String value)
  {
    this.ImageName = value;
  }
  
  public final String getImagePath()
  {
    return this.ImagePath;
  }
  
  public final void setImagePath(String value)
  {
    this.ImagePath = value;
  }
  
  public final boolean getResult()
  {
    return this.Result;
  }
  
  public final void setResult(boolean value)
  {
    this.Result = value;
  }
}