package com.ea.datagen.algo;

import com.ea.config.beans.DatapointRecord;
import com.ea.datagen.config.DataSimulationConfig;

import java.util.List;
import java.util.Map;

public interface DataSimulationStrategy {

	public Map<String, List<DatapointRecord>> simulateData(DataSimulationConfig simulationConfig);	
}