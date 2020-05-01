package com.nparuchuri.estuary.web.server;

import java.util.Enumeration;

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
public class ServerInfoAppContextListener implements ServletContextListener {

	private static Logger logger = LogManager.getLogger(ServerInfoAppContextListener.class);

	public ServerInfoAppContextListener() {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		Enumeration<String> names = sce.getServletContext().getAttributeNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			logger.info("ServletContext attribute name " + name + " Value : " + sce.getServletContext().getAttribute(name));
		}
		
		ServerInstanceInfo.get().setServerName(sce.getServletContext().getVirtualServerName());
		logger.info("Server Instance Info " + ServerInstanceInfo.get());

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
