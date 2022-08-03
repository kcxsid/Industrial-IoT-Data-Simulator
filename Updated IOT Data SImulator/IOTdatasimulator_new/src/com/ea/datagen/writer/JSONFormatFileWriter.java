package com.ea.datagen.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ea.config.beans.DatapointRecord;

public class JSONFormatFileWriter implements Writer {

	@Override
	public void write(Map<String, List<DatapointRecord>> datapointMap) {

		for (Entry<String, List<DatapointRecord>> entry : datapointMap.entrySet()) {

			String key = entry.getKey();
			List<DatapointRecord> value = entry.getValue();
			try (FileWriter fileWriter = new FileWriter(key);
					BufferedWriter buffwriter = new BufferedWriter(fileWriter)) {
				buffwriter.write(entry.getValue().toString());
				buffwriter.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
