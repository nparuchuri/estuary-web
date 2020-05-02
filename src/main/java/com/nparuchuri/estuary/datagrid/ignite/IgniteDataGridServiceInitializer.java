package com.nparuchuri.estuary.datagrid.ignite;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Narendra
 *
 */
@WebListener
public class IgniteDataGridServiceInitializer implements ServletContextListener {
	
	private static Logger logger = LogManager.getLogger(IgniteDataGridServiceInitializer.class);
	
	private IgniteDataGridService idgs;
	
	public IgniteDataGridServiceInitializer() {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Starting Ignite Node after " + sce.getServletContext().getContextPath() + " initialized");
		if ( idgs == null ) {
			idgs = IgniteDataGridService.get();
			idgs.init();
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Stopping Ignite Node after " + sce.getServletContext().getContextPath() + " destroyed");
		idgs.destory();
	}

}
