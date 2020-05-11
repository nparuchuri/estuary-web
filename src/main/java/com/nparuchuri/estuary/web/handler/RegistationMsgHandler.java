package com.nparuchuri.estuary.web.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.nparuchuri.estuary.web.MessageSender;
import com.nparuchuri.estuary.web.TargetSessionMap;
import com.nparuchuri.estuary.web.WebClient;
import com.nparuchuri.estuary.web.WebClientMap;
import com.nparuchuri.estuary.web.msg.BaseMessage;
import com.nparuchuri.estuary.web.msg.WebMessage;

/**
 * 
 * @author Narendra
 *
 */
public class RegistationMsgHandler {
	
	/**
	 * 
	 */
	public static Logger logger = LogManager.getLogger(RegistationMsgHandler.class);
	
	/**
	 * 
	 */
	public static Gson gson = new Gson();
			
	/**
	 * 
	 * @param webClient
	 * @param message
	 */
	public static void handle(WebClient webClient, WebMessage message) {
		
		String target = message.getTarget();
		
		if ( target == null || target.trim().equals("")) {
			MessageSender.send(webClient, BaseMessage.TYPE.REG_ERROR, "Invalid Target, target is required");
		}
		else {
			webClient.setTarget(message.getTarget());
			TargetSessionMap.get().addOrUpdate(message.getTarget(), webClient.getSession().getId());
			logger.info("Target registered " +  webClient );
		}
		logger.debug("final Web Client Map {} ", WebClientMap.get().getWebclients());
		logger.debug("final Target Session Map {} " , TargetSessionMap.get().targetSessionMap());
	}

}
