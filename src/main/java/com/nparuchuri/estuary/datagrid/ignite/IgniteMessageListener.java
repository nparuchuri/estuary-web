package com.nparuchuri.estuary.datagrid.ignite;

import java.util.UUID;

import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.nparuchuri.estuary.web.datagrid.DataGridListener;
import com.nparuchuri.estuary.web.datagrid.DataGridMessage;
import com.nparuchuri.estuary.web.msg.WebMessage;

public class IgniteMessageListener extends DataGridListener implements IgniteBiPredicate<UUID, String>, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5662691821334886935L;
	
	private static Logger logger = LogManager.getLogger(IgniteMessageListener.class);
	
	private Gson gson;
	
	private UUID nodeId;
	
	public IgniteMessageListener(UUID nodeId ) {
		this.nodeId = nodeId;
		this.gson = new Gson();
	}
	
	
	@Override
	public boolean apply(UUID nodeId, String message) {
		logger.info("WebMessage received on " + this.nodeId + " event apply node id : " + nodeId +  " DataGridMessage : " + message);
		DataGridMessage dgMessage = this.gson.fromJson(message, DataGridMessage.class);
		onMessage(dgMessage.getWebMsg());
		return true;
	}

	@Override
	public void onMessage(WebMessage message) {
		distributeMessage(message);
		
		
	}

}
