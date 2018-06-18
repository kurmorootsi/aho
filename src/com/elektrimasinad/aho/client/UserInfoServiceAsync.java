package com.elektrimasinad.aho.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserInfoServiceAsync {
	void getAccountData(String accountName, String accountPassword, String companyName, AsyncCallback<String> callback);
}
