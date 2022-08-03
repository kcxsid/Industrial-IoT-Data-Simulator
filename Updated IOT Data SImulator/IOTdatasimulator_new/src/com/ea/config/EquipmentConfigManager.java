package com.ea.config;

import java.util.Map;

import com.ea.config.equipment.EquipmentTemplate;

/**
 * This reads all the configuration specific to equipment and its parameters.
 */
public interface EquipmentConfigManager {

    public Map<String,EquipmentTemplate> getAllEquipmentTemplateConfigs();

    public EquipmentTemplate getEquipmentTemplate(String equipmentId);


}
