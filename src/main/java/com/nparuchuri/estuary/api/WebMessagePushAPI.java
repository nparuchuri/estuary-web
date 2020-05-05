package com.nparuchuri.estuary.api;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.nparuchuri.estuary.web.datagrid.DataGridLookupService;
import com.nparuchuri.estuary.web.datagrid.DataGridPutException;
import com.nparuchuri.estuary.web.datagrid.DataGridService;
import com.nparuchuri.estuary.web.msg.WebMessage;

@Path("/pushmessage")
public class WebMessagePushAPI  {
	
	private static Logger logger = LogManager.getLogger(WebMessagePushAPI.class);
	
	private Gson gson;
	
	private DataGridService dgs;
	
	public WebMessagePushAPI() {
		this.gson = new Gson();
		this.dgs = DataGridLookupService.get().lookup();
		logger.info("apipush endpoint initialized");
		
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response postWebMessage(String message) {
		logger.debug( " message received : {} ", message);
		WebMessage webMsg = this.gson.fromJson(message, WebMessage.class);
		Response response = null;
		try {
			this.dgs.put(webMsg);
			response = Response.status(Response.Status.OK).entity("Sucess").build();
		} catch (DataGridPutException e) {
			logger.log(Level.ERROR, "exception in writing to datagrid ", e);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed - " + e.getMessage()).build();
		}	
		return response;
	}

}
