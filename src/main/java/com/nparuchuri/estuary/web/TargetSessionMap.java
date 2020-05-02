package com.nparuchuri.estuary.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TargetSessionMap {
	
	private static Logger logger = LogManager.getLogger(TargetSessionMap.class);
	
	private static TargetSessionMap instance;
	
	private Map<String, List<String>> map;
	
	private TargetSessionMap () {
		
		this.map = new HashMap<String, List<String>>();
	}
	
	public static TargetSessionMap get() {
		
		if ( instance == null ) {
			instance = new TargetSessionMap();
		}
		return instance;
	}
	
	/**
	 * 
	 * @param target
	 * @param id
	 * @return
	 */
	public synchronized List<String> addOrUpdate(String target, String id) {
		
		List<String> sessionList = this.map.get(target);
		if ( sessionList == null ) {
			sessionList = new ArrayList<String>();
			sessionList.add(id);
			this.map.put(target, sessionList);
		}
		else {
			// check if target and session id already mapped. otherwise it creates duplicate list and sends duplicate messages. 
			boolean contains = sessionList.contains(id);
			if ( contains ) {
				logger.info("avoiding duplicate registration, target : " + target + " session id " + id);
			}
			else {
				sessionList.add(id);
			}
			
		}
		return sessionList;
	}
	
	/**
	 * Removes  all sub targets 
	 * @param target
	 * @param id
	 * @return
	 */
	public synchronized void remove(String target, String id) {
		if ( target == null ) return; 
		List<String> targetList = TargetStringUtil.getTargetList(target);
		targetList.forEach((t)-> {
			List<String> sessionList = this.map.get(target);
			if ( sessionList != null ) {
				sessionList.remove(id);
				if ( sessionList.size() == 0 ) {
					this.map.remove(target);
				}
			}
			
		} );
	}

	/**
	 * 
	 * @param target
	 * @return
	 */
	public List<String> getSessionList(String target) {
		return this.map.get(target);
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, List<String>> targetSessionMap() {
		return this.map;
	}
}
