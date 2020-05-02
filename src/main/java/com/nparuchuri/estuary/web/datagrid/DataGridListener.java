package com.nparuchuri.estuary.web.datagrid;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nparuchuri.estuary.web.MessageSender;
import com.nparuchuri.estuary.web.TargetSessionMap;
import com.nparuchuri.estuary.web.TargetStringUtil;
import com.nparuchuri.estuary.web.WebClient;
import com.nparuchuri.estuary.web.WebClientMap;
import com.nparuchuri.estuary.web.msg.WebMessage;

/**
 * 
 * @author Narendra
 *
 */
public abstract class DataGridListener {

	private static Logger logger = LogManager.getLogger(DataGridListener.class);

	/**
	 * 
	 * @param message
	 */

	public DataGridListener() {
	}

	public abstract void onMessage(WebMessage message);

	/**
	 * 
	 * @param message
	 */
	protected void distributeMessage(WebMessage message) {
		
		String senderId = message.getClientUniqueId();

		logger.info("Distributing message : " + message);
		List<String> targetList = TargetStringUtil.getTargetList(message.getTarget());
		logger.debug("Conext matched targets " + targetList);

		List<String> sessionIdList = new ArrayList<String>();

		targetList.parallelStream().forEach((target) -> {
			List<String> sessionList = TargetSessionMap.get().getSessionList(target);
			logger.info("Conext matched target " + target + " sessions " + sessionList);
			if (sessionList != null) {
				sessionIdList.addAll(sessionList);
			}
		});

		sessionIdList.parallelStream().forEach((sessionId) -> {
				WebClient receiver = WebClientMap.get().getClient(sessionId);
				
				if ( receiver == null ) {
					logger.info(" Receiver WebClient for session id {} is null ", sessionId );
				}
				else if ( receiver.getClientUniqueId() == null ) {
					logger.info(" Receiver WebClient for Client Unique id {} is null ", sessionId );
				}
				if (receiver.getClientUniqueId().equals(senderId) ) {
					logger.info("Avoiding message echo to sender " + senderId );
				}
				else {
					MessageSender.send(receiver, message.getData());
				}
		});
	}

}
