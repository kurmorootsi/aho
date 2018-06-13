package com.elektrimasinad.aho.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.elektrimasinad.aho.client.AdministratorLogin;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Index implements EntryPoint {
	
	private boolean isDevMode;
	private boolean isMobileView;
	private int MAIN_WIDTH;
	private AdministratorLogin administratorLogin = new AdministratorLogin();
	private VerticalPanel mainPanel;
	private AbsolutePanel headerPanel;
	private DeckPanel contentPanel;
	private HorizontalPanel navPanel;
	//private final static UserInfoServiceAsync userInfoService = GWT.create(UserInfoService.class);
	private String logoutUrl;

	@Override
	public void onModuleLoad() {
		HorizontalPanel navigationPanel = new HorizontalPanel();
		Image headerImage = new Image("res/hes-symbol.jpg");
		headerImage.setStyleName("aho-headerImage");
		headerImage.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(isDevMode) Window.Location.assign(Window.Location.getHref().replace("index", "index"));
				else Window.Location.assign("/Index.html");
			}
			
		});
		
		navigationPanel.setStyleName("aho-navigationPanel");
		navigationPanel.add(headerImage);
		navigationPanel.setCellWidth(headerImage, "52px");
		//Label logoutLabel = new Label("Logi välja");
		
		if (Window.Location.getHref().contains("127.0.0.1")) isDevMode = true;
		else isDevMode = false;
		if (Window.getClientWidth() < 1000) {
			isMobileView = true;
		} else {
			isMobileView = false;
		}
		Window.addResizeHandler(new ResizeHandler() {

		    @Override
		    public void onResize(ResizeEvent event) {
		    	if (Window.getClientWidth() < 1000) {
					isMobileView = true;
				} else {
					isMobileView = false;
				}
		    	updateWidgetSizes();
		    }
		});
		
		//userInfoService.getLogoutUrl(userIdCallback);
		mainPanel = new VerticalPanel();
		mainPanel.setStyleName("panelBackground");
		
		//navigationPanel.add(logoutLabel);
		headerPanel = new AbsolutePanel();
		headerPanel.setStyleName("headerBackground");
		headerPanel.add(navigationPanel);
		
		mainPanel.add(headerPanel);
		
		contentPanel = new DeckPanel();
		mainPanel.add(contentPanel);
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setStyleName("mainBackground2");
		rootPanel.add(mainPanel);
		
		
		init();
		updateWidgetSizes();
		//administratorLogin.createNewAdministratorLogin();
	}
	
	private void updateWidgetSizes() {
		String contentWidth = "90%";
		MAIN_WIDTH = 700;
		if (isMobileView) {
			MAIN_WIDTH = Window.getClientWidth();
			contentWidth = "95%";
		}
		mainPanel.setWidth(MAIN_WIDTH + "px");
		mainPanel.setHeight(Window.getClientHeight() + "px");
		contentPanel.setWidth(contentWidth);
		mainPanel.setCellHorizontalAlignment(contentPanel, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	private void init() {
		createNavigationPanel();
		contentPanel.add(navPanel);
		contentPanel.showWidget(contentPanel.getWidgetIndex(navPanel));
		mainPanel.setCellHorizontalAlignment(contentPanel, HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.setCellHeight(contentPanel, "100%");
	}
	
	private void createNavigationPanel() {
		navPanel = new HorizontalPanel();
		navPanel.setWidth("100%");
		
		Image measurementImage = new Image("res/pik-mootmine.png");
		measurementImage.setStyleName("aho-piktoImage");
		measurementImage.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (isDevMode) Window.Location.assign(Window.Location.getHref().replace("index", "Aho"));
				else Window.Location.assign("/Aho.html");
			}
		});
		
		Image raportsImage = new Image("res/pik-Raport.png");
		raportsImage.setStyleName("aho-piktoImage");
		raportsImage.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (isDevMode) Window.Location.assign(Window.Location.getHref().replace("index", "Raports"));
				else Window.Location.assign("/Raports.html");
			}
		});
		
		Image devicecardImage = new Image("res/pik-seadmed.png");
		devicecardImage.setStyleName("aho-piktoImage");
		devicecardImage.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (isDevMode) Window.Location.assign(Window.Location.getHref().replace("index", "DeviceCard"));
				else Window.Location.assign("/DeviceCard.html");
			}
		});
		
		Image maintenanceImage = new Image("res/pik-hooldus.png");
		maintenanceImage.setStyleName("aho-piktoImage");
		maintenanceImage.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (isDevMode) Window.Location.assign(Window.Location.getHref().replace("index", "Maintenance"));
				else Window.Location.assign("/Hooldus.html");
			}
		});
		navPanel.add(devicecardImage);
		navPanel.add(maintenanceImage);
		navPanel.add(raportsImage);
		navPanel.add(measurementImage);
		
	}
}
