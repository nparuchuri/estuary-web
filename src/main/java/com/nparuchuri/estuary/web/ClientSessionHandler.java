package com.nparuchuri.estuary.web;

import java.net.URI;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nparuchuri.estuary.web.server.ServerInstanceInfo;


/**
 * 
 * @author Narendra
 *
 */
public class ClientSessionHandler {

	private static Logger logger = LogManager.getLogger(ClientSessionHandler.class);
	
	
	/**
	 * 
	 * @param session
	 */
	public static void setServerInfo(Session session) {
		session.getRequestParameterMap().forEach((k, v) -> {  logger.info( "Request Parameter key = " + k + " : value = " + v); } );
		URI requestURI = session.getRequestURI();
		logger.info("request URI string " + session.getRequestURI() );
		ServerInstanceInfo.get().setServerName(requestURI.getHost());
		ServerInstanceInfo.get().setPort(requestURI.getPort());
	}
	
	/**
	 * 
	 * @param session
	 */
	public static void createWebClient(Session session) {
		WebClient webClient = new WebClient(session);
		WebClientMap.get().addClient(webClient);
		logger.debug("final Web Client Map {} ", WebClientMap.get().getWebclients());
		logger.debug("final Target Session Map {} " , TargetSessionMap.get().targetSessionMap());
	}
	
	/**
	 * 
	 * @param webClient 
	 */
	public static void removeSession(WebClient webClient) {
		logger.info("WebClient " + webClient);
		TargetSessionMap.get().remove(webClient.getTarget(), webClient.getSession().getId());
		webClient = WebClientMap.get().removeClient(webClient.getSession().getId());
		logger.info("Removed from WebClientMap " + webClient);
		logger.info("Removed from TargetSessionMap ");
		logger.debug("final Web Client Map {} ", WebClientMap.get().getWebclients());
		logger.debug("final Target Session Map {} " , TargetSessionMap.get().targetSessionMap());
		
	}
	
	
	

}
