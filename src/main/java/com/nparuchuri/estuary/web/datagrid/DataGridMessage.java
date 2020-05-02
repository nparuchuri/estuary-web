package com.nparuchuri.estuary.web.datagrid;

import java.util.UUID;

import com.nparuchuri.estuary.web.msg.WebMessage;
import com.nparuchuri.estuary.web.server.ServerInstanceInfo;

/**
 * 
 * @author Narendra
 *
 */
public class DataGridMessage {
	
	private ServerInstanceInfo serverInstanceInfo;
	
	private WebMessage webMsg;
	
	private UUID dataGridNodeId;

	public DataGridMessage(ServerInstanceInfo serverInstanceInfo, WebMessage webPushmessage) {
		super();
		this.serverInstanceInfo = serverInstanceInfo;
		this.webMsg = webPushmessage;
	}

	public ServerInstanceInfo getServerInstanceInfo() {
		return serverInstanceInfo;
	}

	public WebMessage getWebMsg() {
		return webMsg;
	}

	public UUID getDataGridNodeId() {
		return dataGridNodeId;
	}

	public void setDataGridNodeId(UUID dataGridNodeId) {
		this.dataGridNodeId = dataGridNodeId;
	}

}
