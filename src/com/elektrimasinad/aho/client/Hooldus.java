package com.elektrimasinad.aho.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.elektrimasinad.aho.shared.Company;
import com.elektrimasinad.aho.shared.Measurement;
import com.elektrimasinad.aho.shared.Raport;
import com.elektrimasinad.aho.shared.Unit;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Hooldus implements EntryPoint {
	
//	private static final DeviceTreeServiceAsync deviceTreeService = DeviceCard.getDevicetreeservice();
	protected AsyncCallback<List<Measurement>> getRaportDataCallback;
	
	private int MAIN_WIDTH = 900;
	private int CONTENT_WIDTH = (int) (MAIN_WIDTH * 0.9);
	
	private List<Raport> raports = new ArrayList<Raport>();
	
	private static List<Measurement> raportDataList;

	private DeviceTree devTree;
	private VerticalPanel mainPanel = new VerticalPanel();
	private VerticalPanel raportPanel = new VerticalPanel();
	private DeckPanel contentPanel;
	private VerticalPanel treePanel;
	private VerticalPanel terePanel;
	private VerticalPanel tablePanel;
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
	
	
	@Override
	public void onModuleLoad() {
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
		
		RootPanel root = RootPanel.get();
		root.setStyleName("mainBackground2");
		mainPanel.setStyleName("panelBackground");
		
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
		
		CellTable<Diagnostika> table = new CellTable<Diagnostika>();
		
		contentPanel = new DeckPanel();
		mainPanel.add(contentPanel);
		mainPanel.setCellHeight(contentPanel, "100%");
		mainPanel.setCellHorizontalAlignment(contentPanel, HasHorizontalAlignment.ALIGN_CENTER);
		
		root.add(mainPanel);
		init();
		updateWidgetSizes();
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
	
	private void init() {
		
//		createTreePanel();
		createTerePanel();
		createNewDataTable();
//		contentPanel.add(treePanel);
//		contentPanel.add(unitPanel);
//		contentPanel.add(terePanel);
		contentPanel.add(terePanel);
		contentPanel.add(tablePanel);
//		contentPanel.showWidget(contentPanel.getWidgetIndex(treePanel));
//		contentPanel.showWidget(contentPanel.getWidgetIndex(terePanel));
		contentPanel.showWidget(contentPanel.getWidgetIndex(terePanel));
		contentPanel.showWidget(contentPanel.getWidgetIndex(tablePanel));
	}
	private void createTerePanel() {
		terePanel = new VerticalPanel();
		terePanel.setWidth("100%");
		
		Label lLabel1 = new Label("Diagnostika ja monitooring");
		lLabel1.setStyleName("backSaveLabel noPointer");
		terePanel.add(lLabel1);
	}
	
	private class Diagnostika extends Measurement{
	    private final String address;
	    private final String name;
	    private final String id;
	    private String seade;
	    private final String kommentaar;

	    public Diagnostika(String name, String seade, String address, String id, String kommentaar) {
	      this.name = name;
	      this.seade = seade;
	      this.address = address;
	      this.id = id;
	      this.kommentaar = kommentaar;
	    }
	    
	    public String setDevice(int i) {
			return this.seade = raports.get(i).getCompanyName();
		}
	    
	  } 
	
	private final List<Diagnostika> DIAGNOSTIKA = Arrays.asList();	
	
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
	    
		Diagnostika diag = new Diagnostika("Katlamaja", "SSS", "Pumbad", "K. P2", "ALARM- EL.mootori DE otsas tugev müra. Soovitav esimesel võimalusel laagrid vahetada.");
//		DIAGNOSTIKA.add(diag);
		tablePanel = new VerticalPanel();
		tablePanel.setStyleName("aho-panel1 table2");
		tablePanel.setWidth("100%");
		CellTable<Diagnostika> table = new CellTable<Diagnostika>();

	    // Add a text column to show the name.
	    TextColumn<Diagnostika> nameColumn = new TextColumn<Diagnostika>() {
	      @Override
	      public String getValue(Diagnostika object) {
	        return object.name;
	      }
	    };
	    table.addColumn(nameColumn, "Osakond");

	    // Add a text column to show the address.
	    TextColumn<Diagnostika> addressColumn = new TextColumn<Diagnostika>() {
	      @Override
	      public String getValue(Diagnostika object) {
	        return object.address;
	      }
	    };
	    table.addColumn(addressColumn, "Üksus");
	    
	 // Add a text column to show the address.
	    TextColumn<Diagnostika> idColumn = new TextColumn<Diagnostika>() {
	      @Override
	      public String getValue(Diagnostika object) {
	        return object.id;
	      }
	    };
	    table.addColumn(idColumn, "ID.nr");
	    
	    // Add a text column to show the address.
	    TextColumn<Diagnostika> seadeColumn = new TextColumn<Diagnostika>() {
	      @Override
	      public String getValue(Diagnostika object) {
	        return object.seade;
	      }
	    };
	    table.addColumn(seadeColumn, "Seade");
	    
	    // Add a text column to show the address.
	    TextColumn<Diagnostika> kommentaarColumn = new TextColumn<Diagnostika>() {
	      @Override
	      public String getValue(Diagnostika object) {
	        return object.kommentaar;
	      }
	    };
	    table.addColumn(kommentaarColumn, "Kommentaar");

	    // Set the total row count. This isn't strictly necessary, but it affects
	    // paging calculations, so its good habit to keep the row count up to date.
	    table.setRowCount(DIAGNOSTIKA.size(), true);

	    // Push the data into the widget.
	    table.setRowData(0, DIAGNOSTIKA);
	    tablePanel.add(table);
	    return tablePanel;
	}

}
