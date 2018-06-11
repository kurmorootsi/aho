package com.elektrimasinad.aho.shared;

import java.io.Serializable;
import java.util.Date;

public class MaintenanceItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String desc;
	private String problemDesc;
	private String assignedTo;
	private String materials;
	private String notes;
	private Date completionDate;
	private String type;
	private String devKey;
	private Integer interval;
	
	public MaintenanceItem() {
		
	}
	public void setMaintenanceDevice(String deviceKey) {
		this.devKey = deviceKey;
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
		this.type = maintenanceState;
	}
	public void setMaintenanceAssignedTo() {
		this.assignedTo = "test";
	}
	public void setMaintenanceNotes(String notes) {
		this.notes = notes;
	}
	public void setMaintenanceMaterials(String requiredMaterials) {
		this.materials = requiredMaterials;
	}
	public void setMaintenanceCompleteDate(Date completeDate) {
		this.completionDate = completeDate;
	}
	public void setMaintenanceInterval(Integer interval) {
		this.interval = interval;
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
		return this.type;
	}
	public String getMaintenanceDevice() {
		return this.devKey;
	}
	public String getMaintenanceAssignedTo() {
		return this.assignedTo;
	}
	public String getMaintenanceNotes() {
		return this.notes;
	}
	public String getMaintenanceMaterials() {
		return this.materials;
	}
	public Date getMaintenanceCompleteDate() {
		return this.completionDate;
	}
	public Integer getMaintenanceInterval() {
		return this.interval;
	}
}
