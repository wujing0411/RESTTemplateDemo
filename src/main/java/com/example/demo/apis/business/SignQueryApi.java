package com.example.demo.apis.business;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import com.example.demo.base.RESTBaseAPI;

public class SignQueryApi extends RESTBaseAPI {

	public SignQueryApi(String uri, String sysName) {
		super(uri, sysName);
	}
	
	public HttpEntity<String> query(String appId) {
		this.appendUri(appId);
		return this.doTest(HttpMethod.GET);
	}
	
	public static void main(String[] args) {
		SignQueryApi api = new SignQueryApi(InterfaceName.ACCOUNT_SIGN_QUERY, ServerName.account);
		api.query("4BCA4460-BC93-E711-8C05-48D539B907D4");
	}
}
