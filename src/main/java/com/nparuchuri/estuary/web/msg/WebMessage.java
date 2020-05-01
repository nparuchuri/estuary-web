package com.nparuchuri.estuary.web.msg;

/**
 * 
 * @author Narendra
 *
 */
public class WebMessage {
	
	public static enum TYPE  { ERROR, PAL, PAL_ERROR, REG , REG_ERROR, SACK };
	
	private WebMessage.TYPE type;
	
	private String clientUniqueId;
	
	private String target;
	
	private String data;
	
	public WebMessage.TYPE getType() {
		return type;
	}

	public void setType(WebMessage.TYPE type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getClientUniqueId() {
		return clientUniqueId;
	}

	public void setClientUniqueId(String sessionId) {
		this.clientUniqueId = sessionId;
	}

	@Override
	public String toString() {
		return "WebMessage [type=" + type + ", clientUniqueId=" + clientUniqueId + ", target=" + target + ", data="
				+ data + "]";
	}
	
	
	
}
