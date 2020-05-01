package com.nparuchuri.estuary.web;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Narendra
 *
 */
public class WebClientMap {

	/**
	 * 
	 */
	private static WebClientMap client;

	/**
	 * 
	 */
	private Map<String, WebClient> webclients;

	/**
	 * 
	 */
	public WebClientMap() {

		this.webclients = new HashMap<String, WebClient>();
	}

	/**
	 * 
	 * @return
	 */
	public static WebClientMap get() {
		if (client == null)
			client = new WebClientMap();
		return client;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, WebClient> getWebclients() {
		return this.webclients;
	}

	/**
	 * 
	 * @param session
	 */
	public void addClient(WebClient session) {
		this.webclients.put(session.getSession().getId(), session);
	}
	
	/**
	 * 
	 * @param sessionId
	 */
	public WebClient getClient(String sessionId) {
		return this.webclients.get(sessionId);
	}

	/**
	 * 
	 * @param session
	 */
	public WebClient removeClient(String sessionId) {
		return this.webclients.remove(sessionId);
	}
}
