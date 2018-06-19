package com.elektrimasinad.aho.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserInfoServiceAsync {
	void getAccountData(String accountName, String accountPassword, String companyName, AsyncCallback<String> callback);
	void getAdminAccountData(String username, String password, AsyncCallback<String> callback);
	void createAdminAccount(String username, String password, AsyncCallback<String> callback);
}
