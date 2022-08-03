package com.ea.datagen;

import java.util.List;

import com.ea.config.beans.TransportType;
import com.ea.config.equipment.EquipmentTemplate;
import com.ea.config.parameter.ParameterTemplate;
import com.ea.util.DBUtil;
import com.ea.util.StringUtil;

public class EquipmentProcessor {

	public List<EquipmentTemplate> readConfig() {

		DBUtil util = new DBUtil();
		util.initPool();
		StringUtil stringUtil = new StringUtil();
		List<EquipmentTemplate> equipmentList = util.getEquipmentTemplate();
		for (EquipmentTemplate equipment : equipmentList) {

			TransportType transType = util.getTransportDetails(equipment.getTransTypeID());
			equipment.setTransportType(transType);
			List<ParameterTemplate> pramConfigList = util.getParameterTemplate(equipment.getEqid());
			equipment.setParameters(pramConfigList);
			System.out.println(equipment.toString());
			System.out.println("\tStartTime is :" + stringUtil.getDateTime(equipment.getStarttime()));
			System.out.println("\tEndTime is : " + stringUtil.getDateTime(equipment.getEndtime()));
		}

		util.destroyPool();
		return equipmentList;

	}

	public static void main(String[] args) {

		EquipmentProcessor obj = new EquipmentProcessor();
		obj.readConfig();
	}
}
