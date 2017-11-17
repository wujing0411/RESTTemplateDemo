package com.example.demo.testutils;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.datautils.StaticDataBean;

/**
 * @author wujing
 * @since 2017/11/08
 * */
public class ApiProperty {
	private static final Logger logger = LoggerFactory.getLogger(ApiProperty.class);
	protected static PropertyReader propertyReader;

	public static String getProperty(String key) {
		if (null == propertyReader) {
			String propertyFile = "api.properties";
			String specifiedproperty = System.getProperty("apiproperty");
			if (null != specifiedproperty && !specifiedproperty.equalsIgnoreCase("")) {
				propertyFile = specifiedproperty;
			}

			String systag = "";
			Object systagObj = StaticDataBean.get("systag");
			if (null != systagObj) {
				systag = systagObj.toString();
			}

			propertyFile = systag + propertyFile;

			try {
				propertyReader = new PropertyReader(propertyFile);
			} catch (FileNotFoundException arg5) {
				logger.info(arg5.toString());
				return "";
			}
		}

		return propertyReader.getProperty(key);
	}
}
