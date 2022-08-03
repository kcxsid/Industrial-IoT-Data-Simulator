package com.ea.config.parameter;

import com.ea.config.beans.Pattern;

public class ParameterTemplate {
	private double noOfDataPoints;
	
	private int dataGenPatternType;
	private String tagname;
	private int capturefrequency, publishfrequency;
	private int mpidvalue;
	private Pattern pattern;
	private ParameterType paramType;

	public int getMpidvalue() {
		return mpidvalue;
	}

	public void setMpidvalue(int mpidvalue) {
		this.mpidvalue = mpidvalue;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public int getCapturefrequency() {
		return capturefrequency;
	}

	public void setCapturefrequency(int capturefrequency) {
		this.capturefrequency = capturefrequency;
	}

	public int getPublishfrequency() {
		return publishfrequency;
	}

	public void setPublishfrequency(int publishfrequency) {
		this.publishfrequency = publishfrequency;
	}

	public double getNoOfDataPoints() {
		return noOfDataPoints;
	}

	public void setNoOfDataPoints(double noOfDataPoints) {
		this.noOfDataPoints = noOfDataPoints;
	}

	

	public int getDataGenPatternType() {
		return dataGenPatternType;
	}

	public void setDataGenPatternType(int dataGenPatternType) {
		this.dataGenPatternType = dataGenPatternType;
	}

	@Override
	public String toString() {
		return "\nParameter ID is: " + mpidvalue + " Tag name is:" + tagname ;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public ParameterType getParamType() {
		return paramType;
	}

	public void setParamType(ParameterType paramType) {
		this.paramType = paramType;
	}

}
