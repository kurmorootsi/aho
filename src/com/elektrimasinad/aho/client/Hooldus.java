package com.elektrimasinad.aho.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.elektrimasinad.aho.shared.Company;
import com.elektrimasinad.aho.shared.Device;
import com.elektrimasinad.aho.shared.Measurement;
import com.elektrimasinad.aho.shared.Raport;
import com.elektrimasinad.aho.shared.Unit;
import com.elektrimasinad.aho.shared.DiagnostikaItem;
import com.elektrimasinad.aho.shared.MaintenanceItem;
import com.elektrimasinad.aho.shared.PlannerItem;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;



public class Hooldus implements EntryPoint {
	
	private static final DeviceTreeServiceAsync deviceTreeService = DeviceCard.getDevicetreeservice();
	private AsyncCallback<List<Raport>> getRaportsCallback;
	private AsyncCallback<List<Device>> getDeviceCallback;
	private AsyncCallback<List<MaintenanceItem>> getMaintenanceCallback;
	protected AsyncCallback<List<Measurement>> getRaportDataCallback;
	
	private int MAIN_WIDTH = 900;
	private int CONTENT_WIDTH = (int) (MAIN_WIDTH * 0.9);
	
	private List<Raport> raports = new ArrayList<Raport>();
	private List<Device> devices = new ArrayList<Device>();
	private List<MaintenanceItem> maintenance = new ArrayList<MaintenanceItem>();
	private static List<Measurement> raportDataList;
	
	private DeviceTree devTree;
	private VerticalPanel mainPanel = new VerticalPanel();
	private VerticalPanel raportPanel = new VerticalPanel();
	private DeckPanel contentPanel;
	private DeckPanel content2Panel;
	private VerticalPanel treePanel;
	private VerticalPanel tablePanel;
	private VerticalPanel table2Panel;
	private VerticalPanel unitPanel = new VerticalPanel();
	
	private Unit selectedUnit;
	private static Raport selectedRaport;
	protected Company selectedCompany;
	private Widget inputRaportNr;
	private Widget inputMeasurerName;
	private Widget inputMeasurerPhone;
	private Widget inputDate;
	private boolean isDevMode;
	private boolean isMobileView;
	DebugClientSide Debug = new DebugClientSide();
	
	
	private List<DiagnostikaItem> DIAGNOSTIKA = new ArrayList<DiagnostikaItem>();
	private List<PlannerItem> PLANNER = new ArrayList<PlannerItem>();


