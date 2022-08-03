package com.ea.datagen;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ea.config.beans.DatapointRecord;
import com.ea.datagen.algo.DataSimulationStrategy;
import com.ea.datagen.config.DataSimulationConfig;
import com.ea.datagen.writer.FTPv10FormatFileWriter;
import com.ea.datagen.writer.Writer;

public class DataSimulatorWorker implements Runnable {

	private List<Writer> writer;
	private DataSimulationConfig simulationConfig;

	public DataSimulatorWorker(List<Writer> list, DataSimulationConfig simulationConfig) {

		this.writer = list;
		this.simulationConfig = simulationConfig;
	}

	@Override
	public void run() {

		System.out.println("processing in run");
		try {
			DataSimulationStrategy strategy = simulationConfig.getStrategy();
			Map<String, List<DatapointRecord>> dataMap = strategy.simulateData(simulationConfig);
			List<DatapointRecord> dataSet = new ArrayList<>();
			for (Entry<String, List<DatapointRecord>> entry : dataMap.entrySet()) {
				dataSet.addAll(entry.getValue());
			}
			System.out.println("DATA");
			System.out.println(dataSet.toString());
			for (Writer formatWriter : writer) {

				if (formatWriter instanceof FTPv10FormatFileWriter) {
					formatWriter.write(dataMap);
				} else {
					Map<String, List<DatapointRecord>> data = new HashMap<>();
					data.put(simulationConfig.getFilePath() + File.separator + "Data.json", dataSet);
					formatWriter.write(data);
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		System.out.println("Done for " + simulationConfig.getEquipmentID() + ", parameter"
				+ simulationConfig.getParameterTemplate().getMpidvalue());
	}

}
