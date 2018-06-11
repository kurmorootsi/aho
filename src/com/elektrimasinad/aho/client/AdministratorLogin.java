package com.elektrimasinad.aho.client;

import java.awt.Dialog;
import java.awt.TextField;
import java.util.Date;
import com.elektrimasinad.aho.shared.Device;

import com.elektrimasinad.aho.shared.Device;
import com.elektrimasinad.aho.shared.MaintenanceItem;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.resource.Resource;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

public class AdministratorLogin extends VerticalPanel {
	public AdministratorLogin() {
		super();
	}
	public void createNewAdministratorLogin() {
		super.clear();
		DeviceTreeServiceAsync deviceTreeService = DeviceCard.getDevicetreeservice();
		VerticalPanel LoginPanel = new VerticalPanel();
		HorizontalPanel UserPanel = new HorizontalPanel();
		Label User = new Label("Username");
		TextBox tb = new TextBox();
		HorizontalPanel PassPanel = new HorizontalPanel();
		Label Pass = new Label("Password");
		PasswordTextBox ptb = new PasswordTextBox();

	    tb.addKeyPressHandler(new KeyPressHandler() {
	      public void onKeyPress(KeyPressEvent event) {
	        if (!Character.isDigit(event.getCharCode())) {
	          ((TextBox) event.getSource()).cancelKey();
	        }
	      }
	    });
	    VerticalPanel LoginButtonPanel = new VerticalPanel();
	    Button login = new Button("Log in!", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	          Window.alert("Login successful!");
	          Window.Location.assign("/Index.html");
	        }
	    });
	 
	    LoginPanel.add(UserPanel);
	    UserPanel.add(User);
	    UserPanel.add(tb);
	    LoginPanel.add(PassPanel);
	    PassPanel.add(Pass);
	    PassPanel.add(ptb);
	    LoginPanel.add(LoginButtonPanel);
	    LoginButtonPanel.add(login);
	    add(LoginPanel);
	}
}
