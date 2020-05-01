package com.nparuchuri.estuary.web.server;

public class ServerInstanceInfo {
	
	private static ServerInstanceInfo info;
	
	private String serverName;
	
	private int port;
	
	private ServerInstanceInfo() {
		
	}
	
	public static ServerInstanceInfo get() {
		if ( info == null ) {
			
			info = new ServerInstanceInfo();
		}
		return info;
	}
	
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "ServerInstanceInfo [serverName=" + serverName + ", port=" + port + "]";
	}
	
	

}
