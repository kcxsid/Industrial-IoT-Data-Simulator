package com.ea.datagen.writer;

import com.ea.config.beans.DatapointRecord;

import java.util.List;
import java.util.Map;

public interface Writer {

	public void write(Map<String, List<DatapointRecord>> datapointMap);

}