	@Override
	public void onModuleLoad() {
		Debug.enable();
		Debug.log("Debug enabled");

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
		getRaportsCallback = new AsyncCallback<List<Raport>>() {
			@Override
			public void onSuccess(List<Raport> raportList) {
				//System.out.println(name);
				if (raportList != null) {
					raports = raportList;
					Debug.log("Raportid imporditud.");
					
					deviceTreeService.getListMeasurement(getRaportDataCallback);
					
				} else {
					Debug.log("Raportid ERROR");
				}

			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println(caught);
				Debug.log("Raport alguse Error");
			}
			
		};
		getDeviceCallback = new AsyncCallback<List<Device>>() {
			@Override
			public void onSuccess(List<Device> deviceList) {
				if (deviceList != null) {
					devices = deviceList;
					Debug.log("Seadmed imporditud.");
					init();
					
					
				} else {
					Debug.log("Devices ERROR");
				}

			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println(caught);
				Debug.log("Raport alguse Error");
			}
			
		};
		getRaportDataCallback = new AsyncCallback<List<Measurement>>() {
			
			@Override
			public void onSuccess(List<Measurement> measurementList) {
				if (measurementList != null) {
					raportDataList = measurementList;
					Debug.log("Measurementid imporditud.");
					deviceTreeService.getMaintenanceEntries(getMaintenanceCallback);
				} else {
					Debug.log("Measurementid ERROR");
				}
				updateWidgetSizes();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println(caught);
				Debug.log("Measurement alguse Error");
			}
			
		};
		getMaintenanceCallback = new AsyncCallback<List<MaintenanceItem>>() {
			
			@Override
			public void onSuccess(List<MaintenanceItem> maintenanceList) {
				if (maintenanceList != null) {
					maintenance = maintenanceList;
					Debug.log("Maintenance imporditud.");
					deviceTreeService.getListDevices(getDeviceCallback);
				} else {
					Debug.log("Maintenance ERROR");
				}
				updateWidgetSizes();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println(caught);
				Debug.log("Maintenance alguse Error");
			}
			
		};
		CellTable<DiagnostikaItem> table = new CellTable<DiagnostikaItem>();
		RootPanel root = RootPanel.get();
		root.setStyleName("mainBackground2");
		
		mainPanel.setSize(MAIN_WIDTH + "px", "900px");
		mainPanel.setStyleName("panelBackground b");
 		Image headerImage = new Image("res/hes-symbol.jpg");
 		headerImage.setStyleName("aho-headerImage");
 		headerImage.addClickHandler(new ClickHandler() {
 			
 			@Override
 			public void onClick(ClickEvent event) {
 				if(isDevMode) Window.Location.assign(Window.Location.getHref().replace("index", "index"));
 				else Window.Location.assign("/Index.html");
 			}
 			
 		});
 		
 		HorizontalPanel navigationPanel = new HorizontalPanel();
 		navigationPanel.setStyleName("aho-navigationPanel");
 		navigationPanel.add(headerImage);
 		navigationPanel.setCellWidth(headerImage, "52px");
		AbsolutePanel headerPanel = new AbsolutePanel();
		headerPanel.setStyleName("headerBackground");
		headerPanel.add(navigationPanel);
		mainPanel.add(headerPanel);
		
		contentPanel = new DeckPanel();
		mainPanel.add(contentPanel);
		mainPanel.setCellHeight(contentPanel, "100%");
		mainPanel.setCellHorizontalAlignment(contentPanel, HasHorizontalAlignment.ALIGN_CENTER);
		content2Panel = new DeckPanel();
		mainPanel.add(content2Panel);
		mainPanel.setCellHeight(content2Panel, "100%");
		mainPanel.setCellHorizontalAlignment(content2Panel, HasHorizontalAlignment.ALIGN_CENTER);
		
		root.add(mainPanel);
		
		firstInit();
		updateWidgetSizes();
		createUnitPanel();
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
		contentPanel.setWidth(CONTENT_WIDTH + "px");
	}
	private void firstInit() {
		devTree = new DeviceTree(deviceTreeService);
		devTree.getElement().addClassName("gwt-Tree");
		deviceTreeService.getListRaports(getRaportsCallback);
	}
	private void init() {
		String m = "measurement size: " + Integer.toString(raportDataList.size());
		Debug.log(m);
		createNewDataTable();
		createNewPlannerTable();
		contentPanel.add(tablePanel);
		content2Panel.add(table2Panel);
		contentPanel.showWidget(contentPanel.getWidgetIndex(tablePanel));
		content2Panel.showWidget(content2Panel.getWidgetIndex(table2Panel));
	}
	
