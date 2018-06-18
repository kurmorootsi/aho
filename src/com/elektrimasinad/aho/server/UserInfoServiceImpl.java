package com.elektrimasinad.aho.server;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.elektrimasinad.aho.client.UserInfoService;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserInfoServiceImpl extends RemoteServiceServlet implements UserInfoService {
	private DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	private static final Random RANDOM = new SecureRandom();
	private static final int ITERATIONS = 1000;
	private static final int KEY_LENGTH = 192;
	
	public String getAccountData(String accountName, String accountPassword, String companyName) {
		Key companyKey = KeyFactory.stringToKey("ag5lbGVrdHJpbWFzaW5hZHIvCxIHQ29tcGFueSIORWxla3RyaW1hc2luYWQMCxIHQ29tcGFueRiAgICAgICACgw");
		System.out.println(KeyFactory.keyToString(companyKey));
		Query query = new Query("Company");
		query.setFilter(FilterOperator.EQUAL.of("Name", companyName));
		try {
			Entity e = ds.prepare(query).asSingleEntity();
			String salt = "Elektrimasinad";
			System.out.println(salt);
			byte[] saltArr = salt.getBytes();
			System.out.println(saltArr);
			accountPassword = hashPassword(accountPassword, saltArr);
			String dbName = e.getProperty("Username").toString();
			String dbPassword = e.getProperty("Password").toString();
			System.out.println(dbName);
			System.out.println(dbPassword);
			System.out.println(accountPassword);
			if(accountName.equals(dbName) && accountPassword.equals(dbPassword)) {
				return KeyFactory.keyToString(e.getKey());
			} else {
				return "failed";
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException  e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		return "failed";
	}
	private String hashPassword(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		char[] passwordChar = passwordToHash.toCharArray();
		byte[] saltBytes = salt;
		
		PBEKeySpec spec = new PBEKeySpec(
				passwordChar,
				saltBytes,
				ITERATIONS,
				KEY_LENGTH
				);
		SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hashedPassword = key.generateSecret(spec).getEncoded();
		return String.format("%x", new BigInteger(hashedPassword));
	}
}
