package com.nparuchuri.estuary.web.msg;

public class WebOrderedMessage extends WebMessage {
	
	private boolean ordered;

	public WebOrderedMessage() {
		this.ordered = true;
	}
	
	public boolean isOrdered() {
		return this.ordered;
	}

}
