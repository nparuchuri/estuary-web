package com.nparuchuri.estuary.web;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class TargetSessionMap {
	
	private static TargetSessionMap instance;
	
	private Hashtable<String, List<String>> map;
	
	private TargetSessionMap () {
		
		this.map = new Hashtable<String, List<String>>();
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
			sessionList.add(id);
		}
		return sessionList;
	}
	
	/**
	 * 
	 * @param target
	 * @param id
	 * @return
	 */
	public synchronized boolean remove(String target, String id) {
		
		if ( target == null ) return false;
		
		boolean removed = false;
		List<String> sessionList = this.map.get(target);
		
		if ( sessionList != null ) {
			
			if ( sessionList.remove(id) ) {
				removed = true;
			}
			if ( sessionList.size() == 0 ) {
				this.map.remove(target);
			}
		}
		return removed;
	}

	/**
	 * 
	 * @param target
	 * @return
	 */
	public List<String> getSessionList(String target) {
		return this.map.get(target);
	}
	
}
