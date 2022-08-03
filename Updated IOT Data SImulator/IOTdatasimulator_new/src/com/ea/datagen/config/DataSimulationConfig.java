package com.ea.datagen.config;

import com.ea.config.beans.Pattern;
import com.ea.config.parameter.ParameterTemplate;
import com.ea.datagen.algo.DataSimulationStrategy;

public class DataSimulationConfig {

	public DataSimulationStrategy getStrategy() {
		return strategy;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public ParameterTemplate getParameterTemplate() {
		return parameterTemplate;
	}

	public int getEquipmentID() {
		return equipmentID;
	}

	private DataSimulationStrategy strategy;
	private Pattern pattern;
	private ParameterTemplate parameterTemplate;
	private int equipmentID;
	private long start;
	private long end;
	private String filePath;

	public long getStart() {
		return start;
	}

	public long getEnd() {
		return end;
	}

	public DataSimulationConfig(DataSimulationStrategy strategy, Pattern pattern, ParameterTemplate parameterTemplate,
			int equipmentID, long start, long end, String filePath) {
		this.strategy = strategy;
		this.pattern = pattern;
		this.parameterTemplate = parameterTemplate;
		this.equipmentID = equipmentID;
		this.start = start;
		this.end = end;
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

}
