package com.nparuchuri.estuary.web.datagrid;

import com.nparuchuri.estuary.web.msg.WebMessage;

/**
 * 
 * @author Narendra
 *
 */

public interface DataGridService {
	
	public void init(); 
		
	public void destory();
	
	public void put(WebMessage message);
	
}
