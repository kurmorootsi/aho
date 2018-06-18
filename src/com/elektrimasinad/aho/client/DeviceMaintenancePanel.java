package com.elektrimasinad.aho.client;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.elektrimasinad.aho.shared.Company;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.elektrimasinad.aho.shared.Device;

import com.elektrimasinad.aho.shared.Device;
import com.elektrimasinad.aho.shared.MaintenanceItem;
//import com.elektrimasinad.aho.shared.MyImage;
import com.gargoylesoftware.htmlunit.javascript.host.file.Blob;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.ibm.icu.text.MessagePattern.Part;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.datastore.Query;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.storage.client.Storage;
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

public class DeviceMaintenancePanel extends VerticalPanel {
	//private Device device;
	private static DeviceTreeServiceAsync deviceTreeService = DeviceCard.getDevicetreeservice();
	private List<MaintenanceItem> itemsToEdit;
	private AsyncCallback<List<MaintenanceItem>> getMaintenanceItemsCallback;
	private AsyncCallback<Company> getCompanyCallback;
	private Company selectedCompany;
	private Storage sessionStore;
	//getimage
    
    /*public String getImageUrl(HttpServletRequest req, HttpServletResponse resp, final String bucket) throws IOException, ServletException {
    	Part filePart = req.getPart("file");
    	final String fileName = filePart.getSubmittedFileName();
    	String imageUrl = req.getParameter("imageUrl");
    	// Check extension of file
    	if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
    		final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
    		String[] allowedExt = { "jpg", "jpeg", "png", "gif" };
    		for (String s : allowedExt) {
    			if (extension.equals(s)) {
    				return this.uploadFile(filePart, bucket);
    			}
    		}
    		throw new ServletException("file must be an image");
    	}
    	return imageUrl;
    }
    
    @SuppressWarnings("deprecation")
    public String uploadFile(Part filePart, final String bucketName) throws IOException {
      DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
      DateTime dt = DateTime.now(DateTimeZone.UTC);
      String dtString = dt.toString(dtf);
      final String fileName = filePart.getSubmittedFileName() + dtString;
      BlobInfo blobInfo =
          storage.create(
              BlobInfo
                  .newBuilder(bucketName, fileName)
                  .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
                  .build(),
              filePart.getInputStream());
      return blobInfo.getMediaLink();
    }*/
	
