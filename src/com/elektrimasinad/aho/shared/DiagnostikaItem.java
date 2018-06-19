package com.elektrimasinad.aho.shared;

public class DiagnostikaItem {
    private String address;
    private String name;
    private String id;
    private String seade;
    private String kommentaar;
    private String marking;

    public DiagnostikaItem() {
    	
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
    
    public void setComment(String comment) {
		this.kommentaar = comment;
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
    public String getComment() {
    	return this.kommentaar;
	}
    
    public String getMarking() {
		return marking;
	}

	public void setMarking(String marking) {
		this.marking = marking;
	}
  
  } 
