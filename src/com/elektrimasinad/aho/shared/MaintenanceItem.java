package com.elektrimasinad.aho.shared;

import java.io.Serializable;

public class MaintenanceItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String desc;
	private String problemDesc;
	private String state;
	
	public MaintenanceItem() {
		
	}
	
	public void setMaintenanceName(String maintenanceName) {
		this.name = maintenanceName;
	}
	public void setMaintenanceDescription(String maintenanceDesc) {
		this.desc = maintenanceDesc;
	}
	public void setMaintenanceProblemDescription(String maintenanceProblemDesc) {
		this.problemDesc = maintenanceProblemDesc;
	}
	public void setMaintenanceState(String maintenanceState) {
		this.state = maintenanceState;
	}
	public String getMaintenanceName() {
		return this.name;
	}
	public String getMaintenanceDescription() {
		return this.desc;
	}
	public String getMaintenanceProblemDescription() {
		return this.problemDesc;
	}
	public String getMaintenanceState() {
		return this.state;
	}
}
