package com.elektrimasinad.aho.client;

import java.util.Date;
import java.util.List;

import com.elektrimasinad.aho.shared.Device;

import com.elektrimasinad.aho.shared.Device;
import com.elektrimasinad.aho.shared.MaintenanceItem;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

public class DeviceEditPanel extends VerticalPanel {
	private DeviceMaintenancePanel deviceMaintenancePanel = new DeviceMaintenancePanel();
	private String selectedDevice;
	private List<MaintenanceItem> maintenanceList;
	//private DeviceCard createMaintenancePanelView = new DeviceCard();
	public DeviceEditPanel() {
		super();
	}
	public void createNewDeviceEditPanel(Device device) {
		AsyncCallback<List<MaintenanceItem>> getDeviceEntriesCallback = new AsyncCallback<List<MaintenanceItem>>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<MaintenanceItem> retrievedMaintenanceList) {
				// TODO Auto-generated method stub
				maintenanceList = retrievedMaintenanceList;
				displayMaintenanceEntries(maintenanceList);
			}
			
		};
		super.clear();
		DeviceTreeServiceAsync deviceTreeService = DeviceCard.getDevicetreeservice();
		selectedDevice = device.getDeviceKey();
		
		deviceTreeService.getMaintenanceEntries(selectedDevice, getDeviceEntriesCallback);
	}
	public void displayMaintenanceEntries(List<MaintenanceItem> entryList) {
		if (entryList.size() > 0) {
			Window.alert("stuff found");
		} else {
			Window.alert("stuff not found");
		}
	}
}