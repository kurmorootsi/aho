package com.elektrimasinad.aho.client;

import com.elektrimasinad.aho.shared.Device;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DeviceMaintenancePanel extends VerticalPanel {
	//private Device device;
	public DeviceMaintenancePanel() {
		super();
	}
	public void createNewDeviceMaintenancePanel() {
		super.clear();
		
		HorizontalPanel headerPanel = AhoWidgets.createContentHeader("test");
		add(headerPanel);
	}
}
