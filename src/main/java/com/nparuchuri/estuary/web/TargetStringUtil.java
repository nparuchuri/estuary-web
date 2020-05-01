package com.nparuchuri.estuary.web;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Narendra
 *
 */
public class TargetStringUtil {
	
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static List<String> getTargetList(String target) {
		List<String> targetList = new ArrayList<String>();
		if ( target != null ) {
			char[] chars = target.toCharArray();
			StringBuffer sb = new StringBuffer();
			sb.append(chars[0]);
			if ( chars[0] == '/') {
				targetList.add(sb.toString());
			}
			for(short i = 1 ; i < chars.length;i++) {
				char c = chars[i];
				if ( c == '/') {
					targetList.add(sb.toString());
				}
				sb.append(c);
			}
			if ( chars.length > 1 ) {
				targetList.add(sb.toString());
			}
		}
		return targetList;
	}

}
