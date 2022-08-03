package com.ea.util;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ea.config.beans.Pattern;
import com.ea.config.beans.TransportType;
import com.ea.config.equipment.EquipmentTemplate;
import com.ea.config.parameter.ParameterTemplate;
import com.ea.config.parameter.ParameterType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBUtil {

	private HikariDataSource pooledDatasource;

	public void destroyPool() {
		pooledDatasource.close();
	}

	public void initPool() {
		HikariConfig config = new HikariConfig(System.getProperty("user.dir") + File.separator + "db.properties");
		long startMillis = System.currentTimeMillis();
		pooledDatasource = new HikariDataSource(config);
		long endMillis = System.currentTimeMillis();
		System.out.println("Time taken to initialize hikari data source (ms) : " + (endMillis - startMillis));
	}

	public TransportType getTransportDetails(int transTypeID) {

		TransportType transType = null;
		ResultSet result = null;
		try (Connection con = pooledDatasource.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(
						"SELECT TYPENAME,HOSTNAME,PATHNAME,USERNAME,PASSWORD FROM TRANSPORTTYPE WHERE TRANSPORTTYPEID=?")) {

			preparedStatement.setInt(1, transTypeID);
			result = preparedStatement.executeQuery();
			while (result.next()) {

				transType = new TransportType();
				transType.setTypeName(result.getString("TYPENAME"));
				transType.setHostName(result.getString("HOSTNAME"));
				transType.setPathName(result.getString("PATHNAME"));
				transType.setUsername(result.getString("USERNAME"));
				transType.setPassd(result.getString("PASSWORD"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return transType;
	}

	public List<ParameterTemplate> getParameterTemplate(int eqID) {

		List<ParameterTemplate> returnList = new ArrayList<>();
		ParameterTemplate paraconfig = null;
		Pattern pattern = null;
		ResultSet result = null;
		try (Connection con = pooledDatasource.getConnection();

				PreparedStatement preparedStatement = con
						.prepareStatement("SELECT MPID,TAGNAME,CAPTUREFREQUENCYINSECS,PUBLISHFREQUENCYINSECS,"
								+ "MINIMUMVALUE,MAXIMUMVALUE,MINDEVIATION,MAXDEVIATION,PATTERNTYPE,KEYVALUES,ZOCCURMIN,ZOCCURMAX,PARAMETERTYPE"
								+ " from PARAMETERCONFIG JOIN PATTERNCONFIG ON PARAMETERCONFIG.PATTERNID = PATTERNCONFIG.PATTERNID WHERE EQID = ? order by MPID")) {

			preparedStatement.setInt(1, eqID);
			result = preparedStatement.executeQuery();
			while (result.next()) {

				paraconfig = new ParameterTemplate();
				paraconfig.setMpidvalue(result.getInt("MPID"));
				paraconfig.setTagname(result.getString("TAGNAME"));
				paraconfig.setCapturefrequency(result.getInt("CAPTUREFREQUENCYINSECS"));
				paraconfig.setPublishfrequency(result.getInt("PUBLISHFREQUENCYINSECS"));
				boolean paramType = result.getBoolean("PARAMETERTYPE");
				if (paramType) {
					paraconfig.setParamType(ParameterType.DIGITAL);
				} else {
					paraconfig.setParamType(ParameterType.ANALOG);
				}
				pattern = new Pattern();
				pattern.setKeyValue(result.getString("KEYVALUES"));
				pattern.setPatternType(result.getInt("PATTERNTYPE"));
				pattern.setZeroOccMin(result.getInt("ZOCCURMIN"));
				pattern.setZeroOccMax(result.getInt("ZOCCURMAX"));
				pattern.setMinValue((result.getInt("MINIMUMVALUE")));
				pattern.setMaxValue((result.getInt("MAXIMUMVALUE")));
				pattern.setMinDeviation((result.getInt("MINDEVIATION")));
				pattern.setMaxDeviation(result.getInt("MAXDEVIATION"));

				paraconfig.setPattern(pattern);
				returnList.add(paraconfig);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return returnList;
	}

	public List<EquipmentTemplate> getEquipmentTemplate() {

		List<EquipmentTemplate> returnList = new ArrayList<>();
		EquipmentTemplate equipment = null;
		ResultSet result = null;
		try (Connection con = pooledDatasource.getConnection();
				PreparedStatement preparedStatement = con
						.prepareStatement("SELECT * from EQUIPMENTCONFIG  WHERE ISENABLED = 1 ")) {

			result = preparedStatement.executeQuery();
			while (result.next()) {

				equipment = new EquipmentTemplate();
				equipment.setEqid(result.getInt("EQID"));
				equipment.setStarttime((result.getString("STARTTIME")));
				equipment.setEndtime((result.getString("ENDTIME")));
				int transTypeID = result.getInt("TRANSPORTTYPEID");
				equipment.setTransTypeID(transTypeID);
				returnList.add(equipment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return returnList;
	}

}
