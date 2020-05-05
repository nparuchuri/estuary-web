package com.nparuchuri.estuary.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 
 * @author Narendra
 *
 */
@ApplicationPath("api")
public class EstuaryWebRestApplication extends Application {

	/**
	 * 
	 */
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(WebMessagePushAPI.class);
		return s;
	}
}
