package com.ea.datagen.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ea.config.beans.DatapointRecord;
import com.ea.datagen.config.DataSimulationConfig;
import com.ea.util.StringUtil;

public class AnalogDataSimulationStrategy implements DataSimulationStrategy {
	@Override
	public Map<String, List<DatapointRecord>> simulateData(DataSimulationConfig simulationConfig) {

		double value = 0.0, temp = 0.0, value1 = 0.0;
		int minvalue = simulationConfig.getPattern().getMinValue();
		int publishfrequency = simulationConfig.getParameterTemplate().getPublishfrequency() * 1000;
		int maxvalue = simulationConfig.getPattern().getMaxValue();
		int mindev = simulationConfig.getPattern().getMinDeviation();
		int maxdev = simulationConfig.getPattern().getMaxDeviation();
		String filePath = simulationConfig.getFilePath();
		int capturefrequencysecs = simulationConfig.getParameterTemplate().getCapturefrequency() * 1000;
		long strtDT = simulationConfig.getStart(); // start date
		long endtime = simulationConfig.getEnd();
		int i = 0;
		int datapoints = (int) ((endtime - strtDT) / capturefrequencysecs);

		List<DatapointRecord> datarecord = new ArrayList<>();

		Map<String, List<DatapointRecord>> data = new TreeMap<>();
		int fileCounter = 0;
		String fileName = null;

		double a[] = new double[datapoints];
		for (i = 0; i < datapoints; i++) {
			if (i > 0) {
				value = temp;
			}
			if (value <= minvalue) {
				while (value <= minvalue) {
					value = minvalue + (int) ((Math.random() * (maxdev-mindev) + mindev));
				}
			}
			if (value >= maxvalue) {
				while (value > maxvalue) {
					value = maxvalue - (int) ((Math.random() * (maxdev-mindev) + mindev));
				}
			}

			value1 = (int) ((Math.random() * (maxdev-mindev)) + mindev);
			a[i] = value + value1;
			temp = a[i];

			if (a[i] < minvalue) {
				a[i] = minvalue;
			} else if (a[i] > maxvalue) {
				a[i] = maxvalue;
			}

			DatapointRecord datapointrecord = new DatapointRecord(strtDT, a[i]);
			datarecord.add(datapointrecord);

			if (fileCounter == 0 || fileCounter > publishfrequency) {
				fileName = filePath + simulationConfig.getEquipmentID() + "_"
						+ simulationConfig.getParameterTemplate().getTagname() + "_"
						+ StringUtil.getLoggerDateTime(strtDT) + ".csv";
				fileCounter = 0;
			}
			addDataIntoMap(fileName, strtDT, a[i], data);
			strtDT = strtDT + capturefrequencysecs;
			fileCounter = fileCounter + capturefrequencysecs;
		}
		System.out.println("\n" + data);
		return data;
	}

	public static void addDataIntoMap(String fileName, long timestamp, double value,
			Map<String, List<DatapointRecord>> dataMap) {
		DatapointRecord datapoint = new DatapointRecord(timestamp, value);
		List<DatapointRecord> dataList = dataMap.get(fileName);
		if (dataList == null) {
			dataList = new ArrayList<>();
		}
		dataList.add(datapoint);
		dataMap.put(fileName, dataList);
	}
}
