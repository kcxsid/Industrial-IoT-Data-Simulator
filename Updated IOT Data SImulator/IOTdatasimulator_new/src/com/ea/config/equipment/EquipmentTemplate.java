package com.ea.config.equipment;

import java.util.List;

import com.ea.config.beans.TransportType;
import com.ea.config.parameter.ParameterTemplate;

public class EquipmentTemplate {
	private int eqid;
	List<ParameterTemplate> parameters;
	private int transTypeID;
	private TransportType transportType;
	String starttime, endtime;

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getEqid() {
		return eqid;
	}

	public void setEqid(int eqid) {
		this.eqid = eqid;
	}

	public List<ParameterTemplate> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterTemplate> parameters) {
		this.parameters = parameters;
	}

	public TransportType getTransportType() {
		return transportType;
	}

	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}

	@Override
	public String toString() {
		return "\nFor Equipment : " + eqid + " -> " + "Parameter/s information is: " + parameters;
	}

	public int getTransTypeID() {
		return transTypeID;
	}

	public void setTransTypeID(int transTypeID) {
		this.transTypeID = transTypeID;
	}
}
