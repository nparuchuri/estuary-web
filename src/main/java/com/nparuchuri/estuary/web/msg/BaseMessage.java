package com.nparuchuri.estuary.web.msg;

public class BaseMessage {

	public static enum TYPE  { REG, REG_ERROR, PAL, PAL_ERROR, ERROR };
	
	protected String senderId;
	
	protected String data;

	public BaseMessage() {
		super();
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

}