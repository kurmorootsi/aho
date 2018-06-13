package com.elektrimasinad.aho.server;

import com.elektrimasinad.aho.client.UserInfoService;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserInfoServiceImpl extends RemoteServiceServlet implements UserInfoService {
	private UserService userService = UserServiceFactory.getUserService();
	
	public String getUserId() {
		String uId = userService.getCurrentUser().getUserId();
		return uId;
	}
	public String getLogoutUrl() {
		String logoutUrl = userService.createLogoutURL("/login");
		return logoutUrl;
	}
}
