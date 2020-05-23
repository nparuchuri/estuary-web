package com.nparuchuri.estuary.web.resource;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Narendra
 *
 */

public class WebResourceConfig {
	
	private static String FILE_NAME = "estuary-web.config";
	
	private Logger logger = LogManager.getLogger(WebResourceConfig.class);

	private static WebResourceConfig its;
	
	private Properties props;
	
	/**
	 * 
	 */
	private WebResourceConfig() {
		this.props = new Properties();
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			this.props.load(classLoader.getResourceAsStream(FILE_NAME));
		} catch (IOException e) {
			logger.log(Level.ERROR, "loading estulary-web.config failed", e);
			throw new RuntimeException("loading estulary-web.config failed", e);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static WebResourceConfig get() {
		if ( its == null ) {
			its = new WebResourceConfig();
		}
		return its;
	}
	
	public String getProperty(String name) {
		return this.props.getProperty(name);
	}
	
}
