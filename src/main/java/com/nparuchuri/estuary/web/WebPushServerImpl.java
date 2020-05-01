package com.nparuchuri.estuary.web;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.nparuchuri.estuary.web.handler.PayLoadMsgHandler;
import com.nparuchuri.estuary.web.handler.RegistationMsgHandler;
import com.nparuchuri.estuary.web.msg.WebMessage;

/**
 * 
 * @author Narendra
 *
 */
@ServerEndpoint("/webpuhserver")
public class WebPushServerImpl {
	
	private static Logger logger = LogManager.getLogger(WebPushServerImpl.class);
	
	private Gson gson;

	/**
	 * 
	 */
	public WebPushServerImpl() {
		logger.info("WebPushServerImpl initialized");
		this.gson = new Gson();
	}

	/**
	 * 
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
		ClientSessionHandler.setServerInfo(session);
		ClientSessionHandler.createwebClient(session);
	}

	/**
	 * 
	 * @param message
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("message received on session " + session.getId() + " raw message : " + message);
		WebMessage wpMessage = this.gson.fromJson(message, WebMessage.class);
		WebClient webClient = WebClientMap.get().getClient(session.getId());
		if ( wpMessage.getType() == null ) {
			MessageSender.send(webClient, WebMessage.TYPE.ERROR, "Message type is required" );
		}
		else if ( wpMessage.getType() == WebMessage.TYPE.REG ) {
			
			RegistationMsgHandler.handle(webClient, wpMessage);
			
		} else if ( wpMessage.getType() == WebMessage.TYPE.PAL ) {
			
			PayLoadMsgHandler.handle(webClient, wpMessage);
		}
	}


	/**
	 * 
	 * @param session
	 * @param closeReason
	 */

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info("Disconnected session " + session.getId() + " reason : " + closeReason.getCloseCode() + " : "
				+ closeReason.getReasonPhrase());
		WebClient webClient = WebClientMap.get().getClient(session.getId());		
		ClientSessionHandler.removeSession(webClient);
	}
	
}
