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
	private MaintenanceItem maintenanceItem = new MaintenanceItem();
	private VerticalPanel displayDataLog;
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
		
		displayDataLog = new VerticalPanel();
	}
	public void displayMaintenanceEntries(List<MaintenanceItem> entryList) {
			for (int i = 0; i < entryList.size(); i++) {
				if (entryList.size() > 0) {
					MaintenanceItem item = entryList.get(i);
					HorizontalPanel dataLog = new HorizontalPanel();
					Label data1 = new Label(item.getMaintenanceName());
					Label data2 = new Label(item.getMaintenanceDescription());
					Label data3 = new Label(item.getMaintenanceProblemDescription());
					Label data4 = new Label(item.getMaintenanceNotes());
					Label data5 = new Label(item.getMaintenanceMaterials());
					Button editButton = new Button("Muuda", new ClickHandler() {

						@Override
						public void onClick(ClickEvent arg0) {
							// TODO Auto-generated method stub
							Window.alert("muuda");
						}
						
					});
					data1.setStyleName("maintEntryRowItem");
					data2.setStyleName("maintEntryRowItem");
					data3.setStyleName("maintEntryRowItem");
					data4.setStyleName("maintEntryRowItem");
					data5.setStyleName("maintEntryRowItem");
					editButton.setStyleName("maintEntryRowItem");
					dataLog.add(data1);
					dataLog.add(data2);
					dataLog.add(data3);
					dataLog.add(data4);
					dataLog.add(data5);
					dataLog.add(editButton);
					dataLog.setStyleName("maintEntryRow");
					displayDataLog.add(dataLog);
				} else
					Window.alert("stuff not found");
			}
			add(displayDataLog);
	}
}
