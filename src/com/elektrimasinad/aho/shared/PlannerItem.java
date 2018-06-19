package com.elektrimasinad.aho.shared;

public class PlannerItem {
		private String kuup;
	    private String address;
	    private String name;
	    private String id;
	    private String seade;
	    private String tegevus;

	    public PlannerItem() {
	    	
	    }
	    public void setDates(String dates) {
	    	this.kuup = dates;
	    }
	    
	    public void setName(String name) {
	    	this.name = name;
	    }
	    
	    public void setDevice(String deviceName) {
			this.seade = deviceName;
		}
	    
	    public void setAddress(String deviceAddress) {
			this.address = deviceAddress;
		}
	    
	    public void setID(String id) {
			this.id = id;
		}
	    
	    public void setAction(String action) {
			this.tegevus = action;
		}
	    
	    public String getDates() {
	    	return this.kuup;
	    }
	    public String getName() {
	    	return this.name;
	    }
	    
	    public String getDevice() {
	    	return this.seade;
		}
	    
	    public String getAddress() {
	    	return this.address;
		}
	    
	    public String getID() {
	    	return this.id;
		}
	    public String getAction() {
	    	return this.tegevus;
		}
	    
}
