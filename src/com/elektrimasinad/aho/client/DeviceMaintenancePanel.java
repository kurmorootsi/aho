package com.elektrimasinad.aho.client;

import com.elektrimasinad.aho.shared.Device;
import com.elektrimasinad.aho.shared.MaintenanceItem;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;

public class DeviceMaintenancePanel extends VerticalPanel {
	//private Device device;
	public DeviceMaintenancePanel() {
		super();
	}
	public void createNewDeviceMaintenancePanel(Device device) {
		super.clear();
		DeviceTreeServiceAsync deviceTreeService = DeviceCard.getDevicetreeservice();
		HorizontalPanel headerPanel = AhoWidgets.createContentHeader("Seadme " + device.getDeviceName() + " hooldustoo");
		add(headerPanel);
		
		VerticalPanel RadioPanel = new VerticalPanel();
		RadioPanel.setStyleName("aho-panel1");
		RadioPanel.setWidth("100%");
		HorizontalPanel RadioPanel1 = new HorizontalPanel();
		Label rb00 = new Label("Perioodiline");
		RadioButton rb0 = new RadioButton("myRadioGroup");
		rb0.setFormValue("periodic");
		HorizontalPanel RadioPanel2 = new HorizontalPanel();
		Label rb11 = new Label("Plaaniline");
	    RadioButton rb1 = new RadioButton("myRadioGroup");
	    rb1.setFormValue("onetime");
	    HorizontalPanel RadioPanel3 = new HorizontalPanel();
	    Label rb22 = new Label("Teostatud");
	    RadioButton rb2 = new RadioButton("myRadioGroup");
	    rb2.setFormValue("done");
	    RadioPanel1.add(rb00);
	    RadioPanel1.add(rb0);
	    RadioPanel2.add(rb11);
	    RadioPanel2.add(rb1);
	    RadioPanel3.add(rb22);
	    RadioPanel3.add(rb2);
	    RadioPanel.add(RadioPanel1);
	    RadioPanel.add(RadioPanel2);
	    RadioPanel.add(RadioPanel3);
	    add(RadioPanel);
	    
	    VerticalPanel ProblemPanel = new VerticalPanel();
	    ProblemPanel.setStyleName("aho-panel1");
		ProblemPanel.setWidth("100%");
		HorizontalPanel NamePanel = new HorizontalPanel();
		Label tb00 = new Label("Töö nimetus");
		TextBox tb0 = new TextBox();
		HorizontalPanel DescriptionPanel = new HorizontalPanel();
		Label tb11 = new Label("Töö kirjeldus");
		TextBox tb1 = new TextBox();
		HorizontalPanel ProbDescPanel = new HorizontalPanel();
		Label tb22 = new Label("Probleemi kirjeldus");
		TextBox tb2 = new TextBox();
		ProblemPanel.add(tb00);
		NamePanel.add(tb00);
		NamePanel.add(tb0);
		DescriptionPanel.add(tb11);
		DescriptionPanel.add(tb1);
		ProbDescPanel.add(tb22);
		ProbDescPanel.add(tb2);
		ProblemPanel.add(NamePanel);
		ProblemPanel.add(DescriptionPanel);
		ProblemPanel.add(ProbDescPanel);
		
		Button b = new Button("Sisesta teenus!", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  String state;
		    	  if (rb0.getValue() == true) {
		    		  state = rb0.getFormValue();
		    	  } else if (rb1.getValue() == true) {
		    		  state = rb1.getFormValue();
		    	  } else if (rb2.getValue() == true) {
		    		  state = rb2.getFormValue();
		    	  } else {
		    		  state = null;
		    	  }
		    	  if (tb0.getVisibleLength() > 0 && tb1.getVisibleLength() > 0 && tb2.getVisibleLength() > 0 && state != null) {
		    		  MaintenanceItem m = new MaintenanceItem();
		    		  m.setMaintenanceDevice(device.getDeviceKey().toString());
		    		  m.setMaintenanceName(tb0.getValue());
		    		  m.setMaintenanceDescription(tb1.getValue());
		    		  m.setMaintenanceProblemDescription(tb2.getValue());
		    		  m.setMaintenanceState(state);
		    		  deviceTreeService.storeMaintenanceEntry(m, null);
			    	  Window.alert("Teie teenus on sisestatud!");
		    	  } else {
		    		  Window.alert("Probleem");
		    	  }
		    	  
		      }
		});
		
		ProblemPanel.add(b);
		add(ProblemPanel);
	}
}
