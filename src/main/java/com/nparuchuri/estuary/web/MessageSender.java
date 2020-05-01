package com.nparuchuri.estuary.web;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.nparuchuri.estuary.web.msg.WebMessage;

/**
 * 
 * @author Narendra
 *
 */
public class MessageSender {

	/**
	 * 
	 */
	private static Logger logger = LogManager.getLogger(MessageSender.class);

	/**
	 * 
	 */
	private static Gson gson = new Gson();

	/**
	 * 
	 * @param webClient
	 * @param type
	 * @param data
	 */
	public static void send(WebClient webClient, WebMessage.TYPE type, String data) {
		WebMessage webMsg = new WebMessage();
		webMsg.setType(type);
		webMsg.setClientUniqueId(webClient.getClientUniqueId());
		webMsg.setTarget(webClient.getTarget());
		webMsg.setData(data);
		String txtMsg = gson.toJson(webMsg);
		send(webClient, txtMsg);
	}

	/**
	 * 
	 * @param webClient
	 * @param data
	 */
	public static void send(WebClient webClient, String data) {
		try {
			logger.info("Sending to client : " + webClient.getSession().getId() + " message : " + data);
			webClient.getSession().getBasicRemote().sendText(data);
		} catch (IOException e) {
			logger.log(Level.ERROR, "Faild to send Server ACK, removing web session", e);
			ClientSessionHandler.removeSession(webClient);
		}
	}
}
