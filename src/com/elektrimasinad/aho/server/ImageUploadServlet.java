package com.elektrimasinad.aho.server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

//import com.elektrimasinad.aho.shared.MyImage;
import com.gargoylesoftware.htmlunit.javascript.host.file.Blob;

import java.io.InputStream;

import javax.servlet.ServletException;

//upload image really
public class ImageUploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/*public void doPost(HttpServletRequest req, HttpServletResponse res) {
	    ServletFileUpload upload = new ServletFileUpload();
	    FileItemIterator iter = upload.getItemIterator(req);
	    FileItemStream imageItem = iter.next();
	    InputStream imgStream = imageItem.openStream();

	    Blob imageBlob = new Blob(IOUtils.toByteArray(imgStream));
	    MyImage myImage = new MyImage(imageItem.getName(), imageBlob);

	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    pm.makePersistent(myImage);
	    pm.close();

	    res.setContentType("text/plain");
	    res.getOutputStream().write("OK!".getBytes());
	}*/
}
