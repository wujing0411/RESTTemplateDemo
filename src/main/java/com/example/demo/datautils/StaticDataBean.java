package com.example.demo.datautils;

import java.util.HashMap;

public class StaticDataBean {
	public static final String SYSTAG = "systag";
	private static HashMap<String, Object> databean = new HashMap<String, Object>();

	public static void set(String key, Object value) {
		databean.put(key, value);
	}

	public static Object get(String key) {
		return databean.containsKey(key) ? databean.get(key) : null;
	}
}
