package com.ea.datagen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ea.config.equipment.EquipmentTemplate;
import com.ea.config.parameter.ParameterTemplate;
import com.ea.config.parameter.ParameterType;
import com.ea.datagen.algo.AnalogDataSimulationStrategy;
import com.ea.datagen.algo.DigitalDataSimulationStrategy;
import com.ea.datagen.config.DataSimulationConfig;
import com.ea.datagen.writer.FTPv10FormatFileWriter;
import com.ea.datagen.writer.JSONFormatFileWriter;
import com.ea.datagen.writer.Writer;
import com.ea.util.StringUtil;

public class DataSimulator {

	Properties prop;
	FileInputStream fileInput;

	public void submit() throws InterruptedException, FileNotFoundException, IOException {
		EquipmentProcessor eqReader = new EquipmentProcessor();

		prop = new Properties();
		prop.load(new FileInputStream("application.properties"));
		List<EquipmentTemplate> equipmentTemplates = eqReader.readConfig();
		// Builder buildClass;
		String filePath = prop.getProperty("filePath");
		String fileType = prop.getProperty("fileType");
		Builder buildClass = new Builder().forEquipmentTemplates(equipmentTemplates);
		buildClass.setFilePath(filePath);
		Map<String, DataSimulationConfig> dataGenerationConfigMap = buildClass.build();
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		for (Entry<String, DataSimulationConfig> mapEntry : dataGenerationConfigMap.entrySet()) {

			System.out.println("processing");
			executorService.submit(new DataSimulatorWorker(buildClass.withOutputFormatter(fileType), mapEntry.getValue()));
		}
		executorService.shutdown();
		System.out.println("Is shutdown:" + executorService.isShutdown());
		System.out.println("Is terminated:" + executorService.isTerminated());
		executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
		System.out.println("Is terminated:" + executorService.isTerminated());
	}

	public static class Builder {
		private static Builder INSTANCE = new Builder();
		private static List<EquipmentTemplate> templates = new ArrayList<>();
		private static Map<String, DataSimulationConfig> dataGenerationConfigMap = new HashMap<>();
		private static String filePath;

		public Builder getInstance() {
			return this;
		}

		public Builder forEquipmentTemplates(List<EquipmentTemplate> equipmentTemplates) {
			INSTANCE.templates = equipmentTemplates;
			return this;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public List<Writer> withOutputFormatter(String fileType) {

			List<Writer> writerList = new ArrayList<>();
			if (fileType.equals("0")) {
				writerList.add(new FTPv10FormatFileWriter());
			} else if (fileType.equals("1")) {
				writerList.add(new JSONFormatFileWriter());
			} else {
				writerList.add(new FTPv10FormatFileWriter());
				writerList.add(new JSONFormatFileWriter());
			}

			return writerList;
		}

		public Map<String, DataSimulationConfig> build() {
			if (templates.isEmpty()) {
				throw new RuntimeException("Invalid set of arguments. Equipment templates are not configured");
			}

			int parameterCounter = 0;
			List<EquipmentTemplate> eqList = this.templates;
			for (EquipmentTemplate equipmentTemplate : eqList) {
				List<ParameterTemplate> parameterList = equipmentTemplate.getParameters();
				for (ParameterTemplate parameterTemplate : parameterList) {
					DataSimulationConfig simulationConfig;
					if (parameterTemplate.getParamType() == ParameterType.ANALOG) {
						simulationConfig = new DataSimulationConfig(new AnalogDataSimulationStrategy(),
								parameterTemplate.getPattern(), parameterTemplate, equipmentTemplate.getEqid(),
								StringUtil.getDateTime(equipmentTemplate.getStarttime()).getTime(),
								StringUtil.getDateTime(equipmentTemplate.getEndtime()).getTime(), filePath);
					} else {
						simulationConfig = new DataSimulationConfig(new DigitalDataSimulationStrategy(),
								parameterTemplate.getPattern(), parameterTemplate, equipmentTemplate.getEqid(),
								StringUtil.getDateTime(equipmentTemplate.getStarttime()).getTime(),
								StringUtil.getDateTime(equipmentTemplate.getEndtime()).getTime(), filePath);

					}
					dataGenerationConfigMap.put("ParameterCounter" + parameterCounter++, simulationConfig);
				}
			}

			return dataGenerationConfigMap;
		}
	}

}
