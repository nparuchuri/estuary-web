package com.nparuchuri.estuary.web.msg;

/**
 * 
 * @author Narendra
 *
 */
public class WebMessage extends BaseMessage {
	
	
	
	private WebMessage.TYPE type;
	
	private String target;
	
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

	@Override
	public String toString() {
		return "WebMessage [type=" + type + ", senderId=" + senderId + ", target=" + target + ", data="
				+ data + "]";
	}
	
	
	
}
