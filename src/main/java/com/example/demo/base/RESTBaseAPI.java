package com.example.demo.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.apis.business.SignQueryApi;
import com.example.demo.interfaces.IHttpConnection;
import com.example.demo.models.SignQueryObj;
import com.example.demo.testutils.ApiProperty;

public class RESTBaseAPI<T> implements IHttpConnection<T> {
	private static final Logger logger = LoggerFactory.getLogger(RESTBaseAPI.class);
	private String uri;
	private HashMap<String, ArrayList<String>> params;
	private HttpHeaders header;
	private HttpEntity<Object> request;
	private String body;
	private Object obj;
	
	public RESTBaseAPI(String uri, String sysName) {
		if (null != sysName && !sysName.equalsIgnoreCase("")) {
			this.uri = this.assembleUri(uri, sysName);
		} else {
			this.uri = uri;
		}

		this.setHeader();
	}

	private String assembleUri(String uri, String sysName) {
		String env = ApiProperty.getProperty("Env");
		StringBuffer serverBuffer = new StringBuffer();
		serverBuffer.append(env);
		serverBuffer.append("_");
		if (!sysName.equalsIgnoreCase("") && null != sysName) {
			serverBuffer.append(sysName);
		} else {
			serverBuffer.append(ApiProperty.getProperty("sysName"));
		}

		serverBuffer.append("ApiServer");
		String host = ApiProperty.getProperty(serverBuffer.toString());
		StringBuffer uriBuffer = new StringBuffer();
		if (!host.toUpperCase().startsWith("HTTP")) {
			if (ApiProperty.getProperty(env + "_SSL").equalsIgnoreCase("true")) {
				uriBuffer.append("https://");
			} else {
				uriBuffer.append("http://");
			}
		}

		uriBuffer.append(host);
		uriBuffer.append(uri);
		return uriBuffer.toString();
	}
	
	private void setHeader() {
		String env = ApiProperty.getProperty("Env");
		this.addHeader(ApiProperty.getProperty(env + "_VersionKey"), ApiProperty.getProperty(env + "_Version"));
	}
	
	@Override
	public void appendUri(String appendStr) {
		this.uri = this.uri + "/" + appendStr;
	}

	@Override
	public void setToken(String token) {
		String env = ApiProperty.getProperty("Env");
		this.addHeader(ApiProperty.getProperty(env + "_TokenKey"), token);
	}

	@Override
	public void setParam(String key, String value) {
		if (null == this.params) {
			this.params = new HashMap();
		}

		ArrayList valueList;
		if (this.params.containsKey(key)) {
			valueList = new ArrayList();
			valueList.addAll((Collection) this.params.get(key));
			valueList.add(value);
			this.params.put(key, valueList);
		} else {
			valueList = new ArrayList();
			valueList.add(value);
			this.params.put(key, valueList);
		}
	}

	@Override
	public void setParams(HashMap<String, String> keyvalue) {
		Iterator iter = keyvalue.entrySet().iterator();

		while (iter.hasNext()) {
			Entry paramEntry = (Entry) iter.next();
			this.setParam((String) paramEntry.getKey(), (String) paramEntry.getValue());
		}

	}

	@Override
	public void addHeader(String key, String value) {
		if (null == this.header) {
			header = new HttpHeaders();
		}
		
		if (this.header.containsKey(key)) {
			this.header.remove(key);
		}
		this.header.add(key, value);

	}

	@Override
	public void addHeaders(HashMap<String, String> keyvalue) {
		Iterator iter = keyvalue.entrySet().iterator();
		while(iter.hasNext()) {
			Entry headerEntry = (Entry) iter.next();
			this.header.add((String)headerEntry.getKey(), (String)headerEntry.getValue());
		}
	}

	@Override
	public void setJsonBody(Object arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJsonStringBody(String jsonStr) {
		this.header.add("Content-Type", "application/json");
		this.body = jsonStr;
	}

	@Override
	public void setXMLStringBody(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setFormBody(HashMap<String, String> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMultipartBody(String arg0, HashMap<String, String> arg1, HashMap<String, File> arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResponseEntity<T> doTest(HttpMethod method) {
//		HttpEntity<Object> response = null;
		ResponseEntity<T> response = null;
		if (null == method) {
			this.logger.info("URI调用方式为空。");
			throw new NullPointerException("URI调用方式为空。");
		} else {
			this.request = new HttpEntity<Object>(body, this.header);
			RestTemplate resttemplate = new RestTemplate();
			response = (ResponseEntity<T>) resttemplate.exchange(this.uri, method, this.request, SignQueryObj.class);
		}
		this.logger.info(response.getBody().toString());
		this.logger.info(response.getStatusCodeValue() + "");
		return response;
	}


}
