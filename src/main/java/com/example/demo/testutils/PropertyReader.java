package com.example.demo.testutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {
	private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);
	private String configPath = null;
	private Properties pro = null;
	private InputStream input = null;
	
	public PropertyReader(String propertiesName) throws FileNotFoundException {
		if (null != propertiesName && !propertiesName.equalsIgnoreCase("")) {
			this.configPath = propertiesName;
			URL resourcesUrl = this.getClass().getClassLoader().getResource(this.configPath);
			String resourcesPath1;
			if (null == resourcesUrl) {
				resourcesPath1 = System.getProperty("user.dir") + "/resources/" + this.configPath;
				logger.info("Read properties file in " + resourcesPath1);
				this.input = new FileInputStream(resourcesPath1);
			} else {
				resourcesPath1 = resourcesUrl.getPath();
				logger.info("Read properties resource in " + resourcesPath1);
				this.input = this.getClass().getClassLoader().getResourceAsStream(this.configPath);
			}

			if (null == this.input) {
				FileNotFoundException ioe = new FileNotFoundException(
						"Property File not found: " + resourcesPath1 + this.configPath);
				logger.info(ioe.getMessage());
				throw ioe;
			} else {
				try {
					this.pro = new Properties();
					this.pro.load(this.input);
					this.input.close();
				} catch (IOException arg4) {
					logger.info(arg4.toString() + "Reading Properties catch IOException");
				}

			}
		} else {
			NullPointerException resourcesPath = new NullPointerException(
					"Properties Name is null. I can\'t find the file!");
			logger.info(resourcesPath.toString());
			throw resourcesPath;
		}
	}

	public String getProperty(String key) {
		String result = "";
		if (null != key) {
			result = this.pro.getProperty(key);
			if (null == result) {
				NullPointerException npe = new NullPointerException("Can\'t find : " + key);
				throw npe;
			}
		}

		return result.trim();
	}
}