	private void createUnitPanel() {
		unitPanel.clear();
		unitPanel.setWidth("100%");
	
		Label lBack = new Label("Tagasi");
		lBack.setStyleName("backSaveLabel");
		final Button lBackButton = new Button();
		lBackButton.setStyleName("backButton");
		lBackButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				contentPanel.showWidget(contentPanel.getWidgetIndex(treePanel));
			}
			
		});
		lBack.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				lBackButton.fireEvent(event);
			}
			
		});
		
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setStyleName("backSavePanel");
		buttonsPanel.add(lBackButton);
		buttonsPanel.add(lBack);
		buttonsPanel.setCellWidth(lBackButton, "7%");
		buttonsPanel.setCellWidth(lBack, "43%");
		unitPanel.add(buttonsPanel);
		
		//Header Panel
		HorizontalPanel headerPanel = AhoWidgets.createThinContentHeader(selectedUnit.getUnit());
		unitPanel.add(headerPanel);
		
	
		contentPanel.showWidget(contentPanel.getWidgetIndex(unitPanel));
	}
	
	private VerticalPanel createNewDataTable() {
		
		String s = "raports size: " + Integer.toString(raports.size());
//		Debug.log(s);
		String d = raports.get(0).getCompanyName();
//		Debug.log(d);
		for (int x = 0; x < raports.size(); x++) {
			DiagnostikaItem diag = new DiagnostikaItem();
			diag.setName(raports.get(x).getCompanyName());
			diag.setAddress(raports.get(x).getUnitName());
			diag.setID(raports.get(x).getRaportID());
			for (int y = 0; y < raportDataList.size(); y++) {
				String measureKey = raportDataList.get(y).getRaportKey();
				String raportKey = raports.get(x).getRaportKey();
//				Debug.log(y + " measure key: " + measureKey);
//				Debug.log(x + " raport key: " + raportKey);
				if (measureKey.equals(raportKey)) {
					String v = raportDataList.get(y).getComment();
					diag.setComment(v);
//					Debug.log("comment: " + v);
					String st = raportDataList.get(y).getDeviceName();
					diag.setDevice(st);
//					Debug.log("name: " + st);
				}
			}
			
			DIAGNOSTIKA.add(diag);
			String tr = "diag object created nr: " + x;
//			Debug.log(tr);
		}
		String a = "diagnostika size: " + Integer.toString(DIAGNOSTIKA.size());
//		Debug.log(a);
		tablePanel = new VerticalPanel();
		tablePanel.setStyleName("aho-panel1 table2");
		tablePanel.setWidth("100%");
		Label lLabel = new Label("Diagnostika ja monitooring");
		lLabel.setStyleName("backSaveLabel noPointer");
		CellTable<DiagnostikaItem> table = new CellTable<DiagnostikaItem>();

	    // Add a text column to show the name.
	    TextColumn<DiagnostikaItem> nameColumn = new TextColumn<DiagnostikaItem>() {
	      @Override
	      public String getValue(DiagnostikaItem object) {
	        return object.getName();
	      }
	    };
	    table.addColumn(nameColumn, "Osakond");

	    // Add a text column to show the address.
	    TextColumn<DiagnostikaItem> addressColumn = new TextColumn<DiagnostikaItem>() {
	      @Override
	      public String getValue(DiagnostikaItem object) {
	        return object.getAddress();
	      }
	    };
	    table.addColumn(addressColumn, "Üksus");
	    
	 // Add a text column to show the address.
	    TextColumn<DiagnostikaItem> idColumn = new TextColumn<DiagnostikaItem>() {
	      @Override
	      public String getValue(DiagnostikaItem object) {
	        return object.getID();
	      }
	    };
	    table.addColumn(idColumn, "ID.nr");
	    
	    // Add a text column to show the address.
	    TextColumn<DiagnostikaItem> seadeColumn = new TextColumn<DiagnostikaItem>() {
	      @Override
	      public String getValue(DiagnostikaItem object) {
	        return object.getDevice();
	      }
	    };
	    table.addColumn(seadeColumn, "Seade");
	    
	    // Add a text column to show the address.
	    TextColumn<DiagnostikaItem> kommentaarColumn = new TextColumn<DiagnostikaItem>() {
	      @Override
	      public String getValue(DiagnostikaItem object) {
	        return object.getComment();
	      }
	    };
	    table.addColumn(kommentaarColumn, "Kommentaar");

	    // Set the total row count. This isn't strictly necessary, but it affects
	    // paging calculations, so its good habit to keep the row count up to date.
	    table.setRowCount(DIAGNOSTIKA.size(), true);

	    // Push the data into the widget.
	    table.setRowData(0, DIAGNOSTIKA);
	    tablePanel.add(lLabel);
	    tablePanel.add(table);
	    return tablePanel;
	}
	private VerticalPanel createNewPlannerTable() {
		
		for (int x = 0; x < maintenance.size(); x++) {
			PlannerItem plan = new PlannerItem();
			plan.setAction(maintenance.get(x).getMaintenanceName());
			plan.setID(raports.get(x).getRaportID());
			plan.setDates(maintenance.get(x).getMaintenanceCompleteDate().toString());
				for (int y = 0; y < devices.size(); y++) {
					String deviceKey = devices.get(y).getDeviceKey();
					String mDeviceKey = maintenance.get(x).getMaintenanceDevice();
//					Debug.log(x + " maintenance key: " + mDeviceKey);
//					Debug.log(y + " device key: " + deviceKey);
						if (deviceKey.equals(mDeviceKey)) {
							String st = raportDataList.get(y).getDeviceName();
							plan.setDevice(st);
							Debug.log("seade m��ratud");
								for (int z = 0; z < raportDataList.size(); z++) {
									String deviceID = devices.get(y).getId();
									String mDeviceID = raportDataList.get(z).getDeviceID();
//									Debug.log(y + " device id: " + deviceID);
//									Debug.log(z + " measurements id: " + mDeviceID);
										if (deviceID.equals(mDeviceID)) {
											for (int r = 0; r < raports.size(); r++) {
												String mRaportKey = raportDataList.get(z).getRaportKey();
												String raportKey = raports.get(r).getRaportKey();
//												Debug.log(z + " measurements raport key: " + mRaportKey);
//												Debug.log(r + " real raport key: " + raportKey);
													if (mRaportKey.equals(raportKey)) {
														plan.setName(raports.get(r).getCompanyName());
														plan.setAddress(raports.get(r).getUnitName());
													}
											}
										}
									}
							}
					}
			PLANNER.add(plan);
			}
					
		
		table2Panel = new VerticalPanel();
		table2Panel.setStyleName("aho-panel1 table center");
		tablePanel.setWidth("100%");
		
		Label lLabel = new Label("Planeeritavad tegevused");
		lLabel.setStyleName("backSaveLabel noPointer");
		Label doneLabel = new Label("Tähtaeg möödas");
		doneLabel.setStyleName("dateLabel o");
		Label todayLabel = new Label("Täna");
		todayLabel.setStyleName("dateLabel g");
		Label doLabel = new Label("Tulemas");
		doLabel.setStyleName("dateLabel");
		
		CellTable<PlannerItem> table = new CellTable<PlannerItem>();
		
		TextColumn<PlannerItem> datesColumn = new TextColumn<PlannerItem>() {
		   @Override
		   public String getValue(PlannerItem object) {
		      return object.getDates();
		   }
		};
		table.addColumn(datesColumn, "Kuup");
		
		TextColumn<PlannerItem> nameColumn = new TextColumn<PlannerItem>() {
			@Override
			public String getValue(PlannerItem object) {
				return object.getName();
			}
		};
		table.addColumn(nameColumn, "Osakond");
		
		TextColumn<PlannerItem> addressColumn = new TextColumn<PlannerItem>() {
			@Override
			public String getValue(PlannerItem object) {
				return object.getAddress();
			}
		};
		table.addColumn(addressColumn, "\u00DCksus");
		
		TextColumn<PlannerItem> idColumn = new TextColumn<PlannerItem>() {
			@Override
			public String getValue(PlannerItem object) {
				return object.getID();
			}
		};
		table.addColumn(idColumn, "ID.nr");
		
		TextColumn<PlannerItem> deviceColumn = new TextColumn<PlannerItem>() {
			@Override
			public String getValue(PlannerItem object) {
				return object.getDevice();
			}
		};
		table.addColumn(deviceColumn, "Seade");
		
		TextColumn<PlannerItem> actionColumn = new TextColumn<PlannerItem>() {
			@Override
			public String getValue(PlannerItem object) {
				return object.getAction();
			}
		};
		table.addColumn(actionColumn, "Tegevus");
		    
		
		table.setRowCount(PLANNER.size(), true);
		table.setRowData(0, PLANNER);
		table2Panel.add(doneLabel);
		table2Panel.add(todayLabel);
		table2Panel.add(doLabel);
		table2Panel.add(lLabel);
		
		table2Panel.add(table);
		return table2Panel;
	}

}
