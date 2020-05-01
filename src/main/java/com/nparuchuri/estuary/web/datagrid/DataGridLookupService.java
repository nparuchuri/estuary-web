package com.nparuchuri.estuary.web.datagrid;

import com.nparuchuri.estuary.datagrid.ignite.IgniteDataGridService;

/**
 * 
 * @author Narendra
 *
 */
public class DataGridLookupService {

	/**
	 * 
	 */
	private static DataGridLookupService service;

	/**
	 * 
	 */
	private DataGridLookupService() {

	}

	/**
	 * 
	 * @return
	 */
	public static DataGridLookupService get() {

		if (service == null) {

			service = new DataGridLookupService();
		}

		return service;
	}
	
	
	public DataGridService lookup() {
		
		return IgniteDataGridService.get();
	}

}
