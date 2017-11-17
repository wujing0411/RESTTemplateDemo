package com.example.demo.interfaces;

import java.io.File;
import java.util.HashMap;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface IHttpConnection<T> {
	void appendUri(String arg0);

	void setToken(String arg0);

	void setParam(String arg0, String arg1);

	void setParams(HashMap<String, String> arg0);

	void addHeader(String arg0, String arg1);

	void addHeaders(HashMap<String, String> arg0);

	void setJsonBody(Object arg0);

	void setJsonStringBody(String arg0);

	void setXMLStringBody(String arg0);

	void setFormBody(HashMap<String, String> arg0);

	void setMultipartBody(String arg0, HashMap<String, String> arg1, HashMap<String, File> arg2);

//	HttpEntity<String> doTest(HttpMethod arg0);
	
	ResponseEntity<T> doTest(HttpMethod method);

}
