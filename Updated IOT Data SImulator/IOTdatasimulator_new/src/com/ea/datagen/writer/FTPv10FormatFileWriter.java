package com.ea.datagen.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ea.config.beans.DatapointRecord;

public class FTPv10FormatFileWriter implements Writer {

	@Override
	public void write(Map<String, List<DatapointRecord>> dataMap) {

		for (Entry<String, List<DatapointRecord>> entry : dataMap.entrySet()) {

			String key = entry.getKey();
			List<DatapointRecord> value = entry.getValue();
			try (FileWriter fileWriter = new FileWriter(key);
					BufferedWriter buffwriter = new BufferedWriter(fileWriter)) {
				for (DatapointRecord dataPoint : value) {
					buffwriter.write(dataPoint.getRecord());
					buffwriter.write(",");
					buffwriter.write("\n");
				}
				buffwriter.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
