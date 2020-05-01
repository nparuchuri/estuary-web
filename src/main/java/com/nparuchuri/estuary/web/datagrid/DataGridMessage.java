package com.nparuchuri.estuary.web.datagrid;

import java.util.UUID;

import com.nparuchuri.estuary.web.msg.WebMessage;
import com.nparuchuri.estuary.web.server.ServerInstanceInfo;

public class DataGridMessage {
	
	private ServerInstanceInfo serverInstanceInfo;
	
	private WebMessage webPushmessage;
	
	private UUID dataGridNodeId;

	public DataGridMessage(ServerInstanceInfo serverInstanceInfo, WebMessage webPushmessage) {
		super();
		this.serverInstanceInfo = serverInstanceInfo;
		this.webPushmessage = webPushmessage;
	}

	public ServerInstanceInfo getServerInstanceInfo() {
		return serverInstanceInfo;
	}

	public WebMessage getWebPushmessage() {
		return webPushmessage;
	}

	public UUID getDataGridNodeId() {
		return dataGridNodeId;
	}

	public void setDataGridNodeId(UUID dataGridNodeId) {
		this.dataGridNodeId = dataGridNodeId;
	}

}
