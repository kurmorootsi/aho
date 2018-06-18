package com.elektrimasinad.aho.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("userInfoService")
public interface UserInfoService extends RemoteService{
	String getAccountData(String accountName, String accountPassword, String companyName);
}
