package com.elektrimasinad.aho.client;

import java.util.Date;
import com.elektrimasinad.aho.shared.Device;

import com.elektrimasinad.aho.shared.Device;
import com.elektrimasinad.aho.shared.MaintenanceItem;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

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
		RadioPanel1.setStyleName("aho-panel1");
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
	    RadioPanel1.setCellHorizontalAlignment(rb0, HasHorizontalAlignment.ALIGN_RIGHT);
	    RadioPanel2.add(rb11);
	    RadioPanel2.add(rb1);
	    RadioPanel2.setCellHorizontalAlignment(rb1, HasHorizontalAlignment.ALIGN_RIGHT);
	    RadioPanel3.add(rb22);
	    RadioPanel3.add(rb2);
	    RadioPanel3.setCellHorizontalAlignment(rb2, HasHorizontalAlignment.ALIGN_RIGHT);
	    RadioPanel.add(RadioPanel1);
	    RadioPanel.add(RadioPanel2);
	    RadioPanel.add(RadioPanel3);
	    add(RadioPanel);
	    
	    HorizontalPanel ProblemSignPanel = AhoWidgets.createContentHeader("Perioodiline või plaaniline hooldustegevus");
	    add(ProblemSignPanel);
		ProblemSignPanel.setVisible(false);
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
		//teostaja osa
		HorizontalPanel Person = new HorizontalPanel();
		Label per = new Label("Teostaja");
		ListBox lb = new ListBox();
		HorizontalPanel MaterialList = new HorizontalPanel();
	    Label Material = new Label("Vajalikud materjalid");
		TextArea ta = new TextArea();
	    ta.setCharacterWidth(80);
	    ta.setVisibleLines(50);
	    HorizontalPanel NotesList = new HorizontalPanel();
	    Label Notes = new Label("Märkused");
		TextArea note = new TextArea();
		note.setCharacterWidth(80);
		note.setVisibleLines(50);
		HorizontalPanel ReadyBy = new HorizontalPanel();
		Label Time = new Label("Töö valmib: ");
		DateBox dateBox = new DateBox();
	    dateBox.setValue(new Date());
	    RootPanel.get().add(dateBox);
	    HorizontalPanel WorkInterval = new HorizontalPanel();
		Label Interval = new Label("Interval");
		HorizontalPanel RadioPanel4 = new HorizontalPanel();
		Label rb33 = new Label("Iga 6 kuu tagant");
	    RadioButton rb3 = new RadioButton("myRadioGroup");
	    HorizontalPanel RadioPanel5 = new HorizontalPanel();
		Label rb44 = new Label("Iga 12 kuu tagant");
	    RadioButton rb4 = new RadioButton("myRadioGroup");
	    HorizontalPanel RadioPanel6 = new HorizontalPanel();
		Label rb55 = new Label("Iga 3 kuu tagant");
	    RadioButton rb5 = new RadioButton("myRadioGroup");
	    //väljakutsumised
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
		ProblemPanel.add(Person);
		Person.add(per);
		Person.add(lb);
		ProblemPanel.add(MaterialList);
		MaterialList.add(Material);
		MaterialList.add(ta);
		ProblemPanel.add(NotesList);
		NotesList.add(Notes);
		NotesList.add(note);
		ProblemPanel.add(ReadyBy);
		ReadyBy.add(Time);
		ReadyBy.add(dateBox);
		ProblemPanel.add(WorkInterval);
		WorkInterval.add(Interval);
		WorkInterval.add(RadioPanel4);
		WorkInterval.add(RadioPanel5);
		WorkInterval.add(RadioPanel6);
		RadioPanel4.add(rb33);
		RadioPanel4.add(rb3);
		RadioPanel5.add(rb44);
		RadioPanel5.add(rb4);
		RadioPanel6.add(rb55);
		RadioPanel6.add(rb5);
		ProblemPanel.setVisible(false);
		
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
		add(ProblemSignPanel);
		add(ProblemPanel);
		
		//teostatud töö paneel
		HorizontalPanel DonePanel = AhoWidgets.createContentHeader("Teostatud töö kokkuvõte");
		add(DonePanel);
		DonePanel.setVisible(false);
		
		VerticalPanel WorkPanel = new VerticalPanel();
		WorkPanel.setStyleName("aho-panel1");
		WorkPanel.setWidth("100%");
		HorizontalPanel StopTimePanel = new HorizontalPanel();
		Label stp0 = new Label("Seisaku aeg");
		TextBox stp00 = new TextBox();
		HorizontalPanel SpentTimePanel = new HorizontalPanel();
		Label stp1 = new Label("Tööle kulunud aeg(tundides)");
		TextBox stp11 = new TextBox();
		HorizontalPanel CostPanel = new HorizontalPanel();
		Label cp = new Label("Töö maksumus");
		TextBox cp1 = new TextBox();
		StopTimePanel.add(stp0);
		StopTimePanel.add(stp00);
		SpentTimePanel.add(stp1);
		SpentTimePanel.add(stp11);
		CostPanel.add(cp);
		CostPanel.add(cp1);
		WorkPanel.add(StopTimePanel);
		WorkPanel.add(SpentTimePanel);
		WorkPanel.add(CostPanel);
		WorkPanel.setVisible(false);
		
		Button w = new Button("Lõpeta töö!", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  //deviceTreeService.storeMaintenanceEntry(stp00.getValue(), stp11.getValue(), cp1.getValue(), null);
		    	  Window.alert("Töö on lõpetatud!");
		      }
		});
		WorkPanel.add(w);
		
		ClickHandler ch1=new ClickHandler() {
	        public void onClick(ClickEvent event) {
		    	  DonePanel.setVisible(true);
		    	  WorkPanel.setVisible(true);
		    	  ProblemPanel.setVisible(false);
		  		  ProblemSignPanel.setVisible(false);
	        }
	    };
	    rb2.addClickHandler(ch1);
	    
	    ClickHandler ch=new ClickHandler() {
	        public void onClick(ClickEvent event) {
		    	  ProblemPanel.setVisible(true);
		    	  ProblemSignPanel.setVisible(true);
		    	  DonePanel.setVisible(false);
		    	  WorkPanel.setVisible(false);
	        }
	    };
	    rb0.addClickHandler(ch);
	    rb1.addClickHandler(ch);
	    
	    add(WorkPanel);
	    
	    
	}
}
