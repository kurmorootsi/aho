package com.elektrimasinad.aho.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserInfoServiceAsync {
	void getUserId(AsyncCallback<String> callback);
	void getLogoutUrl(AsyncCallback<String> callback);
}
