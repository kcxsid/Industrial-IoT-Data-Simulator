package com.ea.config.beans;

import com.ea.util.StringUtil;

public class DatapointRecord {

	private long timestamp;
	private double value;

	public DatapointRecord(long timestamp, double value) {
		this.timestamp = timestamp;
		this.value = value;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public double getValue() {
		return value;
	}

	public String toString() { // returns a string of the timestamp and value in the format of a json
		return "[" + timestamp + "," + value + "]";
	}

	public String getRecord() { // returns a string of the timestamp and value in the format of a csv
		return "[" + StringUtil.getLoggerDateTime(timestamp) + "," + value + "]";
	}
}
