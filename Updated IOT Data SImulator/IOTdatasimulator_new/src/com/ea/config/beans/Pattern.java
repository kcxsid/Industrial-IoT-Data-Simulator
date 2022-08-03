package com.ea.config.beans;

public class Pattern {

	private int patternType;
	private String keyValue;
	private int minValue;
	private int maxValue;
	private int minDeviation;
	private int maxDeviation;
	private int zeroOccMin;
	private int zeroOccMax;

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getMinDeviation() {
		return minDeviation;
	}

	public void setMinDeviation(int minDeviation) {
		this.minDeviation = minDeviation;
	}

	public int getMaxDeviation() {
		return maxDeviation;
	}

	public void setMaxDeviation(int maxDeviation) {
		this.maxDeviation = maxDeviation;
	}

	public int getPatternType() {
		return patternType;
	}

	public void setPatternType(int patternType) {
		this.patternType = patternType;
	}

	public String getKeyValue() {
		if (keyValue == null) {
			return "";
		}

		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public int getZeroOccMin() {
		return zeroOccMin;
	}

	public void setZeroOccMin(int zeroOccMin) {
		this.zeroOccMin = zeroOccMin;
	}

	public int getZeroOccMax() {
		return zeroOccMax;
	}

	public void setZeroOccMax(int zeroOccMax) {
		this.zeroOccMax = zeroOccMax;
	}

}
