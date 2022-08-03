package com.ea.datagen.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.*;

import com.ea.config.beans.DatapointRecord;
import com.ea.datagen.config.DataSimulationConfig;
import com.ea.util.StringUtil;

public class DigitalDataSimulationStrategy implements DataSimulationStrategy {
	public int generatebreakdown(int high, int low) {
		int breakdown;
		breakdown = (int) ((Math.random() * (high - low)) + low);
		return breakdown;
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

	@Override
	public Map<String, List<DatapointRecord>> simulateData(DataSimulationConfig simulationConfig) {

		int capturefrequencysecs = simulationConfig.getParameterTemplate().getCapturefrequency();
		int publishfrequency = simulationConfig.getParameterTemplate().getPublishfrequency();
		long starttime = simulationConfig.getStart();
		long endtime = simulationConfig.getEnd();
		int zoccurmin = simulationConfig.getPattern().getZeroOccMin();
		int zoccurmax = simulationConfig.getPattern().getZeroOccMax();
		int random = generatebreakdown(zoccurmin, zoccurmax);
		int ztimemax = simulationConfig.getPattern().getMaxValue();
		int ztimemin = simulationConfig.getPattern().getMinValue();
		String filePath = simulationConfig.getFilePath();
		long time = endtime - starttime; // duration of the breakdown.
		long start[] = new long[random]; // start time of each breakdown
		long end[] = new long[random]; // end time of each breakdown
		int counter = 0; // variable that is used for condition checking
		int j;// loop variables
		

		if (ztimemax * random >= time) {
			random = zoccurmin; 
		}
		if(zoccurmin * ztimemax >=time){
			System.exit(0);
		}
		
		
		long gen[] = new long[random]; // generated time values of each breakdown
		System.out.println("Breakdown value :  " + random);
		Map<String, List<DatapointRecord>> dataMap = new TreeMap<>();
		if (ztimemin > ztimemax) {
			return null;
		}
		while (counter < (random - 1)) {
			counter = 0;
			for (j = 0; j < random; j++) {
				start[j] = (int) (Math.random() * time); // generate a random value of start time
				gen[j] = ((int) ((Math.random() * (ztimemax - ztimemin)) + ztimemin)) * (1000 * 60);

			}
			Arrays.sort(start);
			for (j = 0; j < random; j++) {
				end[j] = start[j] + gen[j];
			}
			for (j = 1; j < random; j++) {

				if (start[j] > end[(j - 1)] && end[j] <= time) {

					counter++;
				}
			}
		}

		int k, l;
		long[] finalarray = new long[2 * random];

		for (l = 0, j = 0; l < random; l++) {
			finalarray[j++] = starttime + start[l];
			finalarray[j++] = starttime + end[l];
		}

		String fileName = null;
		long fileCounter = starttime;

		int newvalue = 0;
		l = 0;
		long lastindxVal = finalarray[finalarray.length - 1];
		long track = starttime;
		while (track <= endtime) {
			if (fileCounter == 0 || fileCounter > (publishfrequency * 1000)) {
				fileCounter = 0;
				fileName = filePath + simulationConfig.getEquipmentID() + "_"
						+ simulationConfig.getParameterTemplate().getTagname() + "_"
						+ StringUtil.getLoggerDateTime(track) + ".csv";
			}
			k = l + 1;
			newvalue = 0;
			if (track > lastindxVal)
				addDataIntoMap(fileName, track, 1.0, dataMap);
			else if (track >= finalarray[l] && track <= finalarray[k] && track <= lastindxVal) {
				addDataIntoMap(fileName, track, 0.0, dataMap);
			} else if (track < finalarray[l] && track < finalarray[k] && track <= lastindxVal) {
				addDataIntoMap(fileName, track, 1.0, dataMap);
			} else {
				l += 2;
				newvalue++;
			}
			if (newvalue == 0) {
				track += (capturefrequencysecs * 1000);
				fileCounter += (capturefrequencysecs * 1000);
			}

		}
		System.out.println("\n" +dataMap);
		return dataMap;

	}

}
