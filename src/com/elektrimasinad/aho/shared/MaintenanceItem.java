package com.elektrimasinad.aho.shared;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class MaintenanceItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String keyString;
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
	private Double cost;
	private Integer downtime;
	private Integer timeSpent;
	private Integer upload;
	
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
	public void setMaintenanceCost(Double cost) {
		this.cost = cost;
	}
	public void setMaintenanceDowntime(Integer downtime) {
		this.downtime = downtime;
	}
	public void setMaintenanceTimeSpent(Integer timeSpent) {
		this.timeSpent = timeSpent;
	}
	/*public void setMaintenanceImage(Integer(Integer.toString(this.upload)) {
		this.upload = upload;
	}*/
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
	public Double getMaintenanceCost() {
		return this.cost;
	}
	public Integer getMaintenanceDowntime() {
		return this.downtime;
	}
	public Integer getMaintenanceTimeSpent() {
		return this.timeSpent;
	}
	/*public Integer getMaintenanceImage() {
		return this.upload;
	}*/
	public HorizontalPanel getAllInserts() {
		HorizontalPanel data = new HorizontalPanel();
		Label Data1 = new Label(this.name);
		Label Data2 = new Label(this.desc);
		Label Data3 = new Label(this.problemDesc);
		Label Data4 = new Label(this.notes);
		Label Data5 = new Label(this.materials);
		Label Data6 = new Label(Integer.toString(this.interval));
		data.add(Data1);
		data.add(Data2);
		data.add(Data3);
		data.add(Data4);
		data.add(Data5);
		data.add(Data6);
		return data;
	}
}
