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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserInfoServiceImpl extends RemoteServiceServlet implements UserInfoService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2691003455069498312L;
	private DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	private static final Random RANDOM = new SecureRandom();
	private static final int ITERATIONS = 1000;
	private static final int KEY_LENGTH = 192;
	public String getAdminAccountData(String accountName, String accountPassword) {
		String salt = "ElektrimasinadAdmins";
		byte[] saltArr = salt.getBytes();
		Query query = new Query("Admins");
		query.setFilter(FilterOperator.EQUAL.of("Username", accountName));
		Entity e = ds.prepare(query).asSingleEntity();
		String hashedPassword;
		try {
			hashedPassword = hashPassword(accountPassword, saltArr);
			if(e.getProperty("Password").equals(hashedPassword)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
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
	public String createAdminAccount(String username, String password) {
		Query query = new Query("Admins");
		query.setFilter(FilterOperator.EQUAL.of("Username", username));
		Iterable<Entity> nameCheck = ds.prepare(query).asIterable();
		if(nameCheck.iterator().hasNext() == false) {
			System.out.println("writing user data");
			Entity e = new Entity("Admins");
			String salt = "ElektrimasinadAdmins";
			byte[] saltArr = salt.getBytes();
			try {
				String hashedPassword = hashPassword(password, saltArr);
				e.setProperty("Username", username);
				e.setProperty("Password", hashedPassword);
				ds.put(e);
				return "account stored";
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return "account not stored";
	}
}