	public DeviceMaintenancePanel() {
		super();
		
	}
	public void createNewDeviceMaintenancePanel(Device device) {
		sessionStore = Storage.getSessionStorageIfSupported();
		getCompanyCallback = new AsyncCallback<Company>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Company arg0) {
				// TODO Auto-generated method stub
				selectedCompany = arg0;
				Window.alert(selectedCompany.getCompanyName());
			}
			
		};
		getMaintenanceItemsCallback = new AsyncCallback<List<MaintenanceItem>>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<MaintenanceItem> itemList) {
				// TODO Auto-generated method stub
				itemsToEdit = itemList;
			}
			
		};
		Window.alert(sessionStore.getItem("selectedCompany"));
		deviceTreeService.getCompany(sessionStore.getItem("Account"), getCompanyCallback);
		HorizontalPanel headerPanel = AhoWidgets.createContentHeader("Seadme " + device.getDeviceName() + " hooldustöö");
		add(headerPanel);
		Window.alert("block1");
		VerticalPanel RadioPanel = new VerticalPanel();
		RadioPanel.setStyleName("aho-panel1");
		HorizontalPanel RadioPanel1 = new HorizontalPanel();
		Label rb00 = new Label("Perioodiline");
		RadioPanel1.setStyleName("aho-panel1");
		RadioButton rb0 = new RadioButton("myRadioGroup");
		rb0.setFormValue("onetime");
		rb0.setStyleName("aho-panel1");
		rb00.setStyleName("aho-label1");
		HorizontalPanel RadioPanel2 = new HorizontalPanel();
		Label rb11 = new Label("Plaaniline");
	    RadioButton rb1 = new RadioButton("myRadioGroup");
	    rb1.setFormValue("onetime");
	    RadioPanel2.setStyleName("aho-panel1");
	    rb1.setStyleName("aho-panel1");
	    rb11.setStyleName("aho-label1");
	    HorizontalPanel RadioPanel3 = new HorizontalPanel();
	    Label rb22 = new Label("Teostatud");
	    RadioButton rb2 = new RadioButton("myRadioGroup");
	    rb2.setFormValue("onetime");
	    RadioPanel3.setStyleName("aho-panel1");
	    rb2.setStyleName("aho-panel1");
	    rb22.setStyleName("aho-label1");
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
	    Window.alert("block2");
	    HorizontalPanel ProblemSignPanel = AhoWidgets.createContentHeader("Perioodiline või plaaniline hooldustegevus");
	    add(ProblemSignPanel);
		ProblemSignPanel.setVisible(false);
	    VerticalPanel ProblemPanel = new VerticalPanel();
	    ProblemPanel.setStyleName("aho-panel1");
		ProblemPanel.setWidth("100%");
		HorizontalPanel NamePanel = new HorizontalPanel();
		Label tb00 = new Label("Töö nimetus");
		TextBox tb0 = new TextBox();
		ProblemPanel.setCellHorizontalAlignment(tb0, HasHorizontalAlignment.ALIGN_RIGHT);
		tb0.setStyleName("aho-textbox1");
	    tb00.setStyleName("aho-label1");
	    NamePanel.setWidth("100%");
		HorizontalPanel DescriptionPanel = new HorizontalPanel();
		Label tb11 = new Label("Töö kirjeldus");
		TextBox tb1 = new TextBox();
		tb1.setStyleName("aho-textbox1");
	    tb11.setStyleName("aho-label1");
	    DescriptionPanel.setWidth("100%");
	    ProblemPanel.setCellHorizontalAlignment(tb1, HasHorizontalAlignment.ALIGN_RIGHT);
		HorizontalPanel ProbDescPanel = new HorizontalPanel();
		ProbDescPanel.setWidth("100%");
		Label tb22 = new Label("Probleemi kirjeldus");
		TextBox tb2 = new TextBox();
		tb2.setStyleName("aho-textbox1");
	    tb22.setStyleName("aho-label1");
	    ProblemPanel.setCellHorizontalAlignment(tb2, HasHorizontalAlignment.ALIGN_RIGHT);
		//teostaja osa
		HorizontalPanel Person = new HorizontalPanel();
		Label per = new Label("Teostaja");
		ListBox lb = new ListBox();
		lb.addItem("Peeter Kelk");
	    lb.addItem("Aivar Saan");
		lb.setStyleName("aho-panel1");
	    per.setStyleName("aho-label1");
	    Person.setWidth("100%");
	    Person.setCellHorizontalAlignment(lb, HasHorizontalAlignment.ALIGN_RIGHT);
		HorizontalPanel MaterialList = new HorizontalPanel();
	    Label Material = new Label("Vajalikud materjalid");
		TextArea ta = new TextArea();
	    ta.setCharacterWidth(50);
	    ta.setVisibleLines(20);
	    ta.setStyleName("aho-panel1");
	    Material.setStyleName("aho-label1");
	    MaterialList.setWidth("100%");
	    MaterialList.setCellHorizontalAlignment(ta, HasHorizontalAlignment.ALIGN_RIGHT);
	    HorizontalPanel NotesList = new HorizontalPanel();
	    Label Notes = new Label("Märkused");
		TextArea note = new TextArea();
		note.setCharacterWidth(50);
		note.setVisibleLines(20);
		note.setStyleName("aho-panel1");
	    Notes.setStyleName("aho-label1");
	    NotesList.setWidth("100%");
	    NotesList.setCellHorizontalAlignment(note, HasHorizontalAlignment.ALIGN_RIGHT);
		HorizontalPanel ReadyBy = new HorizontalPanel();
		Label Time = new Label("Töö valmib: ");
		DateBox dateBox = new DateBox();
	    dateBox.setValue(new Date());
	    ReadyBy.setStyleName("aho-panel1");
	    Time.setStyleName("aho-label1");
	    dateBox.setWidth("100%");
	    RootPanel.get().add(dateBox);
	    ReadyBy.setCellHorizontalAlignment(dateBox, HasHorizontalAlignment.ALIGN_CENTER);
	    VerticalPanel WorkInterval = new VerticalPanel();
		Label Interval = new Label("Interval");
		Interval.setStyleName("aho-label1");
	    WorkInterval.setWidth("100%");
	    WorkInterval.setCellHorizontalAlignment(Interval, HasHorizontalAlignment.ALIGN_LEFT);
		HorizontalPanel RadioPanel4 = new HorizontalPanel();
		Label rb33 = new Label("Iga 6 kuu tagant");
	    RadioButton rb3 = new RadioButton("myRadioGroup");
	    rb33.setStyleName("aho-label1");
	    RadioPanel4.setWidth("100%");
	    RadioPanel4.setCellHorizontalAlignment(rb33, HasHorizontalAlignment.ALIGN_LEFT);
	    RadioPanel4.setCellHorizontalAlignment(rb3, HasHorizontalAlignment.ALIGN_LEFT);
	    HorizontalPanel RadioPanel5 = new HorizontalPanel();
		Label rb44 = new Label("Iga 12 kuu tagant");
	    RadioButton rb4 = new RadioButton("myRadioGroup");
	    rb44.setStyleName("aho-label1");
	    RadioPanel5.setWidth("100%");
	    RadioPanel5.setCellHorizontalAlignment(rb44, HasHorizontalAlignment.ALIGN_LEFT);
	    RadioPanel5.setCellHorizontalAlignment(rb4, HasHorizontalAlignment.ALIGN_LEFT);
	    HorizontalPanel RadioPanel6 = new HorizontalPanel();
		Label rb55 = new Label("Iga 3 kuu tagant");
	    RadioButton rb5 = new RadioButton("myRadioGroup");
	    rb55.setStyleName("aho-label1");
	    RadioPanel6.setWidth("100%");
	    RadioPanel6.setCellHorizontalAlignment(rb55, HasHorizontalAlignment.ALIGN_LEFT);
	    RadioPanel6.setCellHorizontalAlignment(rb5, HasHorizontalAlignment.ALIGN_LEFT);
	    //camera open panel
	    VerticalPanel FileUploadPanel = new VerticalPanel();
	    FileUploadPanel.setWidth("100%");
	    final FormPanel form = new FormPanel();
	    form.setAction("/myFormHandler");
	    form.setEncoding(FormPanel.ENCODING_MULTIPART);
	    form.setMethod(FormPanel.METHOD_POST);
	    Window.alert("block3");
	    HorizontalPanel panel = new HorizontalPanel();
	    form.setWidget(panel);

	    Label tb99 = new Label("Pildi üleslaadur ");
	    HorizontalPanel description = new HorizontalPanel();
	    Label nameFile = new Label("Pildifaili kirjeldus: ");
	    final TextBox tb9 = new TextBox();
	    tb9.setName("textBoxFormElement");

	    HorizontalPanel file = new HorizontalPanel();
	    Label chooseFile = new Label("Vali fail: ");
	   
	    final Label label  = new Label("Pildi lisamine: ");
	    label.setStyleName("aho-label1");
	    FileUpload upload = new FileUpload();
	    upload.getElement().setAttribute("type", "file");
	    upload.getElement().setAttribute("accept", "image/*");
	    upload.getElement().setAttribute("capture", "camera");
	    panel.setCellHorizontalAlignment(upload, HasHorizontalAlignment.ALIGN_LEFT);

		/*
		//image upload servlet
		Blob imageFor(String name, HttpServletResponse res) {
		    PersistenceManager pm = PMF.get().getPersistenceManager();
		    Query query = pm.newQuery("select from MyImage " +
		        "where name = nameParam " +
		        "parameters String nameParam");
		    List<MyImage> results = (List<MyImage>)query.execute(name);
		    Blob image = results.iterator().next().getImage();
		    res.setContentType("image/jpeg");
		    res.getOutputStream().write(image.getBytes());
		}*/
		
	    form.addSubmitHandler(new FormPanel.SubmitHandler() {
	      public void onSubmit(SubmitEvent event) {
	        if (tb9.getText().length() == 0) {
	          Window.alert("Palun sisesta puuduv kirje!");
	          event.cancel();
	        }
	      }
	    });
	    form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
	      public void onSubmitComplete(SubmitCompleteEvent event) {
	        Window.alert(event.getResults());
	      }
	    });
	    Window.alert("block4");
	    //vï¿½ljakutsumised
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
		ProblemPanel.add(FileUploadPanel);
		FileUploadPanel.add(panel);
		panel.add(label);
	    panel.add(upload);
		ProblemPanel.setCellHorizontalAlignment(panel, HasHorizontalAlignment.ALIGN_RIGHT);
		ProblemPanel.setVisible(false);
		Window.alert("block5");
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
		    		  m.setMaintenanceAssignedTo();
		    		  m.setMaintenanceCompleteDate(dateBox.getValue());
		    		  m.setMaintenanceMaterials(ta.getValue());
		    		  m.setMaintenanceNotes(note.getValue());
		    		  //m.setMaintenanceImage(upload.getName());
		    		  if(state.equals("periodic")) {
		    			  m.setMaintenanceInterval(5);
		    		  } else {
		    			 m.setMaintenanceInterval(0);
		    		  }
		    		  deviceTreeService.storeMaintenanceEntry(m, selectedCompany.getCompanyKey(), null);
			    	  Window.alert("Teie teenus on sisestatud!");
		    	  } else {
		    		  Window.alert("Probleem");
		    	  }
		      }
		});
		ProblemPanel.add(b);
		add(ProblemSignPanel);
		add(ProblemPanel);
		Window.alert("block6");
		//teostatud t66 paneel
		HorizontalPanel DonePanel = AhoWidgets.createContentHeader("Teostatud töö kokkuv\u00F5te");
		add(DonePanel);
		DonePanel.setVisible(false);
		
		VerticalPanel WorkPanel = new VerticalPanel();
		WorkPanel.setStyleName("aho-panel1");
		HorizontalPanel StopTimePanel = new HorizontalPanel();
		Label stp0 = new Label("Seisaku aeg");
		TextBox stp00 = new TextBox();
		stp0.setStyleName("aho-label1");
		stp00.setStyleName("aho-textbox1");
	    StopTimePanel.setCellHorizontalAlignment(stp00, HasHorizontalAlignment.ALIGN_RIGHT);
		HorizontalPanel SpentTimePanel = new HorizontalPanel();
		Label stp1 = new Label("Tööle kulunud aeg(tundides)");
		TextBox stp11 = new TextBox();
		stp1.setStyleName("aho-label1");
		stp11.setStyleName("aho-textbox1");
	    SpentTimePanel.setCellHorizontalAlignment(stp11, HasHorizontalAlignment.ALIGN_RIGHT);
		HorizontalPanel CostPanel = new HorizontalPanel();
		Label cp = new Label("Töö maksumus");
		TextBox cp1 = new TextBox();
		cp.setStyleName("aho-label1");
		cp1.setStyleName("aho-textbox1");
		CostPanel.setCellHorizontalAlignment(cp1, HasHorizontalAlignment.ALIGN_RIGHT);
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
		
		Window.alert("block7");
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
	    Window.alert("block8");
	}
	/*public getData() {
		return key;
	}*/
}
