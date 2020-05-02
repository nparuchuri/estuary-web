package com.nparuchuri.estuary.web;

import java.net.URI;

import javax.websocket.Session;

/**
 * 
 * @author Narendra
 *
 */
public class WebClient {

	private String target;

	private String clientUniqueId;

	private Session session;

	/**
	 * 
	 * @param target
	 * @param session
	 */
	public WebClient(Session session) {
		super();
		this.session = session;
		this.target = session.getPathParameters().get("target");
		this.clientUniqueId = session.getPathParameters().get("clientUniqueId");
		if (this.clientUniqueId == null || this.clientUniqueId.equals("")) {
			URI requestURI = session.getRequestURI();
			String hostName = requestURI.getHost();
			String port = Integer.toString(requestURI.getPort());
			String sessionId = session.getId();
			this.clientUniqueId = System.currentTimeMillis() + "-" + hostName + "-" + port + "-" + sessionId;
		}
	}

	/**
	 * 
	 * @param target
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * 
	 * @return
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * 
	 * @return
	 */
	public Session getSession() {
		return session;
	}

	public String getClientUniqueId() {
		return clientUniqueId;
	}

	@Override
	public String toString() {
		String id = "null";
		if (this.session != null)
			id = this.session.getId();
		return "WebClient [target=" + target + ", clientUniqueId=" + clientUniqueId + ", session id =" + id + "]";
	}
}
