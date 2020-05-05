package com.nparuchuri.estuary.web.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nparuchuri.estuary.web.WebClient;
import com.nparuchuri.estuary.web.datagrid.DataGridLookupService;
import com.nparuchuri.estuary.web.datagrid.DataGridPutException;
import com.nparuchuri.estuary.web.msg.WebMessage;


/**
 * 
 * @author Narendra
 *
 */
public class PayLoadMsgHandler {
	
	/**
	 * 
	 */
	public static Logger logger = LogManager.getLogger(PayLoadMsgHandler.class);
	
	
	/**
	 * 
	 * @param webClient
	 * @param message
	 */
	
	public static void handle(WebClient webClient, WebMessage message) {
		try {
			DataGridLookupService.get().lookup().put(message);
		} catch (DataGridPutException e) {
			logger.log(Level.ERROR, "DataGrid put failed", e);
		}
	}

}
