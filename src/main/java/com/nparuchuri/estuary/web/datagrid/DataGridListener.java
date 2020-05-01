package com.nparuchuri.estuary.web.datagrid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.nparuchuri.estuary.web.MessageSender;
import com.nparuchuri.estuary.web.TargetSessionMap;
import com.nparuchuri.estuary.web.TargetStringUtil;
import com.nparuchuri.estuary.web.WebClientMap;
import com.nparuchuri.estuary.web.msg.WebMessage;

/**
 * 
 * @author Narendra
 *
 */
public abstract class DataGridListener {

	private static Logger logger = LogManager.getLogger(DataGridListener.class);

	private Gson gson;

	/**
	 * 
	 * @param message
	 */

	public DataGridListener() {
		this.gson = new Gson();
	}

	public abstract void onMessage(WebMessage message);

	/**
	 * 
	 * @param message
	 */
	protected void distributeMessage(WebMessage message) {

		logger.info("Distributing message : " + message);
		List<String> targetList = TargetStringUtil.getTargetList(message.getTarget());
		logger.debug("Conext matched targets " + targetList);

		List<String> sessionIdList = new ArrayList<String>();

		targetList.parallelStream().forEach((target) -> {
			List<String> sessionList = TargetSessionMap.get().getSessionList(target);
			logger.debug("Conext matched target " + target + " sessions " + sessionList);
			if (sessionList != null) {
				sessionIdList.addAll(sessionList);
			}
		});

		sessionIdList.parallelStream().forEach((sessionId) -> {
				MessageSender.send(WebClientMap.get().getClient(sessionId), message.getData());
		});
	}

}
