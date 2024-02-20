package com.seldocker.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

	private static final Logger log = LoggerFactory.getLogger(Config.class);
	private static final String DEFAULT_PROPERTIES = "config/config.properties";
	private static  Properties PROPERTIES;
	public static void initalize() {
		
		// load default properties
		PROPERTIES = loadProperties();
		// check for any override
		for(String key:PROPERTIES.stringPropertyNames()) {
			if(System.getProperties().containsKey(key)) {
				PROPERTIES.setProperty(key, System.getProperty(key));
			}
		}
		//print in the console
		log.info("Test Properties");
		for(String key:PROPERTIES.stringPropertyNames()) {
			log.info("{}={}",key,PROPERTIES.getProperty(key));
		}
	}
	
	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}
	
	public static Properties loadProperties() {
		Properties properties = new Properties();
		try(InputStream stream= ResourceLoader.getStream(DEFAULT_PROPERTIES)){
			properties.load(stream);
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error("Unable to read :",e);
		}
		return properties;
		
	}
}
